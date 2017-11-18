package com.cgwas.privilegeInfo.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： s_privilege_info <br/>
 *         描述：权限表 <br/>
 */
@SuppressWarnings("serial")
public class PrivilegeInfo implements Serializable {
	protected Long id;// 主键
	protected String privilege_name;// 权限名称
	protected String privilege_type;// 权限类别（菜单，功能）
	protected String privilege_detail;// 权限描述
	protected String privilege_url;// 操作路径
	protected String privilege_mark;// 功能标志
	protected String privilege_icon;// 权限图标
	protected Integer sort;// 排序
	protected Long parent_id;// parent_id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date create_date;// 创建时间
	protected Integer layer;// 层级
	public PrivilegeInfo() {
		super();
	}

	public PrivilegeInfo(Long id) {
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
	
	public String getPrivilege_name() {
		return privilege_name;
	}
	public void setPrivilege_name(String privilege_name) {
		this.privilege_name = privilege_name;
	}
	
	public String getPrivilege_type() {
		return privilege_type;
	}
	public void setPrivilege_type(String privilege_type) {
		this.privilege_type = privilege_type;
	}
	
	public String getPrivilege_detail() {
		return privilege_detail;
	}
	public void setPrivilege_detail(String privilege_detail) {
		this.privilege_detail = privilege_detail;
	}
	
	public String getPrivilege_url() {
		return privilege_url;
	}
	public void setPrivilege_url(String privilege_url) {
		this.privilege_url = privilege_url;
	}
	
	public String getPrivilege_mark() {
		return privilege_mark;
	}
	public void setPrivilege_mark(String privilege_mark) {
		this.privilege_mark = privilege_mark;
	}
	
	public String getPrivilege_icon() {
		return privilege_icon;
	}
	public void setPrivilege_icon(String privilege_icon) {
		this.privilege_icon = privilege_icon;
	}
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}
}
