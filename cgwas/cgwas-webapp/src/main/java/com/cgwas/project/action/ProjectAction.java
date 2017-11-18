package com.cgwas.project.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.FileUtils;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.common.utils.PinyinUtil;
import com.cgwas.company.entity.Company;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.companyPlugin.entity.CompanyPluginVo;
import com.cgwas.companyPlugin.service.api.ICompanyPluginService;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.companySoftware.service.api.ICompanySoftwareService;
import com.cgwas.frameRateCompany.entity.FrameRateCompanyVo;
import com.cgwas.frameRateCompany.service.api.IFrameRateCompanyService;
import com.cgwas.labelTag.entity.LabelTag;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.project.dao.api.IProjectDao;
import com.cgwas.project.entity.Graphics;
import com.cgwas.project.entity.Project;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.projectSearch.service.api.IProjectSearchService;
import com.cgwas.projectStatus.entity.ProjectStatusVo;
import com.cgwas.projectStatus.service.api.IProjectStatusService;
import com.cgwas.projectType.entity.ProjectTypeVo;
import com.cgwas.projectType.service.api.IProjectTypeService;
import com.cgwas.resolutionCompany.entity.ResolutionCompanyVo;
import com.cgwas.resolutionCompany.service.api.IResolutionCompanyService;
import com.cgwas.search.entity.Search;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.entity.SubProjectVo;
import com.cgwas.subproject.service.api.ISubProjectService;
import com.cgwas.taskrecord.entity.TaskRecord;
import com.cgwas.user.dao.api.IUserDao;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userInfo.entity.UserInfoVo;
import com.cgwas.util.AddressUtils;
import com.cgwas.util.CalendarUtil;
import com.cgwas.visitor.entity.Visitor;

/**
 * Author yangjun
 */
@Controller
@RequestMapping("cgwas/projectAction")
public class ProjectAction {
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IProjectDao projectDao;
	@Autowired
	private IProjectService projectService;// 项目业务对象
	@Autowired
	private IProjectStatusService projectStatusService;// 项目状态业务对象
	@Autowired
	private IProjectTypeService projectTypeService;// 项目类型业务对象
	@Autowired
	private IFrameRateCompanyService frameRateService;// 帧数率业务对象
	@Autowired
	private IResolutionCompanyService resolutionService;// 分辨率业务对象
	@Autowired
	private ICompanySoftwareService companySoftwareService;// 公司软件业务对象
	@Autowired
	private ICompanyPluginService companyPluginService;// 公司插件业务对象
	@Autowired
	private IProjectSearchService projectSearchService;// 项目搜索信息业务对象
	@Autowired
	private ISubProjectService subProjectService;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private IUserService uservice;
	@Autowired
	private ICompanyFilesService companyFilesService;
	@Autowired
	private IUserDao userDao;// 用户
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	private boolean flag = true;
    @RequestMapping("/getHotSearch")
    @ResponseBody
    public Result getHotSearch(){
    	List<Search> searchs=projectService.getHotSearchs();
    	return new Result(Result.SUCCESS, "成功", searchs);
    }
	@RequestMapping("/endProjectAction")
	@ResponseBody
	public Result endProjectAction(Long project_id) {
		Map<String, Object> map = getSubProjectIdsOfParent(project_id);
		if (map != null) {
			Long newTaskNum = modelTaskService.getNewTaskNums1(map);
			if (newTaskNum == null) {
				newTaskNum = 0L;
			}
			Long runTaskNum = modelTaskService.getRunTaskNums(map);
			if (runTaskNum == null) {
				runTaskNum = 0L;
			}
			Long finishTaskNum = modelTaskService.getFinishTaskNums(map);
			if (finishTaskNum == null) {
				finishTaskNum = 0L;
			}
			Long total = newTaskNum + runTaskNum + finishTaskNum;
			if (total == 0) {
				total++;
			}
			if (finishTaskNum == total && total != 0) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("project_id", project_id);
				m.put("project_status_id", 5);
				projectService.updateProjectState(m);
				return new Result(Result.SUCCESS, "成功", null);
			}

		}
		return new Result(Result.FAILURE, "该项目有未完成任务，不能结束", null);
	}

	@RequestMapping("/endSubProjectAction")
	@ResponseBody
	public Result endSubProjectAction(Long project_id) {
		Map<String, Object> map = getSubProjectIdsOfSub(project_id);
		if (map != null) {
			Long newTaskNum = modelTaskService.getNewTaskNums1(map);
			if (newTaskNum == null) {
				newTaskNum = 0L;
			}
			Long runTaskNum = modelTaskService.getRunTaskNums(map);
			if (runTaskNum == null) {
				runTaskNum = 0L;
			}
			Long finishTaskNum = modelTaskService.getFinishTaskNums(map);
			if (finishTaskNum == null) {
				finishTaskNum = 0L;
			}
			Long total = newTaskNum + runTaskNum + finishTaskNum;
			if (total == 0) {
				total++;
			}
			if (finishTaskNum == total && total != 0) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("project_id", project_id);
				m.put("project_status_id", 5);
				subProjectService.updateProjectState(m);
				return new Result(Result.SUCCESS, "成功", null);
			}

		}
		return new Result(Result.FAILURE, "该项目有未完成任务，不能结束", null);
	}

	@RequestMapping("/getIndexPageInfo")
	@ResponseBody
	public Result getIndexPageInfo() {
		List<Map<String, Object>> sliderProjectsInfo = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> recAndShowProjectsInfo = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> oldRecAndShowProjectsInfo = new ArrayList<Map<String, Object>>();
		List<ProjectVo> sliderProjects = projectService.getSliderProjects();
		for (ProjectVo p : sliderProjects) {
			Map<String, Object> m = getProjectInfo(p);
			Map<String,Object> mapIds=getSubProjectIdsOfParent(p.getId());
			Date publishTime=modelTaskService.getPublishTimeOfProject(mapIds);
			m.put("publishTime", publishTime);
			if (m != null) {
				sliderProjectsInfo.add(m);
			}
		}
		ProjectVo recommendProject = projectService.getRecommendProject();
		if(recommendProject!=null){
			recommendProject.setProject_id(recommendProject.getId());
			Map<String, Object> rec = getProjectInfo(recommendProject);
			if (rec != null) {
				recAndShowProjectsInfo.add(rec);
			}
		}
	
		List<ProjectVo> showProjects = projectService.getShowProjects();
		for (ProjectVo p : showProjects) {
			p.setProject_id(p.getId());
			Map<String, Object> show = getProjectInfo(p);
			if (show != null) {
				if (!p.equals(recommendProject)) {
					recAndShowProjectsInfo.add(show);
				}
			}
		}
		if (recAndShowProjectsInfo.size() == 5) {
			recAndShowProjectsInfo.remove(4);
		}
		ProjectVo oldRecommendProject = projectService
				.getOldRecommendProject();
		if(oldRecommendProject!=null){
			Map<String, Object> m = getOldProjectInfo(oldRecommendProject);
			if (m != null) {
				oldRecommendProject.setProject_id(oldRecommendProject.getId());
				oldRecAndShowProjectsInfo.add(m);
			}
		}
		List<ProjectVo> oldShowProjects = projectService.getOldShowProjects();
		for (ProjectVo p : oldShowProjects) {
			p.setProject_id(p.getId());
			Map<String, Object> show = getOldProjectInfo(p);
			if (show != null) {
				if (!p.equals(oldRecommendProject)) {
					oldRecAndShowProjectsInfo.add(show);
				}
			}
		}
		if (oldRecAndShowProjectsInfo.size() == 5) {
			oldRecAndShowProjectsInfo.remove(4);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sliderProjectsInfo", sliderProjectsInfo);
		map.put("recAndShowProjectsInfo", recAndShowProjectsInfo);
		map.put("oldRecAndShowProjectsInfo", oldRecAndShowProjectsInfo);
		return new Result(Result.SUCCESS, "成功", map);
	}

	public Map<String, Object> getOldProjectInfo(ProjectVo p) {
		Map<String, Object> map = getSubProjectIdsOfParent(p.getId());
		if (map != null) {
			Map<String, Object> mp = new HashMap<String, Object>();
			Date finishDate = modelTaskService.getFinishDateOfProject(map);
			Double useMoney = modelTaskService.getUseMoneyOfProject(map);
			// Company c=projectService.getCompanyById(p.getCompany_id());
			mp.put("id", p.getId());
			mp.put("name", p.getName());
			mp.put("slider_img", p.getSlider_img());
			mp.put("show_img", p.getShow_img());
			mp.put("recommend_img", p.getRecommend_img());
			mp.put("useMoney", getAvgRate(useMoney));
			mp.put("finishDate", finishDate);
			return mp;
		}
		return null;
	}

	public Map<String, Object> getProjectInfo(ProjectVo p) {
		Map<String, Object> map = getSubProjectIdsOfParent(p.getId());
		if (map != null) {
			Map<String, Object> mp = new HashMap<String, Object>();
			Long newTaskNum = modelTaskService.getNewTaskNums(map);
			if (newTaskNum == null) {
				newTaskNum = 0L;
			}
			Long runTaskNum = modelTaskService.getRunTaskNums(map);
			if (runTaskNum == null) {
				runTaskNum = 0L;
			}
			Long finishTaskNum = modelTaskService.getFinishTaskNums(map);
			if (finishTaskNum == null) {
				finishTaskNum = 0L;
			}
			Long total = newTaskNum + runTaskNum + finishTaskNum;
			if (total == 0) {
				total++;
			}
			System.out.println();
			Company c = projectService.getCompanyById(p.getCompany_id());
			mp.put("id", p.getId());
			mp.put("newTaskRate", getAvgRate(newTaskNum * 1.0 / total));
			mp.put("runTaskRate", getAvgRate(runTaskNum * 1.0 / total));
			mp.put("finishTaskRate", getAvgRate(finishTaskNum * 1.0 / total));
			mp.put("name", p.getName());
			mp.put("slider_img", p.getSlider_img());
			mp.put("show_img", p.getShow_img());
			mp.put("recommend_img", p.getRecommend_img());
			mp.put("company_name", c.getCompany_name());
			mp.put("budget", p.getBudget());
			return mp;
		}
		return null;
	}

	/**
	 * 根据项目id获取项目详情
	 * 
	 * @param project_id项目id
	 * @return
	 */
	@RequestMapping("/getProjectDetails")
	@ResponseBody
	public Result getProjectDetails(Long project_id) {
		// TODO项目管理者人数,已支付金额，预支付金额待处理
		ProjectVo project = projectService.getProjectDetails(project_id);
		List<Long> ids = new ArrayList<Long>();
		ids.add(project_id);
		Map<String, Object> mapIds = new HashMap<String, Object>();
		mapIds.put("ids", ids);
		mapIds.put("is_parent", 1);// 为1表示当前项目是根父项目
		List<SubProject> subProjects1 = projectService
				.getParentSubProjects(mapIds);// 查询子项目数据表中父项目是根父项目的子项目列表
		if (subProjects1.size() > 0) {// 当查询结果size>1说明该项目有子项目
			List<Long> ids1 = new ArrayList<Long>();
			for (SubProject subProject : subProjects1) {
				ids1.add(subProject.getId());
			}
			mapIds.put("ids", ids1);
			mapIds.put("is_parent", 0);// 为0表示当前项目不是根父项目
			for (int i = 0; i < subProjects1.size(); i++) {// 遍历子项目
				if (subProjects1.get(i).getIs_parent() != null
						&& subProjects1.get(i).getIs_parent().equals("1")) {
					// 当subProjects1.get(i).getIs_parent().equals("1")表示该子项目列表中的子项目还有子项目
					List<SubProject> subProjects2 = projectService
							.getSubProjects(mapIds);// 查询子项目的子项目
					if (subProjects2.size() > 0) {
						for (SubProject subProject : subProjects2) {
							ids1.add(subProject.getId());
						}
						mapIds.put("ids", ids1);
						mapIds.put("is_parent", 0);// 为0表示当前项目不是根父项目
						for (int j = 0; j < subProjects2.size(); j++) {// 遍历子项目
							if (subProjects2.get(j).getIs_parent() != null
									&& subProjects2.get(j).getIs_parent()
											.equals("1")) {
								// 当subProjects2.get(i).getIs_parent().equals("1")表示该子项目列表中的子项目还有子项目
								List<SubProject> subProjects3 = projectService
										.getSubProjects(mapIds);
								if (subProjects3.size() > 0) {// 项目一共有四层
									for (SubProject subProject : subProjects3) {
										ids1.add(subProject.getId());
									}
									mapIds.put("ids", ids1);
									mapIds.put("is_parent", 0);// 为0表示当前项目不是根父项目
								}
								break;
							}
						}

					}
					break;
				}
			}

		}
		Long useDate = getUseDate(project.getEnd_time());// 根据结束日期获取可用工作日
		project.setUseDate(useDate);
		if (subProjects1.size() > 0) {// 当subProjects1.size()>0表示该项目下有子项目
			@SuppressWarnings("unchecked")
			List<Long> id = (List<Long>) mapIds.get("ids");
			project.setSubProjectNums(id.size());// 设置项目的子项目总数
		} else {
			project.setSubProjectNums(0);// 项目子项目数为0
		}

		Integer model_task_total = projectService.getModelTaskTotal(mapIds);// 项目下的所有模型任务
		Integer animation_task_total = projectService
				.getanimationTaskTotal(mapIds);// 项目下的所有动画任务
		Integer light_task_total = projectService.getLightTaskTotal(mapIds);// 项目下所有的灯光任务
		project.setTaskTotalNums(light_task_total + model_task_total
				+ animation_task_total);// 项目下所有的任务总数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project_id", project_id);
		map.put("is_parent_project", "1");
		Integer managerNums = projectService.getManagerNums(map);
		// TODO 项目管理者人数
		project.setProjectManagerNums(managerNums);
		Integer modelTaskMakerNums = projectService
				.getModelTaskMakerNums(mapIds);// 项目下所有建模任务的制作者人数
		Integer animationTaskMakerNums = projectService
				.getAnimationTaskMakerNums(mapIds);// 项目下所有动画灯光任务的制作者人数
		project.setProjectMakerNums(modelTaskMakerNums + animationTaskMakerNums);// 项目下所有任务的制作者人数
		// TODO项目的预支付金额
		project.setProjectPrepayment(project.getBudget());
		// TODO项目已支付金额
		Double projectPaid = modelTaskService.getProjectPaid(mapIds);
		if (projectPaid != null) {
			project.setProjectPaid(getAvgRate(projectPaid));
		}
		return new Result(Result.SUCCESS, "成功", project);
	}

	/**
	 * 获取给点结束日期的可用工作日
	 * 
	 * @param end_time
	 * @return
	 */
	private static Long getUseDate(Date end_time) {
		Date date = new Date();
		Long d1 = end_time.getTime();
		Long d2 = date.getTime();
		Long useDate = (d1 - d2) / (1000 * 60 * 60 * 24);// 单位是天
		if (useDate <= 0) {
			return 0L;
		}
		return useDate;
	}
    //任务概况
	@RequestMapping("/getTasksInfoOfDate")
	@ResponseBody
	public Result getTasksInfoOfDate(Long project_id, String is_parent_project) {
		List<Object> newTaskNums = new ArrayList<Object>();
		List<Object> runTaskNums = new ArrayList<Object>();
		List<Object> finishTaskNums = new ArrayList<Object>();
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		if (map != null) {
			String[] dates = CalendarUtil.getCurrentDateOf7Days();
			String[] datees = CalendarUtil.getCurrentDateOfDays();
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("dates", datees);
			for (int i = 0; i < dates.length; i++) {
				map.put("date", dates[i]);
				Long newTaskNum = modelTaskService.getNewTaskNums(map);
				newTaskNums.add(newTaskNum);
				Long runTaskNum = modelTaskService.getRunTaskNums(map);
				runTaskNums.add(runTaskNum);
				Long finishTaskNum = modelTaskService.getFinishTaskNums(map);
				finishTaskNums.add(finishTaskNum);
			}
			mp.put("newTaskNums", newTaskNums);
			mp.put("runTaskNums", runTaskNums);
			mp.put("finishTaskNums", finishTaskNums);
			return new Result(Result.SUCCESS, "成功", mp);
		}
		return new Result(Result.FAILURE, "失败", null);
	}
//总报表里面的任务通过率，交易额，任务发布
	@RequestMapping("/getTasksInfoOfProject")
	@ResponseBody
	public Result getTasksInfoOfProject(Long project_id,
			String is_parent_project, String type) throws Exception {
		List<Double> passRate = new ArrayList<Double>();
		List<Double> trading = new ArrayList<Double>();
		List<Long> publishTaskNums = new ArrayList<Long>();
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		if (map != null) {
			String[] dates = CalendarUtil.getDaysOfCurrentMonth();
			System.out.println(Arrays.toString(dates));
			for (String s : dates) {
				map.put("date", s);
				List<ModelTaskVo> tasks = modelTaskService
						.getFinishTasksByMap(map);

				Double passRateOfTasks = getPassRates(tasks);
				passRate.add(getAvgRate(passRateOfTasks) * 100);

				Double tradingOfTasks = modelTaskService.getTradingByMap(map);
				if (tradingOfTasks == null) {
					trading.add(0.0);
				} else {
					trading.add(getAvgRate(tradingOfTasks));
				}
				Long publishNumsOfTasks = modelTaskService
						.getPublishNumsByMap(map);
				publishTaskNums.add(publishNumsOfTasks);
			}
			map.clear();
			map.put("passRate", passRate);
			map.put("trading", trading);
			map.put("publishTaskNums", publishTaskNums);
		}
		return new Result(Result.SUCCESS, "成功", map);
	}

	private Double getPassRates(List<ModelTaskVo> tasks) {
		Double totalPassRate = 0.0;
		for (ModelTaskVo m : tasks) {
			System.out.println("id:" + m.getId() + ",class_id:"
					+ m.getClass_id() + ",is_parent_project:"
					+ m.getIs_parent_poject() + ",project_id:"
					+ m.getProject_id());
			Double passRate = getPassRate(m);
			totalPassRate += passRate;
		}
		if (tasks.size() > 0) {
			return totalPassRate / tasks.size();
		}
		return 0.0;
	}

	private Double getPassRate(ModelTaskVo m) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_id", m.getId());
		map.put("project_id", m.getProject_id());
		map.put("is_parent_project", m.getIs_parent_poject());
		Long checkerNums = modelTaskService.getMaxDegreeNum(map);
		if (m.getClass_id() == 1) {
			map.put("task_type", "建模");
			map.put("is_model_task", "1");
		} else if (m.getClass_id() == 2) {
			map.put("task_type", "动画");
			map.put("is_model_task", "0");
		} else {
			map.put("task_type", "灯光");
			map.put("is_model_task", "0");
		}
		System.out.println("map:" + map);
		Long maxRepairNums = modelTaskService.getRecheckNum(map);
		if (maxRepairNums == null) {
			maxRepairNums = 0L;
		}
		System.out.println("maxRepairNums:" + maxRepairNums);
		Double rate = 0.0;
		for (int i = 0; i <= maxRepairNums; i++) {
			map.put("num", i);
			Long checkerNumOfTask = modelTaskService.getCheckerNumOfTask(map);
			rate += checkerNumOfTask * 1.0 / checkerNums;
		}
		if (maxRepairNums == 0) {
			return rate;
		} else if (rate > 0 && rate <= 2) {
			return getAvgRate(rate / maxRepairNums) + 0.008;
		} else if (rate > 2 && rate <= 4) {
			return getAvgRate(rate / maxRepairNums + 0.005);
		} else {
			return getAvgRate(rate / maxRepairNums);
		}

	}
	public double getAvgRate(double rate) {
		return ((int) Math.round(rate * 100)) / 100.0;
	}
	 //勤奋表
	@RequestMapping("/getHardWoringUsersInfoOfProject")
	@ResponseBody
	public Result getHardWoringUsersInfoOfProject(Long project_id,
			String is_parent_project, String type) throws Exception {
		List<Map<String, Object>> HardWoringUsersInfo = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		int sum = 0;
		int maxDays = 1;
		if (map != null) {
			Calendar c = Calendar.getInstance();
			if ("week".equals(type)) {
				maxDays = 7;
			} else {
				maxDays = CalendarUtil.getDaysByYearMonth(c.get(Calendar.YEAR),
						Calendar.MONTH + 1);
			}
			List<Long> userIds = projectService.getUserIdsByMap(map);
			Map<String, Object> dMap = new HashMap<String, Object>();
			for (Long id : userIds) {
				dMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", dMap);
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("username", u.getName());
				mp.put("headUrl", u.getHead_pic_path());
				map.put("user_id", id);
				Date joinTime = projectService.getJoinTimeOfMaker(map);
				mp.put("joinTime", joinTime);
				if ("week".equals(type)) {
					dMap.put("beginTime", CalendarUtil.getWeekMonday());
				} else {
					dMap.put("beginTime", CalendarUtil.getFirtDayOfMonth());
				}
				System.out.println(dMap);
				Long finishNums = modelTaskService.getFinishedNumsOfUser(dMap);
				System.out.println("f:"+finishNums);
				if (finishNums != null) {
					sum += finishNums;
				}
				mp.put("finishedNums", finishNums);
				HardWoringUsersInfo.add(mp);
			}
			Collections.sort(HardWoringUsersInfo, new Comparator<Map>() {
				@Override
				public int compare(Map o1, Map o2) {

					return (int) (((Long) o2.get("finishedNums")) - ((Long) o1
							.get("finishedNums")));
				}
			});
		}
		map.clear();
		map.put("HardWoringUsersInfo", HardWoringUsersInfo);
		map.put("avgDays", Math.round(sum * 1.0 / maxDays));
		return new Result(Result.SUCCESS, "成功", map);
	}
    //本周任务完成情况
	@RequestMapping("/getTaskFinishedInfoOfProject")
	@ResponseBody
	public Result getTaskFinishedInfoOfProject(Long project_id,
			String is_parent_project) throws Exception {
		List<Map<String, Object>> groupMember = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		if (map != null) {
			List<Long> userIds = projectService.getUserIdsByMap(map);
			Map<String, Object> dMap = new HashMap<String, Object>();
			for (Long id : userIds) {
				dMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", dMap);
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("username", u.getName());
				mp.put("headUrl", u.getHead_pic_path());
				map.put("user_id", id);
				Date joinTime = projectService.getJoinTimeOfMaker(map);
				mp.put("joinTime", joinTime);
				dMap.put("beginTime", CalendarUtil.getWeekMonday());
				Long finishNums = modelTaskService.getFinishedNumsOfUser(dMap);
				Long outTaskEndTimeNums = modelTaskService
						.getOutTaskEndTimeNums(dMap);
				mp.put("finishedNums", finishNums);
				mp.put("outTaskEndTimeNums", outTaskEndTimeNums);
				groupMember.add(mp);
			}
			Collections.sort(groupMember, new Comparator<Map>() {
				@Override
				public int compare(Map o1, Map o2) {
					if (((Date) o1.get("joinTime")).after((Date) o2
							.get("joinTime"))) {
						return 1;
					}
					return -1;
				}
			});
		}
		return new Result(Result.SUCCESS, "成功", groupMember);
	}
    //参加项目时间
	@RequestMapping("/getMakerInfoOfProject")
	@ResponseBody
	public Result getMakerInfoOfProject(Long project_id,
			String is_parent_project) throws Exception {
		List<Map<String, Object>> groupMember = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		if (map != null) {
			List<Long> userIds = projectService.getUserIdsByMap(map);
			Map<String, Object> dMap = new HashMap<String, Object>();
			for (Long id : userIds) {
				dMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", dMap);
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("username", u.getName());
				mp.put("headUrl", u.getHead_pic_path());
				User user = uservice.getUserById(id);
				try {
					Visitor v = AddressUtils.getAddressByIp(user.getIp());
					mp.put("address", v.getCity());
				} catch (Exception e) {
					mp.put("address", "");
				}
				map.put("user_id", id);
				Date joinTime = projectService.getJoinTimeOfMaker(map);
				mp.put("joinTime", joinTime);
				groupMember.add(mp);
			}
			Collections.sort(groupMember, new Comparator<Map>() {
				@Override
				public int compare(Map o1, Map o2) {
					if (((Date) o1.get("joinTime")).after((Date) o2
							.get("joinTime"))) {
						return -1;
					}
					return 1;
				}
			});
		}
		return new Result(Result.SUCCESS, "成功", groupMember);
	}
    //团队计划完成率
	@RequestMapping("/getGroupMemberFinishRate")
	@ResponseBody
	public Result getGroupMemberFinishRate(Long project_id,
			String is_parent_project) {
		List<Map<String, Object>> groupMember = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		if (map != null) {
			List<Long> userIds = projectService.getUserIdsByMap(map);
			Map<String, Object> dMap = new HashMap<String, Object>();
			for (Long id : userIds) {
				dMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", dMap);
				List<Long> ids = modelTaskService.getTaskNumsOfUser(id);
				dMap.put("beginTime", CalendarUtil.getWeekMonday());
				Long finishNums = modelTaskService.getFinishedNumsOfUser(dMap);
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("username", u.getName());
				mp.put("finishRate",
						Math.round(finishNums * 100.0 / ids.size()));
				if (u.getSex().equals("1")) {
					mp.put("sex", "男");
				} else {
					mp.put("sex", "女");
				}
				groupMember.add(mp);
			}
		}
		return new Result(Result.SUCCESS, "成功", groupMember);
	}
    //我的团队
	@RequestMapping("/getGroupMemberInfo")
	@ResponseBody
	public Result getGroupMemberInfo(Long project_id, String is_parent_project) {
		List<Map<String, Object>> groupMember = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		if (map != null) {
			List<Long> userIds = projectService.getUserIdsByMap(map);
			Map<String, Object> dMap = new HashMap<String, Object>();
			for (Long id : userIds) {
				System.out.println(id);
				dMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", dMap);
				List<Long> ids = modelTaskService.getTaskNumsOfUser(id);
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("userName", u.getName());
				mp.put("headUrl", u.getHead_pic_path());
				mp.put("taskNums", ids.size());
				groupMember.add(mp);
			}
		}
		return new Result(Result.SUCCESS, "成功", groupMember);
	}
     //平台会员与员工比例
	@RequestMapping("/getMakerNumsOfGroup")
	@ResponseBody
	public Result getMakerNumsOfGroup(Long project_id, String is_parent_project) {
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		if (map != null) {
			List<Long> userIds = projectService.getUserIdsByMap(map);
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("userIds", userIds);
			List<Long> ids = projectService.getIdsByMap(mp);
			map.clear();
			map.put("makerNums", (userIds.size() - ids.size()));
			map.put("employeeNums", ids.size());
		}
		return new Result(Result.SUCCESS, "成功", map);
	}

	// 总报表团队任务平均工时
	@RequestMapping("/getAvgTaskTimes")
	@ResponseBody
	public Result getAvgTaskTimes(Long project_id, String is_parent_project) {
		List<Object> avgMakerUseTimes = new ArrayList<Object>();
		List<Object> avgCheckerUseTimes = new ArrayList<Object>();
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		if (map != null) {
			String[] dates = CalendarUtil.getCurrentDateOf7Days();
			String[] datees = CalendarUtil.getCurrentDateOfDays();
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("dates", datees);
			for (int i = 0; i < dates.length; i++) {
				System.out.println("date:" + dates[i]);
				map.put("date", dates[i]);
				Double makeTimes = projectService
						.getTaskUseTimesOfProjects(map);
				Long userNums = projectService.getTaskUserNumsOfParents(map);
				System.out.println("makeTimes:" + makeTimes + "," + "userNums:"
						+ userNums);
				if (makeTimes != null && userNums != null) {
					double avgUseTimesOfMaker = ((int) Math.round(100
							* makeTimes * 1.0 / userNums)) / 100.0;
					avgMakerUseTimes.add(avgUseTimesOfMaker);
				} else {
					avgMakerUseTimes.add(0);
				}
				Double checkTimes = projectService
						.getTaskCheckUseTimesOfProjects(map);
				Long checkerNums = projectService.getCheckNums(map);
				System.out.println("checkTimes:" + checkTimes + ","
						+ "checkerNums:" + checkerNums);
				if (checkTimes != null && checkerNums != null) {
					double avgUseTimesOfChecker = ((int) Math.round(100
							* checkTimes * 1.0 / checkerNums)) / 100.0;
					avgCheckerUseTimes.add(avgUseTimesOfChecker);
				} else {
					avgCheckerUseTimes.add(0);
				}
			}
			mp.put("avgMakerUseTimes", avgMakerUseTimes);
			mp.put("avgCheckerUseTimes", avgCheckerUseTimes);
			return new Result(Result.SUCCESS, "成功", mp);
		}
		return new Result(Result.FAILURE, "失败", null);
	}
	// 总报表里面的任务在各个阶段的总量
	@RequestMapping("/getTaskNumsOfProject")
	@ResponseBody
	public Result getTaskNumsOfProject(Long project_id, String is_parent_project) {
		Map<String, Object> map = null;
		if (is_parent_project.equals("1")) {
			map = getSubProjectIdsOfParent(project_id);
		} else {
			map = getSubProjectIdsOfSub(project_id);
		}
		if (map != null) {
			Long totalNums = modelTaskService.getTaskTotalOfProject(map);
			Long runnNums = modelTaskService.getRunningNumsOfProject(map);//
			Long checkNuns = modelTaskService.getCheckNumsOfProject(map);
			Long finishedNums = modelTaskService.getFinishedNumsOfProject(map);
			Long abandonNums = modelTaskService.getabandonNumsOfProject(map);
			map.put("date", CalendarUtil.getLastDay());
			TaskRecord record = modelTaskService.getTaskRecord(map);
			map.clear();
			if (record == null) {
				map.put("totalNumsState", "up");
				map.put("runnNumsState", "up");
				map.put("checkNunsState", "up");
				map.put("finishedNumsState", "up");
				map.put("abandonNumsState", "up");
			} else {
				if (record.getTotalNums() < totalNums) {
					map.put("totalNumsState", "up");
				} else if (record.getTotalNums() > totalNums) {
					map.put("totalNumsState", "down");
				} else {
					map.put("totalNumsState", "equals");
				}
				if (record.getRunnNums() < runnNums) {
					map.put("runnNumsState", "up");
				} else if (record.getRunnNums() > runnNums) {
					map.put("runnNumsState", "down");
				} else {
					map.put("runnNumsState", "equals");
				}
				if (record.getCheckNuns() < checkNuns) {
					map.put("checkNunsState", "up");
				} else if (record.getCheckNuns() > checkNuns) {
					map.put("checkNunsState", "down");
				} else {
					map.put("checkNunsState", "equals");
				}
				if (record.getFinishedNums() < finishedNums) {
					map.put("finishedNumsState", "up");
				} else if (record.getFinishedNums() > finishedNums) {
					map.put("finishedNumsState", "down");
				} else {
					map.put("finishedNumsState", "equals");
				}
				if (record.getAbandonNums() < abandonNums) {
					map.put("abandonNumsState", "up");
				} else if (record.getAbandonNums() > abandonNums) {
					map.put("abandonNumsState", "down");
				} else {
					map.put("abandonNumsState", "equals");
				}
			}

			map.put("totalNums", totalNums);
			map.put("runnNums", runnNums);
			map.put("checkNuns", checkNuns);
			map.put("finishedNums", finishedNums);
			map.put("abandonNums", abandonNums);
			return new Result(Result.SUCCESS, "成功", map);
		}
		return new Result(Result.FAILURE, "失败", null);
	}

	/**
	 * 获取项目周期方法
	 */
	@RequestMapping("getProjectCycleInfo")
	@ResponseBody
	public Result getProjectCycleInfo(String data, String is_parent_project,
			Long id, String num) {
		Map<String, Object> map = new HashMap<String, Object>();
		// data="{'page':{'pageSize':'3','pageNo':'1'}}";
		JSONObject param = JSON.parseObject(data);
		ProjectVo project = new ProjectVo();
		if (is_parent_project.equals("1")) {
			project = projectService.getProjectById(id);
			setActualBeginTimeAndEndTime(project);
			ProjectSearchVo search = new ProjectSearchVo();// 将data对象的object转换为项目搜索对象
			// TODO公司id需要从session里面获取
			search.setFlag(is_parent_project);
			project.setPsearch(search);
			Map<String, String> map1 = (Map<String, String>) param.get("page");
			Page page = PageUtils.createPage(map1);
			page = projectService.page3(page, project);
			List<SubProjectVo> ps = (List<SubProjectVo>) page.getDataList();
			List<SubProjectVo> datas = new ArrayList<SubProjectVo>();
			for (SubProjectVo p1 : ps) {
				setActualBeginTimeAndEndTime(p1, num);
				p1.setN(1L);
				datas.add(p1);
				for (SubProjectVo p2 : p1.getSubProjects()) {
					setActualBeginTimeAndEndTime(p2, num);
					p2.setN(2L);
					datas.add(p2);
					for (SubProjectVo p3 : p2.getSubProjects()) {
						setActualBeginTimeAndEndTime(p3, num);
						p3.setN(3L);
						datas.add(p3);
					}
				}
			}
			Date begin_time = project.getBegin_time();
			Date end_time = project.getEnd_time();
			Date actual_begin_time = project.getActual_begin_time();
			Date actual_end_time = project.getActual_end_time();
			if (actual_begin_time != null
					&& begin_time.after(actual_begin_time)) {
				begin_time = actual_begin_time;
			}
			if (actual_end_time != null && end_time.before(actual_end_time)) {
				end_time = actual_end_time;
			}
			;
			if ("1".equals(num)) {
				CalendarUtil.changeDay(project);
				List<Map<String, Object>> dataList = CalendarUtil.day(
						begin_time, end_time);
				map.put("dataList", dataList);
			} else if ("2".equals(num)) {
				CalendarUtil.changeWeek(project);
				List<Map<String, Object>> dataList = CalendarUtil.week(
						begin_time, end_time);
				map.put("dataList", dataList);
			} else if ("3".equals(num)) {
				CalendarUtil.changeMonth(project);
				List<Map<String, Object>> dataList = CalendarUtil.month(
						begin_time, end_time);
				map.put("dataList", dataList);
			} else if ("4".equals(num)) {
				CalendarUtil.changeSeason(project);
				List<Map<String, Object>> dataList = CalendarUtil.season(
						begin_time, end_time);
				map.put("dataList", dataList);
			} else if ("5".equals(num)) {
				CalendarUtil.changeYear(project);
				List<Map<String, Object>> dataList = CalendarUtil.year(
						begin_time, end_time);
				map.put("dataList", dataList);
			}

			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("project", project);
			map.put("datas", datas);
		} else {
			SubProjectVo subProject = subProjectService.getSubProjectById(id);
			setActualBeginTimeAndEndTime(subProject, num);
			ProjectSearchVo search = new ProjectSearchVo();// 将data对象的object转换为项目搜索对象
			search.setFlag(is_parent_project);
			project.setId(id);
			project.setPsearch(search);
			Map<String, String> map1 = (Map<String, String>) param.get("page");
			Page page = PageUtils.createPage(map1);
			page = projectService.page3(page, project);
			List<SubProjectVo> datas = new ArrayList<SubProjectVo>();
			List<SubProjectVo> ps = (List<SubProjectVo>) page.getDataList();
			for (SubProjectVo p1 : ps) {
				setActualBeginTimeAndEndTime(p1, num);
				p1.setN(1L);
				datas.add(p1);
				for (SubProjectVo p2 : p1.getSubProjects()) {
					p2.setN(2L);
					setActualBeginTimeAndEndTime(p2, num);
					datas.add(p2);
				}
			}
			Date begin_time = subProject.getBegin_time();
			Date actual_begin_time = subProject.getActual_begin_time();
			if (actual_begin_time != null
					&& begin_time.after(actual_begin_time)) {
				begin_time = actual_begin_time;
			}
			Date end_time = subProject.getEnd_time();
			Date actual_end_time = subProject.getActual_end_time();
			if (actual_end_time != null && end_time.before(actual_end_time)) {
				end_time = actual_end_time;
			}
			;
			if ("1".equals(num)) {
				CalendarUtil.changeDay(subProject);
				List<Map<String, Object>> dataList = CalendarUtil.day(
						begin_time, end_time);
				map.put("dataList", dataList);
			} else if ("2".equals(num)) {
				CalendarUtil.changeWeek(subProject);
				List<Map<String, Object>> dataList = CalendarUtil.week(
						begin_time, end_time);
				map.put("dataList", dataList);
			} else if ("3".equals(num)) {
				CalendarUtil.changeMonth(subProject);
				List<Map<String, Object>> dataList = CalendarUtil.month(
						begin_time, end_time);
				map.put("dataList", dataList);
			} else if ("4".equals(num)) {
				CalendarUtil.changeSeason(subProject);
				List<Map<String, Object>> dataList = CalendarUtil.season(
						begin_time, end_time);
				map.put("dataList", dataList);
			} else if ("5".equals(num)) {
				CalendarUtil.changeYear(subProject);
				List<Map<String, Object>> dataList = CalendarUtil.year(
						begin_time, end_time);
				map.put("dataList", dataList);
			}
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("project", subProject);
			map.put("datas", datas);

		}
		return new Result(Result.SUCCESS, "成功", map);
	}

	/**
	 * 为项目设置实际的开始时间和结束时间
	 * 
	 * @param p
	 */
	private void setActualBeginTimeAndEndTime(SubProjectVo p, String num) {
		Map<String, Object> mapIds = new HashMap<String, Object>();
		mapIds.put("is_parent", 0);// 为1表示当前项目是根父项目
		List<Long> ids = new ArrayList<Long>();
		ids.add(p.getId());
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
		Date actual_begin_time = projectService.getActualBeginTime(mapIds);// 获取项目的实际开始时间
		p.setActual_begin_time(actual_begin_time);
		Integer total = projectService.getTotal(mapIds);// 项目下所有的任务总和
		Integer actual_finish_total = projectService
				.getActualFinishTotal(mapIds);// 项目下任务状态为待评价和已完成数量
		if (actual_finish_total == total) {
			Date actual_end_time = projectService.getActualEndTime(mapIds);
			p.setActual_end_time(actual_end_time);
			if (actual_begin_time != null) {
				long usedDate = actual_end_time.getTime()
						- actual_begin_time.getTime();
				p.setUsedDate((int) Math.round(usedDate * 1.0
						/ (1000 * 60 * 60 * 24)));
			}
		} else if (actual_begin_time != null) {
			Date now = new Date();
			long usedDate = now.getTime() - actual_begin_time.getTime();
			p.setUsedDate((int) Math.round(usedDate * 1.0
					/ (1000 * 60 * 60 * 24)));
		}
		if ("1".equals(num)) {
			CalendarUtil.changeDay(p);

		} else if ("2".equals(num)) {
			CalendarUtil.changeWeek(p);

		} else if ("3".equals(num)) {
			CalendarUtil.changeMonth(p);

		} else if ("4".equals(num)) {
			CalendarUtil.changeSeason(p);

		} else if ("5".equals(num)) {
			CalendarUtil.changeYear(p);
		}
	}

	/**
	 * 为项目设置实际的开始时间和结束时间
	 * 
	 * @param p1
	 */
	private void setActualBeginTimeAndEndTime(ProjectVo p1) {
		Map<String, Object> mapIds = new HashMap<String, Object>();
		mapIds.put("is_parent", 1);// 为1表示当前项目是根父项目
		List<Long> ids = new ArrayList<Long>();
		ids.add(p1.getId());
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
		Date actual_begin_time = projectService.getActualBeginTime(mapIds);// 获取项目的实际开始时间
		p1.setActual_begin_time(actual_begin_time);
		Integer total = projectService.getTotal(mapIds);// 项目下所有的任务总和
		Integer actual_finish_total = projectService
				.getActualFinishTotal(mapIds);// 项目下任务状态为待评价和已完成数量
		if (actual_finish_total == total) {
			Date actual_end_time = projectService.getActualEndTime(mapIds);
			p1.setActual_end_time(actual_end_time);
			p1.setActual_end_time(actual_end_time);
			if (actual_begin_time != null) {
				long usedDate = actual_end_time.getTime()
						- actual_begin_time.getTime();
				p1.setUsedDate((int) Math.round(usedDate * 1.0
						/ (1000 * 60 * 60 * 24)));
			}
		} else if (actual_begin_time != null) {
			Date now = new Date();
			long usedDate = now.getTime() - actual_begin_time.getTime();
			p1.setUsedDate((int) Math.round(usedDate * 1.0
					/ (1000 * 60 * 60 * 24)));
		}
	}

	/**
	 * 项目的列表方法
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Result list(String data, String num, HttpServletRequest request,
			HttpSession session) {
		if (flag) {
			System.out.println("ok");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 23); // 凌晨1点
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date date = calendar.getTime(); // 第一次执行定时任务的时间
			// 如果第一次执行定时任务的时间 小于当前的时间
			// 此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
			if (date.before(new Date())) {
				date = addDay(date, 1);
			}
			Timer timer = new Timer();
			TaskInfoOfProjectTask task = new TaskInfoOfProjectTask();
			// 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
			timer.schedule(task, date, PERIOD_DAY);
			flag=false;
		}
		// //
		Long l1 = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		// data="{'page':{'pageSize':'20','pageNo':'1'},'object':{'sort':'desc','field':'project_no','search':''}}";
		try {
			JSONObject param = JSON.parseObject(data);
			JSON object = (JSON) JSON.toJSON(param.get("object"));
			ProjectVo project = new ProjectVo();
			ProjectSearchVo search = JSONObject.toJavaObject(object,
					ProjectSearchVo.class);// 将data对象的object转换为项目搜索对象
			// TODO公司id需要从session里面获取
			session.setAttribute("projectCompany", search.getCompany_id());
			// Long company_id =(Long) session.getAttribute("sessionCompany");
			// search.setCompany_id(company_id);
			// search.setCompany_id(18L);
			project.setPsearch(search);
			// System.out.println(project.getPsearch().getField() + ":"
			// + project.getPsearch().getSearch());
			Map<String, String> map1 = (Map<String, String>) param.get("page");
			Page page = PageUtils.createPage(map1);
			page = projectService.page(page, project);
			List<ProjectVo> ps = (List<ProjectVo>) page.getDataList();
			for (ProjectVo projectVo : ps) {
				Map<String, String[]> m = new HashMap<String, String[]>();
				m.put("ids", new String[] { projectVo.getId() + "" });
				List<Long> modelTaskIds = projectService.getModelTaskIds(m);// 获取删除项目下的模型任务
				List<Long> animationLightTaskIds = projectService
						.getAnimationLightTaskIds(m);// 获取删除项目下的动画灯光任务
				if (modelTaskIds.size() > 0 || animationLightTaskIds.size() > 0) {
					projectVo.setHasTask(true);
				} else {
					projectVo.setHasTask(false);
				}
			}
			System.out.println("num");
			System.out.println("2".equals(num));
			if ("2".equals(num)) {
				Long l2 = System.currentTimeMillis();
				System.out.println("project");
				Integer total = 0;// 项目下所有的任务总和
				Integer total_undistributed = 0;// 项目任务未分配的数量
				Integer total_running = 0;// 项目任务进行中的数量
				Integer total_finished = 0;// 项目任务已完成的数量
				Integer model_task_total = 0;// 项目模型任务的总数量
				Integer model_task_finished_total = 0;// 项目模型任务已完成的数量
				Integer animation_task_total = 0;// 项目动画任务的总数量
				Integer animation_task_finished_total = 0;// 项目动画任务已完成的数量
				Integer light_task_total = 0;// 项目灯光任务的总数量
				Integer light_task_finished_total = 0;// 项目灯光任务已完成的总数量
				int a = 0;
				for (ProjectVo projectVo : ps) {
					System.out.println(++a);// 遍历获取的项目列表
					Map<String, Object> mapIds = new HashMap<String, Object>();
					mapIds.put("is_parent", 1);// 为1表示当前项目是根父项目
					List<Long> ids = new ArrayList<Long>();
					ids.add(projectVo.getId());
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
									&& subProjects1.get(i).getIs_parent()
											.equals("1")) {
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
												&& subProjects2.get(j)
														.getIs_parent()
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
					total_undistributed = projectService
							.getTotalUndistributed(mapIds);// 项目任务未分配的数量
					total_running = projectService.getTotalRunning(mapIds);// 项目任务进行中的数量
					System.out.println("mapIds:" + mapIds);
					total_finished = projectService.getTotalFinished(mapIds);// 项目任务已完成的数量
					model_task_finished_total = projectService
							.getModelTaskFinishedTotal(mapIds);// 项目模型任务已完成的数量
					model_task_total = projectService.getModelTaskTotal(mapIds);// 项目模型任务的总数量
					animation_task_finished_total = projectService
							.getAnimationTaskFinishedTotal(mapIds);// 项目动画任务已完成的数量
					animation_task_total = projectService
							.getanimationTaskTotal(mapIds);// 项目动画任务的总数量
					light_task_finished_total = projectService
							.getLightTaskFinishedTotal(mapIds);// 项目灯光任务已完成的总数量
					light_task_total = projectService.getLightTaskTotal(mapIds);// 项目灯光任务的总数量
					total = projectService.getTotal(mapIds);// 项目下所有的任务总和
					// 求各种数据
					projectVo.setTotal_undistributed(total_undistributed);
					projectVo.setTotal_running(total_running);
					projectVo.setTotal_finished(total_finished);
					projectVo
							.setModel_task_finished_total(model_task_finished_total);
					projectVo.setModel_task_total(model_task_total);
					projectVo
							.setAnimation_task_finished_total(animation_task_finished_total);
					projectVo.setAnimation_task_total(animation_task_total);
					projectVo
							.setLight_task_finished_total(light_task_finished_total);
					projectVo.setLight_task_total(light_task_total);
					if (total_finished != null && total_finished > 0
							&& total != null && total > 0) {
						// 计算已完成的比例
						projectVo.setFinished_rate((int) (total_finished * 1.0
								/ total * 100)
								+ "%");
					} else {
						projectVo.setFinished_rate("0%");
					}

					setProjectGrahics(projectVo, mapIds);// 设置项目统计图对象
				}
				System.out.println(System.currentTimeMillis() - l2);
			}
			// Map<String, Long> m = new HashMap<String, Long>();
			// m.put("company_id", company_id);
			// Long project_total = projectService.getProjectTotal(m);// 项目总数
			// map.put("project_total", project_total);
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", ps);
			// for (ProjectVo v : ps) {
			// System.out.println(v.getName());
			// }
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		System.out.println(System.currentTimeMillis() - l1);
		return new Result(Boolean.TRUE, "成功", map);
	}

	/**
	 * 项目的列表方法
	 * 
	 * @param data
	 * @param request
	 * @return1
	 */
	@RequestMapping("/getMoreProjectInfo")
	@ResponseBody
	public Result getMoreProjectInfo(String data, String stage, String type,
			HttpServletRequest request, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject param = JSON.parseObject(data);
			JSON object = (JSON) JSON.toJSON(param.get("object"));
			ProjectVo project = new ProjectVo();
			ProjectSearchVo search = JSONObject.toJavaObject(object,
					ProjectSearchVo.class);// 将data对象的object转换为项目搜索对象
			search.setType(type);
			project.setPsearch(search);
			System.out.println(search);
			Map<String, String> map1 = (Map<String, String>) param.get("page");
			Page page = PageUtils.createPage(map1);
			page = projectService.page1(page, project);
			List<ProjectVo> ps = (List<ProjectVo>) page.getDataList();
			List<Map<String, Object>> recAndShowProjectsInfo = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> oldRecAndShowProjectsInfo = new ArrayList<Map<String, Object>>();
			for (ProjectVo projectVo : ps) {
				Map<String, Object> mp = getSubProjectIdsOfParent(projectVo.getId());
				Integer total = projectService.getTotal(mp);
				Long run = modelTaskService.getRunTaskNums(mp);
				Integer undistributed = projectService
						.getTotalUndistributed(mp);
				Integer finished = projectService.getTotalFinished(mp);
				System.out.println("total:" + total);
				System.out.println("run:" + run);
				System.out.println("undistributed:" + undistributed);
				System.out.println("finished:" + finished);
				if ("未开始".equals(stage)) {
					if (total == undistributed) {
						if ("old".equals(type)) {
							Map<String, Object> pro = getOldProjectInfo(projectVo);
							if (pro != null) {
								oldRecAndShowProjectsInfo.add(pro);
							}
						} else {
							Map<String, Object> pro = getProjectInfo(projectVo);
							if (pro != null) {
								recAndShowProjectsInfo.add(pro);
							}
						}

					}
				} else if ("进行中".equals(stage)) {
					if (run != 0) {
						if ("old".equals(type)) {
							Map<String, Object> pro = getOldProjectInfo(projectVo);
							if (pro != null) {
								oldRecAndShowProjectsInfo.add(pro);
							}
						} else {
							Map<String, Object> pro = getProjectInfo(projectVo);
							if (pro != null) {
								recAndShowProjectsInfo.add(pro);
							}
						}
					}
				} else if ("已完成".equals(stage)) {
					if (total == finished && total != 0) {
						if ("old".equals(type)) {
							Map<String, Object> pro = getOldProjectInfo(projectVo);
							if (pro != null) {
								oldRecAndShowProjectsInfo.add(pro);
							}
						} else {
							Map<String, Object> pro = getProjectInfo(projectVo);
							if (pro != null) {
								recAndShowProjectsInfo.add(pro);
							}
						}
					}
				} else {
					if ("old".equals(type)) {
						Map<String, Object> pro = getOldProjectInfo(projectVo);
						if (pro != null) {
							oldRecAndShowProjectsInfo.add(pro);
						}
					} else {
						Map<String, Object> pro = getProjectInfo(projectVo);
						if (pro != null) {
							recAndShowProjectsInfo.add(pro);
						}
					}
				}
			}
			Search s=projectService.getSearch(search.getName());
			if(s!=null){
				Long num=s.getNum();
				s.setNum(++num);
				projectService.updateSearch(s);
			}else{
			    s=new Search();
				s.setNum(1L);
				s.setSearch(search.getName());
				projectService.addSearch(s);
			}
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			if ("old".equals(type)) {
				map.put("dataList", oldRecAndShowProjectsInfo);
			} else {
				map.put("dataList",recAndShowProjectsInfo );
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}

	/**
	 * 设置项目图表数据到项目对象中
	 * 
	 * @param p
	 * @param mapIds
	 */
	private void setProjectGrahics(ProjectVo p, Map<String, Object> mapIds) {
		System.out.println("mapIds:" + mapIds);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Graphics g = new Graphics();
		int[] free = new int[5];// 未分配
		int[] underWay = new int[5];// 进行中
		int[] finish = new int[5];// 已完成
		Date currentDate = new Date();
		Date minPublishDate = projectService.getMinPublishDate(mapIds);// 获取项目中最早的日期
		System.out.println("minPublishDate:" + minPublishDate);
		if (minPublishDate != null) {
			Integer minutes = (int) ((currentDate.getTime() - minPublishDate
					.getTime()) / (1000 * 60 * 5));// 将时间按分钟份5段
			Date endDate1 = getEndDate(minPublishDate, minutes);// 根据开始时间和时间间隔得到结束时间
			mapIds.put("beginDate", minPublishDate);
			mapIds.put("endDate", endDate1);
			System.out.println(sdf.format(minPublishDate)
					+ sdf.format(endDate1));
			Integer total_undistributed = projectService
					.getTotalUndistributed(mapIds);// 项目任务未分配的数量
			Integer total_running = projectService.getTotalRunning(mapIds);// 项目任务进行中的数量
			Integer total_finished = projectService.getTotalFinished(mapIds);// 项目任务已完成的数量
			free[0] = total_undistributed;// 未分配
			underWay[0] = total_running;// 进行中
			finish[0] = total_finished;// 已完成
			Date beginDate = endDate1;
			Date endDate2 = getEndDate(beginDate, minutes);
			System.out.println(sdf.format(beginDate) + sdf.format(endDate2));
			mapIds.put("beginDate", beginDate);
			mapIds.put("endDate", endDate2);
			Integer total_undistributed2 = projectService
					.getTotalUndistributed(mapIds);
			Integer total_running2 = projectService.getTotalRunning(mapIds);
			Integer total_finished2 = projectService.getTotalFinished(mapIds);
			free[1] = total_undistributed2;
			underWay[1] = total_running2;
			finish[1] = total_finished2;
			Date beginDate3 = endDate2;
			Date endDate3 = getEndDate(endDate2, minutes);
			System.out.println(sdf.format(beginDate3) + sdf.format(endDate3));
			mapIds.put("beginDate", beginDate3);
			mapIds.put("endDate", endDate3);
			Integer total_undistributed3 = projectService
					.getTotalUndistributed(mapIds);
			Integer total_running3 = projectService.getTotalRunning(mapIds);
			Integer total_finished3 = projectService.getTotalFinished(mapIds);
			free[2] = total_undistributed3;
			underWay[2] = total_running3;
			finish[2] = total_finished3;
			Date beginDate4 = endDate3;
			Date endDate4 = getEndDate(endDate3, minutes);
			System.out.println(sdf.format(beginDate4) + sdf.format(endDate4));
			mapIds.put("beginDate", beginDate4);
			mapIds.put("endDate", endDate4);
			Integer total_undistributed4 = projectService
					.getTotalUndistributed(mapIds);
			Integer total_running4 = projectService.getTotalRunning(mapIds);
			Integer total_finished4 = projectService.getTotalFinished(mapIds);
			free[3] = total_undistributed4;
			underWay[3] = total_running4;
			finish[3] = total_finished4;
			Date beginDate5 = endDate4;
			Date endDate5 = currentDate;
			mapIds.put("beginDate", beginDate5);
			mapIds.put("endDate", endDate5);
			System.out.println(sdf.format(beginDate5) + sdf.format(endDate5));
			Integer total_undistributed5 = projectService
					.getTotalUndistributed(mapIds);
			Integer total_running5 = projectService.getTotalRunning(mapIds);
			Integer total_finished5 = projectService.getTotalFinished(mapIds);
			free[4] = total_undistributed5;
			underWay[4] = total_running5;
			finish[4] = total_finished5;
		}
		g.setFree(free);
		g.setUnderWay(underWay);
		g.setFinish(finish);
		System.out.println("g:" + g);
		p.setGraphics(g);
	}

	/**
	 * 根据开始日期和时间间隔得到结束日期
	 * 
	 * @param minPublishDate
	 * @param minutes
	 * @return
	 */
	private Date getEndDate(Date minPublishDate, Integer minutes) {
		Calendar c = Calendar.getInstance();
		c.setTime(minPublishDate);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	/**
	 * 根据输入的项目名称获取到项目编号，即项目名称的汉字拼音首字母大写组合 重复的项目代号，重复的在后面加上当前表中的最大id
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/getProjectNameChar")
	@ResponseBody
	public Result getProjectNameChar(String name) {
		String projectNo = PinyinUtil.getHeadCharSpell(name);
		Project p = projectService.getProjectByProjectNo(projectNo);
		if (p != null) {
			String maxId = projectService.getMaxId() + "";
			projectNo += maxId;
		}
		return new Result(Result.SUCCESS, "成功", projectNo.toUpperCase());
	}

	@RequestMapping("/concelImage")
	@ResponseBody
	public Result concelImage(Long project_id, String type) {
		ProjectVo p = projectService.getProjectById(project_id);
		if ("slider".equals(type)) {
			System.out.println("slider:" + p.getSlider_img());
			OSSFilesUtil.deleteFile(p.getSlider_img());
			p.setSlider_img(null);
		} else if ("show".equals(type)) {
			System.out.println("show:" + p.getShow_img());
			OSSFilesUtil.deleteFile(p.getShow_img());
			p.setShow_img(null);
		} else if ("recommend".equals(type)) {
			System.out.println("recommend:" + p.getRecommend_img());
			OSSFilesUtil.deleteFile(p.getRecommend_img());
			p.setRecommend_img(null);
		}
		p.setProject_status_id(p.getProjectStatus().getId());
		p.setProject_type_id(p.getProjectType().getId());
		projectDao.update(p);
		return new Result(Result.SUCCESS, "成功", null);
	}

	@RequestMapping("/insertImage")
	@ResponseBody
	public Result insertImage(String path, Long project_id, String type,
			HttpSession session) {
		Long company_id = (Long) session.getAttribute("projectCompany");
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		if (path.length() > 100) { // 保存用户头像
			Project p = new Project();
			p.setId(project_id);
			byte[] fileByte = FileUtils.base64ToByte(path.toString());
			String savePath = ConstantUtil.USER_PATH + uuid
					+ "/images/projectimg/project" + project_id + "/"
					+ System.currentTimeMillis() + ".jpg";
			OSSFilesUtil.uploadDocumentByString(savePath, fileByte);
			System.out.println(type);
			if ("slider".equals(type)) {
				p.setSlider_img(savePath);
			} else if ("show".equals(type)) {
				p.setShow_img(savePath);
			} else if ("recommend".equals(type)) {
				p.setRecommend_img(savePath);
			}
			projectService.updateIgnoreNull(p);
		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	/**
	 * 创建项目
	 * 
	 * @param project项目对象
	 * @param directorIds导演
	 * @param frameRateContent帧数率
	 * @param resolutionContent分辨率
	 * @param principalIds负责人
	 * @param plugins插件
	 * @param screenwriterIds编剧
	 * @param softwares软件
	 * @param beginTime开始日期
	 * @param endTime结束日期
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/create")
	@ResponseBody
	public Result create(ProjectVo project, String directorIds,
			String frameRateContent, String resolutionContent,
			String principalIds, String plugins, String screenwriterIds,
			String softwares, String labeltagIds, String beginTime,
			String endTime, HttpSession session, StringBuffer cover_path)
			throws Exception {
		Long company_id = (Long) session.getAttribute("projectCompany");
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Project p = projectService.getProjectByProjectNo(project
				.getProject_no());
		if (p != null) {
			return new Result(Result.FAILURE, "項目编号已存在,请重新输入", null);
		}

		String errorInfo = "";
		CompanyFilesVo parentvo = new CompanyFilesVo();
		String folder_path = ConstantUtil.USER_PATH + uuid + "/data/"
				+ project.getProject_folder();
		try {
			if (cover_path.length() > 100) { // 保存用户头像
				byte[] fileByte = FileUtils.base64ToByte(cover_path.toString());
				String savePath = ConstantUtil.USER_PATH + uuid
						+ "/images/projectimg/project/"
						+ System.currentTimeMillis() + ".jpg";
				OSSFilesUtil.uploadDocumentByString(savePath, fileByte);
				project.setCover_path(savePath);
			} else {
				project.setCover_path("cgwas/images/sc/page/companyDefHeard.jpg");
			}
			parentvo.setFile_url(project.getProject_folder().substring(0,project.getProject_folder().lastIndexOf("/")));
			parentvo = companyFilesService.selectForObject(parentvo);
			if (parentvo == null) {
				errorInfo = "创建失败，父文件夹不存在！";
				return new Result(Result.FAILURE, errorInfo, null);
			}
			//保存父文件夹id
			project.setParentFileId(parentvo.getId());
			//保存项目文件夹绝对路径
			project.setFolder_path(folder_path);
			/**
			 * 查询选择的文件夹
			 */
			CompanyFilesVo vo = new CompanyFilesVo();
			vo.setFile_url(project.getProject_folder());
			vo=companyFilesService.selectForObject(vo);
			if(vo!=null){
				if(vo.getFor_id()!=null){
					errorInfo="此文件夹已被指定，请重新选择！";
					return new Result(Result.FAILURE, errorInfo, null);
				}
			}
			
			projectService.create(project, directorIds, principalIds, plugins,
					screenwriterIds, softwares, labeltagIds, beginTime,
					endTime, session);
		} catch (RuntimeException e) {
			errorInfo = e.getMessage();
		}
		if (errorInfo == null || errorInfo.trim().length() == 0) {
			/**
			 * 所有操作成功，最后执行OSS
			 */
			if (!OSSFilesUtil.isFile(folder_path+"/")) {
				OSSFilesUtil.addFile(folder_path);
			}
			return new Result(Result.SUCCESS, "添加成功", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}
	}

	/**
	 * 修改项目
	 * 
	 * @param project项目对象
	 * @param directorIds导演
	 * @param frameRateContent帧数率
	 * @param resolutionContent分辨率
	 * @param principalIds负责人
	 * @param plugins插件
	 * @param screenwriterIds编剧
	 * @param softwares软件
	 * @param beginTime开始日期
	 * @param endTime结束日期
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public @ResponseBody
	Result update(ProjectVo project, String directorIds,
			String screenwriterIds, String frameRateContent,
			String resolutionContent, String principalIds, String plugins,
			String softwares, String labeltagIds, String beginTime,
			String endTime, HttpSession session, StringBuffer cover_path)
			throws Exception {
		String errorInfo = "";
		ProjectVo p = projectService.getProjectById(project.getId());
		Project p1 = projectService.getProjectByProjectNo(project
				.getProject_no());
		if (p1 != null && p1.getId() != project.getId()) {
			return new Result(Result.FAILURE, "項目编号已存在,请重新输入", null);
		}
		if (p != null && p.getProject_folder() != null
				&& !p.getProject_folder().equals(project.getProject_folder())) {
			if (this.hasTaskOrNot(project.getId())) {
				errorInfo = "该项目存在任务文件夹，无法更换路径";
				return new Result(Result.FAILURE, errorInfo, null);
			}
		}
		Long company_id = (Long) session.getAttribute("projectCompany");
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		String folder_path = ConstantUtil.USER_PATH + uuid + "/data/"
				+ project.getProject_folder();
		if (!OSSFilesUtil.isFile(folder_path+"/")) {
			errorInfo = "无效的文件夹路径，请重新选择";
			return new Result(Result.FAILURE, errorInfo, null);
		}
		if (cover_path != null && cover_path.length() > 300) { // 保存用户头像
			byte[] fileByte = FileUtils.base64ToByte(cover_path.toString());
			String path = ConstantUtil.USER_PATH + uuid
					+ "/images/projectimg/project/";
			String savePath = path + System.currentTimeMillis() + ".jpg";
			if (OSSFilesUtil.isFile(path + p.getCover_path())) {
				OSSFilesUtil.deleteFile(path + p.getCover_path());
			}
			OSSFilesUtil.uploadDocumentByString(savePath, fileByte);
			project.setCover_path(savePath);
			;
		}
		CompanyFilesVo vo = new CompanyFilesVo();
		try {
			/**
			 * 判断当前选中文件夹是否更换
			 */
			if (p != null
					&& p.getProject_folder() != null
					&& !p.getProject_folder().equals(
							project.getProject_folder())) {
				errorInfo = "无效的文件夹路径，请重新选择";
				return new Result(Result.FAILURE, errorInfo, null);
			}
			if (project.getCompanyFiles_id() != null) {
				vo = new CompanyFilesVo();
				vo.setId(project.getCompanyFiles_id());
				vo = companyFilesService.selectForObject(vo);
				if (vo != null) {
					if (vo.getFor_id() != null  && !p.getProject_folder().equals(project.getProject_folder())) {
						errorInfo = "此文件夹已被指定，请重新选择！";
						return new Result(Result.FAILURE, errorInfo, null);
					}
				}
			}
			projectService.update(project, directorIds, screenwriterIds,
					principalIds, plugins, softwares, labeltagIds, beginTime,
					endTime, session);
		} catch (RuntimeException e) {
			e.printStackTrace();
			errorInfo = e.getMessage();
		}
		if (errorInfo == null || errorInfo.trim().length() == 0) {
			if (p != null
					&& p.getProject_folder() != null
					&& !p.getProject_folder().equals(
							project.getProject_folder())) {
				/**
				 * 原文件夾項目文件夾能力丟失
				 */
				CompanyFilesVo vo1 = new CompanyFilesVo();
				vo1.setFor_id(project.getId());
				vo1.setFile_type(ConstantUtil.ZC_PROJECT);
				vo1 = companyFilesService.selectForObject(vo1);
				vo1.setFor_id(null);
				vo1.setFile_type("");
				companyFilesService.update(vo1);
				/**
				 * 选中的文件夹更新为项目文件夹
				 */
				vo.setFor_id(project.getId());
				vo.setFile_type(ConstantUtil.ZC_PROJECT);
				companyFilesService.updateIgnoreNull(vo);
			}
			return new Result(Result.SUCCESS, "修改成功", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}
	}

	/**
	 * 批量删除项目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(String ids) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		List<Long> list = new ArrayList<Long>();
		map.put("ids", ids.split(","));
		for (String id : ids.split(",")) {
			list.add(Long.parseLong(id));
		}
		List<Long> subProjectIds = projectService.getSubProjectIds(map);// 获取删除项目下的直接子项目
		List<Long> modelTaskIds = projectService.getModelTaskIds(map);// 获取删除项目下的模型任务
		List<Long> animationLightTaskIds = projectService
				.getAnimationLightTaskIds(map);// 获取删除项目下的动画灯光任务
		if (subProjectIds.size() > 0 || modelTaskIds.size() > 0
				|| animationLightTaskIds.size() > 0) {
			// 如果子项目数，模型任务数，动画任务数有一个大于0，则提示不能删除
			return new Result(Result.FAILURE, "该项目有级联任务或子项目信息,不能删除！", null);
		}
		// 删除项目对应的软件，插件，导演，负责人，编剧等级联数据。
		projectService.deleteAll(map);
		return new Result("删除成功!");
	}

	/**
	 * 获取项目类型数据
	 * 
	 * @return
	 */
	@RequestMapping("/getProjectType")
	@ResponseBody
	public Result getProjectType() {
		return new Result(Result.SUCCESS, "成功",
				projectTypeService.selectForList(new ProjectTypeVo()));
	}

	/**
	 * 获取项目帧数率数据
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/getProjectFrameRate")
	@ResponseBody
	public Result getProjectFrameRate(FrameRateCompanyVo vo) {
		return new Result(Result.SUCCESS, "成功",
				frameRateService.selectForList(vo));
	}

	/**
	 * 获取项目分辨率数据
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/getProjectResolution")
	@ResponseBody
	public Result getProjectResolution(ResolutionCompanyVo vo) {
		return new Result(Result.SUCCESS, "成功",
				resolutionService.selectForList(vo));
	}

	/**
	 * 获取项目软件
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/getProjectSoftware")
	@ResponseBody
	public Result getProjectSoftware(CompanySoftwareVo vo, HttpSession session) {
		// TODOcompany_id
		Long company_id = (Long) session.getAttribute("projectCompany");
		vo.setCompany_id(company_id);
		return new Result(Result.SUCCESS, "成功",
				companySoftwareService.selectForList(vo));
	}

	/**
	 * 获取项目插件
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/getProjectPlugin")
	@ResponseBody
	public Result getProjectPlugin(CompanyPluginVo vo, HttpSession session) {
		Long company_id = (Long) session.getAttribute("projectCompany");
		vo.setCompany_id(company_id);
		return new Result(Result.SUCCESS, "成功",
				companyPluginService.selectForList(vo));
	}

	/**
	 * 获取项目状态
	 * 
	 * @return
	 */
	@RequestMapping("/getProjectStatus")
	@ResponseBody
	public Result getProjectStatus() {
		return new Result(Result.SUCCESS, "成功",
				projectStatusService.selectForList(new ProjectStatusVo()));
	}

	/**
	 * 获取项目搜索信息
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/getProjectSearch")
	@ResponseBody
	public Result getProjectSearch(ProjectSearchVo vo) {
		return new Result(Result.SUCCESS, "成功",
				projectSearchService.selectForList(vo));
	}

	/**
	 * 根据项目id获取项目信息，供修改使用
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getProjectById")
	@ResponseBody
	public Result getProjectById(Long id) {
		ProjectVo v = projectService.getProjectById(id);
		return new Result(Result.SUCCESS, "", v);
	}

	/**
	 * 批量导入项目信息
	 * 
	 * @param filePath
	 * @param company_id
	 * @param errorInfo
	 * @return
	 * @throws Exception
	 */
	public Result importProject(String filePath, Long company_id,
			String errorInfo, HttpSession session) throws Exception {
		try {
			errorInfo += projectService.importProject(filePath, company_id,
					errorInfo, session);
		} catch (RuntimeException e) {
			errorInfo = e.getMessage();
		}
		if (errorInfo == null || errorInfo.trim().length() == 0) {
			return new Result(Result.SUCCESS, "成功导入", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}
	}

	/**
	 * 批量导入请求
	 * 
	 * @param path
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importProject")
	@ResponseBody
	public Result getProjectUrl(String path, HttpServletRequest request,
			HttpSession session) throws Exception {
		path = session.getServletContext().getRealPath("") + path;
		Long company_id = (Long) session.getAttribute("projectCompany");
		return importProject(path, company_id, "", session);
	}

	private boolean hasTaskOrNot(Long id) {
		Map<String, Object> map = getSubProjectIdsOfParent(id);
		Long modelTaskTotal = modelTaskService.getTaskTotal(map);// 获取该项目下的所有建模任务总数
		map.put("class_id", 2);
		Long animationTaskTotal = animationLightTaskService.getTaskTotal(map);// 获取该项目下的所有动画任务总数
		map.put("class_id", 3);
		Long lightTaskTotal = animationLightTaskService.getTaskTotal(map);// 获取该项目下的所有灯光任务总数
		if (modelTaskTotal > 0 || animationTaskTotal > 0 || lightTaskTotal > 0) {
			return true;
		}
		return false;
	}

	private Map<String, Object> getSubProjectIdsOfParent(Long id) {
		Map<String, Object> mapIds = new HashMap<String, Object>();
		List<SubProject> subProjects = new ArrayList<SubProject>();
		mapIds.put("is_parent", 1);// 为1表示当前项目是根父项目
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		mapIds.put("ids", ids);
		List<SubProject> subProjects1 = projectService
				.getParentSubProjects(mapIds);// 获取根项目的直接子项目列表
		if (subProjects1.size() > 0) {// 当subProjects1.size()>0时，说明根父项目有子项目
			List<Long> ids1 = new ArrayList<Long>();
			subProjects.addAll(subProjects1);
			mapIds.put("subProjects", subProjects);
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
						subProjects.addAll(subProjects2);
						mapIds.put("subProjects", subProjects);
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
									subProjects.addAll(subProjects3);
									mapIds.put("subProjects", subProjects);
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

	@RequestMapping("/getSubProjectsOfProject")
	@ResponseBody
	private Result getSubProjectsOfProject(Long project_id) {
		Map<String, Object> map = getSubProjectIdsOfParent(project_id);
		List<SubProject> subProjects = (List<SubProject>) map
				.get("subProjects");
		if (subProjects != null) {
			Iterator<SubProject> its = subProjects.iterator();
			System.out.println(subProjects.size());
			while (its.hasNext()) {
				SubProject s = its.next();
				if (s.getIs_parent() != null && s.getIs_parent().equals("1")) {
					its.remove();
				}
			}
			return new Result(Result.SUCCESS, "成功", subProjects);
		}
		return new Result(Result.SUCCESS, "成功", null);
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

	@RequestMapping("/getLabelTags")
	@ResponseBody
	public Result getLabelTags() {
		List<LabelTag> labelTags = projectService.getAllLabelTag();
		return new Result(Result.SUCCESS, "成功", labelTags);
	}

	class TaskInfoOfProjectTask extends TimerTask {
		List<Long> parentProjectIds = null;
		List<Long> subProjectIds = null;

		@Override
		public void run() {
			System.out.println("task...");
			parentProjectIds = projectService.getParentIds();
			subProjectIds = projectService.getSubIds();
			if (parentProjectIds != null && parentProjectIds.size() > 0) {
				for (Long project_id : parentProjectIds) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("is_parent", "1");
					List<Long> ids = new ArrayList<Long>();
					ids.add(project_id);
					map.put("ids", ids);
					Long totalNums = modelTaskService
							.getTaskTotalOfProject(map);
					Long runnNums = modelTaskService
							.getRunningNumsOfProject(map);
					Long checkNuns = modelTaskService
							.getCheckNumsOfProject(map);
					Long finishedNums = modelTaskService
							.getFinishedNumsOfProject(map);
					Long abandonNums = modelTaskService
							.getabandonNumsOfProject(map);
					TaskRecord t = new TaskRecord();
					t.setTotalNums(totalNums);
					t.setRunnNums(runnNums);
					t.setCheckNuns(checkNuns);
					t.setFinishedNums(finishedNums);
					t.setAbandonNums(abandonNums);
					t.setIs_parent_project("1");
					t.setProject_id(project_id);
					modelTaskService.addTaskRecord(t);
				}
			}
			if (subProjectIds != null && subProjectIds.size() > 0) {
				for (Long sub_project_id : subProjectIds) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("is_parent", "0");
					List<Long> ids = new ArrayList<Long>();
					ids.add(sub_project_id);
					map.put("ids", ids);
					Long totalNums = modelTaskService
							.getTaskTotalOfProject(map);
					Long runnNums = modelTaskService
							.getRunningNumsOfProject(map);
					Long checkNuns = modelTaskService
							.getCheckNumsOfProject(map);
					Long finishedNums = modelTaskService
							.getFinishedNumsOfProject(map);
					Long abandonNums = modelTaskService
							.getabandonNumsOfProject(map);
					TaskRecord t = new TaskRecord();
					t.setTotalNums(totalNums);
					t.setRunnNums(runnNums);
					t.setCheckNuns(checkNuns);
					t.setFinishedNums(finishedNums);
					t.setAbandonNums(abandonNums);
					t.setIs_parent_project("0");
					t.setProject_id(sub_project_id);
					modelTaskService.addTaskRecord(t);
				}
			}
		}
	}

	private Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
}
