package com.cgwas.rolePrivilege.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： s_role_privilege <br/>
 *         描述：角色权限关系表 <br/>
 */
@SuppressWarnings("serial")
public class RolePrivilege implements Serializable {
	protected Long id;// 主键
	protected Long privilege_id;// 权限id
	protected Long role_id;// 角色id
	protected String role_type;// 角色类型
	protected Long company_id;//公司id
	public RolePrivilege() {
		super();
	}

	public RolePrivilege(Long id) {
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
	
	public Long getPrivilege_id() {
		return privilege_id;
	}
	public void setPrivilege_id(Long privilege_id) {
		this.privilege_id = privilege_id;
	}
	
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	
}
