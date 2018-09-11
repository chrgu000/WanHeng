package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Software;
/**
*  Author yangjun
*/
public interface ISoftwareDao extends IDaoSupport {
	Page page(Page page, Software software);
}