package com.cgwas.evaluation.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_evaluation <br/>
 *         描述：p_evaluation <br/>
 */
@SuppressWarnings("serial")
public class Evaluation implements Serializable {
	protected Long id;// id
	protected String content;// content
	protected Double evaluation_num;// evaluation_num

	public Evaluation() {
		super();
	}

	public Evaluation(Long id) {
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
	
	public Double getEvaluation_num() {
		return evaluation_num;
	}
	public void setEvaluation_num(Double evaluation_num) {
		this.evaluation_num = evaluation_num;
	}
}
