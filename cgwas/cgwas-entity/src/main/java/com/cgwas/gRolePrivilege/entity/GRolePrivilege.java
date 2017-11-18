package com.cgwas.gRolePrivilege.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_g_role_privilege <br/>
 *         描述：团队角色权限关系表 <br/>
 */
@SuppressWarnings("serial")
public class GRolePrivilege implements Serializable {
	protected Long id;// 主键
	protected Long privilege_id;// 权限id
	protected Long role_id;// 团队角色id
	protected Long company_file_id;// 公司文件id

	public GRolePrivilege() {
		super();
	}

	public GRolePrivilege(Long id) {
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
	
	public Long getCompany_file_id() {
		return company_file_id;
	}
	public void setCompany_file_id(Long company_file_id) {
		this.company_file_id = company_file_id;
	}
}
