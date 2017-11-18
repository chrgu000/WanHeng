package com.cgwas.freezeRecord.entity;

/**
 * @author 模版生成 <br/>
 *         表名： u_freeze_record <br/>
 *         描述：u_freeze_record <br/>
 */
@SuppressWarnings("serial")
public class FreezeRecordVo extends FreezeRecord {
	String company_name;

	public FreezeRecordVo() {
		super();
	}

	public FreezeRecordVo(Long id) {
		super();
		this.id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

}
