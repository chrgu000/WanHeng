package com.cgwas.companyFiles.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyFiles.entity.CompanyFiles;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanyFilesDao extends IDaoSupport {
	/**
	 * 获取所有信息
	 * @param page
	 * @param companyFiles
	 * @return
	 */
	Page page(Page page, CompanyFiles companyFiles);
	/**
	 * 根据公司获取相应信息
	 * @param page
	 * @param companyFiles
	 * @return
	 */
	Page pageForCompany(Page page, CompanyFiles companyFiles);
	/**
	 * 根据团队角色获取相应信息
	 * @param page
	 * @param companyFiles
	 * @return
	 */
	Page pageForGRole(Page page, CompanyFiles companyFiles);
}