package com.kg.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 教师实体类
 * 
 * @author 杨俊
 * 
 */
public class Teacher implements Serializable {
	private Integer id;
	private String username;// 教师姓名
	private String password;// 登陆密码
	private Integer garden_id;// 园区id
	private Garden garden;// 园区
	private Timestamp join_time;// 注册时间
	private String header_pic_path;// 头像路径
	private String tel;// 手机号
	private String introduce;// 个人简介
	private Integer state;// 审核状态
    private String sex;
    
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGarden_id() {
		return garden_id;
	}

	public void setGarden_id(Integer gardenId) {
		garden_id = gardenId;
	}

	public Garden getGarden() {
		return garden;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	public String getJoin_time() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(join_time==null)
			return  null;
		return sdf.format(join_time);
	}

	public void setJoin_time(Timestamp joinTime) {
		join_time = joinTime;
	}

	public String getHeader_pic_path() {
		return header_pic_path;
	}

	public void setHeader_pic_path(String headerPicPath) {
		header_pic_path = headerPicPath;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Teacher [garden=" + garden + ", garden_id=" + garden_id
				+ ", header_pic_path=" + header_pic_path + ", id=" + id
				+ ", introduce=" + introduce + ", join_time=" + join_time
				+ ", password=" + password + ", sex=" + sex + ", state="
				+ state + ", tel=" + tel + ", username=" + username + "]";
	}

}
