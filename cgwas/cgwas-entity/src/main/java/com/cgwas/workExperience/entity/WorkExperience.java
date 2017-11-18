package com.cgwas.workExperience.entity;



import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_work_experience <br/>
 *         描述：工作经历表 <br/>
 */
@SuppressWarnings("serial")
public class WorkExperience implements Serializable {
	protected Long id;// 主键
	@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date work_begin_date;// 入职日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date work_end_date;// 离职日期
	protected String company_name;// 公司名称
	protected String position;// 职位
	protected String work_details;// 工作描述
	protected Long user_id;// user_id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date creat_time;// 创建时间

	public WorkExperience() {
		super();
	}

	public WorkExperience(Long id) {
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
	
	public Date getWork_begin_date() {
		return work_begin_date;
	}
	public void setWork_begin_date(Date work_begin_date) {
		this.work_begin_date = work_begin_date;
	}
	
	public Date getWork_end_date() {
		return work_end_date;
	}
	public void setWork_end_date(Date work_end_date) {
		this.work_end_date = work_end_date;
	}
	
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getWork_details() {
		return work_details;
	}
	public void setWork_details(String work_details) {
		this.work_details = work_details;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Date getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
}
