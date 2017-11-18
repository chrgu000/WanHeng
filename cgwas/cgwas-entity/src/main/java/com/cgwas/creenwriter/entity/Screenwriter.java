package com.cgwas.creenwriter.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_screenwriter <br/>
 *         描述：p_screenwriter <br/>
 */
@SuppressWarnings("serial")
public class Screenwriter implements Serializable {
	protected Long id;// id
	protected String screenwriter_name;// screenwriter_name
	protected Long user_id;// user_id

	public Screenwriter() {
		super();
	}

	public Screenwriter(Long id) {
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
	
	public String getScreenwriter_name() {
		return screenwriter_name;
	}
	public void setScreenwriter_name(String screenwriter_name) {
		this.screenwriter_name = screenwriter_name;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
