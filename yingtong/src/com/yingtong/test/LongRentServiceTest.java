package com.yingtong.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yingtong.dao.LongRentServiceDao;
import com.yingtong.entity.LongRentService;

public class LongRentServiceTest {
	BeanFactory app = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/spring-servlet.xml");
	LongRentServiceDao dao=app.getBean(LongRentServiceDao.class);
	@Test
	public void testA(){
		LongRentService l=dao.findLongRentServiceById(1);
		System.out.println(l);
		
		
	}
}
