package com.cgwas.common.json.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @remark [通用JSON返回结果类]
 * @extend [NONE]
 * @author [yubangqiong]
 * @date [2017年6月12日 下午1:44:47]
 */
public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9211714455826326921L;
	public static final Boolean SUCCESS = Boolean.TRUE;
	public static final Boolean FAILURE = Boolean.FALSE;

	private Boolean state;
	private String message;
	private Object data;
	private String errorNo; // 错误编号

	public Result(Boolean state, String message, Object data) {
		this.state = state;
		this.message = message;
		this.data = data;
	}

	public Result(String errorNo, Object data) {
		this.errorNo=errorNo;
		this.message=this.getErrorInfo(errorNo);
		this.data=data;
		this.state = Boolean.FALSE;
	}

	public Result(String message) {
		this.state = Boolean.TRUE;
		this.message = message;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(String errorNo) {
		this.errorNo = errorNo;
	}

	/**
	 * 根据错误编号获取错误信息
	 * 
	 * @param errorNo
	 *            错误编号
	 * @return 错误信息
	 * @throws IOException
	 */
	private String getErrorInfo(String errorNo) {
		Properties props = new Properties();
		String retn = null;
		try { // 读取配置文件中的信息
			Resource resource = new ClassPathResource("exceptions_zh_CN.properties");
			InputStream in = resource.getInputStream();
			props.load(in);
			in.close();
			retn = (String) props.get(errorNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (retn == null || "".equals(retn)) {
			return "未知的错误";
		}

		return retn;

	}

}
