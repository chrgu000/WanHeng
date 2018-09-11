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
import com.fengyun.dao.ICommentDao;
import com.fengyun.entity.Comment;
/**
*  Author yangjun
*/
@Service
public class CommentDao extends AbstractDao implements ICommentDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Comment comment) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommentPage", "selectCommentCount");
		return pageQuery.select(comment);
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
	public Page page1(Page page, Comment comment) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCommentPage1", "selectCommentCount1");
		return pageQuery.select(comment);
	}

}