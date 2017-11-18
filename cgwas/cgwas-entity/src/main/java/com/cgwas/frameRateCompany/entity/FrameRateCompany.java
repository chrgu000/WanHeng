package com.cgwas.frameRateCompany.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_frame_rate_company <br/>
 *         描述：公司帧数率表 <br/>
 */
@SuppressWarnings("serial")
public class FrameRateCompany implements Serializable {
	protected Long id;// 主键
	protected String content;// 帧数率描述
	protected String title;// 帧数率
	protected Long company_id;// company_id

	public FrameRateCompany() {
		super();
	}

	public FrameRateCompany(Long id) {
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
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
