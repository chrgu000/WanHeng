package com.cgwas.companySpace.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： z_company_space <br/>
 *         描述：公司空间表 <br/>
 */
@SuppressWarnings("serial")
public class CompanySpace implements Serializable {
	protected Long id;// 主键
	protected Integer init_space;// 初始空间
	protected Long company_id;// 公司id

	public CompanySpace() {
		super();
	}

	public CompanySpace(Long id) {
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
	
	public Integer getInit_space() {
		return init_space;
	}
	public void setInit_space(Integer init_space) {
		this.init_space = init_space;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
