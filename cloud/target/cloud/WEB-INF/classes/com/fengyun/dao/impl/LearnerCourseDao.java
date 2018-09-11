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
import com.fengyun.dao.ILearnerCourseDao;
import com.fengyun.entity.LearnerCourse;
/**
*  Author yangjun
*/
@Service
public class LearnerCourseDao extends AbstractDao implements ILearnerCourseDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, LearnerCourse learnerCourse) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectLearnerCoursePage", "selectLearnerCourseCount");
		return pageQuery.select(learnerCourse);
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