package com.cgwas.evaluation.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.evaluation.dao.api.IEvaluationDao;
import com.cgwas.evaluation.entity.Evaluation;

@Service
public class EvaluationDao extends AbstractDao implements IEvaluationDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, Evaluation evaluation) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectEvaluationPage", "selectEvaluationCount");
		return pageQuery.select(evaluation);
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