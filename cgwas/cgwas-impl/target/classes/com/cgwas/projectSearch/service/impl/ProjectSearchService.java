package com.cgwas.projectSearch.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.projectSearch.dao.api.IProjectSearchDao;
import com.cgwas.projectSearch.entity.ProjectSearch;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.projectSearch.service.api.IProjectSearchService;
/**
*  Author yangjun
*/
@Service
public class ProjectSearchService implements IProjectSearchService {
	private IProjectSearchDao projectSearchDao;

	public Serializable save(ProjectSearch projectSearch){
		return projectSearchDao.save(projectSearch);
	}

	public void delete(ProjectSearch projectSearch){
		projectSearchDao.delete(projectSearch);
	}
	
	public void deleteByExample(ProjectSearch projectSearch){
		projectSearchDao.deleteByExample(projectSearch);
	}

	public void update(ProjectSearch projectSearch){
		projectSearchDao.update(projectSearch);
	}
	
	public void updateIgnoreNull(ProjectSearch projectSearch){
		projectSearchDao.updateIgnoreNull(projectSearch);
	}
		
	public void updateByExample(ProjectSearch projectSearch){
		projectSearchDao.update("updateByExampleProjectSearch", projectSearch);
	}

	public ProjectSearchVo load(ProjectSearch projectSearch){
		return (ProjectSearchVo)projectSearchDao.reload(projectSearch);
	}
	
	public ProjectSearchVo selectForObject(ProjectSearch projectSearch){
		return (ProjectSearchVo)projectSearchDao.selectForObject("selectProjectSearch",projectSearch);
	}
	
	public List<ProjectSearchVo> selectForList(ProjectSearch projectSearch){
		return (List<ProjectSearchVo>)projectSearchDao.selectForList("selectProjectSearch",projectSearch);
	}
	
	public Page page(Page page, ProjectSearch projectSearch) {
		return projectSearchDao.page(page, projectSearch);
	}

	@Autowired
	public void setIProjectSearchDao(
			@Qualifier("projectSearchDao") IProjectSearchDao  projectSearchDao) {
		this.projectSearchDao = projectSearchDao;
	}

}
