package com.cgwas.modeltask.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.auditRecord.entity.AuditRecordVo;
import com.cgwas.clazz.entity.Clazz;
import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.degree.entity.Degree;
import com.cgwas.evaluation.entity.EvaluationVo;
import com.cgwas.modeltask.dao.api.IModelTaskDao;
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
import com.cgwas.userInfo.entity.UserInfo;
/**
*  Author yangjun
*/
@Service
public class ModelTaskDao extends AbstractDao implements IModelTaskDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	@Override
	public Page page4(Page page, ModelTaskVo task) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectModelTaskPage4", "selectModelTaskCount4");
		return pageQuery.select(task);
	}
	@Override
	public Page page1(Page page, ModelTaskVo task) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectModelTaskPage1", "selectModelTaskCount1");
		return pageQuery.select(task);
	}
	@Override
	public Page page(Page page, ModelTaskVo modelTask) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectModelTaskPage", "selectModelTaskCount");
		return pageQuery.select(modelTask);
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
	public List<ModelType> getModelTypes() {
		return (List<ModelType>) daoSupport.selectForList("com.cgwas.modeltask.dao.getModelTypes", null);
	}

	@Override
	public List<PublishType> getPublishTypes() {
		return (List<PublishType>) daoSupport.selectForList("com.cgwas.modeltask.dao.getPublishTypes", null);
	}
	@Override
	public List<Clazz> getClazz() {
		return (List<Clazz>) daoSupport.selectForList("com.cgwas.modeltask.dao.getClazz", null);
	}	
	@Override
	public List<Degree> getDegrees() {
		return (List<Degree>) daoSupport.selectForList("com.cgwas.modeltask.dao.getDegrees", null);
	}

	@Override
	public ModelTaskVo getModelTaskById(ModelTaskVo m) {
		return (ModelTaskVo) daoSupport.selectForObject("com.cgwas.modeltask.dao.getModelTaskById",m );
	}

	@Override
	public void deleteCompanySoftware(Map<String, Long> m) {
		daoSupport.delete("com.cgwas.modeltask.dao.deleteCompanySoftware", m);
	}

	@Override
	public void deleteAll(Map<String, String[]> map) {
		 daoSupport.delete("com.cgwas.modeltask.dao.deleteAll", map);
	}

	@Override
	public void deleteSoftware(Map<String, String[]> map) {
		 daoSupport.delete("com.cgwas.modeltask.dao.deleteSoftware", map);
	}

	@Override
	public Long getModelTypeId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getModelTypeId",map);
	}

	@Override
	public Long getPublishTypeId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getPublishTypeId",map);
	}

	@Override
	public Long getDegreeId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getDegreeId",map);
	}


	@Override
	public List<ModelTaskVo> getTaskByProjectId(Map<String, Object> map) {
		return (List<ModelTaskVo>) daoSupport.selectForList("com.cgwas.modeltask.dao.getTaskByProjectId", map);
	}

	@Override
	public void publishModelTask(Map<String, List<Long>> modelTaskMap) {
		daoSupport.update("com.cgwas.modeltask.dao.publishModelTask", modelTaskMap);
	}

	@Override
	public void publishAnimationLightTask(
			Map<String, List<Long>> animationLightTaskMap) {
		daoSupport.update("com.cgwas.modeltask.dao.publishAnimationLightTask", animationLightTaskMap);
	}

	@Override
	public void pointerModelTaskToUser(Map<String, Object> map) {
		 daoSupport.update("com.cgwas.modeltask.dao.pointerModelTaskToUser", map);
	}

	@Override
	public void pointerAnimationLightTaskToUser(Map<String, Object> map) {
         daoSupport.update("com.cgwas.modeltask.dao.pointerAnimationLightTaskToUser", map);		
	}

	@Override
	public void addTaskApply(TaskApply apply) {
		daoSupport.save("com.cgwas.modeltask.dao.addTaskApply", apply);
	}

	@Override
	public void updatePointStatus(Map<String, Long> map) {
		daoSupport.update("com.cgwas.modeltask.dao.updatePointStatus", map);		
	}

	@Override
	public void publishPointModelTask(Map<String, List<Long>> modelTaskMap) {
		daoSupport.update("com.cgwas.modeltask.dao.publishPointModelTask", modelTaskMap);
	}

	@Override
	public void publishPointAnimationLightTask(
			Map<String, List<Long>> animationLightTaskMap) {
		daoSupport.update("com.cgwas.modeltask.dao.publishPointAnimationLightTask", animationLightTaskMap);
	}

	@Override
	public void updateTaskApply(TaskApply apply) {
		daoSupport.update("com.cgwas.modeltask.dao.updateTaskApply", apply);
		
	}

	@Override
	public void updateModelTask(ModelTaskVo mTask) {
		daoSupport.update("com.cgwas.modeltask.dao.updateMTask", mTask);
		
	}

	@Override
	public Long getNotAgreeNums(Map<String, Object> map) {
		 
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getNotAgreeNums", map);
	}

	@Override
	public Long getCheekerIsNotNullNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getCheekerIsNotNullNums", map);
	}

	@Override
	public void deleteTaskApply(Map<String, Object> map) {
		daoSupport.delete("com.cgwas.modeltask.dao.deleteTaskApply", map);
		
	}

	@Override
	public void addTaskCheck(TaskCheck check) {
		daoSupport.save("com.cgwas.modeltask.dao.addTaskCheck", check);
	}

	@Override
	public List<ProjectSearch> getModelTaskSearch() {
		return (List<ProjectSearch>) daoSupport.selectForList("com.cgwas.modeltask.dao.getModelTaskSearch");
	}

	@Override
	public List<ProjectSearch> getPublishTaskSearch() {
		return (List<ProjectSearch>) daoSupport.selectForList("com.cgwas.modeltask.dao.getPublishTaskSearch");
	}

	@Override
	public List<ProjectSearch> getModelTaskCheckSearch() {
		return (List<ProjectSearch>) daoSupport.selectForList("com.cgwas.modeltask.dao.getModelTaskCheckSearch");
	}

	@Override
	public List<ProjectSearch> getAnimationLightTaskCheckSearch() {
		return (List<ProjectSearch>) daoSupport.selectForList("com.cgwas.modeltask.dao.getAnimationLightTaskCheckSearch");
	}

	@Override
	public List<ModelTaskVo> getCheckStatusTask(Map<String,Object> map) {
		return (List<ModelTaskVo>) daoSupport.selectForList("com.cgwas.modeltask.dao.getCheckStatusTask",map);
	}

	@Override
	public Long getRecheckNum(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getRecheckNum", map);
	}

	@Override
	public Long getMaxDegreeNum(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getMaxDegreeNum", map);
	}

	@Override
	public Long getCurrentDegreeNum(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getCurrentDegreeNum", map);
	}

	@Override
	public UserInfo getNextChecker(Map<String, Object> map) {
		return (UserInfo) daoSupport.selectForObject("com.cgwas.modeltask.dao.getNextChecker", map);
	}

	@Override
	public List<EvaluationVo> getEvaluationInfo() {
		return (List<EvaluationVo>) daoSupport.selectForList("com.cgwas.modeltask.dao.getEvaluationInfo");
	}

	@Override
	public Long getTaskCheckId(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getTaskCheckId", map);
	}

	@Override
	public void updateCheckNum(Map<String, Object> map) {
		daoSupport.update("com.cgwas.modeltask.dao.updateCheckNum", map);
	}

	@Override
	public void addAuditRecord(AuditRecordVo vo) {
		daoSupport.save("com.cgwas.modeltask.dao.addAuditRecord",vo);
		
	}

	@Override
	public void addRepair(Repair repair) {
		daoSupport.save("com.cgwas.modeltask.dao.addRepair",repair);
	}

	@Override
	public void addProof(Proof proof) {
		daoSupport.save("com.cgwas.modeltask.dao.addProof",proof);
	}
	@Override
	public void addRepairImage(RepairImage image) {
		daoSupport.save("com.cgwas.modeltask.dao.addRepairImage",image);
		
	}

	@Override
	public void updateTaskCheck(TaskCheck check) {
		daoSupport.update("com.cgwas.modeltask.dao.updateTaskCheck", check);
		
	}

	@Override
	public TaskCheck getTaskCheck(Map<String, Object> map) {
		return (TaskCheck) daoSupport.selectForObject("com.cgwas.modeltask.dao.getTaskCheck", map);
	}

	@Override
	public List<Long> getPublishModelTaskIds(
			Map<String, List<Long>> modelTaskMap) {
		return (List<Long>) daoSupport.selectForList("com.cgwas.modeltask.dao.getPublishModelTaskIds", modelTaskMap);
	}

	@Override
	public List<Long> getPublishAnimationLightTaskIds(
			Map<String, List<Long>> animationLightTaskMap) {
		return (List<Long>) daoSupport.selectForList("com.cgwas.modeltask.dao.getPublishAnimationLightTaskIds", animationLightTaskMap);
	}

	@Override
	public List<Long> getPublishPointModelTaskIds(
			Map<String, List<Long>> modelTaskMap) {
		return (List<Long>) daoSupport.selectForList("com.cgwas.modeltask.dao.getPublishPointModelTaskIds", modelTaskMap);
	}

	@Override
	public List<Long> getPublishPointAnimationLightTaskIds(
			Map<String, List<Long>> animationLightTaskMap) {
		return (List<Long>) daoSupport.selectForList("com.cgwas.modeltask.dao.getPublishPointAnimationLightTaskIds", animationLightTaskMap);
	}

	@Override
	public void repeatBackModelTask(Map<String, List<Long>> publishModelTaskIds) {
		daoSupport.update("com.cgwas.modeltask.dao.repeatBackModelTask",publishModelTaskIds);
		
	}

	@Override
	public void repeatBackAnimationLightTask(
			Map<String, List<Long>> publishAnimationLightTaskIds) {
		daoSupport.update("com.cgwas.modeltask.dao.repeatBackAnimationLightTask",publishAnimationLightTaskIds);
		
	}

	@Override
	public void repeatBackPointModelTask(
			Map<String, List<Long>> publishPointModelTaskIds) {
		daoSupport.update("com.cgwas.modeltask.dao.repeatBackPointModelTask",publishPointModelTaskIds);
		
	}

	@Override
	public void repeatBackPointAnimationLightTask(
			Map<String, List<Long>> publishPointAnimationLightTaskIds) {
		daoSupport.update("com.cgwas.modeltask.dao.repeatBackPointAnimationLightTask",publishPointAnimationLightTaskIds);
		
	}

	@Override
	public void updateModelTaskTimeLiness(Map<String, Object> map) {
		daoSupport.update("com.cgwas.modeltask.dao.updateModelTaskTimeLiness", map);
	}

	@Override
	public void updateAnimationLightTaskTimeLiness(Map<String, Object> map) {
		daoSupport.update("com.cgwas.modeltask.dao.updateAnimationLightTaskTimeLiness", map);
	}

	@Override
	public void reUpdateModelTask(ModelTaskVo mTask) {
		daoSupport.update("com.cgwas.modeltask.dao.reUpdateModelTask", mTask);
		
	}

	@Override
	public void deleteModelTaskApplys(Map<String, Object> modelTaskMap) {
		daoSupport.delete("com.cgwas.modeltask.dao.deleteModelTaskApplys", modelTaskMap);
		
	}

	@Override
	public void deleteAnimationTaskApply(Map<String, Object> animationTaskMap) {
		daoSupport.delete("com.cgwas.modeltask.dao.deleteAnimationTaskApply", animationTaskMap);
		
	}

	@Override
	public Long getUserIdOfModelTaskApply(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getUserIdOfModelTaskApply", map);
	}

	@Override
	public Long getUserIdOfAnimationTaskApply(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getUserIdOfAnimationTaskApply", map);
	}

	@Override
	public List<ModelTaskVo> getBeginTimeBeforeNowModelTask(
			Map<String, List<Long>> modelTaskMap) {
		return (List<ModelTaskVo>) daoSupport.selectForList("com.cgwas.modeltask.dao.getBeginTimeBeforeNowModelTask", modelTaskMap);
	}

	@Override
	public void repeatBackModelTaskById(Map<String, Long> taskMap) {
		daoSupport.update("com.cgwas.modeltask.dao.repeatBackModelTaskById", taskMap);
		
	}

	@Override
	public void updateModelTaskEndTime(ModelTaskVo mTask) {
		daoSupport.update("com.cgwas.modeltask.dao.updateModelTaskEndTime", mTask);
	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus(Map<String, Object> map) {
		return (List<ModelTaskVo>) daoSupport.selectForList("com.cgwas.modeltask.dao.getTaskByStageOrStatus", map);
	}

	@Override
	public Long getPublushNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getPublushNums",map);
	}

	@Override
	public Long getUnStartNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getUnStartNums",map);
	}

	@Override
	public Long getRunningNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getRunningNums",map);
	}

	@Override
	public Long getCheckNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getCheckNums",map);
	}

	@Override
	public Long getWaitPayNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getWaitPayNums",map);
	}

	@Override
	public Long getWaitEvalutionNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getWaitEvalutionNums",map);
	}

	@Override
	public Long getabandonNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getabandonNums",map);
	}

	@Override
	public Page page2(Page page, Map<String, Object> map) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectModelTaskPage2", "selectModelTaskCount2");
		return pageQuery.select(map);
	}

	@Override
	public Page page3(Page page, Map<String, Object> map) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectModelTaskPage3", "selectModelTaskCount3");
		return pageQuery.select(map);
	}

	@Override
	public Long getFinishedNums(Map<String, Object> map) {
		return (Long) daoSupport.selectForObject("com.cgwas.modeltask.dao.getFinishedNums",map);
	}
	@Override
	public void addProofImage(ProofImage image) {
		daoSupport.save("com.cgwas.modeltask.dao.addProofImage",image);		
	}
	@Override
	public void addTaskRecord(TaskRecord t) {
		daoSupport.save("com.cgwas.modeltask.dao.addTaskRecord",t);		
		
	}
	@Override
	public ModelType getModelTypeById(Long model_type_id) {
		return (ModelType) daoSupport.selectForObject("com.cgwas.modeltask.dao.getModelTypeById",model_type_id);		
	}
}