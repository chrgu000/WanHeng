package com.fengyun.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.fengyun.entity.Comment;
import com.fengyun.entity.Course;
import com.fengyun.entity.Teacher;
import com.fengyun.service.ICommentService;
import com.fengyun.service.ICourseService;
import com.fengyun.service.ITeacherService;

/**
 * 评论 Author yangjun
 */
@Controller
@RequestMapping("com/commentAction")
@Transactional
public class CommentAction {
	@Autowired
	private ICommentService commentService;
	@Autowired
	private ICourseService courseService;//课程
	@Autowired
	private ITeacherService teacherService;
	/**
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @param course_id
	 * @return
	 */
	@RequestMapping("/getCommentByUserId")
	@ResponseBody
	public Result getCommentByUserId(Integer pageSize, Integer pageNo, Comment comment) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			page = PageUtils.createPage(params);
			page = commentService.page1(page, comment);
			map.clear();
			map.put("pageMax", Double.valueOf(Math.ceil(page.getTotal()*1.0/page.getLimit())).intValue());
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
	 * 
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getCommentList")
	@ResponseBody
	public Result getCommentList(Integer pageSize, Integer pageNo,Comment comment,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			page = PageUtils.createPage(params);
			page = commentService.page(page, comment);
			map.clear();
			List<Comment> comments=new ArrayList<Comment>();
			Object user=session.getAttribute("loginUser");
			comments=(List<Comment>) page.getDataList();
			if(page.getDataList()!=null&&user!=null){
				JSONObject u=JSONObject.parseObject(user.toString());
				Long userId = u.getLong("id");
				if(user!=null){
					for(Comment c : comments){
						if(c.getLearner().getUser_id().equals(userId)){
							c.setOwn(true);
						}
					}
				}
			} 
			map.put("pageMax", Double.valueOf(Math.ceil(page.getTotal()*1.0/page.getLimit())).intValue());
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", comments);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}

	@RequestMapping("/create")
	public @ResponseBody
	Result create(@RequestBody Comment comment,HttpSession session,Course course) {
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		if (comment != null) {
			comment.setLearner_user_id(userId);
			comment.setCreate_date(new Date());
			commentService.save(comment);
			Course c=courseService.getCourseInfoById(comment.getCourse_id());
			course.setId(comment.getCourse_id());
			course.setStar_nums(c.getStar_nums()+comment.getStar_level());
			courseService.updateIgnoreNull(course);
			Teacher teacher=c.getTeacher();
			teacher.setEvaluation_score(teacher.getEvaluation_score()+comment.getStar_level());
			teacherService.updateIgnoreNull(teacher);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	@RequestMapping("/createComment")
	public @ResponseBody
	Result createComment(Comment comment,HttpSession session,Course course) {
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		if (comment != null) {
			comment.setLearner_user_id(userId);
			comment.setCreate_date(new Date());
			commentService.save(comment);
			Course c=courseService.getCourseInfoById(comment.getCourse_id());
			course.setId(comment.getCourse_id());
			course.setStar_nums(c.getStar_nums()+comment.getStar_level());
			courseService.updateIgnoreNull(course);
			Teacher teacher=c.getTeacher();
			teacher.setEvaluation_score(teacher.getEvaluation_score()+comment.getStar_level());
			teacherService.updateIgnoreNull(teacher);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	@RequestMapping("/update")
	public @ResponseBody
	Result update(Comment comment) {
		if (comment != null) {
			commentService.updateIgnoreNull(comment);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/delete")
	public @ResponseBody
	Result delete(Comment comment) {
		commentService.delete(comment);
		return new Result("删除成功!");
	}

	
	 
}
