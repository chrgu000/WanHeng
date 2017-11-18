package com.cgwas.companyFilesUser.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companyFilesUser.dao.api.ICompanyFilesUserDao;
import com.cgwas.companyFilesUser.entity.CompanyFilesUser;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class CompanyFilesUserDao extends AbstractDao implements ICompanyFilesUserDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CompanyFilesUser companyFilesUser) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCompanyFilesUserPage", "selectCompanyFilesUserCount");
		return pageQuery.select(companyFilesUser);
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