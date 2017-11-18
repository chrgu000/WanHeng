package com.cgwas.companyAuthInfo.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 公司认证信息实体类
 * @author Lingwh
 *
 */
@SuppressWarnings("serial")
public class CompanyAuthInfo implements Serializable {
	protected Long id;// 主键
	protected String charter_path;// 营业执照照片路径
	protected String status;// 认证状态
	protected Long company_id;// 公司等级id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date creat_time;// 创建时间

	public CompanyAuthInfo() {
		super();
	}

	public CompanyAuthInfo(Long id) {
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
	
	public String getCharter_path() {
		return charter_path;
	}
	public void setCharter_path(String charter_path) {
		this.charter_path = charter_path;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	
	public Date getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}
}
