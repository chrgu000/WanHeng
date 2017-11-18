package com.cgwas.companySection.entity;

import java.io.Serializable;

import javax.persistence.Id;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： u_company_section <br/>
 *         描述：公司部门表 <br/>
 */
@SuppressWarnings("serial")
public class CompanySection implements Serializable {
	protected Long id;// 主键
	protected String section_name;// 部门名称
	protected String section_details;// 部门描述
	protected Long company_id;// 公司id
	protected String is_delete; // 是否删除
	public CompanySection() {
		super();
	}

	public CompanySection(Long id) {
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
	
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	
	public String getSection_details() {
		return section_details;
	}
	public void setSection_details(String section_details) {
		this.section_details = section_details;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	
}
