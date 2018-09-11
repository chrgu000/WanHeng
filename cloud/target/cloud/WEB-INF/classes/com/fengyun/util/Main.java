package com.fengyun.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**
 * 视频点播OpenAPI调用示例
 * 以GetVideoPlayAuth接口为例，其他接口请替换相应接口名称及私有参数
 */
public class Main {
    //账号AK信息请填写(必选)
    private static String access_key_id = "LTAIEF8Ez9nRobW5";
    //账号AK信息请填写(必选)
    private static String access_key_secret = "A9zmv15Z3VdHxefqP41aXxgtMKDuX8";
    //STS临时授权方式访问时该参数为必选，使用主账号AK和RAM子账号AK不需要填写
    private static String security_token = "";
    //以下参数不需要修改
    private final static String VOD_DOMAIN = "http://live.aliyuncs.com/";
    private final static String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String HTTP_METHOD = "GET";
    private final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private final static String UTF_8 = "utf-8";
    private final static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        //生成私有参数，不同API需要修改
        Map<String, String> privateParams = generatePrivateParamters();
        //生成公共参数，不需要修改
        Map<String, String> publicParams = generatePublicParamters();
        //生成OpenAPI地址，不需要修改
        String URL = generateOpenAPIURL(publicParams, privateParams);
        System.out.println(URL);
        //发送HTTP GET 请求
        httpGet(URL);
        
    }
    /**
     * 生成视频点播OpenAPI私有参数
     * 不同API需要修改此方法中的参数
     *
     * @return
     */
    private static Map<String, String> generatePrivateParamters() {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<String,String>();
        privateParams.put("Action", "AddLiveAppRecordConfig");
        privateParams.put("AppName", "J");
        privateParams.put("DomainName", "live.woheyun.com");
        privateParams.put("OssBucket", "yuncloudvideo");
        privateParams.put("OssEndpoint", "oss-cn-shanghai.aliyuncs.com");
        privateParams.put("RecordFormat.1.CycleDuration", "3600");
        privateParams.put("RecordFormat.1.Format", "m3u8");
        privateParams.put("RecordFormat.1.OssObjectPrefix", "record/AppName/StreamName/{Sequence}{EscapedStartTime}{EscapedEndTime}");
        privateParams.put("RecordFormat.1.SliceOssObjectPrefix", "record/AppName/StreamName/{UnixTimestamp}{Sequence}");
        return privateParams;
    }
    /**
     * 生成视频点播OpenAPI公共参数
     * 不需要修改
     *
     * @return
     */
    private static Map<String, String> generatePublicParamters() {
        Map<String, String> publicParams = new HashMap<String,String>();
        publicParams.put("Format", "JSON");
        publicParams.put("AccessKeyId", access_key_id);
        publicParams.put("SignatureMethod", "HMAC-SHA1");
        publicParams.put("SignatureVersion", "1.0");
        publicParams.put("SignatureNonce", generateRandom());
        publicParams.put("Timestamp", generateTimestamp());
        publicParams.put("Version", "2016-11-01");
        if (security_token != null && security_token.length() > 0) {
            publicParams.put("SecurityToken", security_token);
        }
        return publicParams;
    }

    /**
     * 生成OpenAPI地址
     * @param privateParams
     * @return
     * @throws Exception
     */
    private static String generateOpenAPIURL(Map<String, String> publicParams, Map<String, String> privateParams) {
        return generateURL(VOD_DOMAIN, HTTP_METHOD, publicParams, privateParams);
    }

    /**
     * @param domain        请求地址
     * @param httpMethod    HTTP请求方式GET，POST等
     * @param publicParams  公共参数
     * @param privateParams 接口的私有参数
     * @return 最后的url
     */
    private static String generateURL(String domain, String httpMethod, Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> allEncodeParams = getAllParams(publicParams, privateParams);
        String cqsString = getCQS(allEncodeParams);
        out("CanonicalizedQueryString = " + cqsString);
        String stringToSign = httpMethod + "&" + percentEncode("/") + "&" + percentEncode(cqsString);
        out("StringtoSign = " + stringToSign);
        String signature = hmacSHA1Signature(access_key_secret, stringToSign);
        out("Signature = " + signature);
        return domain + "?" + cqsString + "&" + percentEncode("Signature") + "=" + percentEncode(signature);
    }

    private static List<String> getAllParams(Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> encodeParams = new ArrayList<String>();
        if (publicParams != null) {
            for (String key : publicParams.keySet()) {
                String value = publicParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        if (privateParams != null) {
            for (String key : privateParams.keySet()) {
                String value = privateParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        return encodeParams;
    }

    /**
     * 参数urlEncode
     *
     * @param value
     * @return
     */
    private static String percentEncode(String value) {
        try {
            String urlEncodeOrignStr = URLEncoder.encode(value, "UTF-8");
            String plusReplaced = urlEncodeOrignStr.replace("+", "%20");
            String starReplaced = plusReplaced.replace("*", "%2A");
            String waveReplaced = starReplaced.replace("%7E", "~");
            return waveReplaced;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取CQS 的字符串
     *
     * @param allParams
     * @return
     */
    private static String getCQS(List<String> allParams) {
        ParamsComparator paramsComparator = new ParamsComparator();
        Collections.sort(allParams, paramsComparator);
        String cqString = "";
        for (int i = 0; i < allParams.size(); i++) {
            cqString += allParams.get(i);
            if (i != allParams.size() - 1) {
                cqString += "&";
            }
        }

        return cqString;
    }

    private static class ParamsComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }

    private static String hmacSHA1Signature(String accessKeySecret, String stringtoSign) {
        try {
            String key = accessKeySecret + "&";
            try {
                SecretKeySpec signKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
                Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
                mac.init(signKey);
                byte[] rawHmac = mac.doFinal(stringtoSign.getBytes());
                //按照Base64 编码规则把上面的 HMAC 值编码成字符串，即得到签名值（Signature）
                return new String(new BASE64Encoder().encode(rawHmac));
            } catch (Exception e) {
                throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
            }
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成随机数
     *
     * @return
     */
    private static String generateRandom() {
        String signatureNonce = UUID.randomUUID().toString();
        return signatureNonce;
    }

    /**
     * 生成当前UTC时间戳
     *
     * @return
     */
    public static String generateTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    private static String httpGet(String url) throws IOException {
        out("URL = " +  url);
        @SuppressWarnings("resource")
        Scanner s = new Scanner(new URL(url).openStream(), UTF_8).useDelimiter("\\A");
        try {
            String resposne = s.hasNext() ? s.next() : "true";
            out("Response = " + resposne);
            return resposne;
        } finally {
            s.close();
        }
    }

    private static void out(String newLine) {
        LOG.log(Level.INFO, newLine);
    }
}