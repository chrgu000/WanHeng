package com.fengyun.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.fengyun.dao.IChapterDao;
import com.fengyun.entity.Chapter;
/**
*  Author yangjun
*/
@Service
public class ChapterDao extends AbstractDao implements IChapterDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Chapter chapter) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectChapterPage", "selectChapterCount");
		return pageQuery.select(chapter);
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

	@Override
	public Page page1(Page page, Chapter chapter) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectChapterPage1", "selectChapterCount1");
		return pageQuery.select(chapter);
	}

}