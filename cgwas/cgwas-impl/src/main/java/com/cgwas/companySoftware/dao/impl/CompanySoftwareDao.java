package com.cgwas.companySoftware.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companySoftware.dao.api.ICompanySoftwareDao;
import com.cgwas.companySoftware.entity.CompanySoftware;
/**
*  Author yangjun
*/
@Service
public class CompanySoftwareDao extends AbstractDao implements ICompanySoftwareDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CompanySoftware companySoftware) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCompanySoftwarePage", "selectCompanySoftwareCount");
		return pageQuery.select(companySoftware);
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