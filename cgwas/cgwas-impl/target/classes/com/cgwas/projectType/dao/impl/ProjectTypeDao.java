package com.cgwas.projectType.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.projectType.dao.api.IProjectTypeDao;
import com.cgwas.projectType.entity.ProjectType;
/**
*  Author yangjun
*/
@Service
public class ProjectTypeDao extends AbstractDao implements IProjectTypeDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, ProjectType projectType) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProjectTypePage", "selectProjectTypeCount");
		return pageQuery.select(projectType);
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
	public Long getTypeId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("getTypeId", map);
	}

}