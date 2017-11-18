package com.cgwas.userGroup.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userGroup.entity.UserGroup;

public interface IUserGroupDao extends IDaoSupport {
	Page page(Page page, UserGroup userGroup);
}