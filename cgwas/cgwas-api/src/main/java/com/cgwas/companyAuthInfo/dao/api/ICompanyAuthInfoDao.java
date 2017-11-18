package com.cgwas.companyAuthInfo.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfo;

public interface ICompanyAuthInfoDao extends IDaoSupport {
	Page page(Page page, CompanyAuthInfo companyAuthInfo);
}