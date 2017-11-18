package com.jxc.cn;

import java.util.Date;
import java.util.Timer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class token {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 0, 7100 * 1000);
	}

	static class MyTask extends java.util.TimerTask {
		@Override
		public void run() {
			String apiurl = "https://api.weixin.qq.com/cgi-bin/token";
			String appid = "wxcbc3a5073330147a";
			String secret ="eae3313909ca82bd0b39ac1391e5ccfb";
			String result = "";
			String turl = String.format(
					"%s?grant_type=client_credential&appid=%s&secret=%s",
					apiurl, appid, secret);
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(turl);
			try {
				HttpResponse res = client.execute(get);
				String responseContent = null; // 响应内容
				HttpEntity entity = res.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
				JSONObject json = new JSONObject(responseContent);
				// 将json字符串转换为json对象
				if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					if (json.get("access_token") == null) {
						// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
						System.out.println(json.get("errmsg").toString());
					} else {
						// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
						result = json.get("access_token").toString();
						String ticket = "";
						String apiurl2 = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
						String type = "jsapi";
						ticket = Test2.getToken(apiurl2, result, type);
						/*
						 * System.out.println("------------");
						 * System.out.println("实例�?���?);
						 * System.out.println(result);
						 * System.out.println("------------");
						 * System.out.println("实例化内存对�?);
						 */
						CacheMgr cacheMgr = CacheMgr.getInstance();
						if (cacheMgr.getCache("access_token") == null) {
							CacheConfModel cacheConfModel = new CacheConfModel();
							cacheConfModel.setBeginTime((new Date()).getTime());
							cacheConfModel.setDurableTime(7100 * 1000);
							cacheMgr.addCache("access_token", result,
									cacheConfModel);
						} else {
							cacheMgr.removeCache("access_token");
							CacheConfModel cacheConfModel = new CacheConfModel();
							cacheConfModel.setBeginTime((new Date()).getTime());
							cacheConfModel.setDurableTime(7100 * 1000);
							cacheMgr.addCache("access_token", result,
									cacheConfModel);
						}
						if (cacheMgr.getCache("jsapi_ticket") == null) {
							CacheConfModel cacheConfModel2 = new CacheConfModel();
							cacheConfModel2
									.setBeginTime((new Date()).getTime());
							cacheConfModel2.setDurableTime(7100 * 1000);
							// System.out.println("将结果放入内�?);
							cacheMgr.addCache("jsapi_ticket", ticket,
									cacheConfModel2);
						} else {
							cacheMgr.removeCache("jsapi_ticket");
							CacheConfModel cacheConfModel2 = new CacheConfModel();
							cacheConfModel2
									.setBeginTime((new Date()).getTime());
							cacheConfModel2.setDurableTime(7100 * 1000);
							// System.out.println("将结果放入内�?);
							cacheMgr.addCache("jsapi_ticket", ticket,
									cacheConfModel2);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭连接 ,释放资源
				get.releaseConnection();
				client.getConnectionManager().shutdown();
				get = null;
				client = null;
			}
		}
	}
}