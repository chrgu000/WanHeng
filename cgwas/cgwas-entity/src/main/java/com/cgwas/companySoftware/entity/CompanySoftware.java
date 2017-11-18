package com.cgwas.companySoftware.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_company_software <br/>
 *         描述：公司软件表 <br/>
 */
@SuppressWarnings("serial")
public class CompanySoftware implements Serializable {
	protected Long id;// 主键
	protected String software_name;// 软件名称
	protected Long company_id;// 公司id

	public CompanySoftware() {
		super();
	}

	public CompanySoftware(Long id) {
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
	
	public String getSoftware_name() {
		return software_name;
	}
	public void setSoftware_name(String software_name) {
		this.software_name = software_name;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
