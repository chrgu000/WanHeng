package com.cgwas.schedule.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.schedule.dao.api.IScheduleDao;
import com.cgwas.schedule.entity.Schedule;
import com.cgwas.schedule.entity.ScheduleVo;
import com.cgwas.schedule.service.api.IScheduleService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class ScheduleService implements IScheduleService {
	private IScheduleDao scheduleDao;

	public Serializable save(Schedule schedule){
		return scheduleDao.save(schedule);
	}

	public void delete(Schedule schedule){
		scheduleDao.delete(schedule);
	}
	
	public void deleteByExample(Schedule schedule){
		scheduleDao.deleteByExample(schedule);
	}

	public void update(Schedule schedule){
		scheduleDao.update(schedule);
	}
	
	public void updateIgnoreNull(Schedule schedule){
		scheduleDao.updateIgnoreNull(schedule);
	}
		
	public void updateByExample(Schedule schedule){
		scheduleDao.update("updateByExampleSchedule", schedule);
	}

	public ScheduleVo load(Schedule schedule){
		return (ScheduleVo)scheduleDao.reload(schedule);
	}
	
	public ScheduleVo selectForObject(Schedule schedule){
		return (ScheduleVo)scheduleDao.selectForObject("selectSchedule",schedule);
	}
	
	@SuppressWarnings("unchecked")
	public List<ScheduleVo> selectForList(Schedule schedule){
		return (List<ScheduleVo>)scheduleDao.selectForList("selectSchedule",schedule);
	}
	
	public Page page(Page page, Schedule schedule) {
		return scheduleDao.page(page, schedule);
	}

	@Autowired
	public void setIScheduleDao(
			@Qualifier("scheduleDao") IScheduleDao  scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

}
