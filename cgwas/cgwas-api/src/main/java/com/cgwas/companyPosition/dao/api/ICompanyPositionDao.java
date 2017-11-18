package com.cgwas.companyPosition.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyPosition.entity.CompanyPosition;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanyPositionDao extends IDaoSupport {
	Page page(Page page, CompanyPosition companyPosition);
}