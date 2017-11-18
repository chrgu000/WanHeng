package com.cgwas.userCredibility.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.userCredibility.dao.api.IUserCredibilityDao;
import com.cgwas.userCredibility.entity.UserCredibility;

@Service
public class UserCredibilityDao extends AbstractDao implements
		IUserCredibilityDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, UserCredibility userCredibility) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectUserCredibilityPage", "selectUserCredibilityCount");
		return pageQuery.select(userCredibility);
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