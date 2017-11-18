package com.cgwas.arbitrateInfo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class ArbitrateInfoHead implements Serializable {

	protected String arbitrate_state; // 仲裁状态
	protected String arbitrate_type; // 仲裁类型
	protected String project_name; // 项目名字
	protected String task_name; // 任务名字
	protected String task_status; // 任务状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_time; // 创建时间

	public String getArbitrate_state() {
		return arbitrate_state;
	}

	public void setArbitrate_state(String arbitrate_state) {
		this.arbitrate_state = arbitrate_state;
	}

	public String getArbitrate_type() {
		return arbitrate_type;
	}

	public void setArbitrate_type(String arbitrate_type) {
		this.arbitrate_type = arbitrate_type;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getTask_status() {
		return task_status;
	}

	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}



}
