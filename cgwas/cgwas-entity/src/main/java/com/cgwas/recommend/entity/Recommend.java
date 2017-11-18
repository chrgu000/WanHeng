package com.cgwas.recommend.entity;

import javax.persistence.Id;
import java.io.Serializable;
/**
 * 推荐实体类
 * @author Lingwh
 *
 */
@SuppressWarnings("serial")
public class Recommend implements Serializable {
	protected Long id;// 主键
	protected String priority;// 优先级
	protected String type;// type
	protected String reason;// 推荐原因
	protected String status;// 推荐关注/取消关注
	protected String name;// 推荐者
	protected Long relation_id;// 用户id

	public Recommend() {
		super();
	}

	public Recommend(Long id) {
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(Long relation_id) {
		this.relation_id = relation_id;
	}
}
