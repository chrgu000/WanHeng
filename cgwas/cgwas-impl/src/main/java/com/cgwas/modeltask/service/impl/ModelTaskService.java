package com.cgwas.modeltask.service.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cgwas.auditRecord.entity.AuditRecordVo;
import com.cgwas.clazz.entity.Clazz;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.companySoftware.dao.api.ICompanySoftwareDao;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.companySoftware.service.api.ICompanySoftwareService;
import com.cgwas.degree.entity.Degree;
import com.cgwas.evaluation.entity.EvaluationVo;
import com.cgwas.modeltask.dao.api.IModelTaskDao;
import com.cgwas.modeltask.entity.ModelTask;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.modeltype.entity.ModelType;
import com.cgwas.notice.entity.Notice;
import com.cgwas.project.dao.api.IProjectDao;
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
import com.cgwas.util.ExcelImportUtil;

/**
 * Author yangjun
 */
@Service
public class ModelTaskService implements IModelTaskService {
	@Autowired
	private IModelTaskDao modelTaskDao;
	@Autowired
	private ICompanySoftwareDao companySoftwareDao;
	@Autowired
	private ICompanySoftwareService companySoftwareService;
	@Autowired
	private IProjectDao projectDao;
	@Autowired
	private ICompanyFilesService companyFilesService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Serializable save(ModelTaskVo modelTask) {
		return modelTaskDao.save(modelTask);
	}

	@Override
	public void delete(ModelTaskVo modelTask) {
		modelTaskDao.delete(modelTask);
	}

	@Override
	public void deleteByExample(ModelTaskVo modelTask) {
		modelTaskDao.deleteByExample(modelTask);
	}

	@Override
	public void update(ModelTaskVo modelTask) {
		modelTaskDao.update(modelTask);
	}

	@Override
	public void updateIgnoreNull(ModelTaskVo modelTask) {
		modelTaskDao.updateIgnoreNull(modelTask);
	}

	@Override
	public void updateByExample(ModelTaskVo modelTask) {
		modelTaskDao.update("updateByExampleModelTask", modelTask);
	}

	@Override
	public ModelTaskVo load(ModelTask modelTask) {
		return (ModelTaskVo) modelTaskDao.reload(modelTask);
	}

	@Override
	public ModelTaskVo selectForObject(ModelTaskVo modelTask) {
		return (ModelTaskVo) modelTaskDao.selectForObject("selectModelTask",
				modelTask);
	}

	@Override
	public List<ModelTaskVo> selectForList(ModelTaskVo modelTask) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"selectModelTask", modelTask);
	}

	@Override
	public Page page(Page page, ModelTaskVo modelTask) {
		return modelTaskDao.page(page, modelTask);
	}

	@Autowired
	public void setIModelTaskDao(
			@Qualifier("modelTaskDao") IModelTaskDao modelTaskDao) {
		this.modelTaskDao = modelTaskDao;
	}

	@Override
	@Transactional
	public Result update(ModelTaskVo modelTask, String beginTime,
			String endTime, String softwares) {
		String errorInfo = "";
		modelTask.setModified_time(new Date());
		try {
			modelTask.setBegin_time(sdf.parse(beginTime));
			modelTask.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			errorInfo += "时间格式错误,请按照yyyy-MM-dd HH:mm:ss格式录入时间\n";
			return new Result(Result.FAILURE, errorInfo, null);
		}
		modelTaskDao.updateIgnoreNull(modelTask);
		Map<String, Long> m = new HashMap<String, Long>();
		m.put("task_id", modelTask.getId());
		modelTaskDao.deleteCompanySoftware(m);
		Map<String, Object> map = new HashMap<String, Object>();
		List<CompanySoftwareVo> cs = JSON.parseArray(softwares,
				CompanySoftwareVo.class);
		for (CompanySoftwareVo c : cs) {
			if (c.getId() == null) {// 当软件id为空时，表示，该软件是新添加的
				Map<String, Object> sMap = new HashMap<String, Object>();
				sMap.put("softwareName", c.getSoftware_name());
				sMap.put("company_id", modelTask.getCompany_id());
				Long id = (Long) companySoftwareDao.selectForObject(
						"getSoftwareId", sMap);// 根据软件名称查看该软件在
				if (id == null) {// 数据表中是否存在，不存在则添加进去
					CompanySoftwareVo csv = new CompanySoftwareVo();
					csv.setSoftware_name(c.getSoftware_name());
					csv.setCompany_id(modelTask.getCompany_id());
					companySoftwareService.save(csv);// 保存软件至数据表中
					map.put("software_id", csv.getId());
				} else {
					map.put("software_id", id);
				}
			} else {
				map.put("software_id", c.getId());
			}
			map.put("project_id", modelTask.getId());
			map.put("model_type", "m_task");
			projectDao.addProjectCompanySoftware(map);// 将软件保存到项目与软件的中间表中
		}

		if (errorInfo.trim().length() == 0) {
			return new Result(Result.SUCCESS, "保存成功!", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}
	}

	@Override
	@Transactional
	public Result create(ModelTaskVo modelTask, String beginTime,
			String endTime, String softwares, String is_parent_poject,
			HttpSession session, CompanyFilesVo vo) {
		String errorInfo = "";
		modelTask.setCreate_time(new Date());
		modelTask.setModified_time(new Date());
		modelTask.setClass_id(1L);
		Long creater_id = ((User) session.getAttribute("loginUser")).getId();
		modelTask.setCreater_id(creater_id);
		Long company_id = (Long) session.getAttribute("projectCompany");
		modelTask.setCompany_id(company_id);
		try {
			modelTask.setBegin_time(sdf.parse(beginTime));
			modelTask.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			errorInfo += "时间格式错误,请按照yyyy-MM-dd HH:mm:ss格式录入时间\n";
			return new Result(Result.FAILURE, errorInfo, null);
		}
		modelTask.setIs_parent_poject(is_parent_poject);
		modelTaskDao.save(modelTask);

		/**
		 * 保存文件
		 */
		vo.setFor_id(modelTask.getId());
		if (modelTask.getCompanyFiles_id() > 0) {
			companyFilesService.updateIgnoreNull(vo);
		} else {
			companyFilesService.save(vo);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<CompanySoftwareVo> cs = JSON.parseArray(softwares,
				CompanySoftwareVo.class);
		for (CompanySoftwareVo c : cs) {
			if (c.getId() == null) {// 当软件id为空时，表示，该软件是新添加的
				Map<String, Object> sMap = new HashMap<String, Object>();
				sMap.put("softwareName", c.getSoftware_name());
				sMap.put("company_id", modelTask.getCompany_id());
				Long id = (Long) companySoftwareDao.selectForObject(
						"getSoftwareId", sMap);// 根据软件名称查看该软件在
				if (id == null) {// 数据表中是否存在，不存在则添加进去
					CompanySoftwareVo csv = new CompanySoftwareVo();
					csv.setSoftware_name(c.getSoftware_name());
					csv.setCompany_id(modelTask.getCompany_id());
					companySoftwareService.save(csv);// 保存软件至数据表中
					map.put("software_id", csv.getId());
				} else {
					map.put("software_id", id);
				}
			} else {
				map.put("software_id", c.getId());
			}
			map.put("project_id", modelTask.getId());
			map.put("model_type", "m_task");
			projectDao.addProjectCompanySoftware(map);// 将软件保存到项目与软件的中间表中
		}

		if (errorInfo.trim().length() == 0) {
			return new Result(Result.SUCCESS, "添加成功!", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}

	}

	@Override
	public List<ModelType> getModelTypes() {
		return modelTaskDao.getModelTypes();
	}

	@Override
	public List<PublishType> getPublishTypes() {
		return modelTaskDao.getPublishTypes();
	}

	@Override
	public List<Degree> getDegrees() {
		return modelTaskDao.getDegrees();
	}

	@Override
	public ModelTaskVo getModelTaskById(ModelTaskVo m) {
		return modelTaskDao.getModelTaskById(m);
	}

	@Override
	public void deleteAll(Map<String, String[]> map) {
		modelTaskDao.deleteAll(map);
		modelTaskDao.deleteSoftware(map);
	}

	@Override
	public List<Clazz> getClazz() {
		return modelTaskDao.getClazz();
	}

	@Override
	@Transactional
	public String importModelTask(String filePath, Long company_id,
			Long class_id, Long project_id, String is_parent_project,
			String errorInfo, HttpSession session) {
		errorInfo += "";
		Integer num = 1;
		List<Map<String, Object>> list = ExcelImportUtil
				.importModelTask(filePath);
		for (Map<String, Object> map : list) {
			try {
				insertTask(map, company_id, class_id, project_id,
						is_parent_project, num, session);
			} catch (RuntimeException e) {
				errorInfo += e.getMessage();
			}
			num++;
		}
		if (errorInfo.trim().length() > 0) {
			throw new RuntimeException(errorInfo);
		}
		return errorInfo;
	}

	private String insertTask(Map<String, Object> map, Long company_id,
			Long class_id, Long project_id, String is_parent_project,
			Integer num, HttpSession session) {
		String errorInfo = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ModelTaskVo task = (ModelTaskVo) map.get("task");
		String beginTime = (String) map.get("beginTime");
		String endTime = (String) map.get("endTime");
		Long model_type_id = modelTaskDao.getModelTypeId(map);
		Long publish_type_id = modelTaskDao.getPublishTypeId(map);
		Long degree_id = modelTaskDao.getDegreeId(map);
		if (model_type_id == null) {
			try {
				errorInfo += "第" + num + "行:" + map.get("model_type")
						+ "模型类型不在平台中，请核实在录入数据\n";
				model_type_id.intValue();
			} catch (Exception e) {
				throw new RuntimeException(errorInfo);
			}
		}
		if (publish_type_id == null) {
			try {
				errorInfo += "第" + num + "行:" + map.get("publish_type")
						+ "发布类型不在平台中，请核实在录入数据\n";
				publish_type_id.intValue();
			} catch (Exception e) {
				throw new RuntimeException(errorInfo);
			}

		}
		if (degree_id == null) {
			try {
				errorInfo += "第" + num + "行:" + map.get("degree")
						+ "难易度不在平台中，请核实在录入数据\n";
				degree_id.intValue();
			} catch (Exception e) {
				throw new RuntimeException(errorInfo);
			}
		}
		task.setCompany_id(company_id);
		task.setProject_id(project_id);
		task.setIs_parent_poject(is_parent_project);
		task.setClass_id(class_id);
		task.setModel_type_id(model_type_id);
		task.setPublish_type_id(publish_type_id);
		task.setDegree_id(degree_id);
		task.setCreate_time(new Date());
		task.setModified_time(new Date());
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		task.setCreater_id(user_id);
		map.put("company_id", company_id);
		try {
			task.setBegin_time(sdf.parse(beginTime));
			task.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			errorInfo += "第" + num + "行:日期格式不正确,请按照yyyy-MM-dd HH:mm:ss格式\n";
			throw new RuntimeException(errorInfo);
		}

		modelTaskDao.save(task);// 将项目对象添加到数据表中
		String software = (String) map.get("software");// 获取excel中的软件
		String[] softwares = null;
		if (software.indexOf(",") != -1) {// 当软件是英文“，”隔开的，则将软件转换为string数组
			softwares = software.split(",");
		} else if (software.indexOf("，") != -1) {// 当软件是中文“，”隔开的，则将软件转换为string数组
			softwares = software.split("，");
		} else {
			softwares = new String[] { software };
		}
		for (String s : softwares) {// 遍历软件
			Map<String, Object> softwareMap = new HashMap<String, Object>();// 软件参数对象
			Map<String, Object> sMap = new HashMap<String, Object>();
			softwareMap.put("softwareName", s);// 将软件名称存入软件对象中
			softwareMap.put("company_id", company_id);// 将公司id存入软件对象中
			Long software_id = (Long) companySoftwareDao.selectForObject(
					"getSoftwareId", softwareMap);// 根据软件名称和公司id查询软件id
			if (software_id == null) {// 当软件id为空时，说明添加的软件是新的软件，需要添加到对应的数据表中
				CompanySoftwareVo csv = new CompanySoftwareVo();
				csv.setSoftware_name(s);
				csv.setCompany_id(company_id);
				companySoftwareService.save(csv);// 将软件插入对应的数据表中
				sMap.put("project_id", task.getId());
				sMap.put("software_id", csv.getId());
				sMap.put("model_type", "m_task");
				projectDao.addProjectCompanySoftware(sMap);// 将软件插入软件和项目的中间表中
			} else {// 当软件id不为空时，说明添加的软件在数据表中
				sMap.put("project_id", task.getId());
				sMap.put("software_id", software_id);
				sMap.put("model_type", "m_task");
				projectDao.addProjectCompanySoftware(sMap);// 将软件插入软件和项目的中间表中
			}
		}
		return errorInfo;
	}

	@Override
	@Transactional
	public void publishTask(Map<String, List<Long>> modelTaskMap,
			Map<String, List<Long>> animationLightTaskMap) {
		List<Long> modelIds = modelTaskMap.get("ids");
		List<Long> animationids = animationLightTaskMap.get("ids");
		if (modelIds != null && modelIds.size() > 0) {
			modelTaskDao.publishModelTask(modelTaskMap);
		}
		if (animationids != null && animationids.size() > 0) {
			modelTaskDao.publishAnimationLightTask(animationLightTaskMap);
		}

	}

	@Override
	public void publishPointTask(Map<String, List<Long>> modelTaskMap,
			Map<String, List<Long>> animationLightTaskMap) {
		List<Long> modelIds = modelTaskMap.get("ids");
		List<Long> animationids = animationLightTaskMap.get("ids");
		if (modelIds != null && modelIds.size() > 0) {
			modelTaskDao.publishPointModelTask(modelTaskMap);
		}
		if (animationids != null && animationids.size() > 0) {
			modelTaskDao.publishPointAnimationLightTask(animationLightTaskMap);
		}

	}

	@Override
	public void pointerModelTaskToUser(Map<String, Object> map) {
		modelTaskDao.pointerModelTaskToUser(map);
	}

	@Override
	public void pointerAnimationLightTaskToUser(Map<String, Object> map) {
		modelTaskDao.pointerAnimationLightTaskToUser(map);
	}

	@Override
	public void addTaskApply(TaskApply apply) {
		modelTaskDao.addTaskApply(apply);
	}

	@Override
	public void updatePointStatus(Map<String, Long> map) {
		modelTaskDao.updatePointStatus(map);
	}

	@Override
	public void updateTaskApply(TaskApply apply) {
		modelTaskDao.updateTaskApply(apply);

	}

	@Override
	public void updateModelTask(ModelTaskVo mTask) {
		modelTaskDao.updateModelTask(mTask);

	}

	@Override
	public Long getNotAgreeNums(Map<String, Object> map) {
		return modelTaskDao.getNotAgreeNums(map);
	}

	@Override
	public Long getCheekerIsNotNullNums(Map<String, Object> map) {
		return modelTaskDao.getCheekerIsNotNullNums(map);
	}

	@Override
	public void deleteTaskApply(Map<String, Object> map) {
		modelTaskDao.deleteTaskApply(map);
	}

	@Override
	public void addTaskCheck(TaskCheck check) {
		modelTaskDao.addTaskCheck(check);
	}

	@Override
	public List<ProjectSearch> getModelTaskSearch() {
		return modelTaskDao.getModelTaskSearch();
	}

	@Override
	public List<ProjectSearch> getPublishTaskSearch() {
		return modelTaskDao.getPublishTaskSearch();
	}

	@Override
	public List<ModelTaskVo> getTaskByProjectId(Map<String, Object> map) {
		return modelTaskDao.getTaskByProjectId(map);
	}

	@Override
	public List<ProjectSearch> getModelTaskCheckSearch() {
		return modelTaskDao.getModelTaskCheckSearch();
	}

	@Override
	public List<ProjectSearch> getAnimationLightTaskCheckSearch() {
		return modelTaskDao.getAnimationLightTaskCheckSearch();
	}

	@Override
	public List<ModelTaskVo> getCheckStatusTask(Map<String, Object> map) {
		return modelTaskDao.getCheckStatusTask(map);
	}

	@Override
	public Long getRecheckNum(Map<String, Object> map) {
		return modelTaskDao.getRecheckNum(map);
	}

	@Override
	public Long getMaxDegreeNum(Map<String, Object> map) {
		return modelTaskDao.getMaxDegreeNum(map);
	}

	@Override
	public Long getCurrentDegreeNum(Map<String, Object> map) {
		return modelTaskDao.getCurrentDegreeNum(map);
	}

	@Override
	public UserInfo getNextChecker(Map<String, Object> map) {
		return modelTaskDao.getNextChecker(map);
	}

	@Override
	public List<EvaluationVo> getEvaluationInfo() {
		return modelTaskDao.getEvaluationInfo();
	}

	@Override
	public Long getTaskCheckId(Map<String, Object> map) {
		return modelTaskDao.getTaskCheckId(map);
	}

	@Override
	public void updateCheckNum(Map<String, Object> map) {
		modelTaskDao.updateCheckNum(map);
	}

	@Override
	public void addAuditRecord(AuditRecordVo vo) {
		modelTaskDao.addAuditRecord(vo);
	}

	@Override
	public void addRepair(Repair repair) {
		modelTaskDao.addRepair(repair);
	}

	@Override
	public void addRepairImage(RepairImage image) {
		modelTaskDao.addRepairImage(image);

	}

	@Override
	public void updateTaskCheck(TaskCheck check) {
		modelTaskDao.updateTaskCheck(check);

	}

	@Override
	public TaskCheck getTaskCheck(Map<String, Object> map) {
		return modelTaskDao.getTaskCheck(map);
	}

	@Override
	public List<Long> getPublishModelTaskIds(
			Map<String, List<Long>> modelTaskMap) {
		return modelTaskDao.getPublishModelTaskIds(modelTaskMap);
	}

	@Override
	public List<Long> getPublishAnimationLightTaskIds(
			Map<String, List<Long>> animationLightTaskMap) {
		return modelTaskDao
				.getPublishAnimationLightTaskIds(animationLightTaskMap);
	}

	@Override
	public List<Long> getPublishPointModelTaskIds(
			Map<String, List<Long>> modelTaskMap) {
		return modelTaskDao.getPublishPointModelTaskIds(modelTaskMap);
	}

	@Override
	public List<Long> getPublishPointAnimationLightTaskIds(
			Map<String, List<Long>> animationLightTaskMap) {
		return modelTaskDao
				.getPublishPointAnimationLightTaskIds(animationLightTaskMap);
	}

	@Override
	public void repeatBackTask(Map<String, List<Long>> publishModelTaskIds,
			Map<String, List<Long>> publishAnimationLightTaskIds) {
		List<Long> modelIds = publishModelTaskIds.get("ids");
		List<Long> animationids = publishAnimationLightTaskIds.get("ids");
		if (modelIds != null && modelIds.size() > 0) {
			modelTaskDao.repeatBackModelTask(publishModelTaskIds);
		}
		if (animationids != null && animationids.size() > 0) {
			modelTaskDao
					.repeatBackAnimationLightTask(publishAnimationLightTaskIds);
		}

	}

	@Override
	public void repeatBackPointTask(
			Map<String, List<Long>> publishPointModelTaskIds,
			Map<String, List<Long>> publishPointAnimationLightTaskIds) {
		List<Long> modelIds = publishPointModelTaskIds.get("ids");
		List<Long> animationids = publishPointAnimationLightTaskIds.get("ids");
		if (modelIds != null && modelIds.size() > 0) {
			modelTaskDao.repeatBackPointModelTask(publishPointModelTaskIds);
		}
		if (animationids != null && animationids.size() > 0) {
			modelTaskDao
					.repeatBackPointAnimationLightTask(publishPointAnimationLightTaskIds);
		}

	}

	@Override
	public void updateModelTaskTimeLiness(Map<String, Object> map) {
		modelTaskDao.updateModelTaskTimeLiness(map);
	}

	@Override
	public void updateAnimationLightTaskTimeLiness(Map<String, Object> map) {
		modelTaskDao.updateAnimationLightTaskTimeLiness(map);
	}

	@Override
	public void reUpdateModelTask(ModelTaskVo mTask) {
		modelTaskDao.reUpdateModelTask(mTask);

	}

	@Override
	public void deleteModelTaskApplys(Map<String, Object> modelTaskMap) {
		modelTaskDao.deleteModelTaskApplys(modelTaskMap);

	}

	@Override
	public void deleteAnimationTaskApply(Map<String, Object> animationTaskMap) {
		modelTaskDao.deleteAnimationTaskApply(animationTaskMap);

	}

	@Override
	public Long getUserIdOfModelTaskApply(Map<String, Object> map) {

		return modelTaskDao.getUserIdOfModelTaskApply(map);
	}

	@Override
	public Long getUserIdOfAnimationTaskApply(Map<String, Object> map) {
		return modelTaskDao.getUserIdOfAnimationTaskApply(map);
	}

	@Override
	public List<ModelTaskVo> getBeginTimeBeforeNowModelTask(
			Map<String, List<Long>> modelTaskMap) {
		return modelTaskDao.getBeginTimeBeforeNowModelTask(modelTaskMap);
	}

	@Override
	public void repeatBackModelTaskById(Map<String, Long> taskMap) {
		modelTaskDao.repeatBackModelTaskById(taskMap);
	}

	@Override
	public void updateModelTaskEndTime(ModelTaskVo mTask) {
		modelTaskDao.updateModelTaskEndTime(mTask);

	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus(Map<String, Object> map) {
		return modelTaskDao.getTaskByStageOrStatus(map);
	}

	@Override
	public Long getPublushNums(Map<String, Object> map) {
		return modelTaskDao.getPublushNums(map);
	}

	@Override
	public Long getUnStartNums(Map<String, Object> map) {
		return modelTaskDao.getUnStartNums(map);
	}

	@Override
	public Long getRunningNums(Map<String, Object> map) {
		return modelTaskDao.getRunningNums(map);
	}

	@Override
	public Long getCheckNums(Map<String, Object> map) {
		return modelTaskDao.getCheckNums(map);
	}

	@Override
	public Long getWaitPayNums(Map<String, Object> map) {

		return modelTaskDao.getWaitPayNums(map);
	}

	@Override
	public Long getWaitEvalutionNums(Map<String, Object> map) {
		return modelTaskDao.getWaitEvalutionNums(map);
	}

	@Override
	public Long getFinishedNums(Map<String, Object> map) {
		return modelTaskDao.getFinishedNums(map);
	}

	@Override
	public Long getabandonNums(Map<String, Object> map) {
		return modelTaskDao.getabandonNums(map);
	}

	@Override
	public Page page1(Page page, ModelTaskVo task) {
		return modelTaskDao.page1(page, task);
	}

	@Override
	public Date getSubmitDate(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getSubmitDate", map);
	}

	@Override
	public void updatePublishTypeOfTask(Map<String, Object> map) {
		modelTaskDao.update("com.cgwas.modeltask.dao.updatePublishTypeOfTask",
				map);

	}

	@Override
	public List<Notice> getNoticeInfo(Notice notice) {
		return (List<Notice>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getNoticeInfo", notice);
	}

	@Override
	public ModelTaskVo getDetails(Long task_id) {
		return (ModelTaskVo) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getDetails", task_id);
	}

	@Override
	public Page page2(Page page, Map<String, Object> map) {
		return modelTaskDao.page2(page, map);
	}

	@Override
	public Date getModelTaskBeginTime(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getModelTaskBeginTime", map);
	}

	@Override
	public Date getModelTaskEndTime(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getModelTaskEndTime", map);
	}

	@Override
	public Date getModelTaskActualBeginTime(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getModelTaskActualBeginTime", map);
	}

	@Override
	public Long getTaskTotal(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getTaskTotal", map);
	}

	@Override
	public Long getTaskFinishedTotal(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getTaskFinishedTotal", map);
	}

	@Override
	public Date getModelTaskActualEndTime(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getModelTaskActualEndTime", map);
	}

	@Override
	public Page page3(Page page, Map<String, Object> map) {
		return modelTaskDao.page3(page, map);
	}

	@Override
	public Date getModelTaskActualEndTime(Long id) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getModelTaskActualEndTime1", id);
	}

	@Override
	public Long getTaskOfSomeInfoNums(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getTaskOfSomeInfoNums", map);
	}

	@Override
	public Long getPointTaskNums(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getPointTaskNums", map);
	}

	@Override
	public Long getGetTaskNums(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getGetTaskNums", map);
	}

	@Override
	public Long getTaskClassNums(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getTaskClassNums", map);
	}

	@Override
	public Long getIsDrawOfMe(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getIsDrawOfMe", map);
	}

	@Override
	public Date getBeginTime(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getBeginTime", map);
	}

	@Override
	public Date getEndTime(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getEndTime", map);
	}

	@Override
	public Date getActualBeginTime(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getActualBeginTime", map);
	}

	@Override
	public Date getActualEndTime(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getActualEndTime", map);
	}

	@Override
	public List<UserInfo> getUserInfos(Map<String, Object> mp) {
		return (List<UserInfo>) modelTaskDao.selectForList("getUserInfos", mp);

	}

	@Override
	public Long getRoleIdByUserId(Long user_id) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getRoleIdByUserId", user_id);
	}

	@Override
	public List<Map<String, Long>> getJoinProjectsByUserId(Long user_id) {
		return (List<Map<String, Long>>) modelTaskDao.selectForList(
				"getJoinProjectsByUserId", user_id);
	}

	@Override
	public Long getIsMaker(Long user_id) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getIsMaker", user_id);
	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus1(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskByStageOrStatus1", map);
	}

	@Override
	public List<Long> getCompanysByUserId(Long user_id) {
		return (List<Long>) modelTaskDao.selectForList("getCompanysByUserId",
				user_id);
	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus2(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskByStageOrStatus2", map);
	}

	@Override
	public Long getIsChecker(Map<String, Object> mp) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getIsChecker", mp);
	}

	@Override
	public List<ModelTaskVo> getCheckStatusTasks(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getCheckStatusTasks", map);
	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus3(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskByStageOrStatus3", map);
	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus4(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskByStageOrStatus4", map);
	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus5(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskByStageOrStatus5", map);
	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus6(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskByStageOrStatus6", map);
	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus7(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskByStageOrStatus7", map);
	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatusCompany(
			Map<String, Object> map) {
		System.out.println(map);
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskByStageOrStatusCompany", map);

	}

	@Override
	public List<ModelTaskVo> getTaskByStageOrStatus0(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskByStageOrStatus0", map);
	}

	@Override
	public Long getTaskId(ModelTaskVo modelTask) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getTaskId", modelTask);
	}

	@Override
	public List<Long> getTaskIds(CompanyFilesVo v) {
		return (List<Long>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskIds", v);
	}

	@Override
	public Long getPublishTaskNums(Long company_id) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getPublishTaskNums", company_id);
	}

	@Override
	public Long getSuccessTaskNums(Long company_id) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getSuccessTaskNums", company_id);
	}

	@Override
	public Long getSuccessTaskNumsOfUser(Long user_id) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getSuccessTaskNumsOfUser", user_id);
	}

	@Override
	public Long getIsCheckerOrNot(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getIsCheckerOrNot", map);
	}

	@Override
	public Double getProjectPaid(Map<String, Object> mapIds) {
		return (Double) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getProjectPaid", mapIds);
	}

	@Override
	public Repair getRepairInfo(Map<String, Object> map) {
		return (Repair) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getRepairInfo", map);
	}

	@Override
	public List<ModelTaskVo> getTasksByIds(Map<String, String[]> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTasksByIds", map);
	}

	@Override
	public void reBackTask(Long task_id) {
		modelTaskDao.update("com.cgwas.modeltask.dao.reBackTask", task_id);
	}

	@Override
	public Long isChecker(Long user_id) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.isChecker", user_id);
	}

	@Override
	public Long getIsGroupMemeber(Map<String, Object> mp1) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getIsGroupMemeber", mp1);
	}

	@Override
	public Long getTaskApply(TaskApply apply) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getTaskApply", apply);
	}

	@Override
	public Long getIsMakerOfGroup(Map<String, Object> m) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getIsMakerOfGroup", m);
	}

	@Override
	public List<String> getCheckersOfTask(Map<String, Object> mp) {
		return (List<String>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getCheckersOfTask", mp);
	}

	@Override
	public String getCurrentChecker(Map<String, Object> mp) {
		return (String) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getCurrentChecker", mp);
	}

	@Override
	public Long hasGroupMemeber(Map<String, Object> mp) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.hasGroupMemeber", mp);
	}

	@Override
	public void addProof(Proof proof) {
		modelTaskDao.addProof(proof);

	}

	@Override
	public void addProofImage(ProofImage image) {
		modelTaskDao.addProofImage(image);
	}

	@Override
	public Long getRunningNumsOfProject(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getRunningNumsOfProject", map);
	}

	@Override
	public Long getCheckNumsOfProject(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getCheckNumsOfProject", map);
	}

	@Override
	public Long getFinishedNumsOfProject(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getFinishedNumsOfProject", map);
	}

	@Override
	public Long getabandonNumsOfProject(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getabandonNumsOfProject", map);
	}

	@Override
	public Long getTaskTotalOfProject(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getTaskTotalOfProject", map);
	}

	@Override
	public Date getRepairTime(Map<String, Object> m) {
		return  (Date)modelTaskDao.selectForObject("com.cgwas.modeltask.dao.getRepairTime", m);
	}

	@Override
	public TaskRecord getTaskRecord(Map<String, Object> map) {
		return  (TaskRecord)modelTaskDao.selectForObject("com.cgwas.modeltask.dao.getTaskRecord",map);
	}

	@Override
	public ModelTaskVo getReceivingModelTaskById(Long task_id) {
		return  (ModelTaskVo)modelTaskDao.selectForObject("com.cgwas.modeltask.dao.getReceivingModelTaskById",task_id);
	}

	@Override
	public void addTaskRecord(TaskRecord t) {
		modelTaskDao.addTaskRecord(t);
	}

	@Override
	public Date getCommitTime(Map<String, Object> map) {
		return  (Date)modelTaskDao.selectForObject("com.cgwas.modeltask.dao.getCommitTime",map);
	}

	@Override
	public List<Long> getTaskNumsOfUser(Long id) {
		return (List<Long>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getTaskNumsOfUser",id);
	}

	@Override
	public Long getFinishedNumsOfUser(Map<String, Object> dMap) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getFinishedNumsOfUser",dMap);
	}

	@Override
	public Long getOutTaskEndTimeNums(Map<String, Object> dMap) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getOutTaskEndTimeNums",dMap);
	}

	@Override
	public Long getPublishNumsByMap(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getPublishNumsByMap",map);
	}

	@Override
	public Double getTradingByMap(Map<String, Object> map) {
		return (Double) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getTradingByMap",map);
	}

	@Override
	public List<ModelTaskVo> getFinishTasksByMap(Map<String, Object> map) {
		return (List<ModelTaskVo>) modelTaskDao.selectForList(
				"com.cgwas.modeltask.dao.getFinishTasksByMap", map);
	}

	@Override
	public Long getCheckerNumOfTask(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getCheckerNumOfTask",map);
	}

	@Override
	public Long getNewTaskNums(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getNewTaskNums",map);
	}

	 

	@Override
	public Long getFinishTaskNums(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getFinishTaskNums",map);
	}

	@Override
	public Long getRunTaskNums(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getRunTaskNums",map);
	}

	@Override
	public Page page4(Page page, ModelTaskVo task) {
		return modelTaskDao.page4(page, task);
	}

	@Override
	public ModelType getModelTypeById(Long model_type_id) {
		return modelTaskDao.getModelTypeById(model_type_id);
	}

	@Override
	public Long getCurrentCheckNum(Map<String, Object> mp) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getCurrentCheckNum",mp);
	}

	@Override
	public Date getCheckDate(Map<String, Object> mp) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getCheckDate",mp);
	}

	@Override
	public Date getFinishDateOfProject(Map<String, Object> map) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getFinishDateOfProject",map);
	}

	@Override
	public Double getUseMoneyOfProject(Map<String, Object> map) {
		return (Double) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getUseMoneyOfProject",map);
	}

	@Override
	public Date getPublishTimeOfProject(Map<String, Object> mapIds) {
		return (Date) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getPublishTimeOfProject",mapIds);
	}

	@Override
	public Long getNewTaskNums1(Map<String, Object> map) {
		return (Long) modelTaskDao.selectForObject(
				"com.cgwas.modeltask.dao.getNewTaskNums1",map);
	}
}
