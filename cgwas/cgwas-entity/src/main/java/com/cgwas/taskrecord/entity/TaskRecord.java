package com.cgwas.taskrecord.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_task_record <br/>
 *         描述：p_task_record <br/>
 */
@SuppressWarnings("serial")
public class TaskRecord implements Serializable {
	protected Long id;// id
	protected Long totalNums;// totalNums
	protected Long runnNums;// runnNums
	protected Long checkNuns;// checkNuns
	protected Long finishedNums;// finishedNums
	protected Long abandonNums;// abandonNums
	protected Date create_time;// create_time
    protected String is_parent_project;
    protected Long project_id;
    
	public String getIs_parent_project() {
		return is_parent_project;
	}

	public void setIs_parent_project(String is_parent_project) {
		this.is_parent_project = is_parent_project;
	}

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public TaskRecord() {
		super();
	}

	public TaskRecord(Long id) {
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
	
	 
	public Long getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(Long totalNums) {
		this.totalNums = totalNums;
	}

	public Long getRunnNums() {
		return runnNums;
	}

	public void setRunnNums(Long runnNums) {
		this.runnNums = runnNums;
	}

	public Long getCheckNuns() {
		return checkNuns;
	}

	public void setCheckNuns(Long checkNuns) {
		this.checkNuns = checkNuns;
	}

	public Long getFinishedNums() {
		return finishedNums;
	}

	public void setFinishedNums(Long finishedNums) {
		this.finishedNums = finishedNums;
	}

	public Long getAbandonNums() {
		return abandonNums;
	}

	public void setAbandonNums(Long abandonNums) {
		this.abandonNums = abandonNums;
	}

	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
