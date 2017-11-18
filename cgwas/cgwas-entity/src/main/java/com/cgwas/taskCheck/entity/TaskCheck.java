package com.cgwas.taskCheck.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author yangjun <br/>s
 *         表名： a_task_check <br/>
 *         描述：任务审核表 <br/>
 */
@SuppressWarnings("serial")
public class TaskCheck implements Serializable {
	protected Long tid;// 主键
	protected String task_type;// 任务类型
	protected Long audit_order;// 审核对应序号
	protected Long task_id;// 任务id
	protected Long user_id;// 制作者id
	protected Date submit_time;// 任务提交时间
    protected Long project_id;
    protected String is_parent_project;
    protected Long num;//返修次数
    
	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public String getIs_parent_project() {
		return is_parent_project;
	}

	public void setIs_parent_project(String is_parent_project) {
		this.is_parent_project = is_parent_project;
	}

	public TaskCheck() {
		super();
	}

	public TaskCheck(Long id) {
		super();
		this.tid = id;
	}
	
	
	
	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	
	 
	
	public Long getAudit_order() {
		return audit_order;
	}

	public void setAudit_order(Long audit_order) {
		this.audit_order = audit_order;
	}

	public Long getTask_id() {
		return task_id;
	}
	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Date getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}
}
