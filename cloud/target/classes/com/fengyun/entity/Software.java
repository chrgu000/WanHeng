package com.fengyun.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： y_software <br/>
 *         描述：y_software <br/>
 */
@SuppressWarnings("serial")
public class Software implements Serializable {
	protected Long id;// 主键
	protected String content;// 内容
	protected Integer interest_direction_id;
	protected Integer type_id;

	public Integer getInterest_direction_id() {
		return interest_direction_id;
	}

	public void setInterest_direction_id(Integer interest_direction_id) {
		this.interest_direction_id = interest_direction_id;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Software() {
		super();
	}

	public Software(Long id) {
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
