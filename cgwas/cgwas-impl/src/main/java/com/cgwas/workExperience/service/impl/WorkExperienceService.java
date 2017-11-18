package com.cgwas.workExperience.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.workExperience.dao.api.IWorkExperienceDao;
import com.cgwas.workExperience.entity.WorkExperience;
import com.cgwas.workExperience.entity.WorkExperienceVo;
import com.cgwas.workExperience.service.api.IWorkExperienceService;

@Service
public class WorkExperienceService implements IWorkExperienceService {
	private IWorkExperienceDao workExperienceDao;

	public Serializable save(WorkExperience workExperience) {
		return workExperienceDao.save(workExperience);
	}

	public void delete(WorkExperience workExperience) {
		workExperienceDao.delete(workExperience);
	}

	public void deleteByExample(WorkExperience workExperience) {
		workExperienceDao.deleteByExample(workExperience);
	}

	public void update(WorkExperience workExperience) {
		workExperienceDao.update(workExperience);
	}

	public void updateIgnoreNull(WorkExperience workExperience) {
		workExperienceDao.updateIgnoreNull(workExperience);
	}

	public void updateByExample(WorkExperience workExperience) {
		workExperienceDao.update("updateByExampleWorkExperience",
				workExperience);
	}

	public WorkExperienceVo load(WorkExperience workExperience) {
		return (WorkExperienceVo) workExperienceDao.reload(workExperience);
	}

	public WorkExperienceVo selectForObject(WorkExperience workExperience) {
		return (WorkExperienceVo) workExperienceDao.selectForObject(
				"selectWorkExperience", workExperience);
	}

	public List<WorkExperienceVo> selectForList(WorkExperience workExperience) {
		return (List<WorkExperienceVo>) workExperienceDao.selectForList(
				"selectWorkExperience", workExperience);
	}

	public Page page(Page page, WorkExperience workExperience) {
		return workExperienceDao.page(page, workExperience);
	}

	@Autowired
	public void setIWorkExperienceDao(
			@Qualifier("workExperienceDao") IWorkExperienceDao workExperienceDao) {
		this.workExperienceDao = workExperienceDao;
	}

	public List<WorkExperience> getWorkExperienceByUserId(Long userId) {
		return (List<WorkExperience>) workExperienceDao.selectForList(
				"getWorkExperienceByUserId", userId);
	}

	@Override
	public Serializable updateWorkExperienceByUserId(
			WorkExperience workExperience) {
		return workExperienceDao.update("updateWorkExperienceByUserId",
				workExperience);
	}

}
