package com.cgwas.arbitrateUserInfo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.arbitrateUserInfo.dao.api.IArbitrateUserInfoDao;
import com.cgwas.arbitrateUserInfo.entity.ArbitrateUserInfo;
import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;

@Service
public class ArbitrateUserInfoDao extends AbstractDao implements
		IArbitrateUserInfoDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, ArbitrateUserInfo arbitrateUserInfo) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectArbitrateUserInfoPage", "selectArbitrateUserInfoCount");
		return pageQuery.select(arbitrateUserInfo);
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