package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.CourseTradeSkill;
/**
*  Author yangjun
*/
public interface ICourseTradeSkillDao extends IDaoSupport {
	Page page(Page page, CourseTradeSkill courseTradeSkill);
}