package com.jxc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtil {
	public static List<Map> getCalendar() {
		List<Map> dates = new ArrayList<Map>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("M");
		SimpleDateFormat sdf2 = new SimpleDateFormat("E");
		SimpleDateFormat sdf3 = new SimpleDateFormat("d");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		Date date=new Date();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("month", Integer.parseInt(sdf1.format(firstDate)));
		map.put("week", changeWeek(sdf2.format(firstDate)));
		map.put("lastday", Integer.parseInt(sdf3.format(lastDate)));
		map.put("day", Integer.parseInt(sdf3.format(date)));
		cal.add(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate2 = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate2 = cal.getTime();
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map2.put("month", Integer.parseInt(sdf1.format(firstDate2)));
		map2.put("week", changeWeek(sdf2.format(firstDate2)));
		map2.put("lastday", Integer.parseInt(sdf3.format(lastDate2)));
		cal.add(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate3 = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate3 = cal.getTime();
		Map<String, Integer> map3 = new HashMap<String, Integer>();
		map3.put("month", Integer.parseInt(sdf1.format(firstDate3)));
		map3.put("week", changeWeek(sdf2.format(firstDate3)));
		map3.put("lastday", Integer.parseInt(sdf3.format(lastDate3)));
		dates.add(map);
		dates.add(map2);
		dates.add(map3);
		return dates;
	}

	public static Integer changeWeek(String week) {
		if (week.equals("星期一")) {
			return 1;
		} else if (week.equals("星期二")) {
			return 2;
		} else if (week.equals("星期三")) {
			return 3;
		} else if (week.equals("星期四")) {
			return 4;
		} else if (week.equals("星期五")) {
			return 5;
		} else if (week.equals("星期二")) {
			return 2;
		} else if (week.equals("星期六")) {
			return 6;
		} else
			return 7;
	}

 
}
