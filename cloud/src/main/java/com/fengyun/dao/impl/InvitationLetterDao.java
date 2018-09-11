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
import com.fengyun.dao.IInvitationLetterDao;
import com.fengyun.entity.InvitationLetter;
/**
*  Author yangjun
*/
@Service
public class InvitationLetterDao extends AbstractDao implements IInvitationLetterDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, InvitationLetter invitationLetter) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectInvitationLetterPage", "selectInvitationLetterCount");
		return pageQuery.select(invitationLetter);
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