package com.cgwas.animationlighttask.service.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.cgwas.animationlighttask.entity.AnimationLightTask;
import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.projectSearch.entity.ProjectSearch;
/**
*  Author yangjun
*/
public interface IAnimationLightTaskService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(AnimationLightTask animationLightTask);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(AnimationLightTask animationLightTask);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(AnimationLightTask animationLightTask);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(AnimationLightTask animationLightTask);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(AnimationLightTask animationLightTask);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract AnimationLightTaskVo load(AnimationLightTask animationLightTask);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract AnimationLightTaskVo selectForObject(AnimationLightTask animationLightTask);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<AnimationLightTaskVo> selectForList(AnimationLightTask animationLightTask);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, AnimationLightTask animationLightTask);

	public abstract Result create(AnimationLightTask animationLightTask,
			String beginTime, String endTime, String softwares,String is_parent_project, HttpSession session,CompanyFilesVo vo);

	public abstract Result update(AnimationLightTaskVo animationLightTask,
			String beginTime, String endTime, String softwares);

	public abstract void deleteAll(Map<String, String[]> map);

	public abstract AnimationLightTaskVo getAnimationLightTaskById(
			AnimationLightTaskVo m);

	public abstract String importAnimationLightTask(String filePath, Long company_id,Long class_id,Long project_id,String is_parent_project,String errorInfo, HttpSession session);

	public abstract void updatePointStatus(Map<String, Long> map);

	public abstract void update(AnimationLightTask aTask);

	public abstract void updateAnimationLightTask(AnimationLightTask aTask);

	public abstract List<ProjectSearch> getAnimationLightTaskSearch();

	public abstract List<AnimationLightTaskVo> getCheckStatusTask(
			Map<String, Object> map);

	public abstract void reUpdateAnimationLightTask(AnimationLightTask aTask);

	public abstract List<AnimationLightTask> getBeginTimeBeforeNowAnimationLightTask(
			Map<String, List<Long>> animationLightTaskMap);

	public abstract void repeatBackAnimationTaskById(
			Map<String, Long> taskMap);

	public abstract void updateAnimationLightTaskEndTime(
			AnimationLightTaskVo aTask);

	public abstract void updatePublishTypeOfTask(Map<String, Object> map);

	public abstract AnimationLightTaskVo getDetails(Long task_id);

	public abstract Page page2(Page page, Map<String, Object> map);

	public abstract Long getTaskTotal(Map<String, Object> map);

	public abstract Long getTaskFinishedTotal(Map<String, Object> map);

	public abstract Date getTaskBeginTime(Map<String, Object> map);

	public abstract Date getTaskEndTime(Map<String, Object> map);

	public abstract Date getTaskActualBeginTime(Map<String, Object> map);

	public abstract Date getTaskActualEndTime(Map<String, Object> map);

	public abstract Date getAnimationLightTaskActualEndTime(Long id);

	public abstract Long getTaskId(AnimationLightTaskVo animationLightTask);

	public abstract List<AnimationLightTaskVo> getTasksByIds(
			Map<String, String[]> map);

	public abstract void reBackTask(Long task_id);

	public abstract AnimationLightTaskVo getReceivingModelTaskById(Long task_id);
 

}