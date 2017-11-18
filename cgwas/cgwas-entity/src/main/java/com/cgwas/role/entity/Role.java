package com.cgwas.role.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： s_role <br/>
 *         描述：角色表 <br/>
 */
@SuppressWarnings("serial")
public class Role implements Serializable {
	protected Long id;// 主键
	protected String role_name;// 角色名称
	protected String role_details;// 角色描述
	protected String role_type;// 角色类型
	public Role() {
		super();
	}

	public Role(Long id) {
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
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public String getRole_details() {
		return role_details;
	}
	public void setRole_details(String role_details) {
		this.role_details = role_details;
	}

	public String getRole_type() {
		return role_type;
	}

	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
}
