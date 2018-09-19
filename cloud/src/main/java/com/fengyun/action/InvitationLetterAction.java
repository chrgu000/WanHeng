package com.fengyun.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.fengyun.entity.Course;
import com.fengyun.entity.InvitationLetter;
import com.fengyun.entity.LearnerCourse;
import com.fengyun.entity.LetterImg;
import com.fengyun.entity.Teacher;
import com.fengyun.entity.UserInfo;
import com.fengyun.service.ICourseService;
import com.fengyun.service.IInvitationLetterService;
import com.fengyun.service.ILearnerCourseService;
import com.fengyun.service.ITeacherService;
import com.fengyun.util.Base64Image;
import com.fengyun.util.HTTPConfig;
import com.fengyun.util.HttpUtil;

/**
 * 邀请函 Author yangjun
 */
@Controller
@RequestMapping("com/invitationLetterAction")
@Transactional
public class InvitationLetterAction {
	@Autowired
	private IInvitationLetterService invitationLetterService;// 邀约函
	@Autowired
	private ITeacherService teacherService;// 教师
	@Autowired
	private ICourseService courseService;// 课程
	@Autowired
	private ILearnerCourseService learnerCourseService;// 我的课程
    /**
     * 获取邀约函的总数
     * @param session
     * @return
     */
	@RequestMapping("/getLetterNum")
	@ResponseBody
	public Result getLetterNum(HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long userId = user.getLong("id");
		Long letterNum = invitationLetterService.getLetterNum(userId);
		return new Result(Boolean.TRUE, "成功", letterNum);
	}

	/**
	 * 讲师或者学员获取邀约函列表
	 * @param pageSize
	 * @param pageNo
	 * @param letter
	 * @param session
	 * @return
	 */
	@RequestMapping("/getLetterList")
	@ResponseBody
	public Result getLetterList(Integer pageSize, Integer pageNo,
			InvitationLetter letter, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long userId = user.getLong("id");
		Page page = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			page = PageUtils.createPage(params);
			letter.setTeacher_user_id(userId);
			page = invitationLetterService.page(page, letter);
			int send = 0, receive = 0, letterNum = 0;
			@SuppressWarnings("unchecked")
			List<InvitationLetter> letters = (List<InvitationLetter>) page
					.getDataList();
			for (InvitationLetter l : letters) {
				if (l.getLearner_user_id().equals(userId)) {
					send++;
					l.setSendOrReceive("send");//发送的
				} else {
					receive++;
					l.setSendOrReceive("receive");//接收的
				}
				if (!"Y".equals(l.getIs_replay())) {
					letterNum++;
				}
			}
			map.put("total", page.getTotal());
			map.put("send", send);
			map.put("receive", receive);
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", letters);
			map.put("letterNum", letterNum);
			map.put("pageMax",
					Double.valueOf(
							Math.ceil(page.getTotal() * 1.0 / page.getLimit()))
							.intValue());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}
    /**
     * 根据id获取邀约函信息
     * @param id
     * @param sendOrReceive
     * @param session
     * @return
     */
	@RequestMapping("/getInvitationLetterById")
	@ResponseBody
	public Result getInvitationLetterById(Long id, String sendOrReceive,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long userId = user.getLong("id");
		InvitationLetter invitationLetter = invitationLetterService
				.getInvitationLetterById(id);
		if (userId.equals(invitationLetter.getLearner_user_id())) {
			map.put("role", "learner");
		} else {
			map.put("role", "teacher");
		}
		Teacher teacher = teacherService.getTeacherByUsrId(userId);
		if (teacher != null && "Y".equals(teacher.getRole_type())) {
			map.put("role_type", "固定讲师");
		} else if (teacher != null && "Y".equals(teacher.getIs_apply())) {
			map.put("role_type", "临时讲师");
		} else {
			map.put("role_type", "学员");
		}
		Map<String, Object> query = new HashMap<String, Object>();
		if ("send".equals(sendOrReceive)) {
			query.put("userId", invitationLetter.getTeacher_user_id());
		} else {
			query.put("userId", invitationLetter.getLearner_user_id());
		}
		String result1 = HttpUtil.doPost(HTTPConfig.HTTP_PREFIX
				+ "/cgwas/cloud/getUserInfoById.action", query);
		JSONArray users = JSONArray.parseObject(result1).getJSONArray("data");
		JSONObject obj = users.getJSONObject(0);
		UserInfo userInfo = new UserInfo();
		userInfo.setId(Long.valueOf(query.get("userId").toString()));
		userInfo.setHead_pic_path(obj.getString("head_pic_path"));
		userInfo.setName(obj.getString("name"));
		invitationLetter.setUser(userInfo);
		map.put("invitationLetter", invitationLetter);
		return new Result(Result.SUCCESS, "成功", map);
	}
    /**
     * 判断讲师是否有多余邀约函以便于可否创建邀约课程
     * @param session
     * @param course_id
     * @return
     */
	@RequestMapping("/checkTeacherCreateCourseByUserId")
	@ResponseBody
	public Result checkTeacherCreateCourseByUserId(HttpSession session,
			Long course_id) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long userId = user.getLong("id");
		Long unReplyLetterNum = invitationLetterService
				.getUnReplyLetterNumByUserId(userId);//未回复邀约函数量
		Long applyCourseNum = courseService.getApplyCourseNumByUserId(userId);//已创建的邀约课程数量
		Course c = courseService.getCourseInfoById(course_id);
		if (unReplyLetterNum == 0
				|| (applyCourseNum >= unReplyLetterNum && c == null)
				|| (c != null && "Y".equals(c.getIs_reply()))) {
			return new Result(Result.SUCCESS, "", false);
		}
		return new Result(Result.SUCCESS, "", true);
	}

	// 创建邀约函时的图片
	@Transactional
	@RequestMapping("/createLetterImg")
	public @ResponseBody
	Result createLetterImg(LetterImg img) {
		String imgFileUrl = "/usr/memory/cgwas/cloud/images/";
		String url = img.getImg_url();
		if (url != null && url.length() > 50) {
			url = url.substring(url.indexOf(",") + 1);
		}
		if (url != null && url.length() > 50) {
			String imgFilePath = imgFileUrl
					+ (new Random().nextInt(1000) + ""
							+ System.currentTimeMillis() + new Random()
								.nextInt(1000)) + ".jpg";
			boolean b = Base64Image.GenerateImage(url, imgFilePath);
			if (b) {
				url = imgFilePath.substring(11, imgFilePath.length());
			}
		}
		img.setImg_url(url);
		invitationLetterService.addLetterImg(img);

		return new Result(Result.SUCCESS, "保存成功!", null);
	}

	// 创建邀约函
	@Transactional
	@RequestMapping("/create")
	public @ResponseBody
	Result createInvitationLetter(InvitationLetter invitationLetter,
			HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long learner_user_id = user.getLong("id");
		invitationLetter.setLearner_user_id(learner_user_id);
		invitationLetter.setInvite_time(new Date());
		invitationLetterService.save(invitationLetter);
		return new Result(Result.SUCCESS, "保存成功!", invitationLetter.getId());
	}

	/**
	 * 获取老师所收的邀约函中最新的一个未读邀约函
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/getLastLetterOfIsNotRead")
	@ResponseBody
	public Result getLastLetterOfIsNotRead(HttpSession session) {
		JSONObject user = JSONObject.parseObject(session.getAttribute(
				"loginUser").toString());
		Long userId = user.getLong("id");
		InvitationLetter letter = invitationLetterService
				.getLastLetterOfIsNotRead(userId);
		if (letter != null) {
			return new Result(Result.SUCCESS, "成功", letter);
		} else {
			return new Result(Result.FAILURE, "成功", letter);
		}

	}

	/**
	 * 老师对邀约函是否同意
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/agreeLetterOrNot")
	@ResponseBody
	public Result agreeLetterOrNot(
			@RequestBody InvitationLetter invitationLetter, HttpSession session) {
		if ("N".equals(invitationLetter.getIs_agree())) {
			invitationLetter.setIs_replay("Y");
			invitationLetter.setReplay_time(new Date());
		} else {
			JSONObject user = JSONObject.parseObject(session.getAttribute(
					"loginUser").toString());
			Long userId = user.getLong("id");
			Teacher teacher = teacherService.getTeacherByUsrId(userId);
			if (teacher == null) {
				teacher = new Teacher();
				teacher.setUser_id(userId);
				teacher.setIs_apply("Y");
				teacher.setApply_date(new Date());
				teacher.setEvaluation_score(0);
				teacher.setCourse_num(0);
				teacher.setProject_score(100);
				teacherService.save(teacher);
			}
		}
		invitationLetterService.updateIgnoreNull(invitationLetter);
		return new Result(Result.SUCCESS, "成功", null);
	}
	/**
	 * 邀约函回复
	 * @param letter
	 * @param learnerCourse
	 * @return
	 */
	@RequestMapping("/replyLetter")
	@ResponseBody
	public Result replyLetter(InvitationLetter letter,
			LearnerCourse learnerCourse) {
		letter.setReplay_time(new Date());
		letter.setIs_replay("Y");
		invitationLetterService.updateIgnoreNull(letter);
		letter = invitationLetterService
				.getInvitationLetterById(letter.getId());
		learnerCourse.setId(null);
		learnerCourse.setCourse_id(letter.getCourse_id());
		learnerCourse.setUser_id(letter.getLearner_user_id());
		learnerCourse.setType("2");
		learnerCourse.setCreate_date(new Date());
		learnerCourse.setDelflag("N");
		learnerCourse.setIs_free("Y");
		learnerCourse.setLearn_degree(0);
		learnerCourseService.save(learnerCourse);
		Course course = courseService.getCourseInfoById(letter.getCourse_id());
		course.setId(letter.getCourse_id());
		course.setIs_reply("Y");
		course.setJoin_nums(course.getJoin_nums() + 1);
		courseService.updateIgnoreNull(course);
		return new Result(Result.SUCCESS, "回函成功!", null);
	}
}
