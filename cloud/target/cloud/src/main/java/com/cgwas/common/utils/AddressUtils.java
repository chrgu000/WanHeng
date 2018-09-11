package com.cgwas.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 根据IP地址获取详细的地域信息 淘宝API :
 * http://ip.taobao.com/service/getIpInfo.php?ip=218.192.3.42 新浪API :
 * http://int.
 * dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.192.3.42
 * 
 * @File AddressUtils.java
 * @Package org.gditc.weicommunity.util
 * @Description TODO
 * @Copyright Copyright © 2014
 * @Site https://github.com/Cryhelyxx
 * @Blog http://blog.csdn.net/Cryhelyxx
 * @Email cryhelyxx@gmail.com
 * @Company GDITC
 * @Date 2014年11月6日 下午1:46:37
 * @author Cryhelyxx
 * @version 1.0
 */
public class AddressUtils {
	/**
	 * 
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getAddresses(String content, String encodingString)
			throws UnsupportedEncodingException {
		// 这里调用淘宝API
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		// 从http://whois.pconline.com.cn取得IP所在的省市区信息
		String returnStr = getResult(urlStr, content, encodingString);
		if (returnStr != null) {
			// 处理返回的省市区信息
			// System.out.println("(1) unicode转换成中文前的returnStr : " + returnStr);
			returnStr = decodeUnicode(returnStr);
			// System.out.println("(2) unicode转换成中文后的returnStr : " + returnStr);
			String[] temp = returnStr.split(",");
			if (temp.length < 3) {
				return "0";// 无效IP，局域网测试
			}
			return returnStr;
		}
		return null;
	}

	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	private static String getResult(String urlStr, String content,
			String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());// 打开输出流往对端服务器写数据
			out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.flush();// 刷新
			out.close();// 关闭输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}

	/**
	 * unicode 转换成 中文
	 * 
	 * @author fanhui 2007-3-15
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}

	// 测试
	public static void main(String[] args) throws Exception {
		System.out.println(getCityByIP("192.168.16.236"));
	}

	public static Map<String,Object> getAddressByIp(String ip) throws Exception {
		// json_result用于接收返回的json数据
		String json_result = null;
		Map<String,Object> map= new HashMap<String,Object>();
		try {
			json_result = AddressUtils.getAddresses("ip=" + ip, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(json_result);
		System.out.println("json数据： " + json);
		String country = JSONObject.fromObject(json.get("data")).get("country")
				.toString();
		String region = JSONObject.fromObject(json.get("data")).get("region")
				.toString();
		String city = JSONObject.fromObject(json.get("data")).get("city")
				.toString();
		String county = JSONObject.fromObject(json.get("data")).get("county")
				.toString();
		String isp = JSONObject.fromObject(json.get("data")).get("isp")
				.toString();
		String area = JSONObject.fromObject(json.get("data")).get("area")
				.toString();
		map.put("area", area);
		map.put("city", city);
		map.put("country", country);
		map.put("region", region);
		map.put("county", county);
		map.put("isp", isp);
		map.put("ip", ip);
		map.put("visit_time", new Date());
		map.put("status", "1");

		return map;
	}

	public static  String getCityByIP(String strIP) {
		try {
			URL url = new URL("http://counter.sina.com.cn/ip?ip="
					+ strIP);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "gbk"));
			String line = null;
			StringBuffer result = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();
			System.out.println("result:"+result.toString());
				strIP = result.substring(result.indexOf("该IP所在地为："));
				strIP = strIP.substring(strIP.indexOf("：") + 1);
				String city = strIP.substring(strIP.indexOf("省") + 1,
						strIP.indexOf("市"));
				return city;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}