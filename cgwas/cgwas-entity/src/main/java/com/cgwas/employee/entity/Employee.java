package com.cgwas.employee.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 员工实体类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class Employee implements Serializable {
	protected Long id;// 主键
	protected String name;// 姓名
	protected Long section_id;// 部门id
	protected Long position_id;// 职位id
	protected Long user_id;// 用户名
	protected Long company_id;// 公司id
	protected Long emp_status_id;// 状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date begin_date;// 入职时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date end_date;// 离职时间
	protected String is_delete;

	public Employee() {
		super();
	}

	public Employee(Long id) {
		super();
		this.id = id;
	}

	@Id
	// 主键
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSection_id() {
		return section_id;
	}

	public void setSection_id(Long section_id) {
		this.section_id = section_id;
	}

	public Long getPosition_id() {
		return position_id;
	}

	public void setPosition_id(Long position_id) {
		this.position_id = position_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public Long getEmp_status_id() {
		return emp_status_id;
	}

	public void setEmp_status_id(Long emp_status_id) {
		this.emp_status_id = emp_status_id;
	}

	public Date getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
}
