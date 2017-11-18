package com.cgwas.frameRateCompany.dao.api;

import java.util.Map;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.frameRateCompany.entity.FrameRateCompany;
/**
*  Author yangjun
*/
public interface IFrameRateCompanyDao extends IDaoSupport {
	Page page(Page page, FrameRateCompany frameRateCompany);

	Long getFrameRateId(Map<String, Object> map);
}