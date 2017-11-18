package com.cgwas.groupUser.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.groupUser.dao.api.IGroupUserDao;
import com.cgwas.groupUser.entity.GroupUser;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class GroupUserDao extends AbstractDao implements IGroupUserDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, GroupUser groupUser) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectGroupUserPage", "selectGroupUserCount");
		return pageQuery.select(groupUser);
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