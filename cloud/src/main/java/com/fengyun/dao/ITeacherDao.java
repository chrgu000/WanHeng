package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Teacher;
/**
*  Author yangjun
*/
public interface ITeacherDao extends IDaoSupport {
	Page page(Page page, Teacher teacher);
}