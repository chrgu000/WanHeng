package com.cgwas.menuRole.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.menuRole.entity.MenuRole;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IMenuRoleDao extends IDaoSupport {
	Page page(Page page, MenuRole menuRole);
}