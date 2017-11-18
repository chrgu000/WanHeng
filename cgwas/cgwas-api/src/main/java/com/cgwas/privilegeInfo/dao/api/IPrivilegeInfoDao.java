package com.cgwas.privilegeInfo.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.privilegeInfo.entity.PrivilegeInfo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IPrivilegeInfoDao extends IDaoSupport {
	Page page(Page page, PrivilegeInfo privilegeInfo);
}