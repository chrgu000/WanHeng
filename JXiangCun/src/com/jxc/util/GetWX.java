package com.jxc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class GetWX {

	/**
	 * @param args
	 */
	private static final Logger log = Logger.getLogger(GetWX.class);// æ—¥å¿—æ–‡ä»¶


	public static String getPro(String pro) {
		GetWX loadProp = new GetWX();
		InputStream in = loadProp.getClass().getResourceAsStream(
				"/com/jxc/util/wx.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(pro);
	}
public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://139.196.9.64:3306/","root","aidehuxi");
			System.out.println(" Ô¶³ÌMysqlÁ¬½Ó²âÊÔ£º"  + conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
		}
	}
}
}
