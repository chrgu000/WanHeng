package com.yingtong.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yingtong.dao.AdminDao;

public class AdminTest {
	BeanFactory app = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/spring-servlet.xml");
@Test
public void addTest(){
	AdminDao dao=app.getBean(AdminDao.class);
	 
  System.out.println(dao.findRows());
}
}
