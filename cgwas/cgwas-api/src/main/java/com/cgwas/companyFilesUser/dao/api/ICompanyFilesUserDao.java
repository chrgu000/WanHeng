package com.cgwas.companyFilesUser.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyFilesUser.entity.CompanyFilesUser;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanyFilesUserDao extends IDaoSupport {
	Page page(Page page, CompanyFilesUser companyFilesUser);
}