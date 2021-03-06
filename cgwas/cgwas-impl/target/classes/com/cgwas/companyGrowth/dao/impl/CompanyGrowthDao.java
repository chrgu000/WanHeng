package com.cgwas.companyGrowth.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companyGrowth.dao.api.ICompanyGrowthDao;
import com.cgwas.companyGrowth.entity.CompanyGrowth;

@Service
public class CompanyGrowthDao extends AbstractDao implements ICompanyGrowthDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, CompanyGrowth companyGrowth) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectCompanyGrowthPage", "selectCompanyGrowthCount");
		return pageQuery.select(companyGrowth);
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