package com.cgwas.companyPlugin.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyPlugin.entity.CompanyPlugin;
/**
*  Author yangjun
*/
public interface ICompanyPluginDao extends IDaoSupport {
	Page page(Page page, CompanyPlugin companyPlugin);
}