package com.cgwas.messageDetail.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.messageDetail.dao.api.IMessageDetailDao;
import com.cgwas.messageDetail.entity.MessageDetail;

@Service
public class MessageDetailDao extends AbstractDao implements IMessageDetailDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, MessageDetail messageDetail) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectMessageDetailPage", "selectMessageDetailCount");
		return pageQuery.select(messageDetail);
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