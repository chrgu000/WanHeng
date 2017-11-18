package com.cgwas.company.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.company.entity.Company;


public interface ICompanyDao extends IDaoSupport {
	Page page(Page page, Company company);
}