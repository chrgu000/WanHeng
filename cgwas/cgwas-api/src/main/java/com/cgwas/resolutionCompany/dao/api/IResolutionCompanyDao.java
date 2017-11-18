package com.cgwas.resolutionCompany.dao.api;

import java.util.Map;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.resolutionCompany.entity.ResolutionCompany;
/**
*  Author yangjun
*/
public interface IResolutionCompanyDao extends IDaoSupport {
	Page page(Page page, ResolutionCompany resolutionCompany);

	Long getResolutionId(Map<String, Object> map);
}