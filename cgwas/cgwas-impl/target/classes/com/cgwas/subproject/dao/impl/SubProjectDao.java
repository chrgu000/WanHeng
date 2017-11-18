package com.cgwas.subproject.dao.impl;

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
import com.cgwas.subproject.dao.api.ISubProjectDao;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.entity.SubProjectVo;

/**
 * Author yangjun
 */
@Service
public class SubProjectDao extends AbstractDao implements ISubProjectDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, SubProject subProject) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"com.cgwas.subproject.dao.selectSubProjectPage", "com.cgwas.subproject.dao.selectSubProjectCount");
		return pageQuery.select(subProject);
	}

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
	public SubProjectVo getSubProjectById(Long id) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", id);
		return (SubProjectVo) daoSupport.selectForObject("com.cgwas.subproject.dao.getSubProjectById",
				map);
	}

	@Override
	public List<SubProjectVo> getSubProjects(Map<String, Object> map) {
		return (List<SubProjectVo>) daoSupport.selectForList(
				"com.cgwas.subproject.dao.getSubProjects", map);
	}

	@Override
	public List<Long> getSubProjectIds(Map<String, String[]> map) {
		return (List<Long>) daoSupport.selectForList(
				"com.cgwas.subproject.dao.getSubProjectIds", map);
	}

	@Override
	public List<Long> getModelTaskIds(Map<String, String[]> map) {
		return (List<Long>) daoSupport.selectForList(
				"com.cgwas.subproject.dao.getModelTaskIds", map);
	}

	@Override
	public List<Long> getAnimationLightTaskIds(Map<String, String[]> map) {
		return (List<Long>) daoSupport.selectForList(
				"com.cgwas.subproject.dao.getAnimationLightTaskIds", map);
		}

	@Override
	public void deleteProjectDirectorByProjectId(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.subproject.dao.deleteProjectDirectorByProjectId", map);
		
	}

	@Override
	public void deleteAll(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.subproject.dao.deleteAll", map);
		
	}

	@Override
	public void deleterProjectPrincipalByProjectId(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.subproject.dao.deleterProjectPrincipalByProjectId", map);
		
	}

	@Override
	public void deleteProjectScreenwriterByProjectId(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.subproject.dao.deleteProjectScreenwriterByProjectId", map);
	}

	@Override
	public List<SubProjectVo> getDataList(SubProjectVo project) {
		return (List<SubProjectVo>) daoSupport.selectForList("com.cgwas.subproject.dao.getDataList", project);
	}

	@Override
	public List<Long> getModelTaskIdByMaps(Map<String, Object> map) {
		return (List<Long>) daoSupport.selectForList(
				"com.cgwas.subproject.dao.getModelTaskIdByMaps", map);
	}

	@Override
	public List<Long> getAnimationLightTaskIdByMaps(Map<String, Object> map) {
		return (List<Long>) daoSupport.selectForList(
				"com.cgwas.subproject.dao.getAnimationLightTaskIdByMaps", map);
	}

	@Override
	public SubProjectVo getProjectDetails(Long project_id) {
		return  (SubProjectVo) daoSupport.selectForObject(
				"com.cgwas.subproject.dao.getProjectDetails", project_id);
	}

	@Override
	public Long getParentParentId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject(
				"com.cgwas.subproject.dao.getParentParentId", map);
	}

	@Override
	public Long getSubParentId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject(
				"com.cgwas.subproject.dao.getSubParentId", map);
	}

	@Override
	public Long getParentId(Long id) {
		return (Long) daoSupport.selectForObject(
				"com.cgwas.subproject.dao.getParentId", id);
	}

	@Override
	public List<SubProjectVo> getSubProjectsByUserId(Long user_id) {
		return (List<SubProjectVo>) daoSupport.selectForList(
				"com.cgwas.subproject.dao.getSubProjectsByUserId", user_id);
	}
	@Override
	public void updateProject(Map<String, Object> m) {
		daoSupport.selectForList(
				"com.cgwas.subproject.dao.updateProject", m);
		
	}

	@Override
	public List<SubProjectVo> getSubProjectsByIds(Map<String, String[]> map) {
		return (List<SubProjectVo>) daoSupport.selectForList("getSubProjectsByIds",map);
	}

	@Override
	public void updateProjectState(Map<String, Object> m) {
		daoSupport.update("com.cgwas.subproject.dao.updateProjectState",m);
	}

}