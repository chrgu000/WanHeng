package com.cgwas.taskCheck.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.taskCheck.dao.api.ITaskCheckDao;
import com.cgwas.taskCheck.entity.TaskCheck;


@Service
public class TaskCheckDao extends AbstractDao implements ITaskCheckDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, TaskCheck taskCheck) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectTaskCheckPage", "selectTaskCheckCount");
		return pageQuery.select(taskCheck);
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