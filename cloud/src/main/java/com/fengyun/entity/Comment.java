package com.fengyun.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

/**
 * @author yangjun <br/>s
 *         表名： y_comment <br/>
 *         描述：y_comment <br/>
 */
@SuppressWarnings("serial")
public class Comment implements Serializable {
	protected Long id;// 主键
	protected String content;// 评论内容
	protected Long course_id;// 课程id
	protected Date create_date;// 评论日期
	protected Long learner_user_id;// 学员用户id
	protected Integer star_level;// 星级数量
	protected Integer learn_degree;// 学习进度
	protected UserInfo learner;
	protected Long user_id;
	protected Boolean own;
	
	public Boolean getOwn() {
		return own;
	}

	public void setOwn(Boolean own) {
		this.own = own;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public UserInfo getLearner() {
		return learner;
	}

	public void setLearner(UserInfo learner) {
		this.learner = learner;
	}

	public Comment() {
		super();
	}

	public Comment(Long id) {
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
	
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public Long getLearner_user_id() {
		return learner_user_id;
	}
	public void setLearner_user_id(Long learner_user_id) {
		this.learner_user_id = learner_user_id;
	}
	
	public Integer getStar_level() {
		return star_level;
	}
	public void setStar_level(Integer star_level) {
		this.star_level = star_level;
	}
	
	public Integer getLearn_degree() {
		return learn_degree;
	}
	public void setLearn_degree(Integer learn_degree) {
		this.learn_degree = learn_degree;
	}
}
