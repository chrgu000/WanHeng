package com.dq.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 管理员实体类
 * 
 * @author 杨俊
 * 
 */
public class Admin implements Serializable {
	private Integer id;
	private String username;// 用户名
	private String password;// 登录密码
	private Integer role_id;// 角色id
	private Role role;//角色对象
	private Timestamp join_time;// 注册时间
	private String ip;// 登录ip
	private String token;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer roleId) {
		role_id = roleId;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", ip=" + ip + ", join_time=" + join_time
				+ ", password=" + password + ", role=" + role + ", role_id="
				+ role_id + ", token=" + token + ", username=" + username + "]";
	}
public static void main(String[] args) {
	System.out.println(System.currentTimeMillis());
}
}
