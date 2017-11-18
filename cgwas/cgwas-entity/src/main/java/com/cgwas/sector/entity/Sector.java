package com.cgwas.sector.entity;

import javax.persistence.Id;
import java.io.Serializable;


/**
 * 行业实体类
 * @author Lingwh
 *
 */
@SuppressWarnings("serial")
public class Sector implements Serializable {
	protected Long id;// 主键
	protected String content;// 行业内容
	protected String details;// 行业描述
	protected Long company_id;// 主键

	public Sector() {
		super();
	}

	public Sector(Long id) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
