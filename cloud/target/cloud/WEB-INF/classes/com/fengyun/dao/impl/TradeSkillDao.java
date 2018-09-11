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
import com.fengyun.dao.ITradeSkillDao;
import com.fengyun.entity.TradeSkill;
/**
*  Author yangjun
*/
@Service
public class TradeSkillDao extends AbstractDao implements ITradeSkillDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, TradeSkill tradeSkill) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectTradeSkillPage", "selectTradeSkillCount");
		return pageQuery.select(tradeSkill);
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