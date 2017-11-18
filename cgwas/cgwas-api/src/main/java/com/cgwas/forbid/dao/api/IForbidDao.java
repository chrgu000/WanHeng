package com.cgwas.forbid.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.forbid.entity.Forbid;



public interface IForbidDao extends IDaoSupport {
	Page page(Page page, Forbid forbid);
}