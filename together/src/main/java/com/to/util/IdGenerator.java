package com.to.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class IdGenerator {
	public static String genPrimaryKey() {
		return UUID.randomUUID().toString();
	}

	public static String genTradeNum() {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String str = "" + System.currentTimeMillis();
		return str;
	}

	public static String genOrdersNum() {
		Random random = new Random();
		String s = random.nextInt(10) + "";
		String str = s + System.currentTimeMillis();
		return str;
	}

	public static String genOrdersNum2() {
		String str = "" + System.currentTimeMillis();
		return str;
	}
}
