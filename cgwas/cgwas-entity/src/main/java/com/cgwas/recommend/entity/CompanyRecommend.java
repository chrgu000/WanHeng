package com.cgwas.recommend.entity;

import java.util.Date;

public class CompanyRecommend {
	protected Long id; // ID
	protected String nickname; // 昵称
	protected String company_name; // 公司名字
	protected String legal_person; // 法人
	protected Date establish_date; // 成立日期
	protected String registered_assets; // 注册资本
	protected String status; // 登记状态
	protected String tel; // 手机号
	protected String priority; // 优先级
	protected String status1; // 实名认证
	protected String reason; // 推荐原因

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLegal_person() {
		return legal_person;
	}

	public void setLegal_person(String legal_person) {
		this.legal_person = legal_person;
	}

	public Date getEstablish_date() {
		return establish_date;
	}

	public void setEstablish_date(Date establish_date) {
		this.establish_date = establish_date;
	}

	public String getRegistered_assets() {
		return registered_assets;
	}

	public void setRegistered_assets(String registered_assets) {
		this.registered_assets = registered_assets;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
