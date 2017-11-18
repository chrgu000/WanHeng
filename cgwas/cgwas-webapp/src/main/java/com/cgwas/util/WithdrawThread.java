package com.cgwas.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgwas.common.json.entity.LmWithdraw;
import com.cgwas.common.spring.CurrentSpringContext;
import com.cgwas.withdraw.service.api.IWithdrawService;

@Component
public class WithdrawThread extends Thread {	  	   
		  
	@Autowired
	private IWithdrawService withdrawService;
	
	private LmWithdraw lmWithdraw;
	private String password;
	private String ipAddress;
	
	//本身就是一个Thread，就不用再new一个  
    public void run(){ //run方法里写的是什么，线程调用时执行的就是什么  
    	System.out.println("密码:"+password);
    	withdrawService = (IWithdrawService)CurrentSpringContext.getBean("lmWithdrawService");
	    String a=withdrawService.addWithdraw( lmWithdraw,password,ipAddress);
	    System.out.println(a);
    }
	
	public LmWithdraw getLmWithdraw() {
		return lmWithdraw;
	}
	public void setLmWithdraw(LmWithdraw lmWithdraw) {
		this.lmWithdraw = lmWithdraw;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	} 
}
	  
	