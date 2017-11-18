package com.cgwas.companySoftware.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companySoftware.entity.CompanySoftware;
/**
*  Author yangjun
*/
public interface ICompanySoftwareDao extends IDaoSupport {
	Page page(Page page, CompanySoftware companySoftware);
}