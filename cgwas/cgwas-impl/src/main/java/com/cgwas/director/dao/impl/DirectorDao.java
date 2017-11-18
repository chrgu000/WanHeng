package com.cgwas.director.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.director.dao.api.IDirectorDao;
import com.cgwas.director.entity.Director;
/**
*  Author yangjun
*/
@Service
public class DirectorDao extends AbstractDao implements IDirectorDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Director director) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectDirectorPage", "selectDirectorCount");
		return pageQuery.select(director);
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