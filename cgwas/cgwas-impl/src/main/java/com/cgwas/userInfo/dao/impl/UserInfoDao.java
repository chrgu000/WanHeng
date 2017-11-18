package com.cgwas.userInfo.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.userInfo.dao.api.IUserInfoDao;
import com.cgwas.userInfo.entity.UserInfo;

@Service
public class UserInfoDao extends AbstractDao implements IUserInfoDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, UserInfo userInfo) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectUserInfoPage", "selectUserInfoCount");
		return pageQuery.select(userInfo);
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