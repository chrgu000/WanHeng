package com.cgwas.principal.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.principal.entity.Principal;
/**
*  Author yangjun
*/
public interface IPrincipalDao extends IDaoSupport {
	Page page(Page page, Principal principal);
}