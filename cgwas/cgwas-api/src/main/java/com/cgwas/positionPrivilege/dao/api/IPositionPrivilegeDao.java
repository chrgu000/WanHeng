package com.cgwas.positionPrivilege.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.positionPrivilege.entity.PositionPrivilege;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IPositionPrivilegeDao extends IDaoSupport {
	Page page(Page page, PositionPrivilege positionPrivilege);
}