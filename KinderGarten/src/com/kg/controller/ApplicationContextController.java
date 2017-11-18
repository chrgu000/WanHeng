package com.kg.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller("app")
@RequestMapping("/app")
public class ApplicationContextController implements EnvironmentAware{
 private Environment environment=null;
@RequestMapping("/a.do")
public void getApp(){
	System.out.println("ok");
	System.out.println("ww");
}

public void setEnvironment(Environment arg0) {
	environment=arg0;
	
}
}
