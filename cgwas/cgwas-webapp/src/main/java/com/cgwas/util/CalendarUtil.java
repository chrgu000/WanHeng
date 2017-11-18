package com.cgwas.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cgwas.project.entity.ProjectVo;
import com.cgwas.subproject.entity.SubProjectVo;
/**
 * 获取年，季，月，周，日相关信息工具类
 * @author Administrator
 *
 */
public class CalendarUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMM");
	private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
	private static SimpleDateFormat sdf4 = new SimpleDateFormat("MM");
	private static SimpleDateFormat sdf5 = new SimpleDateFormat("M月d日");
/**
 * 为传入的对象中的开始时间，结束时间，实际开始时间，实际结束时间转换对应的日格式
 * @param obj
// */ 
	public static void main(String[] args) {
		System.out.println(getLastDay());
	}
	public static String getLastDay(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date first=cal.getTime();
		return sdf.format(first);
	}
	public static String[] getDaysOfCurrentMonth(){
		List<String> dates=new ArrayList<String>();
		Calendar cal=new GregorianCalendar();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int maxDays=getDaysByYearMonth(year,month);
		for (int i = 1; i <= maxDays; i++) {
            String date=year+"-"+(month<10?"0"+month:month)+"-"+(i<10?"0"+i:i);
            dates.add(date);
		}
		return dates.toArray(new String[0]);
	}
	public static String getFirtDayOfMonth(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date first=cal.getTime();
		return sdf.format(first)+" 00:00:00";
		}
	public static String getWeekMonday(){
		Calendar cal=new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		Date first=cal.getTime();
		return sdf.format(first)+" 00:00:00";
		}
	public static String[] getCurrentDateOfDays(){
		Calendar c=Calendar.getInstance();
		String[] dates=new String[7];
		dates[0]=sdf5.format(c.getTime());
		 for (int i = 1; i < dates.length; i++) {
            c.add(Calendar.DAY_OF_MONTH, -1);
            dates[i]=sdf5.format(c.getTime());
		}
		 return dates;
	}
	public static String[] getCurrentDateOf7Days(){
		Calendar c=Calendar.getInstance();
		String[] dates=new String[7];
		dates[0]=sdf.format(c.getTime());
		 for (int i = 1; i < dates.length; i++) {
            c.add(Calendar.DAY_OF_MONTH, -1);
            dates[i]=sdf.format(c.getTime());
		}
		 return dates;
	}
	public static void changeDay(Object obj) {
		try {
			Class clz = obj.getClass();
			Method getBegin_timeMethod = clz.getMethod("getBegin_time");
			Method setL_begin_timeMethod = clz.getMethod("setL_begin_time",
					Long.class);
			Method getEnd_timeMethod = clz.getMethod("getEnd_time");
			Method setL_end_timeMethod = clz.getMethod("setL_end_time",
					Long.class);
			Method getActual_begin_timeMethod = clz
					.getMethod("getActual_begin_time");
			Method setL_actual_begin_timeMethod = clz.getMethod(
					"setL_actual_begin_time", Long.class);
			Method getActual_end_timeMethod = clz
					.getMethod("getActual_end_time");
			Method setL_actual_end_timeMethod = clz.getMethod(
					"setL_actual_end_time", Long.class);
			Date begin_time = (Date) getBegin_timeMethod.invoke(obj);
			Date end_time = (Date) getEnd_timeMethod.invoke(obj);
			Date actual_begin_time = (Date) getActual_begin_timeMethod
					.invoke(obj);
			Date actual_end_time = (Date) getActual_end_timeMethod.invoke(obj);
			setL_begin_timeMethod.invoke(obj, sdf.parse(sdf.format(begin_time))
					.getTime());
			setL_end_timeMethod.invoke(obj, sdf.parse(sdf.format(end_time))
					.getTime());
			if (actual_begin_time != null) {
				setL_actual_begin_timeMethod.invoke(obj,
						sdf.parse(sdf.format(actual_begin_time)).getTime());
			}
			if (actual_end_time != null) {
				setL_actual_end_timeMethod.invoke(obj,
						sdf.parse(sdf.format(actual_end_time)).getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 为传入的对象中的开始时间，结束时间，实际开始时间，实际结束时间转换对应的周格式
	 * @param obj
	 */
	public static void changeWeek(Object obj) {
		try {
			Class clz = obj.getClass();
			Method getBegin_timeMethod = clz.getMethod("getBegin_time");
			Method setL_begin_timeMethod = clz.getMethod("setL_begin_time",
					Long.class);
			Method getEnd_timeMethod = clz.getMethod("getEnd_time");
			Method setL_end_timeMethod = clz.getMethod("setL_end_time",
					Long.class);
			Method getActual_begin_timeMethod = clz
					.getMethod("getActual_begin_time");
			Method setL_actual_begin_timeMethod = clz.getMethod(
					"setL_actual_begin_time", Long.class);
			Method getActual_end_timeMethod = clz
					.getMethod("getActual_end_time");
			Method setL_actual_end_timeMethod = clz.getMethod(
					"setL_actual_end_time", Long.class);
			Calendar c = Calendar.getInstance();
			Date begin_time = (Date) getBegin_timeMethod.invoke(obj);
			c.setTime(begin_time);
			String week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
			if (week_of_year.length() < 2) {
				week_of_year = "0" + week_of_year;
			}
			setL_begin_timeMethod.invoke(obj,
					Long.valueOf(sdf2.format(begin_time) + week_of_year));
			Date end_time = (Date) getEnd_timeMethod.invoke(obj);
			c.setTime(end_time);
			week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
			if (week_of_year.length() < 2) {
				week_of_year = "0" + week_of_year;
			}
			setL_end_timeMethod.invoke(obj,
					Long.valueOf(sdf2.format(end_time) + week_of_year));
			Date actual_begin_time = (Date) getActual_begin_timeMethod
					.invoke(obj);
			if (actual_begin_time != null) {
				c.setTime(actual_begin_time);
				week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
				if (week_of_year.length() < 2) {
					week_of_year = "0" + week_of_year;
				}
				setL_actual_begin_timeMethod.invoke(
						obj,
						Long.parseLong(sdf2.format(actual_begin_time)
								+ week_of_year));
			}
			Date actual_end_time = (Date) getActual_end_timeMethod.invoke(obj);
			if (actual_end_time != null) {
				c.setTime(actual_end_time);
				week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
				if (week_of_year.length() < 2) {
					week_of_year = "0" + week_of_year;
				}
				setL_actual_end_timeMethod.invoke(
						obj,
						Long.parseLong(sdf2.format(actual_end_time)
								+ week_of_year));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 为传入的对象中的开始时间，结束时间，实际开始时间，实际结束时间转换对应的月格式
	 * @param obj
	 */
	public static void changeMonth(Object obj) {
		try {
			Class clz = obj.getClass();
			Method getBegin_timeMethod = clz.getMethod("getBegin_time");
			Method setL_begin_timeMethod = clz.getMethod("setL_begin_time",
					Long.class);
			Method getEnd_timeMethod = clz.getMethod("getEnd_time");
			Method setL_end_timeMethod = clz.getMethod("setL_end_time",
					Long.class);
			Method getActual_begin_timeMethod = clz
					.getMethod("getActual_begin_time");
			Method setL_actual_begin_timeMethod = clz.getMethod(
					"setL_actual_begin_time", Long.class);
			Method getActual_end_timeMethod = clz
					.getMethod("getActual_end_time");
			Method setL_actual_end_timeMethod = clz.getMethod(
					"setL_actual_end_time", Long.class);
			Date begin_time = (Date) getBegin_timeMethod.invoke(obj);
			setL_begin_timeMethod.invoke(obj,
					Long.valueOf(sdf2.format(begin_time)));
			Date end_time = (Date) getEnd_timeMethod.invoke(obj);
			setL_end_timeMethod
					.invoke(obj, Long.valueOf(sdf2.format(end_time)));
			Date actual_begin_time = (Date) getActual_begin_timeMethod
					.invoke(obj);
			if (actual_begin_time != null) {
				setL_actual_begin_timeMethod.invoke(obj,
						Long.parseLong(sdf2.format(actual_begin_time)));
			}
			Date actual_end_time = (Date) getActual_end_timeMethod.invoke(obj);
			if (actual_end_time != null) {
				setL_actual_end_timeMethod.invoke(obj,
						Long.parseLong(sdf2.format(actual_end_time)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 为传入的对象中的开始时间，结束时间，实际开始时间，实际结束时间转换对应的季格式
	 * @param obj
	 */
	public static void changeSeason(Object obj) {
		try {
			Class clz = obj.getClass();
			Method getBegin_timeMethod = clz.getMethod("getBegin_time");
			Method setL_begin_timeMethod = clz.getMethod("setL_begin_time",
					Long.class);
			Method getEnd_timeMethod = clz.getMethod("getEnd_time");
			Method setL_end_timeMethod = clz.getMethod("setL_end_time",
					Long.class);
			Method getActual_begin_timeMethod = clz
					.getMethod("getActual_begin_time");
			Method setL_actual_begin_timeMethod = clz.getMethod(
					"setL_actual_begin_time", Long.class);
			Method getActual_end_timeMethod = clz
					.getMethod("getActual_end_time");
			Method setL_actual_end_timeMethod = clz.getMethod(
					"setL_actual_end_time", Long.class);
			Date begin_time = (Date) getBegin_timeMethod.invoke(obj);
			setL_begin_timeMethod.invoke(
					obj,
					Long.valueOf(sdf3.format(begin_time)
							+ getSeason(begin_time)));
			Date end_time = (Date) getEnd_timeMethod.invoke(obj);
			setL_end_timeMethod.invoke(obj,
					Long.valueOf(sdf3.format(end_time) + getSeason(end_time)));
			Date actual_begin_time = (Date) getActual_begin_timeMethod
					.invoke(obj);
			if (actual_begin_time != null) {
				setL_actual_begin_timeMethod.invoke(
						obj,
						Long.valueOf(sdf3.format(actual_begin_time)
								+ getSeason(actual_begin_time)));
			}
			Date actual_end_time = (Date) getActual_end_timeMethod.invoke(obj);
			if (actual_end_time != null) {
				setL_actual_end_timeMethod.invoke(
						obj,
						Long.valueOf(sdf3.format(actual_end_time)
								+ getSeason(actual_end_time)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 为传入的对象中的开始时间，结束时间，实际开始时间，实际结束时间转换对应的年格式
	 * @param obj
	 */
	public static void changeYear(Object obj) {
		try {
			Class clz = obj.getClass();
			Method getBegin_timeMethod = clz.getMethod("getBegin_time");
			Method setL_begin_timeMethod = clz.getMethod("setL_begin_time",
					Long.class);
			Method getEnd_timeMethod = clz.getMethod("getEnd_time");
			Method setL_end_timeMethod = clz.getMethod("setL_end_time",
					Long.class);
			Method getActual_begin_timeMethod = clz
					.getMethod("getActual_begin_time");
			Method setL_actual_begin_timeMethod = clz.getMethod(
					"setL_actual_begin_time", Long.class);
			Method getActual_end_timeMethod = clz
					.getMethod("getActual_end_time");
			Method setL_actual_end_timeMethod = clz.getMethod(
					"setL_actual_end_time", Long.class);
			Date begin_time = (Date) getBegin_timeMethod.invoke(obj);
			setL_begin_timeMethod.invoke(obj,
					Long.valueOf(sdf3.format(begin_time)));
			Date end_time = (Date) getEnd_timeMethod.invoke(obj);
			setL_end_timeMethod
					.invoke(obj, Long.valueOf(sdf3.format(end_time)));
			Date actual_begin_time = (Date) getActual_begin_timeMethod
					.invoke(obj);
			if (actual_begin_time != null) {
				setL_actual_begin_timeMethod.invoke(obj,
						Long.valueOf(sdf3.format(actual_begin_time)));
			}
			Date actual_end_time = (Date) getActual_end_timeMethod.invoke(obj);
			if (actual_end_time != null) {
				setL_actual_end_timeMethod.invoke(obj,
						Long.valueOf(sdf3.format(actual_end_time)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * 根据传入的时间对象获取传入时间所在的季度
 * @param time
 * @return
 */
	private static Integer getSeason(Date time) {
		String str = sdf4.format(time);
		Integer season = Integer.parseInt(str);
		System.out.println(season);
		if (season >= 1 && season < 4) {
			return 1;
		} else if (season >= 4 && season < 7) {
			return 2;
		} else if (season >= 7 && season < 10) {
			return 3;
		} else {
			return 4;
		}
	}
/**
 * 根据开始时间，结束时间生成年份集合
 * @param begin_time
 * @param end_time
 * @return
 */
	public static List<Map<String, Object>> year(Date begin_time, Date end_time) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		GregorianCalendar begin = (GregorianCalendar) Calendar.getInstance();
		GregorianCalendar end = (GregorianCalendar) Calendar.getInstance();
		begin.setTime(begin_time);
		end.setTime(end_time);
		int begin_year = begin.get(Calendar.YEAR);
		int end_year = end.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> list = new ArrayList<Long>();
		for (int i = begin_year; i <= end_year; i++) {
			list.add(Long.valueOf(i));
		}
		map.put("date", list);
		dataList.add(map);
		return dataList;
	}
	/**
	 * 根据开始时间，结束时间生成年，季度集合
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static List<Map<String, Object>> season(Date begin_time,
			Date end_time) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		GregorianCalendar begin = (GregorianCalendar) Calendar.getInstance();
		GregorianCalendar end = (GregorianCalendar) Calendar.getInstance();
		begin.setTime(begin_time);
		end.setTime(end_time);
		int begin_year = begin.get(Calendar.YEAR);
		int end_year = end.get(Calendar.YEAR);
		for (int i = begin_year; i <= end_year; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Long> list = new ArrayList<Long>();
			for (int j = 1; j <= 4; j++) {
				list.add(Long.valueOf(i + "" + j));
			}
			map.put("name", i);
			map.put("date", list);
			dataList.add(map);
		}
		return dataList;
	}
	/**
	 * 根据开始时间，结束时间生成年月集合
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static List<Map<String, Object>> month(Date begin_time, Date end_time) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		GregorianCalendar begin = (GregorianCalendar) Calendar.getInstance();
		GregorianCalendar end = (GregorianCalendar) Calendar.getInstance();
		begin.setTime(begin_time);
		end.setTime(end_time);
		int begin_year = begin.get(Calendar.YEAR);
		int end_year = end.get(Calendar.YEAR);
		for (int i = begin_year; i <= end_year; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Long> list = new ArrayList<Long>();
			for (int j = 1; j <= 12; j++) {
				list.add(getMonthInfo(i, j));
			}
			map.put("name", i);
			map.put("date", list);
			dataList.add(map);
		}
		return dataList;
	}
/**
 * 根据年月数生成组成的新数据
 * @param year
 * @param month
 * @return
 */
	private static Long getMonthInfo(int year, int month) {
		String s = month + "";
		if (month < 10) {
			s = "0" + s;
		}
		return Long.valueOf(year + s);
	}
	/**
	 * 根据开始时间，结束时间生成年月周集合
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static List<Map<String, Object>> week(Date begin_time, Date end_time) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		GregorianCalendar begin = (GregorianCalendar) Calendar.getInstance();
		GregorianCalendar end = (GregorianCalendar) Calendar.getInstance();
		begin.setTime(begin_time);
		end.setTime(end_time);
		int begin_year = begin.get(Calendar.YEAR);
		int end_year = end.get(Calendar.YEAR);
		int begin_month = begin.get(Calendar.MONTH) + 1;
		int end_month = end.get(Calendar.MONTH) + 1;
		if (begin_year < end_year) {//当开始时间的年份小于结束时间的年份
			for (int i = begin_year; i <= end_year; i++) {//遍历年份
				if (i == begin_year) {//当遍历年份当年份是开始年份时
					for (int j = begin_month; j <= 12; j++) {//遍历月份，从开始月份到12月
						Map<String, Object> map = new HashMap<String, Object>();
						int maxDay = getDaysByYearMonth(i, j);//根据年月获取当前月最大天数
						if (j == 12) {
							maxDay--;
						}
						String currentTime = getCurrentTime(i, j);//根据年月获取年月时间字符串
						int[] week = getWeek(i, j, maxDay);//根据年月以及该月的最大天数获取当月第一天和最后一天所在周
						List<Long> list = new ArrayList<Long>();
						for (int k = week[0]; k <= week[1]; k++) {//遍历周
							list.add(getWeekInfo(i, j, k));//生成年月周数字
						}
						map.put("name", currentTime);
						map.put("date", list);
						dataList.add(map);
					}
				} else if (i != end_year && i > begin_year) {//当年在开始年与结束年之间且不等于开始年和结束年
					for (int j = 1; j <= 12; j++) {//月份从1月到12月
						Map<String, Object> map = new HashMap<String, Object>();
						int maxDay = getDaysByYearMonth(i, j);//生成遍历年月的最大天数
						if (j == 12) {
							maxDay--;
						}
						String currentTime = getCurrentTime(i, j);//获取年月的组合字符串
						int[] week = getWeek(i, j, maxDay);//获取遍历年月所在的周数，最大周，最小周
						List<Long> list = new ArrayList<Long>();
						for (int k = week[0]; k <= week[1]; k++) {
							list.add(getWeekInfo(i, j, k));//组合年月周字符串
						}
						map.put("name", currentTime);
						map.put("date", list);
						dataList.add(map);
					}
				} else if (i == end_year) {//遍历年为结束年
					for (int j = 1; j <= end_month; j++) {//遍历月份，从1月到结束月
						Map<String, Object> map = new HashMap<String, Object>();
						String currentTime = getCurrentTime(i, j);
						int maxDay = getDaysByYearMonth(i, j);
						if (j == 12) {
							maxDay--;
						}
						int[] week = getWeek(i, j, maxDay);
						List<Long> list = new ArrayList<Long>();
						for (int k = week[0]; k <= week[1]; k++) {
							list.add(getWeekInfo(i, j, k));
						}
						map.put("name", currentTime);
						map.put("date", list);
						dataList.add(map);
					}
				}
			}
		} else {
			if (begin_month <= end_month) {//到开始年与结束年在同一年
				for (int j = begin_month; j <= end_month; j++) {//遍历月份，从开始月到结束月
					Map<String, Object> map = new HashMap<String, Object>();
					int maxDay = getDaysByYearMonth(begin_year, j);//根据年月获取当月的最大天数
					if (j == 12) {
						maxDay--;
					}
					String currentTime = getCurrentTime(begin_year, j);//获取年月字符串
					int[] week = getWeek(begin_year, j, maxDay);//生成当月的开始天和结束天所在的周数
					List<Long> list = new ArrayList<Long>();
					for (int k = week[0]; k <= week[1]; k++) {
						list.add(getWeekInfo(begin_year, j, k));//生成年月周字符串
					}
					map.put("name", currentTime);
					map.put("date", list);
					dataList.add(map);
				}
			}
		}
		return dataList;
	}
/**
 * 根据年月周生成三个组合后的新数据
 * @param year
 * @param month
 * @param week
 * @return
 */
	private static Long getWeekInfo(int year, int month, int week) {
		String s1 = month + "";
		String s2 = week + "";
		if (month < 10) {
			s1 = 0 + s1;
		}
		if (week < 10) {
			s2 = 0 + s2;
		}
		return Long.valueOf(year + s1 + s2);
	}
/**
 * 根据年月当月最大天数获取当月所在最大周数和最小周数
 * @param year
 * @param month
 * @param maxDay
 * @return
 */
	private static int[] getWeek(int year, int month, int maxDay) {
		int[] a = new int[2];
		String begin = year + "-" + month + "-1";
		String end = year + "-" + month + "-" + maxDay;
		try {
			Calendar c = Calendar.getInstance();
			Date start = sdf.parse(begin);
			c.setTime(start);
			a[0] = c.get(Calendar.WEEK_OF_YEAR);
			Date finish = sdf.parse(end);
			c.setTime(finish);
			a[1] = c.get(Calendar.WEEK_OF_YEAR);
			return a;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据开始时间，结束时间生成年月日集合
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public static List<Map<String, Object>> day(Date begin_time, Date end_time) {
		try {
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			GregorianCalendar begin = (GregorianCalendar) Calendar
					.getInstance();
			GregorianCalendar end = (GregorianCalendar) Calendar.getInstance();
			begin.setTime(begin_time);
			end.setTime(end_time);
			int begin_year = begin.get(Calendar.YEAR);
			int end_year = end.get(Calendar.YEAR);
			int begin_month = begin.get(Calendar.MONTH) + 1;
			int end_month = end.get(Calendar.MONTH) + 1;
			if (begin_year < end_year) {
				for (int i = begin_year; i <= end_year; i++) {
					if (i != end_year && i == begin_year) {
						for (int j = begin_month; j <= 12; j++) {
							Map<String, Object> map = new HashMap<String, Object>();
							int maxDay = getDaysByYearMonth(i, j);
							String currentTime = getCurrentTime(i, j);
							List<Long> list = new ArrayList<Long>();
							// List<String> l1 = new ArrayList<String>();
							for (int k = 1; k <= maxDay; k++) {
								Calendar c = Calendar.getInstance();
								c.set(Calendar.YEAR, i);
								c.set(Calendar.MONTH, j - 1);
								c.set(Calendar.DAY_OF_MONTH, k);
								list.add(sdf.parse(sdf.format(c.getTime()))
										.getTime());
								// l1.add(sdf.format(c.getTime()));
							}
							map.put("name", currentTime);
							map.put("date", list);
							dataList.add(map);
						}
					} else if (i != end_year && i > begin_year) {
						for (int j = 1; j <= 12; j++) {
							Map<String, Object> map = new HashMap<String, Object>();
							int maxDay = getDaysByYearMonth(i, j);
							String currentTime = getCurrentTime(i, j);
							List<Long> list = new ArrayList<Long>();
							// List<String> l1 = new ArrayList<String>();
							for (int k = 1; k <= maxDay; k++) {
								Calendar c = Calendar.getInstance();
								c.set(Calendar.YEAR, i);
								c.set(Calendar.MONTH, j - 1);
								c.set(Calendar.DAY_OF_MONTH, k);
								list.add(sdf.parse(sdf.format(c.getTime()))
										.getTime());
								// l1.add(sdf.format(c.getTime()));s
							}
							map.put("name", currentTime);
							map.put("date", list);
							dataList.add(map);
						}
					} else if (i == end_year) {
						for (int j = 1; j <= end_month; j++) {
							Map<String, Object> map = new HashMap<String, Object>();
							String currentTime = getCurrentTime(i, j);
							List<Long> list = new ArrayList<Long>();
							// List<String> l1 = new ArrayList<String>();
							int maxDay = getDaysByYearMonth(i, j);
							for (int k = 1; k <= maxDay; k++) {
								Calendar c = Calendar.getInstance();
								c.set(Calendar.YEAR, i);
								c.set(Calendar.MONTH, j - 1);
								c.set(Calendar.DAY_OF_MONTH, k);
								list.add(sdf.parse(sdf.format(c.getTime()))
										.getTime());
								// l1.add(sdf.format(c.getTime()));
							}
							map.put("name", currentTime);
							map.put("date", list);
							dataList.add(map);
						}
					}
				}
			} else {
				if (begin_month <= end_month) {
					for (int j = begin_month; j <= end_month; j++) {
						Map<String, Object> map = new HashMap<String, Object>();
						int maxDay = getDaysByYearMonth(begin_year, j);
						String currentTime = getCurrentTime(begin_year, j);
						List<Long> list = new ArrayList<Long>();
						// List<String> l1 = new ArrayList<String>();
						for (int k = 1; k <= maxDay; k++) {
							Calendar c = Calendar.getInstance();
							c.set(Calendar.YEAR, begin_year);
							c.set(Calendar.MONTH, j - 1);
							c.set(Calendar.DAY_OF_MONTH, k);
							list.add(sdf.parse(sdf.format(c.getTime()))
									.getTime());
							// l1.add(sdf.format(c.getTime()));
						}
						map.put("name", currentTime);
						map.put("date", list);
						dataList.add(map);
					}
				}
			}
			return dataList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
/**
 * 根据年月生成年月字符串
 * @param year
 * @param month
 * @return
 */
	private static String getCurrentTime(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		return sdf1.format(a.getTime());
	}
	/**
	 * 根据年月生成当月的最大天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
/**
 * 根据传入的map对象和num值修改对应的年，季，月，周，日数据
 * @param map
 * @param num
 * @throws Exception
 */
	public static void changeMapInfo(Map<String, Object> map, String num)
			throws Exception {
		Long modelBeginTime = null, modelEndTime = null, modelActualBeginTime = null, modelActualEndTime = null;
		Long animationBeginTime = null, animationEndTime = null, animationActualBeginTime = null, animationActualEndTime = null;
		Long lightBeginTime = null, lightEndTime = null, lightActualBeginTime = null, lightActualEndTime = null;
		Date modelTaskBeiginTime = (Date) map.get("modelTaskBeiginTime");
		Date modelTaskEndTime = (Date) map.get("modelTaskEndTime");
		Date modelTaskActualBeginTime = (Date) map
				.get("modelTaskActualBeginTime");
		Date modelTaskActualEndTime = (Date) map.get("modelTaskActualEndTime");

		Date animationTaskBeiginTime = (Date) map
				.get("animationTaskBeiginTime");
		Date animationTaskEndTime = (Date) map.get("animationTaskEndTime");
		Date animationTaskActualBeginTime = (Date) map
				.get("animationTaskActualBeginTime");
		Date animationTaskActualEndTime = (Date) map
				.get("animationTaskActualEndTime");

		Date lightTaskBeiginTime = (Date) map.get("lightTaskBeiginTime");
		Date lightTaskEndTime = (Date) map.get("lightTaskEndTime");
		Date lightTaskActualBeginTime = (Date) map
				.get("lightTaskActualBeginTime");
		Date lightTaskActualEndTime = (Date) map.get("lightTaskActualEndTime");
		if ("1".equals(num)) {//日
			modelBeginTime = sdf.parse(sdf.format(modelTaskBeiginTime))
					.getTime();
			modelEndTime = sdf.parse(sdf.format(modelTaskEndTime)).getTime();
			if (modelTaskActualBeginTime != null) {
				modelActualBeginTime = sdf.parse(
						sdf.format(modelTaskActualBeginTime)).getTime();
			}
			if (modelTaskActualEndTime != null) {
				modelActualEndTime = sdf.parse(
						sdf.format(modelTaskActualEndTime)).getTime();
			}
			animationBeginTime = sdf.parse(sdf.format(animationTaskBeiginTime))
					.getTime();
			animationEndTime = sdf.parse(sdf.format(animationTaskEndTime))
					.getTime();
			if (animationTaskActualBeginTime != null) {
				animationActualBeginTime = sdf.parse(
						sdf.format(animationTaskActualBeginTime)).getTime();
			}
			if (animationTaskActualEndTime != null) {
				animationActualEndTime = sdf.parse(
						sdf.format(animationTaskActualEndTime)).getTime();
			}
			lightBeginTime = sdf.parse(sdf.format(lightTaskBeiginTime))
					.getTime();
			lightEndTime = sdf.parse(sdf.format(lightTaskEndTime)).getTime();
			if (lightTaskActualBeginTime != null) {
				lightActualBeginTime = sdf.parse(
						sdf.format(lightTaskActualBeginTime)).getTime();
			}
			if (lightTaskActualEndTime != null) {
				lightActualEndTime = sdf.parse(
						sdf.format(lightTaskActualEndTime)).getTime();
			}

		} else if ("2".equals(num)) {//周
			Calendar c = Calendar.getInstance();
			c.setTime(modelTaskBeiginTime);
			String week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
			if (week_of_year.length() < 2) {
				week_of_year = "0" + week_of_year;
			}
			modelBeginTime = Long.valueOf(sdf2.format(modelTaskBeiginTime)
					+ week_of_year);
			c.setTime(modelTaskEndTime);
			week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
			if (week_of_year.length() < 2) {
				week_of_year = "0" + week_of_year;
			}
			modelEndTime = Long.valueOf(sdf2.format(modelTaskEndTime)
					+ week_of_year);

			if (modelTaskActualBeginTime != null) {
				c.setTime(modelTaskActualBeginTime);
				week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
				if (week_of_year.length() < 2) {
					week_of_year = "0" + week_of_year;
				}
				modelActualBeginTime = Long.valueOf(sdf2
						.format(modelTaskActualBeginTime) + week_of_year);
			}
			if (modelTaskActualEndTime != null) {
				c.setTime(modelTaskActualEndTime);
				week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
				if (week_of_year.length() < 2) {
					week_of_year = "0" + week_of_year;
				}
				modelActualEndTime = Long.valueOf(sdf2
						.format(modelTaskActualEndTime) + week_of_year);
			}

			c.setTime(animationTaskBeiginTime);
			week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
			if (week_of_year.length() < 2) {
				week_of_year = "0" + week_of_year;
			}
			animationBeginTime = Long.valueOf(sdf2
					.format(animationTaskBeiginTime) + week_of_year);
			c.setTime(animationTaskEndTime);
			week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
			if (week_of_year.length() < 2) {
				week_of_year = "0" + week_of_year;
			}
			animationEndTime = Long.valueOf(sdf2.format(animationTaskEndTime)
					+ week_of_year);
			if (animationTaskActualBeginTime != null) {
				c.setTime(animationTaskActualBeginTime);
				week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
				if (week_of_year.length() < 2) {
					week_of_year = "0" + week_of_year;
				}
				animationActualBeginTime = Long.valueOf(sdf2
						.format(animationTaskActualBeginTime) + week_of_year);
			}
			if (animationTaskActualEndTime != null) {
				c.setTime(animationTaskActualEndTime);
				week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
				if (week_of_year.length() < 2) {
					week_of_year = "0" + week_of_year;
				}
				animationActualEndTime = Long.valueOf(sdf2
						.format(animationTaskActualEndTime) + week_of_year);
			}
			c.setTime(lightTaskBeiginTime);
			week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
			if (week_of_year.length() < 2) {
				week_of_year = "0" + week_of_year;
			}
			lightBeginTime = Long.valueOf(sdf2.format(lightTaskBeiginTime)
					+ week_of_year);
			c.setTime(lightTaskEndTime);
			week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
			if (week_of_year.length() < 2) {
				week_of_year = "0" + week_of_year;
			}
			lightEndTime = Long.valueOf(sdf2.format(lightTaskEndTime)
					+ week_of_year);
			if (lightTaskActualBeginTime != null) {
				c.setTime(lightTaskActualBeginTime);
				week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
				if (week_of_year.length() < 2) {
					week_of_year = "0" + week_of_year;
				}
				lightActualBeginTime = Long.valueOf(sdf2
						.format(lightTaskActualBeginTime) + week_of_year);
			}
			if (lightTaskActualEndTime != null) {
				c.setTime(lightTaskActualEndTime);
				week_of_year = c.get(Calendar.WEEK_OF_YEAR) + "";
				if (week_of_year.length() < 2) {
					week_of_year = "0" + week_of_year;
				}
				lightActualEndTime = Long.valueOf(sdf2
						.format(lightTaskActualEndTime) + week_of_year);
			}

		} else if ("3".equals(num)) {//月
			modelBeginTime = Long.parseLong(sdf2.format(modelTaskBeiginTime));
			modelEndTime = Long.parseLong(sdf2.format(modelTaskEndTime));
			if (modelTaskActualBeginTime != null) {
				modelActualBeginTime = Long.parseLong(sdf2
						.format(modelTaskActualBeginTime));
			}
			if (modelTaskActualEndTime != null) {
				modelActualEndTime = Long.parseLong(sdf2
						.format(modelTaskActualEndTime));
			}
			animationBeginTime = Long.parseLong(sdf2
					.format(animationTaskBeiginTime));
			animationEndTime = Long
					.parseLong(sdf2.format(animationTaskEndTime));
			if (animationTaskActualBeginTime != null) {
				animationActualBeginTime = Long.parseLong(sdf2
						.format(animationTaskActualBeginTime));
			}
			if (animationTaskActualEndTime != null) {
				animationActualEndTime = Long.parseLong(sdf2
						.format(animationTaskActualEndTime));
			}
			lightBeginTime = Long.parseLong(sdf2.format(lightTaskBeiginTime));
			lightEndTime = Long.parseLong(sdf2.format(lightTaskEndTime));
			if (lightTaskActualBeginTime != null) {
				lightActualBeginTime = Long.parseLong(sdf2
						.format(lightTaskActualBeginTime));
			}
			if (lightTaskActualEndTime != null) {
				lightActualEndTime = Long.parseLong(sdf2
						.format(lightTaskActualEndTime));
			}
		} else if ("4".equals(num)) {//季
			modelBeginTime = Long.valueOf(sdf3.format(modelTaskBeiginTime)
					+ getSeason(modelTaskBeiginTime));
			modelEndTime = Long.valueOf(sdf3.format(modelTaskEndTime)
					+ getSeason(modelTaskEndTime));
			if (modelTaskActualBeginTime != null) {
				modelActualBeginTime = Long.valueOf(sdf3
						.format(modelTaskActualBeginTime)
						+ getSeason(modelTaskActualBeginTime));
			}
			if (modelTaskActualEndTime != null) {
				modelActualEndTime = Long.valueOf(sdf3
						.format(modelTaskActualEndTime)
						+ getSeason(modelTaskActualEndTime));
			}
			animationBeginTime = Long.valueOf(sdf3
					.format(animationTaskBeiginTime)
					+ getSeason(animationTaskBeiginTime));
			animationEndTime = Long
					.parseLong(sdf2.format(animationTaskEndTime));
			if (animationTaskActualBeginTime != null) {
				animationActualBeginTime = Long.valueOf(sdf3
						.format(animationTaskActualBeginTime)
						+ getSeason(animationTaskActualBeginTime));
			}
			if (animationTaskActualEndTime != null) {
				animationActualEndTime = Long.valueOf(sdf3
						.format(animationTaskActualEndTime)
						+ getSeason(animationTaskActualEndTime));
			}
			lightBeginTime = Long.valueOf(sdf3.format(lightTaskBeiginTime)
					+ getSeason(lightTaskBeiginTime));
			lightEndTime = Long.valueOf(sdf3.format(lightTaskEndTime)
					+ getSeason(lightTaskEndTime));
			if (lightTaskActualBeginTime != null) {
				lightActualBeginTime = Long.valueOf(sdf3
						.format(lightTaskActualBeginTime)
						+ getSeason(lightTaskActualBeginTime));
			}
			if (lightTaskActualEndTime != null) {
				lightActualEndTime = Long.valueOf(sdf3
						.format(lightTaskActualEndTime)
						+ getSeason(lightTaskActualEndTime));
			}
		} else if ("5".equals(num)) {//年
			modelBeginTime = Long.parseLong(sdf3.format(modelTaskBeiginTime));
			modelEndTime = Long.parseLong(sdf3.format(modelTaskEndTime));
			if (modelTaskActualBeginTime != null) {
				modelActualBeginTime = Long.parseLong(sdf3
						.format(modelTaskActualBeginTime));
			}
			if (modelTaskActualEndTime != null) {
				modelActualEndTime = Long.parseLong(sdf3
						.format(modelTaskActualEndTime));
			}
			animationBeginTime = Long.parseLong(sdf3
					.format(animationTaskBeiginTime));
			animationEndTime = Long
					.parseLong(sdf3.format(animationTaskEndTime));
			if (animationTaskActualBeginTime != null) {
				animationActualBeginTime = Long.parseLong(sdf3
						.format(animationTaskActualBeginTime));
			}
			if (animationTaskActualEndTime != null) {
				animationActualEndTime = Long.parseLong(sdf3
						.format(animationTaskActualEndTime));
			}
			lightBeginTime = Long.parseLong(sdf3.format(lightTaskBeiginTime));
			lightEndTime = Long.parseLong(sdf3.format(lightTaskEndTime));
			if (lightTaskActualBeginTime != null) {
				lightActualBeginTime = Long.parseLong(sdf3
						.format(lightTaskActualBeginTime));
			}
			if (lightTaskActualEndTime != null) {
				lightActualEndTime = Long.parseLong(sdf3
						.format(lightTaskActualEndTime));
			}
		}
		map.put("modelBeginTime", modelBeginTime);
		map.put("modelEndTime", modelEndTime);
		map.put("modelActualBeginTime", modelActualBeginTime);
		map.put("modelActualEndTime", modelActualEndTime);
		map.put("animationBeginTime", animationBeginTime);
		map.put("animationEndTime", animationEndTime);
		map.put("animationActualBeginTime", animationActualBeginTime);
		map.put("animationActualEndTime", animationActualEndTime);
		map.put("lightBeginTime", lightBeginTime);
		map.put("lightEndTime", lightEndTime);
		map.put("lightActualBeginTime", lightActualBeginTime);
		map.put("lightActualEndTime", lightActualEndTime);
	}
}
