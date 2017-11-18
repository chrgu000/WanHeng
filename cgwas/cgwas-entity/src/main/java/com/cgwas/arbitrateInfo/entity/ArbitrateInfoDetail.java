package com.cgwas.arbitrateInfo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class ArbitrateInfoDetail implements Serializable {
	protected Long id; // id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date start_time; // 开始时间
	protected String arbitrate_type; // 仲裁类型
	protected String arbitrate_title; // 仲裁标题
	protected String arbitrate_state; // 仲裁状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date audit_time; // 仲裁时间
	private String nickname; // 发起方名字
	private String company_name; // 被告方名字
	private String aname; // 发起方名字
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}

	private String dname; // 被告方名字
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public String getArbitrate_type() {
		return arbitrate_type;
	}
	public void setArbitrate_type(String arbitrate_type) {
		this.arbitrate_type = arbitrate_type;
	}
	public String getArbitrate_title() {
		return arbitrate_title;
	}
	public void setArbitrate_title(String arbitrate_title) {
		this.arbitrate_title = arbitrate_title;
	}
	public String getArbitrate_state() {
		return arbitrate_state;
	}
	public void setArbitrate_state(String arbitrate_state) {
		this.arbitrate_state = arbitrate_state;
	}
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
	
//	protected String company_name; // 公司名字
	
//	protected Date create_time; // 创建时间
	
	
	//protected String arbitrate_content; // 仲裁内容
	
	

	protected String user_type; // 用户类型

	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	
}
