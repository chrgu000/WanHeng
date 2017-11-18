package com.cgwas.positionMenu.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： u_position_menu <br/>
 *         描述：职位菜单关系表 <br/>
 */
@SuppressWarnings("serial")
public class PositionMenu implements Serializable {
	protected Long id;// 主键
	protected Long position_id;// 职位id
	protected Long menu_id;// 菜单id

	public PositionMenu() {
		super();
	}

	public PositionMenu(Long id) {
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
	
	public Long getPosition_id() {
		return position_id;
	}
	public void setPosition_id(Long position_id) {
		this.position_id = position_id;
	}
	
	public Long getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}
}
