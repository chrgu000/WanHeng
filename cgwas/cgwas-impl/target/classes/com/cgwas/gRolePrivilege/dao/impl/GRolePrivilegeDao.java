package com.cgwas.gRolePrivilege.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.gRolePrivilege.dao.api.IGRolePrivilegeDao;
import com.cgwas.gRolePrivilege.entity.GRolePrivilege;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class GRolePrivilegeDao extends AbstractDao implements IGRolePrivilegeDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, GRolePrivilege gRolePrivilege) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectGRolePrivilegePage", "selectGRolePrivilegeCount");
		return pageQuery.select(gRolePrivilege);
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