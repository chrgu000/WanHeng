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
import com.fengyun.dao.ICourseDao;
import com.fengyun.entity.Course;
/**
*  Author yangjun
*/
@Service
public class CourseDao extends AbstractDao implements ICourseDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Course course) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"com.fengyun.dao.ICourseDao.selectCoursePage", "com.fengyun.dao.ICourseDao.selectCourseCount");
		return pageQuery.select(course);
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

	@Override
	public Page page1(Page page, Course course) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCoursePage1", "selectCourseCount1");
		return pageQuery.select(course);
	}

	@Override
	public Page page2(Page page, Course course) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCoursePage2", "selectCourseCount2");
		return pageQuery.select(course);
	}

	@Override
	public Page page3(Page page, Course course) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectCoursePage3", "selectCourseCount3");
		return pageQuery.select(course);
	}

	@Override
	public Page page4(Page page, Course course) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"com.fengyun.dao.ICourseDao.selectCoursePage4", "com.fengyun.dao.ICourseDao.selectCourseCount4");
		return pageQuery.select(course);
	}

	@Override
	public Page page5(Page page, Course course) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"com.fengyun.dao.ICourseDao.selectCoursePage5", "com.fengyun.dao.ICourseDao.selectCourseCount5");
		return pageQuery.select(course);
	}

}