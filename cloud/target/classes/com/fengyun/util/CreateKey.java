package com.fengyun.util;

import java.text.ParseException;
import java.util.Arrays;

public class CreateKey {
	public static String getStudyKey(String AppName,String StreamName){
		MD5 md5 = new MD5();
		String uri="/"+Pinyin4jUtil.getPinYinHeadChar(AppName)+"/"+Pinyin4jUtil.getPinYinHeadChar(StreamName)+".flv";
		String s=36000+System.currentTimeMillis()/1000+"";
		String str=uri+"-"+s+"-0-0-"+"sn2CQoNymw";
		String hashValue=md5.getMD5ofStr(str).toLowerCase();
 		return "https://zhibo.woheyun.com"+uri+"?auth_key="+s+"-0-0-"+hashValue;
	}
	public static String getTeachKey(String AppName,String StreamName){
		MD5 md5=new MD5();
		String uri="/"+Pinyin4jUtil.getPinYinHeadChar(AppName)+"/"+Pinyin4jUtil.getPinYinHeadChar(StreamName);
		String s=36000+System.currentTimeMillis()/1000+"";
		String str=uri+"-"+s+"-0-0-"+"sn2CQoNymw";
		String hashValue=md5.getMD5ofStr(str).toLowerCase();
		return "rtmp://video-center.alivecdn.com"+uri+"?vhost=zhibo.woheyun.com&auth_key="+s+"-0-0-"+hashValue;
	}
	public static void main(String[] args) throws ParseException {
		 System.out.println(getTeachKey("12_27","27"));
		 System.out.println(getStudyKey("12_27","27"));
	}
}
