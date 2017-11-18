package com.cgwas.companySection.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companySection.dao.api.ICompanySectionDao;
import com.cgwas.companySection.entity.CompanySection;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class CompanySectionDao extends AbstractDao implements ICompanySectionDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CompanySection companySection) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCompanySectionPage", "selectCompanySectionCount");
		page=pageQuery.select(companySection);
		return page;
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