package com.cgwas.userCompany.entity;

import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户和公司关系实体类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class UserCompany implements Serializable {
	protected Long id;// 主键
	protected Long use_id;// 用户id
	protected String relation;

	protected Long company_id;
	protected String company_name; // 公司名字

	protected String head_pic_path; // 公司头像
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date regist_time; // 法人注册时间
	protected String name; // 发人名字
	protected Long project_no; // 项目数量
	protected String tel; // 法人电话

	public UserCompany() {
		super();
	}

	public UserCompany(Long id) {
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

	public Long getUse_id() {
		return use_id;
	}

	public void setUse_id(Long use_id) {
		this.use_id = use_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getHead_pic_path() {
		return head_pic_path;
	}

	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}

	public Date getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProject_no() {
		return project_no;
	}

	public void setProject_no(Long project_no) {
		this.project_no = project_no;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
