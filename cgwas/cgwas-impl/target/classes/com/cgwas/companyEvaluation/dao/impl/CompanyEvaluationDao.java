package com.cgwas.companyEvaluation.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companyEvaluation.dao.api.ICompanyEvaluationDao;
import com.cgwas.companyEvaluation.entity.CompanyEvaluation;

@Service
public class CompanyEvaluationDao extends AbstractDao implements
		ICompanyEvaluationDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, CompanyEvaluation companyEvaluation) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectCompanyEvaluationPage", "selectCompanyEvaluationCount");
		return pageQuery.select(companyEvaluation);
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