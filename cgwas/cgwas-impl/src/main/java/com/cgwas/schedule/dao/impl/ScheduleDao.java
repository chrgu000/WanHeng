package com.cgwas.schedule.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.schedule.dao.api.IScheduleDao;
import com.cgwas.schedule.entity.Schedule;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class ScheduleDao extends AbstractDao implements IScheduleDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Schedule schedule) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectSchedulePage", "selectScheduleCount");
		return pageQuery.select(schedule);
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