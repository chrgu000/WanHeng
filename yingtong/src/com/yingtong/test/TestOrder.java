package com.yingtong.test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom.JDOMException;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yingtong.entity.Order;
import com.yingtong.page.OrderPage;
import com.yingtong.service.OrderService;
import com.yingtong.util.ConfigUtil;
import com.yingtong.util.HttpUtil;
import com.yingtong.util.PayCommonUtil;
import com.yingtong.util.QRCodeUtil;
import com.yingtong.util.XMLUtil;

public class TestOrder {
	BeanFactory app = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/spring-servlet.xml");
	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;
	// LOGO宽度
	private static final int WIDTH = 60;
	// LOGO高度
	private static final int HEIGHT = 60;

	 
	@Test
	public void testFind(){
		OrderService service=app.getBean(OrderService.class);
		OrderPage page=new OrderPage();
		List<Order> orders=service.findAllOrderByPage(page);
	System.out.println(orders);
	}
	public static void main(String[] args) {
	    	 String appid = ConfigUtil.APPID;  // appid  
	        //String appsecret = PayConfigUtil.APP_SECRET; // appsecret  
	        String mch_id = ConfigUtil.MCH_ID; // 商业号  
	        String key = ConfigUtil.API_KEY; // key  
	  
	        String currTime = "20160729151405";  
	        String strTime = currTime.substring(8, currTime.length());  
	        String strRandom = 2390 + "";  
	        String nonce_str = strTime + strRandom;  
	          System.out.println(currTime+":"+strRandom);
	        String order_price = "1"; // 价格   注意：价格的单位是分  
	        String body = "goodssssss";   // 商品名称  
	        String out_trade_no = "11338"; // 订单号  
	          
	        // 获取发起电脑 ip  
	        String spbill_create_ip = "192.168.16.111";  
	        // 回调接口   
	        String notify_url = ConfigUtil.NOTIFY_URL;  
	        String trade_type = "NATIVE";  
	          
	        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
	        packageParams.put("appid", appid);  
	        packageParams.put("mch_id", mch_id);  
	        packageParams.put("nonce_str", nonce_str);  
	        packageParams.put("body", body);  
	        packageParams.put("out_trade_no", out_trade_no);  
	        packageParams.put("total_fee", order_price);  
	        packageParams.put("spbill_create_ip", spbill_create_ip);  
	        packageParams.put("notify_url", notify_url);  
	        packageParams.put("trade_type", trade_type);  
	        String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);  
	        packageParams.put("sign", sign);  
	        String requestXML = PayCommonUtil.getRequestXml(packageParams);  
	        String resXml = HttpUtil.postData(ConfigUtil.UNIFIED_ORDER_URL , requestXML);  
	        Map map=null;
			try {
				map = XMLUtil.doXMLParse(resXml);
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
	        //String return_code = (String) map.get("return_code");  
	        //String prepay_id = (String) map.get("prepay_id");  
	        String urlCode = (String) map.get("code_url");  
	        System.out.println(urlCode);
	        String pth="E:/apache-tomcat-8.0.26/webapps/yingtong/erweima";
	        String name=System.currentTimeMillis()+"";
	      try {
			QRCodeUtil.encode(name, urlCode, null, pth, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	          
	}
}
