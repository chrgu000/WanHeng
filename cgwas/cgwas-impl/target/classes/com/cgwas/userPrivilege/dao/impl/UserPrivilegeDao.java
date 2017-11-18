package com.cgwas.userPrivilege.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.userPrivilege.dao.api.IUserPrivilegeDao;
import com.cgwas.userPrivilege.entity.UserPrivilege;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class UserPrivilegeDao extends AbstractDao implements IUserPrivilegeDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, UserPrivilege userPrivilege) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectUserPrivilegePage", "selectUserPrivilegeCount");
		return pageQuery.select(userPrivilege);
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