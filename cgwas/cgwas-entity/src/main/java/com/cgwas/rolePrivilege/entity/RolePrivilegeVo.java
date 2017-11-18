package com.cgwas.rolePrivilege.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： s_role_privilege <br/>
 *         描述：角色权限关系表 <br/>
 */
 @SuppressWarnings("serial")
 @JsonInclude(value=Include.NON_NULL) 
public class RolePrivilegeVo extends RolePrivilege {
	private Long[] ids;
	private String role_type;
	private List<Long> pidList;
	private String userName;//用户名
	private Long userId;//用户id

	public String getRole_type() {
		return role_type;
	}
	
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	
	public RolePrivilegeVo() {
		super();
	}

	public RolePrivilegeVo(Long id) {
		super();
		this.id = id;
	}

	public List<Long> getPidList() {
		return pidList;
	}

	public void setPidList(List<Long> pidList) {
		this.pidList = pidList;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


}
