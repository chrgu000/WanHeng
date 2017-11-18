package com.cgwas.freezeRecord.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.freezeRecord.dao.api.IFreezeRecordDao;
import com.cgwas.freezeRecord.entity.FreezeRecord;



@Service
public class FreezeRecordDao extends AbstractDao implements IFreezeRecordDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, FreezeRecord freezeRecord) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectFreezeRecordPage", "selectFreezeRecordCount");
		return pageQuery.select(freezeRecord);
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