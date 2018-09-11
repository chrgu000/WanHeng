package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.LearnerCourse;
/**
*  Author yangjun
*/
public interface ILearnerCourseDao extends IDaoSupport {
	Page page(Page page, LearnerCourse learnerCourse);
}