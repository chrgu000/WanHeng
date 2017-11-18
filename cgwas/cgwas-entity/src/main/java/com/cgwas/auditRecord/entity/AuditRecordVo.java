package com.cgwas.auditRecord.entity;

/**
 * @author yangjun <br/>
 *         表名： a_audit_record <br/>
 *         描述：审核记录表 <br/>
 */
 @SuppressWarnings("serial")
public class AuditRecordVo extends AuditRecord {

	public AuditRecordVo() {
		super();
	}

	public AuditRecordVo(Long id) {
		super();
		this.id = id;
	}


}
