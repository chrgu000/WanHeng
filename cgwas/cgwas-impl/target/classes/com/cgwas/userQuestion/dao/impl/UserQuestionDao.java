package com.cgwas.userQuestion.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.userQuestion.dao.api.IUserQuestionDao;
import com.cgwas.userQuestion.entity.UserQuestion;

@Service
public class UserQuestionDao extends AbstractDao implements IUserQuestionDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, UserQuestion userQuestion) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectUserQuestionPage", "selectUserQuestionCount");
		return pageQuery.select(userQuestion);
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