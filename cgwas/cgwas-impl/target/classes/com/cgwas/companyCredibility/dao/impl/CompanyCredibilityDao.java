package com.cgwas.companyCredibility.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companyCredibility.dao.api.ICompanyCredibilityDao;
import com.cgwas.companyCredibility.entity.CompanyCredibility;

@Service
public class CompanyCredibilityDao extends AbstractDao implements
		ICompanyCredibilityDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, CompanyCredibility companyCredibility) {

		IPageQuery pageQuery = pageQueryFactory
				.getPageQuery(page, "selectCompanyCredibilityPage",
						"selectCompanyCredibilityCount");
		return pageQuery.select(companyCredibility);
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