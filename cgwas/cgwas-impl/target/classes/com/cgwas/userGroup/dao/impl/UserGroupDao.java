package com.cgwas.userGroup.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.userGroup.dao.api.IUserGroupDao;
import com.cgwas.userGroup.entity.UserGroup;

@Service
public class UserGroupDao extends AbstractDao implements IUserGroupDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, UserGroup userGroup) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectUserGroupPage", "selectUserGroupCount");
		return pageQuery.select(userGroup);
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