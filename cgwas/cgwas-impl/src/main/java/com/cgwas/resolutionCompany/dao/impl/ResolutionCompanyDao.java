package com.cgwas.resolutionCompany.dao.impl;

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
import com.cgwas.resolutionCompany.dao.api.IResolutionCompanyDao;
import com.cgwas.resolutionCompany.entity.ResolutionCompany;
/**
*  Author yangjun
*/
@Service
public class ResolutionCompanyDao extends AbstractDao implements IResolutionCompanyDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, ResolutionCompany resolutionCompany) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectResolutionCompanyPage", "selectResolutionCompanyCount");
		return pageQuery.select(resolutionCompany);
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
	public Long getResolutionId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("getResolutionId", map);
	}

}