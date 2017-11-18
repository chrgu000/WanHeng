package com.cgwas.gRolePrivilege.entity;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_g_role_privilege <br/>
 *         描述：团队角色权限关系表 <br/>
 */
 @SuppressWarnings("serial")
public class GRolePrivilegeVo extends GRolePrivilege {
	 private Long[] ids;
	 private String userName;//用户名
	 private Long userId;//用户id
	public GRolePrivilegeVo() {
		super();
	}

	public GRolePrivilegeVo(Long id) {
		super();
		this.id = id;
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
