package com.cgwas.userAuthInfo.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userAuthInfo.entity.UserAuthInfo;



public interface IUserAuthInfoDao extends IDaoSupport {
	Page page(Page page, UserAuthInfo userAuthInfo);
}