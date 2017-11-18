package com.cgwas.userInfo.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userInfo.entity.UserInfo;

public interface IUserInfoDao extends IDaoSupport {
	Page page(Page page, UserInfo userInfo);
}