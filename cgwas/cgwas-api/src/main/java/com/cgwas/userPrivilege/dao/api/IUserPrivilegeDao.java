package com.cgwas.userPrivilege.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userPrivilege.entity.UserPrivilege;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IUserPrivilegeDao extends IDaoSupport {
	Page page(Page page, UserPrivilege userPrivilege);
}