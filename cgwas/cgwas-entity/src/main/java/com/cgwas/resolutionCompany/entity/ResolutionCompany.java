package com.cgwas.resolutionCompany.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_resolution_company <br/>
 *         描述：公司分辨率 <br/>
 */
@SuppressWarnings("serial")
public class ResolutionCompany implements Serializable {
	protected Long id;// 主键
	protected String title;// 分辨率
	protected String content;// 分辨率描述
	protected Long company_id;// company_id

	public ResolutionCompany() {
		super();
	}

	public ResolutionCompany(Long id) {
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
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
