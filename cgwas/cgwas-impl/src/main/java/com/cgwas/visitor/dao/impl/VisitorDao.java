package com.cgwas.visitor.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.visitor.dao.api.IVisitorDao;
import com.cgwas.visitor.entity.Visitor;
@Service
public class VisitorDao extends AbstractDao implements IVisitorDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Visitor visitor) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectVisitorPage", "selectVisitorCount");
		return pageQuery.select(visitor);
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