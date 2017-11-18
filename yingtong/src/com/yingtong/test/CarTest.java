package com.yingtong.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yingtong.entity.User;

public class CarTest {
	private Integer number = 0;
	BeanFactory app = new FileSystemXmlApplicationContext(
			"WebRoot/WEB-INF/spring-servlet.xml");
 public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Timestamp t = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				 String time = sdf.format(t);
				if (time.equals("00:00:00")) {
					System.out.println(true);
				}
			}
		}, 1000, 1000);
}
	public void getCar() {
	
	}

}
