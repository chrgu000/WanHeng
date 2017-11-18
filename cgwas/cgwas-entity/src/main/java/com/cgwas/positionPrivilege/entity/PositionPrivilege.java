package com.cgwas.positionPrivilege.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： u_position_privilege <br/>
 *         描述：公司职位权限关系表 <br/>
 */
@SuppressWarnings("serial")
public class PositionPrivilege implements Serializable {
	protected Long id;// 主键
	protected Long privilege_id;// 权限id
	protected Long position_id;// 职位id

	public PositionPrivilege() {
		super();
	}

	public PositionPrivilege(Long id) {
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
	
	public Long getPosition_id() {
		return position_id;
	}
	public void setPosition_id(Long position_id) {
		this.position_id = position_id;
	}
}
