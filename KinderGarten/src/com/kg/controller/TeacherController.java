package com.kg.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kg.dao.BabyDao;
import com.kg.entity.Admin;
import com.kg.entity.Baby;
import com.kg.entity.Garden;
import com.kg.entity.Teacher;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.TeacherPage;
import com.kg.service.TeacherService;
import com.kg.util.Base64Image;
import com.kg.util.CookieUtil;
import com.kg.util.MD5;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/teacher")
@SessionAttributes("teacherPage")
public class TeacherController {
	private static final Logger log = Logger.getLogger(TeacherController.class);
	@Resource
	private TeacherService service;
	@Resource
	private BabyDao bdao;

	@RequestMapping("/loadTeacher.do")
	public void loadAdmin(Integer pageIndex, Integer limit, String key,
			Integer garden_id, Integer state, TeacherPage page, Integer g_id,
			HttpServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		page.setUsername(key);
		page.setGarden_id(garden_id);
		if (g_id != null) {
			page.setGarden_id(g_id);
		}
		if(admin.getRole_id()==2&&admin.getGardens()!=null&&g_id==null){
			List<Integer> gardenIds=new ArrayList<Integer>();
			for (Garden g : admin.getGardens()) {
			    gardenIds.add(g.getId());	
			}
			page.setGardenIds(gardenIds);
		}
		page.setState(state);
		List<Teacher> teachers = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			teachers = service.getTeacherByPage(page);
			map.put("rows", teachers);
			map.put("results", rows);
			map.put("hasError", false);
			map.put("error", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("hasError", true);
			map.put("error", "error");
			map.put("rows", "");
			map.put("results", 0);
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			teachers = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/t_changeState.do")
	public void changeState(Integer state, Integer id,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		try {
			if (state.equals(1)) {
				state = 0;
			} else {
				state = 1;
			}
			Teacher t = new Teacher();
			t.setId(id);
			t.setState(state);
			service.updateTeacher(t);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			JSONObject object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
		}
	}

	@RequestMapping("/updTeacher.do")
	public void updateTeacher(Teacher teacher, HttpServletResponse response)
			throws Exception {
		service.updateTeacher(teacher, response);
	}

	@RequestMapping("/updTeacherById.do")
	public void updateTeacherById(Teacher teacher,
			HttpServletResponse response, HttpServletRequest request,
			String imgStr) throws Exception {
		if (imgStr != null && imgStr != "") {
			imgStr = imgStr.substring(23, imgStr.length());
			String imgFilePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "uploadstart/" + System.currentTimeMillis() + ".jpg";
			boolean b = Base64Image.GenerateImage(imgStr, imgFilePath);
			if (b) {
				String header_pic_path = "/"
						+ imgFilePath.substring(
								imgFilePath.lastIndexOf("/") - 11, imgFilePath
										.length());
				teacher.setHeader_pic_path(header_pic_path);
			}
		}
		service.updateTeacher(teacher, response);
	}

	@RequestMapping("/deleteTeacher.do")
	public void deleteTeacher(String ids, HttpServletResponse response,
			HttpSession session) throws Exception {
		service.deleteTeacher(ids, response, session);
	}

	@RequestMapping("/getTeacherById.do")
	public void getTeacherById(Integer id, HttpServletResponse response)
			throws Exception {
		Teacher teacher = service.getTeacherById(id);
		JSONObject object = JSONObject.fromObject(teacher);
		ResponseUtil.write(response, object);
	}

	@RequestMapping("/getTeacher.do")
	public void getTeacher(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer teacher_id = null;
		Cookie cookie = CookieUtil.getCookieByName(request, "teacher");
		if (cookie != null) {
			String str = cookie.getValue();
			JSONObject obj = JSONObject.fromObject(str);
			Teacher t = (Teacher) JSONObject.toBean(obj, Teacher.class);
			teacher_id = t.getId();
		}
		Teacher teacher = service.getTeacher(teacher_id);
		JSONObject object = JSONObject.fromObject(teacher);
		ResponseUtil.write(response, object);
	}

	@RequestMapping("/getBabysByTeacherId.do")
	public void getBabysByTeacherId(Integer teacher_id,
			HttpServletResponse response) throws IOException {
		Map<String, String[]> map = new HashMap<String, String[]>();
		String[] s = new String[1];
		s[0] = teacher_id.toString();
		map.put("ids", s);
		List<Baby> babys = bdao.getBabyByTeacher(map);
		if (babys.size() > 0) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}
	}

	@RequestMapping("/getTeachersByGardenId.do")
	public void getTeachersByGardenId(Integer garden_id,
			HttpServletResponse response) throws Exception {
		List<Teacher> teachers = service.getTeachersByGardenId(garden_id);
		JSONArray arr = JSONArray.fromObject(teachers);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/getTels.do")
	public void getTels(HttpServletResponse response) throws Exception {
		List<Teacher> teachers = service.findAllTeacher();
		List<String> tels = new ArrayList<String>();
		for (Teacher teacher : teachers) {
			tels.add(teacher.getTel());
		}
		JSONArray arr = JSONArray.fromObject(tels);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/regist.do")
	public void regist(Teacher teacher, String code, HttpSession session,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			String num = session.getAttribute("num") + "";
			if (code != null && code.equals(num)) {
				Timestamp regist_time = new Timestamp(System
						.currentTimeMillis());
				teacher.setJoin_time(regist_time);
				MD5 md5 = new MD5();
				if (teacher != null && teacher.getPassword() != null) {
					teacher.setPassword(md5.getMD5ofStr(teacher.getPassword()));// 将密码进行MD5加密
				}
				teacher.setSex("男");
				teacher.setState(0);
				service.regist(teacher);// 注册
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("注册成功!");
			} else {
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("验证码错误!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	@RequestMapping("/checkLogin.do")
	public void checkLogin(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "teacher");
		if (cookie != null) {
			String value = cookie.getValue();
			JSONObject obj = JSONObject.fromObject(value);
			Teacher teacher = (Teacher) JSONObject.toBean(obj, Teacher.class);
			teacher = service.getTeacherById(teacher.getId());
			if (teacher != null) {
				ResponseUtil.write(response, 1);
			} else {
				ResponseUtil.write(response, 0);
			}
		} else {
			ResponseUtil.write(response, 0);
		}
	}

	@RequestMapping("/login.do")
	public void login(Teacher teacher, HttpServletResponse response,
			HttpSession session, HttpServletRequest request) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		MD5 md5 = new MD5();
		Teacher teacher1 = null;
		if (teacher != null && teacher.getPassword() != null) {
			teacher.setPassword(md5.getMD5ofStr(teacher.getPassword()));// 将密码进行MD5加密
			teacher1 = service.login(teacher);// 登录
		}
		if (teacher1 != null) {
			Cookie cookie1 = new Cookie("teacher", null);
			cookie1.setMaxAge(0);
			cookie1.setPath("/");
			response.addCookie(cookie1);
			JSONObject o = JSONObject.fromObject(teacher1);
			Cookie cookie = new Cookie("teacher", o.toString());
			cookie.setMaxAge(1296000000);
			cookie.setPath("/");
			response.addCookie(cookie);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} else {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("用户名或密码错误!");
		}
		object = JSONObject.fromObject(returnInfo);
		ResponseUtil.write(response, object);
	}

	@RequestMapping("/findbackPwd.do")
	// 找回密码
	public void updatePwdAction(Teacher teacher, String code,
			HttpSession session, HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		String num = session.getAttribute("num") + "";
		if (code != null && code.equals(num)) {
			if (teacher != null && teacher.getPassword() != null) {
				MD5 md5 = new MD5();
				teacher.setPassword(md5.getMD5ofStr(teacher.getPassword()));
			}
			service.updatePwdByTel(teacher);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("密码修改成功");
		} else {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("验证码输入错误!");
		}
		object = JSONObject.fromObject(returnInfo);
		ResponseUtil.write(response, object);
	}

	@RequestMapping("/getRandomNumber.do")
	public void getRandomNumber(HttpServletResponse response,
			HttpSession session) throws Exception {
		Random rand = new Random();
		Integer number = rand.nextInt(10000);
		session.setAttribute("number", number);
		ResponseUtil.write(response, number);
	}

	@RequestMapping("/loginOut.do")
	public void loginOut(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName() != null && cookie.getName().equals("teacher")) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
				ResponseUtil.write(response, 1);
			}
		}
	}
}
