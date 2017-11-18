package com.cgwas.animationlighttask.dao.api;

import java.util.List;
import java.util.Map;

import javax.security.sasl.SaslClient;

import com.cgwas.animationlighttask.entity.AnimationLightTask;
import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.projectSearch.entity.ProjectSearch;
/**
*  Author yangjun
*/
public interface IAnimationLightTaskDao extends IDaoSupport {
	Page page(Page page, AnimationLightTask animationLightTask);

	void deleteCompanySoftware(Map<String, Long> m);

	void deleteAll(Map<String, String[]> map);

	void deleteSoftware(Map<String, String[]> map);

	AnimationLightTaskVo getAnimationLightTaskById(AnimationLightTaskVo m);

	void updatePointStatus(Map<String, Long> map);

	void updateAnimationLightTask(AnimationLightTask aTask);

	List<ProjectSearch> getAnimationLightTaskSearch();

	List<AnimationLightTaskVo> getCheckStatusTask(Map<String, Object> map);

	void reUpdateAnimationLightTask(AnimationLightTask aTask);

	List<AnimationLightTask> getBeginTimeBeforeNowAnimationLightTask(
			Map<String, List<Long>> animationLightTaskMap);

	void repeatBackAnimationTaskById(Map<String, Long> taskMap);

	void updateAnimationLightTaskEndTime(AnimationLightTaskVo aTask);

	Page page2(Page page, Map<String, Object> map);

}