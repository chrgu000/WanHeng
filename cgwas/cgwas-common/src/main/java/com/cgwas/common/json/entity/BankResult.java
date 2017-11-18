package com.cgwas.common.json.entity;

import java.io.Serializable;

public class BankResult implements Serializable {
	protected String errorNo; // 错误编号
	protected String errorMsg; // 错误信息

	public String getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(String errorNo) {
		this.errorNo = errorNo;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
