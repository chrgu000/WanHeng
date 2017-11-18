package com.cgwas.arbitrateUserInfo.dao.api;

import com.cgwas.arbitrateUserInfo.entity.ArbitrateUserInfo;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;

public interface IArbitrateUserInfoDao extends IDaoSupport {
	Page page(Page page, ArbitrateUserInfo arbitrateUserInfo);
}