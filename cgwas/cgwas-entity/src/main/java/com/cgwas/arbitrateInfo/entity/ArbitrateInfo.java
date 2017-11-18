package com.cgwas.arbitrateInfo.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_arbitrate_info <br/>
 *         描述：仲裁表 <br/>
 */
@SuppressWarnings("serial")
public class ArbitrateInfo implements Serializable {
	protected Long id;// 主键
	protected String arbitrate_title;// 仲裁标题
	protected String arbitrate_type;// 仲裁类型
	protected String arbitrate_state;// 仲裁状态
	protected String task_type;// 任务类型
	protected Long task_id;// 任务id
	protected Long apply_id;// 审核人id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date create_time;// 创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date audit_time;// 审核时间

	public ArbitrateInfo() {
		super();
	}

	public ArbitrateInfo(Long id) {
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
	
	public String getArbitrate_title() {
		return arbitrate_title;
	}
	public void setArbitrate_title(String arbitrate_title) {
		this.arbitrate_title = arbitrate_title;
	}
	
	public String getArbitrate_type() {
		return arbitrate_type;
	}
	public void setArbitrate_type(String arbitrate_type) {
		this.arbitrate_type = arbitrate_type;
	}
	
	public String getArbitrate_state() {
		return arbitrate_state;
	}
	public void setArbitrate_state(String arbitrate_state) {
		this.arbitrate_state = arbitrate_state;
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
	
	public Long getApply_id() {
		return apply_id;
	}
	public void setApply_id(Long apply_id) {
		this.apply_id = apply_id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
}
