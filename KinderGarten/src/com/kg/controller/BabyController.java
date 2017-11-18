package com.kg.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.kg.dao.GardenDao;
import com.kg.entity.Admin;
import com.kg.entity.Baby;
import com.kg.entity.Garden;
import com.kg.entity.Picture;
import com.kg.entity.Teacher;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.BabyPage;
import com.kg.service.BabyService;
import com.kg.service.PictureService;
import com.kg.service.TeacherService;
import com.kg.util.Base64Image;
import com.kg.util.CookieUtil;
import com.kg.util.MD5;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/baby")
@SessionAttributes("babyPage")
public class BabyController {
	private static final Logger log = Logger.getLogger(BabyController.class);
	@Resource
	private BabyService service;
	@Resource
	private TeacherService tservice;
	@Resource
	private PictureService pservice;
	@Resource
	private BabyDao dao;
    @Resource
    private GardenDao gdao;
	@RequestMapping("/loadBaby.do")
	public void loadAdmin(Integer pageIndex, Integer limit, String key,
			Integer garden_id, Integer teacher_id, Integer state, Integer g_id,
			Integer t_id, BabyPage page, HttpServletResponse response)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		page.setUsername(key);
		page.setGarden_id(garden_id);
		page.setTeacher_id(teacher_id);
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
		if (admin.getGarden_id() != null) {
			page.setGarden_id(admin.getGarden_id());
		}
		if (t_id != null) {
			page.setTeacher_id(t_id);
		}
		page.setState(state);
		List<Baby> babys = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			babys = service.getBabyByPage(page);
			map.put("rows", babys);
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
			babys = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/b_changeState.do")
	public void changeState(Integer state, Integer id,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		try {
			if (state.equals(1)) {
				state = 0;
			} else {
				state = 1;
			}
			Baby b = new Baby();
			b.setId(id);
			b.setState(state);
			service.updateBaby(b);
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

	@RequestMapping("/updBaby.do")
	public void updateBaby(Baby baby, HttpServletResponse response)
			throws Exception {
		service.updateBaby(baby, response);
	}

	@RequestMapping("/updBabyById.do")
	public void updateBabyById(Baby baby, HttpServletResponse response,HttpServletRequest request,String imgStr)
			throws Exception {
		if(imgStr!=null &&imgStr!=""){
			imgStr=imgStr.substring(23, imgStr.length());
			String imgFilePath=request.getSession().getServletContext().getRealPath("/")+ "uploadstart/"+System.currentTimeMillis()+".jpg";
			boolean b=Base64Image.GenerateImage(imgStr, imgFilePath);
			if(b){
				String header_pic_path="/"+imgFilePath.substring(imgFilePath.lastIndexOf("/")-11, imgFilePath.length());
				baby.setHeader_pic_path(header_pic_path);
			}
		}else{
			String endDate=baby.getEndDate();
			if(endDate!=null){
				Date date=Date.valueOf(endDate);
				if(date.after(new Date(System.currentTimeMillis()))){
					baby.setIsStudy("1");
				}else{
					baby.setIsStudy("0");
				}
			}
		}
		service.updateBaby(baby, response);
	}

	@RequestMapping("/deleteBaby.do")
	public void deleteBaby(String ids, HttpServletResponse response,HttpSession session)
			throws Exception {
		service.deleteBaby(ids, response,session);
	}

	@RequestMapping("/getBabyById.do")
	public void getBabyById(Integer id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		if (cookie != null) {
			String str = cookie.getValue();
			JSONObject obj = JSONObject.fromObject(str);
			Baby b = (Baby) JSONObject.toBean(obj, Baby.class);
			if (id == null) {
				id = b.getId();
			}
		}
		Baby baby = service.getBaby(id);
		JSONObject object = JSONObject.fromObject(baby);
		ResponseUtil.write(response, object);
	}

	@RequestMapping("/getBaby.do")
	public void getBaby(String id, HttpServletResponse response)
			throws Exception {
		Baby baby = dao.getBaby(Integer.parseInt(id));
		JSONObject object = JSONObject.fromObject(baby);
		ResponseUtil.write(response, object);
	}

	@RequestMapping("/getById.do")
	public void getById(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		String str = cookie.getValue();
		JSONObject obj = JSONObject.fromObject(str);
		Baby b = (Baby) JSONObject.toBean(obj, Baby.class);
		Baby baby = dao.getBaby(b.getId());
		JSONObject object = JSONObject.fromObject(baby);
		ResponseUtil.write(response, object);
	}

	@RequestMapping("/getPicturesByBabyId.do")
	public void getPicturesByBabyId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		String str = cookie.getValue();
		JSONObject obj = JSONObject.fromObject(str);
		Baby b = (Baby) JSONObject.toBean(obj, Baby.class);
		List<Picture> pictures = pservice.getPicturesByBabyId(b.getId());
		JSONArray arr = JSONArray.fromObject(pictures);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/getTeachersByGardenId.do")
	public void getTeachersByGardenId(Integer id, HttpServletResponse response)
			throws Exception {
		List<Teacher> teachers = tservice.getTeachersByGardenId(id);
		JSONArray arr = JSONArray.fromObject(teachers);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/getTels.do")
	public void getTels(HttpServletResponse response) throws Exception {
		List<Baby> babys = service.findAllBaby();
		List<String> tels = new ArrayList<String>();
		for (Baby baby : babys) {
			tels.add(baby.getTel());
		}
		JSONArray arr = JSONArray.fromObject(tels);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/regist.do")
	public void regist(Baby baby, String code, HttpSession session,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			String num = session.getAttribute("num") + "";
			if (code != null && code.equals(num)) {
				Timestamp regist_time = new Timestamp(System
						.currentTimeMillis());
				baby.setJoin_time(regist_time);
				MD5 md5 = new MD5();
				if (baby != null && baby.getPassword() != null) {
					baby.setPassword(md5.getMD5ofStr(baby.getPassword()));// 将密码进行MD5加密
				}
				baby.setSex("男");
				baby.setIsStudy("1");
				baby.setState(0);
				service.regist(baby);// 注册
				Garden garden=gdao.getGardenById(baby.getGarden_id());
				SimpleDateFormat sdf=new SimpleDateFormat("yy");
				String c=garden.getCode()+sdf.format(new java.util.Date())+baby.getId();
				baby.setCode(c);
				service.updateBaby(baby);
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

	@RequestMapping("/login.do")
	public void login(Baby baby, HttpServletResponse response,
			HttpSession session) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		MD5 md5 = new MD5();
		Baby baby1 = null;
		if (baby != null && baby.getPassword() != null) {
			baby.setPassword(md5.getMD5ofStr(baby.getPassword()));// 将密码进行MD5加密
			baby1 = service.login(baby);// 登录
		}
		if (baby1 != null) {
			JSONObject o = JSONObject.fromObject(baby1);
			Cookie cookie = new Cookie("baby", o.toString());
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
	public void updatePwdAction(Baby baby, String code, HttpSession session,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		String num = session.getAttribute("num") + "";
		if (code != null && code.equals(num)) {
			if (baby != null && baby.getPassword() != null) {
				MD5 md5 = new MD5();
				baby.setPassword(md5.getMD5ofStr(baby.getPassword()));
			}
			service.updatePwdByTel(baby);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("密码修改成功");
		} else {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("验证码输入错误!");
		}
		object = JSONObject.fromObject(returnInfo);
		ResponseUtil.write(response, object);
	}

	@RequestMapping("/getBabysByTeacherId.do")
	public void getBabysByTeacherId(String isStudy,HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "teacher");
		JSONObject object = JSONObject.fromObject(cookie.getValue());
		Teacher teacher = (Teacher) JSONObject.toBean(object, Teacher.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", teacher.getId()+"");
		map.put("isStudy", isStudy);
		List<Baby> babys = dao.getBabyByTeacherIds(map);
		JSONArray arr = JSONArray.fromObject(babys);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/checkLogin.do")
	public void checkLogin(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		if (cookie != null) {
			String value=cookie.getValue();
			JSONObject obj=JSONObject.fromObject(value);
			Baby baby=(Baby) JSONObject.toBean(obj, Baby.class);
			baby=service.getBabyById(baby.getId());
			if(baby!=null){
				ResponseUtil.write(response, 1);
			}else{
				ResponseUtil.write(response, 0);
			}
		} else {
			ResponseUtil.write(response, 0);
		}
	}
	

	@RequestMapping("/getBabyEndDateAndTeacherInfo.do")
	public void getBabyEndDateAndTeacherInfo(Integer id,
			HttpServletResponse response) throws Exception {
		Baby baby = service.getBabyById(id);
		if (baby.getEndDate() == null || baby.getTeacher_id() == null) {
			ResponseUtil.write(response, 1);
		} else
			ResponseUtil.write(response, 2);
	}
	@RequestMapping("/loginOut.do")
	public void loginOut(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie[] cookies=request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName()!=null&&cookie.getName().equals("baby")){
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
				ResponseUtil.write(response, 1);
			}
		}
	}
}
