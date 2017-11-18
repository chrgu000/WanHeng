package com.cgwas.principal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.principal.dao.api.IPrincipalDao;
import com.cgwas.principal.entity.Principal;
/**
*  Author yangjun
*/
@Service
public class PrincipalDao extends AbstractDao implements IPrincipalDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Principal principal) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectPrincipalPage", "selectPrincipalCount");
		return pageQuery.select(principal);
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