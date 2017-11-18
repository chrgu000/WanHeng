package com.cgwas.companyAuthInfo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 得到可认证 公司列表实体类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class AttestationCompanyAuthInfo implements Serializable {
	protected Long id; // 公司id
	protected String nickname; // 用户昵称
	protected String company_name; // 公司名字
	protected String legal_person; // 法人代表
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date company_begin_date; // 公司成立日期
	protected Long registered_assets; // 公司注册资本
	protected String status; // 公司
	protected String tel; // 联系电话
	protected String status1; // 实名认证状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date last_login_time; // 最后登录日期
	protected String province; // 所在省份
	protected String city; // 所在城市
	protected String area;// param2
	protected String address;// param3
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

	public Date getCompany_begin_date() {
		return company_begin_date;
	}

	public void setCompany_begin_date(Date company_begin_date) {
		this.company_begin_date = company_begin_date;
	}

	public Long getRegistered_assets() {
		return registered_assets;
	}

	public void setRegistered_assets(Long registered_assets) {
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

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
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
