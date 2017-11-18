package com.cgwas.messageDetail.entity;

import java.util.Date;

public class MessageInfo {
	protected Long id; // 信息id
	protected String message_type; // 信息类型
	protected String content; // 信息内容
	protected Date send_time; // 发送时间
	protected Long for_id; // 回复信息ID
	protected String read_state; // 阅读状态
	protected String sort; // 排序
	protected Long send_id; // 接受者ID
	protected Long user_id; // 发送者ID
	protected Date read_time; // 读取日期
	protected Long parent_id; // 回复信息ID
	protected String send_name;
	protected String user_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	public String getRead_state() {
		return read_state;
	}

	public void setRead_state(String read_state) {
		this.read_state = read_state;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Long getSend_id() {
		return send_id;
	}

	public void setSend_id(Long send_id) {
		this.send_id = send_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getRead_time() {
		return read_time;
	}

	public void setRead_time(Date read_time) {
		this.read_time = read_time;
	}

	public Long getFor_id() {
		return for_id;
	}

	public void setFor_id(Long for_id) {
		this.for_id = for_id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
