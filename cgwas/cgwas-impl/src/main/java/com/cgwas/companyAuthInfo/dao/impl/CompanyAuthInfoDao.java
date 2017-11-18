package com.cgwas.companyAuthInfo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companyAuthInfo.dao.api.ICompanyAuthInfoDao;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfo;

@Service
public class CompanyAuthInfoDao extends AbstractDao implements
		ICompanyAuthInfoDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, CompanyAuthInfo companyAuthInfo) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectCompanyAuthInfoPage", "selectCompanyAuthInfoCount");
		return pageQuery.select(companyAuthInfo);
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