package com.cgwas.forbid.entity;

import java.util.Date;

/**
 * 公司禁用记录实体类
 * 
 * @author Lingwh
 * 
 */
public class CompanyForbid {
	protected String company_name; // 公司名字
	protected String content;// 禁用内容
	protected String reason; // 禁用原因
	protected Date creat_time; // 创建时间
	protected String baner; // 禁用人
	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public String getBaner() {
		return baner;
	}

	public void setBaner(String baner) {
		this.baner = baner;
	}

}
