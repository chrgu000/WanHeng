package com.yingtong.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class GetWX {

	/**
	 * @param args
	 */
	private static final Logger log = Logger.getLogger(GetWX.class);// 日志文件

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getPro("yuming"));
	}

	public static String getPro(String pro) {
		GetWX loadProp = new GetWX();
		InputStream in = loadProp.getClass().getResourceAsStream(
				"/com/yingtong/util/wx.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(pro);
	}

}
