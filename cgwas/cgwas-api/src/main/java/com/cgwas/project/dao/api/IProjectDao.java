package com.cgwas.project.dao.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.company.entity.Company;
import com.cgwas.labelTag.entity.LabelTag;
import com.cgwas.project.entity.Project;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.project.entity.UserProject;
import com.cgwas.search.entity.Search;
import com.cgwas.subproject.entity.SubProject;
/**
*  Author yangjun
*/
public interface IProjectDao extends IDaoSupport {
	Page page(Page page, Project project);
   
	List<Project> getProjectByIds(Map<String,String[]> map);
	
	void addProjectCompanyPlugin(Map<String, Object> map);

	void addProjectCompanySoftware(Map<String, Object> map);

	void addProjectDirector(Map<String, Object> map);

	void addProjectPrincipal(Map<String, Object> map);

	void deleteCompanyPlugin(Map<String, Long> m);

	void deleteCompanySoftware(Map<String, Long> m);

	void deleteProjectDirector(Map<String, Long> m);

	void deleterProjectPrincipal(Map<String, Long> m);

	void deleteAll(Map<String, String[]> map);

	void deleteCompanyPluginByProjectId(Map<String, String[]> map);

	void deleteCompanySoftwareByProjectId(Map<String, String[]> map);

	void deleteProjectDirectorByProjectId(Map<String, String[]> map);

	void deleterProjectPrincipalByProjectId(Map<String, String[]> map);

	void addProjectScreenwriter(Map<String, Object> map);

	void deleteProjectScreenwriter(Map<String, Long> m);

	ProjectVo getProjectById(Long id);

	List<Long> getSubProjectIds(Map<String, String[]> map);

	List<Long> getModelTaskIds(Map<String, String[]> projectIds);

	List<Long> getAnimationLightTaskIds(Map<String, String[]> projectIds);

	void deleteProjectScreenwriterByProjectId(Map<String, String[]> map);
	List<UserProject> getProjectByUserIds(Long userId);

	List<ProjectVo> getDataList(ProjectVo project);

	List<SubProject> getParentSubProjects(Map<String, Object> mapIds);

	List<SubProject> getSubProjects(Map<String, Object> mapIds);

	Integer getTotalUndistributed(Map<String, Object> mapIds);

	Integer getTotalRunning(Map<String, Object> mapIds);

	Integer getTotalFinished(Map<String, Object> mapIds);

	Integer getModelTaskFinishedTotal(Map<String, Object> mapIds);

	Integer getModelTaskTotal(Map<String, Object> mapIds);

	Integer getAnimationTaskFinishedTotal(Map<String, Object> mapIds);

	Integer getanimationTaskTotal(Map<String, Object> mapIds);

	Integer getLightTaskFinishedTotal(Map<String, Object> mapIds);

	Integer getLightTaskTotal(Map<String, Object> mapIds);

	Integer getTotal(Map<String, Object> mapIds);

	Long getProjectTotal(Map<String, Long> m);

	Date getMinPublishDate(Map<String, Object> mapIds);

	ProjectVo getProjectDetails(Long project_id);

	Integer getModelTaskMakerNums(Map<String, Object> mapIds);

	Integer getAnimationTaskMakerNums(Map<String, Object> mapIds);

	Project getProjectByProjectNo(String projectNo);

	Long getMaxId();

	Page page3(Page page, ProjectVo project);

	Date getActualBeginTime(Map<String, Object> mapIds);

	Integer getActualFinishTotal(Map<String, Object> mapIds);

	Date getActualEndTime(Map<String, Object> mapIds);

	List<ProjectVo> getProjectsByUserId(Long user_id);

	List<ProjectVo> getProjectsByCompanyId(Long company_id);

	Integer getManagerNums(Map<String, Object> map);

	List<ProjectVo> getProjectsByIds(Map<String, String[]> map);

	List<LabelTag> getAllLabelTag();

	void addProjectLabel(Map<String, Object> mp);

	void deleteProjectLabelTag(Map<String, Long> m);

	List<Long> getParentIds();

	List<Long> getSubIds();

	Double getTaskUseTimesOfProjects(Map<String, Object> map);

	Long getTaskUserNumsOfParents(Map<String, Object> map);

	Double getTaskCheckUseTimesOfProjects(Map<String, Object> map);

	Long getCheckNums(Map<String, Object> map);

	List<Long> getUserIdsByMap(Map<String, Object> map);

	List<Long> getIdsByMap(Map<String, Object> map);

	Date getJoinTimeOfMaker(Map<String, Object> map);

	List<ProjectVo> getSliderProjects();

	ProjectVo getRecommendProject();

	List<ProjectVo> getShowProjects();

	Company getCompanyById(Long company_id);

	Page page1(Page page, ProjectVo project);


	List<ProjectVo> getOldShowProjects();

	void updateProjectState(Map<String, Object> m);

	ProjectVo getOldRecommendProject();

	Search getSearch(String search);

	void updateSearch(Search search);

	void addSearch(Search search);

	List<Search> getHotSearchs();

	 

}