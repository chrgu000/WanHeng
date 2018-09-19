package com.fengyun.action;

import java.text.SimpleDateFormat;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.ConstantUtil;
import com.fengyun.entity.Teacher;
import com.fengyun.service.ITeacherService;
import com.fengyun.util.HTTPConfig;
import com.fengyun.util.HttpUtil;
import com.fengyun.util.ImageUtil;

/**讲师
 */
@Controller
@RequestMapping("com/teacherAction") 
@Transactional
public class TeacherAction{
	@Autowired
	private ITeacherService teacherService;
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	/**
     * 获取老师的项目信息,供提交申请资料用
     * @param session
     * @return
     */
	@RequestMapping("/getTeacherInfo")
	@ResponseBody
	public Result getTeacherInfo(HttpSession session) {
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		Map<String,Object> query=new HashMap<String,Object>();
		query.put("userId", userId);
		String result1=HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/getUserInfoById.action", query);
		JSONArray users=JSONArray.parseObject(result1).getJSONArray("data");
		JSONObject obj=users.getJSONObject(0);
		String userName=(String) obj.get("name");
		Teacher teacher=teacherService.getTeacherByUsrId(userId);
		if(teacher==null){
			teacher=new Teacher();
			teacher.setName(userName);
		}
		return new Result(Result.SUCCESS, "成功", teacher);
	}
    /**
     * 获取老师的项目信息,供提交申请资料用
     * @param session
     * @return
     */
	@RequestMapping("/getTeacherInfoOfProject")
	@ResponseBody
	public Result getTeacherInfoOfProject(HttpSession session) {
		 
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		Teacher t=teacherService.getTeacherByUsrId(userId);
		Map<String, Object> teacherInfo = new HashMap<String, Object>();
		if(t==null){
			teacherInfo.put("evaluation_score", 0);
			teacherInfo.put("course_num", 0);
		}else{
			teacherInfo.put("evaluation_score", t.getEvaluation_score());
			teacherInfo.put("course_num", t.getCourse_num());
		}
		
		teacherInfo.put("project_score", 100);
		return new Result(Result.SUCCESS, "成功", teacherInfo);
	}
	/**
	 * 判断指定用户是不是讲师
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkTeacherByUserId")
	@ResponseBody
	public Result checkTeacherByUserId(HttpSession session){
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		Teacher teacher=teacherService.checkTeacherByUserId(userId);
		if(userId==1L){
			return new Result(Result.SUCCESS, "admin", teacher);
		}else if(teacher!=null){
			return new Result(Result.SUCCESS, "", teacher);
		}
		return new Result(Result.FAILURE, "", null);
	}
    /**
     * 根据用户id获取老师的信息，供讲师设置首页用,
     * 也依据teacher是否为空作为当前人员是不是讲师的依据，在创建课程前加以判断
     * @param session
     * @returnf
     */
	@RequestMapping("/getTeacherByUserId")
	@ResponseBody
	public Result getTeacherByUsrId(HttpSession session,String flag) {
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		if(user!=null){
			Long userId = user.getLong("id");
			Teacher teacher = teacherService.getTeacherByUsrId(userId);
			if("create".equals(flag)){
				if(teacher!=null){
					return new Result(Result.SUCCESS, "已是固定讲师", teacher);
				}
				return new Result(Result.FAILURE, "不是固定讲师", null);
			}
			
			return new Result(Result.SUCCESS, "成功", teacher);
		}
		return new Result(Result.FAILURE, "未登录", null);
	}
	/**
	 * 根据用户id获取讲师信息
	 * @param user_id
	 * @return
	 */
	@RequestMapping("/getTeacherInfoByUserId")
	@ResponseBody
	public Result getTeacherInfoByUserId(Long user_id){
		Teacher teacher = teacherService.getTeacherByUsrId(user_id);
		return new Result(Result.SUCCESS, "成功", teacher);
	}
	/**
	 * 根据教师id获取老师的信息，供审核员查看审核讲师信息用
	 * @param id
	 * @return
	 */
	@RequestMapping("/getTeacherById")
	@ResponseBody
	public Result getTeacherById(Long id) {
		Teacher teacher = teacherService.getTeacherById(id);
		return new Result(Result.SUCCESS, "成功", teacher);
	}
	
	/**
	 * 审核人员获取讲师列表信息
	 * @param pageSize
	 * @param pageNo
	 * @param teacher
	 * @param startDate 申请起始时间
	 * @param endDate   申请结束时间
	 * @return
	 */
	@RequestMapping("/getCheckTeacherList")
	@ResponseBody
	public Result getCheckTeacherList(Integer pageSize,Integer pageNo,Teacher teacher,String startDate,String endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			if(startDate!=null&&!startDate.trim().equals("")){
				map.put("startDate", sdf.format(sdf.parse(startDate)));
			}
			if(endDate!=null&&!endDate.trim().equals("")){
				map.put("endDate", sdf.format(sdf.parse(endDate)));
			}
			teacher.setSearch(map);
			page = PageUtils.createPage(params);
			page = teacherService.page(page, teacher);
			map.clear();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
			map.put("pageMax", Double.valueOf(Math.ceil(page.getTotal()*1.0/page.getLimit())).intValue());
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}
	/**
	 * 按照名称搜索老师信息
	 * @param name
	 * @return
	 */
	@RequestMapping("/searchTeacherByName")
	@ResponseBody
	public Result searchTeacherByName(String name) {
		List<Teacher> teachers=teacherService.getTeacherByName(name);
		return new Result(Boolean.TRUE, "成功", teachers);
	}
	/**
	 * 首页根据讲师名字搜索讲师列表信息
	 * @param pageSize
	 * @param pageNo
	 * @param teacher
	 * @param startDate 申请起始时间
	 * @param endDate   申请结束时间
	 * @return
	 */
	@RequestMapping("/searchTeacherList")
	@ResponseBody
	public Result searchTeacherList(Integer pageSize,Integer pageNo,Teacher teacher) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			page = PageUtils.createPage(params);
			page = teacherService.page(page, teacher);
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
     * 提交讲师申请资料
     * @param teacher
     * @param session
     * @return
     */
	@RequestMapping("/create")
	@Transactional
	public @ResponseBody
	Result create(@RequestBody Teacher teacher,HttpSession session) {
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		if (teacher != null) {
			teacher.setCheck_status("C");
			teacher.setUser_id(userId);
			teacher.setRole_type("Y");
			teacher.setApply_date(new Date());
		} else {
			return new Result("数据传输失败!");
		}
		ImageUtil.saveImageOnline(teacher, session);//将teacher的图片64码生成图片并返回图片路径到teacher对象中
		Teacher teach=teacherService.getTeacherByUsrId(userId);
		if(teach!=null){
			teacher.setId(teach.getId());
			teacherService.updateIgnoreNull(teacher);
		}else{
			teacherService.save(teacher);
		}
		Map<String,Object> query=new HashMap<String,Object>();
		query.put("firstTitle", "申请讲师审核消息提醒");
		query.put("applyTitle", "讲师审核申请");
		query.put("userId", userId);
		query.put("applyType", "讲师审核");
		query.put("remark", "请尽快登录我和云平台进行审核");
		HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/wxMessageSendApi/applySend.json", query);
		return new Result(Result.SUCCESS,"提交成功,请在消息中心查看审核结果!",null);
	}
	/**
	 * 更改讲师在后台的信息
	 * @param teacher
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateIndexTeacherData")
	@ResponseBody
	public Result updateIndexTeacherData(@RequestBody Teacher teacher,HttpSession session){
		ImageUtil.saveImageOnline(teacher, session);
		teacherService.updateIgnoreNull(teacher);
		return new Result("保存成功!");
	}
    /**
     * 讲师在审核调用该方法
     * @param teacher
     * @param session
     * @return
     */
	@RequestMapping("/update")
	public @ResponseBody
	Result update(@RequestBody Teacher teacher,HttpSession session) {
		JSONObject user=JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		if (teacher != null) {
			teacherService.updateIgnoreNull(teacher);
			teacher=teacherService.getTeacherById(teacher.getId());
			Map<String,Object> message=new HashMap<String,Object>();
			message.put("send_id", userId);
			message.put("user_id",teacher.getUser_id());
			message.put("read_state","N" );
			message.put("msg_content",teacher.getCheck_idea());
			if("N".equals(teacher.getCheck_status())){
				message.put("title",teacher.getCheck_idea() );
				message.put("msg_content",teacher.getCheck_idea());
			}else{
				message.put("title","恭喜您申请的讲师已通过!" );
				message.put("msg_content","恭喜您申请的讲师已通过!");
			}
			message.put("message_type", "2");
			message.put("manipulate_status", ConstantUtil.MESSAGE_ABNEGATION);
			HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/cloud/sendMessage.action", message);
			
			Map<String,Object> query=new HashMap<String,Object>();
			query.put("firstTitle", "讲师审核结果消息提醒");
			query.put("userId", teacher.getUser_id());
			if("Y".equals(teacher.getCheck_status())){
				query.put("applyTitle", "审核通过!");
				query.put("remark", "恭喜您的讲师申请,审核通过!");
			}else{
				query.put("applyTitle", "审核未通过!");
				query.put("remark", "抱歉,您的讲师申请,审核未通过!");
			}
			HttpUtil.doPost(HTTPConfig.HTTP_PREFIX+"/cgwas/wxMessageSendApi/auditorSend.json", query);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
}
