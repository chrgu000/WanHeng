package com.cgwas.groupUser.entity;

import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_group_user <br/>
 *         描述：项目团队成员表 <br/>
 */
@SuppressWarnings("serial")
public class GroupUser implements Serializable {
	protected Long id;// 主键
	protected String is_parent_project;// 是否是父项目(0/1）
	protected Integer num;// 序号
	protected Long user_id;// 用户id
	protected Long role_id;// 管理角色
	protected Long project_id;// 所属项目id
	protected Long company_id;// 公司id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date create_time;// 创建时间
	public GroupUser() {
		super();
	}

	public GroupUser(Long id) {
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
	
	public String getIs_parent_project() {
		return is_parent_project;
	}
	public void setIs_parent_project(String is_parent_project) {
		this.is_parent_project = is_parent_project;
	}
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	
	public Long getProject_id() {
		return project_id;
	}
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
