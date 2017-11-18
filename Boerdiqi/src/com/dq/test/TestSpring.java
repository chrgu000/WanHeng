package com.dq.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dq.dao.AdminDao;
import com.dq.entity.Admin;
import com.dq.page.AdminPage;
import com.dq.page.Page;

public class TestSpring {
String config="spring-mvc.xml";
ApplicationContext app=new ClassPathXmlApplicationContext(config);
@Test
public void test(){
	AdminDao dao=app.getBean(AdminDao.class);
	Page page=new AdminPage();
	List<Admin> admins=dao.getAllByPage(page);
	for (Admin admin : admins) {
		System.out.println(admin);
	}
}
}
