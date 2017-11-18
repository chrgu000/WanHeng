package com.cgwas.userAuthInfo.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.userAuthInfo.dao.api.IUserAuthInfoDao;
import com.cgwas.userAuthInfo.entity.UserAuthInfo;

@Service
public class UserAuthInfoDao extends AbstractDao implements IUserAuthInfoDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, UserAuthInfo userAuthInfo) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectUserAuthInfoPage", "selectUserAuthInfoCount");
		return pageQuery.select(userAuthInfo);
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