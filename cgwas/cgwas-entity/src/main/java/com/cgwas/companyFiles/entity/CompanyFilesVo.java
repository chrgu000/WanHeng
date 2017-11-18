package com.cgwas.companyFiles.entity;

import java.util.List;

import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_company_files <br/>
 *         描述：公司文件夹表 <br/>
 */
 @SuppressWarnings("serial")
 @JsonInclude(value=Include.NON_NULL) 
public class CompanyFilesVo extends CompanyFiles {
	 private Long company_id;
	 private Long user_id;
	 private String company_name;
	 private Long menu_id;
	 private List<PrivilegeInfoVo> privilegeInfoList;
	 private String role_type;//角色类型
	 private String gRole_type;//团队角色类型
	 private Long visible;
	 private List<Long> ids;
	 private Long project_id; //项目id
	 private String is_parent_project;//是否是父项目
	public CompanyFilesVo() {
		super();
	}

	public CompanyFilesVo(Long id) {
		super();
		this.id = id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public List<PrivilegeInfoVo> getPrivilegeInfoList() {
		return privilegeInfoList;
	}

	public void setPrivilegeInfoList(List<PrivilegeInfoVo> privilegeInfoList) {
		this.privilegeInfoList = privilegeInfoList;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public Long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}

	public String getRole_type() {
		return role_type;
	}

	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}

	public String getgRole_type() {
		return gRole_type;
	}

	public void setgRole_type(String gRole_type) {
		this.gRole_type = gRole_type;
	}

	public Long getVisible() {
		return visible;
	}

	public void setVisible(Long visible) {
		this.visible = visible;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public String getIs_parent_project() {
		return is_parent_project;
	}

	public void setIs_parent_project(String is_parent_project) {
		this.is_parent_project = is_parent_project;
	}


}
