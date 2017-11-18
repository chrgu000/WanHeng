package com.cgwas.recommend.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.recommend.dao.api.IRecommendDao;
import com.cgwas.recommend.entity.Recommend;

@Service
public class RecommendDao extends AbstractDao implements IRecommendDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, Recommend recommend) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectRecommendPage", "selectRecommendCount");
		return pageQuery.select(recommend);
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