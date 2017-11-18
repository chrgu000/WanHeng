package com.cgwas.forbid.entity;




import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户禁止实体类
 * @author Lingwh
 *
 */
@SuppressWarnings("serial")
public class Forbid implements Serializable {
	protected Long id;// 主键
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date validity;// 禁止有效期
	protected Long type;// 用户id
	protected String reason;// 理由
	protected Long for_id;// 主键
	protected Long type_id;// 禁止类型id
	protected Long user_id;// user_id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date creat_time;// creat_time

	public Forbid() {
		super();
	}

	public Forbid(Long id) {
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
	
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Long getFor_id() {
		return for_id;
	}
	public void setFor_id(Long for_id) {
		this.for_id = for_id;
	}
	
	public Long getType_id() {
		return type_id;
	}
	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Date getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}
	
}
