package com.cgwas.user.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.user.entity.User;

public interface IUserDao extends IDaoSupport {
	Page page(Page page, User user);
}