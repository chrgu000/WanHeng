package com.cgwas.companyEvaluation.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_company_evaluation <br/>
 *         描述：公司评价表 <br/>
 */
@SuppressWarnings("serial")
public class CompanyEvaluation implements Serializable {
	protected Long id;// 主键
	protected String content;// 评价内容
	protected String reply;// 回复
	protected Long user_id;// 评价者id
	protected Long company_id;// 公司id
	protected String evaluate_type;// evaluate_type
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_time;// create_time
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date reply_time;// reply_time
	protected String status;// 状态
	protected Long cedibility; // 公司信用
	protected Long match_degree; // 任务难度
	protected Long task_id; // 任务id
	protected Long task_type; // 任务类型


	public CompanyEvaluation() {
		super();
	}

	public CompanyEvaluation(Long id) {
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

	public String getEvaluate_type() {
		return evaluate_type;
	}

	public void setEvaluate_type(String evaluate_type) {
		this.evaluate_type = evaluate_type;
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

	public Long getCedibility() {
		return cedibility;
	}

	public void setCedibility(Long cedibility) {
		this.cedibility = cedibility;
	}

	public Long getMatch_degree() {
		return match_degree;
	}

	public void setMatch_degree(Long match_degree) {
		this.match_degree = match_degree;
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
