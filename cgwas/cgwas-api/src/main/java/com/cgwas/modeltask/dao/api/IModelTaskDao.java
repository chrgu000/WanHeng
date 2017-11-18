package com.cgwas.modeltask.dao.api;

import java.util.List;
import java.util.Map;

import com.cgwas.auditRecord.entity.AuditRecordVo;
import com.cgwas.clazz.entity.Clazz;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.degree.entity.Degree;
import com.cgwas.evaluation.entity.EvaluationVo;
import com.cgwas.modeltask.entity.ModelTask;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltype.entity.ModelType;
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
*  Author yangjun
*/
public interface IModelTaskDao extends IDaoSupport {
	Page page(Page page, ModelTaskVo modelTask);

	List<ModelType> getModelTypes();

	List<PublishType> getPublishTypes();

	List<Degree> getDegrees();

	ModelTaskVo getModelTaskById(ModelTaskVo m);

	void deleteCompanySoftware(Map<String, Long> m);

	void deleteAll(Map<String, String[]> map);

	void deleteSoftware(Map<String, String[]> map);

	List<Clazz> getClazz();

	Long getModelTypeId(Map<String, Object> map);

	Long getPublishTypeId(Map<String, Object> map);

	Long getDegreeId(Map<String, Object> map);

	void publishModelTask(Map<String, List<Long>> modelTaskMap);

	void publishAnimationLightTask(Map<String, List<Long>> animationLightTaskMap);

	void pointerModelTaskToUser(Map<String, Object> map);

	void pointerAnimationLightTaskToUser(Map<String, Object> map);

	void addTaskApply(TaskApply apply);

	void updatePointStatus(Map<String, Long> map);

	void publishPointModelTask(Map<String, List<Long>> modelTaskMap);

	void publishPointAnimationLightTask(
			Map<String, List<Long>> animationLightTaskMap);

	void updateTaskApply(TaskApply apply);

	void updateModelTask(ModelTaskVo mTask);

	Long getNotAgreeNums(Map<String, Object> map);

	Long getCheekerIsNotNullNums(Map<String, Object> map);

	void deleteTaskApply(Map<String, Object> map);

	void addTaskCheck(TaskCheck check);

	List<ProjectSearch> getModelTaskSearch();

	List<ProjectSearch> getPublishTaskSearch();

	List<ModelTaskVo> getTaskByProjectId(Map<String, Object> map);

	List<ProjectSearch> getModelTaskCheckSearch();

	List<ProjectSearch> getAnimationLightTaskCheckSearch();

	List<ModelTaskVo> getCheckStatusTask(Map<String, Object> map);

	Long getRecheckNum(Map<String, Object> map);

	Long getMaxDegreeNum(Map<String, Object> map);

	Long getCurrentDegreeNum(Map<String, Object> map);

	UserInfo getNextChecker(Map<String, Object> map);

	List<EvaluationVo> getEvaluationInfo();

	Long getTaskCheckId(Map<String, Object> map);

	void updateCheckNum(Map<String, Object> map);

	void addAuditRecord(AuditRecordVo vo);

	void addRepair(Repair repair);

	void addRepairImage(RepairImage image);

	void updateTaskCheck(TaskCheck check);

	TaskCheck getTaskCheck(Map<String, Object> map);

	List<Long> getPublishModelTaskIds(Map<String, List<Long>> modelTaskMap);

	List<Long> getPublishAnimationLightTaskIds(
			Map<String, List<Long>> animationLightTaskMap);

	List<Long> getPublishPointModelTaskIds(Map<String, List<Long>> modelTaskMap);

	List<Long> getPublishPointAnimationLightTaskIds(
			Map<String, List<Long>> animationLightTaskMap);

	void repeatBackModelTask(Map<String, List<Long>> publishModelTaskIds);

	void repeatBackAnimationLightTask(
			Map<String, List<Long>> publishAnimationLightTaskIds);

	void repeatBackPointModelTask(
			Map<String, List<Long>> publishPointModelTaskIds);

	void repeatBackPointAnimationLightTask(
			Map<String, List<Long>> publishPointAnimationLightTaskIds);

	void updateModelTaskTimeLiness(Map<String, Object> map);

	void updateAnimationLightTaskTimeLiness(Map<String, Object> map);

	void reUpdateModelTask(ModelTaskVo mTask);

	void deleteModelTaskApplys(Map<String, Object> modelTaskMap);

	void deleteAnimationTaskApply(Map<String, Object> animationTaskMap);

	Long getUserIdOfModelTaskApply(Map<String, Object> map);

	Long getUserIdOfAnimationTaskApply(Map<String, Object> map);

	List<ModelTaskVo> getBeginTimeBeforeNowModelTask(
			Map<String, List<Long>> modelTaskMap);

	void repeatBackModelTaskById(Map<String, Long> taskMap);

	void updateModelTaskEndTime(ModelTaskVo mTask);

	List<ModelTaskVo> getTaskByStageOrStatus(Map<String, Object> map);

	Long getPublushNums(Map<String, Object> map);

	Long getUnStartNums(Map<String, Object> map);

	Long getRunningNums(Map<String, Object> map);

	Long getCheckNums(Map<String, Object> map);

	Long getWaitPayNums(Map<String, Object> map);

	Long getWaitEvalutionNums(Map<String, Object> map);

	Long getabandonNums(Map<String, Object> map);

	Page page1(Page page, ModelTaskVo task);

	Page page2(Page page, Map<String, Object> map);

	Page page3(Page page, Map<String, Object> map);

	Long getFinishedNums(Map<String, Object> map);

	void addProof(Proof proof);

	void addProofImage(ProofImage image);

	void addTaskRecord(TaskRecord t);

	Page page4(Page page, ModelTaskVo task);

	ModelType getModelTypeById(Long model_type_id);
 
}