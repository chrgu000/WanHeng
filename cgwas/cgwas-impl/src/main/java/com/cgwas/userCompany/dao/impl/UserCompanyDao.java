package com.cgwas.userCompany.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.userCompany.dao.api.IUserCompanyDao;
import com.cgwas.userCompany.entity.UserCompany;

@Service
public class UserCompanyDao extends AbstractDao implements IUserCompanyDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, UserCompany userCompany) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectUserCompanyPage", "selectUserCompanyCount");
		return pageQuery.select(userCompany);
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