package com.cgwas.userEvaluation.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.userEvaluation.dao.api.IUserEvaluationDao;
import com.cgwas.userEvaluation.entity.UserEvaluation;

@Service
public class UserEvaluationDao extends AbstractDao implements
		IUserEvaluationDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, UserEvaluation userEvaluation) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectUserEvaluationPage", "selectUserEvaluationCount");
		return pageQuery.select(userEvaluation);
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