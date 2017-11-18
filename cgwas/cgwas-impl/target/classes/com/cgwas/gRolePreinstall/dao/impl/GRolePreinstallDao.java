package com.cgwas.gRolePreinstall.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.gRolePreinstall.dao.api.IGRolePreinstallDao;
import com.cgwas.gRolePreinstall.entity.GRolePreinstall;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class GRolePreinstallDao extends AbstractDao implements IGRolePreinstallDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, GRolePreinstall gRolePreinstall) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectGRolePreinstallPage", "selectGRolePreinstallCount");
		return pageQuery.select(gRolePreinstall);
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