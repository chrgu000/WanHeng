package com.cgwas.userCompany.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userCompany.entity.UserCompany;


public interface IUserCompanyDao extends IDaoSupport {
	Page page(Page page, UserCompany userCompany);
}