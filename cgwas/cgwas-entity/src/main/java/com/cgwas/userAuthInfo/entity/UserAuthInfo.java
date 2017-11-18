package com.cgwas.userAuthInfo.entity;


import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_user_auth_info <br/>
 *         描述：认证信息表 <br/>
 */
@SuppressWarnings("serial")
public class UserAuthInfo implements Serializable {
	protected Long id;// 主键
	protected String idcard;// 身份证号
	protected String idcard_pic_path;// 身份证照路径
	protected String status;// 认证状态
	protected Long user_id;// user_id
	@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date create_time; // 创建时间
	
	protected String idcard_pic_path_back;// 身份证照背面路径
	public UserAuthInfo() {
		super();
	}

	public UserAuthInfo(Long id) {
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
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	public String getIdcard_pic_path() {
		return idcard_pic_path;
	}
	public void setIdcard_pic_path(String idcard_pic_path) {
		this.idcard_pic_path = idcard_pic_path;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getIdcard_pic_path_back() {
		return idcard_pic_path_back;
	}

	public void setIdcard_pic_path_back(String idcard_pic_path_back) {
		this.idcard_pic_path_back = idcard_pic_path_back;
	}
	
}
