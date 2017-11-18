package com.cgwas.menuRole.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： s_menu_role <br/>
 *         描述：菜单角色关系表 <br/>
 */
@SuppressWarnings("serial")
public class MenuRole implements Serializable {
	protected Long id;// 主键
	protected Long menu_id;// 权限内容
	protected Long role_id;// 角色id

	public MenuRole() {
		super();
	}

	public MenuRole(Long id) {
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
	
	public Long getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}
	
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
}
