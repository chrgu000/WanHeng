package com.cgwas.recommend.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户推荐实体类
 * 
 * @author Lingwh
 * 
 */
public class UserRecommend {
	protected Long id; // 用户id
	protected String nickname; // 用户名
	protected String name; // 姓名
	protected String sex; // 性别
	@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date birth; // 生日
	protected String tel; // 电话
	protected String company_name; // 公司
	protected String relation; // 职称
	protected String fpriority; // 优先级
	protected String reason; // 推荐原因
	protected String status;// 实名认证

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

	public String getFpriority() {
		return fpriority;
	}

	public void setFpriority(String fpriority) {
		this.fpriority = fpriority;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
