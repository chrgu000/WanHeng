package com.cgwas.walletInfo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.walletInfo.dao.api.IWalletInfoDao;
import com.cgwas.walletInfo.entity.WalletInfo;

@Service
public class WalletInfoDao extends AbstractDao implements IWalletInfoDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, WalletInfo walletInfo) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectWalletInfoPage", "selectWalletInfoCount");
		return pageQuery.select(walletInfo);
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