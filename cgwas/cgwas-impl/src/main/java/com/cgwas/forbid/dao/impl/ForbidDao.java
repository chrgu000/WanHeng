package com.cgwas.forbid.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.forbid.dao.api.IForbidDao;
import com.cgwas.forbid.entity.Forbid;

@Service
public class ForbidDao extends AbstractDao implements IForbidDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Forbid forbid) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectForbidPage", "selectForbidCount");
		return pageQuery.select(forbid);
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