package com.cgwas.auditRecord.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.auditRecord.dao.api.IAuditRecordDao;
import com.cgwas.auditRecord.entity.AuditRecord;
import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;

@Service
public class AuditRecordDao extends AbstractDao implements IAuditRecordDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, AuditRecord auditRecord) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectAuditRecordPage", "selectAuditRecordCount");
		return pageQuery.select(auditRecord);
	}

	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}

}