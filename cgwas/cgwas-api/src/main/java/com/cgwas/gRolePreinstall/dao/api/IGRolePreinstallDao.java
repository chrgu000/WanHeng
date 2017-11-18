package com.cgwas.gRolePreinstall.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.gRolePreinstall.entity.GRolePreinstall;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IGRolePreinstallDao extends IDaoSupport {
	Page page(Page page, GRolePreinstall gRolePreinstall);
}