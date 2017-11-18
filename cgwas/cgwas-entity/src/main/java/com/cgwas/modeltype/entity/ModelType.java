package com.cgwas.modeltype.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_model_type <br/>
 *         描述：模型类型 <br/>
 */
@SuppressWarnings("serial")
public class ModelType implements Serializable {
	protected Long id;// 主键
	protected String content;// 类型内容

	public ModelType() {
		super();
	}

	public ModelType(Long id) {
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
