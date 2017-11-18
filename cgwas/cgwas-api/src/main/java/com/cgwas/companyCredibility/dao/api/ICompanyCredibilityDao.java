package com.cgwas.companyCredibility.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyCredibility.entity.CompanyCredibility;

public interface ICompanyCredibilityDao extends IDaoSupport {
	Page page(Page page, CompanyCredibility companyCredibility);
}