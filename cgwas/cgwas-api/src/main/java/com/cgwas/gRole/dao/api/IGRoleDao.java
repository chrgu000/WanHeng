package com.cgwas.gRole.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.gRole.entity.GRole;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IGRoleDao extends IDaoSupport {
	Page page(Page page, GRole gRole);
}