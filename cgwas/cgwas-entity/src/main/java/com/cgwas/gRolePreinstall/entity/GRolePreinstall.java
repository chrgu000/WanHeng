package com.cgwas.gRolePreinstall.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_g_role_preinstall <br/>
 *         描述：团队角色预设表 <br/>
 */
@SuppressWarnings("serial")
public class GRolePreinstall implements Serializable {
	protected Long id;// 主键
	protected String role_name;// 角色名称

	public GRolePreinstall() {
		super();
	}

	public GRolePreinstall(Long id) {
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
}
