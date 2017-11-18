package com.cgwas.companyPlugin.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.companyPlugin.dao.api.ICompanyPluginDao;
import com.cgwas.companyPlugin.entity.CompanyPlugin;
/**
*  Author yangjun
*/
@Service
public class CompanyPluginDao extends AbstractDao implements ICompanyPluginDao {
	@Autowired
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CompanyPlugin companyPlugin) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCompanyPluginPage", "selectCompanyPluginCount");
		return pageQuery.select(companyPlugin);
	}

	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	
	

}