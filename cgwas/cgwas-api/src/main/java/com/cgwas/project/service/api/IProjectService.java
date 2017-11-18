package com.cgwas.project.service.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.company.entity.Company;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.labelTag.entity.LabelTag;
import com.cgwas.project.entity.Project;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.project.entity.UserProject;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.search.entity.Search;
import com.cgwas.subproject.entity.SubProject;
/**
*  Author yangjun
*/
public interface IProjectService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Project project);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Project project);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Project project);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 * @param endTime 
	 * @param beginTime 
	 * @param softwares 
	 * @param plugins 
	 * @param principalIds 
	 * @param screenwriterIds 
	 * @param directorIds 
	 */

	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Project project);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Project project);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ProjectVo load(Project project);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ProjectVo selectForObject(Project project);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ProjectVo> selectForList(Project project);
	
	public abstract List<Project> getProjectByIds(Map<String,String[]> map);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Project project);

	public abstract void addProjectCompanyPlugin(Map<String, Object> map);

	public abstract void addProjectCompanySoftware(Map<String, Object> map);

	public abstract void addProjectDirector(Map<String, Object> map);

	public abstract void addProjectPrincipal(Map<String, Object> map);

	public abstract void create(ProjectVo project, String directorIds, 
			String principalIds, String plugins, String screenwriterIds,String softwares, String labeltagIds,String beginTime,String endTime, HttpSession session)throws Exception;

	public abstract void deleteAll(Map<String, String[]> map);

	public abstract String importProject(String filePath,Long company_id,String errorInfo, HttpSession session)throws Exception;
	/**
	 * 根据用户Id 得到项目 
	 * @param map
	 * @return
	 */
	public List<UserProject> getProjectByUserIds(Long userId);

	public abstract ProjectVo getProjectById(Long id);

	public abstract List<Long> getSubProjectIds(Map<String, String[]> map);

	public abstract List<Long> getModelTaskIds(
			Map<String, String[]> map);

	public abstract List<Long> getAnimationLightTaskIds(
			Map<String, String[]> map);

	public abstract List<ProjectVo> getDataList(ProjectVo project);

	public abstract List<SubProject> getParentSubProjects(
			Map<String, Object> mapIds);

	public abstract List<SubProject> getSubProjects(
			Map<String, Object> mapIds);

	public abstract Integer getTotalUndistributed(Map<String, Object> mapIds);

	public abstract Integer getTotalRunning(Map<String, Object> mapIds);

	public abstract Integer getTotalFinished(Map<String, Object> mapIds);

	public abstract Integer getModelTaskFinishedTotal(Map<String, Object> mapIds);

	public abstract Integer getModelTaskTotal(Map<String, Object> mapIds);

	public abstract Integer getAnimationTaskFinishedTotal(
			Map<String, Object> mapIds);

	public abstract Integer getanimationTaskTotal(Map<String, Object> mapIds);

	public abstract Integer getLightTaskFinishedTotal(Map<String, Object> mapIds);

	public abstract Integer getLightTaskTotal(Map<String, Object> mapIds);

	public abstract Integer getTotal(Map<String, Object> mapIds);

	public abstract Long getProjectTotal(Map<String, Long> m);

	public abstract Date getMinPublishDate(Map<String, Object> mapIds);

	public abstract ProjectVo getProjectDetails(Long project_id);

	public abstract Integer getModelTaskMakerNums(Map<String, Object> mapIds);

	public abstract Integer getAnimationTaskMakerNums(Map<String, Object> mapIds);

	public abstract Project getProjectByProjectNo(String projectNo);

	public abstract Long getMaxId();

	public abstract void update(ProjectVo project, String directorIds,
			String screenwriterIds, String principalIds, String plugins,
			String softwares, String labeltagIds,String beginTime, String endTime,  HttpSession session) throws Exception;

	public abstract Page page3(Page page, ProjectVo project);

	public abstract Date getActualBeginTime(Map<String, Object> mapIds);

	public abstract Integer getActualFinishTotal(Map<String, Object> mapIds);

	public abstract Date getActualEndTime(Map<String, Object> mapIds);

	public abstract List<ProjectVo> getProjectsByUserId(Long user_id);

	public abstract List<ProjectVo> getProjectsByCompanyId(Long company_id);

	public abstract Integer getManagerNums(Map<String, Object> map);

	public abstract List<LabelTag> getAllLabelTag();

	public abstract List<Long> getParentIds();

	public abstract List<Long> getSubIds();

	public abstract Double getTaskUseTimesOfProjects(Map<String, Object> map);

	public abstract Long getTaskUserNumsOfParents(Map<String, Object> map);

	public abstract Double getTaskCheckUseTimesOfProjects(
			Map<String, Object> map);

	public abstract Long getCheckNums(Map<String, Object> map);

	public abstract List<Long> getUserIdsByMap(Map<String, Object> map);

	public abstract List<Long> getIdsByMap(Map<String, Object> map);

	public abstract Date getJoinTimeOfMaker(Map<String, Object> map);

	public abstract List<ProjectVo> getSliderProjects();

	public abstract ProjectVo getRecommendProject();

	public abstract List<ProjectVo> getShowProjects();

	public abstract Company getCompanyById(Long id);

	public abstract Page page1(Page page, ProjectVo project);


	public abstract List<ProjectVo> getOldShowProjects();

	public abstract void updateProjectState(Map<String, Object> m);

	public abstract ProjectVo getOldRecommendProject();

	public abstract Search getSearch(String search);

	public abstract void updateSearch(Search s);

	public abstract void addSearch(Search s);

	public abstract List<Search> getHotSearchs();

	 


}