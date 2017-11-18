package com.to.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

import java.io.*;
import java.util.*;

public class HttpClientUtil {
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
	public static Map<String,String> doGet(String url) throws Exception{
		Map<String,String> map=new HashMap<String,String>();
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
					JSONObject obj=JSONObject.fromObject(response);
					JSONArray arr=JSONArray.fromObject(obj.get("geocodes"));
						JSONObject o=JSONObject.fromObject(arr.get(0));
						String[] s=o.get("location").toString().split(",");
						map.put("latitude",s[1]);
						map.put("longitude",s[0]);
				}
			} catch (Exception e){
				throw new Exception(e);
			} finally{
				httpResponse.close();
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				get.releaseConnection();
				closeHttpClient(httpClient);
			} catch (IOException e) {
				throw new Exception(e);
			}
		}
		return map;

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
	public static void main(String[] args) throws Exception {
		String url="http://restapi.amap.com/v3/geocode/geo?key=80eb6e5d424ed185d20da350aaefd75a&address=杭州市滨江区滨和路998号1幢701室";
		Map<String,String> map=doGet(url);
		String latitude=map.get("latitude");
		String longitude=map.get("longitude");
		System.out.println(latitude+":"+longitude);

	}
}
