package com.to.controller;


import com.to.entity.User;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.UserService;
import com.to.util.CookieUtil;
import com.to.util.MD5;
import com.to.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Scope("prototype")
@SessionAttributes("page")
public class UserController {

	@Resource
	private UserService service;
	@RequestMapping("/loadUser.do")
	public void loadUser(Integer pageIndex, Integer limit, String key, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<User> users = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				users = service.getUserByPage(page);
				map.put("rows", users);
				map.put("results", rows);
				map.put("hasError", false);
				map.put("error", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("hasError", true);
			map.put("results", 0);
			map.put("error", "error");
			map.put("rows", "");
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			users = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateUser.do")
		public void updateUser(User user,HttpServletResponse response)throws Exception{
		service.updateUser(user,response);
	}
	@RequestMapping("/deleteUser.do")
	public void deleteUser(String  ids,HttpServletResponse response)throws Exception{
		service.deleteUser(ids,response);
	}
	@RequestMapping("/regist.do")
	public void regist(User user, String code, HttpSession session,
					   HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			String num = session.getAttribute("num") + "";
			if (code != null && code.equals(num)) {
				Timestamp time = new Timestamp(System
						.currentTimeMillis());
				user.setJoinTime(time);
				user.setDelflag((short)2);
				user.setModifiedTime(time);
				MD5 md5 = new MD5();
				if (user != null && user.getPassword() != null) {
					user.setPassword(md5.getMD5ofStr(user.getPassword()));// 将密码进行MD5加密
				}
				service.addUser(user,response);// 注册
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
	// 找回密码
	@RequestMapping("/findbackPwd.do")
	public void updatePwdAction(User user, String code, HttpSession session,
								HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		String num = session.getAttribute("num") + "";
		if (code != null && code.equals(num)) {
			if (user != null && user.getPassword() != null) {
				MD5 md5 = new MD5();
				user.setPassword(md5.getMD5ofStr(user.getPassword()));
				user.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			}
			service.updatePwdByTel(user);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("密码修改成功");
		} else {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("验证码输入错误!");
		}
		object = JSONObject.fromObject(returnInfo);
		ResponseUtil.write(response, object);
	}
	@RequestMapping("/login.do")
	public void login(User user, HttpServletResponse response,
					  HttpSession session) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		MD5 md5 = new MD5();
		User user1 = null;
		try{
			if (user != null && user.getPassword() != null) {
				user.setPassword(md5.getMD5ofStr(user.getPassword()));// 将密码进行MD5加密
				user1 = service.login(user);// 登录
			}
			if (user1 != null) {
				JSONObject o = JSONObject.fromObject(user1);
				/*Cookie cookie = new Cookie("user", o.toString());*/
				Cookie cookie = new Cookie("user", URLEncoder.encode(o.toString(),"utf-8"));
				cookie.setMaxAge(1296000000);
				cookie.setPath("/");
				response.addCookie(cookie);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			} else {
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("用户名或密码错误!");
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
		}
	}
	@RequestMapping("/checkLogin.do")
	public void checkLogin(HttpServletResponse response,
						   HttpServletRequest request) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "user");
		if (cookie != null) {
			String value= URLDecoder.decode(cookie.getValue(),"utf-8");
			JSONObject obj=JSONObject.fromObject(value);
			User user=(User) JSONObject.toBean(obj, User.class);
			user=service.getUserById(user.getId());
			if(user!=null){
				ResponseUtil.write(response, 1);
			}else{
				ResponseUtil.write(response, 0);
			}
		} else {
			ResponseUtil.write(response, 0);
		}
	}
	@RequestMapping("/getTels.do")
	public void getTels(HttpServletResponse response) throws Exception {
		List<String> tels = service.findAllTel();
		JSONArray arr = JSONArray.fromObject(tels);
		ResponseUtil.write(response, arr);
	}
@RequestMapping("/getAreas.do")
	public void getAreas(String city,HttpServletResponse response) throws Exception {
	List<String> areas=service.findAllAreaByCity(city);
	JSONArray arr=JSONArray.fromObject(areas);
	ResponseUtil.write(response,arr);
}
}
