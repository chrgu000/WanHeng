package com.chat.util;


import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;



public class ReadVideo {
	public static String getVideoTimeLength(String path) {
		File source = new File(path);
		if (source.exists()) {
			Encoder encoder = new Encoder();
			File file = new File(path);
			try {
				MultimediaInfo m = encoder.getInfo(file);
				Long ls = m.getDuration();
				return secToTime(ls.intValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	 public static String secToTime(int time) {  
	        String timeStr = null;  
	        int hour = 0;  
	        int minute = 0;  
	        int second = 0;  
	        int millisecond = 0;  
	        if (time <= 0)  
	            return "00:00:00.000";  
	        else {  
	            second = time /1000;  
	            minute = second / 60;  
	            millisecond = time % 1000;  
	            if (second < 60) {  
	                  
	                timeStr = "00:00:" + unitFormat(second);  
	            }else if (minute < 60) {  
	                second = second % 60;  
	                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);  
	            }else{//数字>=3600 000的时候  
	                hour = minute /60;  
	                minute = minute % 60;  
	                second = second - hour * 3600 - minute * 60;  
	                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
	            }  
	        }  
	        return timeStr;  
	    }  
	  
	    public static String unitFormat(int i) {//时分秒的格式转换  
	        String retStr = null;  
	        if (i >= 0 && i < 10)  
	            retStr = "0" + Integer.toString(i);  
	        else  
	            retStr = "" + i;  
	        return retStr;  
	    }  
	  
	    public static String unitFormat2(int i) {//毫秒的格式转换  
	        String retStr = null;  
	        if (i >= 0 && i < 10)  
	            retStr = "00" + Integer.toString(i);  
	        else if (i >=10 && i < 100) {  
	             retStr = "0" + Integer.toString(i);  
	        }  
	        else  
	            retStr = "" + i;  
	        return retStr;  
	    }  
	 
}