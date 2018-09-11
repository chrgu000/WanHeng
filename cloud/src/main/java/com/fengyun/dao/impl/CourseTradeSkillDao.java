package com.fengyun.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.fengyun.dao.ICourseTradeSkillDao;
import com.fengyun.entity.CourseTradeSkill;
/**
*  Author yangjun
*/
@Service
public class CourseTradeSkillDao extends AbstractDao implements ICourseTradeSkillDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, CourseTradeSkill courseTradeSkill) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCourseTradeSkillPage", "selectCourseTradeSkillCount");
		return pageQuery.select(courseTradeSkill);
	}

	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	
	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}

}