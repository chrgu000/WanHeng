package com.kg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
static{
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}
public static void main(String[] args) throws SQLException {
   Connection conn=DriverManager.getConnection("jdbc:mysql://114.55.39.173:3306/kindergarten?characterEncoding=utf-8", "root", "aidehuxi");
   System.out.println(conn);
   
}
}
