package com.cgwas.employee.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class EmployeeInfo implements Serializable {
	protected Long id; // 信息ID
	protected Long company_id; // 公司id
	protected Long user_id;// 用户Id
	protected String username; // 昵称
	protected String name; // 姓名
	protected String sex; // 性别
	protected Date birth; // 生日
	protected String tel; // 手机
	protected String section_name; // 部门
	protected String position_name; // 职称
	protected Date last_login_time; // 最后登录时间
	protected String emp_status_id; // 当前状态
	protected String status_name;
	protected Date begin_date; // 入职日期
	protected Date end_date; // 离职日期
	protected Long sectionId; // 职位ID
	protected Long positionId; // 部门ID
	protected String head_pic_path; // 头像路径
	// 邀请
	protected String email;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getEmp_status_id() {
		return emp_status_id;
	}

	public void setEmp_status_id(String emp_status_id) {
		this.emp_status_id = emp_status_id;
	}

	public Date getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}


	public String getHead_pic_path() {
		return head_pic_path;
	}

	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}

}
