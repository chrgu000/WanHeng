package com.cgwas.projectSearch.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.projectSearch.entity.ProjectSearch;
/**
*  Author yangjun
*/
public interface IProjectSearchDao extends IDaoSupport {
	Page page(Page page, ProjectSearch projectSearch);
}