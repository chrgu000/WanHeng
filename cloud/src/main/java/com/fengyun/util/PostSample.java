package com.fengyun.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class PostSample {  
	 public static void post() {  
	  HttpClient httpClient = new HttpClient();  
	  HttpConnectionManagerParams managerParams = httpClient  
	    .getHttpConnectionManager().getParams();  
	  // 设置连接超时时间(单位毫秒)  
	  managerParams.setConnectionTimeout(600000);  
	  // 设置读数据超时时间(单位毫秒)  
//	  managerParams.setSoTimeout(120000);  
	  
	  String url = "http://localhost:8080/cloud/com/uploadVedio.do?type=vedios";  
	  PostMethod postMethod = new PostMethod(url);  
	  
	  // 将请求参数XML的值放入postMethod中  
	  String strResponse = null;  
	  try {  
	   postMethod.setRequestEntity(new StringRequestEntity(  
	     createRequestXML(), "text/xml", "UTF-8"));  
	   int statusCode = httpClient.executeMethod(postMethod);  
	   if (statusCode != HttpStatus.SC_OK) {  
	    throw new IllegalStateException("Method failed: "  
	      + postMethod.getStatusLine());  
	   }  
	   strResponse = postMethod.getResponseBodyAsString();  
	  } catch (Exception ex) {  
	   throw new IllegalStateException(ex.toString());  
	  } finally {  
	   // 释放连接  
	   postMethod.releaseConnection();  
	  }  
	  System.out.println(strResponse);  
	 }
	 public static String createRequestXML() {  
		  StringBuffer buffer = new StringBuffer();  
		  buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");  
		  buffer.append("<PACKET>");  
		  buffer.append("<HEAD>");  
		  buffer.append("<REQUEST_TYPE>01</REQUEST_TYPE>");  
		  buffer.append("</HEAD>");  
		  buffer.append("</PACKET>");  
		  return buffer.toString();  
		  
		 }  
	 }  