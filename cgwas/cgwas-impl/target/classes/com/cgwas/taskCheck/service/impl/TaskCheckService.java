package com.cgwas.taskCheck.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.taskCheck.dao.api.ITaskCheckDao;
import com.cgwas.taskCheck.entity.TaskCheck;
import com.cgwas.taskCheck.entity.TaskCheckVo;
import com.cgwas.taskCheck.service.api.ITaskCheckService;

@Service
public class TaskCheckService implements ITaskCheckService {
	private ITaskCheckDao taskCheckDao;

	public Serializable save(TaskCheck taskCheck) {
		return taskCheckDao.save(taskCheck);
	}

	public void delete(TaskCheck taskCheck) {
		taskCheckDao.delete(taskCheck);
	}

	public void deleteByExample(TaskCheck taskCheck) {
		taskCheckDao.deleteByExample(taskCheck);
	}

	public void update(TaskCheck taskCheck) {
		taskCheckDao.update(taskCheck);
	}

	public void updateIgnoreNull(TaskCheck taskCheck) {
		taskCheckDao.updateIgnoreNull(taskCheck);
	}

	public void updateByExample(TaskCheck taskCheck) {
		taskCheckDao.update("updateByExampleTaskCheck", taskCheck);
	}

	public TaskCheckVo load(TaskCheck taskCheck) {
		return (TaskCheckVo) taskCheckDao.reload(taskCheck);
	}

	public TaskCheckVo selectForObject(TaskCheck taskCheck) {
		return (TaskCheckVo) taskCheckDao.selectForObject("selectTaskCheck",
				taskCheck);
	}

	public List<TaskCheckVo> selectForList(TaskCheck taskCheck) {
		return (List<TaskCheckVo>) taskCheckDao.selectForList(
				"selectTaskCheck", taskCheck);
	}

	public Page page(Page page, TaskCheck taskCheck) {
		return taskCheckDao.page(page, taskCheck);
	}

	@Autowired
	public void setITaskCheckDao(
			@Qualifier("taskCheckDao") ITaskCheckDao taskCheckDao) {
		this.taskCheckDao = taskCheckDao;
	}

}
