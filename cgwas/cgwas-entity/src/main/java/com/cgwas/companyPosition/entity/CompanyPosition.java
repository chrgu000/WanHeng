package com.cgwas.companyPosition.entity;

import java.io.Serializable;

import javax.persistence.Id;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： u_company_position <br/>
 *         描述：职位表 <br/>
 */
@SuppressWarnings("serial")
public class CompanyPosition implements Serializable {
	protected Long id;// 主键
	protected String position_name;// 职位名称
	protected String position_details;// 职位描述
	protected Long company_id;// 公司id
	protected String is_delete;// 是否删除

	public CompanyPosition() {
		super();
	}

	public CompanyPosition(Long id) {
		super();
		this.id = id;
	}

	@Id
	// 主键
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}

	public String getPosition_details() {
		return position_details;
	}

	public void setPosition_details(String position_details) {
		this.position_details = position_details;
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
