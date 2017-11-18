package com.cgwas.forbid.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 可禁用公司实体类
 * 
 * @author Lingwh
 * 
 */
public class ForbidCompany {
	protected Long id; // 用户iD
	protected String nickname;// 昵称
	protected String company_name;// 企业名称
	protected String legal_person; // 法人
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date company_begin_date; // 成立日期
	protected String registered_assets; // 注册资本
	protected String status; // 登记状态
	protected String tel; // 电话
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date last_login_time; // 上次登录时间
	protected String status1; // 实名认证状态
	protected String province; // 省份
	protected String city; // 城市
	protected String area; // 地区
	protected String address;// 详细地址

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCompany_begin_date() {
		return company_begin_date;
	}

	public void setCompany_begin_date(Date company_begin_date) {
		this.company_begin_date = company_begin_date;
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

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
