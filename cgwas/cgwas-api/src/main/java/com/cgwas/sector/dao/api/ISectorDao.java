package com.cgwas.sector.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.sector.entity.Sector;



public interface ISectorDao extends IDaoSupport {
	Page page(Page page, Sector sector);
}