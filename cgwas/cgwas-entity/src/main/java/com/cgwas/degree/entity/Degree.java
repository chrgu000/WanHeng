package com.cgwas.degree.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_degree <br/>
 *         描述：难易度表 <br/>
 */
@SuppressWarnings("serial")
public class Degree implements Serializable {
	protected Long id;// 主键
	protected String content;// 内容
	protected Double degree_of_difficulty;// 难度系数

	public Degree() {
		super();
	}

	public Degree(Long id) {
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
	
	public Double getDegree_of_difficulty() {
		return degree_of_difficulty;
	}
	public void setDegree_of_difficulty(Double degree_of_difficulty) {
		this.degree_of_difficulty = degree_of_difficulty;
	}
}
