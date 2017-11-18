package com.cgwas.taskapply.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_task_apply <br/>
 *         描述：任务申请记录表 <br/>
 */
@SuppressWarnings("serial")
public class TaskApply implements Serializable {
	protected Long id;// 主键
	protected String apply_status;// 审核状态
	protected String task_type;// 任务类型
	protected Long task_id;// 任务id
	protected Long user_id;// 申请人id
	protected Long checker_id;// 审核员id
	protected Date create_time;// 创建时间
	protected Date check_time;// 审核时间

	public TaskApply() {
		super();
	}

	public TaskApply(Long id) {
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
	
	public String getApply_status() {
		return apply_status;
	}
	public void setApply_status(String apply_status) {
		this.apply_status = apply_status;
	}
	
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
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
	
	public Long getChecker_id() {
		return checker_id;
	}
	public void setChecker_id(Long checker_id) {
		this.checker_id = checker_id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getCheck_time() {
		return check_time;
	}
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
}
