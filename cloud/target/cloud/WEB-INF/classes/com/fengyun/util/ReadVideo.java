package com.fengyun.util;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	    public static void changeFormatOfVideo(){
	    	File source = new File("C:/Users/yangjun/Desktop/video/37-IO流-练习-切割文件-思路.avi");  
	    	File target = new File("C:/Users/yangjun/Desktop/target/avi.flv"); 
	    	AudioAttributes audio = new AudioAttributes();  
	    	audio.setCodec("libmp3lame");  
	    	audio.setBitRate(new Integer(64000));  
	    	audio.setChannels(new Integer(1));  
	    	audio.setSamplingRate(new Integer(22050));  
	    	VideoAttributes video = new VideoAttributes();  
	    	video.setCodec("flv");  
	    	video.setBitRate(new Integer(160000));  
	    	video.setFrameRate(new Integer(15));  
	    	video.setSize(new VideoSize(400, 300));  
	    	EncodingAttributes attrs = new EncodingAttributes();  
	    	attrs.setFormat("flv");  
	    	attrs.setAudioAttributes(audio);  
	    	attrs.setVideoAttributes(video);  
	    	Encoder encoder = new Encoder();  
	    	try {
				encoder.encode(source, target, attrs);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InputFormatException e) {
				e.printStackTrace();
			} catch (EncoderException e) {
				e.printStackTrace();
			}
	    }
	public static void main(String[] args) {
		System.out.println(Double.toString(Double.MAX_VALUE));
		System.out.println(Double.MAX_VALUE);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		Integer time=(int) System.currentTimeMillis();
		Date date=new Date();
		date.setTime(time);
		System.out.println(sdf.format(date));
		changeFormatOfVideo();
//		String path = "F:/vue2.0+node.js+MongoDB高清不加密版/第1章 课程介绍/人再囧途之泰囧_高清.mp4";
//		String s="http://www.iqiyi.com/v_19rriftej7.html?src=frbdaldjunest&vfm=bdvtx&frp=v.baidu.com%2Fmovie_intro%2F&kwid=109727";
//		System.out.println(getVideoTimeLength(path));
	}
}