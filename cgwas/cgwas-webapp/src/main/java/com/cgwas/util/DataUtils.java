package com.cgwas.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
	
	public static SimpleDateFormat sdfyyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static SimpleDateFormat sdfyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	
	/** 
	 * 时间戳转换成日期格式字符串 
	 * @param seconds 精确到秒的字符串 
	 * @param sdf 如：SimpleDateFormat 对象
	 * @return 
	 */
	public static String timeStamp2Date(String seconds, SimpleDateFormat sdf) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/** 
	 * 日期格式字符串转换成时间戳 
	 * @param date 字符串日期 
	 * @param sdf 如：SimpleDateFormat 对象
	 * @return 
	 */
	public static String date2TimeStamp(String date_str, SimpleDateFormat sdf) {
		try {
			return String.valueOf(sdf.parse(date_str).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/** 
	 * 取得当前时间戳（精确到秒） 
	 * @return 
	 */
	public static String timeStamp() {
		long time = System.currentTimeMillis();
		String t = String.valueOf(time / 1000);
		return t;
	}
}
