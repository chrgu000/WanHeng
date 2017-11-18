package com.cgwas.frameRateCompany.dao.impl;

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
import com.cgwas.frameRateCompany.dao.api.IFrameRateCompanyDao;
import com.cgwas.frameRateCompany.entity.FrameRateCompany;
/**
*  Author yangjun
*/
@Service
public class FrameRateCompanyDao extends AbstractDao implements IFrameRateCompanyDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, FrameRateCompany frameRateCompany) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectFrameRateCompanyPage", "selectFrameRateCompanyCount");
		return pageQuery.select(frameRateCompany);
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
	public Long getFrameRateId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("getFrameRateId",map);
	}

}