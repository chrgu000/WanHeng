package com.cgwas.workExperience.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.workExperience.dao.api.IWorkExperienceDao;
import com.cgwas.workExperience.entity.WorkExperience;

@Service
public class WorkExperienceDao extends AbstractDao implements IWorkExperienceDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, WorkExperience workExperience) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectWorkExperiencePage", "selectWorkExperienceCount");
		return pageQuery.select(workExperience);
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