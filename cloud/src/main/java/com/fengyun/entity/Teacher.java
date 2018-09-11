package com.fengyun.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

/**
 * @author yangjun <br/>s
 *         表名： y_teacher <br/>
 *         描述：y_teacher <br/>
 */
@SuppressWarnings("serial")
public class Teacher implements Serializable {
	protected Long id;// 主键
	protected Long user_id;// 用户id
	protected Integer evaluation_score;// 综合评分
	protected Integer course_num;// 已授课程数量
	protected Integer project_score;// 项目积分
	protected String name;// 真实名称
	protected String introduce;// 个人介绍
	protected String head_pic_url;// 头像封面路径
	protected String teacher_certification;// 教师资格证
	protected String academic_certificate;// 学历证书
	protected String other_certificate;// 其他证书
	protected String composition;//作品
	protected String check_status;// 审核状态(Y为通过,N为未通过,C为审核中)
	protected String is_apply;//是否是邀约讲师(Y为是，N为否)'
	protected Date apply_date;// 申请日期
	protected String role_type;// 讲师角色(Y为固定讲师,N不是固定讲师)
	protected String check_idea;// 审核意见
    protected Map<String,Object> search=new HashMap<String,Object>();
    protected List<Course> courses;
    
    
	public String getIs_apply() {
		return is_apply;
	}

	public void setIs_apply(String is_apply) {
		this.is_apply = is_apply;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Map<String, Object> getSearch() {
		return search;
	}

	public void setSearch(Map<String, Object> search) {
		this.search = search;
	}

	public Teacher() {
		super();
	}

	public Teacher(Long id) {
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
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Integer getEvaluation_score() {
		return evaluation_score;
	}
	public void setEvaluation_score(Integer evaluation_score) {
		this.evaluation_score = evaluation_score;
	}
	
 	public Integer getCourse_num() {
		return course_num;
	}
	public void setCourse_num(Integer course_num) {
		this.course_num = course_num;
	}
	
	public Integer getProject_score() {
		return project_score;
	}
	public void setProject_score(Integer project_score) {
		this.project_score = project_score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getHead_pic_url() {
		return head_pic_url;
	}
	public void setHead_pic_url(String head_pic_url) {
		this.head_pic_url = head_pic_url;
	}
	
	public String getTeacher_certification() {
		return teacher_certification;
	}
	public void setTeacher_certification(String teacher_certification) {
		this.teacher_certification = teacher_certification;
	}
	
	public String getAcademic_certificate() {
		return academic_certificate;
	}
	public void setAcademic_certificate(String academic_certificate) {
		this.academic_certificate = academic_certificate;
	}
	
	public String getOther_certificate() {
		return other_certificate;
	}
	public void setOther_certificate(String other_certificate) {
		this.other_certificate = other_certificate;
	}
	
	public String getCheck_status() {
		return check_status;
	}
	public void setCheck_status(String check_status) {
		this.check_status = check_status;
	}
	
	public Date getApply_date() {
		return apply_date;
	}
	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}
	
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	
	public String getCheck_idea() {
		return check_idea;
	}
	public void setCheck_idea(String check_idea) {
		this.check_idea = check_idea;
	}
}
