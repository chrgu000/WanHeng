package com.cgwas.message.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 消息实体类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class Message implements Serializable {
	protected Long id;// 主键
	protected String title;//标题
	protected String message_type;// message_type
	protected String content;// 审核状态
	protected String manipulate_status;//操作状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date send_time;// 任务提交时间
	protected Long for_id;

	public Message() {
		super();
	}

	public Message(Long id) {
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

	public Long getFor_id() {
		return for_id;
	}

	public void setFor_id(Long for_id) {
		this.for_id = for_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getManipulate_status() {
		return manipulate_status;
	}

	public void setManipulate_status(String manipulate_status) {
		this.manipulate_status = manipulate_status;
	}

}
