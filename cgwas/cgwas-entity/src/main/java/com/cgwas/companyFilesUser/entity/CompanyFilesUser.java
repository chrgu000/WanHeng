package com.cgwas.companyFilesUser.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_company_files_user <br/>
 *         描述：公司文件用户关系表 <br/>
 */
@SuppressWarnings("serial")
public class CompanyFilesUser implements Serializable {
	protected Long id;// 主键
	protected Long company_files_id;// 公司文件id
	protected Long user_id;// 用户id

	public CompanyFilesUser() {
		super();
	}

	public CompanyFilesUser(Long id) {
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
	
	public Long getCompany_files_id() {
		return company_files_id;
	}
	public void setCompany_files_id(Long company_files_id) {
		this.company_files_id = company_files_id;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
