package com.cgwas.role.entity;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： s_role <br/>
 *         描述：角色表 <br/>
 */
 @SuppressWarnings("serial")
public class RoleVo extends Role {
	private long[] ids;
	public RoleVo() {
		super();
	}

	public RoleVo(Long id) {
		super();
		this.id = id;
	}

	public long[] getIds() {
		return ids;
	}

	public void setIds(long[] ids) {
		this.ids = ids;
	}


}
