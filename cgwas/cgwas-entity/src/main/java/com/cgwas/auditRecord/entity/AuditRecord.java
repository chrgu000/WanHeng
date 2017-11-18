package com.cgwas.auditRecord.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * @author yangjun <br/>s
 *         表名： a_audit_record <br/>
 *         描述：审核记录表 <br/>
 */
@SuppressWarnings("serial")
public class AuditRecord implements Serializable {
	protected Long id;// 主键
	protected Long checker_id;// 审核员id
	protected Date check_time;// 审核时间
	protected String comment;// 备注
	protected Double production_speed;// 制作速度
	protected Double production_quality;// 制作质量
	protected Long task_check_id;// task_check_id
    protected Long degree_id;//难度系数id
    protected Long evaluation_id;//好评id
    protected Long num;//返修次数
    
	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getDegree_id() {
		return degree_id;
	}

	public void setDegree_id(Long degree_id) {
		this.degree_id = degree_id;
	}

	public Long getEvaluation_id() {
		return evaluation_id;
	}

	public void setEvaluation_id(Long evaluation_id) {
		this.evaluation_id = evaluation_id;
	}

	public AuditRecord() {
		super();
	}

	public AuditRecord(Long id) {
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
	
	public Long getChecker_id() {
		return checker_id;
	}
	public void setChecker_id(Long checker_id) {
		this.checker_id = checker_id;
	}
	
	public Date getCheck_time() {
		return check_time;
	}
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Double getProduction_speed() {
		return production_speed;
	}
	public void setProduction_speed(Double production_speed) {
		this.production_speed = production_speed;
	}
	
	public Double getProduction_quality() {
		return production_quality;
	}
	public void setProduction_quality(Double production_quality) {
		this.production_quality = production_quality;
	}
	
	public Long getTask_check_id() {
		return task_check_id;
	}
	public void setTask_check_id(Long task_check_id) {
		this.task_check_id = task_check_id;
	}
}
