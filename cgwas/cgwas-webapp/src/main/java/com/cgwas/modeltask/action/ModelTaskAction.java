package com.cgwas.modeltask.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.animationlighttask.entity.AnimationLightTask;
import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfo;
import com.cgwas.arbitrateInfo.service.api.IArbitrateInfoService;
import com.cgwas.auditRecord.entity.AuditRecord;
import com.cgwas.auditRecord.entity.AuditRecordVo;
import com.cgwas.auditRecord.service.api.IAuditRecordService;
import com.cgwas.clazz.entity.Clazz;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.FileUtils;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.companyEvaluation.entity.CompanyEvaluation;
import com.cgwas.companyEvaluation.service.api.ICompanyEvaluationService;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.companySoftware.service.api.ICompanySoftwareService;
import com.cgwas.degree.entity.Degree;
import com.cgwas.degree.service.api.IDegreeService;
import com.cgwas.evaluation.entity.Evaluation;
import com.cgwas.evaluation.entity.EvaluationVo;
import com.cgwas.evaluation.service.api.IEvaluationService;
import com.cgwas.freezeRecord.entity.FreezeRecord;
import com.cgwas.freezeRecord.service.api.IFreezeRecordService;
import com.cgwas.imgInfo.service.api.IImgInfoService;
import com.cgwas.message.service.api.ISendMessageService;
import com.cgwas.modeltask.entity.ModelTask;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.modeltype.entity.ModelType;
import com.cgwas.notice.entity.Notice;
import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.project.entity.Project;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.projectSearch.entity.ProjectSearch;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.proof.entity.Proof;
import com.cgwas.proofimage.entity.ProofImage;
import com.cgwas.publishtype.entity.PublishType;
import com.cgwas.repair.entity.Repair;
import com.cgwas.repairimage.entity.RepairImage;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.entity.SubProjectVo;
import com.cgwas.subproject.service.api.ISubProjectService;
import com.cgwas.taskCheck.entity.TaskCheck;
import com.cgwas.taskCheck.service.api.ITaskCheckService;
import com.cgwas.taskapply.entity.TaskApply;
import com.cgwas.user.entity.User;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userCredibility.entity.UserCredibility;
import com.cgwas.userCredibility.service.api.IUserCredibilityService;
import com.cgwas.userEvaluation.entity.UserEvaluation;
import com.cgwas.userEvaluation.service.api.IUserEvaluationService;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.util.CalendarUtil;

/**
 * 对应任务状态的修改都要修改对应的modified_time的值，便于项目中统计用，动画灯光任务同理 Author yangjun
 */
@Scope("prototype")
@Controller
@RequestMapping("cgwas/modeltaskAction")
public class ModelTaskAction {
	@Autowired
	private IProjectService projectService;// 项目业务对象
	@Autowired
	private ISubProjectService subProjectService;// 子项目业务对象
	@Autowired
	private ITaskCheckService taskCheckService;// 审核
	@Autowired
	private IAuditRecordService auditRecordService;// 审核记录
	@Autowired
	private IDegreeService degreeService;// 难易度
	@Autowired
	private IEvaluationService evaluationService;// 评价
	@Autowired
	private IUserCredibilityService userCredibilityService;// 信誉度
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;// 动画灯光任务
	@Autowired
	private IModelTaskService modelTaskService;// 建模任务
	@Autowired
	private ICompanySoftwareService companySoftwareService;// 软件
	@Autowired
	private ICompanyFilesService companyFilesService;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private IFreezeRecordService freezeRecordService;
	@Autowired
	private ICompanyEvaluationService companyEvaluationService;
	@Autowired
	private IUserEvaluationService userEvaluationService;
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;
	@Autowired
	private ISendMessageService sendMessageService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Map<String, List<Long>> publishModelTaskIds = new HashMap<String, List<Long>>();// 发布阶段发布中状态的建模任务id集合
	private Map<String, List<Long>> publishAnimationLightTaskIds = new HashMap<String, List<Long>>();// 发布阶段发布中状态的发布动画灯光任务id集合
	private Map<String, List<Long>> publishPointModelTaskIds = new HashMap<String, List<Long>>();// 发布阶段指派中状态的建模任务id集合
	private Map<String, List<Long>> publishPointAnimationLightTaskIds = new HashMap<String, List<Long>>();// 发布阶段指派中状态动画灯光任务id集合
	private Map<String, Long> taskMap = new HashMap<String, Long>();// 同意任务的id，包括建模动画灯光任务
	private Map<String, Object> evaluationTaskMap = new HashMap<String, Object>();
	@Autowired
	private IArbitrateInfoService arbitrateInfoService;
	@Autowired
	private IImgInfoService imgInfoService;

	// public static void main(String[] rags) {
	// LinkedHashSet<ModelTaskVo> set = new LinkedHashSet<ModelTaskVo>();
	// List<ModelTaskVo> l1 = new ArrayList<ModelTaskVo>();
	// List<ModelTaskVo> l2 = new ArrayList<ModelTaskVo>();
	// Clazz clz = new Clazz();
	// clz.setId(1L);
	// ModelTaskVo m1=new ModelTaskVo();
	// m1.setId(1L);
	// m1.setClazz(clz);
	// ModelTaskVo m2=new ModelTaskVo();
	// m2.setId(2L);
	// m2.setClazz(clz);
	// ModelTaskVo m3=new ModelTaskVo();
	// m3.setId(3L);
	// m3.setClazz(clz);
	// ModelTaskVo m4=new ModelTaskVo();
	// m4.setId(1L);
	// m4.setClazz(clz);
	// m4.setDraw(true);
	// ModelTaskVo m5=new ModelTaskVo();
	// m5.setId(2L);
	// m5.setClazz(clz);
	// m5.setDraw(true);
	// ModelTaskVo m6=new ModelTaskVo();
	// m6.setId(3L);
	// m6.setClazz(clz);
	// m6.setDraw(true);
	// l1.add(m1);
	// l1.add(m2);
	// l1.add(m3);
	// l2.add(m4);
	// l2.add(m5);
	// l2.add(m6);
	// set.addAll(l2);
	// set.addAll(l1);
	// for (ModelTaskVo m : set) {
	// System.out.println(m.getId() + "," + m.getClazz().getId() + ", 1"
	// + m.isDraw());
	// }
	// }
	//
	@RequestMapping("/getAllTasksOfProject")
	@ResponseBody
	public Result getAllTasksOfProject(String data, Long project_id,
			Long sub_project_id, HttpServletRequest request, String type,
			HttpSession session) {
		// data =
		// "{'page':{'pageSize':'20','pageNo':'1'},'object':{'sort':'desc','field':'getNums','project_id':'','sub_project_id':'6','clazz_id':'1','degree_id':'1','stage':'已完成'}}";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject param = JSON.parseObject(data);
			JSON object = (JSON) JSON.toJSON(param.get("object"));
			ModelTaskVo task = new ModelTaskVo();
			ProjectSearchVo search = JSONObject.toJavaObject(object,
					ProjectSearchVo.class);// 搜索信息对象
			System.out.println("search:type:" + type + ",project_id:"
					+ search.getProject_id() + ",sub_project_id:"
					+ sub_project_id);
			if (search.getProject_id() != null
					&& !search.getProject_id().equals("")
					&& "parent".equals(type)) {
				Map<String, Object> mp = getSubProjectIdsOfParent(search
						.getProject_id());
				if (map != null) {
					search.setIdsInfo(mp);
					search.setSub_project_id(null);
				}
			}
			task.setPsearch(search);
			System.out.println(search);
			Map<String, String> map1 = (Map<String, String>) param.get("page");
			Page page = PageUtils.createPage(map1);
			page = modelTaskService.page4(page, task);
			List<ModelTaskVo> mts = (List<ModelTaskVo>) page.getDataList();
			for (ModelTaskVo m : mts) {
				if (m.getClazz().getId() == 1) {
					ModelType mt = modelTaskService.getModelTypeById(m
							.getModel_type_id());
					m.setModeltype(mt);
				}
				if (!"待评价".equals(m.getStage())
						&& !"待评价".equals(m.getCompany_stage())
						&& !"已完成".equals(m.getStage())
						&& !"已完成".equals(m.getCompany_stage()))
					setFreeEndDay(m);
			}
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", mts);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Result.SUCCESS, "成功", map);
	}

	private void setFreeEndDay(ModelTaskVo m) {
		Date date = m.getEnd_time();
		Date now = new Date();
		Long times = date.getTime() - now.getTime();
		if (times > 0) {
			double t = times * 1.0 / (1000 * 60 * 60 * 24);
			m.setFreeEndDay(Math.round(t));
		}
	}

	/**
	 * 建模任务列表
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Result list(String data, HttpServletRequest request,
			HttpSession session) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
//		 data =
//		 "{'page':{'pageSize':'100','pageNo':'1'},'object':{'sort':'asc','field':'','search':''}}";
		try {
			JSONObject param = JSON.parseObject(data);
			JSON object = (JSON) JSON.toJSON(param.get("object"));
			ModelTaskVo task = new ModelTaskVo();
			ProjectSearchVo search = JSONObject.toJavaObject(object,
					ProjectSearchVo.class);// 搜索信息对象
			Long role_id = modelTaskService.getRoleIdByUserId(user_id);
			System.out.println("list_role_id:" + role_id);
			List<Long> parentIds = new ArrayList<Long>();
			List<Long> subIds = new ArrayList<Long>();
			if (role_id != null && role_id.equals(2L)) {// 操作者代表公司
				map.put("company_id", company_id);
			} else if (role_id != null && role_id.equals(3L)) {// 操作者代表会员，需要进一步判断是公司员工或者是制作者。
				List<Map<String, Long>> projectMaps = modelTaskService
						.getJoinProjectsByUserId(user_id);
				System.out.println("projectMaps:" + projectMaps);
				if (projectMaps.size() > 0) {
					for (Map<String, Long> m : projectMaps) {
						if (m.get("is_parent_project").equals(1L)) {
							Map<String, Object> m1 = getSubProjectIdsOfParent(m
									.get("project_id"));
							if (m1.get("is_parent").equals(1)) {
								parentIds.addAll((List<Long>) m1.get("ids"));
							} else {
								subIds.addAll((List<Long>) m1.get("ids"));
							}
						}
					}
					for (Map<String, Long> m : projectMaps) {
						if (m.get("is_parent_project").equals(0L)) {
							Map<String, Object> m1 = getSubProjectIdsOfSub(m
									.get("project_id"));
							if (!subIds.containsAll((List<Long>) m1.get("ids"))) {
								subIds.addAll((List<Long>) m1.get("ids"));
							}
						}
					}
				}
				Iterator<Long> its = subIds.iterator();
				while (its.hasNext()) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("project_id", its.next());
					m.put("is_parent_project", "0");
					m.put("user_id", user_id);
					Long isMaker = modelTaskService.getIsMakerOfGroup(m);
					if (isMaker != null && isMaker > 0) {
						its.remove();
					}
				}
				map.put("parentIds", parentIds);
				map.put("subIds", subIds);
				System.out.println("parentIds:" + parentIds + ",subIds:"
						+ subIds);
				// // 根据userId往员工表里面该员工所属哪几个公司，查看是否是公司员工。
				List<Long> companyIds = modelTaskService
						.getCompanysByUserId(user_id);
				System.out.println("companyIds:" + companyIds);
				map.put("companyIds", companyIds);
				if (companyIds.size() == 0 && parentIds.size() == 0
						&& subIds.size() == 0) {
					// 当前操作者是制作者：制作者能看到全部外部任务和指派给自己的指派任务
					System.out.println("---------------1----------------");
				} else if (companyIds.size() > 0 && parentIds.size() == 0
						&& subIds.size() == 0) {
					// 当前操作者是公司员工：可以看到发布中的所有外部任务和自己所属公司的内部任务和指派给自己的任务
					System.out
							.println("----------------6ok--------------------");
				} else if (companyIds.size() == 0
						&& (parentIds.size() > 0 || subIds.size() > 0)) {
					// 当前操作者是团队成员：可以看到团队所参与项目的所有任务
					System.out
							.println("-----------------5ok-------------------");
				} else if (companyIds.size() > 0
						&& (parentIds.size() > 0 || subIds.size() > 0)) {
					// 当前操作者既是公司员工又是团队成员:可以看到发布中的所有外部任务和自己所属公司的内部任务和指派任务以及团队所参与项目的内部任务和指派任务
					// 可以看到团队所参与项目的所有非发布中任务
					System.out
							.println("----------------------7ok--------------------");
				}

			}
			if (search.getProject_id() == null) {
				search.setUser_id(user_id);
			}
			task.setPsearch(search);
			System.out.println("list_map:" + map);
			task.setMap(map);
			Map<String, String> map1 = (Map<String, String>) param.get("page");
			Page page = PageUtils.createPage(map1);
			page = modelTaskService.page(page, task);
			List<ModelTaskVo> mts = (List<ModelTaskVo>) page.getDataList();
			List<Long> ids=new ArrayList<Long>();
			for (ModelTaskVo m : mts) {
				Map<String, Object> mm = new HashMap<String, Object>();
				mm.put("user_id", user_id);
				mm.put("project_id", m.getProject_id());
				mm.put("is_parent_project", m.getIs_parent_poject());
				mm.put("task_id", m.getId());
				mm.put("task_type", "建模");
				Long isCheckerOfCurrentTask =null;
				if("审核中".equals(m.getStage())){
					isCheckerOfCurrentTask= modelTaskService
						.getIsCheckerOrNot(mm);
					if(isCheckerOfCurrentTask==null){
						ids.add(m.getId());
					}
				}
				if (role_id == 2) {
					m.setObserver("company");
				} else {
					if (isCheckerOfCurrentTask != null) {
						m.setObserver("checker");
					}
					if (!"checker".equals(m.getObserver())) {
						if (m.getIs_parent_poject().equals("1")
								&& parentIds.contains(m.getProject_id())) {
							m.setObserver("company");
						} else if (m.getIs_parent_poject().equals("0")
								&& subIds.contains(m.getProject_id())) {
							m.setObserver("company");
						} else {
							m.setObserver("maker");
						}
					}
				}
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("task_id", m.getId());
				// if (m.getStage()!=null&&m.getStage().equals("进行中")
				// && !m.getTask_status().equals("收回中")
				// && m.getEnd_time().before(new Date())) {
				// ModelTaskVo m1 = new ModelTaskVo();
				// m1.setId(m.getId());
				// m1.setTask_status("收回中");
				// m.setTask_status("收回中");
				// modelTaskService.updateModelTask(m1);
				// mp.put("task_type", "建模");
				// }
				mp.put("user_id", user_id);
				if (m.getStage() != null && m.getStage().equals("发布中")) {
					List<UserInfo> userInfos = modelTaskService
							.getUserInfos(mp);
					Long id = modelTaskService.getIsDrawOfMe(mp);
					if (id != null) {
						m.setDraw(true);
					} else {
						m.setDraw(false);
					}
					if (userInfos.size() > 1) {
						m.setMorePeople(true);
					} else {
						m.setMorePeople(false);
					}
					m.setUserInfos(userInfos);
				}
				if (m.getIs_parent_poject().equals("1")) {
					m.setProject(projectService.getProjectById(m
							.getProject_id()));
				} else {
					m.setSubProject(subProjectService.getSubProjectById(m
							.getProject_id()));
				}
			}
			if(ids.size()>0){
				Iterator<ModelTaskVo> modelTasks=mts.iterator();
				while(modelTasks.hasNext()){
					ModelTaskVo v=modelTasks.next();
					if(ids.contains(v.getId())){
						modelTasks.remove();
					}
				}
			}
			System.out.println(search);
			for (ModelTaskVo m : mts) {
				if (m.getMaker() != null) {
					System.out.println("id:" + m.getId() + ",stage:"
							+ m.getStage() + ",task_status:"
							+ m.getTask_status() + ",user_id:"
							+ m.getMaker().getUser_id() + "class_id:"+m.getClass_id()+",size:" + mts.size()
							+ ",project_id:" + m.getProject_id()
							+ ",is_parent_project:" + m.getIs_parent_poject()
							+ ",observer:" + m.getObserver());
				} else {
					System.out.println("id:" + m.getId() + ",stage:"
							+ m.getStage() + ",task_status:"
							+ m.getTask_status() + ",user_id:" + m.getUser_id()+ "class_id:"+m.getClass_id()+",size:" + mts.size() + ",project_id:"
							+ m.getProject_id() + ",is_parent_project:"
							+ m.getIs_parent_poject() + ",observer:"
							+ m.getObserver());
				}
			}
			map.put("total", page.getTotal()-ids.size());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", mts);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Result.SUCCESS, "成功", map);
	}

	@RequestMapping("/getTaskByStageOrStatus")
	@ResponseBody
	public Result getTaskByStageOrStatus(String status, String stage,
			Long class_id, String beginTime, HttpSession session, String field,
			String sort, Long project_id, String is_parent_project)
			throws Exception {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		if (session.getAttribute("loginUser") == null) {
			return new Result("RY0008", null);
		}
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		Long role_id = modelTaskService.getRoleIdByUserId(user_id);
		System.out.println("getTaskByStageOrStatus_role_id:" + role_id);
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
		if (beginTime != null && !"".equals(beginTime)) {
			Date begin = sdf.parse(beginTime);
			beginTime = sdf1.format(begin);
			map.put("begin_time", beginTime);
		}
		map.put("class_id", class_id);
		map.put("stage", stage);
		map.put("status", status);
		map.put("field", field);
		map.put("sort", sort);
		map.put("user_id", user_id);
		map.put("is_parent_project", is_parent_project);
		map.put("project_id", project_id);
		LinkedHashSet<ModelTaskVo> taskSet = new LinkedHashSet<ModelTaskVo>();
		List<ModelTaskVo> tasks = null;
		if (role_id != null && role_id.equals(2L)) {// 操作者代表公司
			map.put("company_id", company_id);
			tasks = modelTaskService.getTaskByStageOrStatusCompany(map);
			taskSet.addAll(tasks);
			setProjectOfTasks(taskSet);
			for (ModelTaskVo m : taskSet) {
				m.setObserver("company");
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("task_id", m.getId());
				if (m.getClazz().getId().equals(1L)) {
					mp.put("task_type", "建模");
				} else if (m.getClazz().getId().equals(2L)) {
					mp.put("task_type", "动画");
				} else if (m.getClazz().getId().equals(3L)) {
					mp.put("task_type", "灯光");
				}

				if (stage.equals("发布中")) {
					List<UserInfo> userInfos = modelTaskService
							.getUserInfos(mp);
					if (userInfos.size() > 1) {
						m.setMorePeople(true);
					} else {
						m.setMorePeople(false);
					}
					m.setUserInfos(userInfos);
				}
			}
		} else if (role_id != null && role_id.equals(3L)) {
			List<Map<String, Long>> projectMaps = modelTaskService
					.getJoinProjectsByUserId(user_id);
			System.out.println("projectMaps:" + projectMaps);
			List<Long> parentIds = new ArrayList<Long>();
			List<Long> subIds = new ArrayList<Long>();
			if (projectMaps.size() > 0) {
				for (Map<String, Long> m : projectMaps) {
					if (m.get("is_parent_project").equals(1L)) {
						Map<String, Object> m1 = getSubProjectIdsOfParent(m
								.get("project_id"));
						if (m1.get("is_parent").equals(1)) {
							parentIds.addAll((List<Long>) m1.get("ids"));
						} else {
							subIds.addAll((List<Long>) m1.get("ids"));
						}
					}
				}
				for (Map<String, Long> m : projectMaps) {
					if (m.get("is_parent_project").equals(0L)) {
						Map<String, Object> m1 = getSubProjectIdsOfSub(m
								.get("project_id"));
						if (!subIds.containsAll((List<Long>) m1.get("ids"))) {
							subIds.addAll((List<Long>) m1.get("ids"));
						}
					}
				}
			}
			Iterator<Long> its = subIds.iterator();
			while (its.hasNext()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("project_id", its.next());
				m.put("is_parent_project", "0");
				m.put("user_id", user_id);
				Long isMaker = modelTaskService.getIsMakerOfGroup(m);
				if (isMaker != null && isMaker > 0) {
					its.remove();
				}
			}
			// parentIds.clear();
			// subIds.clear();
			map.put("parentIds", parentIds);
			map.put("subIds", subIds);
			System.out.println("parentIds:" + parentIds + ",subIds:" + subIds);
			// // 根据userId往员工表里面该员工所属哪几个公司，查看是否是公司员工。
			List<Long> companyIds = modelTaskService
					.getCompanysByUserId(user_id);
			// companyIds.clear();
			System.out.println("companyIds:" + companyIds);
			if (companyIds.size() > 0) {
				map.put("companyIds", companyIds);
			}
			System.out.println("getTaskByStageOrStatus_map:" + map);
			Long isChecker = modelTaskService.isChecker(user_id);
			System.out.println("isChcker:" + isChecker);
			if (isChecker != null && isChecker.equals(2L)
					&& stage.equals("审核中")) {
				List<ModelTaskVo> mTasks = modelTaskService
						.getCheckStatusTasks(map);
				for (ModelTaskVo m : mTasks) {
					m.setObserver("checker");
				}
				taskSet.addAll(mTasks);
				System.out.println("size:" + taskSet.size());
			}
			if (companyIds.size() == 0 && parentIds.size() == 0
					&& subIds.size() == 0) {
				// 当前操作者是制作者：制作者能看到全部外部任务和指派给自己的指派任务
				System.out.println("---------------1----------------");
				tasks = modelTaskService.getTaskByStageOrStatus(map);
				taskSet.addAll(tasks);
			} else if (companyIds.size() > 0 && parentIds.size() == 0
					&& subIds.size() == 0) {
				// 当前操作者是公司员工：可以看到发布中的所有外部任务和自己所属公司的内部任务和指派给自己的任务
				System.out.println("----------------6ok--------------------");
				tasks = modelTaskService.getTaskByStageOrStatus6(map);
				taskSet.addAll(tasks);
			} else if (companyIds.size() == 0
					&& (parentIds.size() > 0 || subIds.size() > 0)) {
				// 当前操作者是团队成员：可以看到团队所参与项目的所有任务
				System.out.println("-----------------5ok-------------------");
				tasks = modelTaskService.getTaskByStageOrStatus5(map);
				taskSet.addAll(tasks);
			} else if (companyIds.size() > 0
					&& (parentIds.size() > 0 || subIds.size() > 0)) {
				// 当前操作者既是公司员工又是团队成员:可以看到发布中的所有外部任务和自己所属公司的内部任务和指派任务以及团队所参与项目的内部任务和指派任务
				// 可以看到团队所参与项目的所有非发布中任务
				System.out.println("----------------------7ok--------------------");
				tasks = modelTaskService.getTaskByStageOrStatus7(map);
				System.out.println(tasks.size());
				taskSet.addAll(tasks);
			}
			Iterator<ModelTaskVo> taskIts = taskSet.iterator();
			while (taskIts.hasNext()) {
				ModelTaskVo m = taskIts.next();
				if (!"checker".equals(m.getObserver())&&isChecker != null && isChecker.equals(2L)
						&& stage.equals("审核中")&&!user_id.equals(m.getUser_id())) {
					taskIts.remove();
					
				} else if(!"checker".equals(m.getObserver())&&(isChecker == null || !isChecker.equals(2L))){
					if (m.getIs_parent_poject().equals("1")
							&& parentIds.contains(m.getProject_id())) {
						m.setObserver("company");
					} else if (m.getIs_parent_poject().equals("0")
							&& subIds.contains(m.getProject_id())) {
						m.setObserver("company");
					} else {
						m.setObserver("maker");
					}
				}else if(!"checker".equals(m.getObserver())&&isChecker != null && isChecker.equals(2L)
						&& !stage.equals("审核中")){
					if (m.getIs_parent_poject().equals("1")
							&& parentIds.contains(m.getProject_id())) {
						m.setObserver("company");
					} else if (m.getIs_parent_poject().equals("0")
							&& subIds.contains(m.getProject_id())) {
						m.setObserver("company");
					} else {
						m.setObserver("maker");
					}
				}

				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("task_id", m.getId());
				if (m.getClazz().getId().equals(1L)) {
					// if (m.getStage().equals("进行中")
					// && !m.getTask_status().equals("收回中")
					// && m.getEnd_time().before(new Date())) {
					// ModelTaskVo m1 = new ModelTaskVo();
					// m1.setId(m.getId());
					// m1.setTask_status("收回中");
					// m.setTask_status("收回中");
					// modelTaskService.updateModelTask(m1);
					// }
					mp.put("task_type", "建模");
				} else if (m.getClazz().getId().equals(2L)) {
					// if (m.getStage().equals("进行中")
					// && !m.getTask_status().equals("收回中")
					// && m.getEnd_time().before(new Date())) {
					// AnimationLightTaskVo a = new AnimationLightTaskVo();
					// a.setId(m.getId());
					// a.setTask_status("收回中");
					// m.setTask_status("收回中");
					// animationLightTaskService.updateAnimationLightTask(a);
					// }
					mp.put("task_type", "动画");
				} else if (m.getClazz().getId().equals(3L)) {
					// if (m.getStage().equals("进行中")
					// && !m.getTask_status().equals("收回中")
					// && m.getEnd_time().before(new Date())) {
					// AnimationLightTaskVo a = new AnimationLightTaskVo();
					// a.setId(m.getId());
					// a.setTask_status("收回中");
					// m.setTask_status("收回中");
					// animationLightTaskService.updateAnimationLightTask(a);
					// }
					mp.put("task_type", "灯光");
				}
				mp.put("user_id", user_id);
				if (stage.equals("发布中")) {
					List<UserInfo> userInfos = modelTaskService
							.getUserInfos(mp);
					Long id = modelTaskService.getIsDrawOfMe(mp);
					if (id != null) {
						m.setDraw(true);
					} else {
						m.setDraw(false);
					}
					if (userInfos.size() > 1) {
						m.setMorePeople(true);
					} else {
						m.setMorePeople(false);
					}
					m.setUserInfos(userInfos);
				}
			}
		}
		setProjectOfTasks(taskSet);
		for (ModelTaskVo m : taskSet) {
			if (m.getMaker() != null) {
				System.out.println("id:" + m.getId() + ",stage:" + m.getStage()
						+ ",task_status:" + m.getTask_status() + ",company_stage:"+m.getCompany_stage()+",company_status:"+m.getCompany_status()+",user_id:"
						+ m.getMaker().getUser_id() + ",size:" + taskSet.size()
						+ ",project_id:" + m.getProject_id()
						+ ",is_parent_project:" + m.getIs_parent_poject()
						+ ",observer:" + m.getObserver() + "project:"
						+ m.getProjectMap());
			} else {
				System.out.println("id:" + m.getId() + ",stage:" + m.getStage()
						 + ",company_stage:"+m.getCompany_stage()+",company_status:"+m.getCompany_status()+ ",task_status:" + m.getTask_status() + ",user_id:"
						+ m.getUser_id() + ",size:" + taskSet.size()
						+ ",project_id:" + m.getProject_id()
						+ ",is_parent_project:" + m.getIs_parent_poject()
						+ ",observer:" + m.getObserver() + "project:"
						+ m.getProjectMap());
			}
		}
		return new Result(Result.SUCCESS, "成功", taskSet);
	}

	private void setProjectOfTasks(LinkedHashSet<ModelTaskVo> taskSet) {
		for (ModelTaskVo m : taskSet) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (m.getIs_parent_poject().equals("1")) {
				Project p = projectService.getProjectById(m.getProject_id());
				map.put("is_parent_project", 1);
				map.put("id", m.getProject_id());
				map.put("name", p.getName());
				m.setProjectMap(map);
			} else {
				SubProject p = subProjectService.getSubProjectById(m
						.getProject_id());
				map.put("is_parent_project", 0);
				map.put("id", m.getProject_id());
				map.put("name", p.getName());
				m.setProjectMap(map);
			}
		}

	}

	@RequestMapping("/getStageTaskNums")
	@ResponseBody
	public Result getStageTaskNums(HttpSession session) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Long role_id = modelTaskService.getRoleIdByUserId(user_id);
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("getStageTaskNums_role_id:" + role_id);
		map.put("user_id", user_id);
		if (role_id != null && role_id.equals(2L)) {// 操作者代表公司
			map.put("company_id", company_id);
		} else if (role_id != null && role_id.equals(3L)) {// 操作者代表会员，需要进一步判断是公司员工或者是制作者。
			List<Map<String, Long>> projectMaps = modelTaskService
					.getJoinProjectsByUserId(user_id);
			System.out.println("projectMaps:" + projectMaps);
			List<Long> parentIds = new ArrayList<Long>();
			List<Long> subIds = new ArrayList<Long>();
			if (projectMaps.size() > 0) {
				for (Map<String, Long> m : projectMaps) {
					if (m.get("is_parent_project").equals(1L)) {
						Map<String, Object> m1 = getSubProjectIdsOfParent(m
								.get("project_id"));
						if (m1.get("is_parent").equals(1)) {
							parentIds.addAll((List<Long>) m1.get("ids"));
						} else {
							subIds.addAll((List<Long>) m1.get("ids"));
						}
					}
				}
				for (Map<String, Long> m : projectMaps) {
					if (m.get("is_parent_project").equals(0L)) {
						Map<String, Object> m1 = getSubProjectIdsOfSub(m
								.get("project_id"));
						if (!subIds.containsAll((List<Long>) m1.get("ids"))) {
							subIds.addAll((List<Long>) m1.get("ids"));
						}
					}
				}
			}
			Iterator<Long> its = subIds.iterator();
			while (its.hasNext()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("project_id", its.next());
				m.put("is_parent_project", "0");
				m.put("user_id", user_id);
				Long isMaker = modelTaskService.getIsMakerOfGroup(m);
				if (isMaker != null && isMaker > 0) {
					its.remove();
				}
			}
			map.put("parentIds", parentIds);
			map.put("subIds", subIds);
			System.out.println("parentIds:" + parentIds + ",subIds:" + subIds);
			// // 根据userId往员工表里面该员工所属哪几个公司，查看是否是公司员工。
			List<Long> companyIds = modelTaskService
					.getCompanysByUserId(user_id);
			// companyIds.clear();
			System.out.println("companyIds:" + companyIds);
			map.put("companyIds", companyIds);
			System.out.println("getStageTaskNums_map:" + map);
			if (companyIds.size() == 0 && parentIds.size() == 0
					&& subIds.size() == 0) {
				// 当前操作者是制作者：制作者能看到全部外部任务和指派给自己的指派任务
				System.out.println("---------------1----------------");
			} else if (companyIds.size() > 0 && parentIds.size() == 0
					&& subIds.size() == 0) {
				// 当前操作者是公司员工：可以看到发布中的所有外部任务和自己所属公司的内部任务和指派给自己的任务
				System.out.println("----------------6ok--------------------");
			} else if (companyIds.size() == 0
					&& (parentIds.size() > 0 || subIds.size() > 0)) {
				// 当前操作者是团队成员：可以看到团队所参与项目的所有任务
				System.out.println("-----------------5ok-------------------");
			} else if (companyIds.size() > 0
					&& (parentIds.size() > 0 || subIds.size() > 0)) {
				// 当前操作者既是公司员工又是团队成员:可以看到发布中的所有外部任务和自己所属公司的内部任务和指派任务以及团队所参与项目的内部任务和指派任务
				// 可以看到团队所参与项目的所有非发布中任务
				System.out
						.println("----------------------7ok--------------------");
			}
		}
		Long publishNums = modelTaskService.getPublushNums(map);// 发布中任务数,根据任务角色不同，结果也不同
		Long unStartNums = modelTaskService.getUnStartNums(map);// 未开始任务数
		Long runningNums = modelTaskService.getRunningNums(map);// 运行中任务数
		Long isChecker = modelTaskService.isChecker(user_id);
		LinkedHashSet<ModelTaskVo> taskSet = new LinkedHashSet<ModelTaskVo>();
		Long checkNums = 0L;
		if (isChecker != null && isChecker.equals(2L)) {
			List<ModelTaskVo> mTasks = modelTaskService
					.getCheckStatusTasks(map);
			taskSet.addAll(mTasks);
		Iterator<ModelTaskVo> taskIts = taskSet.iterator();
		while (taskIts.hasNext()) {
			ModelTaskVo m = taskIts.next();
			if (!"checker".equals(m.getObserver())&&isChecker != null && isChecker.equals(2L)
				&&!user_id.equals(m.getUser_id())) {
				taskIts.remove();
			}
		}
		checkNums =(long) taskSet.size();
		}else{
			checkNums = modelTaskService.getCheckNums(map);// 审核中任务数
		}
		
		Long waitPayNums = modelTaskService.getWaitPayNums(map);// 待付款任务数
		Long waitEvalutionNums = modelTaskService.getWaitEvalutionNums(map);// 待评价任务数
		Long finishedNums = modelTaskService.getFinishedNums(map);// 已完成任务
		List<Long> parentIds = (List<Long>) map.get("parentIds");
		List<Long> subIds = (List<Long>) map.get("subIds");
		if (role_id == 2 || isChecker == null
				&& (parentIds.size() > 0 || subIds.size() > 0)) {
			System.out.println("ok1");
			Long abandonNums = modelTaskService.getabandonNums(map);// 已放弃任务数
			Long totalNums = publishNums + unStartNums + runningNums
					+ checkNums + waitPayNums + waitEvalutionNums + abandonNums
					+ finishedNums;
			map.clear();
			map.put("abandonNums", abandonNums);
			map.put("totalNums", totalNums);
		} else {
			System.out.println("ok2");
			Long totalNums = publishNums + unStartNums + runningNums
					+ checkNums + waitPayNums + waitEvalutionNums
					+ finishedNums;
			map.put("totalNums", totalNums);
		}
		map.put("publishNums", publishNums);
		map.put("unStartNums", unStartNums);
		map.put("runningNums", runningNums);
		map.put("checkNums", checkNums);
		map.put("waitPayNums", waitPayNums);
		map.put("waitEvalutionNums", waitEvalutionNums);
		map.put("finishedNums", finishedNums);
		return new Result(Result.SUCCESS, "成功", map);
	}

	/**
	 * 根据项目阶段，状态查询任务
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/getTaskByStageOrStatus1")
	@ResponseBody
	public Result getTaskByStageOrStatus1(String status, String stage,
			Long class_id, String beginTime, HttpSession session, String field,
			String sort) throws Exception {
		// Long company_id = (Long) session.getAttribute("sessionCompany");
		Long company_id = 18L;
		// Long user_id = ((User) session.getAttribute("loginUser")).getId();
		Long user_id = 59L;
		// if (session.getAttribute("loginUser") == null) {
		// return new Result("RY0008", null);
		// }
		//
		Long role_id = modelTaskService.getRoleIdByUserId(user_id);
		// Long role_id = 3L;
		System.out.println("getTaskByStageOrStatus_role_id:" + role_id);
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
		if (beginTime != null && !"".equals(beginTime)) {
			Date begin = sdf.parse(beginTime);
			beginTime = sdf1.format(begin);
			map.put("begin_time", beginTime);
		}
		map.put("class_id", class_id);
		map.put("stage", stage);
		map.put("status", status);
		map.put("field", field);
		map.put("sort", sort);
		map.put("user_id", user_id);
		/*
		 * 待处理sort,field
		 */
		LinkedHashSet<ModelTaskVo> taskSet = new LinkedHashSet<ModelTaskVo>();
		List<ModelTaskVo> tasks = null;
		if (role_id != null && role_id.equals(2L)) {// 操作者代表公司
			map.put("company_id", company_id);
			tasks = modelTaskService.getTaskByStageOrStatusCompany(map);
			taskSet.addAll(tasks);
			for (ModelTaskVo m : taskSet) {
				m.setObserver("company");
				// if (m.getMaker() != null) {
				// System.out.println(m.getId() + "," + m.getStage() + ","
				// + m.getTask_status() + ","
				// + m.getMaker().getUser_id() + "," + taskSet.size()
				// + "," + m.getProject_id() + ","
				// + m.getCompany().getId());
				// } else {
				// System.out.println(m.getId() + "," + m.getStage() + ","
				// + m.getTask_status() + "," + m.getUser_id() + ","
				// + taskSet.size() + "," + m.getProject_id() + ","
				// + m.getCompany().getId());
				// }
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("task_id", m.getId());
				if (m.getClazz().getId().equals(1L)) {
					if (m.getStage().equals("进行中")
							&& !m.getTask_status().equals("收回中")
							&& m.getEnd_time().before(new Date())) {
						ModelTaskVo m1 = new ModelTaskVo();
						m1.setId(m.getId());
						m1.setTask_status("收回中");
						m.setTask_status("收回中");
						modelTaskService.updateModelTask(m1);
					}
					mp.put("task_type", "建模");
				} else if (m.getClazz().getId().equals(2L)) {
					if (m.getStage().equals("进行中")
							&& !m.getTask_status().equals("收回中")
							&& m.getEnd_time().before(new Date())) {
						AnimationLightTaskVo a = new AnimationLightTaskVo();
						a.setId(m.getId());
						a.setTask_status("收回中");
						m.setTask_status("收回中");
						animationLightTaskService.updateAnimationLightTask(a);
					}
					mp.put("task_type", "动画");
				} else if (m.getClazz().getId().equals(3L)) {
					if (m.getStage().equals("进行中")
							&& !m.getTask_status().equals("收回中")
							&& m.getEnd_time().before(new Date())) {
						AnimationLightTaskVo a = new AnimationLightTaskVo();
						a.setId(m.getId());
						a.setTask_status("收回中");
						m.setTask_status("收回中");
						animationLightTaskService.updateAnimationLightTask(a);
					}
					mp.put("task_type", "灯光");
				}
				if (stage.equals("发布中")) {
					List<UserInfo> userInfos = modelTaskService
							.getUserInfos(mp);
					if (userInfos.size() > 1) {
						m.setMorePeople(true);
					} else {
						m.setMorePeople(false);
					}
					m.setUserInfos(userInfos);
				}
			}
		} else if (role_id != null && role_id.equals(3L)) {// 操作者代表会员，需要进一步判断是公司员工或者是制作者。
			Long isMaker = modelTaskService.getIsMaker(user_id);
			System.out.println("isMaker:" + isMaker);
			if (isMaker != null && isMaker.equals(5L)) {
				map.put("isMaker", 1);
			}
			List<Map<String, Long>> projectMaps = modelTaskService
					.getJoinProjectsByUserId(user_id);
			System.out.println("projectMaps:" + projectMaps);
			List<Long> parentIds = new ArrayList<Long>();
			List<Long> subIds = new ArrayList<Long>();
			if (projectMaps.size() > 0) {
				for (Map<String, Long> m : projectMaps) {
					if (m.get("is_parent_project").equals(1L)) {
						Map<String, Object> m1 = getSubProjectIdsOfParent(m
								.get("project_id"));
						if (m1.get("is_parent").equals(1)) {
							parentIds.addAll((List<Long>) m1.get("ids"));
						} else {
							subIds.addAll((List<Long>) m1.get("ids"));
						}
					}
				}
				for (Map<String, Long> m : projectMaps) {
					if (m.get("is_parent_project").equals(0L)) {
						Map<String, Object> m1 = getSubProjectIdsOfSub(m
								.get("project_id"));
						if (!subIds.containsAll((List<Long>) m1.get("ids"))) {
							subIds.addAll((List<Long>) m1.get("ids"));
						}
					}
				}
			}
			// parentIds.clear();
			// subIds.clear();
			map.put("parentIds", parentIds);
			map.put("subIds", subIds);
			System.out.println("parentIds:" + parentIds + ",subIds:" + subIds);
			// // 根据userId往员工表里面该员工所属哪几个公司，查看是否是公司员工。
			List<Long> companyIds = modelTaskService
					.getCompanysByUserId(user_id);
			// companyIds.clear();
			System.out.println("companyIds:" + companyIds);
			if (companyIds.size() > 0) {
				map.put("companyIds", companyIds);
			}
			System.out.println("getTaskByStageOrStatus_map:" + map);
			if ((isMaker == null || (isMaker != null && isMaker == 5L))
					&& companyIds.size() == 0 && subIds.size() == 0
					&& parentIds.size() == 0) {
				// 当前操作者是制作者：制作者能看到全部外部任务和指派给自己的指派任务
				System.out.println("---------------1----------------");
				tasks = modelTaskService.getTaskByStageOrStatus(map);
				taskSet.addAll(tasks);
			} else if ((isMaker == null || (isMaker != null && isMaker == 5L))
					&& companyIds.size() > 0 && subIds.size() == 0
					&& parentIds.size() == 0) {
				// 当前操作者既是制作者又是公司员工：可以看到发布中的所有外部任务和指派给自己的指派任务
				// 以及公司员工所属公司的发布中的内部任务。以及制作者参与的其它阶段的任务
				System.out.println("------------------2-----------------");
				tasks = modelTaskService.getTaskByStageOrStatus2(map);
				taskSet.addAll(tasks);
			} else if ((isMaker == null || (isMaker != null && isMaker == 5L))
					&& companyIds.size() > 0
					&& (subIds.size() > 0 || parentIds.size() > 0)) {
				// 当前操作者既是制作者又是公司员工还是团队成员:可以看到发布中的所有外部任务和自己所属公司的内部任务和指派任务以及团队所参与项目的内部任务和指派任务，以及指派给自己的指派任务
				// 以及团队成员参与项目的所有阶段任务以及非发布阶段跟制作者有关的所有任务
				System.out.println("------------------3----------------------");
				tasks = modelTaskService.getTaskByStageOrStatus3(map);
				taskSet.addAll(tasks);
			} else if ((isMaker == null || (isMaker != null && isMaker == 5L))
					&& companyIds.size() == 0
					&& (subIds.size() > 0 || parentIds.size() > 0)) {
				// 当前操作者既是制作者又是团队成员：可以看到发布中的所有外部任务和指派给自己的指派任务
				// 以及以及团队成员参与项目的所有阶段任务
				System.out.println("-----------------------4----------------");
				tasks = modelTaskService.getTaskByStageOrStatus4(map);
				taskSet.addAll(tasks);
			} else if (isMaker == null && companyIds.size() == 0
					&& (subIds.size() > 0 || parentIds.size() > 0)) {
				// 当前操作者是团队成员：可以看到团队所参与项目的所有任务
				System.out.println("-----------------5-------------------");
				tasks = modelTaskService.getTaskByStageOrStatus5(map);
				taskSet.addAll(tasks);
			} else if (isMaker == null && companyIds.size() > 0
					&& subIds.size() == 0 && parentIds.size() == 0) {
				// 当前操作者是公司员工：可以看到发布中的所有外部任务和自己所属公司的内部任务和指派任务
				System.out.println("----------------6--------------------");
				tasks = modelTaskService.getTaskByStageOrStatus6(map);
				taskSet.addAll(tasks);
			} else if (isMaker == null && companyIds.size() > 0
					&& (subIds.size() > 0 || parentIds.size() > 0)) {
				// 当前操作者既是公司员工又是团队成员:可以看到发布中的所有外部任务和自己所属公司的内部任务和指派任务以及团队所参与项目的内部任务和指派任务
				// 可以看到团队所参与项目的所有非发布中任务
				System.out
						.println("----------------------7--------------------");
				tasks = modelTaskService.getTaskByStageOrStatus7(map);
				System.out.println(tasks.size());
				taskSet.addAll(tasks);
			}
			Long isChecker = modelTaskService.isChecker(user_id);
			System.out.println("isChcker:" + isChecker);
			if (isChecker != null && isChecker.equals(2L)
					&& stage.equals("审核中")) {
				taskSet.clear();
				System.out.println("-------------------------------");
				List<ModelTaskVo> mTasks = modelTaskService
						.getCheckStatusTasks(map);
				for (ModelTaskVo m : mTasks) {
					m.setObserver("checker");
				}
				taskSet.addAll(mTasks);
				System.out.println(map);
				System.out.println("size:" + taskSet.size());
				System.out.println("---------------------------------------");
			} else {
				for (ModelTaskVo m : taskSet) {
					Map<String, Object> mp1 = new HashMap<String, Object>();
					mp1.put("project_id", m.getProject_id());
					mp1.put("is_parent_project", m.getIs_parent_poject());
					mp1.put("user_id", user_id);
					Long isGroupMemeber = modelTaskService
							.getIsGroupMemeber(mp1);
					if (isGroupMemeber != null && isGroupMemeber > 0) {
						m.setObserver("company");
					} else {
						m.setObserver("maker");
					}
					if (m.getMaker() != null) {
						System.out.println(m.getId() + "," + m.getStage() + ","
								+ m.getTask_status() + ","
								+ m.getMaker().getUser_id() + ","
								+ taskSet.size() + "," + m.getProject_id());
					} else {
						System.out.println(m.getId() + "," + m.getStage() + ","
								+ m.getTask_status() + "," + m.getUser_id()
								+ "," + taskSet.size() + ","
								+ m.getProject_id());
					}
					Map<String, Object> mp = new HashMap<String, Object>();
					mp.put("task_id", m.getId());

					if (m.getClazz().getId().equals(1L)) {
						if (m.getStage().equals("进行中")
								&& !m.getTask_status().equals("收回中")
								&& m.getEnd_time().before(new Date())) {
							ModelTaskVo m1 = new ModelTaskVo();
							m1.setId(m.getId());
							m1.setTask_status("收回中");
							m.setTask_status("收回中");
							modelTaskService.updateModelTask(m1);
						}
						mp.put("task_type", "建模");
					} else if (m.getClazz().getId().equals(2L)) {
						if (m.getStage().equals("进行中")
								&& !m.getTask_status().equals("收回中")
								&& m.getEnd_time().before(new Date())) {
							AnimationLightTaskVo a = new AnimationLightTaskVo();
							a.setId(m.getId());
							a.setTask_status("收回中");
							m.setTask_status("收回中");
							animationLightTaskService
									.updateAnimationLightTask(a);
						}
						mp.put("task_type", "动画");
					} else if (m.getClazz().getId().equals(3L)) {
						if (m.getStage().equals("进行中")
								&& !m.getTask_status().equals("收回中")
								&& m.getEnd_time().before(new Date())) {
							AnimationLightTaskVo a = new AnimationLightTaskVo();
							a.setId(m.getId());
							a.setTask_status("收回中");
							m.setTask_status("收回中");
							animationLightTaskService
									.updateAnimationLightTask(a);
						}
						mp.put("task_type", "灯光");
					}
					mp.put("user_id", user_id);
					if (stage.equals("发布中")) {
						List<UserInfo> userInfos = modelTaskService
								.getUserInfos(mp);
						Long id = modelTaskService.getIsDrawOfMe(mp);
						if (id != null) {
							m.setDraw(true);
						} else {
							m.setDraw(false);
						}
						if (userInfos.size() > 1) {
							m.setMorePeople(true);
						} else {
							m.setMorePeople(false);
						}
						m.setUserInfos(userInfos);
					}
				}
			}

		}
		return new Result(Result.SUCCESS, "成功", taskSet);
	}

	/**
	 * 按任务类目周期
	 * 
	 * @param id
	 * @param is_parent_project
	 * @return
	 */

	@RequestMapping("/getTaskClassCycleInfo")
	@ResponseBody
	public Result getTaskClassCycleInfo(Long id, String is_parent_project,
			String num) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (is_parent_project != null && is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(id);// 获取任务项目是父项目的任务报表信息
		} else {
			map = getSubProjectIdsOfSub(id);// 获取任务项目是子项目的任务报表信息
		}
		Date begin_time, end_time, actual_begin_time, actual_end_time;
		begin_time = modelTaskService.getBeginTime(map);// 获取项目的任务中最早的计划开始时间
		end_time = modelTaskService.getEndTime(map);// 获取项目的任务中最晚的计划结束时间
		actual_begin_time = modelTaskService.getActualBeginTime(map);// 获取项目的任务中最早的实际开始时间
		actual_end_time = modelTaskService.getActualEndTime(map);// 获取项目的任务中最万的实际结束时间
		if (actual_begin_time != null && begin_time.after(actual_begin_time)) {
			begin_time = actual_begin_time;
			// 当实际开始时间不为空且在计划开始时间之前，那么让计划开始时间等于该时间
		}
		if (actual_end_time != null && end_time.before(actual_end_time)) {
			end_time = actual_end_time;
			// 当实际结束时间不为空且在计划结束时间之后，那么让计划结束时间等于该时间
		}
		Long modelTaskTotal = modelTaskService.getTaskTotal(map);// 获取该项目下的所有建模任务总数
		Long modelTaskFinishedTotal = modelTaskService
				.getTaskFinishedTotal(map);// 获取该项目下的所有建模任务的已完成总数
		double modelTaskFinishRate = modelTaskFinishedTotal * 1.0
				/ modelTaskTotal;// 计算建模任务的完成百分比
		Date modelTaskBeiginTime = modelTaskService.getModelTaskBeginTime(map);// 获取该项目下的建模任务中最早计划开始时间
		Date modelTaskEndTime = modelTaskService.getModelTaskEndTime(map);// 获取该项目下的建模任务中最晚计划结束时间
		Date modelTaskActualBeginTime = modelTaskService
				.getModelTaskActualBeginTime(map);// 获取该项目下的建模任务中最早实际开始时间
		Date modelTaskActualEndTime = null;
		if (modelTaskTotal == modelTaskFinishedTotal) {
			// 当所有任务都已完成，获取该项目下的建模任务中最晚实际结束时间
			modelTaskActualEndTime = modelTaskService
					.getModelTaskActualEndTime(map);
		}
		map.put("class_id", 2);
		Long animationTaskTotal = animationLightTaskService.getTaskTotal(map);// 获取该项目下的所有动画任务总数
		Long animationTaskFinishedTotal = animationLightTaskService
				.getTaskFinishedTotal(map);// 获取该项目下的所有动画任务的已完成总数
		double animationTaskFinishRate = animationTaskFinishedTotal * 1.0
				/ animationTaskTotal;// 计算动画任务的完成百分比
		Date animationTaskBeiginTime = animationLightTaskService
				.getTaskBeginTime(map);// 获取该项目下的动画任务中最早计划开始时间
		Date animationTaskEndTime = animationLightTaskService
				.getTaskEndTime(map);// 获取该项目下的动画任务中最晚计划结束时间
		Date animationTaskActualBeginTime = animationLightTaskService
				.getTaskActualBeginTime(map);// 获取该项目下的动画任务中最早实际开始时间
		Date animationTaskActualEndTime = null;
		if (animationTaskTotal == animationTaskFinishedTotal) {
			// 当所有任务都已完成，获取该项目下的动画任务中最晚实际结束时间
			animationTaskActualEndTime = animationLightTaskService
					.getTaskActualEndTime(map);
		}
		map.put("class_id", 3);
		Long lightTaskTotal = animationLightTaskService.getTaskTotal(map);// 获取该项目下的所有灯光任务总数
		Long lightTaskFinishedTotal = animationLightTaskService
				.getTaskFinishedTotal(map);// 获取该项目下的所有灯光任务的已完成总数
		double lightTaskFinishRate = lightTaskFinishedTotal * 1.0
				/ lightTaskTotal;// 计算灯光任务的完成百分比
		Date lightTaskBeiginTime = animationLightTaskService
				.getTaskBeginTime(map);// 获取该项目下的灯光任务中最早计划开始时间
		Date lightTaskEndTime = animationLightTaskService.getTaskEndTime(map);// 获取该项目下的灯光任务中最晚计划结束时间
		Date lightTaskActualBeginTime = animationLightTaskService
				.getTaskActualBeginTime(map);// 获取该项目下的灯光任务中最早实际开始时间
		Date lightTaskActualEndTime = null;
		if (lightTaskTotal == lightTaskFinishedTotal) {
			// 当所有任务都已完成，获取该项目下的灯光任务中最晚实际结束时间
			lightTaskActualEndTime = animationLightTaskService
					.getTaskActualEndTime(map);
		}
		if ("1".equals(num)) {
			// 生成日
			List<Map<String, Object>> dataList = CalendarUtil.day(begin_time,
					end_time);
			map.put("dataList", dataList);
		} else if ("2".equals(num)) {
			// 生成周
			List<Map<String, Object>> dataList = CalendarUtil.week(begin_time,
					end_time);
			map.put("dataList", dataList);
		} else if ("3".equals(num)) {
			// 生成月
			List<Map<String, Object>> dataList = CalendarUtil.month(begin_time,
					end_time);
			map.put("dataList", dataList);
		} else if ("4".equals(num)) {
			// 生成季
			List<Map<String, Object>> dataList = CalendarUtil.season(
					begin_time, end_time);
			map.put("dataList", dataList);
		} else if ("5".equals(num)) {
			// 生成年
			List<Map<String, Object>> dataList = CalendarUtil.year(begin_time,
					end_time);
			map.put("dataList", dataList);
		}
		map.put("modelTaskBeiginTime", modelTaskBeiginTime);
		map.put("modelTaskEndTime", modelTaskEndTime);
		map.put("modelTaskActualBeginTime", modelTaskActualBeginTime);
		map.put("modelTaskActualEndTime", modelTaskActualEndTime);
		map.put("modelTaskFinishRate", modelTaskFinishRate);
		map.put("animationTaskBeiginTime", animationTaskBeiginTime);
		map.put("animationTaskEndTime", animationTaskEndTime);
		map.put("animationTaskActualBeginTime", animationTaskActualBeginTime);
		map.put("animationTaskActualEndTime", animationTaskActualEndTime);
		map.put("animationTaskFinishRate", animationTaskFinishRate);
		map.put("lightTaskBeiginTime", lightTaskBeiginTime);
		map.put("lightTaskEndTime", lightTaskEndTime);
		map.put("lightTaskActualBeginTime", lightTaskActualBeginTime);
		map.put("lightTaskActualEndTime", lightTaskActualEndTime);
		map.put("lightTaskFinishRate", lightTaskFinishRate);
		CalendarUtil.changeMapInfo(map, num);// 转换时间格式为所需格式
		return new Result(Result.SUCCESS, "成功", map);
	}

	/**
	 * 按任务周期
	 * 
	 * @param data
	 * @param id
	 * @param is_parent_project
	 * @return
	 */
	@RequestMapping("/getTaskCycleInfo")
	@ResponseBody
	public Result getTaskCycleInfo(String data, Long id,
			String is_parent_project, String num) {
		// data="{'page':{'pageSize':'5','pageNo':'1'}}";
		JSONObject param = JSON.parseObject(data);
		Map<String, Object> map = new HashMap<String, Object>();
		Date begin_time, end_time, actual_begin_time, actual_end_time;
		if (is_parent_project != null && is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(id);// 获取项目下所有的子项目id信息
			ProjectVo project = projectService.getProjectById(id);
			map.put("project", project);
		} else {
			map = getSubProjectIdsOfSub(id);// 获取项目下所有的子项目id信息
			SubProjectVo subProject = subProjectService.getSubProjectById(id);
			map.put("project", subProject);
		}

		Map<String, String> map1 = (Map<String, String>) param.get("page");
		Page page = PageUtils.createPage(map1);
		page = modelTaskService.page3(page, map);
		List<ModelTaskVo> ps = (List<ModelTaskVo>) page.getDataList();
		for (ModelTaskVo m : ps) {
			setOtherInfo(m, num);
		}
		begin_time = modelTaskService.getBeginTime(map);
		end_time = modelTaskService.getEndTime(map);
		actual_begin_time = modelTaskService.getActualBeginTime(map);
		actual_end_time = modelTaskService.getActualEndTime(map);
		if (actual_begin_time != null && begin_time.after(actual_begin_time)) {
			begin_time = actual_begin_time;
		}
		if (actual_end_time != null && end_time.before(actual_end_time)) {
			end_time = actual_end_time;
		}
		if ("1".equals(num)) {
			List<Map<String, Object>> dataList = CalendarUtil.day(begin_time,
					end_time);
			map.put("dataList", dataList);
		} else if ("2".equals(num)) {
			List<Map<String, Object>> dataList = CalendarUtil.week(begin_time,
					end_time);
			map.put("dataList", dataList);
		} else if ("3".equals(num)) {
			List<Map<String, Object>> dataList = CalendarUtil.month(begin_time,
					end_time);
			map.put("dataList", dataList);
		} else if ("4".equals(num)) {
			List<Map<String, Object>> dataList = CalendarUtil.season(
					begin_time, end_time);
			map.put("dataList", dataList);
		} else if ("5".equals(num)) {
			List<Map<String, Object>> dataList = CalendarUtil.year(begin_time,
					end_time);
			map.put("dataList", dataList);
		}
		map.put("total", page.getTotal());
		map.put("pageSize", page.getLimit());
		map.put("pageNo", page.getCurrentPage());
		map.put("datas", ps);
		return new Result(Result.SUCCESS, "成功", map);
	}

	/**
	 * 为任务设置实际结束时间和实际用时天数
	 * 
	 * @param m
	 */
	private void setOtherInfo(ModelTaskVo m, String num) {
		if (m.getClass_id().equals(1)) {// 1代表是建模任务
			Date actual_end_time = modelTaskService.getModelTaskActualEndTime(m
					.getId());// 根据建模任务id查询其实际结束时间
			if (actual_end_time != null) {
				m.setActual_end_time(actual_end_time);
				if (m.getActual_begin_time() != null) {
					m.setL_actual_begin_time(m.getActual_begin_time().getTime());
					long usedDate = actual_end_time.getTime()
							- m.getActual_begin_time().getTime();// 实际用时天数
					m.setUsedDate((int) Math.round(usedDate * 1.0
							/ (1000 * 60 * 60 * 24)));// 实际天数是四舍五入
				}
			} else if (m.getActual_begin_time() != null) {
				Date now = new Date();
				long usedDate = now.getTime()
						- m.getActual_begin_time().getTime();
				m.setUsedDate((int) Math.round(usedDate * 1.0
						/ (1000 * 60 * 60 * 24)));
			}
		} else {
			Date actual_end_time = animationLightTaskService
					.getAnimationLightTaskActualEndTime(m.getId());// 根据动画灯光任务id查询其实际结束时间
			if (actual_end_time != null) {
				m.setActual_end_time(actual_end_time);
				m.setL_actual_end_time(actual_end_time.getTime());
				if (m.getActual_begin_time() != null) {
					m.setL_actual_begin_time(m.getActual_begin_time().getTime());
					long usedDate = actual_end_time.getTime()
							- m.getActual_begin_time().getTime();
					m.setUsedDate((int) Math.round(usedDate * 1.0
							/ (1000 * 60 * 60 * 24)));
				}
			} else if (m.getActual_begin_time() != null) {
				m.setL_actual_begin_time(m.getActual_begin_time().getTime());
				Date now = new Date();
				long usedDate = now.getTime()
						- m.getActual_begin_time().getTime();
				m.setUsedDate((int) Math.round(usedDate * 1.0
						/ (1000 * 60 * 60 * 24)));
			}
		}
		if ("1".equals(num)) {
			CalendarUtil.changeDay(m);

		} else if ("2".equals(num)) {
			CalendarUtil.changeWeek(m);

		} else if ("3".equals(num)) {
			CalendarUtil.changeMonth(m);

		} else if ("4".equals(num)) {
			CalendarUtil.changeSeason(m);

		} else if ("5".equals(num)) {
			CalendarUtil.changeYear(m);
		}
	}

	/**
	 * 项目任务指派/领取比例图
	 * 
	 * @param id
	 * @param is_parent_project
	 * @return
	 */
	@RequestMapping("/getPointGetTaskRate")
	@ResponseBody
	public Result getPointGetTaskRate(Long id, String is_parent_project) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (is_parent_project != null && is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(id);// 获取任务项目是父项目的任务报表信息
		} else {
			map = getSubProjectIdsOfSub(id);// 获取任务项目是子项目的任务报表信息
		}
		Long pointTaskNums = modelTaskService.getPointTaskNums(map);
		Long getTaskNums = modelTaskService.getGetTaskNums(map);
		map.clear();
		map.put("pointTaskNums", pointTaskNums);
		map.put("getTaskNums", getTaskNums);
		return new Result(Result.SUCCESS, "成功", map);
	}

	/**
	 * 按任务类型比例
	 * 
	 * @param id
	 * @param is_parent_project
	 * @return
	 */
	@RequestMapping("/getTaskTypeRate")
	@ResponseBody
	public Result getTaskTypeRate(Long id, String is_parent_project) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (is_parent_project != null && is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(id);// 获取任务项目是父项目的任务报表信息
		} else {
			map = getSubProjectIdsOfSub(id);// 获取任务项目是子项目的任务报表信息
		}
		map.put("stage", "发布中");
		Long publish = modelTaskService.getTaskOfSomeInfoNums(map);
		map.put("stage", "放弃中");
		Long abandon = modelTaskService.getTaskOfSomeInfoNums(map);
		map.put("stage", "进行中");
		Long running = modelTaskService.getTaskOfSomeInfoNums(map);
		map.put("stage", "审核中");
		Long checking = modelTaskService.getTaskOfSomeInfoNums(map);
		map.put("stage", "已完成");
		Long finished = modelTaskService.getTaskOfSomeInfoNums(map);
		map.put("stage", "未发布");
		Long unpublish = modelTaskService.getTaskOfSomeInfoNums(map);
		map.put("stage", null);
		map.put("publish_type_id", 1);
		Long outTask = modelTaskService.getTaskOfSomeInfoNums(map);
		map.put("publish_type_id", 2);
		Long innerTask = modelTaskService.getTaskOfSomeInfoNums(map);
		map.put("publish_type_id", 3);
		Long pointTask = modelTaskService.getTaskOfSomeInfoNums(map);
		map.clear();
		map.put("publish", publish);
		map.put("abandon", abandon);
		map.put("running", running);
		map.put("checking", checking);
		map.put("finished", finished);
		map.put("unpublish", unpublish);
		map.put("outTask", outTask);
		map.put("innerTask", innerTask);
		map.put("pointTask", pointTask);
		return new Result(Result.SUCCESS, "成功", map);
	}

	/**
	 * 任务类目比例图
	 * 
	 * @param id
	 * @param is_parent_project
	 * @return
	 */
	@RequestMapping("/getTaskClassInfo")
	@ResponseBody
	public Result getTaskClassInfo(Long id, String is_parent_project) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (is_parent_project != null && is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(id);// 获取任务项目是父项目的任务报表信息
		} else {
			map = getSubProjectIdsOfSub(id);// 获取任务项目是子项目的任务报表信息
		}
		map.put("class_id", 1);
		Long model = modelTaskService.getTaskClassNums(map);
		map.put("class_id", 2);
		Long animation = modelTaskService.getTaskClassNums(map);
		map.put("class_id", 3);
		Long light = modelTaskService.getTaskClassNums(map);
		map.clear();
		map.put("model", model);
		map.put("animation", animation);
		map.put("light", light);
		return new Result(Result.SUCCESS, "成功", map);
	}

	private Map<String, Object> getSubProjectIdsOfParent(Long id) {
		Map<String, Object> mapIds = new HashMap<String, Object>();
		mapIds.put("is_parent", 1);// 为1表示当前项目是根父项目
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		mapIds.put("ids", ids);
		List<SubProject> subProjects1 = projectService
				.getParentSubProjects(mapIds);// 获取根项目的直接子项目列表
		if (subProjects1.size() > 0) {// 当subProjects1.size()>0时，说明根父项目有子项目
			List<Long> ids1 = new ArrayList<Long>();
			for (SubProject subProject : subProjects1) {
				ids1.add(subProject.getId());
			}
			mapIds.put("ids", ids1);
			mapIds.put("is_parent", 0);// 为0表示当前项目不是根父项目
			for (int i = 0; i < subProjects1.size(); i++) {// 遍历直接子项目
				if (subProjects1.get(i).getIs_parent() != null
						&& subProjects1.get(i).getIs_parent().equals("1")) {
					// 当subProjects1.get(i).getIs_parent().equals("1")说明该子项目还有子项目
					List<SubProject> subProjects2 = projectService
							.getSubProjects(mapIds);
					if (subProjects2.size() > 0) {// 当subProjects2.size()>0时，说明根子项目还有有子项目
						for (SubProject subProject : subProjects2) {
							ids1.add(subProject.getId());
						}
						mapIds.put("ids", ids1);
						mapIds.put("is_parent", 0);// 为0表示当前项目不是根父项目
						for (int j = 0; j < subProjects2.size(); j++) {// 遍历子项目的子项目列表
							if (subProjects2.get(j).getIs_parent() != null
									&& subProjects2.get(j).getIs_parent()
											.equals("1")) {
								// 当subProjects1.get(j).getIs_parent().equals("1")说明该子项目还有子项目
								List<SubProject> subProjects3 = projectService
										.getSubProjects(mapIds);
								if (subProjects3.size() > 0) {// 项目一共有四层
									for (SubProject subProject : subProjects3) {
										ids1.add(subProject.getId());
									}
									mapIds.put("is_parent", 0);// 为0表示当前项目不是根父项目
									mapIds.put("ids", ids1);
								}
								break;
							}
						}

					}
					break;
				}
			}
		}
		return mapIds;
	}

	private Map<String, Object> getSubProjectIdsOfSub(Long id) {
		Map<String, Object> mapIds = new HashMap<String, Object>();
		mapIds.put("is_parent", 0);// 为1表示当前项目是根父项目
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		mapIds.put("ids", ids);
		List<SubProject> subProjects1 = projectService.getSubProjects(mapIds);// 获取根项目的直接子项目列表
		if (subProjects1.size() > 0) {// 当subProjects1.size()>0时，说明根父项目有子项目
			List<Long> ids1 = new ArrayList<Long>();
			for (SubProject subProject : subProjects1) {
				ids1.add(subProject.getId());
			}
			mapIds.put("ids", ids1);
			mapIds.put("is_parent", 0);// 为0表示当前项目不是根父项目
			for (int i = 0; i < subProjects1.size(); i++) {// 遍历直接子项目
				if (subProjects1.get(i).getIs_parent() != null
						&& subProjects1.get(i).getIs_parent().equals("1")) {
					// 当subProjects1.get(i).getIs_parent().equals("1")说明该子项目还有子项目
					List<SubProject> subProjects2 = projectService
							.getSubProjects(mapIds);
					if (subProjects2.size() > 0) {// 当subProjects2.size()>0时，说明根子项目还有有子项目
						for (SubProject subProject : subProjects2) {
							ids1.add(subProject.getId());
						}
						mapIds.put("ids", ids1);
						mapIds.put("is_parent", 0);// 为0表示当前项目不是根父项目
					}
					break;
				}
			}
		}
		return mapIds;
	}

	/**
	 * 根据项目id获取发布的任务，包括建模，动画，灯光三种任务
	 * 
	 * @param project_id
	 * @param is_parent_project
	 * @return
	 */
	@RequestMapping("/getPublishTask")
	@ResponseBody
	public Result getPublishTask(Long project_id, String is_parent_project,
			String data, HttpSession session) {
		Long company_id = (Long) session.getAttribute("projectCompany");
		System.out.println("company_id:" + company_id);
		System.out.println("project_id:" + project_id);
		System.out.println("is_parent_project" + is_parent_project);
		// data=
		// "{'page':{'pageSize':'10','pageNo':'1'},'object':{'sort':'asc','field':'task_class','search':'动画'}}";
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject param = JSON.parseObject(data);
		JSON object = (JSON) JSON.toJSON(param.get("object"));
		ProjectSearchVo search = JSONObject.toJavaObject(object,
				ProjectSearchVo.class);
		ModelTaskVo task = new ModelTaskVo();
		task.setProject_id(project_id);
		task.setIs_parent_poject(is_parent_project);// 此处的is_parent_project判断所查询的任务是不是直接的根父项目下的任务
		task.setPsearch(search);
		task.setCompany_id(company_id);
		Map<String, String> map1 = (Map<String, String>) param.get("page");
		Page page = PageUtils.createPage(map1);
		page = modelTaskService.page1(page, task);
		List<ModelTaskVo> mts = (List<ModelTaskVo>) page.getDataList();
		for (ModelTaskVo m : mts) {
			if (m.getClazz() != null) {
				System.out.println(m.getClazz().getContent());
			} else {
				System.out.println(m.getClass_id());
			}
		}
		map.put("total", page.getTotal());
		map.put("pageSize", page.getLimit());
		map.put("pageNo", page.getCurrentPage());
		map.put("dataList", mts);
		// List<ModelTaskVo> task = modelTaskService.getTaskByProjectId(map);//
		// 此处的modelTask是建模与动画灯光合并在一起的任务集合
		return new Result(Result.SUCCESS, "成功", map);
	}

	/**
	 * 获取任务搜索信息
	 * 
	 * @return
	 */
	@RequestMapping("/getModelTaskSearch")
	@ResponseBody
	public Result getModelTaskSearch() {
		List<ProjectSearch> search = modelTaskService.getModelTaskSearch();
		return new Result(Result.SUCCESS, "成功", search);
	}

	/**
	 * 添加任务
	 * 
	 * @param modelTask建模任务对象
	 * @param beginTime开始日期
	 * @param endTime结束日期
	 * @param softwares软件
	 * @param is_parent_project项目是否是根项目
	 * @return
	 */
	@RequestMapping("/create")
	public @ResponseBody
	Result create(ModelTaskVo modelTask, String beginTime, String endTime,
			String softwares, String is_parent_project, HttpSession session) {
		Long company_id = (Long) session.getAttribute("projectCompany");
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Long task_id = modelTaskService.getTaskId(modelTask);
		if (task_id != null) {
			return new Result(Result.FAILURE, "该任务编号已存在，请更换编号", null);
		}
		List<Long> ids = new ArrayList<Long>();
		ids.add(modelTask.getProject_id());
		Map<String, Object> mapIds = new HashMap<String, Object>();
		mapIds.put("ids", ids);
		String errorInfo = "";
		List<SubProject> subProjects = null;
		/**
		 * 用来判断父项目是子项目还是根项目
		 */
		boolean flag = true;
		if (is_parent_project.equals("1")) {// 当其为1时表示任务所属项目是根项目
			flag = false;
			subProjects = projectService.getParentSubProjects(mapIds);// 查询子项目数据表中父项目是根父项目的子项目列表
		} else {
			subProjects = projectService.getSubProjects(mapIds);// 查询子项目的子项目
		}
		if (subProjects != null && subProjects.size() > 0) {// 当子项目存在，则不能在该项目中创建任务
			return new Result(Result.FAILURE, "该项目下已有子项目了，不能创建任务", null);
		}

		CompanyFilesVo parentvo = new CompanyFilesVo();
		parentvo.setFor_id(modelTask.getProject_id());
		if (flag) {
			parentvo.setFile_type(ConstantUtil.ZC_SUB_PROJECT);
		} else {
			parentvo.setFile_type(ConstantUtil.ZC_PROJECT);
		}
		parentvo = companyFilesService.selectForObject(parentvo);
		if (parentvo == null) {
			errorInfo = "创建失败，无父文件夹.";
			return new Result(Result.FAILURE, errorInfo, null);
		}
		String path = ConstantUtil.USER_PATH + uuid + "/data/";
		String folder_path = path + modelTask.getCommit_path();
		/**
		 * 判断 默认task文件夹
		 */
		CompanyFilesVo vo = new CompanyFilesVo();
		if (!OSSFilesUtil.isFile(folder_path + "/")) {
			String modelUrl = modelTask.getCommit_path().substring(0,
					modelTask.getCommit_path().lastIndexOf("/"));
			String modelFileName = modelUrl
					.substring(modelUrl.lastIndexOf("/") + 1);
			String taskUrl = modelUrl.substring(0, modelUrl.lastIndexOf("/"));
			String taskFileName = taskUrl
					.substring(taskUrl.lastIndexOf("/") + 1);
			if (!taskFileName.equals("task") || !modelFileName.equals("Model")) {
				errorInfo = "任务提交路径无效，请重新选择";
				return new Result(Result.FAILURE, errorInfo, null);
			}
			/**
			 * 判断是否存在task
			 */
			CompanyFilesVo taskvo = new CompanyFilesVo();
			taskvo.setFile_url(taskUrl);
			taskvo = companyFilesService.selectForObject(taskvo);
			if (taskvo == null) {
				taskvo = new CompanyFilesVo();
				taskvo.setCompany_id(company_id);
				taskvo.setIs_file(ConstantUtil.IS_FILE);
				taskvo.setParent_id(parentvo.getId());
				taskvo.setCreator_id(user_id);
				taskvo.setFile_name(taskFileName);
				taskvo.setFile_url(taskUrl);
				OSSFilesUtil.addFile(path + taskUrl);
				companyFilesService.save(taskvo);
			}
			/**
			 * 判断是否存在Model文件夹
			 */
			CompanyFilesVo modelVo = new CompanyFilesVo();
			modelVo.setFile_url(modelUrl);
			modelVo = companyFilesService.selectForObject(modelVo);
			if (modelVo == null) {
				modelVo = new CompanyFilesVo();
				modelVo.setCompany_id(company_id);
				modelVo.setIs_file(ConstantUtil.IS_FILE);
				modelVo.setParent_id(taskvo.getId());
				modelVo.setCreator_id(user_id);
				modelVo.setFile_name(modelFileName);
				modelVo.setFile_url(modelUrl);
				OSSFilesUtil.addFile(path + modelUrl);
				companyFilesService.save(modelVo);
			}
			OSSFilesUtil.addFile(folder_path);
			vo.setFile_type(ConstantUtil.ZC_TASK);
			vo.setTask_type(ConstantUtil.MODEL_TASK);
			vo.setCompany_id(company_id);
			vo.setIs_file(ConstantUtil.IS_FILE);
			vo.setParent_id(modelVo.getId());
			vo.setCreator_id(user_id);
			vo.setFile_url(modelTask.getCommit_path());
			vo.setFile_name(modelTask.getCommit_path().substring(
					modelTask.getCommit_path().lastIndexOf("/") + 1,
					modelTask.getCommit_path().length()));
			modelTask.setCompanyFiles_id(0l);
		} else {
			if (modelTask.getCompanyFiles_id() == null
					|| modelTask.getCompanyFiles_id() == 0) {
				errorInfo = "不要故意整系统君，谢谢!";
				return new Result(Result.FAILURE, errorInfo, null);
			}
			/**
			 * 查询选择的文件夹，并更新为任务文件夹
			 */
			vo.setId(modelTask.getCompanyFiles_id());
			vo = companyFilesService.selectForObject(vo);
			if (vo != null) {
				if (vo.getFor_id() != null) {
					errorInfo = "此文件夹已被指定，请重新选择!";
					return new Result(Result.FAILURE, errorInfo, null);
				}
				vo.setFile_type(ConstantUtil.ZC_TASK);
				vo.setTask_type(ConstantUtil.MODEL_TASK);
			} else {
				errorInfo = "无效的文件夹路径，请重新选择";
				return new Result(Result.FAILURE, errorInfo, null);
			}
		}
		return modelTaskService.create(modelTask, beginTime, endTime,
				softwares, is_parent_project, session, vo);
	}

	/**
	 * 修改项目
	 * 
	 * @param modelTask建模任务对象
	 * @param beginTime开始日期
	 * @param endTime结束日期
	 * @param softwares软件
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody
	Result update(ModelTaskVo modelTask, String beginTime, String endTime,
			String softwares, HttpSession session) {
		Long task_id = modelTaskService.getTaskId(modelTask);
		if (task_id != null && !task_id .equals(modelTask.getId())){
			return new Result(Result.FAILURE, "该任务编号已存在，请更换编号", null);
		}
		Long company_id = (Long) session.getAttribute("projectCompany");
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		ModelTaskVo modelTask1 = new ModelTaskVo();
		modelTask1.setId(modelTask.getId());
		modelTask1 = modelTaskService.getModelTaskById(modelTask1);
		String errorInfo = "";
		String folder_path = ConstantUtil.USER_PATH + uuid + "/data/"
				+ modelTask.getCommit_path();
		if (!OSSFilesUtil.isFile(folder_path + "/")) {
			errorInfo = "无效的文件夹路径，请重新选择";
			return new Result(Result.FAILURE, errorInfo, null);
		}
		CompanyFilesVo vo = new CompanyFilesVo();
		/**
		 * 判断该路径是否存在
		 */
		if (OSSFilesUtil.isFile(folder_path + "/")) {
			/**
			 * 判断当前选中文件夹是否更换
			 */
			if (modelTask.getCompanyFiles_id() != null
					&& !modelTask.getCommit_path().equals(
							modelTask1.getCommit_path())) {
				/**
				 * 查询选择的文件夹，并更新为项目文件夹
				 */
				vo = new CompanyFilesVo();
				vo.setId(modelTask.getCompanyFiles_id());
				vo = companyFilesService.selectForObject(vo);
				if (vo != null) {
					if (vo.getFor_id() != null) {
						errorInfo = "此文件夹已被指定，请重新选择!";
						return new Result(Result.FAILURE, errorInfo, null);
					} else {
						/**
						 * 原文件夾項目文件夾能力丟失
						 */
						CompanyFilesVo vo1 = new CompanyFilesVo();
						vo1.setFor_id(modelTask.getId());
						vo1.setFile_type(ConstantUtil.ZC_TASK);
						vo1.setTask_type(ConstantUtil.ANIMATION_LIGHT_TASK);
						vo1 = companyFilesService.selectForObject(vo1);
						vo1.setFor_id(null);
						vo1.setFile_type(null);
						vo1.setTask_type(null);
						companyFilesService.update(vo1);
						/**
						 * 选中的文件夹更新为项目文件夹
						 */
						vo.setFor_id(modelTask.getId());
						vo.setFile_type(ConstantUtil.ZC_TASK);
						vo.setTask_type(ConstantUtil.MODEL_TASK);
						companyFilesService.updateIgnoreNull(vo);
					}
				} else {
					errorInfo = "无效的文件夹路径，请重新选择";
					return new Result(Result.FAILURE, errorInfo, null);
				}
			}
		} else {
			errorInfo = "无效的文件夹路径，请重新选择";
			return new Result(Result.FAILURE, errorInfo, null);
		}
		modelTask.setCompany_id(company_id);
		return modelTaskService
				.update(modelTask, beginTime, endTime, softwares);
	}

	// TODO删除对于金钱的处理
	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody
	Result delete(String ids) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("ids", ids.split(","));
		List<ModelTaskVo> mTasks = modelTaskService.getTasksByIds(map);
		for (ModelTaskVo m : mTasks) {
			companyFilesService.deleteRelationByFileUrl(m.getCommit_path());
		}
		modelTaskService.deleteAll(map);
		return new Result("删除成功!");
	}

	/**
	 * 根据id查找建模任务数据，供修改用
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("/getModelTaskById")
	@ResponseBody
	public Result getModelTaskById(ModelTaskVo m) {
		ModelTask task = modelTaskService.getModelTaskById(m);
		return new Result(Result.SUCCESS, "成功", task);
	}

	/**
	 * 获取任务软件
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/getTaskSoftware")
	@ResponseBody
	public Result getProjectSoftware(CompanySoftwareVo vo, HttpSession session) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		vo.setCompany_id(company_id);
		return new Result(Result.SUCCESS, "成功",
				companySoftwareService.selectForList(vo));
	}

	/**
	 * 获取任务类型
	 * 
	 * @return
	 */
	@RequestMapping("/getModelType")
	@ResponseBody
	public Result getModelType() {
		List<ModelType> modelTypes = modelTaskService.getModelTypes();
		return new Result(Result.SUCCESS, "成功", modelTypes);
	}

	/**
	 * 获取任务发布类型
	 */
	@RequestMapping("/getPublishType")
	@ResponseBody
	public Result getPublishType() {
		List<PublishType> publishTypes = modelTaskService.getPublishTypes();
		return new Result(Result.SUCCESS, "成功", publishTypes);
	}

	/**
	 * 获取难易度
	 * 
	 * @return
	 */
	@RequestMapping("/getDegree")
	@ResponseBody
	public Result getDegree() {
		List<Degree> degrees = modelTaskService.getDegrees();
		return new Result(Result.SUCCESS, "成功", degrees);
	}

	/**
	 * 获取任务类目
	 * @return
	 */
	@RequestMapping("/getClazz")
	@ResponseBody
	public Result getClazz(HttpSession session,HttpServletRequest request) {
		System.out.println("session:"+session);
		List<Clazz> clazz = modelTaskService.getClazz();
		System.out.println(clazz);
		session.setAttribute("age", null);
		return new Result(Result.SUCCESS, "成功", clazz);
	}

	public Result importModelTask(String filePath, Long company_id,
			Long class_id, Long project_id, String errorInfo,
			String is_parent_project, HttpSession session) throws Exception {
		errorInfo = "";
		try {
			errorInfo += modelTaskService
					.importModelTask(filePath, company_id, class_id,
							project_id, is_parent_project, errorInfo, session);
		} catch (RuntimeException e) {
			errorInfo = e.getMessage();
		}
		if (errorInfo.trim().length() == 0) {
			return new Result(Result.SUCCESS, "成功导入", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}
	}

	/**
	 * 批量导入建模任务
	 * 
	 * @param filePath文件路径
	 * @param company_id公司id
	 * @param class_id类目id
	 * @param project_id项目id
	 * @param errorInfo错误信息
	 * @param is_parent_project项目是否是父项目
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importModelTask")
	@ResponseBody
	public Result importTask(String filePath, Long class_id, Long project_id,
			String errorInfo, String is_parent_project, HttpSession session)
			throws Exception {
		List<Long> ids = new ArrayList<Long>();
		ids.add(project_id);
		Map<String, Object> mapIds = new HashMap<String, Object>();
		mapIds.put("ids", ids);
		List<SubProject> subProjects = null;
		if (is_parent_project.equals("1")) {// 当其为1时表示任务所属项目是根项目
			subProjects = projectService.getParentSubProjects(mapIds);// 查询子项目数据表中父项目是根父项目的子项目列表
		} else {
			subProjects = projectService.getSubProjects(mapIds);// 查询子项目的子项目
		}
		if (subProjects != null && subProjects.size() > 0) {// 当子项目存在，则不能在该项目中创建任务
			return new Result(Result.FAILURE, "该项目下已有子项目了，不能导入任务", null);
		}
		filePath = session.getServletContext().getRealPath("/") + filePath;
		Long company_id = (Long) session.getAttribute("sessionCompany");
		return importModelTask(filePath, company_id, class_id, project_id,
				errorInfo, is_parent_project, session);
	}

	/**
	 * 获取发布任务搜索信息
	 * 
	 * @return
	 */
	@RequestMapping("/getPublishTaskSearch")
	@ResponseBody
	public Result getPublishTaskSearch() {
		List<ProjectSearch> search = modelTaskService.getPublishTaskSearch();
		return new Result(Result.SUCCESS, "成功", search);
	}

	private void changeTaskTime(String task_type, long id) {
		if (task_type.equals("建模")) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(id);
			mTask = modelTaskService.getModelTaskById(mTask);
			Date begin_time = mTask.getBegin_time();
			Date now = new Date();
			now.setTime(System.currentTimeMillis() + 60 * 1000);
			mTask.setBegin_time(now);
			Date end_time = mTask.getEnd_time();
			end_time.setTime(end_time.getTime() + now.getTime()
					- begin_time.getTime());
			mTask.setEnd_time(end_time);
			modelTaskService.updateModelTask(mTask);
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(id);
			aTask = animationLightTaskService.getAnimationLightTaskById(aTask);
			Date begin_time = aTask.getBegin_time();
			Date now = new Date();
			now.setTime(System.currentTimeMillis() + 60 * 1000);
			aTask.setBegin_time(now);
			Date end_time = aTask.getEnd_time();
			end_time.setTime(end_time.getTime() + now.getTime()
					- begin_time.getTime());
			aTask.setEnd_time(end_time);
			animationLightTaskService.updateAnimationLightTask(aTask);
		}
	}

	/**
	 * 发布任务，注意发布中的任务有指派任务，倘若有指派任务，则需要通知指派者
	 * 
	 * @param publish_info
	 * @return
	 */
	@RequestMapping("/publishTask")
	@ResponseBody
	public Result publishTask(String publish_info, String flag,
			double timeliness, String password) {
		// TODO设置发布金额，将该金钱冻住
		Map<String, List<Long>> modelTaskMap = new HashMap<String, List<Long>>();// 保存发布建模任务id的map
		Map<String, List<Long>> animationLightTaskMap = new HashMap<String, List<Long>>();// 保存发布动画灯光任务id的map
		List<Long> modelTaskIds = new ArrayList<Long>();// 发布建模id集合
		List<Long> animationLightTaskIds = new ArrayList<Long>();// 发布动画灯光id集合
		String[] publish = publish_info.split(",");// 发布传来的id信息
		for (String s : publish) {
			String[] info = s.split(":");
			if (info[1].equals("建模")) {// 当任务类型为建模时，将id存在建模集合id中
				modelTaskIds.add(Long.parseLong(info[0]));
			} else {
				animationLightTaskIds.add(Long.parseLong(info[0]));// 否则将id存在动画灯光集合id中
			}
		}
		Map<String, Object> m = new HashMap<String, Object>();
		String msg = "";
		boolean hasGroupMember = false;
		for (String s : publish) {
			String[] info = s.split(":");
			if (info[1].equals("建模")) {// 当任务类型为建模时，将id存在建模集合id中
				modelTaskIds.add(Long.parseLong(info[0]));
				ModelTaskVo mTask = new ModelTaskVo();
				mTask.setId(Long.parseLong(info[0]));
				mTask = modelTaskService.getModelTaskById(mTask);
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("project_id", mTask.getProject_id());
				mp.put("is_parent_project", mTask.getIs_parent_poject());
				Long isGroupMember = modelTaskService.hasGroupMemeber(mp);
				if (isGroupMember == null) {
					hasGroupMember = true;
					msg += mTask.getName() + "、";
				}
				// 任务发布公司法人信息
				UserCompany reUser = userCompanyService.companygetUser(mTask
						.getCompany_id());

				// 冻结资金
				FreezeRecord saveFreezeRecord = new FreezeRecord();
				saveFreezeRecord.setCompany_id(mTask.getCompany_id());
				saveFreezeRecord.setTask_id(mTask.getId());
				saveFreezeRecord.setTask_type(mTask.getClass_id().toString());
				saveFreezeRecord.setTrade_type("1");
				saveFreezeRecord.setFreeze_price(Double.parseDouble(mTask
						.getPrice()));

				saveFreezeRecord.setUser_id(reUser.getUse_id());
				String retn = freezeRecordService.saveFreezeRecord(
						saveFreezeRecord, password, "pass");
				if (!"1".equals(retn) && !"RY0078".equals(retn)) {
					return new Result(retn, null); // 冻结失败
				}
			} else {
				animationLightTaskIds.add(Long.parseLong(info[0]));// 否则将id存在动画灯光集合id中
				AnimationLightTaskVo aTask = new AnimationLightTaskVo();
				aTask.setId(Long.parseLong(info[0]));
				aTask = animationLightTaskService
						.getAnimationLightTaskById(aTask);
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("project_id", aTask.getProject_id());
				mp.put("is_parent_project", aTask.getIs_parent_poject());
				Long isGroupMember = modelTaskService.hasGroupMemeber(mp);
				if (isGroupMember == null) {
					hasGroupMember = true;
					msg += aTask.getPattern_number() + "、";
				}
				// 任务发布公司法人信息
				UserCompany reUser = userCompanyService.companygetUser(aTask
						.getCompany_id());
				FreezeRecord saveFreezeRecord = new FreezeRecord();
				saveFreezeRecord.setCompany_id(aTask.getCompany_id());
				saveFreezeRecord.setTask_id(aTask.getId());
				saveFreezeRecord.setTask_type(aTask.getClass_id().toString());
				saveFreezeRecord.setTrade_type("1");
				saveFreezeRecord.setFreeze_price(Double.parseDouble(aTask
						.getPrice()));
				saveFreezeRecord.setUser_id(reUser.getUse_id());
				String retn = freezeRecordService.saveFreezeRecord(
						saveFreezeRecord, password, "pass");
				if (!"1".equals(retn) && !"RY0078".equals(retn)) {
					return new Result(retn, null); // 冻结失败
				}
			}
		}
		if (hasGroupMember) {
			m.put("group", true);
			msg = msg.substring(0, msg.length() - 1);
			msg += "等任务没有团队，需要添加团队后才能发布！";
			return new Result(Result.FAILURE, msg, m);
		} else {
			m.put("group", false);
		}
		String message = "";
		boolean is_out_time = false;
		boolean outtime = false;
		modelTaskMap.put("ids", modelTaskIds);// 将建模id集合存在建模idmap中
		if (modelTaskIds.size() > 0) {
			List<ModelTaskVo> modelTasks = modelTaskService
					.getBeginTimeBeforeNowModelTask(modelTaskMap);// 查看给定的建模id集合中有无当前时间已过开始日期
			if (modelTasks.size() > 0) {// 当发布任务中有当前时间过了开始日期,则提醒不能发布
				for (ModelTaskVo modelTask : modelTasks) {
					message += modelTask.getName() + "、";
					if (flag != null && flag.equals("1")) {
						changeTaskTime("建模", modelTask.getId());
					} else {
						is_out_time = true;
					}
				}
			}
		}

		animationLightTaskMap.put("ids", animationLightTaskIds);// 将动画灯光id集合存在动画灯光idmap中
		if (animationLightTaskIds.size() > 0) {
			List<AnimationLightTask> animationTasks = animationLightTaskService
					.getBeginTimeBeforeNowAnimationLightTask(animationLightTaskMap);// 查看给定的动画灯光id集合中有无当前时间已过开始日期
			if (animationTasks.size() > 0) {// 当发布任务中有当前时间过了开始日期,则提醒不能发布
				for (AnimationLightTask animationLightTask : animationTasks) {
					if (flag != null && flag.equals("1")) {
						changeTaskTime("动画", animationLightTask.getId());
					} else {
						is_out_time = true;
					}
					message += animationLightTask.getPattern_number() + "、";
				}
			}
		}
		if (is_out_time) {
			m.put("time", true);
			message = message.substring(0, message.length() - 1);
			message += "等任务开始日期已过去，是否调整并发布？";
			outtime = true;
			return new Result(Result.FAILURE, message, m);
		} else {
			m.put("time", false);
		}
		modelTaskService.publishTask(modelTaskMap, animationLightTaskMap);// 发布一般任务
		modelTaskService.publishPointTask(modelTaskMap, animationLightTaskMap);// 发布指派任务
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("timeliness", timeliness);// 发布任务的时效性保存在map中
		if (modelTaskIds.size() > 0) {// 当发布的任务中有建模任务，则将建模任务id集合存到map中
			map.put("modelTaskIds", modelTaskIds);
			modelTaskService.updateModelTaskTimeLiness(map);// 修改发布建模任务的时效性
			List<Long> publishModelTaskListIds = modelTaskService
					.getPublishModelTaskIds(modelTaskMap);// 获取发布建模任务中状态是发布中的id集合
			List<Long> publishPointModelTaskListIds = modelTaskService
					.getPublishPointModelTaskIds(modelTaskMap);// 获取发布建模任务中状态是指派中的id集合
			publishModelTaskIds.put("ids", publishModelTaskListIds);// 将发布的建模任务状态为发布中的id集合存在map中
			publishPointModelTaskIds.put("ids", publishPointModelTaskListIds);// 将发布的建模任务状态为指派中的id集合存在map中
		}
		if (animationLightTaskIds.size() > 0) {// 当发布的任务中有动画灯光任务，则将动画灯光任务id集合存到map中
			map.put("animationLightTaskIds", animationLightTaskIds);
			modelTaskService.updateAnimationLightTaskTimeLiness(map);// 修改发布动画灯光任务的时效性
			List<Long> publishAnimationLightTaskListIds = modelTaskService
					.getPublishAnimationLightTaskIds(animationLightTaskMap);// 获取发布动画灯光任务中状态是发布中的id集合
			List<Long> publishPointAnimationLightTaskListIds = modelTaskService
					.getPublishPointAnimationLightTaskIds(animationLightTaskMap);// 获取发布动画灯光任务中状态是指派中的id集合
			publishAnimationLightTaskIds.put("ids",
					publishAnimationLightTaskListIds);// 将发布的动画灯光任务状态为发布中的id集合存在map中
			publishPointAnimationLightTaskIds.put("ids",
					publishPointAnimationLightTaskListIds);// 将发布的动画灯光98任务状态为指派中的id集合存在map中
		}
		RepeatBackTask t1 = new RepeatBackTask();// 收回发布任务过了时效性的任务
		RepeatBackPointTask t2 = new RepeatBackPointTask();// 收回发布任务过了时效性的指派任务
		Timer task = new Timer();
		task.schedule(t1, (long) (timeliness * 60 * 60 * 1000));
		task.schedule(t2, (long) (timeliness * 60 * 60 * 1000));
		// task.schedule(t1, 1000 * 60 * 5);
		// task.schedule(t2, 1000 * 60 * 5);
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 收回发布任务过了时效性的任务定时器
	 * 
	 * @author Administrator
	 * 
	 */
	class RepeatBackTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("repeatBackTask...");
			List<Long> ids = publishModelTaskIds.get("ids");
			if (ids != null) {
				for (Long l : ids) {
					System.out.print(l + ",");
				}
			}
			System.out.println();
			List<Long> ids1 = publishAnimationLightTaskIds.get("ids");
			if (ids1 != null) {
				for (Long l : ids1) {
					System.out.print(l + ",");
				}
			}

			System.out.println();
			if (publishModelTaskIds.get("ids") != null
					&& publishModelTaskIds.get("ids").size() > 0) {
				Map<String, Object> modelTaskMap = new HashMap<String, Object>();
				modelTaskMap.put("task_type", "建模");
				modelTaskMap.put("ids", publishModelTaskIds.get("ids"));
				for (Long task_id : publishModelTaskIds.get("ids")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("task_id", task_id);
					map.put("task_type", "建模");
					Long user_id = modelTaskService
							.getUserIdOfModelTaskApply(map);// 根据task_id,task_type这两个字段，获取领取最早的那一个的user_id
					if (user_id != null) {// 倘若获取的user_id不为空，则同意他的领取
						agreeGetTask(task_id, "建模", "1", user_id, null);
					}
				}
				modelTaskService.deleteModelTaskApplys(modelTaskMap);// 删除发布建模任务中的领取任务信息
			}
			if (publishAnimationLightTaskIds.get("ids") != null
					&& publishAnimationLightTaskIds.get("ids").size() > 0) {
				Map<String, Object> animationTaskMap = new HashMap<String, Object>();
				animationTaskMap.put("task_type1", "动画");
				animationTaskMap.put("task_type2", "灯光");
				animationTaskMap.put("ids",
						publishAnimationLightTaskIds.get("ids"));
				for (Long task_id : publishAnimationLightTaskIds.get("ids")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("task_id", task_id);
					Long user_id = modelTaskService
							.getUserIdOfAnimationTaskApply(map);// 根据task_id,task_type这两个字段，获取领取最早的那一个的user_id
					if (user_id != null) {
						agreeGetTask(task_id, "动画", "1", user_id, null);// 倘若获取的user_id不为空，则同意他的领取
					}
				}
				modelTaskService.deleteAnimationTaskApply(animationTaskMap);// 删除发布动画灯光任务中的领取任务信息
			}
			modelTaskService.repeatBackTask(publishModelTaskIds,
					publishAnimationLightTaskIds);// 收回发布任务过了时效性的任务
		}
	}

	/**
	 * 收回发布任务过了时效性的指派任务定时器
	 * 
	 * @author Administrator
	 * 
	 */
	class RepeatBackPointTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("RepeatBackPointTask...");
			List<Long> ids = publishPointModelTaskIds.get("ids");
			if (ids != null) {
				for (Long l : ids) {
					System.out.print(l + ",");
				}
			}

			System.out.println();
			List<Long> ids1 = publishPointAnimationLightTaskIds.get("ids");
			if (ids1 != null) {
				for (Long l : ids1) {
					System.out.print(l + ",");
				}
			}
			System.out.println();
			modelTaskService.repeatBackPointTask(publishPointModelTaskIds,
					publishPointAnimationLightTaskIds);
		}

	}

	// 重新发布已放弃中的任务
	// user_id已存在怎么办
	@RequestMapping("/repeatPublishTask")
	@ResponseBody
	public Result repeatPublisTask(Long task_id, String task_type,
			Long publish_type_id, Long user_id, HttpSession session,
			String password) {
		// TODO设置发布金额，将该金钱冻住,
		// 发布时，其发布类型需要改变不
		Long company_id = (Long) session.getAttribute("sessionCompany");
		UserCompany u1 = userCompanyService.companygetUser(company_id);
		Long userId = ((User) session.getAttribute("loginUser")).getId();
		if (userId == null) {
			return new Result("RY0008", null);
		}
		Long isMaker = modelTaskService.getIsMaker(user_id);
		Long isChecker = modelTaskService.isChecker(user_id);
		if (isMaker == null && isChecker == null) {
			if (!userId.equals(u1.getUse_id())) {
				PrivilegeInfoVo info = new PrivilegeInfoVo();
				info.setCompany_id(company_id);
				info.setUser_id(userId);
				info.setPrivilege_url("repeatPublishTask");
				info.setParent_id(14L);
				List<PrivilegeInfoVo> privilegeList = privilegeInfoService
						.isPrivilegeByUrl(info);
				if (privilegeList.size() == 0) {
					return new Result(Result.FAILURE, "您沒用该访问权限", null);
				}
			}
		}
		Map<String, List<Long>> modelTaskMap = new HashMap<String, List<Long>>();
		Map<String, List<Long>> animationLightTaskMap = new HashMap<String, List<Long>>();
		List<Long> modelTaskIds = new ArrayList<Long>();
		List<Long> animationLightTaskIds = new ArrayList<Long>();
		updatePublishTypeOfTask(publish_type_id + "", task_id + "", task_type,
				user_id);
		if (task_type.equals("建模")) {
			modelTaskIds.add(task_id);
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask = modelTaskService.getModelTaskById(mTask);
			// 冻结资金
			FreezeRecord saveFreezeRecord = new FreezeRecord();
			saveFreezeRecord.setCompany_id(mTask.getCompany_id());
			saveFreezeRecord.setTask_id(mTask.getId());
			saveFreezeRecord.setTask_type(mTask.getClass_id().toString());
			saveFreezeRecord.setTrade_type("1");
			saveFreezeRecord.setFreeze_price(Double.parseDouble(mTask
					.getPrice()));
			UserCompany reUser = userCompanyService.companygetUser(mTask
					.getCompany_id());
			saveFreezeRecord.setUser_id(reUser.getUse_id());
			String retn = freezeRecordService.saveFreezeRecord(
					saveFreezeRecord, password, "pass");
			if (!"1".equals(retn) && !"RY0078".equals(retn)) {
				return new Result(retn, null); // 冻结失败
			}

		} else {
			animationLightTaskIds.add(task_id);
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask = animationLightTaskService.getAnimationLightTaskById(aTask);
			// 任务发布公司法人信息
			UserCompany reUser = userCompanyService.companygetUser(aTask
					.getCompany_id());
			FreezeRecord saveFreezeRecord = new FreezeRecord();
			saveFreezeRecord.setCompany_id(aTask.getCompany_id());
			saveFreezeRecord.setTask_id(aTask.getId());
			saveFreezeRecord.setTask_type(aTask.getClass_id().toString());
			saveFreezeRecord.setTrade_type("1");
			saveFreezeRecord.setFreeze_price(Double.parseDouble(aTask
					.getPrice()));
			saveFreezeRecord.setUser_id(reUser.getUse_id());
			String retn = freezeRecordService.saveFreezeRecord(
					saveFreezeRecord, password, "pass");
			if (!"1".equals(retn) && !"RY0078".equals(retn)) {
				return new Result(retn, null); // 冻结失败
			}
		}
		modelTaskMap.put("ids", modelTaskIds);
		animationLightTaskMap.put("ids", animationLightTaskIds);
		modelTaskService.publishTask(modelTaskMap, animationLightTaskMap);// 发布一般任务
		modelTaskService.publishPointTask(modelTaskMap, animationLightTaskMap);// 发布指派任务
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 将任务指派给制作者
	 * 
	 * @param pointerInfo
	 * @param user_id
	 * @param model_type
	 * @return
	 */
	@RequestMapping("/pointTaskToUser")
	@ResponseBody
	public Result pointTaskToUser(String pointerInfo, Long user_id,
			String model_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> pointerIds = new ArrayList<Long>();
		String[] ids = pointerInfo.split(",");
		for (String s : ids) {
			pointerIds.add(Long.valueOf(s));
		}
		map.put("ids", pointerIds);
		map.put("user_id", user_id);
		if (model_type.equals("建模")) {
			modelTaskService.pointerModelTaskToUser(map);
		} else {
			modelTaskService.pointerAnimationLightTaskToUser(map);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 制作人同意指派与否
	 * 
	 * @param task_id
	 * @param task_type
	 * @param point_status
	 * @return
	 */
	@RequestMapping("/agreePointTask")
	@ResponseBody
	public Result agreePointTask(Long task_id, String task_type,
			String point_status, HttpSession session) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		CompanyFilesVo v = new CompanyFilesVo();
		v.setUser_id(user_id);
		v.setFor_id(task_id);
		v.setTask_type(task_type);
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("task_id", task_id);
		map.put("point_status", Long.parseLong(point_status));
		System.out.println("map:" + map);
		if (task_type.equals("建模")) {
			modelTaskService.updatePointStatus(map);
			if (point_status.equals("1")) {
				taskMap.put("task_id", task_id);
				Timer timer = new Timer();
				RepeatBackModelTask rbTask = new RepeatBackModelTask();
				timer.schedule(rbTask, 1000 * 60 * 60 * 12);// 从制作者同意指派后12小时还未开始，则收回
				// timer.schedule(rbTask, 1000 * 60 * 3);
				ModelTaskVo m = new ModelTaskVo();
				m.setId(task_id);
				m = modelTaskService.getModelTaskById(m);
				v.setCompany_id(m.getCompany_id());
				v.setProject_id(m.getProject_id());
				v.setIs_parent_project(m.getIs_parent_poject());
				companyFilesService.addGroleProvilege(v);
			}
		} else {
			animationLightTaskService.updatePointStatus(map);
			if (point_status.equals("1")) {
				taskMap.put("task_id", task_id);
				Timer timer = new Timer();
				RepeatBackAnimationTask rbTask = new RepeatBackAnimationTask();
				timer.schedule(rbTask, 1000 * 60 * 60 * 12);// 从制作者同意指派后12小时还未开始，则收回
				// timer.schedule(rbTask, 1000 * 60 * 3);
				AnimationLightTaskVo a = new AnimationLightTaskVo();
				a.setId(task_id);
				a = animationLightTaskService.getAnimationLightTaskById(a);
				v.setCompany_id(a.getCompany_id());
				v.setProject_id(a.getProject_id());
				v.setIs_parent_project(a.getIs_parent_poject());
				companyFilesService.addGroleProvilege(v);
			}
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	// 收回指派建模任务超过未开始任务有效期12小时定时器
	class RepeatBackModelTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("backPointModelTask:" + taskMap.get("task_id"));
			modelTaskService.repeatBackModelTaskById(taskMap);
		}
	}

	// 收回指派动画灯光任务超过未开始任务有效期12小时定时器
	class RepeatBackAnimationTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("backPointAnimationTask:"
					+ taskMap.get("task_id"));
			animationLightTaskService.repeatBackAnimationTaskById(taskMap);
		}
	}

	/**
	 * 领取任务
	 * 
	 * @return
	 */
	@RequestMapping("/getTask")
	@ResponseBody
	public Result getTask(Long task_id, String task_type, HttpSession session,
			String password) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		if (task_type.equals("建模")) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask = modelTaskService.getModelTaskById(mTask);
			// 冻结资金
			FreezeRecord saveFreezeRecord = new FreezeRecord();
			saveFreezeRecord.setCompany_id(null);
			saveFreezeRecord.setTask_id(mTask.getId());
			saveFreezeRecord.setTask_type(mTask.getClass_id().toString());
			saveFreezeRecord.setTrade_type("2");
			saveFreezeRecord.setFreeze_price(Double.parseDouble(mTask
					.getPrice()) * 0.25);
			saveFreezeRecord.setUser_id(user_id);
			String retn = freezeRecordService.saveFreezeRecord(
					saveFreezeRecord, password, "pass");
			if (!"1".equals(retn) && !"RY0078".equals(retn)) {
				return new Result(retn, null); // 冻结失败
			}
			Long num = mTask.getGetTaskNums();
			if (num == null) {
				num = 1L;
			} else {
				num++;
			}
			mTask.setGetTaskNums(num);
			mTask.setTask_status("领取中");
			modelTaskService.updateIgnoreNull(mTask);

		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask = animationLightTaskService.getAnimationLightTaskById(aTask);
			// 冻结资金
			FreezeRecord saveFreezeRecord = new FreezeRecord();
			saveFreezeRecord.setCompany_id(null);
			saveFreezeRecord.setTask_id(aTask.getId());
			saveFreezeRecord.setTask_type(aTask.getClass_id().toString());
			saveFreezeRecord.setTrade_type("2");
			saveFreezeRecord.setFreeze_price(Double.parseDouble(aTask
					.getPrice()) * 0.25);
			saveFreezeRecord.setUser_id(user_id);
			String retn = freezeRecordService.saveFreezeRecord(
					saveFreezeRecord, password, "pass");
			if (!"1".equals(retn) && !"RY0078".equals(retn)) {
				return new Result(retn, null); // 冻结失败
			}
			Long num = aTask.getGetTaskNums();
			if (num == null) {
				num = 1L;
			} else {
				num++;
			}
			aTask.setGetTaskNums(num);
			aTask.setTask_status("领取中");
			animationLightTaskService.updateIgnoreNull(aTask);
		}
		TaskApply apply = new TaskApply();
		apply.setUser_id(user_id);
		apply.setTask_id(task_id);
		apply.setCreate_time(new Date());
		apply.setTask_type(task_type);
		Long id = modelTaskService.getTaskApply(apply);
		if (id == null) {
			modelTaskService.addTaskApply(apply);// 在申请领取表中加入记录
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 公司同意制作人领取任务与否
	 * 
	 * @param task_id
	 * @param task_type
	 * @param point_status
	 * @return
	 */
	@RequestMapping("/agreeGetTask")
	@ResponseBody
	public Result agreeGetTask(Long task_id, String task_type,
			String agree_status, Long user_id, HttpSession session) {
		if (session != null) {
			Long company_id = (Long) session.getAttribute("sessionCompany");
			UserCompany u1 = userCompanyService.companygetUser(company_id);
			Long userId = ((User) session.getAttribute("loginUser")).getId();
			if (userId == null) {
				return new Result("RY0008", null);
			}

			Long isMaker = modelTaskService.getIsMaker(userId);
			Long isChecker = modelTaskService.isChecker(userId);
			if (isMaker == null && isChecker == null) {
				if (!userId.equals(u1.getUse_id())) {
					PrivilegeInfoVo info = new PrivilegeInfoVo();
					info.setCompany_id(company_id);
					info.setUser_id(userId);
					info.setPrivilege_url("agreeGetTask");
					info.setParent_id(14L);
					List<PrivilegeInfoVo> privilegeList = privilegeInfoService
							.isPrivilegeByUrl(info);
					if (privilegeList.size() == 0) {
						return new Result(Result.FAILURE, "您沒用该访问权限", null);
					}
				}
			}

		}
		CompanyFilesVo v = new CompanyFilesVo();
		v.setUser_id(user_id);
		v.setFor_id(task_id);
		v.setTask_type(task_type);
		System.out.println(agree_status);
		System.out.println(agree_status.equals("0"));
		if (agree_status.equals("0")) {
			// 倘若公司不同意制作者的领取，则在领取申请表里面修改相关数据
			Long checker_id = ((User) session.getAttribute("loginUser"))
					.getId();
			if (checker_id == null) {
				return new Result("RY0008", null);
			}
			TaskApply apply = new TaskApply();
			apply.setTask_id(task_id);
			apply.setUser_id(user_id);
			apply.setCheck_time(new Date());
			apply.setChecker_id(checker_id);
			apply.setApply_status("未通过");
			modelTaskService.updateTaskApply(apply);
			List<Long> ids = modelTaskService.getTaskIds(v);
			if (ids.size() == 0 && task_type.equals("建模")) {
				ModelTaskVo mTask = new ModelTaskVo();
				mTask.setId(task_id);
				mTask.setStage("发布中");
				mTask.setTask_status("发布中");
				modelTaskService.updateModelTask(mTask);
			} else if (ids.size() == 0 && !task_type.equals("建模")) {
				AnimationLightTaskVo aTask = new AnimationLightTaskVo();
				aTask�KŹ�_�.oޤ�K�
MhR�ǿ��b��6����V��픷���v[rۉn-�����w�?���5�k��ɠ�J�(��ר�?��Sëu��U�t��31K�V��y�\E�*�)�4��.�+Bu�8C�1/j���tE�) �qO���6Α��X�8^Qo�(��PҪ�FC��VM�Yg�]���YRi#[KDK�Z�,r�W�Rq�"���v1W���	�8W�)�]�,�Cu�X��e4���bv�|���q���(���jZ�$�Quk{�i5�-�����V�Z>���>�^��lS9��ZA�,R���em*�1%�Wb�y�,p!��-O��=W�p���aK�Fg����SY��v��C�ėFP��^�q[χP��6�������<�uz����+��B��3+������8�q�+y��;�E.;��TC�����df�K��^[�>���`�̓��Rw��ı�/�o��v�/�p*���(񜩟 ��z�Kd=���;Quer = new Timer();
				RepeatBackModelTask rbTask = new RepeatBackModelTask();//
				// 同意领取后设置一个12小时的定时器，如果未开始，则收回
				timer.schedule(rbTask, 1000 * 60 * 60 * 12);
				// timer.schedule(rbTask, 1000 * 60 * 3);
				ModelTaskVo m = modelTaskService.getModelTaskById(mTask);
				v.setProject_id(m.getProject_id());
				v.setIs_parent_project(m.getIs_parent_poject());
				v.setCompany_id(m.getCompany_id());

			}
			// mTask = modelTaskService.getModelTaskById(mTask);
			// timeliness = mTask.getTimeliness();
			// modified = mTask.getModified_time();
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			if (agree_status.equals("1")) {
				aTask.setTask_status("未开始");
				aTask.setStage("未开始");
				aTask.setUser_id(user_id);
				animationLightTaskService.updateAnimationLightTask(aTask);
				taskMap.put("task_id", task_id);
				Timer timer = new Timer();
				RepeatBackAnimationTask rbTask = new RepeatBackAnimationTask();//
				// 同意领取后设置一个12小时的定时器，如果未开始，则收回
				timer.schedule(rbTask, 1000 * 60 * 60 * 12);
				AnimationLightTaskVo a = animationLightTaskService
						.getAnimationLightTaskById(aTask);
				v.setProject_id(a.getProject_id());
				v.setIs_parent_project(a.getIs_parent_poject());
				v.setCompany_id(a.getCompany_id());
			}

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_id", task_id);
		map.put("task_type", task_type);
		// Long num1 = modelTaskService.getNotAgreeNums(map);
		// Long num2 = modelTaskService.getCheekerIsNotNullNums(map);
		if (agree_status.equals("1")) {
			modelTaskService.deleteTaskApply(map);
			companyFilesService.addGroleProvilege(v);
			if ("建模".equals(task_type)) {
				task_type = "1";
			} else {
				task_type = "2";
			}
			sendMessageService.createReceiveTaskMessage(task_id, task_type,
					user_id);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	@RequestMapping("repairTask")
	@ResponseBody
	@Transactional
	public Result repairTask(Long task_id, String task_type, String content,
			String repair_imgs, Repair repair, HttpSession session) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Long company_id = null;
		CompanyFilesVo vo = new CompanyFilesVo();
		vo.setFile_type(ConstantUtil.ZC_TASK);
		vo.setFor_id(task_id);
		Map<String, Object> map = new HashMap<String, Object>();
		Date end_time = null;
		if (task_type.equals("建模")) {
			vo.setTask_type(ConstantUtil.MODEL_TASK);
			ModelTaskVo task = new ModelTaskVo();
			task.setId(task_id);
			task = modelTaskService.getModelTaskById(task);
			company_id = task.getCompany_id();
			end_time = task.getEnd_time();
			map.put("project_id", task.getProject_id());
			map.put("task_id", task_id);
			map.put("is_parent_project", task.getIs_parent_poject());
			map.put("is_model_task", "1");
		} else {
			vo.setTask_type(ConstantUtil.ANIMATION_LIGHT_TASK);
			AnimationLightTaskVo task = new AnimationLightTaskVo();
			task.setId(task_id);
			task = animationLightTaskService.getAnimationLightTaskById(task);
			company_id = task.getCompany_id();
			end_time = task.getEnd_time();
			map.put("project_id", task.getProject_id());
			map.put("task_id", task_id);
			map.put("is_parent_project", task.getIs_parent_poject());
			map.put("is_model_task", "0");
		}

		vo = companyFilesService.selectForObject(vo);
		map.put("task_type", task_type);
		Long task_check_id = modelTaskService.getTaskCheckId(map);// 获取审核表id
		Date submitDate = modelTaskService.getSubmitDate(map);
		Date now = new Date();
		end_time.setTime(now.getTime() - submitDate.getTime()
				+ end_time.getTime());
		map.put("next_check_degree_num", 0);// 审核员序号为0表示当前任务不需要任何审核员审核
		map.put("id", task_check_id);
		modelTaskService.updateCheckNum(map);// 更改审核员序号
		Long num = modelTaskService.getRecheckNum(map);// 返修次数
		if (num == null) {
			num = 0L;
		}
		num++;
		repair.setTask_id(task_id);
		repair.setNum(num);
		if (task_type.equals("建模")) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask.setStage("进行中");
			mTask.setTask_status("返修中");
			mTask.setEnd_time(end_time);
			ModelTaskVo mtask = modelTaskService.getModelTaskById(mTask);
			map.put("task_type", "建模");
			Date commitTime = modelTaskService.getCommitTime(map);
			long usedDate = now.getTime() - commitTime.getTime();
			double checkUserTimes = ((int) Math
					.round((usedDate * 1.0 / (1000 * 60 * 60)) * 100)) / 100.0;
			if (mtask.getCheckUserTimes() == null
					|| "".equals(mtask.getCheckUserTimes().trim())) {
				mtask.setCheckUserTimes(Double.toString(checkUserTimes));
			} else {
				checkUserTimes += Double.valueOf(mtask.getCheckUserTimes());
				mtask.setCheckUserTimes(Double.toString(checkUserTimes));
			}
			modelTaskService.updateModelTask(mtask);
			modelTaskService.updateModelTask(mTask);
			repair.setIs_model_task("1");
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask.setStage("进行中");
			aTask.setTask_status("返修中");
			aTask.setEnd_time(end_time);
			AnimationLightTaskVo atask = animationLightTaskService
					.getAnimationLightTaskById(aTask);
			if (atask.getClass_id() == 2) {
				map.put("task_type", "动画");
			} else {
				map.put("task_type", "灯光");
			}
			Date commitTime = modelTaskService.getCommitTime(map);
			long usedDate = now.getTime() - commitTime.getTime();
			double checkUserTimes = ((int) Math
					.round((usedDate * 1.0 / (1000 * 60 * 60)) * 100)) / 100.0;
			if (atask.getCheckUserTimes() == null
					|| "".equals(atask.getCheckUserTimes().trim())) {
				atask.setCheckUserTimes(Double.toString(checkUserTimes));
			} else {
				checkUserTimes += Double.valueOf(atask.getCheckUserTimes());
				atask.setCheckUserTimes(Double.toString(checkUserTimes));
			}
			animationLightTaskService.updateAnimationLightTask(atask);
			animationLightTaskService.updateAnimationLightTask(aTask);
			repair.setIs_model_task("0");
		}
		repair.setAdvice_content(content);
		modelTaskService.addRepair(repair);
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		String[] images = repair_imgs.split("&");
		String repairFileUrl = ConstantUtil.USER_PATH + uuid + "/data/"
				+ vo.getFile_url() + "/repair/";
		/**
		 * 返修文件夹是否存在
		 */
		CompanyFilesVo c = new CompanyFilesVo();
		c.setFile_url(vo.getFile_url() + "/repair");
		c = companyFilesService.selectForObject(c);
		if (c == null) {
			c = new CompanyFilesVo();
			c.setCompany_id(company_id);
			c.setFile_name("repir");
			c.setParent_id(vo.getId());
			c.setIs_file(ConstantUtil.IS_FILE);
			c.setCreator_id(user_id);
			c.setFile_url(vo.getFile_url() + "/repair");
			companyFilesService.save(c);
			OSSFilesUtil.addFile(repairFileUrl);
		}
		for (String s : images) {
			String prefix = System.currentTimeMillis() + ".jpg";
			byte[] fileByte = FileUtils.base64ToByte(s);
			String savePath = repairFileUrl + prefix;
			/**
			 * 保存图片文件信息
			 */
			CompanyFilesVo repirImg = new CompanyFilesVo();
			repirImg.setCompany_id(company_id);
			repirImg.setCreator_id(user_id);
			repirImg.setFile_name(prefix);
			repirImg.setFile_url(c.getFile_url() + "/" + prefix);
			repirImg.setParent_id(c.getId());
			repirImg.setIs_file(ConstantUtil.NO_FILE);// 图片文件
			companyFilesService.save(repirImg);

			OSSFilesUtil.uploadDocumentByString(savePath, fileByte);
			RepairImage image = new RepairImage();
			image.setAdvice_path(savePath);
			image.setRepair_id(repair.getId());
			modelTaskService.addRepairImage(image);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}
public static void main(String[] args) {
	Calendar c=Calendar.getInstance();
	c.add(Calendar.HOUR, -22);
	Date commitTime=c.getTime();
	long usedDate = new Date().getTime() - commitTime.getTime();
	double checkUserTimes = ((int) Math
			.round((usedDate * 1.0 / (1000 * 60 * 60)) * 100)) / 100.0;
	System.out.println(checkUserTimes);
}
	@RequestMapping("commitProofOfMaker")
	@ResponseBody
	public Result commitProofOfMaker(Long task_id, String task_type,
			String content, String proof_imgs, Proof proof,
			HttpSession session, HttpServletRequest request) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		UserCompany u1 = userCompanyService.companygetUser(company_id);
		User loginUser = (User) session.getAttribute("loginUser");
		Long userId = ((User) session.getAttribute("loginUser")).getId();
		if (userId == null) {
			return new Result("RY0008", null);
		}
		Long isMaker = modelTaskService.getIsMaker(userId);
		Long isChecker = modelTaskService.isChecker(userId);
		if (isMaker == null && isChecker == null) {
			if (!userId.equals(u1.getUse_id())) {
				PrivilegeInfoVo info = new PrivilegeInfoVo();
				info.setCompany_id(company_id);
				info.setUser_id(userId);
				info.setPrivilege_url("commitProofOfMaker");
				info.setParent_id(14L);
				List<PrivilegeInfoVo> privilegeList = privilegeInfoService
						.isPrivilegeByUrl(info);
				if (privilegeList.size() == 0) {
					return new Result(Result.FAILURE, "您沒用该访问权限", null);
				}
			}
		}
		proof.setTask_id(task_id);
		if (task_type.equals("建模")) {
			proof.setIs_model_task("1");
		} else {
			proof.setIs_model_task("0");
		}
		proof.setAdvince_content(content);
		proof.setInfo("maker");
		modelTaskService.addProof(proof);
		String[] images = proof_imgs.split("&");
		// arbitrateInfo.set
		List<String> imgs = new ArrayList<String>();
		for (String s : images) {
			String prefix = System.currentTimeMillis() + ""
					+ (int) ((Math.random() * 9 + 1) * 100000);
			ProofImage image = new ProofImage();
			String path = FileUtils.getSaveArbitrateUserInfoIMGPath(
					loginUser.getUuid(), prefix);
			image.setAdvice_path(path);
			image.setProof_id(proof.getId());
			// 保存图片
			byte[] fileByte = FileUtils.base64ToByte(s);
			OSSFilesUtil.uploadDocumentByString(path, fileByte);
			imgs.add(path);
			modelTaskService.addProofImage(image);
		}

		ArbitrateInfo arbitrateInfo = new ArbitrateInfo();
		arbitrateInfo.setTask_type(task_type);
		arbitrateInfo.setTask_id(task_id);

		String retn = arbitrateInfoService.createdArbitrateInfo(arbitrateInfo,
				userId, "", content, imgs, request);
		if ("1".equals(retn)) {
			return new Result(Result.SUCCESS, "提交成功", null);
		} else if ("-2".equals(retn)) {
			return new Result(Result.FAILURE, "管理员发起", null);
		} else {
			return new Result(retn, null);
		}
	}

	@RequestMapping("/reBackTaskOfRunning")
	@ResponseBody
	@Transactional
	public Result reBackTaskOfRunning(Long task_id, String task_type,
			String content, String proof_imgs, String rate, Proof proof,
			HttpSession session, HttpServletRequest request) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		UserCompany u1 = userCompanyService.companygetUser(company_id);
		User loginUser = (User) session.getAttribute("loginUser");
		Long userId = ((User) session.getAttribute("loginUser")).getId();
		if (userId == null) {
			return new Result("RY0008", null);
		}
		Long isMaker = modelTaskService.getIsMaker(userId);
		Long isChecker = modelTaskService.isChecker(userId);
		if (isMaker == null && isChecker == null) {
			if (!userId.equals(u1.getUse_id())) {
				PrivilegeInfoVo info = new PrivilegeInfoVo();
				info.setCompany_id(company_id);
				info.setUser_id(userId);
				info.setPrivilege_url("reBackTaskOfRunning");
				info.setParent_id(14L);
				List<PrivilegeInfoVo> privilegeList = privilegeInfoService
						.isPrivilegeByUrl(info);
				if (privilegeList.size() == 0) {
					return new Result(Result.FAILURE, "您沒用该访问权限", null);
				}
			}
		}
		proof.setTask_id(task_id);
		if (task_type.equals("建模")) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask.setTask_status("锁定中");
			modelTaskService.updateModelTask(mTask);
			proof.setIs_model_task("1");
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask.setTask_status("锁定中");
			animationLightTaskService.updateAnimationLightTask(aTask);
			proof.setIs_model_task("0");
		}
		proof.setAdvince_content(content);
		proof.setInfo("company");
		modelTaskService.addProof(proof);
		String[] images = proof_imgs.split("&");
		// arbitrateInfo.set
		List<String> imgs = new ArrayList<String>();
		for (String s : images) {
			String prefix = System.currentTimeMillis() + ""
					+ (int) ((Math.random() * 9 + 1) * 100000);
			ProofImage image = new ProofImage();
			String path = FileUtils.getSaveArbitrateUserInfoIMGPath(
					loginUser.getUuid(), prefix);
			image.setAdvice_path(path);
			image.setProof_id(proof.getId());
			// 保存图片
			byte[] fileByte = FileUtils.base64ToByte(s);
			OSSFilesUtil.uploadDocumentByString(path, fileByte);
			imgs.add(path);
			modelTaskService.addProofImage(image);
		}
		ArbitrateInfo arbitrateInfo = new ArbitrateInfo();
		arbitrateInfo.setTask_type(task_type);
		arbitrateInfo.setTask_id(task_id);

		String retn = arbitrateInfoService.createdArbitrateInfo(arbitrateInfo,
				userId, "", content, imgs, request);
		if ("1".equals(retn)) {
			return new Result(Result.SUCCESS, "收回成功", null);
		} else if ("-2".equals(retn)) {
			return new Result(Result.FAILURE, "管理员发起", null);
		} else {
			return new Result(retn, null);
		}

	}

	@RequestMapping("/reBackTask")
	@ResponseBody
	public Result reBackTask(Long task_id, String task_type, HttpSession session) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		UserCompany u1 = userCompanyService.companygetUser(company_id);
		Long userId = ((User) session.getAttribute("loginUser")).getId();
		if (userId == null) {
			return new Result("RY0008", null);
		}
		Long isMaker = modelTaskService.getIsMaker(userId);
		Long isChecker = modelTaskService.isChecker(userId);
		if (isMaker == null && isChecker == null) {
			if (!userId.equals(u1.getUse_id())) {
				PrivilegeInfoVo info = new PrivilegeInfoVo();
				info.setCompany_id(company_id);
				info.setUser_id(userId);
				info.setPrivilege_url("reBackTask");
				info.setParent_id(14L);
				List<PrivilegeInfoVo> privilegeList = privilegeInfoService
						.isPrivilegeByUrl(info);
				if (privilegeList.size() == 0) {
					return new Result(Result.FAILURE, "您沒用该访问权限", null);
				}
			}
		}
		if (task_type.equals("建模")) {
			modelTaskService.reBackTask(task_id);
		} else {
			animationLightTaskService.reBackTask(task_id);
		}
		return new Result(Result.SUCCESS, "收回成功", null);
	}

	@RequestMapping("/getAbandonTaskCause")
	@ResponseBody
	public Result getAbandonTaskCause(Long task_id, String task_type,
			String cause, HttpSession session) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		if ("建模".equals(task_type)) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask.setCause(cause);
			modelTaskService.updateModelTask(mTask);
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask.setCause(cause);
			animationLightTaskService.updateAnimationLightTask(aTask);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	@RequestMapping("/cancelAbandonTask")
	@ResponseBody
	public Result cancelAbandonTask(Long task_id, String task_type,
			HttpSession session) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		if ("建模".equals(task_type)) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask = modelTaskService.getModelTaskById(mTask);
			mTask.setTask_status(mTask.getStage());
			modelTaskService.updateModelTask(mTask);
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask = animationLightTaskService.getAnimationLightTaskById(aTask);
			aTask.setTask_status(aTask.getStage());
			animationLightTaskService.updateAnimationLightTask(aTask);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	@RequestMapping("/discussAbandonTask")
	@ResponseBody
	public Result discussAbandonTask(Long task_id, String task_type,
			String rate, HttpSession session) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Long role_id = modelTaskService.getRoleIdByUserId(user_id);
		if ("建模".equals(task_type)) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask.setRate(rate);
			ModelTaskVo m=modelTaskService.getModelTaskById(mTask);
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("user_id", user_id);
			mp.put("project_id", m.getProject_id());
			mp.put("is_parent_project", m.getIs_parent_poject());
			mp.put("task_id", task_id);
			mp.put("task_type", "建模");
			Long isGroupMember = modelTaskService.getIsGroupMemeber(mp);
			if (role_id == 2) {
				mTask.setAbandon("company");
			} else {
				if ("指派中".equals(m.getTask_status())) {
					mTask.setAbandon("maker");
				} else {
					if (isGroupMember != null && isGroupMember > 0) {
						mTask.setAbandon("company");
					} else {
						mTask.setAbandon("maker");
					}
				}
			}
			mTask.setTask_status("放弃中");
			modelTaskService.updateModelTask(mTask);
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask.setRate(rate);
			AnimationLightTaskVo a=animationLightTaskService.getAnimationLightTaskById(aTask);
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("user_id", user_id);
			mp.put("project_id", a.getProject_id());
			mp.put("is_parent_project", a.getIs_parent_poject());
			mp.put("task_id", task_id);
			if(a.getClass_id().equals(2)){
				mp.put("task_type", "动画");
			}else{
				mp.put("task_type", "灯光");
			}
			Long isGroupMember = modelTaskService.getIsGroupMemeber(mp);
			if (role_id == 2) {
				aTask.setAbandon("company");
			} else {
				if ("指派中".equals(a.getTask_status())) {
					aTask.setAbandon("maker");
				} else {
					if (isGroupMember != null && isGroupMember > 0) {
						aTask.setAbandon("company");
					} else {
						aTask.setAbandon("maker");
					}
				}
			}
			aTask.setTask_status("放弃中");
			animationLightTaskService.updateAnimationLightTask(aTask);
		}

		return new Result(Result.SUCCESS, "成功", null);
	}

	@RequestMapping("/abandonTask")
	@ResponseBody
	public Result abandonTask(Long task_id, String task_type,
			HttpSession session) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		UserCompany u1 = userCompanyService.companygetUser(company_id);
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Long isMaker = modelTaskService.getIsMaker(user_id);
		Long isChecker = modelTaskService.isChecker(user_id);
		if (isMaker == null && isChecker == null) {
			if (u1 == null || !user_id.equals(u1.getUse_id())) {
				PrivilegeInfoVo info = new PrivilegeInfoVo();
				info.setCompany_id(company_id);
				info.setUser_id(user_id);
				info.setPrivilege_url("abandonTask");
				info.setParent_id(14L);
				List<PrivilegeInfoVo> privilegeList = privilegeInfoService
						.isPrivilegeByUrl(info);
				if (privilegeList.size() == 0) {
					return new Result(Result.FAILURE, "您沒用该访问权限", null);
				}
			}
		}
		ModelTaskVo mTask = new ModelTaskVo();
		mTask.setId(task_id);
		mTask.setStage("已放弃");
		mTask.setTask_status("已放弃");
		if (task_type.equals("建模")) {
			ModelTaskVo mTask1 = modelTaskService.getModelTaskById(mTask);
			Date endTime = mTask1.getEnd_time();
			String state = mTask1.getStage();
			if ("未开始".equals(state)) {
				state = "1";
			} else if ("进行中".equals(state)) {
				if (new Date().after(endTime)) {
					state = "3";
				} else {
					state = "2";
				}
			}
			if (isMaker != null && isMaker.equals(5L)) {
				// 解冻金额
				String ret = freezeRecordService.releaseFreezeRecord(task_id,
						mTask1.getClass_id(), "", mTask1.getUser_id(), "2",
						state, null);
				if (!"1".equals(ret)) {
					return new Result(ret, null);

				}
			} else {
				// 解冻金额
				UserCompany uc = userCompanyService.companygetUser(mTask1
						.getCompany_id());
				String ret = freezeRecordService.releaseFreezeRecord(task_id,
						mTask1.getClass_id(), "", uc.getUse_id(), "3", state,
						null);
				if (!"1".equals(ret)) {
					return new Result(ret, null);

				}

			}
			modelTaskService.updateModelTask(mTask);

		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask.setStage("已放弃");
			aTask.setTask_status("已放弃");
			AnimationLightTaskVo aTask1 = animationLightTaskService
					.getAnimationLightTaskById(aTask);
			Date end_time = aTask1.getEnd_time();

			String state = aTask1.getStage();
			if ("未开始".equals(state)) {
				state = "1";
			} else if ("进行中".equals(state)) {
				if (new Date().after(end_time)) {
					state = "3";
				} else {
					state = "2";
				}
			}
			if (isMaker != null && isMaker.equals(5L)) {
				// 解冻金额
				String ret = freezeRecordService.releaseFreezeRecord(task_id,
						aTask1.getClass_id(), "", aTask1.getUser_id(), "2",
						state, null);
				if (!"1".equals(ret)) {
					return new Result(ret, null);

				}
			} else {
				// mTask.setAbandon("maker");
				// 解冻金额
				UserCompany uc = userCompanyService.companygetUser(aTask1
						.getCompany_id());
				String ret = freezeRecordService.releaseFreezeRecord(task_id,
						aTask1.getClass_id(), "", uc.getUse_id(), "3", state,
						null);
				if (!"1".equals(ret)) {
					return new Result(ret, null);

				}
			}
			animationLightTaskService.updateAnimationLightTask(aTask);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 放弃任务
	 * 
	 * @param task_id
	 * @param task_type
	 * @return
	 */
	@RequestMapping("/confirmAbandonTask")
	@ResponseBody
	public Result confirmAbandonTask(Long task_id, String task_type,
			HttpSession session) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		ModelTaskVo mTask = new ModelTaskVo();
		AnimationLightTaskVo aTask = new AnimationLightTaskVo();
		Long company_id = null;
		ModelTaskVo t1 = null;
		AnimationLightTaskVo t2 = null;
		String indemnition = null;
		String rate = null;
		if (task_type.equals("建模")) {

			mTask.setId(task_id);
			t1 = modelTaskService.getModelTaskById(mTask);
			indemnition = t1.getAbandon();
			rate = t1.getRate();
			user_id = t1.getMaker().getId();
			company_id = t1.getCompany_id();
			// ---------------------------
			mTask.setStage("已放弃");
			mTask.setTask_status("已放弃");
			modelTaskService.updateModelTask(mTask);
		} else {
			aTask.setId(task_id);
			t2 = animationLightTaskService.getAnimationLightTaskById(aTask);
			rate = t2.getRate();
			indemnition = t2.getAbandon();
			user_id = t2.getMaker().getId();
			company_id = t2.getCompany_id();
			// -------------------------
			aTask.setStage("已放弃");
			aTask.setTask_status("已放弃");
			animationLightTaskService.updateAnimationLightTask(aTask);
		}
		Long typeTask = 0L;
		if (task_type.equals("建模")) {
			typeTask = 1L;
		} else if (task_type.equals("动画")) {
			typeTask = 2L;
		} else {
			typeTask = 3L;
		}
		if (indemnition != null && indemnition.equals("maker")) {
			System.out.println("indemnition:" + indemnition);
			System.out.println("rate:" + rate);
			System.out.println("task_type:" + typeTask);
			System.out.println("user_id:" + user_id);
			System.out.println("maker");
			int custom = Integer.parseInt(rate);
			String retn = freezeRecordService.releaseFreezeRecord(task_id,
					typeTask, "", user_id, "7", "1", custom);
			if ("1".equals(retn)) {
				return new Result(Boolean.TRUE, "已经转账！", null);

			} else {

				return new Result(retn, null);
			}
		} else if (indemnition != null && indemnition.equals("company")) {
			System.out.println("indemnition:" + indemnition);
			System.out.println("rate:" + rate);
			System.out.println("task_type:" + typeTask);
			System.out.println("user_id:" + user_id);
			System.out.println("company" + company_id);
			UserCompany userCompany = userCompanyService
					.companygetUser(company_id);
			int custom = Integer.parseInt(rate);
			String retn = freezeRecordService.releaseFreezeRecord(task_id,
					typeTask, "", userCompany.getUse_id(), "8", "1", custom);
			if ("1".equals(retn)) {
				if (task_type.equals("建模")) {
					modelTaskService.updateModelTask(mTask);
				} else {
					animationLightTaskService.updateAnimationLightTask(aTask);
				}

				return new Result(Boolean.TRUE, "已经转账！", null);

			} else {

				return new Result(retn, null);
			}

		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 制作者开始任务
	 * 
	 * @param task_id
	 * @param task_type
	 * @return
	 */
	@RequestMapping("/beginTask")
	@ResponseBody
	public Result beginTask(Long task_id, String task_type) {
		// TODO缺少赔偿金额的程序，将该任务冻结的钱解冻
		if (task_type.equals("建模")) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask.setStage("进行中");
			mTask.setTask_status("进行中");
			modelTaskService.updateModelTask(mTask);
		} else {
			AnimationLightTask aTask = new AnimationLightTask();
			aTask.setId(task_id);
			aTask.setStage("进行中");
			aTask.setTask_status("进行中");
			animationLightTaskService.updateAnimationLightTask(aTask);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 延期任务时间，设置结束时间即可
	 * 
	 * @param delay_time
	 * @param task_type
	 * @param task_id
	 * @return
	 */
	@RequestMapping("/delayTaskWorkTime")
	@ResponseBody
	public Result delayTaskWorkTime(double delay_time, String task_type,
			Long task_id, String delay_cause, HttpSession session) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		UserCompany u1 = userCompanyService.companygetUser(company_id);
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Long isMaker = modelTaskService.getIsMaker(user_id);
		Long isChecker = modelTaskService.isChecker(user_id);
		if (isMaker == null && isChecker == null) {
			if (!user_id.equals(u1.getUse_id())) {
				PrivilegeInfoVo info = new PrivilegeInfoVo();
				info.setCompany_id(company_id);
				info.setUser_id(user_id);
				info.setPrivilege_url("delayTaskWorkTime");
				info.setParent_id(14L);
				List<PrivilegeInfoVo> privilegeList = privilegeInfoService
						.isPrivilegeByUrl(info);
				if (privilegeList.size() == 0) {
					return new Result(Result.FAILURE, "您沒用该访问权限", null);
				}
			}
		}
		if (task_type.equals("建模")) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask = modelTaskService.getModelTaskById(mTask);
			Date end_time = mTask.getEnd_time();
			Calendar c = Calendar.getInstance();
			c.setTime(end_time);
			c.add(Calendar.MINUTE, (int) (delay_time * 60));
			mTask.setEnd_time(c.getTime());
			mTask.setDelay_cause(delay_cause);
			modelTaskService.updateModelTaskEndTime(mTask);
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask = animationLightTaskService.getAnimationLightTaskById(aTask);
			Date end_time = aTask.getEnd_time();
			Calendar c = Calendar.getInstance();
			c.setTime(end_time);
			c.add(Calendar.MINUTE, (int) (delay_time * 60));
			aTask.setDelay_cause(delay_cause);
			aTask.setEnd_time(c.getTime());
			animationLightTaskService.updateAnimationLightTaskEndTime(aTask);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 制作者提交已完成的任务
	 *
	 * @param task_id
	 * @param task_type
	 * @return
	 */
	@RequestMapping("commitTask")
	@ResponseBody
	@Transactional
	public Result commitTask(Long task_id, String task_type, String localPath) {
		Long user_id = null;
		String is_parent_project = null;
		Long project_id = null;

		ModelTaskVo mTask = null;
		AnimationLightTaskVo aTask = null;
		if (task_type.equals("建模")) {
			mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask.setStage("审核中");
			mTask.setTask_status("审核中");
			modelTaskService.updateModelTask(mTask);// 修改任务状态
			mTask = modelTaskService.getModelTaskById(mTask);
			user_id = mTask.getMaker().getId();
			project_id = mTask.getProject_id();
			is_parent_project = mTask.getIs_parent_poject();
		} else {
			aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask.setStage("审核中");
			aTask.setTask_status("审核中");
			animationLightTaskService.updateAnimationLightTask(aTask);
			aTask = animationLightTaskService.getAnimationLightTaskById(aTask);
			user_id = aTask.getMaker().getId();
			project_id = aTask.getProject_id();
			is_parent_project = aTask.getIs_parent_poject();
		}
		// OSSFilesUtil.uploadDocumentByFile(commit_path, localPath);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_id", task_id);
		map.put("project_id", project_id);
		map.put("is_parent_project", is_parent_project);
		map.put("task_type", task_type);
		TaskCheck check = modelTaskService.getTaskCheck(map);// 获取任务的审核情况
		if (task_type.equals("建模")) {
			map.put("is_model_task", "1");
		} else {
			map.put("is_model_task", "0");
		}
		Long num = modelTaskService.getRecheckNum(map);// 获取返修次数
		if (num == null) {
			num = 0L;
		}
		if (check == null) {// 如果任务的设和情况为空，则初始化审核情况
			check = new TaskCheck();
			check.setSubmit_time(new Date());
			check.setAudit_order(1L);// 审核员的序号为1
			check.setTask_id(task_id);
			check.setTask_type(task_type);
			check.setUser_id(user_id);
			check.setIs_parent_project(is_parent_project);
			check.setProject_id(project_id);
			check.setNum(0L);// 返修次数为0
			modelTaskService.addTaskCheck(check);
			if (mTask != null) {
				Date beginTime = mTask.getActual_begin_time();
				double actual_working_hours = ((int) Math
						.round(((new Date().getTime() - beginTime.getTime()) / (1000 * 60 * 60.0)) * 100)) / 100.0;
				mTask.setActual_working_hours(String
						.valueOf(actual_working_hours));
				modelTaskService.updateModelTask(mTask);
			}
			if (aTask != null) {
				Date beginTime = aTask.getActual_begin_time();
				double actual_working_hours = ((int) Math
						.round(((new Date().getTime() - beginTime.getTime()) / (1000 * 60 * 60.0)) * 100)) / 100.0;
				aTask.setActual_working_hours(String
						.valueOf(actual_working_hours));
				animationLightTaskService.updateAnimationLightTask(aTask);
			}
		} else {
			check.setSubmit_time(new Date());
			check.setAudit_order(1L);// 设置审核员的初始化1
			check.setNum(num);// 设置返修次数
			modelTaskService.updateTaskCheck(check);
			Map<String, Object> m = new HashMap<String, Object>();
			if (mTask != null) {
				m.put("task_id", mTask.getId());
				m.put("num", num);
				m.put("is_model_task", "1");
				Date repairTime = modelTaskService.getRepairTime(m);
				double actual_working_hours = ((int) Math
						.round(((new Date().getTime() - repairTime.getTime()) / (1000 * 60 * 60.0)) * 100)) / 100.0;
				if (mTask.getActual_working_hours() != null) {
					actual_working_hours += Double.valueOf(mTask
							.getActual_working_hours());
				}
				mTask.setActual_working_hours(String
						.valueOf(actual_working_hours));
				modelTaskService.updateModelTask(mTask);
			}
			if (aTask != null) {
				m.put("task_id", aTask.getId());
				m.put("num", num);
				m.put("is_model_task", "0");
				Date repairTime = modelTaskService.getRepairTime(m);
				double actual_working_hours = ((int) Math
						.round(((new Date().getTime() - repairTime.getTime()) / (1000 * 60 * 60.0)) * 100)) / 100.0;
				if (aTask.getActual_working_hours() != null) {
					actual_working_hours += Double.valueOf(aTask
							.getActual_working_hours());
				}
				aTask.setActual_working_hours(String
						.valueOf(actual_working_hours));
				animationLightTaskService.updateAnimationLightTask(aTask);
			}
		}
		return new Result(Result.SUCCESS, "成功", null);
	}
 
	/**
	 * 获取任务排序的数据（模型）
	 * 
	 * @return
	 */
	@RequestMapping("/getModelTaskCheckSearch")
	@ResponseBody
	public Result getModelTaskCheckSearch() {
		List<ProjectSearch> search = modelTaskService.getModelTaskCheckSearch();
		return new Result(Result.SUCCESS, "成功", search);
	}

	/**
	 * 获取任务排序的数据（动画灯光）
	 * 
	 * @return
	 */
	@RequestMapping("/getAnimationLightTaskCheckSearch")
	@ResponseBody
	public Result getAnimationLightTaskCheckSearch() {
		List<ProjectSearch> search = modelTaskService
				.getAnimationLightTaskCheckSearch();
		return new Result(Result.SUCCESS, "成功", search);
	}

	/**
	 * 获取审核状态下的任务
	 * 
	 * @param data
	 * @param is_parent_project
	 * @param task_type
	 * @return
	 */
	@RequestMapping("/getCheckStatusTask")
	@ResponseBody
	public Result getCheckStatusTask(String data, String is_parent_project,
			String task_type, HttpSession session) {
		// TODO获取审核用户id,公司id
		Long company_id = (Long) session.getAttribute("sessionCompany");
		Long checker_id = ((User) session.getAttribute("loginUser")).getId();
		if (checker_id == null) {
			return new Result("RY0008", null);
		}
		// is_parent_project = "0";
		// task_type = "动画";
		// data =
		// "{'page':{'pageSize':'3','pageNo':'1'},'object':{'sort':'desc','field':'username','search':''}}";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_parent_project", is_parent_project);
		map.put("user_id", checker_id);
		map.put("task_type", task_type);
		map.put("company_id", company_id);
		JSONObject param = JSON.parseObject(data);
		JSON object = (JSON) JSON.toJSON(param.get("object"));
		ProjectSearchVo search = JSONObject.toJavaObject(object,
				ProjectSearchVo.class);
		map.put("search", search);
		Map<String, String> map1 = (Map<String, String>) param.get("page");
		Page page = PageUtils.createPage(map1);
		if (task_type.equals("建模")) {
			page = modelTaskService.page2(page, map);
			List<ModelTaskVo> mts = (List<ModelTaskVo>) page.getDataList();
			map.put("dataList", mts);
		} else {
			page = animationLightTaskService.page2(page, map);
			List<AnimationLightTaskVo> mts = (List<AnimationLightTaskVo>) page
					.getDataList();
			map.put("dataList", mts);
		}
		map.put("total", page.getTotal());
		map.put("pageSize", page.getLimit());
		map.put("pageNo", page.getCurrentPage());
		return new Result(Result.SUCCESS, "成功", map);
	}

	@RequestMapping("/getEvaluationInfo")
	@ResponseBody
	public Result getEvaluationInfo() {
		List<EvaluationVo> evaluations = modelTaskService.getEvaluationInfo();
		return new Result(Result.SUCCESS, "成功", evaluations);
	}

	@RequestMapping("/isCheckerOrNot")
	@ResponseBody
	public Result isCheckerOrNot(HttpSession session, Long task_id,
			String task_type) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (task_type.equals("建模")) {
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(task_id);
			mTask = modelTaskService.getModelTaskById(mTask);
			map.put("project_id", mTask.getProject_id());
			map.put("is_parent_project", mTask.getIs_parent_poject());
			map.put("user_id", user_id);
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask = animationLightTaskService.getAnimationLightTaskById(aTask);
			map.put("project_id", aTask.getProject_id());
			map.put("is_parent_project", aTask.getIs_parent_poject());
			map.put("user_id", user_id);
		}
		map.put("task_id", task_id);
		map.put("task_type", task_type);
		System.out.println("map:-------------->" + map);
		Long id = modelTaskService.getIsCheckerOrNot(map);
		if (id == null) {
			return new Result(Result.FAILURE, "成功", null);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 获取审核任务的相关信息
	 */
	@RequestMapping("/getCheckTaskInfo")
	@ResponseBody
	public Result getCheckTaskInfo(Long task_id, String task_type,
			String is_parent_project, HttpSession session) {
		Long checker_id = ((User) session.getAttribute("loginUser")).getId();
		if (checker_id == null) {
			return new Result("RY0008", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_parent_project", is_parent_project);
		if (task_type.equals("建模")) {
			ModelTaskVo task = new ModelTaskVo();
			task.setId(task_id);
			task = modelTaskService.getModelTaskById(task);
			map.put("project_id", task.getProject_id());
			map.put("task", task);
		} else {
			AnimationLightTaskVo task = new AnimationLightTaskVo();
			task.setId(task_id);
			task = animationLightTaskService.getAnimationLightTaskById(task);
			map.put("project_id", task.getProject_id());
			map.put("task", task);
		}
		map.put("user_id", checker_id);
		map.put("task_id", task_id);
		map.put("task_type", task_type);
		if ("建模".equals(task_type)) {
			map.put("is_model_task", 1);
		} else {
			map.put("is_model_task", 0);
		}
		Long num = modelTaskService.getRecheckNum(map);// 返修次数
		map.put("recheck_num", num);// 返修次数
		System.out.println("map:" + map);
		Long degree_num = modelTaskService.getMaxDegreeNum(map);// degree_num代表最高审核层级序号
		Long current_degree_num = modelTaskService.getCurrentDegreeNum(map);// current_degree_num代表当前审核层级序号
		System.out.println(degree_num + "," + current_degree_num);
		if (current_degree_num != null && degree_num != null
				&& current_degree_num < degree_num) {
			Long next_check_num = current_degree_num + 1;
			map.put("next_check_num", next_check_num);
			UserInfo user = modelTaskService.getNextChecker(map);
			map.put("user", user);
		}
		return new Result(Result.SUCCESS, "成功", map);
	}

	/**
	 * 通过审核任务
	 */
	@RequestMapping("/passTask")
	@ResponseBody
	@Transactional
	public Result passTask(Long task_id, String task_type,
			String is_parent_project, AuditRecordVo vo, HttpSession session) {
		Long checker_id = ((User) session.getAttribute("loginUser")).getId();
		if (checker_id == null) {
			return new Result("RY0008", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_parent_project", is_parent_project);
		Long num = null;
		if (task_type.equals("建模")) {
			ModelTaskVo task = new ModelTaskVo();
			task.setId(task_id);
			task = modelTaskService.getModelTaskById(task);
			map.put("project_id", task.getProject_id());
			map.put("is_model_task", "1");
			map.put("task_id", task_id);
			num = modelTaskService.getRecheckNum(map);// 返修次数
		} else {
			AnimationLightTaskVo task = new AnimationLightTaskVo();
			task.setId(task_id);
			task = animationLightTaskService.getAnimationLightTaskById(task);
			map.put("project_id", task.getProject_id());
			map.put("is_model_task", "0");
			map.put("task_id", task_id);
			num = modelTaskService.getRecheckNum(map);// 返修次数
		}
		map.put("user_id", checker_id);
		map.put("task_id", task_id);
		map.put("task_type", task_type);
		if (num == null) {
			num = 0L;
		}
		System.out.println("map:" + map);
		Long degree_num = modelTaskService.getMaxDegreeNum(map);// degree_num代表最高审核层级序号
		Long current_degree_num = modelTaskService.getCurrentDegreeNum(map);// current_degree_num代表当前审核层级序号
		System.out.println("degree:" + degree_num);
		System.out.println("current_degree_num:" + current_degree_num);
		if (degree_num != null && current_degree_num != null
				&& current_degree_num < degree_num) {// 当不是最高级审核员审核时，执行以下操作
			Long task_check_id = modelTaskService.getTaskCheckId(map);// 获取审核表id
			System.out.println(task_check_id);
			map.put("next_check_degree_num", current_degree_num + 1);
			map.put("id", task_check_id);
			System.out.println("map1:" + map);
			modelTaskService.updateCheckNum(map);// 更改审核员序号
			vo.setChecker_id(checker_id);
			vo.setCheck_time(new Date());
			vo.setTask_check_id(task_check_id);
			vo.setNum(num);
			modelTaskService.addAuditRecord(vo);// 添加审核记录到审核表
		} else {
			Long task_check_id = modelTaskService.getTaskCheckId(map);// 获取审核表id
			map.put("next_check_degree_num", 0);
			map.put("id", task_check_id);
			modelTaskService.updateCheckNum(map);// 更改审核员序号
			vo.setChecker_id(checker_id);
			vo.setCheck_time(new Date());
			vo.setTask_check_id(task_check_id);
			vo.setNum(num);
			modelTaskService.addAuditRecord(vo);// 添加审核记录到审核表
			Date now = new Date();
			if (task_type.equals("建模")) {
				ModelTaskVo mTask = new ModelTaskVo();
				mTask.setId(task_id);
				mTask.setStage("待付款");
				mTask.setTask_status("待付款");
				ModelTaskVo mtask = modelTaskService.getModelTaskById(mTask);
				map.put("task_type", "建模");
				System.out.println("model:" + map);
				Date commitTime = modelTaskService.getCommitTime(map);
				long usedDate = now.getTime() - commitTime.getTime();
				double checkUserTimes = ((int) Math.round(100 * usedDate * 1.0
						/ (1000 * 60 * 60 * 24))) / 100.0;
				if (mtask.getCheckUserTimes() == null
						|| "".equals(mtask.getCheckUserTimes().trim())) {
					mtask.setCheckUserTimes(Double.toString(checkUserTimes));
				} else {
					checkUserTimes += Double.valueOf(mtask.getCheckUserTimes());
					mtask.setCheckUserTimes(Double.toString(checkUserTimes));
				}
				modelTaskService.updateModelTask(mtask);
				modelTaskService.updateModelTask(mTask);

				// 得到userId
				ModelTaskVo searModelTask = modelTaskService
						.getModelTaskById(mTask);
				// 得到用户id
				long userId = searModelTask.getMaker().getId();

				// 搜索 taskCheck
				TaskCheck taskCheck = new TaskCheck();
				taskCheck.setTask_type("建模");
				taskCheck.setTask_id(searModelTask.getId());
				// 得到 TaskCheck
				TaskCheck searchTaskCheck = taskCheckService
						.selectForObject(taskCheck);
				Long teskNum = searchTaskCheck.getNum(); // 检查次数

				// 搜索 auditRecord
				AuditRecord auditRecord = new AuditRecord();
				auditRecord.setNum(searchTaskCheck.getNum());
				auditRecord.setTask_check_id(searchTaskCheck.getTid());
				// 得到 平均auditRecord
				AuditRecord searchAuditRecord = auditRecordService
						.getAVGAuditRecord(auditRecord);
				// 获取难度id
				Long degreeId = 1L;
				Long evaluation_id = 1L;
				if (searchAuditRecord != null) {
					degreeId = searchAuditRecord.getDegree_id() == null ? 1L
							: searchAuditRecord.getDegree_id();
					evaluation_id = searchAuditRecord.getEvaluation_id() == null ? 1L
							: searchAuditRecord.getEvaluation_id();
				} else {
					searchAuditRecord = new AuditRecord();
					searchAuditRecord.setProduction_speed(0.0);
					searchAuditRecord.setProduction_quality(0.0);
				}

				// 得到难度积分
				Degree degree = new Degree();
				degree.setId(degreeId);
				Degree searchDegree = degreeService.selectForObject(degree);
				// 得到评价积分
				Evaluation evaluation = new Evaluation();
				evaluation.setId(evaluation_id);
				Evaluation searchEvaluation = evaluationService
						.selectForObject(evaluation);

				UserCredibility userCredibility = new UserCredibility();
				userCredibility.setUser_id(userId);
				// 计算并添加积分
				UserCredibility searchUserCredibility = userCredibilityService
						.selectUserCredibilityByUserId(userCredibility);
				// 更改次数
				long changeNum = 0l;
				if (searchUserCredibility == null) {// 如果为null则创建一个
					UserCredibility newUserCredibility = new UserCredibility();
					newUserCredibility.setCreat_time(new Date());
					newUserCredibility.setPassing_rate(100.0);
					newUserCredibility.setProduction_quality(0.0);
					newUserCredibility.setProduction_speed(0.0);
					newUserCredibility.setRating_points(0.0);
					newUserCredibility.setUser_id(userId);
					newUserCredibility.setUpdate_tiems(0L);
					userCredibilityService.save(newUserCredibility);
					searchUserCredibility = newUserCredibility; // 搜索的为新的
				}
				changeNum = searchUserCredibility.getUpdate_tiems();
				// 积分等于原积分 +（难度*评价）
				double newRatingPoints = searchUserCredibility
						.getRating_points()
						+ searchDegree.getDegree_of_difficulty()
						* searchEvaluation.getEvaluation_num();
				// 更新后用户评价
				searchUserCredibility.setRating_points(newRatingPoints);
				// 更新后的制作质量 (原质量直+现质量直) / 2
				searchUserCredibility
						.setProduction_quality((searchUserCredibility
								.getProduction_quality() * changeNum + searchAuditRecord
									.getProduction_quality()) / (changeNum + 1));
				// 更新后的制作质量 (原速度直+现速度直) / 2
				searchUserCredibility
						.setProduction_speed((searchUserCredibility
								.getProduction_speed() * changeNum + searchAuditRecord
									.getProduction_speed()) / (changeNum + 1));
				// 更新后的完成率
				// 检查人员
				List<AuditRecord> cherckerList = auditRecordService
						.getcheckerNum(auditRecord);

				int chercker = 0;
				if (cherckerList != null) {
					chercker = cherckerList.size();
				}
				// 检查次数及数量

				List<Long> checkCount = auditRecordService
						.getcheckCount(auditRecord);
				// 平均通过率
				double avgPassingRate = 0.0;
				for (Long checkNum : checkCount) {
					avgPassingRate += checkNum;
				}
				avgPassingRate = avgPassingRate / checkCount.size() / chercker;

				if (Double.isNaN(avgPassingRate)) {
					avgPassingRate = 0.0;
				}
				double passing = (searchUserCredibility.getPassing_rate() / 100
						* changeNum + avgPassingRate)
						/ (changeNum + 1) * 100;
				double addPassing = 0.0; // 奖励通过率

				if (teskNum >= 2) {
					addPassing = 0.8;
				} else if (teskNum >= 4) {
					addPassing = 0.5;
				}

				if ((passing + addPassing) > 100) { // 通过率只可在100以内
					passing = 100;

				} else {
					passing += addPassing;

				}
				// 更新后的通过率
				searchUserCredibility.setPassing_rate(passing);
				// 更新更改次数
				searchUserCredibility.setUpdate_tiems(searchUserCredibility
						.getUpdate_tiems() + 1);
				// 更新个人信息
				userCredibilityService.updateIgnoreNull(searchUserCredibility);
				return new Result(Result.SUCCESS, "成功", null);

			} else { // 灯光
				AnimationLightTaskVo aTask = new AnimationLightTaskVo();
				aTask.setId(task_id);
				aTask.setStage("待付款");
				aTask.setTask_status("待付款");
				AnimationLightTaskVo atask = animationLightTaskService
						.getAnimationLightTaskById(aTask);
				if (atask.getClass_id() == 2) {
					map.put("task_type", "动画");
				} else {
					map.put("task_type", "灯光");
				}
				System.out.println("animation:" + map);
				System.out
						.println("--------------------------------------------------------");
				Date commitTime = modelTaskService.getCommitTime(map);
				long usedDate = now.getTime() - commitTime.getTime();
				double checkUserTimes = ((int) Math.round(100 * usedDate * 1.0
						/ (1000 * 60 * 60 * 24))) / 100.0;
				if (atask.getCheckUserTimes() == null
						|| "".equals(atask.getCheckUserTimes().trim())) {
					atask.setCheckUserTimes(Double.toString(checkUserTimes));
				} else {
					checkUserTimes += Double.valueOf(atask.getCheckUserTimes());
					atask.setCheckUserTimes(Double.toString(checkUserTimes));
				}
				animationLightTaskService.updateAnimationLightTask(atask);
				animationLightTaskService.updateAnimationLightTask(aTask);

				// 得到userId
				AnimationLightTaskVo searModelTask = animationLightTaskService
						.getAnimationLightTaskById(aTask);
				// 得到用户id
				long userId = searModelTask.getMaker().getId();

				// 搜索 taskCheck
				TaskCheck taskCheck = new TaskCheck();
				if (atask.getClass_id() == 2) {
					taskCheck.setTask_type("动画");
				} else {
					taskCheck.setTask_type("灯光");
				}
				taskCheck.setTask_id(searModelTask.getId());
				// 得到 TaskCheck
				TaskCheck searchTaskCheck = taskCheckService
						.selectForObject(taskCheck);
				Long teskNum = searchTaskCheck.getNum(); // 检查次数

				// 搜索 auditRecord
				AuditRecord auditRecord = new AuditRecord();
				auditRecord.setNum(searchTaskCheck.getNum());
				auditRecord.setTask_check_id(searchTaskCheck.getTid());
				// 得到 平均auditRecord
				AuditRecord searchAuditRecord = auditRecordService
						.getAVGAuditRecord(auditRecord);
				// 获取难度id
				Long degreeId = 1L;
				Long evaluation_id = 1L;
				if (searchAuditRecord != null) {
					degreeId = searchAuditRecord.getDegree_id() == null ? 1L
							: searchAuditRecord.getDegree_id();
					evaluation_id = searchAuditRecord.getEvaluation_id() == null ? 1L
							: searchAuditRecord.getEvaluation_id();
				} else {
					searchAuditRecord = new AuditRecord();
					searchAuditRecord.setProduction_speed(0.0);
					searchAuditRecord.setProduction_quality(0.0);
				}
				// 得到难度积分
				Degree degree = new Degree();
				degree.setId(degreeId);
				Degree searchDegree = degreeService.selectForObject(degree);
				// 得到评价积分
				Evaluation evaluation = new Evaluation();
				evaluation.setId(evaluation_id);
				Evaluation searchEvaluation = evaluationService
						.selectForObject(evaluation);

				UserCredibility userCredibility = new UserCredibility();
				userCredibility.setUser_id(userId);
				// 计算并添加积分
				UserCredibility searchUserCredibility = userCredibilityService
						.selectUserCredibilityByUserId(userCredibility);
				// 更改次数
				long changeNum = 0l;
				if (searchUserCredibility == null) {// 如果为null则创建一个
					UserCredibility newUserCredibility = new UserCredibility();
					newUserCredibility.setCreat_time(new Date());
					newUserCredibility.setPassing_rate(100.0);
					newUserCredibility.setProduction_quality(0.0);
					newUserCredibility.setProduction_speed(0.0);
					newUserCredibility.setRating_points(0.0);
					newUserCredibility.setUser_id(userId);
					newUserCredibility.setUpdate_tiems(0L);
					userCredibilityService.save(newUserCredibility);
					searchUserCredibility = newUserCredibility; // 搜索的为新的
				}
				changeNum = searchUserCredibility.getUpdate_tiems();
				// 积分等于原积分 +（难度*评价）
				double newRatingPoints = searchUserCredibility
						.getRating_points()
						+ searchDegree.getDegree_of_difficulty()
						* searchEvaluation.getEvaluation_num();

				// 更新后用户评价
				searchUserCredibility.setRating_points(newRatingPoints);
				// 更新后的制作质量 (原质量直+现质量直) / 2
				searchUserCredibility
						.setProduction_quality((searchUserCredibility
								.getProduction_quality() * changeNum + searchAuditRecord
									.getProduction_quality()) / (changeNum + 1));
				// 更新后的制作质量 (原速度直+现速度直) / 2
				searchUserCredibility
						.setProduction_speed((searchUserCredibility
								.getProduction_speed() * changeNum + searchAuditRecord
									.getProduction_speed()) / (changeNum + 1));
				// 更新后的完成率
				// 检查人员
				List<AuditRecord> cherckerList = auditRecordService
						.getcheckerNum(auditRecord);

				int chercker = 0;
				if (cherckerList != null) {
					chercker = cherckerList.size();
				}
				// 检查次数及数量

				List<Long> checkCount = auditRecordService
						.getcheckCount(auditRecord);
				// 平均通过率
				double avgPassingRate = 0.0;
				for (Long checkNum : checkCount) {
					avgPassingRate += checkNum;
				}
				avgPassingRate = avgPassingRate / checkCount.size() / chercker;
				double passing = (searchUserCredibility.getPassing_rate() / 100
						* changeNum + avgPassingRate)
						/ (changeNum + 1) * 100;
				double addPassing = 0.0; // 奖励通过率

				if (teskNum >= 2) {
					addPassing = 0.8;
				} else if (teskNum >= 4) {
					addPassing = 0.5;
				}

				if ((passing + addPassing) > 100) { // 通过率只可在100以内
					passing = 100;

				} else {
					passing += addPassing;

				}
				// 更新后的通过率
				searchUserCredibility.setPassing_rate(passing);
				// 更新更改次数
				searchUserCredibility.setUpdate_tiems(searchUserCredibility
						.getUpdate_tiems() + 1);
				// 更新个人信息
				userCredibilityService.updateIgnoreNull(searchUserCredibility);
				return new Result(Result.SUCCESS, "成功", null);

			}
		}
		return new Result(Result.SUCCESS, "成功", map);
	}

	@RequestMapping("/getRepairInfo")
	@ResponseBody
	public Result getRepairInfo(String task_type, Long task_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_id", task_id);
		if (task_type.equals("建模")) {
			map.put("is_model_task", "1");
		} else {
			map.put("is_model_task", "0");
		}
		Repair repair = modelTaskService.getRepairInfo(map);
		return new Result(Result.SUCCESS, "成功", repair);

	}

	@RequestMapping("/payTaskMoney")
	@ResponseBody
	@Transactional
	public Result payTaskMoney(Long task_id, String task_type, String password,
			HttpSession session) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		UserCompany u1 = userCompanyService.companygetUser(company_id);
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Long isMaker = modelTaskService.getIsMaker(user_id);
		Long isChecker = modelTaskService.isChecker(user_id);
		if (isMaker == null && isChecker == null) {
			if (!user_id.equals(u1.getUse_id())) {
				PrivilegeInfoVo info = new PrivilegeInfoVo();
				info.setCompany_id(company_id);
				info.setUser_id(user_id);
				info.setPrivilege_url("payTaskMoney");
				info.setParent_id(14L);
				List<PrivilegeInfoVo> privilegeList = privilegeInfoService
						.isPrivilegeByUrl(info);
				if (privilegeList.size() == 0) {
					return new Result(Result.FAILURE, "您沒用该访问权限", null);
				}
			}
		}
		Long taskType = 0L;
		ModelTaskVo mTask = new ModelTaskVo();
		AnimationLightTaskVo aTask = new AnimationLightTaskVo();
		if (task_type.equals("建模")) {

			mTask.setId(task_id);
			mTask.setStage("待评价");
			mTask.setTask_status("待评价");
			mTask.setCompany_stage("待评价");
			mTask.setCompany_status("待评价");
			// modelTaskService.updateModelTask(mTask);
			taskType = 1L;
		} else if (task_type.equals("动画")) {
			aTask.setId(task_id);
			aTask.setStage("待评价");
			aTask.setTask_status("待评价");
			aTask.setCompany_stage("待评价");
			aTask.setCompany_status("待评价");
			// animationLightTaskService.updateAnimationLightTask(aTask);
			taskType = 2L;
		} else {
			taskType = 3L;

			aTask.setId(task_id);
			aTask.setStage("待评价");
			aTask.setTask_status("待评价");
			aTask.setCompany_stage("待评价");
			aTask.setCompany_status("待评价");
			// animationLightTaskService.updateAnimationLightTask(aTask);
		}

		// 付款
		String retn = freezeRecordService.releaseFreezeRecord(task_id,
				taskType, password, user_id, "1", "", null);
		evaluationTaskMap.put("task_id", task_id);
		evaluationTaskMap.put("task_type", task_type);
		Timer timer = new Timer();
		EvaluationTaskTimer task = new EvaluationTaskTimer();
		timer.schedule(task, 1000 * 60 * 60 * 72);
		if ("1".equals(retn)) {
			if (task_type.equals("建模")) {

				modelTaskService.updateModelTask(mTask);
			} else if (task_type.equals("动画")) {

				animationLightTaskService.updateAnimationLightTask(aTask);

			} else {

				animationLightTaskService.updateAnimationLightTask(aTask);
			}

			return new Result(Result.SUCCESS, "成功", null);
		} else {
			return new Result(retn, null);
		}
	}

	class EvaluationTaskTimer extends TimerTask {
		@Override
		public void run() {
			Long task_id = (Long) evaluationTaskMap.get("task_id");
			String task_type = (String) evaluationTaskMap.get("task_type");
			if (task_type.equals("建模")) {
				ModelTaskVo task = new ModelTaskVo();
				task.setId(task_id);
				task = modelTaskService.getModelTaskById(task);
				if (task.getCompany_stage().equals("待评价")) {
					// 添加公司评论
					CompanyEvaluation companyEvaluation = new CompanyEvaluation();
					companyEvaluation.setContent("未做出评价，默认好评");
					companyEvaluation.setUser_id(task.getUser_id());
					companyEvaluation.setCompany_id(task.getCompany_id());
					companyEvaluation.setEvaluate_type("1");
					companyEvaluation.setCreate_time(new Date());
					companyEvaluation.setStatus("1");
					companyEvaluation.setMatch_degree(5L);
					companyEvaluation.setCedibility(5L);
					companyEvaluation.setTask_id(task_id);
					companyEvaluation.setTask_type(1L);
					companyEvaluationService.save(companyEvaluation);
					// 改为已评论
					task.setCompany_stage("已完成");
					task.setCompany_status("已完成");

				}
				if (task.getStage().equals("待评价")) {
					// 用户评论
					UserEvaluation userEvaluation = new UserEvaluation();
					userEvaluation.setContent("未做出评价，默认好评");
					userEvaluation.setUser_id(task.getUser_id());
					userEvaluation.setEvaluate_type("1");
					userEvaluation.setCreate_time(new Date());
					userEvaluation.setStatus("1");
					userEvaluation.setProduction_speed(5L);
					userEvaluation.setProduction_quality(5L);
					userEvaluation.setTask_id(task_id);
					userEvaluation.setTask_type(1L);
					userEvaluationService.save(userEvaluation);
					task.setStage("已完成");
					task.setTask_status("已完成");
				}
				modelTaskService.updateModelTask(task);
			} else {
				AnimationLightTaskVo lightTask = new AnimationLightTaskVo();
				lightTask.setId(task_id);
				lightTask = animationLightTaskService
						.getAnimationLightTaskById(lightTask);
				if (lightTask.getCompany_stage().equals("待评价")) {
					// 添加公司评论
					CompanyEvaluation companyEvaluation = new CompanyEvaluation();
					companyEvaluation.setContent("未做出评价，默认好评");
					companyEvaluation.setUser_id(lightTask.getUser_id());
					companyEvaluation.setCompany_id(lightTask.getCompany_id());
					companyEvaluation.setTask_type(task_type.equals("动画") ? 2L
							: 3L);
					companyEvaluation.setCreate_time(new Date());
					companyEvaluation.setStatus("1");
					companyEvaluation.setMatch_degree(5L);
					companyEvaluation.setCedibility(5L);
					companyEvaluation.setTask_id(task_id);
					companyEvaluation.setTask_type(1L);
					companyEvaluation.setEvaluate_type("1");
					companyEvaluationService.save(companyEvaluation);
					lightTask.setCompany_stage("已完成");
					lightTask.setCompany_status("已完成");

				}
				if (lightTask.getStage().equals("待评价")) {
					// 用户评论
					UserEvaluation userEvaluation = new UserEvaluation();
					userEvaluation.setContent("未做出评价，默认好评");
					userEvaluation.setUser_id(lightTask.getUser_id());
					userEvaluation.setEvaluate_type("1");
					userEvaluation.setCreate_time(new Date());
					userEvaluation.setStatus("1");
					userEvaluation.setProduction_speed(5L);
					userEvaluation.setProduction_quality(5L);
					userEvaluation.setTask_id(task_id);
					userEvaluation.setTask_type(task_type.equals("动画") ? 2L
							: 3L);
					userEvaluationService.save(userEvaluation);
					lightTask.setStage("已完成");
					lightTask.setTask_status("已完成");

				}
				// 改为已评论
				lightTask.setStage("已评价");
				animationLightTaskService.updateAnimationLightTask(lightTask);

			}

		}
	}

	/**
	 * 修改任务的发布类型
	 */
	@RequestMapping("/updatePublishTypeOfTask")
	@ResponseBody
	public Result updatePublishTypeOfTask(String publish_type_id,
			String task_ids, String task_type, Long user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] ids = task_ids.split(",");
		System.out.println("task_ids:" + Arrays.toString(ids));
		map.put("publish_type_id", publish_type_id);
		map.put("ids", ids);
		map.put("user_id", user_id);
		if (task_type.equals("建模")) {
			modelTaskService.updatePublishTypeOfTask(map);
		} else {
			animationLightTaskService.updatePublishTypeOfTask(map);
		}
		return new Result(Result.SUCCESS, "成功", null);

	}

	@RequestMapping("/getNoticeInfo")
	@ResponseBody
	public Result getNoticeInfo(Notice notice, HttpSession session) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		// Long isMaker = modelTaskService.getIsMaker(user_id);
		//
		// if (isMaker!=null&&isMaker==5) {
		// // 操作者代表会员
		// notice.setRole("普通会员");
		// }else{
		// // 操作者代表公司
		// notice.setRole("动画公司");
		// }
		// System.out.println(notice);
		List<Notice> notices = modelTaskService.getNoticeInfo(notice);
		return new Result(Result.SUCCESS, "成功", notices);
	}

	@RequestMapping("/getTaskDetails")
	@ResponseBody
	public Result getTaskDetails(Long task_id, HttpSession session) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
//		Long user_id=9L;
		Long role_id = modelTaskService.getRoleIdByUserId(user_id);
		ModelTaskVo task = modelTaskService.getDetails(task_id);
		session.setAttribute("projectCompany", task.getCompany_id());
		// if (task.getStage() != null && task.getStage().equals("进行中")
		// && !task.getTask_status().equals("收回中")
		// && task.getEnd_time().before(new Date())) {
		// ModelTaskVo v = new ModelTaskVo();
		// v.setTask_status("收回中");
		// v.setId(task_id);
		// task.setTask_status("收回中");
		// modelTaskService.updateModelTask(v);
		// }
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("task_id", task_id);
		map.put("task_type", "建模");
		Long id = modelTaskService.getIsDrawOfMe(map);
		if (id == null) {
			task.setDraw(false);
		} else {
			task.setDraw(true);
		}
		Long project_id = task.getProject_id();
		String is_parent_project = task.getIs_parent_poject();
		if (is_parent_project.equals("1")) {
			task.setProject(projectService.getProjectById(project_id));
		} else {
			task.setSubProject(subProjectService.getSubProjectById(project_id));
		}
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("user_id", user_id);
		mp.put("project_id", task.getProject_id());
		mp.put("is_parent_project", task.getIs_parent_poject());
		mp.put("task_id", task.getId());
		mp.put("task_type", "建模");
		System.out.println("mp----:" + mp);
		Long isGroupMember = modelTaskService.getIsGroupMemeber(mp);
		if (role_id == 2) {
			task.setObserver("company");
		} else {
			if ("指派中".equals(task.getTask_status())) {
				task.setObserver("maker");
			} else {
				if (isGroupMember != null && isGroupMember > 0) {
					task.setObserver("company");
				} else {
					task.setObserver("maker");
				}
			}
		}
		System.out.println(task.getStage()+":"+"审核中".equals(task.getStage()));
		if ("审核中".equals(task.getStage())) {
			List<Map<String, Object>> checkInfos = new ArrayList<Map<String, Object>>();
			Long isChecker = modelTaskService.getIsCheckerOrNot(mp);
			if (isChecker != null) {
				task.setObserver("checker");
			}
			// 暂时废弃
			// List<String> checkers = modelTaskService.getCheckersOfTask(mp);
			// String currentChecker = modelTaskService.getCurrentChecker(mp);
			// task.setCheckers(checkers);
			// task.setCurrentChecker(currentChecker);
			Long taskCheckId = modelTaskService.getTaskCheckId(mp);
			Long maxDegree = modelTaskService.getMaxDegreeNum(mp);
			Date submitDate = null;
			if (maxDegree!=null&&maxDegree >= 1) {
				submitDate = modelTaskService.getSubmitDate(mp);
			}
			Long currentDegree = modelTaskService.getCurrentCheckNum(mp);
			System.out.println("maxDegree"+maxDegree);
			System.out.println("currentDegree:"+currentDegree);
			if(maxDegree!=null){
				for (int i = 1; i <= maxDegree; i++) {
					Map<String, Object> m = new HashMap<String, Object>();
					if(i==1&&currentDegree.equals(1L)){
						Date checkDate = new Date();
						long useTime = checkDate.getTime()
								- submitDate.getTime();
						m.put("checker", "审核员01");
						m.put("useTime", useTime);
						checkInfos.add(m);
					 
					} else if (i < currentDegree&&currentDegree>=2) {
						mp.put("task_check_id", taskCheckId);
						mp.put("num", i-1);
						Date checkDate = modelTaskService.getCheckDate(mp);
						if (i == 1 && checkDate != null && submitDate != null) {
							long useTime = checkDate.getTime()
									- submitDate.getTime();
							m.put("checker", "审核员01");
							m.put("useTime", useTime);
							checkInfos.add(m);
						} else {
							mp.put("num", i-2);
							Date lastCheckDate = modelTaskService.getCheckDate(mp);
							long useTime = lastCheckDate.getTime()-checkDate.getTime();
							if (i < 10) {
								m.put("checker", "审核员0" + i);
							} else {
								m.put("checker", "审核员" + i);
							}
							m.put("useTime", useTime);
							checkInfos.add(m);
						}
					}else if(i==currentDegree&&i>=2){
						mp.put("num", i-2);
						Date lastCheckDate = modelTaskService.getCheckDate(mp);
						long useTime =new Date().getTime()-lastCheckDate.getTime();
						if (i < 10) {
							m.put("checker", "审核员0" + i);
						} else {
							m.put("checker", "审核员" + i);
						}
						m.put("useTime", useTime);
						checkInfos.add(m);
					}else if(i>currentDegree){
						if (i < 10) {
							m.put("checker", "审核员0" + i);
							checkInfos.add(m);
						} else {
							m.put("checker", "审核员" + i);
							checkInfos.add(m);
						}
					}
					
				}
				task.setCheckInfos(checkInfos);
			}
		}
		return new Result(Result.SUCCESS, "成功", task);
	}

	@RequestMapping("/getConpanyTaskInfo")
	@ResponseBody
	public Result getConpanyTaskInfo(Long company_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long publishTaskNums = modelTaskService.getPublishTaskNums(company_id);
		Long successTaskNums = modelTaskService.getSuccessTaskNums(company_id);
		Double successsTaskRate = (double) Math
				.round((successTaskNums * 1.0 / publishTaskNums) * 100) / 100;
		map.put("publishTaskNums", publishTaskNums);
		map.put("successTaskNums", successTaskNums);
		map.put("successsTaskRate", successsTaskRate);
		return new Result(Result.SUCCESS, "成功", map);
	}

	@RequestMapping("/getSuccessTask")
	@ResponseBody
	public Result getSuccessTask(Long user_id) {
		Long successTaskNums = modelTaskService
				.getSuccessTaskNumsOfUser(user_id);
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("successTaskNums", successTaskNums);
		return new Result(Result.SUCCESS, "成功", map);
	}

	@RequestMapping("/checkTaskIsReceivingOrNot")
	@ResponseBody
	public Result checkTaskIsReceivingOrNot(Long task_id, Long is_model_task) {
		if (is_model_task == 1) {
			ModelTaskVo m = modelTaskService.getReceivingModelTaskById(task_id);
			if (m == null) {
				return new Result(Result.FAILURE, "成功", null);
			} else {
				return new Result(Result.SUCCESS, "成功", null);
			}
		} else {
			AnimationLightTaskVo a = animationLightTaskService
					.getReceivingModelTaskById(task_id);
			if (a == null) {
				return new Result(Result.FAILURE, "成功", null);
			} else {
				return new Result(Result.SUCCESS, "成功", null);
			}
		}

	}

}
