package com.cgwas.role.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.role.entity.Role;

public interface IRoleDao extends IDaoSupport {
	Page page(Page page, Role role);
}