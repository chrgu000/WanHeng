package com.cgwas.spaceOrder.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.spaceOrder.dao.api.ISpaceOrderDao;
import com.cgwas.spaceOrder.entity.SpaceOrder;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class SpaceOrderDao extends AbstractDao implements ISpaceOrderDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, SpaceOrder spaceOrder) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectSpaceOrderPage", "selectSpaceOrderCount");
		return pageQuery.select(spaceOrder);
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