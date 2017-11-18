package com.cgwas.companyFilesUser.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_company_files_user <br/>
 *         描述：公司文件用户关系表 <br/>
 */
 @SuppressWarnings("serial")
 @JsonInclude(value=Include.NON_NULL)
public class CompanyFilesUserVo extends CompanyFilesUser {
	private Long parent_id;
	public CompanyFilesUserVo() {
		super();
	}

	public CompanyFilesUserVo(Long id) {
		super();
		this.id = id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}


}
