package com.cgwas.director.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.director.entity.Director;
/**
*  Author yangjun
*/
public interface IDirectorDao extends IDaoSupport {
	Page page(Page page, Director director);
}