package com.cgwas.arbitrateInfo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.arbitrateInfo.dao.api.IArbitrateInfoDao;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfo;
import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;

@Service
public class ArbitrateInfoDao extends AbstractDao implements IArbitrateInfoDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, ArbitrateInfo arbitrateInfo) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectArbitrateInfoPage", "selectArbitrateInfoCount");
		return pageQuery.select(arbitrateInfo);
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