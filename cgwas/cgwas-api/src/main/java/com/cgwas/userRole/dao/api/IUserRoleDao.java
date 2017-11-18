package com.cgwas.userRole.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userRole.entity.UserRole;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IUserRoleDao extends IDaoSupport {
	Page page(Page page, UserRole userRole);
}