package com.fengyun.video.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class BaseTransCode {
	// 账号AK信息请填写(必选)
	protected static String access_key_id = "LTAIb0sGSGN9T3i0";
	// 账号AK信息请填写(必选)
	protected static String access_key_secret = "erTfHnefUebWAQrThN1synKAcr32Gx";
	// STS临时授权方式访问时该参数为必选，使用主账号AK和RAM子账号AK不需要填写
	protected static String security_token = "";
	// 以下参数不需要修改
	protected final static String TRANS_CODE = "http://mts.cn-hangzhou.aliyuncs.com/";
	protected final static String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	protected final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	protected final static String HTTP_METHOD = "GET";
	protected final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	protected final static String UTF_8 = "utf-8";
	private final static Logger LOG = Logger.getLogger(BaseTransCode.class
			.getName());
	/**
	 * 生成OpenAPI地址
	 * 
	 * @param protectedParams
	 * @return
	 * @throws Exception
	 */
	protected static String generateOpenAPIURL(
			Map<String, String> publicParams,
			Map<String, String> protectedParams) {
		return generateURL(TRANS_CODE, HTTP_METHOD, publicParams,
				protectedParams);
	}

	/**
	 * @param domain
	 *            请求地址
	 * @param httpMethod
	 *            HTTP请求方式GET，POST等
	 * @param publicParams
	 *            公共参数
	 * @param protectedParams
	 *            接口的私有参数
	 * @return 最后的url
	 */
	protected static String generateURL(String domain, String httpMethod,
			Map<String, String> publicParams,
			Map<String, String> protectedParams) {
		List<String> allEncodeParams = getAllParams(publicParams,
				protectedParams);
		String cqsString = getCQS(allEncodeParams);
		out("CanonicalizedQueryString = " + cqsString);
		String stringToSign = httpMethod + "&" + percentEncode("/") + "&"
				+ percentEncode(cqsString);
		out("StringtoSign = " + stringToSign);
		String signature = hmacSHA1Signature(access_key_secret, stringToSign);
		out("Signature = " + signature);
		return domain + "?" + cqsString + "&" + percentEncode("Signature")
				+ "=" + percentEncode(signature);
	}

	protected static List<String> getAllParams(
			Map<String, String> publicParams,
			Map<String, String> protectedParams) {
		List<String> encodeParams = new ArrayList<String>();
		if (publicParams != null) {
			for (String key : publicParams.keySet()) {
				String value = publicParams.get(key);
				// 将参数和值都urlEncode一下。
				String encodeKey = percentEncode(key);
				String encodeVal = percentEncode(value);
				encodeParams.add(encodeKey + "=" + encodeVal);
			}
		}
		if (protectedParams != null) {
			for (String key : protectedParams.keySet()) {
				String value = protectedParams.get(key);
				// 将参数和值都urlEncode一下。
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
	protected static String percentEncode(String value) {
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
	protected static String getCQS(List<String> allParams) {
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

	protected static class ParamsComparator implements Comparator<String> {
		@Override
		public int compare(String lhs, String rhs) {
			return lhs.compareTo(rhs);
		}
	}

	protected static String hmacSHA1Signature(String accessKeySecret,
			String stringtoSign) {
		try {
			String key = accessKeySecret + "&";
			try {
				SecretKeySpec signKey = new SecretKeySpec(key.getBytes(),
						HMAC_SHA1_ALGORITHM);
				Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
				mac.init(signKey);
				byte[] rawHmac = mac.doFinal(stringtoSign.getBytes());
				// 按照Base64 编码规则把上面的 HMAC 值编码成字符串，即得到签名值（Signature）
				return new String(new BASE64Encoder().encode(rawHmac));
			} catch (Exception e) {
				throw new SignatureException("Failed to generate HMAC : "
						+ e.getMessage());
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
	protected static String generateRandom() {
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

	protected static String httpGet(String url) throws IOException {
		out("URL = " + url);
		@SuppressWarnings("resource")
		Scanner s = new Scanner(new URL(url).openStream(), UTF_8)
				.useDelimiter("\\A");
		try {
			String resposne = s.hasNext() ? s.next() : "true";
			out("Response = " + resposne);
			return resposne;
		} finally {
			s.close();
		}
	}

	/**
	 * 生成视频点播OpenAPI公共参数 不需要修改
	 * 
	 * @return
	 */
	protected static Map<String, String> generatePublicParamters() {
		Map<String, String> publicParams = new HashMap<String, String>();
		publicParams.put("Format", "JSON");
		publicParams.put("AccessKeyId", access_key_id);
		publicParams.put("SignatureMethod", "HMAC-SHA1");
		publicParams.put("SignatureVersion", "1.0");
		publicParams.put("SignatureNonce", generateRandom());
		publicParams.put("Timestamp", generateTimestamp());
		publicParams.put("Version", "2014-06-18");
		return publicParams;
	}

	protected static void out(String newLine) {
		LOG.log(Level.INFO, newLine);
	}
}
