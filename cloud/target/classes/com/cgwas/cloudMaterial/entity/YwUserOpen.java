package com.cgwas.cloudMaterial.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class YwUserOpen implements Serializable{
	
	private Integer user_open_id;
	private Integer user_id;
	private String open_id;
	private String is_open;
	private String nickname;
	private String headimgurl;
	private String city;
	private String sex;
	private String province;
	public Integer getUser_open_id() {
		return user_open_id;
	}
	public void setUser_open_id(Integer user_open_id) {
		this.user_open_id = user_open_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public String getIs_open() {
		return is_open;
	}
	public void setIs_open(String is_open) {
		this.is_open = is_open;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public YwUserOpen( String open_id,
			String is_open, String nickname, String headimgurl, String city,
			String sex, String province) {
		super();
		this.open_id = open_id;
		this.is_open = is_open;
		this.nickname = nickname;
		this.headimgurl = headimgurl;
		this.city = city;
		this.sex = sex;
		this.province = province;
	}
	public YwUserOpen() {
		super();
	}
}
