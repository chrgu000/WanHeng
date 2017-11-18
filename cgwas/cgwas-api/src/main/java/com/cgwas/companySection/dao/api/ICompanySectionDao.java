package com.cgwas.companySection.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companySection.entity.CompanySection;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanySectionDao extends IDaoSupport {
	Page page(Page page, CompanySection companySection);
}