package com.cgwas.gRoleMenu.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_g_role_menu <br/>
 *         描述：菜单团队角色关系表 <br/>
 */
@SuppressWarnings("serial")
public class GRoleMenu implements Serializable {
	protected Long id;// 主键
	protected Long menu_id;// 权限内容
	protected Long g_role_id;// 角色id

	public GRoleMenu() {
		super();
	}

	public GRoleMenu(Long id) {
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
	
	public Long getG_role_id() {
		return g_role_id;
	}
	public void setG_role_id(Long g_role_id) {
		this.g_role_id = g_role_id;
	}
}
