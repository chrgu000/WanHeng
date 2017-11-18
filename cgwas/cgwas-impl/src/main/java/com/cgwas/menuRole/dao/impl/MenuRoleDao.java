package com.cgwas.menuRole.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.menuRole.dao.api.IMenuRoleDao;
import com.cgwas.menuRole.entity.MenuRole;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class MenuRoleDao extends AbstractDao implements IMenuRoleDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, MenuRole menuRole) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectMenuRolePage", "selectMenuRoleCount");
		return pageQuery.select(menuRole);
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