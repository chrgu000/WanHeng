package com.cgwas.labelTag.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_label_tag <br/>
 *         描述：标签表 <br/>
 */
@SuppressWarnings("serial")
public class LabelTag implements Serializable {
	protected Long id;// 主键
	protected String label_name;// 标签名称
	 

	public LabelTag() {
		super();
	}

	public LabelTag(Long id) {
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
	
	public String getLabel_name() {
		return label_name;
	}
	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}
	
	 
}
