package com.cgwas.privilegeInfo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.privilegeInfo.dao.api.IPrivilegeInfoDao;
import com.cgwas.privilegeInfo.entity.PrivilegeInfo;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class PrivilegeInfoDao extends AbstractDao implements IPrivilegeInfoDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, PrivilegeInfo privilegeInfo) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectPrivilegeInfoPage", "selectPrivilegeInfoCount");
		return pageQuery.select(privilegeInfo);
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