package com.cgwas.companySpace.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companySpace.dao.api.ICompanySpaceDao;
import com.cgwas.companySpace.entity.CompanySpace;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class CompanySpaceDao extends AbstractDao implements ICompanySpaceDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CompanySpace companySpace) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCompanySpacePage", "selectCompanySpaceCount");
		return pageQuery.select(companySpace);
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