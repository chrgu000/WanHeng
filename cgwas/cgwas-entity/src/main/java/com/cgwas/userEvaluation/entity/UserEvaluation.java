package com.cgwas.userEvaluation.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_user_evaluation <br/>
 *         描述：制作者评价表 <br/>
 */
@SuppressWarnings("serial")
public class UserEvaluation implements Serializable {
	protected Long id;// 主键
	protected String content;// 评价内容
	protected String reply;// 回复
	protected String evaluate_type;// evaluate_type
	protected Integer degree_id;// 难易度
	protected Long comment_id;// 评价者id
	protected Long user_id;// 制作者id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_time;// create_time
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date reply_time;// reply_time
	protected String status;

	protected Long production_speed;

	protected Long production_quality;

	protected String tag;
	protected Long task_id; // 任务id
	
	protected Long task_type; // 任务类型

	public UserEvaluation() {
		super();
	}

	public UserEvaluation(Long id) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getEvaluate_type() {
		return evaluate_type;
	}

	public void setEvaluate_type(String evaluate_type) {
		this.evaluate_type = evaluate_type;
	}

	public Integer getDegree_id() {
		return degree_id;
	}

	public void setDegree_id(Integer degree_id) {
		this.degree_id = degree_id;
	}

	public Long getComment_id() {
		return comment_id;
	}

	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getReply_time() {
		return reply_time;
	}

	public void setReply_time(Date reply_time) {
		this.reply_time = reply_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getProduction_speed() {
		return production_speed;
	}

	public void setProduction_speed(Long production_speed) {
		this.production_speed = production_speed;
	}

	public Long getProduction_quality() {
		return production_quality;
	}

	public void setProduction_quality(Long production_quality) {
		this.production_quality = production_quality;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public Long getTask_type() {
		return task_type;
	}

	public void setTask_type(Long task_type) {
		this.task_type = task_type;
	}

	
}
