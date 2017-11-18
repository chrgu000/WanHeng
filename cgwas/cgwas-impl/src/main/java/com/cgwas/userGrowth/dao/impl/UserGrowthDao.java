package com.cgwas.userGrowth.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.userGrowth.dao.api.IUserGrowthDao;
import com.cgwas.userGrowth.entity.UserGrowth;
@Service
public class UserGrowthDao extends AbstractDao implements IUserGrowthDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, UserGrowth userGrowth) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectUserGrowthPage", "selectUserGrowthCount");
		return pageQuery.select(userGrowth);
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