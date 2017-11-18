package com.cgwas.animationlighttask.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.animationlighttask.entity.AnimationLightTask;
import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.FileUtils;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.common.utils.PinyinUtil;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.project.entity.Project;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.projectSearch.entity.ProjectSearch;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.entity.SubProjectVo;
import com.cgwas.subproject.service.api.ISubProjectService;
import com.cgwas.user.entity.User;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userInfo.entity.UserInfo;

/**
 * Author yangjun
 */
@Controller
@RequestMapping("cgwas/animationlighttaskAction")
public class AnimationLightTaskAction {
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private ISubProjectService subProjectService;// 子项目业务对象
	@Autowired
	private IProjectService projectService;// 项目业务对象
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private ICompanyFilesService companyFilesService;

	@RequestMapping("/index")
	public String index() {
		return "/dsoul/animationLightTask/animationLightTask_index.jsp";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Result list(String data, HttpServletRequest reques,
			HttpSession session) {
		Long company_id = (Long) session.getAttribute("sessionCompany");
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
//		 data =
//		 "{'page':{'pageSize':'50','pageNo':'1'},'object':{'sort':'desc','field':'','search':'','clazz_id':'2'}}";
		try {
			JSONObject param = JSON.parseObject(data);
			JSON object = (JSON) JSON.toJSON(param.get("object"));
			AnimationLightTaskVo task = new AnimationLightTaskVo();
			ProjectSearchVo search = JSONObject.toJavaObject(object,
					ProjectSearchVo.class);
			Long project_id = search.getProject_id();
			Project p = null;
			String frame_rate = null;
			if ("1".equals(search.getFlag())) {
				p = projectService.getProjectById(project_id);
				frame_rate = p.getFrame_rate();
			} else if (search.getFlag() != null) {
				SubProject s = subProjectService.getSubProjectById(project_id);
				project_id = s.getProject_id();
				if (project_id == null) {
					project_id = s.getSub_parent_project_id();
					s = subProjectService.getSubProjectById(project_id);
					project_id = s.getProject_id();
					if (project_id == null) {
						project_id = s.getSub_parent_project_id();
						s = subProjectService.getSubProjectById(project_id);
						project_id = s.getProject_id();
						p = projectService.getProjectById(project_id);
						frame_rate = p.getFrame_rate();
					} else {
						p = projectService.getProjectById(project_id);
						frame_rate = p.getFrame_rate();
					}
				} else {
					p = projectService.getProjectById(project_id);
					frame_rate = p.getFrame_rate();
				}
			}

			Long role_id = modelTaskService.getRoleIdByUserId(user_id);
			List<Long> parentIds = new ArrayList<Long>();
			List<Long> subIds = new ArrayList<Long>();
			if (project_id == null) {
				search.setUser_id(user_id);
			}
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
				// companyIds.add(1L);
				// companyIds.clear();
				System.out.println("companyIds:" + companyIds);
				map.put("companyIds", companyIds);
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
					System.out.println("----------------------7ok--------------------");
				}
				
			}
			task.setPsearch(search);
			task.setMap(map);
			Map<String, String> map1 = (Map<String, String>) param.get("page");
			Page page = PageUtils.createPage(map1);
			page = animationLightTaskService.page(page, task);
			List<AnimationLightTaskVo> mts = (List<AnimationLightTaskVo>) page
					.getDataList();
			System.out.println(frame_rate);
			List<Long> ids=new ArrayList<Long>();
			for (AnimationLightTaskVo m : mts) {
				Map<String, Object> mm = new HashMap<String, Object>();
				mm.put("task_id", m.getId());
				if (m.getClass_id().equals(2L)) {
					mm.put("task_type", "动画");
				} else if (m.getClass_id().equals(3L)) {
					mm.put("task_type", "灯光");
				}
				mm.put("user_id", user_id);
				mm.put("project_id", m.getProject_id());
				mm.put("is_parent_project", m.getIs_parent_poject());
				mm.put("task_id", m.getId());
				Long isCheckerOfCurrentTask = null;
				if("审核中".equals(m.getStage())){
					isCheckerOfCurrentTask= modelTaskService
						.getIsCheckerOrNot(mm);
					if(isCheckerOfCurrentTask==null){
						ids.add(m.getId());
					}
				}
				if(role_id==2){
					m.setObserver("company");
				}else{
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
//				Map<String, Object> mp = new HashMap<String, Object>();
//				mp.put("task_id", m.getId());
//				if (m.getStage()!=null&&m.getStage().equals("进行中")
//						&& !m.getTask_status().equals("收回中")
//						&& m.getEnd_time().before(new Date())) {
//					ModelTaskVo m1 = new ModelTaskVo();
//					m1.setId(m.getId());
//					m1.setTask_status("收回中");
//					m.setTask_status("收回中");
//					animationLightTaskService.updateAnimationLightTask(m);
//				}
				mm.put("user_id", user_id);
				if (m.getStage()!=null&&m.getStage().equals("发布中")) {
					List<UserInfo> userInfos = modelTaskService
							.getUserInfos(mm);
					Long id = modelTaskService.getIsDrawOfMe(mm);
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
				Double time = Double
						.parseDouble(m.getTime());
				if (frame_rate != null) {
					Double frameRate = Double.parseDouble(frame_rate);
					double a = 1.0 * time / frameRate;
					double b = (double) Math.round(a * 100) / 100;
					m.setSecond(b);
				}
			}
			if(ids.size()>0){
				Iterator<AnimationLightTaskVo> animationLightTasks=mts.iterator();
				while(animationLightTasks.hasNext()){
					AnimationLightTaskVo v=animationLightTasks.next();
					if(ids.contains(v.getId())){
						animationLightTasks.remove();
					}
				}
			}
			for (AnimationLightTaskVo m : mts) {
				if (m.getMaker() != null) {
					System.out.println("id:" + m.getId() + ",stage:" + m.getStage()
							+ ",task_status:" + m.getTask_status() + ",user_id:"
							+ m.getMaker().getUser_id() + ",size:" + mts.size()
							+ ",project_id:" + m.getProject_id()
							+ ",is_parent_project:" + m.getIs_parent_poject()
							+ ",observer:" + m.getObserver());
				} else {
					System.out.println("id:" + m.getId() + ",stage:" + m.getStage()
							+ ",task_status:" + m.getTask_status() + ",user_id:"
							+ m.getUser_id() + ",size:" + mts.size()
							+ ",project_id:" + m.getProject_id()
							+ ",is_parent_project:" + m.getIs_parent_poject()
							+ ",observer:" + m.getObserver());
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
		return new Result(Boolean.TRUE, "成功", map);
	}

	@RequestMapping("/getAnimationLightTaskSearch")
	@ResponseBody
	public Result getAnimationLightTaskSearch(ProjectSearchVo vo) {
		List<ProjectSearch> search = animationLightTaskService
				.getAnimationLightTaskSearch();
		return new Result(Result.SUCCESS, "成功", search);
	}

	/**
	 * @param animationLightTask
	 * @param beginTime
	 * @param endTime
	 * @param softwares
	 * @return
	 */

	@RequestMapping("/create")
	@ResponseBody
	Result create(AnimationLightTaskVo animationLightTask, String beginTime,
			String endTime, String softwares, String is_parent_project,
			HttpSession session) {
		Long company_id = (Long) session.getAttribute("projectCompany");
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		System.out.println("publish_type_id:"
				+ animationLightTask.getPublish_type_id() + ",user_id:"
				+ animationLightTask.getUser_id());
		Long task_id = animationLightTaskService.getTaskId(animationLightTask);
		if (task_id != null) {
			return new Result(Result.FAILURE, "该任务编号已存在，请更换编号", null);
		}

		List<Long> ids = new ArrayList<Long>();
		ids.add(animationLightTask.getProject_id());
		Map<String, Object> mapIds = new HashMap<String, Object>();

		String errorInfo = "";
		mapIds.put("ids", ids);
		/**
		 * 用来判断父项目是子项目还是根项目
		 */
		boolean flag = true;
		List<SubProject> subProjects = null;
		if (is_parent_project.equals("1")) {
			flag = false;
			subProjects = projectService.getParentSubProjects(mapIds);// 查询子项目数据表中父项目是根父项目的子项目列表
		} else {
			subProjects = projectService.getSubProjects(mapIds);// 查询子项目的子项目
		}
		if (subProjects != null && subProjects.size() > 0) {
			return new Result(Result.FAILURE, "该项目下已有子项目了，不能创建任务", null);
		}
		String taskType = "";
		String imgurl = "";
		if (animationLightTask.getClass_id() == 2) {
			taskType = "Ani";
			imgurl = "a/" + System.currentTimeMillis() + ".jpg";
		} else if (animationLightTask.getClass_id() == 3) {
			taskType = "Light";
			imgurl = "l/" + System.currentTimeMillis() + ".jpg";
		}
		animationLightTask.setCompany_id(company_id);
		if (animationLightTask.getCart() != null
				&& animationLightTask.getCart().length() > 100) { // 保存用户头像
			byte[] fileByte = FileUtils.base64ToByte(animationLightTask
					.getCart());
			String savePath = ConstantUtil.USER_PATH + uuid
					+ "/images/projectimg/task/" + imgurl;
			OSSFilesUtil.uploadDocumentByString(savePath, fileByte);
			animationLightTask.setCart(savePath);
		} else {
			animationLightTask
					.setCart("cgwas/images/sc/page/companyDefHeard.jpg");
		}

		CompanyFilesVo parentvo = new CompanyFilesVo();
		parentvo.setFor_id(animationLightTask.getProject_id());
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
		String folder_path = path + animationLightTask.getCommit_path();
		CompanyFilesVo vo = new CompanyFilesVo();
		/**
		 * 判断是文件夹是否存在
		 */
		if (!OSSFilesUtil.isFile(folder_path + "/")) {
			/**
			 * 默认task文件夹
			 */
			String modelUrl = animationLightTask.getCommit_path().substring(0,
					animationLightTask.getCommit_path().lastIndexOf("/"));
			String modelFileName = modelUrl
					.substring(modelUrl.lastIndexOf("/") + 1);
			String taskUrl = modelUrl.substring(0, modelUrl.lastIndexOf("/"));
			String taskFileName = taskUrl
					.substring(taskUrl.lastIndexOf("/") + 1);
			if (!taskFileName.equals("task") || !modelFileName.equals(taskType)) {
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
			CompanyFilesVo modelvo = new CompanyFilesVo();
			modelvo.setFile_url(modelUrl);
			modelvo = companyFilesService.selectForObject(modelvo);
			if (modelvo == null) {
				modelvo = new CompanyFilesVo();
				modelvo.setCompany_id(company_id);
				modelvo.setIs_file(ConstantUtil.IS_FILE);
				modelvo.setParent_id(taskvo.getId());
				modelvo.setCreator_id(user_id);
				modelvo.setFile_name(modelFileName);
				modelvo.setFile_url(modelUrl);
				OSSFilesUtil.addFile(path + modelUrl);
				companyFilesService.save(modelvo);
			}
			OSSFilesUtil.addFile(folder_path);
			vo.setFile_type(ConstantUtil.ZC_TASK);
			vo.setTask_type(ConstantUtil.ANIMATION_LIGHT_TASK);
			vo.setCompany_id(company_id);
			vo.setIs_file(ConstantUtil.IS_FILE);
			vo.setParent_id(modelvo.getId());
			vo.setCreator_id(user_id);
			vo.setFile_url(animationLightTask.getCommit_path());
			vo.setFile_name(animationLightTask.getCommit_path().substring(
					animationLightTask.getCommit_path().lastIndexOf("/") + 1,
					animationLightTask.getCommit_path().length()));
			animationLightTask.setCompanyFiles_id(0l);
		} else {
			if (animationLightTask.getCompanyFiles_id() == null
					|| animationLightTask.getCompanyFiles_id() == 0) {
				errorInfo = "不要故意整系统君，谢谢!";
				return new Result(Result.FAILURE, errorInfo, null);
			}
			/**
			 * 查询选择的文件夹，并更新为任务文件夹
			 */
			vo.setId(animationLightTask.getCompanyFiles_id());
			vo = companyFilesService.selectForObject(vo);
			if (vo != null) {
				if (vo.getFor_id() != null) {
					errorInfo = "此文件夹已被指定，请重新选择!";
					return new Result(Result.FAILURE, errorInfo, null);
				}
				vo.setFile_type(ConstantUtil.ZC_TASK);
				vo.setTask_type(ConstantUtil.ANIMATION_LIGHT_TASK);
			} else {
				errorInfo = "无效的文件夹路径，请重新选择!";
				return new Result(Result.FAILURE, errorInfo, null);
			}
		}
		return animationLightTaskService.create(animationLightTask, beginTime,
				endTime, softwares, is_parent_project, session, vo);
	}
public static void main(String[] args) {
	Long a=156L;
	Long b=156L;
	System.out.println(a.equals(b));
}
	@RequestMapping("/update")
	@ResponseBody
	public Result update(AnimationLightTaskVo animationLightTask,
			String beginTime, String endTime, String softwares,
			HttpSession session) {
		System.out.println("publish_type_id:"
				+ animationLightTask.getPublish_type_id() + ",user_id:"
				+ animationLightTask.getUser_id());
		// TODO 判断当前任务镜头号是否重复 ，否则无法更新
		System.out.println(animationLightTask.getProject_id());
		System.out.println(animationLightTask.getIs_parent_poject());
		System.out.println(animationLightTask.getPattern_number());
		System.out.println(animationLightTask.getClass_id());
		Long task_id = animationLightTaskService.getTaskId(animationLightTask);
		System.out.println("task_id" + task_id);
		System.out.println("id:" + animationLightTask.getId());
		System.out.println(task_id==animationLightTask.getId());
		if (task_id != null && !task_id .equals(animationLightTask.getId())){
			return new Result(Result.FAILURE, "该任务编号已存在，请更换编号", null);
		}
		Long company_id = (Long) session.getAttribute("projectCompany");
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		animationLightTask.setCompany_id(company_id);
		AnimationLightTaskVo animationLightTask1 = new AnimationLightTaskVo();
		animationLightTask1.setId(animationLightTask.getId());
		animationLightTask1 = animationLightTaskService
				.getAnimationLightTaskById(animationLightTask1);
		String errorInfo = "";
		if (animationLightTask.getCart() != null
				&& animationLightTask.getCart().length() > 300) { // 保存用户头像
			byte[] fileByte = FileUtils.base64ToByte(animationLightTask
					.getCart());
			String imgurl = "";
			if (animationLightTask.getClass_id() == 2) {
				imgurl = "a/" + System.currentTimeMillis() + ".jpg";
			} else if (animationLightTask.getClass_id() == 3) {
				imgurl = "l/" + System.currentTimeMillis() + ".jpg";
			}
			String path = ConstantUtil.USER_PATH + uuid
					+ "/images/projectimg/task/";
			String savePath = path + imgurl;
			if (OSSFilesUtil.isFile(path + animationLightTask1.getCart())) {
				OSSFilesUtil.deleteFile(path + animationLightTask1.getCart());
			}
			OSSFilesUtil.uploadDocumentByString(savePath, fileByte);
			animationLightTask.setCart(savePath);
		}
		String folder_path = ConstantUtil.USER_PATH + uuid + "/data/"
				+ animationLightTask.getCommit_path();
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
			if (animationLightTask.getCompanyFiles_id() != null
					&& !animationLightTask1.getCommit_path().equals(
							animationLightTask.getCommit_path())) {
				/**
				 * 查询选择的文件夹，并更新为项目文件夹
				 */
				vo = new CompanyFilesVo();
				vo.setId(animationLightTask.getCompanyFiles_id());
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
						vo1.setFor_id(animationLightTask.getId());
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
						vo.setFor_id(animationLightTask.getId());
						vo.setFile_type(ConstantUtil.ZC_TASK);
						vo.setTask_type(ConstantUtil.ANIMATION_LIGHT_TASK);
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

		return animationLightTaskService.update(animationLightTask, beginTime,
				endTime, softwares);
	}

	@RequestMapping("/delete")
	public @ResponseBody
	Result delete(String ids) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("ids", ids.split(","));
		List<AnimationLightTaskVo> anis = animationLightTaskService
				.getTasksByIds(map);
		for (AnimationLightTaskVo a : anis) {
			companyFilesService.deleteRelationByFileUrl(a.getCommit_path());
			if (a != null && !a.getCart().endsWith("sc/page/companyDefHeard.jpg")) {
				OSSFilesUtil.deleteFile(a.getCart());
			}
		}
		animationLightTaskService.deleteAll(map);
		return new Result("删除成功!");
	}

	@RequestMapping("/getAnimationLightTaskById")
	@ResponseBody
	public Result getAnimationLightTaskById(AnimationLightTaskVo m) {
		AnimationLightTaskVo task = animationLightTaskService
				.getAnimationLightTaskById(m);
		return new Result(Result.SUCCESS, "成功", task);
	}

	@RequestMapping("/importAnimationLightTask")
	@ResponseBody
	public Result importAnimationLightTask(String filePath, Long class_id,
			Long project_id, String errorInfo, String is_parent_project,
			HttpSession session) throws Exception {
		// DOTO 判断项目是否是父项目任务
		errorInfo = "";

		filePath = session.getServletContext().getRealPath("/") + filePath;
		try {
			Long company_id = (Long) session.getAttribute("sessionCompany");
			errorInfo += animationLightTaskService.importAnimationLightTask(
					filePath, company_id, class_id, project_id,
					is_parent_project, errorInfo, session);
		} catch (RuntimeException e) {
			errorInfo += e.getMessage();
		}
		if (errorInfo.trim().length() == 0) {
			return new Result(Result.SUCCESS, "成功", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}
	}

	@RequestMapping("/getDetails")
	@ResponseBody
	public Result getDetails(Long task_id,  HttpSession session) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
//		Long user_id=9L;
		Long role_id = modelTaskService.getRoleIdByUserId(user_id);
		System.out.println("role_id:" + role_id);
		AnimationLightTaskVo task = animationLightTaskService
				.getDetails(task_id);
		String task_type="";
		if(task.getClazz().getId()==2){
			task_type="动画";
		}else{
			task_type="灯光";
		}
		session.setAttribute("projectCompany", task.getCompany_id());
//		if (task.getStage() != null && task.getStage().equals("进行中")
//				&& !task.getTask_status().equals("收回中")
//				&& task.getEnd_time().before(new Date())) {
//			AnimationLightTaskVo a = new AnimationLightTaskVo();
//			a.setId(task_id);
//			a.setTask_status("收回中");
//			task.setTask_status("收回中");
//			animationLightTaskService.updateAnimationLightTask(a);
//		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("task_id", task_id);
		map.put("task_type", task_type);
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

		mp.put("task_type", task_type);
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
		if ("审核中".equals(task.getStage())) {
			 List<Map<String,Object>> checkInfos=new ArrayList<Map<String,Object>>();
			Long isChecker = modelTaskService.getIsCheckerOrNot(mp);
			if (isChecker != null) {
				task.setObserver("checker");
			}
			// 暂时废弃
//			List<String> checkers = modelTaskService.getCheckersOfTask(mp);
//			String currentChecker = modelTaskService.getCurrentChecker(mp);
//			task.setCheckers(checkers);
//			task.setCurrentChecker(currentChecker);
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

}
