package com.cgwas.director.entity;

import javax.persistence.Id;

import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_director <br/>
 *         描述：导演表 <br/>
 */
@SuppressWarnings("serial")
public class Director implements Serializable {
	protected Long id;// id
	protected String director_name;// director_name
	protected Long user_id;// 创建者id
	protected String head_pic_path;//头像
	
	public String getHead_pic_path() {
		return head_pic_path;
	}

	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}

	public Director() {
		super();
	}

	public Director(Long id) {
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
	
	public String getDirector_name() {
		return director_name;
	}
	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
