package com.cgwas.company.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.company.dao.api.ICompanyDao;
import com.cgwas.company.entity.Company;

@Service
public class CompanyDao extends AbstractDao implements ICompanyDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Company company) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCompanyPage", "selectCompanyCount");
		return pageQuery.select(company);
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