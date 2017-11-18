package com.cgwas.bankInfo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.bankInfo.dao.api.IBankInfoDao;
import com.cgwas.bankInfo.entity.BankInfo;
import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;

@Service
public class BankInfoDao extends AbstractDao implements IBankInfoDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, BankInfo bankInfo) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectBankInfoPage", "selectBankInfoCount");
		return pageQuery.select(bankInfo);
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