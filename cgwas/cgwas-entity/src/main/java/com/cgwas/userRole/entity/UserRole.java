package com.cgwas.userRole.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： u_user_role <br/>
 *         描述：用户角色关系表 <br/>
 */
@SuppressWarnings("serial")
public class UserRole implements Serializable {
	protected Long id;// 主键
	protected Long user_id;// 用户id
	protected Long role_id;// 角色id

	public UserRole() {
		super();
	}

	public UserRole(Long id) {
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
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
}
