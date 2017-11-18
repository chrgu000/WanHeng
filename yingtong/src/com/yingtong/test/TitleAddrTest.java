package com.yingtong.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yingtong.dao.TitleAddrDao;
import com.yingtong.entity.TitleAddr;

public class TitleAddrTest {
	BeanFactory app = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/spring-servlet.xml");
	@Test
	public void testTitleAddr(){
		TitleAddrDao dao=app.getBean(TitleAddrDao.class);
		TitleAddr td=dao.findTitleAddrById(1);
		System.out.println(td);
	}
}
