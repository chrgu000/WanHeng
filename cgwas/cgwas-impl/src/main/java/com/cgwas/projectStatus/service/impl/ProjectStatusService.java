package com.cgwas.projectStatus.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.projectStatus.dao.api.IProjectStatusDao;
import com.cgwas.projectStatus.entity.ProjectStatus;
import com.cgwas.projectStatus.entity.ProjectStatusVo;
import com.cgwas.projectStatus.service.api.IProjectStatusService;
/**
*  Author yangjun
*/
@Service
public class ProjectStatusService implements IProjectStatusService {
	private IProjectStatusDao projectStatusDao;

	public Serializable save(ProjectStatus projectStatus){
		return projectStatusDao.save(projectStatus);
	}

	public void delete(ProjectStatus projectStatus){
		projectStatusDao.delete(projectStatus);
	}
	
	public void deleteByExample(ProjectStatus projectStatus){
		projectStatusDao.deleteByExample(projectStatus);
	}

	public void update(ProjectStatus projectStatus){
		projectStatusDao.update(projectStatus);
	}
	
	public void updateIgnoreNull(ProjectStatus projectStatus){
		projectStatusDao.updateIgnoreNull(projectStatus);
	}
		
	public void updateByExample(ProjectStatus projectStatus){
		projectStatusDao.update("updateByExampleProjectStatus", projectStatus);
	}

	public ProjectStatusVo load(ProjectStatus projectStatus){
		return (ProjectStatusVo)projectStatusDao.reload(projectStatus);
	}
	
	public ProjectStatusVo selectForObject(ProjectStatus projectStatus){
		return (ProjectStatusVo)projectStatusDao.selectForObject("selectProjectStatus",projectStatus);
	}
	
	public List<ProjectStatusVo> selectForList(ProjectStatus projectStatus){
		return (List<ProjectStatusVo>)projectStatusDao.selectForList("selectProjectStatus",projectStatus);
	}
	
	public Page page(Page page, ProjectStatus projectStatus) {
		return projectStatusDao.page(page, projectStatus);
	}

	@Autowired
	public void setIProjectStatusDao(
			@Qualifier("projectStatusDao") IProjectStatusDao  projectStatusDao) {
		this.projectStatusDao = projectStatusDao;
	}

}
