package com.cgwas.common.utils.withdraw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
    private final static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    public static CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    public static void closeHttpClient(CloseableHttpClient client) throws IOException {
        if (client != null) {
            client.close();
        }
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        String response = null;
        // 创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        // 用get方法发送http请求
        HttpGet get = new HttpGet(url);
        try {
            CloseableHttpResponse httpResponse = null;
            // 发送get请求
            httpResponse = httpClient.execute(get);
            try {
                // response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    // System.out.println("响应状态码:" +
                    // httpResponse.getStatusLine() + "," + HttpStatus.SC_OK);
                    // System.out.println("-------------------------------------------------");
                    // System.out.println("响应内容:" +
                    // EntityUtils.toString(entity, "UTF-8"));
                    // System.out.println("-------------------------------------------------");
                    response = EntityUtils.toString(entity, "UTF-8");
                    ;
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                get.releaseConnection();
                closeHttpClient(httpClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;

    }

    /**
     * post请求，带普通参数
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = getHttpClient();
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        HttpPost post = new HttpPost(url);
        String response = null;
        try {
            // 创建参数
            for (String key : keySet) {
                list.add(new BasicNameValuePair(key, params.get(key)));
            }
            // url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
            post.setEntity(uefEntity);
            // System.out.println("POST 请求...." + post.getURI());
            // 执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    // System.out.println("-------------------------------------------------------");
                    // System.out.println(EntityUtils.toString(entity,
                    // "UTF-8"););
                    // System.out.println("-------------------------------------------------------");
                    response = EntityUtils.toString(entity, "UTF-8");
                    ;
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                post.releaseConnection();
                closeHttpClient(httpClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * post请求,参数json
     *
     * @param url
     * @param json
     * @return
     */

    public static String postJson(String url, JSONObject json) {
        HttpClient httpclient = new HttpClient();
        PostMethod post = new PostMethod(url);
        String response = null;
        try {
            post.setRequestEntity(new StringRequestEntity(json.toString(), "application/json", "UTF-8"));
            httpclient.executeMethod(post);
            InputStream inputStream = post.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str.replace("\\", ""));
            }
            response = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
            httpclient.getHttpConnectionManager().closeIdleConnections(0);
        }
        return response;
    }

    public static String postXml(String url, String xml) {
        HttpClient httpclient = new HttpClient();
        PostMethod post = new PostMethod(url);
        String response = null;
        try {
            post.setRequestEntity(new StringRequestEntity(xml, "text/xml", "GBK"));
            httpclient.executeMethod(post);
            InputStream inputStream = post.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str.replace("\\", ""));
            }
            response = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
            httpclient.getHttpConnectionManager().closeIdleConnections(0);
        }
        return response;
    }

    /**
     * 发送https请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return 返回微信服务器响应的信息
     */
//    public static String httpsRequest(String requestUrl, String requestMethod,
//                                      String outputStr) {
//        try {
//            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
//            TrustManager[] tm = {new MyX509TrustManager()};
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//            sslContext.init(null, tm, new java.security.SecureRandom());
//            // 从上述SSLContext对象中得到SSLSocketFactory对象
//            SSLSocketFactory ssf = sslContext.getSocketFactory();
//            URL url = new URL(requestUrl);
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setSSLSocketFactory(ssf);
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            // 设置请求方式（GET/POST）
//            conn.setRequestMethod(requestMethod);
//            conn.setRequestProperty("content-type",
//                    "application/x-www-form-urlencoded");
//            // 当outputStr不为null时向输出流写数据
//            if (null != outputStr) {
//                OutputStream outputStream = conn.getOutputStream();
//                // 注意编码格式
//                outputStream.write(outputStr.getBytes("UTF-8"));
//                outputStream.close();
//            }
//            // 从输入流读取返回内容
//            InputStream inputStream = conn.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(
//                    inputStream, "utf-8");
//            BufferedReader bufferedReader = new BufferedReader(
//                    inputStreamReader);
//            String str = null;
//            StringBuffer buffer = new StringBuffer();
//            while ((str = bufferedReader.readLine()) != null) {
//                buffer.append(str);
//            }
//            // 释放资源
//            bufferedReader.close();
//            inputStreamReader.close();
//            inputStream.close();
//            inputStream = null;
//            conn.disconnect();
//            return buffer.toString();
//        } catch (ConnectException ce) {
//            log.error("连接超时：{}", ce);
//        } catch (Exception e) {
//            log.error("https请求异常：{}", e);
//        }
//        return null;
//    }

    /**
     * 通过GET方式发起http请求
     */
    public void requestByGetMethod() {
        // 创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        try {
            // 用get方法发送http请求
            HttpGet get = new HttpGet("http://www.baidu.com");
            System.out.println("执行get请求:...." + get.getURI());
            CloseableHttpResponse httpResponse = null;
            // 发送get请求
            httpResponse = httpClient.execute(get);
            try {
                // response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    System.out.println("响应状态码:" + httpResponse.getStatusLine() + "," + HttpStatus.SC_OK);
                    System.out.println("-------------------------------------------------");
                    System.out.println("响应内容:" + EntityUtils.toString(entity, "UTF-8"));
                    System.out.println("-------------------------------------------------");
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeHttpClient(httpClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * POST方式发起http请求
     */
    public void requestByPostMethod() {
        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpPost post = new HttpPost("http://www.baidu.com"); // 这里用上本机的某个工程做测试
            // 创建参数列表
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("j_username", "admin"));
            list.add(new BasicNameValuePair("j_password", "admin"));
            // url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
            post.setEntity(uefEntity);
            System.out.println("POST 请求...." + post.getURI());
            // 执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    System.out.println("-------------------------------------------------------");
                    System.out.println(EntityUtils.toString(entity, "UTF-8"));
                    System.out.println("-------------------------------------------------------");
                }
            } finally {
                httpResponse.close();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeHttpClient(httpClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
