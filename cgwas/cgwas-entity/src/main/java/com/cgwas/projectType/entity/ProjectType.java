package com.cgwas.projectType.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_project_type <br/>
 *         描述：项目类型 <br/>
 */
@SuppressWarnings("serial")
public class ProjectType implements Serializable {
	protected Long id;// 主键
	protected String content;// 内容

	public ProjectType() {
		super();
	}

	public ProjectType(Long id) {
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
}
