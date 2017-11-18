package com.cgwas.subproject.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.project.dao.api.IProjectDao;
import com.cgwas.project.entity.Graphics;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.projectStatus.entity.ProjectStatusVo;
import com.cgwas.projectStatus.service.api.IProjectStatusService;
import com.cgwas.role.entity.Role;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.entity.SubProjectVo;
import com.cgwas.subproject.service.api.ISubProjectService;
import com.cgwas.user.entity.User;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.util.Base64Image;

/**
 * Author yangjun
 */
@Controller
@RequestMapping("cgwas/sub_projectAction")
public class SubProjectAction {
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IProjectService projectService;// 根父项目
	@Autowired
	private ISubProjectService subProjectService;// 子项目
	@Autowired
	private IProjectStatusService projectStatusService;// 项目状态
	@Autowired
	private IProjectDao projectDao;// 项目
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private ICompanyFilesService companyFilesService;

	/**
	 * 根据项目id获取项目详情
	 * 
	 * @param project_id
	 * @return
	 */
	@RequestMapping("/getProjectDetails")
	@ResponseBody
	public Result getProjectDetails(Long project_id) {
		// 项目管理者人数,已支付金额，预支付金额待处理
		SubProjectVo project = subProjectService.getProjectDetails(project_id);// 根据项目id获取子项目对象
		List<Long> ids = new ArrayList<Long>();
		ids.add(project_id);
		Map<String, Object> mapIds = new HashMap<String, Object>();
		mapIds.put("ids", ids);
		mapIds.put("is_parent", 0);// 0表示当前项目不是根项目
		List<SubProject> subProjects1 = projectService.getSubProjects(mapIds);// 根据项目id获取对应的子项目集合
		if (subProjects1.size() > 0) {// 当子项目集合的个数大于0，说明该项目有子项目
			List<Long> ids1 = new ArrayList<Long>();
			for (SubProject subProject : subProjects1) {
				ids1.add(subProject.getId());// 集合保存项目id
			}
			mapIds.put("ids", ids1);
			mapIds.put("is_parent", 0);// 0表示当前项目不是根项目
			for (int i = 0; i < subProjects1.size(); i++) {// 遍历子项目
				if (subProjects1.get(i).getIs_parent() != null
						&& subProjects1.get(i).getIs_parent().equals("1")) {// 当subProjects1.get(i).getIs_parent().equals("1")说明子项目集合中有下一级子项目
					List<SubProject> subProjects2 = projectService
							.getSubProjects(mapIds);// 根据子项目集合获取下一级子项目
					if (subProjects2.size() > 0) {// 项目一共有四层
						for (SubProject subProject : subProjects2) {
							ids1.add(subProject.getId());
						}
						mapIds.put("ids", ids1);
						mapIds.put("is_parent", 0);// 0表示当前项目不是根项目
					}
					break;
				}
			}
		}
		Long useDate = getUseDate(project.getEnd_time());// 获取可用工作日
		project.setUseDate(useDate);
		if (subProjects1.size() > 0) {// 当subProjects1.size()>0说明该项目有子项目
			@SuppressWarnings("unchecked")
			List<Long> id = (List<Long>) mapIds.get("ids");// 该项目下所有子项目总数
			project.setSubProjectNums(id.size());
		} else {
			project.setSubProjectNums(0);// 当subProjects1.size()=0说明该项目没有子项目
		}

		Integer model_task_total = projectService.getModelTaskTotal(mapIds);// 项目下的所有模型任务
		Integer animation_task_total = projectService
				.getanimationTaskTotal(mapIds);// 项目下的所有动画任务
		Integer light_task_total = projectService.getLightTaskTotal(mapIds);// 项目下所有的灯光任务
		project.setTaskTotalNums(light_task_total + model_task_total
				+ animation_task_total);// 项目下所有的任务总数
		// TODO 项目管理者人数
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("project_id", project_id);
		map.put("is_parent_project", "0");
		Integer managerNums=projectService.getManagerNums(map);
		project.setProjectManagerNums(managerNums);
		Integer modelTaskMakerNums = projectService
				.getModelTaskMakerNums(mapIds);// 项目下所有建模任务的制作者人数
		Integer animationTaskMakerNums = projectService
				.getAnimationTaskMakerNums(mapIds);// 项目下所有动画灯光任务的制作者人数
		project.setProjectMakerNums(modelTaskMakerNums + animationTaskMakerNums);// 项目下所有任务的制作者人数
		Double paid=modelTaskService.getProjectPaid(mapIds);
		// TODO项目已支付金额
		if(paid!=null){
			project.setProjectPaid(getAvgRate(paid));
		}
		return new Result(Result.SUCCESS, "成功", project);
	}
	public double getAvgRate(double rate){
		return ((int)Math.round(rate*100))/100.0;
	}
	/**
	 * 获取给定结束日期的可用工作日
	 * 
	 * @param end_time
	 * @return
	 */
	private static Long getUseDate(Date end_time) {
		Date date = new Date();
		Long d1 = end_time.getTime();
		Long d2 = date.getTime();
		Long useDate = (d1 - d2) / (1000 * 60 * 60 * 24);// 单位是天
		System.out.println(useDate);
		if (useDate <= 0) {
			return 0L;
		}
		return useDate;
	}

	/**
	 * 子项目的列表方法
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Result list(String data, String num, HttpServletRequest request,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// data =
		// "{'page':{'pageSize':'3','pageNo':'1'},'object':{'sort':'asc','field':'content_status','search':'激活','id':'179','flag':1}}";
		try {
			JSONObject param = JSON.parseObject(data);
			JSON object = (JSON) JSON.toJSON(param.get("object"));
			SubProjectVo project = new SubProjectVo();
			ProjectSearchVo search = JSONObject.toJavaObject(object,
					ProjectSearchVo.class);// 将data对象的object转换为项目搜索对象
			System.out.println(search);
			Long company_id = (Long) session.getAttribute("projectCompany");
			search.setCompany_id(company_id);
			project.setPsearch(search);
			Map<String, String> map1 = (Map<String, String>) param.get("page");
			Page page = PageUtils.createPage(map1);
			page = subProjectService.page(page, project);
			List<SubProjectVo> ps = (List<SubProjectVo>) page.getDataList();
			for (SubProjectVo projectVo : ps) {
				Map<String, String[]> m = new HashMap<String, String[]>();
				m.put("ids", new String[]{projectVo.getId()+""});
				List<Long> modelTaskIds = subProjectService.getModelTaskIds(m);// 获取删除项目下的模型任务
				List<Long> animationLightTaskIds = subProjectService
						.getAnimationLightTaskIds(m);// 获取删除项目下的动画灯光任务
				System.out.println(m);
				System.out.println(modelTaskIds);
				System.out.println(animationLightTaskIds);
				if (modelTaskIds.size() > 0
						|| animationLightTaskIds.size() > 0) {
					projectVo.setHasTask(true);
				}else{
					projectVo.setHasTask(false);
				}
			}
			if ("2".equals(num)) {
				System.out.println("subProject");
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
				for (SubProjectVo projectVo : ps) {// 遍历子项目
					Map<String, Object> mapIds = new HashMap<String, Object>();
					mapIds.put("is_parent", 0);// 为0表示当前项目不是根父项目
					List<Long> ids = new ArrayList<Long>();
					ids.add(projectVo.getId());
					mapIds.put("ids", ids);
					List<SubProject> subProjects1 = projectService
							.getSubProjects(mapIds);// 根据项目id获取子项目
					if (subProjects1.size() > 0) {// 当subProjects1.size()>0时，说明项目有子项目
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
								if (subProjects2.size() > 0) {// 项目一共有四层
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
					total_undistributed = projectService
							.getTotalUndistributed(mapIds);// 项目任务未分配的数量
					total_running = projectService.getTotalRunning(mapIds);// 项目任务进行中的数量
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
					if (total_finished!=null&&total_finished>0&&total!=null&&total >0) {
						// 计算已完成的比例
						projectVo
								.setFinished_rate((int) (total_finished*1.0 / total * 100)
										+ "%");
					} else {
						projectVo.setFinished_rate("0%");
					}

					setProjectGrahics(projectVo, mapIds);// 设置项目统计图对象
				}
			}

			// ====================================================
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", ps);
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
	private void setProjectGrahics(SubProjectVo p, Map<String, Object> mapIds) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Graphics g = new Graphics();
		int[] free = new int[5];// 未分配
		int[] underWay = new int[5];// 进行中
		int[] finish = new int[5];// 已完成
		Date currentDate = new Date();
		Date minPublishDate = projectService.getMinPublishDate(mapIds);// 获取项目中最早的日期
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
	 * 创建项目
	 * 
	 * @param project项目对象
	 * @param is_parent_project父项目是不是根父项目
	 * @param directorIds导演
	 * @param principalIds负责人
	 * @param screenwriterIds编剧
	 * @param beginTime开始日期
	 * @param endTime结束日期
	 * @return
	 */
	@RequestMapping("/create")
	public @ResponseBody
	Result create(SubProjectVo project, String is_parent_project,
			String directorIds, String principalIds, String screenwriterIds,
			String beginTime, String endTime, HttpSession session,
			StringBuffer cover_path) {
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		if (user_id == null) {
			return new Result("RY0008", null);
		}
		System.out.println("ok");
		if (is_parent_project.equals("0")) {
			project.setSub_parent_project_id(project.getProject_id());
			project.setProject_id(null);
		}
		Long company_id = (Long) session.getAttribute("projectCompany");
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		String errorInfo = "";
		CompanyFilesVo parentvo = new CompanyFilesVo();
		parentvo.setFile_url(project.getProject_folder().substring(0,project.getProject_folder().lastIndexOf("/")));
		parentvo.setIs_file(ConstantUtil.IS_FILE);
		parentvo = companyFilesService.selectForObject(parentvo);
		String folder_path = ConstantUtil.USER_PATH + uuid + "/data/"
				+ project.getProject_folder();
		if (parentvo == null) {
			errorInfo = "创建失败，父文件夹不存在！";
			return new Result(Result.FAILURE, errorInfo, null);
		}
		if (!OSSFilesUtil.isFile(folder_path.substring(0,
				folder_path.lastIndexOf("/") + 1))) {
			errorInfo = "无效的文件夹路径，请重新选择";
			return new Result(Result.FAILURE, errorInfo, null);
		}
		//保存父文件夹id
		project.setParentFileId(parentvo.getId());
		//保存项目文件夹绝对路径
		project.setFolder_path(folder_path);
		// TODOcompany_id
		Map<String, Object> map = new HashMap<String, Object>();
		if (is_parent_project != null && is_parent_project.equals("1")) {
			// 当is_parent_project==1，说明父项目是根父项目
			map.put("ids", new String[] { project.getProject_id() + "" });
		} else {
			// 当is_parent_project==0，说明父项目不是根父项目
			map.put("ids", new String[] { project.getSub_parent_project_id()
					+ "" });
		}
		map.put("is_parent_project", is_parent_project);
		List<Long> modelTaskIds = subProjectService.getModelTaskIdByMaps(map);
		List<Long> animationLightTaskIds = subProjectService
				.getAnimationLightTaskIdByMaps(map);
		if (modelTaskIds.size() > 0 || animationLightTaskIds.size() > 0) {
			return new Result(Result.FAILURE, "该子项目的父项目中有任务了，不能创建子项目！", null);
		}
		CompanyFilesVo vo = new CompanyFilesVo();
		try {
			if (cover_path.length() > 100) { // 保存用户头像
				byte[] fileByte = FileUtils.base64ToByte(cover_path.toString());
				// 父项目代号_子项目名称首拚.jpg
				// TODO
				String savePath = ConstantUtil.USER_PATH + uuid
						+ "/images/projectimg/" + System.currentTimeMillis()+ ".jpg";
				OSSFilesUtil.uploadDocumentByString(savePath, fileByte);
				project.setCover_path(savePath);
			} else {
				project.setCover_path("cgwas/images/sc/page/companyDefHeard.jpg");
			}
			/**
			 * 查询选择的文件夹
			 */
			vo.setFile_url(project.getProject_folder());
			vo=companyFilesService.selectForObject(vo);
			if(vo!=null){
				if(vo.getFor_id()!=null){
					errorInfo="此文件夹已被指定，请重新选择！";
					return new Result(Result.FAILURE, errorInfo, null);
				}
			}
			subProjectService.create(project, directorIds, principalIds,
					screenwriterIds, beginTime, endTime, session);
		} catch (RuntimeException e) {
			e.printStackTrace();
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
	 * @param is_parent_projectis_parent_project父项目是不是根父项目
	 * @param directorIds导演
	 * @param principalIds负责人
	 * @param screenwriterIds编剧
	 * @param beginTime开始日期
	 * @param endTime结束日期
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody
	Result update(SubProjectVo project, String is_parent_project,
			String directorIds, String principalIds, String screenwriterIds,
			String beginTime, String endTime, HttpSession session,
			StringBuffer cover_path) {
		String errorInfo = "";
		SubProjectVo p = subProjectService.getSubProjectById(project.getId());
		if (!p.getProject_folder().equals(project.getProject_folder())) {
			if (this.hasTaskOrNot(project.getId())) {
				errorInfo = "该项目存在任务文件夹，无法更换路径";
				return new Result(Result.FAILURE, errorInfo, null);
			}
		}
		if (is_parent_project.equals("0")) {
			project.setSub_parent_project_id(project.getProject_id());
			project.setProject_id(null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (is_parent_project != null && is_parent_project.equals("1")) {
			// 当is_parent_project==1，说明父项目是根父项目
			map.put("ids", new String[] { project.getProject_id() + "" });
		} else {
			// 当is_parent_project==0，说明父项目不是根父项目
			map.put("ids", new String[] { project.getSub_parent_project_id()
					+ "" });
		}
		map.put("is_parent_project", is_parent_project);
		List<Long> modelTaskIds = subProjectService.getModelTaskIdByMaps(map);
		List<Long> animationLightTaskIds = subProjectService
				.getAnimationLightTaskIdByMaps(map);
		if (modelTaskIds.size() > 0 || animationLightTaskIds.size() > 0) {
			return new Result(Result.FAILURE, "该子项目的父项目中有任务了，不能创建子项目！", null);
		}
		Long company_id = (Long) session.getAttribute("projectCompany");
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		if (cover_path.length() > 300) { // 保存用户头像
			byte[] fileByte = FileUtils.base64ToByte(cover_path.toString());
			// 父项目代号_子项目名称首拚.jpg
			String path=ConstantUtil.USER_PATH + uuid + "/images/projectimg/";
			String savePath =  path+ System.currentTimeMillis()+ ".jpg";
			if (OSSFilesUtil.isFile(path+p.getCover_path())) {
				OSSFilesUtil.deleteFile(path+p.getCover_path());
			}
			OSSFilesUtil.uploadDocumentByString(savePath, fileByte);
			project.setCover_path(savePath);
		}
		CompanyFilesVo vo = new CompanyFilesVo();
		String folder_path = ConstantUtil.USER_PATH + uuid + "/data/"+ project.getProject_folder();
		try {
			/**
			 * 判断该路径是否存在
			 */
			if (!OSSFilesUtil.isFile(folder_path+"/")) {
				errorInfo="无效的文件夹路径，请重新选择";
				return new Result(Result.FAILURE, errorInfo, null);
			}
			/**
			 * 查询选择的文件夹，并更新为项目文件夹
			 */
			vo=new CompanyFilesVo();
			vo.setFile_url(project.getProject_folder());
			vo=companyFilesService.selectForObject(vo);
			if(vo.getFor_id()!=null && !p.getProject_folder().equals(project.getProject_folder())){
				errorInfo="此文件夹已被指定，请重新选择！";
				return new Result(Result.FAILURE, errorInfo, null);
			}
			subProjectService.update(project, directorIds, principalIds,
					screenwriterIds, beginTime, endTime, session);
		} catch (RuntimeException e) {
			errorInfo = e.getMessage();
		}
		if (errorInfo == null || errorInfo.trim().length() == 0) {
			if(!p.getProject_folder().equals(project.getProject_folder())){
				/**
				 * 原文件夾項目文件夾能力丟失
				 */
				CompanyFilesVo vo1=new CompanyFilesVo();
				vo1.setFor_id(project.getId());
				vo1.setIs_file(ConstantUtil.IS_FILE);
				vo1.setFile_type(ConstantUtil.ZC_SUB_PROJECT);
				vo1=companyFilesService.selectForObject(vo1);
				vo1.setFor_id(null);
				vo1.setFile_type(null);
				companyFilesService.update(vo1);
				/**
				 * 选中的文件夹更新为项目文件夹
				 */
				vo.setFor_id(project.getId());
				vo.setFile_type(ConstantUtil.ZC_SUB_PROJECT);
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
		map.put("ids", ids.split(","));
		List<Long> subProjectIds = subProjectService.getSubProjectIds(map);// 获取删除项目下的直接子项目
		List<Long> modelTaskIds = subProjectService.getModelTaskIds(map);// 获取删除项目下的模型任务
		List<Long> animationLightTaskIds = subProjectService
				.getAnimationLightTaskIds(map);// 获取删除项目下的动画灯光任务
		if (subProjectIds.size() > 0 || modelTaskIds.size() > 0
				|| animationLightTaskIds.size() > 0) {
			// 如果子项目数，模型任务数，动画任务数有一个大于0，则提示不能删除
			return new Result(Result.FAILURE, "该子项目有级联任务或子项目信息,不能删除！", null);
		}
		// 删除项目对应的导演，负责人，编剧等级联数据。
		subProjectService.deleteAll(map);
		for (Long long1 : animationLightTaskIds) {

		}
		return new Result("删除成功!");
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
				subProjectService.selectForList(vo));
	}

	/**
	 * 根据项目id获取项目信息，供修改使用
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSubProjectById")
	@ResponseBody
	public Result getSubProjectById(Long id) {
		SubProjectVo v = subProjectService.getSubProjectById(id);
		return new Result(Result.SUCCESS, "成功", v);
	}

	/**
	 * 批量导入子项目
	 * 
	 * @param path导入文件的路径
	 * @param flag父项目是不是根父项目
	 * @param project_id根父项目id
	 * @param sub_parent_project_id父项目id
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importProject")
	@ResponseBody
	public Result getProjectUrl(String path, String is_parent_project,
			Long project_id, HttpServletRequest request, HttpSession session)
			throws Exception {
		Long sub_parent_project_id = null;
		if (is_parent_project.equals("0")) {
			sub_parent_project_id = project_id;
			project_id = null;
		}
		Long company_id = (Long) session.getAttribute("projectCompany");
		path = session.getServletContext().getRealPath("") + path;
		return importProject(path, is_parent_project, project_id,
				sub_parent_project_id, company_id, "", session);
	}

	public Result importProject(String filePath, String is_parent_project,
			Long project_id, Long sub_parent_project_id, Long company_id,
			String errorInfo, HttpSession session) throws Exception {
		errorInfo = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if (is_parent_project != null && is_parent_project.equals("1")) {
			map.put("ids", new String[] { project_id + "" });
		} else {
			map.put("ids", new String[] { sub_parent_project_id + "" });
		}
		map.put("is_parent_project", is_parent_project);
		List<Long> modelTaskIds = subProjectService.getModelTaskIdByMaps(map);// 根据父项目id获取对应的模型任务
		List<Long> animationLightTaskIds = subProjectService
				.getAnimationLightTaskIdByMaps(map);// 根据父项目id获取对应的动画灯光任务
		if (modelTaskIds.size() > 0 || animationLightTaskIds.size() > 0) {
			// 当模型任务和动画灯光任务存在时，则不能导入子项目啦
			return new Result(Result.FAILURE, "该子项目的父项目中有任务了，不能导入子项目！", null);
		}
		try {
			errorInfo += subProjectService.importProject(filePath, project_id,
					sub_parent_project_id, company_id, errorInfo, session);
		} catch (RuntimeException e) {
			e.printStackTrace();
			errorInfo = e.getMessage();
		}
		if (errorInfo == null || errorInfo.trim().length() == 0) {
			return new Result(Result.SUCCESS, "成功成功", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}
	}

	/**
	 * 根据父项目id获取其父项目的兄弟项目集合
	 * 
	 * @param flag表示其父项目是不是根父项目1为是0为不是
	 * @param project_id当前页面的父项目id
	 * @return
	 */
	@RequestMapping("/getParentProject")
	@ResponseBody
	public Result getParentProject(String flag, Long project_id,
			HttpSession session) {
		Long company_id = (Long) session.getAttribute("projectCompany");
		List<Map<String, Object>> parent = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_id", company_id);
		map.put("project_id", project_id);
		if (flag.equals("1")) {
			// 当flag为1时表示项目的父项目是根父项目，那么他的父项目集合是公司下的根父项目集合
			List<ProjectVo> projectVo = (List<ProjectVo>) projectDao
					.selectForList(
							"com.cgwas.project.dao.getProjectByCompanyId", map);// 根据公司id获取根项目集合作为父项目集合
			for (ProjectVo p : projectVo) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", p.getId());// 项目id
				m.put("content", p.getName());// 项目名称
				m.put("is_parent_project", "1");// 是不是父项目1表是0表不是
				m.put("project_folder", p.getProject_folder());
				parent.add(m);
			}
		} else {
			Long parent_id = subProjectService.getParentParentId(map);// 获取该父项目的父项目id，project_id字段
			if (parent_id != null) {
				map.put("is_parent", 1);// 当is_parent=1时，表示该父项目的父项目id是project_id字段
				map.put("project_id", parent_id);
				List<SubProjectVo> subProjectVo = subProjectService
						.getSubProjects(map);// 该父项目的兄弟项目
				for (SubProjectVo s : subProjectVo) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("id", s.getId());
					m.put("content", s.getName());
					m.put("is_parent_project", "0");// 是不是父项目1表是0表不是
					m.put("project_folder", s.getProject_folder());
					parent.add(m);
				}
			} else {
				parent_id = subProjectService.getSubParentId(map);// 获取该父项目的父项目id，sub_parent_project_id字段
				map.put("project_id", parent_id);
				map.put("is_parent", 0);
				List<SubProjectVo> subProjectVo = subProjectService
						.getSubProjects(map);// 该父项目的兄弟项目
				for (SubProjectVo s : subProjectVo) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("id", s.getId());
					m.put("content", s.getName());
					m.put("is_parent_project", "0");// 是不是父项目1表是0表不是
					m.put("project_folder", s.getProject_folder());
					parent.add(m);
				}
			}
		}
		return new Result(Result.SUCCESS, "成功", parent);
	}

	@RequestMapping("/checkIsLastSubProject")
	@ResponseBody
	public Result checkIsLastSubProject(Long id) {
		boolean flag = true;
		Long parent_id = subProjectService.getParentId(id);
		if (parent_id != null) {
			parent_id = subProjectService.getParentId(parent_id);
			if (parent_id != null) {
				flag = false;
			}
		}
		return new Result(flag, "成功", null);
	}
	
	@RequestMapping("/changeOutOfTime")
	@ResponseBody
	public Result changeOutOfTime(Long id, String is_parent_project,
			String begin_time, String end_time) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begin = sdf.parse(begin_time);
		Date end = sdf.parse(end_time);
		if ("1".equals(is_parent_project)) {
			ProjectVo projectVo = projectService.getProjectById(id);
			if (projectVo.getBegin_time().after(begin)) {
				projectVo.setBegin_time(begin);
			}
			if (projectVo.getEnd_time().before(end)) {
				projectVo.setEnd_time(end);
			}
			projectService.updateIgnoreNull(projectVo);
		} else {
			SubProjectVo subProjectVo = subProjectService.getSubProjectById(id);
			if (subProjectVo.getBegin_time().after(begin)) {
				subProjectVo.setBegin_time(begin);
			}
			if (subProjectVo.getEnd_time().before(end)) {
				subProjectVo.setEnd_time(end);
			}
			subProjectService.updateIgnoreNull(subProjectVo);
			if (subProjectVo.getSub_parent_project_id() != null) {
				SubProjectVo subProjectVo1 = subProjectService
						.getSubProjectById(subProjectVo
								.getSub_parent_project_id());
				if (subProjectVo1.getBegin_time().after(begin)) {
					subProjectVo1.setBegin_time(begin);
				}
				if (subProjectVo1.getEnd_time().before(end)) {
					subProjectVo1.setEnd_time(end);
				}
				subProjectService.updateIgnoreNull(subProjectVo1);
				if (subProjectVo1.getSub_parent_project_id() != null) {
					SubProjectVo subProjectVo2 = subProjectService
							.getSubProjectById(subProjectVo1
									.getSub_parent_project_id());
					if (subProjectVo2.getBegin_time().after(begin)) {
						subProjectVo2.setBegin_time(begin);
					}
					if (subProjectVo2.getEnd_time().before(end)) {
						subProjectVo2.setEnd_time(end);
					}
					subProjectService.updateIgnoreNull(subProjectVo2);
					ProjectVo projectVo = projectService
							.getProjectById(subProjectVo2.getProject_id());
					if (projectVo.getBegin_time().after(begin)) {
						projectVo.setBegin_time(begin);
					}
					if (projectVo.getEnd_time().before(end)) {
						projectVo.setEnd_time(end);
					}
					projectService.updateIgnoreNull(projectVo);

				} else {
					ProjectVo projectVo = projectService
							.getProjectById(subProjectVo1.getProject_id());
					if (projectVo.getBegin_time().after(begin)) {
						projectVo.setBegin_time(begin);
					}
					if (projectVo.getEnd_time().before(end)) {
						projectVo.setEnd_time(end);
					}
					projectService.updateIgnoreNull(projectVo);
				}

			} else {
				ProjectVo projectVo = projectService
						.getProjectById(subProjectVo.getProject_id());
				if (projectVo.getBegin_time().after(begin)) {
					projectVo.setBegin_time(begin);
				}
				if (projectVo.getEnd_time().before(end)) {
					projectVo.setEnd_time(end);
				}
				projectService.updateIgnoreNull(projectVo);
			}

		}
		return new Result(Result.SUCCESS, "成功", null);
	}

	@RequestMapping("/getProjectsByCompanyId")
	@ResponseBody
	public Result getProjectsByCompanyId(Long company_id) {
		Set<Map<String, Object>> ps = new HashSet<Map<String, Object>>();
		List<ProjectVo> projects = projectService
				.getProjectsByCompanyId(company_id);
		for (ProjectVo p : projects) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("project_name", p.getName());
			map.put("begin_time", p.getBegin_time());
			map.put("end_time", p.getEnd_time());
			map.put("project_status", p.getProjectStatus().getContent());
			if (p.getPrincipals() != null && p.getPrincipals().size() > 0)
				map.put("principal_name", p.getPrincipals().get(0)
						.getPrincipal_name());
			map.put("id", p.getId());
			ps.add(map);
		}
		return new Result(Result.SUCCESS, "成功", ps);
	}

	@RequestMapping("/getProjectsByUserId")
	@ResponseBody
	public Result getProjectsByUserId(Long user_id) {
		Set<Map<String, Object>> ps = new HashSet<Map<String, Object>>();
		List<ProjectVo> projects = projectService.getProjectsByUserId(user_id);
		for (ProjectVo p : projects) {
			Map<String, Object> map = new HashMap<String, Object>();
			String roles = "";
			System.out.println(p.getRoles());
			for (Role r : p.getRoles()) {
				roles += (r.getRole_name() + ",");
			}
			if (roles.length() > 0) {
				roles = roles.substring(0, roles.length() - 1);
			}
			map.put("project_name", p.getName());
			map.put("begin_time", p.getBegin_time());
			map.put("end_time", p.getEnd_time());
			map.put("project_status", p.getProjectStatus().getContent());
			if (p.getPrincipals() != null && p.getPrincipals().size() > 0)
				map.put("principal_name", p.getPrincipals().get(0)
						.getPrincipal_name());
			map.put("role_name", roles);
			map.put("id", p.getId());
			ps.add(map);
		}
		List<SubProjectVo> subProjects = subProjectService
				.getSubProjectsByUserId(user_id);
		for (SubProjectVo s : subProjects) {
			Map<String, Object> map = new HashMap<String, Object>();
			String roles = "";
			for (Role r : s.getRoles()) {
				roles += (r.getRole_name() + ",");
			}
			if (roles.length() > 0) {
				roles = roles.substring(0, roles.length() - 1);
			}
			Long project_id = s.getProject_id();
			Long subProject_id = s.getSub_parent_project_id();
			ProjectVo p = null;
			if (project_id == null && subProject_id != null) {
				SubProjectVo sub = subProjectService
						.getSubProjectById(subProject_id);
				project_id = sub.getProject_id();
				subProject_id = sub.getSub_parent_project_id();
				if (project_id == null && subProject_id != null) {
					sub = subProjectService.getSubProjectById(subProject_id);
					project_id = sub.getProject_id();
					p = projectService.getProjectById(project_id);
				} else {
					p = projectService.getProjectById(project_id);
				}
			} else {
				p = projectService.getProjectById(project_id);
			}
			if (p != null) {
				map.put("project_name", p.getName() + "-" + s.getName());
			} else {
				map.put("project_name", s.getName());
			}
			map.put("begin_time", s.getBegin_time());
			map.put("end_time", s.getEnd_time());
			map.put("project_status", s.getProjectStatus().getContent());
			if (s.getPrincipals() != null && s.getPrincipals().size() > 0)
				map.put("principal_name", s.getPrincipals().get(0)
						.getPrincipal_name());
			map.put("role_name", roles);
			map.put("id", s.getId());
			ps.add(map);
		}
		return new Result(Result.SUCCESS, "成功", ps);
	}

	private boolean hasTaskOrNot(Long id) {
		Map<String, Object> map = getSubProjectIdsOfSub(id);
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
