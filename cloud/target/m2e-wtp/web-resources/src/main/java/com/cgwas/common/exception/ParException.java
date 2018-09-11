/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.cgwas.common.exception;

/**
 * 类ParException.java的实现描述：接收service处理返回异常类
 * 
 * @author yubangqiong 
 */
public class ParException extends RuntimeException {

    private static final long serialVersionUID = -4355541051845188534L;

    /**
	 * 
	 */
	private String exceptionName; 
	public ParException(String exceptionName){
		  this.exceptionName=exceptionName;
	}
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
}
