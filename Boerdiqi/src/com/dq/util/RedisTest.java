package com.dq.util;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTest {
String config="applicationContext.xml";
ApplicationContext app=new ClassPathXmlApplicationContext(config);
@Test
public void getConnection(){
	System.out.println(app);
}
}
