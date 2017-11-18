package com.cgwas.auditRecord.dao.api;

import com.cgwas.auditRecord.entity.AuditRecord;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;

public interface IAuditRecordDao extends IDaoSupport {
	Page page(Page page, AuditRecord auditRecord);
}