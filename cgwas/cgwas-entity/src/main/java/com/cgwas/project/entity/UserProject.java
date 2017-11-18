package com.cgwas.project.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户项目关系类
 * 
 * @author Lingwh
 * 
 */
public class UserProject {
	protected String name; // 项目名字
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date begin_time; // 项目开始日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date end_time; // 项目结束日期
	protected String project_status_id; // 项目状态ID
	protected String content; // 项目状态名字
	protected String creater_name; // 创建名字
	protected String position; // 职位

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getProject_status_id() {
		return project_status_id;
	}

	public void setProject_status_id(String project_status_id) {
		this.project_status_id = project_status_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreater_name() {
		return creater_name;
	}

	public void setCreater_name(String creater_name) {
		this.creater_name = creater_name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
