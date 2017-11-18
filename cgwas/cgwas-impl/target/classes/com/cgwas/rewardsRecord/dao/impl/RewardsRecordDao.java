package com.cgwas.rewardsRecord.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.rewardsRecord.dao.api.IRewardsRecordDao;
import com.cgwas.rewardsRecord.entity.RewardsRecord;

@Service
public class RewardsRecordDao extends AbstractDao implements IRewardsRecordDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, RewardsRecord rewardsRecord) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectRewardsRecordPage", "selectRewardsRecordCount");
		return pageQuery.select(rewardsRecord);
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