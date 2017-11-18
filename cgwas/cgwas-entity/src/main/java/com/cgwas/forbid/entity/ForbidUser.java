package com.cgwas.forbid.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 可禁用用户
 * 
 * @author Lingwh
 * 
 */
public class ForbidUser {
	protected Long id;// ID
	protected String nickname; // 用户名
	protected String name; // 姓名
	protected String sex; // 性别
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date birth; // 生日
	protected String tel; // 电话
	protected String company_name; // 所属公司
	protected String relation; // 职位
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date last_login_time; // 上次登录
	protected String status; // 认证状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date regist_time; // 注册时间

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}

}
