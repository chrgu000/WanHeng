package com.cgwas.projectStatus.dao.impl;

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
import com.cgwas.projectStatus.dao.api.IProjectStatusDao;
import com.cgwas.projectStatus.entity.ProjectStatus;
/**
*  Author yangjun
*/
@Service
public class ProjectStatusDao extends AbstractDao implements IProjectStatusDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, ProjectStatus projectStatus) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProjectStatusPage", "selectProjectStatusCount");
		return pageQuery.select(projectStatus);
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
	public Long getStatusId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("getStatusId", map);
	}
}