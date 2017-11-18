package com.cgwas.companySpace.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companySpace.entity.CompanySpace;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanySpaceDao extends IDaoSupport {
	Page page(Page page, CompanySpace companySpace);
}