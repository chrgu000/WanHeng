package com.cgwas.educationalBackground.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.educationalBackground.dao.api.IEducationalBackgroundDao;
import com.cgwas.educationalBackground.entity.EducationalBackground;

@Service
public class EducationalBackgroundDao extends AbstractDao implements IEducationalBackgroundDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, EducationalBackground educationalBackground) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectEducationalBackgroundPage", "selectEducationalBackgroundCount");
		return pageQuery.select(educationalBackground);
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