package com.cgwas.principal.entity;

import javax.persistence.Id;

import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_principal <br/>
 *         描述：负责人表 <br/>
 */
@SuppressWarnings("serial")
public class Principal implements Serializable {
	protected Long id;// id
	protected String principal_name;// 负责人姓名
	protected Long user_id;// 用户id
	protected String head_pic_path;//头像
	
	public String getHead_pic_path() {
		return head_pic_path;
	}

	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}

	public Principal() {
		super();
	}

	public Principal(Long id) {
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
	
	public String getPrincipal_name() {
		return principal_name;
	}
	public void setPrincipal_name(String principal_name) {
		this.principal_name = principal_name;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
