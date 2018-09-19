package com.fengyun.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.fengyun.entity.LearnerCourse;
import com.fengyun.service.ICourseService;
import com.fengyun.service.ILearnerCourseService;
/**我的课程
*  Author yangjun
*/
@Transactional
@Controller
@RequestMapping("com/learnerCourseAction")
public class LearnerCourseAction {
	private ILearnerCourseService learnerCourseService;
	@Autowired
    private ICourseService courseService;
	/**
	 * 根据课程用户id查询该用户下所有参与的课程
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @param course_id
	 * @return
	 */
	@RequestMapping("/getLearnerCourseList")
	@ResponseBody
	public Result getLearnerCourseList(Integer pageSize, Integer pageNo, LearnerCourse learnerCourse,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = null;
		try {
			JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
			Long userId = user.getLong("id");
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			page = PageUtils.createPage(params);
			learnerCourse.setUser_id(userId);
			page = learnerCourseService.page(page, learnerCourse);
			map.clear();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}
	
	
	 /**
	  * 创建我的课程
	  * @param learnerCourse
	  * @return
	  */
	@RequestMapping("/create")
	public @ResponseBody Result create(LearnerCourse learnerCourse) {
		if (learnerCourse != null) {
			learnerCourseService.save(learnerCourse);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	/**
	 * 更改我的课程
	 * @param learnerCourse
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Result update(LearnerCourse learnerCourse) {
		if (learnerCourse != null) {
			learnerCourseService.updateIgnoreNull(learnerCourse);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	} 
	/**
	 * 删除我参与的课程
	 * @param learnerCourse
	 * @return
	 */
	
	@RequestMapping("/delete")
	public @ResponseBody Result delete(LearnerCourse learnerCourse) {
		learnerCourse.setDelflag("Y");
		learnerCourseService.updateIgnoreNull(learnerCourse);
		Map<String,Object> query=new HashMap<String,Object>();
		Integer num=courseService.getCourseJoinNumsById(learnerCourse.getCourse_id());
		num--;
		query.put("course_id", learnerCourse.getCourse_id());
		query.put("num", num);
		courseService.updateJoinNum(query);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setLearnerCourseService(
			@Qualifier("learnerCourseService") ILearnerCourseService learnerCourseService) {
		this.learnerCourseService = learnerCourseService;
	}
}
