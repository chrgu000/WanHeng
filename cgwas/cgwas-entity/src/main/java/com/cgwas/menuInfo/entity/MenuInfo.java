package com.cgwas.menuInfo.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： s_menu_info <br/>
 *         描述：菜单表 <br/>
 */
@SuppressWarnings("serial")
public class MenuInfo implements Serializable {
	protected Long id;// 主键
	protected String menu_name;// 菜单名称
	protected Long parent_id;// 父id
	protected String sort;// 排序

	public MenuInfo() {
		super();
	}

	public MenuInfo(Long id) {
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
	
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}
