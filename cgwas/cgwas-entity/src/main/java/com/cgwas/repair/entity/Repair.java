package com.cgwas.repair.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

/**
 * @author yangjun <br/>s
 *         表名： p_repair <br/>
 *         描述：返修表 <br/>
 */
@SuppressWarnings("serial")
public class Repair implements Serializable {
	protected Long id;// 主键
	protected Long task_id;// 任务id
	protected String advice_content;// 意见文字描述
	protected String is_model_task;// 是否为模型任务
	protected Long num;// 返修次数
	public Repair() {
		super();
	}

	public Repair(Long id) {
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
	
	public String getAdvice_content() {
		return advice_content;
	}
	public void setAdvice_content(String advice_content) {
		this.advice_content = advice_content;
	}
	
	public String getIs_model_task() {
		return is_model_task;
	}
	public void setIs_model_task(String is_model_task) {
		this.is_model_task = is_model_task;
	}
	
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
}
