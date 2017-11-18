package com.cgwas.space.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.space.dao.api.ISpaceDao;
import com.cgwas.space.entity.Space;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class SpaceDao extends AbstractDao implements ISpaceDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Space space) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectSpacePage", "selectSpaceCount");
		return pageQuery.select(space);
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