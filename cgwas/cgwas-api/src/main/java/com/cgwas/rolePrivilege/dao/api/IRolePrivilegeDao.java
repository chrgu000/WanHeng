package com.cgwas.rolePrivilege.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.rolePrivilege.entity.RolePrivilege;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IRolePrivilegeDao extends IDaoSupport {
	Page page(Page page, RolePrivilege rolePrivilege);
}