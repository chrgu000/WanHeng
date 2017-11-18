package com.cgwas.userPrivilege.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： u_user_privilege <br/>
 *         描述：用户权限关系表 <br/>
 */
@SuppressWarnings("serial")
public class UserPrivilege implements Serializable {
	protected Long id;// id
	protected Long privilege_id;// 权限id
	protected Long user_id;// 用户id

	public UserPrivilege() {
		super();
	}

	public UserPrivilege(Long id) {
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
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
