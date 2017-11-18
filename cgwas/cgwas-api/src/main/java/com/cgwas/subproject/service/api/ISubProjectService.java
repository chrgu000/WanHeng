package com.cgwas.subproject.service.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.entity.SubProjectVo;
/**
*  Author yangjun
*/
public interface ISubProjectService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(SubProject subProject);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(SubProject subProject);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(SubProject subProject);
	public abstract void delete(SubProject subProject);
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(SubProject subProject);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract SubProjectVo load(SubProject subProject);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract SubProjectVo selectForObject(SubProject subProject);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<SubProjectVo> selectForList(SubProject subProject);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, SubProject subProject);

	public abstract void create(SubProjectVo project, String directorIds,
			String principalIds,  String screenwriterIds,String beginTime, String endTime, HttpSession session);

	public abstract void update(SubProjectVo project, String directorIds,
			String principalIds, String screenwriterIds, String beginTime,
			String endTime, HttpSession session);

	public abstract Object selectForList(ProjectSearchVo vo);
	public abstract String importProject(String filePath, Long project_id,Long sub_parent_project_id,
			Long company_id, String errorInfo, HttpSession session)throws Exception;


	public abstract SubProjectVo getSubProjectById(Long id);


	public abstract List<SubProjectVo> getSubProjects(Map<String, Object> map);


	public abstract List<Long> getSubProjectIds(Map<String, String[]> map);

	public abstract List<Long> getModelTaskIds(Map<String, String[]> map);

	public abstract List<Long> getAnimationLightTaskIds(
			Map<String, String[]> map);

	public abstract void deleteAll(Map<String, String[]> map);

	public abstract List<SubProjectVo> getDataList(SubProjectVo project);

	public abstract List<Long> getModelTaskIdByMaps(Map<String, Object> map);

	public abstract List<Long> getAnimationLightTaskIdByMaps(
			Map<String, Object> map);

	public abstract SubProjectVo getProjectDetails(Long project_id);

	public abstract Long getParentParentId(Map<String, Object> map);

	public abstract Long getSubParentId(Map<String, Object> map);

	public abstract Long getParentId(Long id);

	public abstract List<SubProjectVo> getSubProjectsByUserId(Long user_id);

	public abstract void updateProjectState(Map<String, Object> m);

}