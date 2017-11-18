package com.cgwas.gRole.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_g_role <br/>
 *         描述：团队角色表 <br/>
 */
@SuppressWarnings("serial")
public class GRole implements Serializable {
	protected Long id;// 主键
	protected String role_name;// 角色名称
	protected Long for_id;// 管理层级
	protected String is_parent_poject;// is_parent_poject
	protected Long poject_id;// 所属项目id
	protected Integer num;// 序号
	protected Long role_p_id;//是否可更改

	public GRole() {
		super();
	}

	public GRole(Long id) {
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
	
	public Long getFor_id() {
		return for_id;
	}
	public void setFor_id(Long for_id) {
		this.for_id = for_id;
	}
	
	public String getIs_parent_poject() {
		return is_parent_poject;
	}
	public void setIs_parent_poject(String is_parent_poject) {
		this.is_parent_poject = is_parent_poject;
	}
	
	public Long getProject_id() {
		return poject_id;
	}
	public void setProject_id(Long poject_id) {
		this.poject_id = poject_id;
	}
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getRole_p_id() {
		return role_p_id;
	}

	public void setRole_p_id(Long role_p_id) {
		this.role_p_id = role_p_id;
	}
}
