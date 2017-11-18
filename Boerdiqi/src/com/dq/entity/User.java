package com.dq.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 买家实体类
 * 
 * @author 杨俊
 * 
 */
public class User implements Serializable {
	private Integer id;
	private String tel;//手机号
	private String openId;//微信openId
	private String imgUrl;//微信头像路径
	private String nickname;// 买家用户名
	private String password;// 登录密码
	private Integer integral;//积分
	private Timestamp join_time;// 注册时间

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJoin_time() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(join_time==null)
			return null;
		return sdf.format(join_time);
	}

	public void setJoin_time(Timestamp joinTime) {
		join_time = joinTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", imgUrl=" + imgUrl + ", integral="
				+ integral + ", join_time=" + join_time + ", nickname="
				+ nickname + ", openId=" + openId + ", password=" + password
				+ ", tel=" + tel + "]";
	}

}
