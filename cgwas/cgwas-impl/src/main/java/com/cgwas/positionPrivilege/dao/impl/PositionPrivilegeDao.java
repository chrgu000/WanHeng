package com.cgwas.positionPrivilege.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.positionPrivilege.dao.api.IPositionPrivilegeDao;
import com.cgwas.positionPrivilege.entity.PositionPrivilege;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class PositionPrivilegeDao extends AbstractDao implements IPositionPrivilegeDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, PositionPrivilege positionPrivilege) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectPositionPrivilegePage", "selectPositionPrivilegeCount");
		return pageQuery.select(positionPrivilege);
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