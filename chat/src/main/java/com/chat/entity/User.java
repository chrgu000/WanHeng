package com.chat.entity;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_user <br/>
 *         描述：用户表 <br/>
 */
@SuppressWarnings("serial")
public class User implements Serializable {
	protected Long id;// 主键
	protected String uuid;// UUID
	protected String username;// 用户名
	protected String nickname;// 用户昵称
	protected String tel;// 手机号
	protected String password;// 登录密码
	@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date regist_time;// 注册平台时间
	protected String last_login_time;// 上次登录时间
	protected String ip;// 登录ip
	protected Integer login_times;// 登录次数
	protected char is_delete; // 逻辑删除标志（Y/N）
	public User() {
		super();
	}

	public User(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getRegist_time() {
		return regist_time;
	}
	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}
	
	public String getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getLogin_times() {
		return login_times;
	}
	public void setLogin_times(Integer login_times) {
		this.login_times = login_times;
	}

	public char getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(char is_delete) {
		this.is_delete = is_delete;
	}
	
}
