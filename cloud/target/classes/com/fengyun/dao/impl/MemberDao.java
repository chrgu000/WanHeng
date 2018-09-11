package com.fengyun.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.fengyun.dao.IMemberDao;
import com.fengyun.entity.Member;

/**
*  Author yangjun
*/
@Service
public class MemberDao extends AbstractDao implements IMemberDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Member member) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectMemberPage", "selectMemberCount");
		return pageQuery.select(member);
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

	@Override
	public Page page1(Page page, Member member) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectGroupMessagePage", "selectGroupMessageCount");
		return pageQuery.select(member);
	}

}