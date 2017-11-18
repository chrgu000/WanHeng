package com.cgwas.publishtype.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_publish_type <br/>
 *         描述：发布类型 <br/>
 */
@SuppressWarnings("serial")
public class PublishType implements Serializable {
	protected Long id;// 主键
	protected String content;// 发布类型内容

	public PublishType() {
		super();
	}

	public PublishType(Long id) {
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
