package com.cgwas.animationlighttask.dao.impl;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.animationlighttask.dao.api.IAnimationLightTaskDao;
import com.cgwas.animationlighttask.entity.AnimationLightTask;
import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.projectSearch.entity.ProjectSearch;
/**
*  Author yangjun
*/
@Service
public class AnimationLightTaskDao extends AbstractDao implements IAnimationLightTaskDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, AnimationLightTask animationLightTask) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAnimationLightTaskPage", "selectAnimationLightTaskCount");
		return pageQuery.select(animationLightTask);
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
	public void deleteCompanySoftware(Map<String, Long> m) {
		 daoSupport.delete("com.cgwas.animationlighttask.dao.deleteCompanySoftware", m);
	}

	@Override
	public void deleteAll(Map<String, String[]> map) {
		daoSupport.delete("com.cgwas.animationlighttask.dao.deleteAll", map);
	}

	@Override
	public void deleteSoftware(Map<String, String[]> map) {
		 daoSupport.delete("com.cgwas.animationlighttask.dao.deleteSoftware", map);
	}

	@Override
	public AnimationLightTaskVo getAnimationLightTaskById(AnimationLightTaskVo m) {
		return (AnimationLightTaskVo) daoSupport.selectForObject("com.cgwas.animationlighttask.dao.getAnimationLightTaskById",m );
	}

	@Override
	public void updatePointStatus(Map<String, Long> map) {
		daoSupport.update("com.cgwas.animationlighttask.dao.updatePointStatus", map);
		
	}

	@Override
	public void updateAnimationLightTask(AnimationLightTask aTask) {
		daoSupport.update("com.cgwas.animationlighttask.dao.updateAnimationTask", aTask);
		
	}

	@Override
	public List<ProjectSearch> getAnimationLightTaskSearch() {
		return (List<ProjectSearch>) daoSupport.selectForList("com.cgwas.animationlighttask.dao.getAnimationLightTaskSearch");
	}

	@Override
	public List<AnimationLightTaskVo> getCheckStatusTask(Map<String,Object> map) {
		return (List<AnimationLightTaskVo>) daoSupport.selectForList("com.cgwas.animationlighttask.dao.getCheckStatusTask",map);
	}

	@Override
	public void reUpdateAnimationLightTask(AnimationLightTask aTask) {
		daoSupport.update("com.cgwas.animationlighttask.dao.reUpdateAnimationLightTask", aTask);
		
	}

	@Override
	public List<AnimationLightTask> getBeginTimeBeforeNowAnimationLightTask(
			Map<String, List<Long>> animationLightTaskMap) {
		return (List<AnimationLightTask>) daoSupport.selectForList("com.cgwas.animationlighttask.dao.getBeginTimeBeforeNowAnimationLightTask",animationLightTaskMap);
	}

	@Override
	public void repeatBackAnimationTaskById(Map<String, Long> taskMap) {
		daoSupport.update("com.cgwas.animationlighttask.dao.repeatBackAnimationTaskById", taskMap);
	}

	@Override
	public void updateAnimationLightTaskEndTime(AnimationLightTaskVo aTask) {
		daoSupport.update("com.cgwas.animationlighttask.dao.updateAnimationLightTaskEndTime", aTask);
		
	}

	@Override
	public Page page2(Page page, Map<String, Object> map) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectAnimationLightTaskPage2", "selectAnimationLightTaskCount2");
		return pageQuery.select(map);	}
}