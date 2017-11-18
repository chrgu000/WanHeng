package com.cgwas.companyGrowth.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 公司成长实体类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class CompanyGrowth implements Serializable {
	protected Long id;// 主键
	protected String contribute;// 贡献
	protected Integer flat;// 平台币
	protected Long company_id;// 成长等级id

	public CompanyGrowth() {
		super();
	}

	public CompanyGrowth(Long id) {
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

	public String getContribute() {
		return contribute;
	}

	public void setContribute(String contribute) {
		this.contribute = contribute;
	}

	public Integer getFlat() {
		return flat;
	}

	public void setFlat(Integer flat) {
		this.flat = flat;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
