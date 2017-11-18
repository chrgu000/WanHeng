package com.cgwas.screenwriter.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.creenwriter.entity.Screenwriter;
import com.cgwas.screenwriter.dao.api.IScreenwriterDao;
/**
*  Author yangjun
*/
@Service
public class ScreenwriterDao extends AbstractDao implements IScreenwriterDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Screenwriter screenwriter) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectScreenwriterPage", "selectScreenwriterCount");
		return pageQuery.select(screenwriter);
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