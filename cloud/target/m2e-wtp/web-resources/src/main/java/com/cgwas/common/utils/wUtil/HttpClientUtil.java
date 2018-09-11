package com.cgwas.common.utils.wUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
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
	
	public static String postString(String url, String json) {
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(url);
		String response = null;
		try {
			post.setRequestEntity(new StringRequestEntity(json, "application/json", "UTF-8"));
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
    /**
     * post请求,参数json
     *
     * @param url
     * @param json
     * @return
     */
	public static String postJson(String url, net.sf.json.JSONObject json) {

		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(url);
		String response = null;
		InputStream inputStream = null;
		BufferedReader br = null;
		try {
			post.setRequestEntity(new StringRequestEntity(json.toString(), "application/json", "UTF-8"));
			httpclient.executeMethod(post);
			inputStream = post.getResponseBodyAsStream();
			br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str;
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str.replace("\\", ""));
			}
			response = stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("微信公众号推送异常打印", "");
		} finally {
			try {
				if (inputStream != null) inputStream.close();
				if (br != null) inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			post.releaseConnection();
			httpclient.getHttpConnectionManager().closeIdleConnections(0);
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
        InputStream inputStream = null;
		BufferedReader br = null;
        try {
            post.setRequestEntity(new StringRequestEntity(json.toString(), "application/json", "UTF-8"));
            httpclient.executeMethod(post);
            inputStream = post.getResponseBodyAsStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str.replace("\\", ""));
            }
            response = stringBuffer.toString();
            if (inputStream != null) inputStream.close();
			if (br != null) inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
            httpclient.getHttpConnectionManager().closeIdleConnections(0);
        }
        return response;
    }

}
