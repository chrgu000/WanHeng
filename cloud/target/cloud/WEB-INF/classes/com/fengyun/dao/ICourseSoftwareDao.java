package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.CourseSoftware;
/**
*  Author yangjun
*/
public interface ICourseSoftwareDao extends IDaoSupport {
	Page page(Page page, CourseSoftware courseSoftware);
}