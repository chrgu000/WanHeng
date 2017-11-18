package com.cgwas.labelTag.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.labelTag.dao.api.ILabelTagDao;
import com.cgwas.labelTag.entity.LabelTag;
/**
*  Author yangjun
*/
@Service
public class LabelTagDao extends AbstractDao implements ILabelTagDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, LabelTag labelTag) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectLabelTagPage", "selectLabelTagCount");
		return pageQuery.select(labelTag);
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