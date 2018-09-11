package com.fengyun.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： y_learner_course <br/>
 *         描述：y_learner_course <br/>
 */
@SuppressWarnings("serial")
public class LearnerCourse implements Serializable {
	protected Long id;// 主键
	protected Long course_id;// 课程id
	protected String type;// 课程所属类型(1为已报名课程,2为邀约课程,3为收藏课程)
	protected Date create_date;// 创建日期
	protected String delflag;// 删除标记(Y为已删除,N为未删除)
	protected String is_free;// 是否免费(Y为免费,N为付费)
	protected String is_buy;// 付费课程是否已购买(Y为购买,N为为购买)
	protected Integer learn_degree;// 学习进度
	protected Long user_id;// 用户id

	public LearnerCourse() {
		super();
	}

	public LearnerCourse(Long id) {
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
	
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
	public String getIs_free() {
		return is_free;
	}
	public void setIs_free(String is_free) {
		this.is_free = is_free;
	}
	
	public String getIs_buy() {
		return is_buy;
	}
	public void setIs_buy(String is_buy) {
		this.is_buy = is_buy;
	}
	
	public Integer getLearn_degree() {
		return learn_degree;
	}
	public void setLearn_degree(Integer learn_degree) {
		this.learn_degree = learn_degree;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
