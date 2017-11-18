package com.cgwas.arbitrateUserInfo.entity;



import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_arbitrate_user_info <br/>
 *         描述：仲裁用户信息表 <br/>
 */
@SuppressWarnings("serial")
public class ArbitrateUserInfo implements Serializable {
	protected Long id;// 主键
	protected String arbitrate_content;// 仲裁内容
	protected String user_type;// 用户类型(发起方，被告方)
	protected char is_pass;// 是否通过
	protected Long arbitrate_id;// 仲裁id
	protected Long user_id;// 用户id
	protected Long company_id;// 公司id

	public ArbitrateUserInfo() {
		super();
	}

	public ArbitrateUserInfo(Long id) {
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
	
	public String getArbitrate_content() {
		return arbitrate_content;
	}
	public void setArbitrate_content(String arbitrate_content) {
		this.arbitrate_content = arbitrate_content;
	}
	
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	public char getIs_pass() {
		return is_pass;
	}
	public void setIs_pass(char is_pass) {
		this.is_pass = is_pass;
	}
	
	public Long getArbitrate_id() {
		return arbitrate_id;
	}
	public void setArbitrate_id(Long arbitrate_id) {
		this.arbitrate_id = arbitrate_id;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
