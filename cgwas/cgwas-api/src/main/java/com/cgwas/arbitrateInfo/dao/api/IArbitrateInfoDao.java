package com.cgwas.arbitrateInfo.dao.api;

import com.cgwas.arbitrateInfo.entity.ArbitrateInfo;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;

public interface IArbitrateInfoDao extends IDaoSupport {
	Page page(Page page, ArbitrateInfo arbitrateInfo);
}