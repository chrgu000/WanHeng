package com.kg.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 管理员实体类
 * 
 * @author 杨俊
 * 
 */
public class Admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;// 用户名
	private String password;// 密码
	private Integer role_id;// 角色id
	private Timestamp join_time;// 注册时间
	private Integer garden_id;// 园区id
	private Role role;
	private List<Garden> gardens;
    private String ip;
    private String token;
    private String gardenes;
    
    
	public String getGardenes() {
		return gardenes;
	}

	public void setGardenes(String gardenes) {
		this.gardenes = gardenes;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Garden> getGardens() {
		return gardens;
	}

	public void setGardens(List<Garden> gardens) {
		this.gardens = gardens;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Integer getGarden_id() {
		return garden_id;
	}

	public void setGarden_id(Integer gardenId) {
		garden_id = gardenId;
	}

	 
	
}
