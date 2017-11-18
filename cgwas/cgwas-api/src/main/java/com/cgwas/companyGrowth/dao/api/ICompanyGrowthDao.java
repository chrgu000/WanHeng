package com.cgwas.companyGrowth.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyGrowth.entity.CompanyGrowth;

public interface ICompanyGrowthDao extends IDaoSupport {
	Page page(Page page, CompanyGrowth companyGrowth);
}