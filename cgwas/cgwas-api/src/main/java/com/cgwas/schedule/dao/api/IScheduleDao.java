package com.cgwas.schedule.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.schedule.entity.Schedule;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IScheduleDao extends IDaoSupport {
	Page page(Page page, Schedule schedule);
}