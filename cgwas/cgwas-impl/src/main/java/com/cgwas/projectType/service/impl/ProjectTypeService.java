package com.cgwas.projectType.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.projectType.dao.api.IProjectTypeDao;
import com.cgwas.projectType.entity.ProjectType;
import com.cgwas.projectType.entity.ProjectTypeVo;
import com.cgwas.projectType.service.api.IProjectTypeService;
/**
*  Author yangjun
*/
@Service
public class ProjectTypeService implements IProjectTypeService {
	private IProjectTypeDao projectTypeDao;

	public Serializable save(ProjectType projectType){
		return projectTypeDao.save(projectType);
	}

	public void delete(ProjectType projectType){
		projectTypeDao.delete(projectType);
	}
	
	public void deleteByExample(ProjectType projectType){
		projectTypeDao.deleteByExample(projectType);
	}

	public void update(ProjectType projectType){
		projectTypeDao.update(projectType);
	}
	
	public void updateIgnoreNull(ProjectType projectType){
		projectTypeDao.updateIgnoreNull(projectType);
	}
		
	public void updateByExample(ProjectType projectType){
		projectTypeDao.update("updateByExampleProjectType", projectType);
	}

	public ProjectTypeVo load(ProjectType projectType){
		return (ProjectTypeVo)projectTypeDao.reload(projectType);
	}
	
	public ProjectTypeVo selectForObject(ProjectType projectType){
		return (ProjectTypeVo)projectTypeDao.selectForObject("selectProjectType",projectType);
	}
	
	public List<ProjectTypeVo> selectForList(ProjectType projectType){
		return (List<ProjectTypeVo>)projectTypeDao.selectForList("selectProjectType",projectType);
	}
	
	public Page page(Page page, ProjectType projectType) {
		return projectTypeDao.page(page, projectType);
	}

	@Autowired
	public void setIProjectTypeDao(
			@Qualifier("projectTypeDao") IProjectTypeDao  projectTypeDao) {
		this.projectTypeDao = projectTypeDao;
	}

}
