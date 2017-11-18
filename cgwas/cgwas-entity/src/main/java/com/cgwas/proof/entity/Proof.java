package com.cgwas.proof.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_proof <br/>
 *         描述：p_proof <br/>
 */
@SuppressWarnings("serial")
public class Proof implements Serializable {
	protected Long id;// id
	protected Long task_id;// task_id
	protected String advince_content;// advince_content
	protected String is_model_task;// is_model_task
    protected String rate;
    protected String info;
    
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Proof() {
		super();
	}

	public Proof(Long id) {
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
	
	public Long getTask_id() {
		return task_id;
	}
	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}
	
	public String getAdvince_content() {
		return advince_content;
	}
	public void setAdvince_content(String advince_content) {
		this.advince_content = advince_content;
	}
	
	public String getIs_model_task() {
		return is_model_task;
	}
	public void setIs_model_task(String is_model_task) {
		this.is_model_task = is_model_task;
	}
}
