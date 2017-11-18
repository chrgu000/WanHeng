package com.cgwas.projectType.dao.api;

import java.util.Map;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.projectType.entity.ProjectType;
/**
*  Author yangjun
*/
public interface IProjectTypeDao extends IDaoSupport {
	Page page(Page page, ProjectType projectType);

	Long getTypeId(Map<String, Object> map);

}