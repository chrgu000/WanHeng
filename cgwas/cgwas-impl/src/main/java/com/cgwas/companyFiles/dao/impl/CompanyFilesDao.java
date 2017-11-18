package com.cgwas.companyFiles.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companyFiles.dao.api.ICompanyFilesDao;
import com.cgwas.companyFiles.entity.CompanyFiles;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class CompanyFilesDao extends AbstractDao implements ICompanyFilesDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CompanyFiles companyFiles) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectFilesPage", "selectFilesCount");
		return pageQuery.select(companyFiles);
	}
	
	@Override
	public Page pageForCompany(Page page, CompanyFiles companyFiles) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectListForCompany", "selectCountForCompany");
		return pageQuery.select(companyFiles);
	}



	@Override
	public Page pageForGRole(Page page, CompanyFiles companyFiles) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectListForGRole", "selectCountForGRole");
		return pageQuery.select(companyFiles);
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