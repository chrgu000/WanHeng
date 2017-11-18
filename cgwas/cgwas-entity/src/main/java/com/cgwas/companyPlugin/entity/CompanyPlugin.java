package com.cgwas.companyPlugin.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_company_plugin <br/>
 *         描述：公司插件表 <br/>
 */
@SuppressWarnings("serial")
public class CompanyPlugin implements Serializable {
	protected Long id;// 主键
	protected String plugin_name;// 插件名称
	protected Long company_id;// 公司id

	public CompanyPlugin() {
		super();
	}

	public CompanyPlugin(Long id) {
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
	
	public String getPlugin_name() {
		return plugin_name;
	}
	public void setPlugin_name(String plugin_name) {
		this.plugin_name = plugin_name;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
