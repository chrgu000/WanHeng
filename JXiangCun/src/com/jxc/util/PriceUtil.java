package com.jxc.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxc.entity.DatePrice;

public class PriceUtil {
	public static List<Map<String, Object>> getPrice(Integer month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d E");
		List<Map<String, Object>> prices = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar cal3 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, month);
		cal2.set(Calendar.MONTH, month);
		cal3.set(Calendar.MONTH, month);
		cal2.add(Calendar.MONTH, 1);
		cal3.add(Calendar.MONTH, 2);
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		cal2.set(Calendar.DAY_OF_MONTH, 1);
		cal3.set(Calendar.DAY_OF_MONTH, 1);
		String firstdate1 = sdf.format(cal1.getTime());
		String firstdate2 = sdf.format(cal2.getTime());
		String firstdate3=sdf.format(cal3.getTime());
		cal2.add(Calendar.DAY_OF_MONTH, -1);
		String lastdate1 = sdf.format(cal2.getTime());
		cal2.add(Calendar.MONTH, 2);
		cal2.set(Calendar.DAY_OF_MONTH, 1);
		cal2.add(Calendar.DAY_OF_MONTH, -1);
		cal3.add(Calendar.MONTH, 1);
		cal3.set(Calendar.DAY_OF_MONTH, 1);
		cal3.add(Calendar.DAY_OF_MONTH, -1);
		String lastdate2 = sdf.format(cal2.getTime());
		String lastdate3=sdf.format(cal3.getTime());
		map1.put("firstdate",
				firstdate1.substring(0, firstdate1.lastIndexOf("-") + 1));
		map1.put("firstweek", getWeek(firstdate1));
		map1.put("lastday", getLastDay(lastdate1));
		map2.put("firstdate",
				firstdate2.substring(0, firstdate2.lastIndexOf("-") + 1));
		map2.put("firstweek", getWeek(firstdate2));
		map2.put("lastday", getLastDay(lastdate2));
		map3.put("firstdate", firstdate3.substring(0, firstdate3.lastIndexOf("-")+1));
		map3.put("firstweek", getWeek(firstdate3));
		map3.put("lastday", getLastDay(lastdate3));
		prices.add(map1);
		prices.add(map2);
		prices.add(map3);
		return prices;
	}

	public static Integer getWeek(String date) {
		String s = date.substring(date.length() - 1, date.length());
		if (s.equals("一")) {
			return 1;
		} else if (s.equals("二")) {
			return 2;
		} else if (s.equals("三")) {
			return 3;
		} else if (s.equals("四")) {
			return 4;
		} else if (s.equals("五")) {
			return 5;
		} else if (s.equals("六")) {
			return 6;
		} else {
			return 7;
		}
	}

	public static Integer getLastDay(String date) {
		String s = date.substring(date.lastIndexOf("-") + 1);
		if (s.length() == 4) {
			s = s.substring(0, 1);
		} else {
			s = s.substring(0, 2);
		}
		return Integer.parseInt(s);
	}

	public static List<DatePrice> getDatePrice(List<DatePrice> dateprices,
			Date pointDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d E");
		List<DatePrice> datePrices = new ArrayList<DatePrice>();
		for (DatePrice datePrice : dateprices) {
			if (datePrice.getDate().equals(pointDate)) {
				datePrices.add(datePrice);
				break;
			}
		}
		for (DatePrice datePrice : dateprices) {
			String date = sdf.format(datePrice.getDate());
			String s = date.substring(date.length() - 1, date.length());
			if (s.equals("一")) {
				datePrices.add(datePrice);
				break;
			}
		}
		for (DatePrice datePrice : dateprices) {
			String date = sdf.format(datePrice.getDate());
			String s = date.substring(date.length() - 1, date.length());
			if (s.equals("五")) {
				datePrices.add(datePrice);
				break;
			}
		}
		return datePrices;
	}
	public static void main(String[] args) {
		 List<Map<String,Object>> maps=getPrice(8);
		 for (Map<String, Object> map : maps) {
			System.out.println(map);
		}
		 
	}
}
