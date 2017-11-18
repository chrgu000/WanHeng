package com.cgwas.messageDetail.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 信息详情类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class MessageDetail implements Serializable {
	protected Long id;// 主键
	protected String read_state;// read_state
	protected Integer sort;// sort
	protected Long send_id;// 发件人id
	protected Long user_id;// 收件人id
	protected Long message_id;// message_id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date send_time;// 发送时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date read_time;// read_time
	protected char is_delete; // 是否删除（Y/N）
	private String read_content;//阅读内容

	public MessageDetail() {
		super();
	}

	public MessageDetail(Long id) {
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

	public String getRead_state() {
		return read_state;
	}

	public void setRead_state(String read_state) {
		this.read_state = read_state;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
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

	public Long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Long message_id) {
		this.message_id = message_id;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	public Date getRead_time() {
		return read_time;
	}

	public void setRead_time(Date read_time) {
		this.read_time = read_time;
	}

	public char getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(char is_delete) {
		this.is_delete = is_delete;
	}

	public String getRead_content() {
		return read_content;
	}

	public void setRead_content(String read_content) {
		this.read_content = read_content;
	}

}
