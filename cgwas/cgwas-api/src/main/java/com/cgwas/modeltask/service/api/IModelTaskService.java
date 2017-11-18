package com.cgwas.modeltask.service.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.cgwas.auditRecord.entity.AuditRecordVo;
import com.cgwas.clazz.entity.Clazz;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.degree.entity.Degree;
import com.cgwas.evaluation.entity.EvaluationVo;
import com.cgwas.modeltask.entity.ModelTask;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltype.entity.ModelType;
import com.cgwas.notice.entity.Notice;
import com.cgwas.projectSearch.entity.ProjectSearch;
import com.cgwas.proof.entity.Proof;
import com.cgwas.proofimage.entity.ProofImage;
import com.cgwas.publishtype.entity.PublishType;
import com.cgwas.repair.entity.Repair;
import com.cgwas.repairimage.entity.RepairImage;
import com.cgwas.taskCheck.entity.TaskCheck;
import com.cgwas.taskapply.entity.TaskApply;
import com.cgwas.taskrecord.entity.TaskRecord;
import com.cgwas.user.entity.User;
import com.cgwas.userInfo.entity.UserInfo;

/**
 * Author yangjun
 */
public interface IModelTaskService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ModelTaskVo modelTask);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ModelTaskVo modelTask);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(ModelTaskVo modelTask);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(ModelTaskVo modelTask);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(ModelTaskVo modelTask);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ModelTaskVo load(ModelTask modelTask);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ModelTaskVo selectForObject(ModelTaskVo modelTask);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ModelTaskVo> selectForList(ModelTaskVo modelTask);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ModelTaskVo modelTask);

	public abstract Result create(ModelTaskVo modelTask, String beginTime,
			String endTime, String softwares, String is_parent_poject, HttpSession session,CompanyFilesVo vo);

	public abstract List<ModelType> getModelTypes();

	public abstract List<PublishType> getPublishTypes();

	public abstract List<Degree> getDegrees();

	public abstract ModelTaskVo getModelTaskById(ModelTaskVo m);

	public abstract Result update(ModelTaskVo modelTask, String beginTime,
			String endTime, String softwares);

	public abstract void deleteAll(Map<String, String[]> map);

	public abstract List<Clazz> getClazz();

	public abstract String importModelTask(String filePath, Long company_id,
			Long class_id, Long project_id, String is_parent_project,
			String errorInfo, HttpSession session);

	public abstract void publishTask(Map<String, List<Long>> modelTaskMap,
			Map<String, List<Long>> animationLightTaskMap);

	public abstract void pointerModelTaskToUser(Map<String, Object> map);

	public abstract void pointerAnimationLightTaskToUser(Map<String, Object> map);

	public abstract void addTaskApply(TaskApply apply);

	public abstract void updatePointStatus(Map<String, Long> map);

	public abstract void publishPointTask(Map<String, List<Long>> modelTaskMap,
			Map<String, List<Long>> animationLightTaskMap);

	public abstract void update(ModelTaskVo mTask);

	public abstract void updateTaskApply(TaskApply apply);

	public abstract void updateModelTask(ModelTaskVo mTask);

	public abstract Long getNotAgreeNums(Map<String, Object> map);

	public abstract Long getCheekerIsNotNullNums(Map<String, Object> map);

	public abstract void deleteTaskApply(Map<String, Object> map);

	public abstract void addTaskCheck(TaskCheck check);

	public abstract List<ProjectSearch> getModelTaskSearch();

	public abstract List<ProjectSearch> getPublishTaskSearch();

	public abstract List<ModelTaskVo> getTaskByProjectId(Map<String, Object> map);

	public abstract List<ProjectSearch> getModelTaskCheckSearch();

	public abstract List<ProjectSearch> getAnimationLightTaskCheckSearch();

	public abstract List<ModelTaskVo> getCheckStatusTask(Map<String, Object> map);

	public abstract Long getRecheckNum(Map<String, Object> map);

	public abstract Long getMaxDegreeNum(Map<String, Object> map);

	public abstract Long getCurrentDegreeNum(Map<String, Object> map);

	public abstract UserInfo getNextChecker(Map<String, Object> map);

	public abstract List<EvaluationVo> getEvaluationInfo();

	public abstract Long getTaskCheckId(Map<String, Object> map);

	public abstract void updateCheckNum(Map<String, Object> map);

	public abstract void addAuditRecord(AuditRecordVo vo);

	public abstract void addRepair(Repair repair);

	public abstract void addRepairImage(RepairImage image);

	public abstract void updateTaskCheck(TaskCheck check);

	public abstract TaskCheck getTaskCheck(Map<String, Object> map);
 
	public abstract List<Long> getPublishModelTaskIds(
			Map<String, List<Long>> modelTaskMap);

	public abstract List<Long> getPublishAnimationLightTaskIds(
			Map<String, List<Long>> animationLightTaskMap);
 
	public abstract List<Long> getPublishPointModelTaskIds(
			Map<String, List<Long>> modelTaskMap);

	public abstract List<Long> getPublishPointAnimationLightTaskIds(
			Map<String, List<Long>> animationLightTaskMap);

	public abstract void repeatBackTask(
			Map<String, List<Long>> publishModelTaskIds,
			Map<String, List<Long>> publishAnimationLightTaskIds);

	public abstract void repeatBackPointTask(
			Map<String, List<Long>> publishPointModelTaskIds,
			Map<String, List<Long>> publishPointAnimationLightTaskIds);

	public abstract void updateModelTaskTimeLiness(Map<String, Object> map);

	public abstract void updateAnimationLightTaskTimeLiness(
			Map<String, Object> map);

	public abstract void reUpdateModelTask(ModelTaskVo mTask);

	public abstract void deleteModelTaskApplys(Map<String, Object> modelTaskMap);

	public abstract void deleteAnimationTaskApply(
			Map<String, Object> animationTaskMap);

	public abstract Long getUserIdOfModelTaskApply(Map<String, Object> map);

	public abstract Long getUserIdOfAnimationTaskApply(Map<String, Object> map);

	public abstract List<ModelTaskVo> getBeginTimeBeforeNowModelTask(
			Map<String, List<Long>> modelTaskMap);

	public abstract void repeatBackModelTaskById(Map<String, Long> taskMap);

	public abstract void updateModelTaskEndTime(ModelTaskVo mTask);

	public abstract List<ModelTaskVo> getTaskByStageOrStatus(
			Map<String, Object> map);

	public abstract Long getPublushNums(Map<String, Object> map);

	public abstract Long getUnStartNums(Map<String, Object> map);

	public abstract Long getRunningNums(Map<String, Object> map);

	public abstract Long getCheckNums(Map<String, Object> map);

	public abstract Long getWaitPayNums(Map<String, Object> map);

	public abstract Long getWaitEvalutionNums(Map<String, Object> map);

	public abstract Long getabandonNums(Map<String, Object> map);

	public abstract Page page1(Page page, ModelTaskVo task);

	public abstract Date getSubmitDate(Map<String, Object> map);

	public abstract void updatePublishTypeOfTask(Map<String, Object> map);

	public abstract List<Notice> getNoticeInfo(Notice notice);

	public abstract ModelTaskVo getDetails(Long task_id);

	public abstract Page page2(Page page, Map<String, Object> map);

	public abstract Date getModelTaskBeginTime(Map<String, Object> map);

	public abstract Date getModelTaskEndTime(Map<String, Object> map);

	public abstract Date getModelTaskActualBeginTime(Map<String, Object> map);

	public abstract Long getTaskTotal(Map<String, Object> map);

	public abstract Long getTaskFinishedTotal(Map<String, Object> map);

	public abstract Date getModelTaskActualEndTime(Map<String, Object> map);

	public abstract Page page3(Page page, Map<String, Object> map);

	public abstract Date getModelTaskActualEndTime(Long id);

	public abstract Long getTaskOfSomeInfoNums(Map<String, Object> map);

	public abstract Long getPointTaskNums(Map<String, Object> map);

	public abstract Long getGetTaskNums(Map<String, Object> map);

	public abstract Long getTaskClassNums(Map<String, Object> map);

	public abstract Long getIsDrawOfMe(Map<String, Object> map);

	public abstract Date getBeginTime(Map<String, Object> map);

	public abstract Date getEndTime(Map<String, Object> map);

	public abstract Date getActualBeginTime(Map<String, Object> map);

	public abstract Date getActualEndTime(Map<String, Object> map);

	public abstract List<UserInfo> getUserInfos(Map<String, Object> mp);

	public abstract Long getFinishedNums(Map<String, Object> map);

	public abstract Long getRoleIdByUserId(Long user_id);

	public abstract List<Map<String, Long>> getJoinProjectsByUserId(Long user_id);

	public abstract Long getIsMaker(Long user_id);

	public abstract List<ModelTaskVo> getTaskByStageOrStatus1(
			Map<String, Object> map);

	public abstract List<Long> getCompanysByUserId(Long user_id);

	public abstract List<ModelTaskVo> getTaskByStageOrStatus2(
			Map<String, Object> map);

	public abstract Long getIsChecker(Map<String, Object> mp);

	public abstract List<ModelTaskVo> getCheckStatusTasks(
			Map<String, Object> map);

	public abstract List<ModelTaskVo> getTaskByStageOrStatus3(
			Map<String, Object> map);

	public abstract List<ModelTaskVo> getTaskByStageOrStatus4(
			Map<String, Object> map);

	public abstract List<ModelTaskVo> getTaskByStageOrStatus5(
			Map<String, Object> map);

	public abstract List<ModelTaskVo> getTaskByStageOrStatus6(
			Map<String, Object> map);

	public abstract List<ModelTaskVo> getTaskByStageOrStatus7(
			Map<String, Object> map);

	public abstract List<ModelTaskVo> getTaskByStageOrStatusCompany(
			Map<String, Object> map);

	public abstract List<ModelTaskVo> getTaskByStageOrStatus0(
			Map<String, Object> map);

	public abstract Long getTaskId(ModelTaskVo modelTask);

	public abstract List<Long> getTaskIds(CompanyFilesVo v);

	public abstract Long getPublishTaskNums(Long company_id);

	public abstract Long getSuccessTaskNums(Long company_id);

	public abstract Long getSuccessTaskNumsOfUser(Long user_id);

	public abstract Long getIsCheckerOrNot(Map<String, Object> map);

	public abstract Double getProjectPaid(Map<String, Object> mapIds);

	public abstract Repair getRepairInfo(Map<String, Object> map);

	public abstract List<ModelTaskVo> getTasksByIds(Map<String, String[]> map);

	public abstract void reBackTask(Long task_id);

	public abstract Long isChecker(Long user_id);

	public abstract Long getIsGroupMemeber(Map<String, Object> mp1);

	public abstract Long getTaskApply(TaskApply apply);

	public abstract Long getIsMakerOfGroup(Map<String, Object> m);

	public abstract List<String> getCheckersOfTask(Map<String, Object> mp);


	public abstract String getCurrentChecker(Map<String, Object> mp);

	public abstract Long hasGroupMemeber(Map<String, Object> mp);

	public abstract void addProof(Proof proof);

	public abstract void addProofImage(ProofImage image);

	public abstract Long getRunningNumsOfProject(Map<String, Object> map);

	public abstract Long getCheckNumsOfProject(Map<String, Object> map);

	public abstract Long getFinishedNumsOfProject(Map<String, Object> map);

	public abstract Long getabandonNumsOfProject(Map<String, Object> map);

	public abstract Long getTaskTotalOfProject(Map<String, Object> map);

	public abstract Date getRepairTime(Map<String, Object> m);

	public abstract TaskRecord getTaskRecord(Map<String, Object> map);

	public abstract ModelTaskVo getReceivingModelTaskById(Long task_id);

	public abstract void addTaskRecord(TaskRecord t);

	public abstract Date getCommitTime(Map<String, Object> map);

	public abstract List<Long> getTaskNumsOfUser(Long id);

	public abstract Long getFinishedNumsOfUser(Map<String, Object> dMap);

	public abstract Long getOutTaskEndTimeNums(Map<String, Object> dMap);

	public abstract Long getPublishNumsByMap(Map<String, Object> map);

	public abstract Double getTradingByMap(Map<String, Object> map);

	public abstract List<ModelTaskVo> getFinishTasksByMap(
			Map<String, Object> map);

	public abstract Long getCheckerNumOfTask(Map<String, Object> map);

	public abstract Long getNewTaskNums(Map<String, Object> map);

	public abstract Long getRunTaskNums(Map<String, Object> map);

	public abstract Long getFinishTaskNums(Map<String, Object> map);

	public abstract Page page4(Page page, ModelTaskVo task);

	public abstract ModelType getModelTypeById(Long model_type_id);

	public abstract Long getCurrentCheckNum(Map<String, Object> mp);

	public abstract Date getCheckDate(Map<String, Object> mp);

	public abstract Date getFinishDateOfProject(Map<String, Object> map);

	public abstract Double getUseMoneyOfProject(Map<String, Object> map);

	public abstract Date getPublishTimeOfProject(Map<String, Object> mapIds);

	public abstract Long getNewTaskNums1(Map<String, Object> map);


}