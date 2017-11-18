package com.cgwas.notice.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_notice <br/>
 *         描述：p_notice <br/>
 */
@SuppressWarnings("serial")
public class Notice implements Serializable {
	protected Long id;// id
	protected String content;// 内容
	protected String stage;// 任务阶段
	protected String task_status;// 任务状态
	protected String role;// 角色

	public Notice() {
		super();
	}

	public Notice(Long id) {
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
	
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	public String getTask_status() {
		return task_status;
	}
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", content=" + content + ", stage=" + stage
				+ ", task_status=" + task_status + ", role=" + role + "]";
	}
	
}
