package com.cgwas.subproject.dao.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.entity.SubProjectVo;
/**
*  Author yangjun
*/
public interface ISubProjectDao extends IDaoSupport {
	Page page(Page page, SubProject subProject);

	SubProjectVo getSubProjectById(Long id);
	List<SubProjectVo> getSubProjects(Map<String, Object> map);

	List<Long> getSubProjectIds(Map<String, String[]> map);

	List<Long> getModelTaskIds(Map<String, String[]> map);

	List<Long> getAnimationLightTaskIds(Map<String, String[]> map);

	void deleteProjectDirectorByProjectId(Map<String, String[]> map);

	void deleteAll(Map<String, String[]> map);

	void deleterProjectPrincipalByProjectId(Map<String, String[]> map);

	void deleteProjectScreenwriterByProjectId(Map<String, String[]> map);

	List<SubProjectVo> getDataList(SubProjectVo project);

	List<Long> getModelTaskIdByMaps(Map<String, Object> map);

	List<Long> getAnimationLightTaskIdByMaps(Map<String, Object> map);

	SubProjectVo getProjectDetails(Long project_id);

	Long getParentParentId(Map<String, Object> map);

	Long getSubParentId(Map<String, Object> map);

	Long getParentId(Long id);

	List<SubProjectVo> getSubProjectsByUserId(Long user_id);

	void updateProject(Map<String, Object> m);

	List<SubProjectVo> getSubProjectsByIds(Map<String, String[]> map);

	void updateProjectState(Map<String, Object> m);
}