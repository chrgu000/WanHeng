package com.cgwas.gRolePrivilege.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.gRolePrivilege.entity.GRolePrivilege;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IGRolePrivilegeDao extends IDaoSupport {
	Page page(Page page, GRolePrivilege gRolePrivilege);
}