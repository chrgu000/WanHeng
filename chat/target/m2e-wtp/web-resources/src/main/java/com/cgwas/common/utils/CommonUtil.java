package com.cgwas.common.utils;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

public class CommonUtil {
	/**
	 * 获取请求ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")
					|| ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 判断是否存在中文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static Double getTaxMoney(double money, String type) {
		double retnMoney = 0.0;
		if ("finish".equals(type)) {// 违约计算 违约收取5%佣金
			double tax =  0.05 ;

			retnMoney = money * (1 - tax);
		} else { // 默认为违约
			double tax =  0.00;

			retnMoney = money * (1 - tax);

		}
		// 保留两位小数
		BigDecimal b = new BigDecimal(retnMoney);
		retnMoney = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return retnMoney;

	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(Date s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		res = simpleDateFormat.format(s);
		return res;
	}

	// base64字符串转byte[]
	public static byte[] base64String2ByteFun(String base64Str) {
		return Base64.decodeBase64(base64Str);
	}

	// byte[]转base64
	public static String byte2Base64StringFun(byte[] b) {
		return Base64.encodeBase64String(b);
	}

	/**
	 * 获取N年之后的隔天
	 * @param year
	 * @return
	 */
	public static Date getMoneyYear(int year) {
		SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar aGregorianCalendar = new GregorianCalendar();
        // Get last month GregorianCalendar object
        aGregorianCalendar.set(Calendar.YEAR, aGregorianCalendar
                .get(Calendar.YEAR) + 1);
        aGregorianCalendar.set(Calendar.DATE, aGregorianCalendar.get(Calendar.DATE) + year);
        String currentYearAndMonth = aSimpleDateFormat.format(aGregorianCalendar.getTime());
        Date date=null;
        try {
        	date=aSimpleDateFormat.parse(currentYearAndMonth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 根据数字和百分比计算积
	 * @param figure 数字
	 * @param percentage 百分比 小于0的数
	 * @return
	 */
	public static Double FormetFileSize(double figure,double percentage) {
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.parseDouble(df.format(figure * percentage));
	}
}
