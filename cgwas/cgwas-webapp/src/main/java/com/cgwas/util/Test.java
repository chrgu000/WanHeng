package com.cgwas.util;

import java.util.Hashtable;
import java.util.Map;


public class Test {
	public static void main(String[] args) {
		//hashMap key和value可以为空,但线程不安全 hashtable key和value不能为空,线程安全
		Map<String,Object> map=new Hashtable<String, Object>();
		map.put("name", "杨俊");
		map.put("age", 27);
		map.put("salary",7500);
		map.put("score", 88);
		 
		
	}
}
