package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Course;
/**
*  Author yangjun
*/
public interface ICourseDao extends IDaoSupport {
	Page page(Page page, Course course);

	Page page1(Page page, Course course);

	Page page2(Page page, Course course);

	Page page3(Page page, Course course);

	Page page4(Page page, Course course);
}