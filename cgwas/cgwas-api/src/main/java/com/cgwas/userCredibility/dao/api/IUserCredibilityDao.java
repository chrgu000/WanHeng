package com.cgwas.userCredibility.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userCredibility.entity.UserCredibility;

public interface IUserCredibilityDao extends IDaoSupport {
	Page page(Page page, UserCredibility userCredibility);
}