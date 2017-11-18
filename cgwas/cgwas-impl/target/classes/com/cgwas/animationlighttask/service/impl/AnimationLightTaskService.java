package com.cgwas.animationlighttask.service.impl;

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
import com.cgwas.animationlighttask.dao.api.IAnimationLightTaskDao;
import com.cgwas.animationlighttask.entity.AnimationLightTask;
import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.companySoftware.dao.impl.CompanySoftwareDao;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.companySoftware.service.api.ICompanySoftwareService;
import com.cgwas.modeltask.dao.api.IModelTaskDao;
import com.cgwas.project.dao.api.IProjectDao;
import com.cgwas.projectSearch.entity.ProjectSearch;
import com.cgwas.user.entity.User;
import com.cgwas.util.ExcelImportUtil;

/**
 * Author yangjun
 */
@Service
public class AnimationLightTaskService implements IAnimationLightTaskService {
	private IAnimationLightTaskDao animationLightTaskDao;
	@Autowired
	private CompanySoftwareDao companySoftwareDao;
	@Autowired
	private ICompanySoftwareService companySoftwareService;
	@Autowired
    private IProjectDao projectDao;
	@Autowired
	private IModelTaskDao modelTaskDao;
	@Autowired
	private ICompanyFilesService companyFilesService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Serializable save(AnimationLightTask animationLightTask) {
		return animationLightTaskDao.save(animationLightTask);
	}

	public void delete(AnimationLightTask animationLightTask) {
		animationLightTaskDao.delete(animationLightTask);
	}

	public void deleteByExample(AnimationLightTask animationLightTask) {
		animationLightTaskDao.deleteByExample(animationLightTask);
	}

	public void updateIgnoreNull(AnimationLightTask animationLightTask) {
		animationLightTaskDao.updateIgnoreNull(animationLightTask);
	}

	public void updateByExample(AnimationLightTask animationLightTask) {
		animationLightTaskDao.update("updateByExampleAnimationLightTask",
				animationLightTask);
	}

	public AnimationLightTaskVo load(AnimationLightTask animationLightTask) {
		return (AnimationLightTaskVo) animationLightTaskDao
				.reload(animationLightTask);
	}

	public AnimationLightTaskVo selectForObject(
			AnimationLightTask animationLightTask) {
		return (AnimationLightTaskVo) animationLightTaskDao.selectForObject(
				"selectAnimationLightTask", animationLightTask);
	}

	public List<AnimationLightTaskVo> selectForList(
			AnimationLightTask animationLightTask) {
		return (List<AnimationLightTaskVo>) animationLightTaskDao
				.selectForList("selectAnimationLightTask", animationLightTask);
	}

	public Page page(Page page, AnimationLightTask animationLightTask) {
		return animationLightTaskDao.page(page, animationLightTask);
	}

	@Autowired
	public void setIAnimationLightTaskDao(
			@Qualifier("animationLightTaskDao") IAnimationLightTaskDao animationLightTaskDao) {
		this.animationLightTaskDao = animationLightTaskDao;
	}

	@Override
	@Transactional
	public Result  create(AnimationLightTask animationLightTask,
			String beginTime, String endTime, String softwares,String is_parent_project,HttpSession session,CompanyFilesVo vo) {
		String errorInfo = "";
		animationLightTask.setCreate_time(new Date());
		animationLightTask.setModified_time(new Date());
		Long user_id =((User) session.getAttribute("loginUser")).getId();
		animationLightTask.setCreater_id(user_id);
		try {
			animationLightTask.setBegin_time(sdf.parse(beginTime));
			animationLightTask.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			errorInfo += "时间格式错误,请按照yyyy-MM-dd HH:mm:ss格式录入时间\n";
			e.printStackTrace();
			return new Result(Result.FAILURE, errorInfo, null);
		}
		animationLightTask.setIs_parent_poject(is_parent_project);
		animationLightTaskDao.save(animationLightTask);
		
		/**
		 * 保存文件
		 */
		vo.setFor_id(animationLightTask.getId());
		if(animationLightTask.getCompanyFiles_id()>0){
			companyFilesService.updateIgnoreNull(vo);
		}else{
			companyFilesService.save(vo);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<CompanySoftwareVo> cs = JSON.parseArray(softwares,
				CompanySoftwareVo.class);
		for (CompanySoftwareVo c : cs) {
			if (c.getId() == null) {// 当软件id为空时，表示，该软件是新添加的
				Map<String, Object> sMap = new HashMap<String, Object>();
				sMap.put("softwareName", c.getSoftware_name());
				sMap.put("company_id", animationLightTask.getCompany_id());
				Long id = (Long) companySoftwareDao.selectForObject(
						"getSoftwareId", sMap);// 根据软件名称查看该软件在
				if (id == null) {// 数据表中是否存在，不存在则添加进去
					CompanySoftwareVo csv = new CompanySoftwareVo();
					csv.setSoftware_name(c.getSoftware_name());
					csv.setCompany_id(animationLightTask.getCompany_id());
					companySoftwareService.save(csv);// 保存软件至数据表中
					map.put("software_id", csv.getId());
				} else {
					map.put("software_id", id);
				}
			} else {
				map.put("software_id", c.getId());
			}
			map.put("project_id", animationLightTask.getId());
			map.put("model_type", "a_task");
			projectDao.addProjectCompanySoftware(map);// 将软件保存到项目与软件的中间表中
		}

		if (errorInfo.trim().length() == 0) {
			return new Result(Result.SUCCESS, "添加成功!", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}

	}

	@Override
	@Transactional
	public Result update(AnimationLightTaskVo animationLightTask,
			String beginTime, String endTime, String softwares) {
		String errorInfo = "";
		animationLightTask.setModified_time(new Date());
		try {
			animationLightTask.setBegin_time(sdf.parse(beginTime));
			animationLightTask.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			errorInfo += "时间格式错误,请按照yyyy-MM-dd HH:mm:ss格式录入时间\n";
			e.printStackTrace();
			return new Result(Result.FAILURE, errorInfo, null);
		}
		animationLightTaskDao.updateIgnoreNull(animationLightTask);
		Map<String, Long> m = new HashMap<String, Long>();
		m.put("task_id", animationLightTask.getId());
		animationLightTaskDao.deleteCompanySoftware(m);
		Map<String, Object> map = new HashMap<String, Object>();
		List<CompanySoftwareVo> cs = JSON.parseArray(softwares,
				CompanySoftwareVo.class);
		for (CompanySoftwareVo c : cs) {
			if (c.getId() == null) {// 当软件id为空时，表示，该软件是新添加的
				Map<String, Object> sMap = new HashMap<String, Object>();
				sMap.put("softwareName", c.getSoftware_name());
				sMap.put("company_id", animationLightTask.getCompany_id());
				Long id = (Long) companySoftwareDao.selectForObject(
						"getSoftwareId", sMap);// 根据软件名称查看该软件在
				if (id == null) {// 数据表中是否存在，不存在则添加进去
					CompanySoftwareVo csv = new CompanySoftwareVo();
					csv.setSoftware_name(c.getSoftware_name());
					csv.setCompany_id(animationLightTask.getCompany_id());
					companySoftwareService.save(csv);// 保存软件至数据表中
					map.put("software_id", csv.getId());
				} else {
					map.put("software_id", id);
				}
			} else {
				map.put("software_id", c.getId());
			}
			map.put("project_id", animationLightTask.getId());
			map.put("model_type", "a_task");
			projectDao.addProjectCompanySoftware(map);// 将软件保存到项目与软件的中间表中
		}

		if (errorInfo.trim().length() == 0) {
			return new Result(Result.SUCCESS, "保存成功!", null);
		} else {
			return new Result(Result.FAILURE, errorInfo, null);
		}
	}

	@Override
	public void deleteAll(Map<String, String[]> map) {
		animationLightTaskDao.deleteAll(map);
		animationLightTaskDao.deleteSoftware(map);
	}

	@Override
	public AnimationLightTaskVo getAnimationLightTaskById(AnimationLightTaskVo m) {
		return animationLightTaskDao.getAnimationLightTaskById(m);
	}

	@Override
	@Transactional
	public String importAnimationLightTask(String filePath, Long company_id,Long class_id,Long project_id,String is_parent_project,String errorInfo,HttpSession session) {
		errorInfo += "";
		Integer num = 1;
		List<Map<String, Object>> list = ExcelImportUtil
				.importAnimationLightTask(filePath);
		for (Map<String, Object> map : list) {
			try {
				insertTask(map, company_id, class_id,project_id,is_parent_project,num,session);
			} catch (RuntimeException e) {
				e.printStackTrace();
				errorInfo += e.getMessage();
			}
			num++;
		}
		if(errorInfo.trim().length()>0){
			 throw new RuntimeException(errorInfo);
		}
		return errorInfo;
	}

	private String insertTask(Map<String, Object> map, Long company_id,
			Long class_id, Long project_id, String is_parent_project,
			Integer num, HttpSession session) {
		String errorInfo = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		AnimationLightTaskVo task = (AnimationLightTaskVo) map.get("task");
		String beginTime=(String)map.get("beginTime");
		String endTime=(String)map.get("endTime");
		Long publish_type_id=modelTaskDao.getPublishTypeId(map);
		Long degree_id=modelTaskDao.getDegreeId(map);
 		if(publish_type_id==null){
			try {
				errorInfo += "第" + num + "行:" + map.get("publish_type")
						+ "发布类型不在平台中，请核实在录入数据\n";
				publish_type_id.intValue();
			} catch (Exception e) {
				throw new RuntimeException(errorInfo);
			}
		}
		if(degree_id==null){
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
		task.setPublish_type_id(publish_type_id);
		task.setDegree_id(degree_id);
		task.setCompany_id(company_id);
		task.setCreate_time(new Date());
		task.setModified_time(new Date());
		Long user_id =((User) session.getAttribute("loginUser")).getId();
		task.setCreater_id(user_id);
		map.put("company_id", company_id); 
		try {
			task.setBegin_time(sdf.parse(beginTime));
			task.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			errorInfo += "第" + num + "行:日期格式不正确,请按照yyyy-MM-dd HH:mm:ss格式\n";
			throw new RuntimeException(errorInfo);
		}

		animationLightTaskDao.save(task);// 将项目对象添加到数据表中
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
				sMap.put("model_type", "a_task");
				projectDao.addProjectCompanySoftware(sMap);// 将软件插入软件和项目的中间表中
			} else {// 当软件id不为空时，说明添加的软件在数据表中
				sMap.put("project_id", task.getId());
				sMap.put("software_id", software_id);
				sMap.put("model_type", "a_task");
				projectDao.addProjectCompanySoftware(sMap);// 将软件插入软件和项目的中间表中
			}
		}
		return errorInfo;
		
	}

	@Override
	public void updatePointStatus(Map<String, Long> map) {
		animationLightTaskDao.updatePointStatus(map);
		
	}

	@Override
	public void update(AnimationLightTask aTask) {
		animationLightTaskDao.update(aTask);
		
	}

	@Override
	public void updateAnimationLightTask(AnimationLightTask aTask) {
		animationLightTaskDao.updateAnimationLightTask(aTask);
		
	}

	@Override
	public List<ProjectSearch> getAnimationLightTaskSearch() {
		return animationLightTaskDao.getAnimationLightTaskSearch();
	}

	@Override
	public List<AnimationLightTaskVo> getCheckStatusTask(Map<String, Object> map) {
		return animationLightTaskDao.getCheckStatusTask(map);
	}

	@Override
	public void reUpdateAnimationLightTask(AnimationLightTask aTask) {
		animationLightTaskDao.reUpdateAnimationLightTask(aTask);
		
	}

	@Override
	public List<AnimationLightTask> getBeginTimeBeforeNowAnimationLightTask(
			Map<String, List<Long>> animationLightTaskMap) {
		
		return animationLightTaskDao.getBeginTimeBeforeNowAnimationLightTask(animationLightTaskMap);
	}

	@Override
	public void repeatBackAnimationTaskById(Map<String, Long> taskMap) {
		animationLightTaskDao.repeatBackAnimationTaskById(taskMap);
	}

	@Override
	public void updateAnimationLightTaskEndTime(AnimationLightTaskVo aTask) {
		animationLightTaskDao.updateAnimationLightTaskEndTime(aTask);
		
	}

	@Override
	public void updatePublishTypeOfTask(Map<String, Object> map) {
		animationLightTaskDao.update("com.cgwas.animationlighttask.dao.updatePublishTypeOfTask", map);
		
	}

	@Override
	public AnimationLightTaskVo getDetails(Long task_id) {
		return (AnimationLightTaskVo) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getDetails", task_id);
	}

	@Override
	public Page page2(Page page, Map<String, Object> map) {
		return animationLightTaskDao.page2(page, map);
	}

	@Override
	public Long getTaskTotal(Map<String, Object> map) {
		return (Long) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getTaskTotal1", map);
	}

	@Override
	public Long getTaskFinishedTotal(Map<String, Object> map) {
		return (Long) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getTaskFinishedTotal", map);
	}

	@Override
	public Date getTaskBeginTime(Map<String, Object> map) {
		return (Date) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getTaskBeginTime", map);
	}

	@Override
	public Date getTaskEndTime(Map<String, Object> map) {
		return (Date) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getTaskEndTime", map);
	}

	@Override
	public Date getTaskActualBeginTime(Map<String, Object> map) {
		return (Date) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getTaskActualBeginTime", map);
	}

	@Override
	public Date getTaskActualEndTime(Map<String, Object> map) {
		return (Date) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getTaskActualEndTime", map);
	}

	@Override
	public Date getAnimationLightTaskActualEndTime(Long id) {
		return (Date) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getAnimationLightTaskActualEndTime", id);
	}

	@Override
	public Long getTaskId(AnimationLightTaskVo animationLightTask) {
		return (Long) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getTaskId", animationLightTask);
	}
	@Override
	public List<AnimationLightTaskVo> getTasksByIds(Map<String, String[]> map) {
		return (List<AnimationLightTaskVo>) animationLightTaskDao.selectForList("com.cgwas.animationlighttask.dao.getTasksByIds", map);
	}

	@Override
	public void reBackTask(Long task_id) {
		 animationLightTaskDao.update("com.cgwas.animationlighttask.dao.reBackTask",task_id);		
	}

	@Override
	public AnimationLightTaskVo getReceivingModelTaskById(Long task_id) {
		return (AnimationLightTaskVo) animationLightTaskDao.selectForObject("com.cgwas.animationlighttask.dao.getReceivingModelTaskById", task_id);
	}
}
