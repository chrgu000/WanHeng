package com.cgwas.projectStatus.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_project_status <br/>
 *         描述：项目状态表 <br/>
 */
@SuppressWarnings("serial")
public class ProjectStatus implements Serializable {
	protected Long id;// 主键
	protected String content;// 状态内容
	protected String color;// 状态对应的颜色

	public ProjectStatus() {
		super();
	}

	public ProjectStatus(Long id) {
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
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
