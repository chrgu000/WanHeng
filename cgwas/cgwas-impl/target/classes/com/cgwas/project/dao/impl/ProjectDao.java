package com.cgwas.project.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.company.entity.Company;
import com.cgwas.labelTag.entity.LabelTag;
import com.cgwas.project.dao.api.IProjectDao;
import com.cgwas.project.entity.Project;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.project.entity.UserProject;
import com.cgwas.search.entity.Search;
import com.cgwas.subproject.entity.SubProject;
/**
*  Author yangjun
*/
@Service
public class ProjectDao extends AbstractDao implements IProjectDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	
	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}
	@Override
	public void addProjectScreenwriter(Map<String, Object> map) {
		daoSupport.save("com.cgwas.project.dao.addProjectScreenwriter", map);
		
	}
	@Override
	public void addProjectCompanyPlugin(Map<String, Object> map) {
		daoSupport.save("com.cgwas.project.dao.addProjectCompanyPlugin", map);
	}

	@Override
	public void addProjectCompanySoftware(Map<String, Object> map) {
		daoSupport.save("com.cgwas.project.dao.addProjectCompanySoftware", map);
		
	}

	@Override
	public void addProjectDirector(Map<String, Object> map) {
		daoSupport.save("com.cgwas.project.dao.addProjectDirector",map);
		
	}

	@Override
	public void addProjectPrincipal(Map<String, Object> map) {
		daoSupport.save("com.cgwas.project.dao.addProjectPrincipal", map);
		
	}

	@Override
	public void deleteCompanyPlugin(Map<String,Long> m) {
		daoSupport.delete("com.cgwas.project.dao.deleteCompanyPlugin", m);
	}

	@Override
	public void deleteCompanySoftware(Map<String,Long> m) {
		daoSupport.delete("com.cgwas.project.dao.deleteCompanySoftware",m);
		
	}

	@Override
	public void deleteProjectDirector(Map<String,Long> m) {
		daoSupport.delete("com.cgwas.project.dao.deleteProjectDirector",m);
		
	}

	@Override
	public void deleterProjectPrincipal(Map<String,Long> m) {
		daoSupport.delete("com.cgwas.project.dao.deleteProjectPrincipal",m);
		
	}

	@Override
	public void deleteAll(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.project.dao.deleteProject",map);
		
	}

	@Override
	public void deleteCompanyPluginByProjectId(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.project.dao.deleteCompanyPluginByProjectId", map);
		
	}

	@Override
	public void deleteCompanySoftwareByProjectId(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.project.dao.deleteCompanySoftwareByProjectId",map);
		
	}

	@Override
	public void deleteProjectDirectorByProjectId(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.project.dao.deleteProjectDirectorByProjectId",map);
	}

	@Override
	public void deleterProjectPrincipalByProjectId(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.project.dao.deleteProjectPrincipalByProjectId",map);
	}
	@Override
	public void deleteProjectScreenwriter(Map<String, Long> m) {
		daoSupport.delete("com.cgwas.project.dao.deleteProjectScreenwriter",m);
	}
	@Override
	public Page page(Page page, Project project) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProjectPage", "selectProjectCount");
		return pageQuery.select(project);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjectByIds(Map<String,String[]> ids) {
		return (List<Project>) daoSupport.selectForList("com.cgwas.project.dao.getProjectByIds", ids);
	}

	@Override
	public ProjectVo getProjectById(Long id) {
		Map<String,Long> map=new HashMap<String,Long>();
		map.put("id", id);
		return (ProjectVo) daoSupport.selectForObject("com.cgwas.project.dao.getProjectById", map);
	}

	@Override
	public List<Long> getSubProjectIds(Map<String, String[]> map) {
		return  (List<Long>) daoSupport.selectForList("com.cgwas.project.dao.getSubProjectIds", map);
	}

	@Override
	public List<Long> getModelTaskIds(Map<String, String[]> projectIds) {
		return (List<Long>) daoSupport.selectForList("com.cgwas.project.dao.getModelTaskIds", projectIds);
	}

	@Override
	public List<Long> getAnimationLightTaskIds(Map<String, String[]> projectIds) {
		return (List<Long>) daoSupport.selectForList("com.cgwas.project.dao.getAnimationLightTaskIds", projectIds);
	}

	@Override
	public void deleteProjectScreenwriterByProjectId(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.project.dao.deleteProjectScreenwriterByProjectId",map);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserProject> getProjectByUserIds(Long userId) {
		List<UserProject> retnList = new ArrayList<UserProject>();
		retnList.addAll((Collection<? extends UserProject>) daoSupport
				.selectForList("com.cgwas.project.dao.getProjectByUserIdsDirector", userId));
		retnList.addAll((Collection<? extends UserProject>) daoSupport
				.selectForList("com.cgwas.project.dao.getProjectByUserIdsPrincipal", userId));
		retnList.addAll((Collection<? extends UserProject>) daoSupport
				.selectForList("com.cgwas.project.dao.getProjectByUserIdscreenwriter", userId));
		return retnList;
	}

	@Override
	public List<ProjectVo> getDataList(ProjectVo project) {
		return (List<ProjectVo>) daoSupport.selectForList("com.cgwas.project.dao.getDataList", project);
	}

	@Override
	public List<SubProject> getParentSubProjects(Map<String, Object> mapIds) {
		return (List<SubProject>) daoSupport.selectForList("com.cgwas.project.dao.getParentSubProjects", mapIds);
	}

	@Override
	public List<SubProject> getSubProjects(Map<String, Object> mapIds) {
		return (List<SubProject>) daoSupport.selectForList("com.cgwas.project.dao.getSubProjects", mapIds);
	}

	

	@Override
	public Integer getTotalUndistributed(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getTotalUndistributed", mapIds);
	}

	@Override
	public Integer getTotalRunning(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getTotalRunning", mapIds);
	}

	@Override
	public Integer getTotalFinished(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getTotalFinished", mapIds);
	}

	@Override
	public Integer getModelTaskFinishedTotal(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getModelTaskFinishedTotal", mapIds);

	}

	@Override
	public Integer getModelTaskTotal(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getModelTaskTotal", mapIds);
	}

	@Override
	public Integer getAnimationTaskFinishedTotal(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getAnimationTaskFinishedTotal", mapIds);
	}

	@Override
	public Integer getanimationTaskTotal(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getanimationTaskTotal", mapIds);
	}

	@Override
	public Integer getLightTaskFinishedTotal(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getLightTaskFinishedTotal", mapIds);
	}

	@Override
	public Integer getLightTaskTotal(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getLightTaskTotal", mapIds);
	}

	@Override
	public Integer getTotal(Map<String, Object> mapIds) {
		return (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getTotal", mapIds);
	}

	@Override
	public Long getProjectTotal(Map<String, Long> m) {
		return (Long) daoSupport.selectForObject("com.cgwas.project.dao.getProjectTotal", m);
	}

	@Override
	public Date getMinPublishDate(Map<String, Object> mapIds) {
		return (Date) daoSupport.selectForObject("com.cgwas.project.dao.getMinPublishDate",mapIds);
	}

	@Override
	public ProjectVo getProjectDetails(Long project_id) {
		return (ProjectVo) daoSupport.selectForObject("com.cgwas.project.dao.getProjectDetails",project_id);
	}

	@Override
	public Integer getModelTaskMakerNums(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getModelTaskMakerNums",mapIds);
	}

	@Override
	public Integer getAnimationTaskMakerNums(Map<String, Object> mapIds) {
		return  (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getAnimationTaskMakerNums",mapIds);
	}

	@Override
	public Project getProjectByProjectNo(String projectNo) {
		return (Project) daoSupport.selectForObject("com.cgwas.project.dao.getProjectByProjectNo",projectNo);
	}

	@Override
	public Long getMaxId() {
		return (Long) daoSupport.selectForObject("com.cgwas.project.dao.getMaxId");
	}
	@Override
	public Page page1(Page page, ProjectVo project) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProjectPage1", "selectProjectCount1");
		return pageQuery.select(project);
	}
	@Override
	public Page page3(Page page, ProjectVo project) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProjectPage3", "selectProjectCount3");
		return pageQuery.select(project);
	}

	@Override
	public Date getActualBeginTime(Map<String, Object> mapIds) {
		return (Date) daoSupport.selectForObject("com.cgwas.project.dao.getActualBeginTime",mapIds);
	}

	@Override
	public Integer getActualFinishTotal(Map<String, Object> mapIds) {
		return (Integer) daoSupport.selectForObject("com.cgwas.project.dao.getActualFinishTotal",mapIds);
	}

	@Override
	public Date getActualEndTime(Map<String, Object> mapIds) {
		return (Date) daoSupport.selectForObject("com.cgwas.project.dao.getActualEndTime",mapIds);
	}

	@Override
	public List<ProjectVo> getProjectsByUserId(Long user_id) {
		return (List<ProjectVo>) daoSupport.selectForList("com.cgwas.project.dao.getProjectsByUserId", user_id);
	}

	@Override
	public List<ProjectVo> getProjectsByCompanyId(Long company_id) {
		return (List<ProjectVo>) daoSupport.selectForList("com.cgwas.project.dao.getProjectsByCompanyId", company_id);
	}

	@Override
	public Integer getManagerNums(Map<String, Object> map) {
		return (Integer) daoSupport.selectForObject("getManagerNums", map);
	}

	@Override
	public List<ProjectVo> getProjectsByIds(Map<String, String[]> map) {
		return (List<ProjectVo>) daoSupport.selectForList("com.cgwas.project.dao.getProjectsByIds", map);
	}

	@Override
	public List<LabelTag> getAllLabelTag() {
		return (List<LabelTag>) daoSupport.selectForList("com.cgwas.project.dao.getAllLabelTag");
	}

	@Override
	public void addProjectLabel(Map<String, Object> mp) {
		daoSupport.save("com.cgwas.project.dao.addProjectLabel", mp);
		
	}

	@Override
	public void deleteProjectLabelTag(Map<String, Long> m) {
		daoSupport.delete("com.cgwas.project.dao.deleteProjectLabelTag", m);
	}

	@Override
	public List<Long> getParentIds() {
		return (List<Long>) daoSupport.selectForList("com.cgwas.project.dao.getParentIds");
	}

	@Override
	public List<Long> getSubIds() {
		return (List<Long>) daoSupport.selectForList("com.cgwas.project.dao.getSubIds");
	}

	@Override
	public Double getTaskUseTimesOfProjects(Map<String, Object> map) {
		return (Double) daoSupport.selectForObject("com.cgwas.project.dao.getTaskUseTimesOfProjects",map);
	}

	@Override
	public Long getTaskUserNumsOfParents(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.project.dao.getTaskUserNumsOfParents",map);
	}

	@Override
	public Double getTaskCheckUseTimesOfProjects(Map<String, Object> map) {
		return (Double) daoSupport.selectForObject("com.cgwas.project.dao.getTaskCheckUseTimesOfProjects",map);
	}

	@Override
	public Long getCheckNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.project.dao.getCheckNums",map);
	}

	@Override
	public List<Long> getUserIdsByMap(Map<String, Object> map) {
		return (List<Long>) daoSupport.selectForList("com.cgwas.project.dao.getUserIdsByMap",map);
	}

	@Override
	public List<Long> getIdsByMap(Map<String, Object> map) {
		return (List<Long>) daoSupport.selectForList("com.cgwas.project.dao.getIdsByMap",map);
	}

	@Override
	public Date getJoinTimeOfMaker(Map<String, Object> map) {
		return (Date) daoSupport.selectForObject("com.cgwas.project.dao.getJoinTimeOfMaker",map);
	}

	@Override
	public List<ProjectVo> getSliderProjects() {
		return (List<ProjectVo>) daoSupport.selectForList("com.cgwas.project.dao.getSliderProjects");
	}

	@Override
	public ProjectVo getRecommendProject() {
		return (ProjectVo) daoSupport.selectForObject("com.cgwas.project.dao.getRecommendProject");
	}

	@Override
	public List<ProjectVo> getShowProjects() {
		return (List<ProjectVo>) daoSupport.selectForList("com.cgwas.project.dao.getShowProjects");
	}

	@Override
	public Company getCompanyById(Long company_id) {
		return (Company) daoSupport.selectForObject("com.cgwas.project.dao.getCompanyById",company_id);
	}

	 

	@Override
	public List<ProjectVo> getOldShowProjects() {
		return (List<ProjectVo>) daoSupport.selectForList("com.cgwas.project.dao.getOldShowProjects");
	}

	@Override
	public void updateProjectState(Map<String, Object> m) {
		daoSupport.update("com.cgwas.project.dao.updateProjectState",m);
		
	}

	@Override
	public ProjectVo getOldRecommendProject() {
		return (ProjectVo) daoSupport.selectForObject("com.cgwas.project.dao.getOldRecommendProject");
	}

	@Override
	public Search getSearch(String search) {
		return (Search) daoSupport.selectForObject("com.cgwas.project.dao.getSearch");
	}

	@Override
	public void updateSearch(Search search) {
		daoSupport.update("com.cgwas.project.dao.updateSearch",search);
		
	}

	@Override
	public void addSearch(Search search) {
		daoSupport.save("com.cgwas.project.dao.addSearch",search);
		
	}

	@Override
	public List<Search> getHotSearchs() {
		return (List<Search>) daoSupport.selectForList("com.cgwas.project.dao.getHotSearchs");
	}

	 

	 
}