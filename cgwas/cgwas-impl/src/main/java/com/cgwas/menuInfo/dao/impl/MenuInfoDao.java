package com.cgwas.menuInfo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.menuInfo.dao.api.IMenuInfoDao;
import com.cgwas.menuInfo.entity.MenuInfo;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class MenuInfoDao extends AbstractDao implements IMenuInfoDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, MenuInfo menuInfo) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectMenuInfoPage", "selectMenuInfoCount");
		return pageQuery.select(menuInfo);
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