package com.cgwas.projectStatus.dao.api;

import java.util.Map;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.projectStatus.entity.ProjectStatus;
/**
*  Author yangjun
*/
public interface IProjectStatusDao extends IDaoSupport {
	Page page(Page page, ProjectStatus projectStatus);

	Long getStatusId(Map<String, Object> map);

	 
}