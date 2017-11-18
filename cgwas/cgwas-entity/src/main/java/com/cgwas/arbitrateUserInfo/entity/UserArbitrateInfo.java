package com.cgwas.arbitrateUserInfo.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserArbitrateInfo implements Serializable {
	protected Long id; // 仲裁信息id
	protected String is_pass; //是否胜利
	
	

	protected String nickname; // 昵称
	protected String head_pic_path; // 头像路径
	protected String flat; // 分数
	protected String sex; // 性别
	protected String arbitrate_content; // 内容
	protected char user_type; // 用户类型
	protected Long arbitrate_id; // 所属仲裁编号
	protected Long company_id; // 对方Id
	protected Long user_id; // 对方Id
	protected String company_name; // 对方名字
	protected String head_pic_path1; // 对方头像路径
	protected double flat1; // 对方积分
	
	public String getIs_pass() {
		return is_pass;
	}

	public void setIs_pass(String is_pass) {
		this.is_pass = is_pass;
	}
	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getHead_pic_path1() {
		return head_pic_path1;
	}

	public void setHead_pic_path1(String head_pic_path1) {
		this.head_pic_path1 = head_pic_path1;
	}

	public double getFlat1() {
		return flat1;
	}

	public void setFlat1(double flat1) {
		this.flat1 = flat1;
	}

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

	public String getHead_pic_path() {
		return head_pic_path;
	}

	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getArbitrate_content() {
		return arbitrate_content;
	}

	public void setArbitrate_content(String arbitrate_content) {
		this.arbitrate_content = arbitrate_content;
	}

	public char getUser_type() {
		return user_type;
	}

	public void setUser_type(char c) {
		this.user_type = c;
	}

	public Long getArbitrate_id() {
		return arbitrate_id;
	}

	public void setArbitrate_id(Long arbitrate_id) {
		this.arbitrate_id = arbitrate_id;
	}

}
