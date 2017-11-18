package com.dq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dq.entity.User;
import com.dq.page.UserPage;
import com.dq.service.UserService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserController {
	private static final Logger log = Logger.getLogger(UserController.class);
	@Resource
	private UserService service;
	@RequestMapping("/loadUser.do")
	public void loadUser(Integer pageIndex, Integer limit,UserPage page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<User> users = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				users = service.getByPage(page);
				map.put("rows", users);
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
			users = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateUser.do")
		public void updateUser(User user,HttpServletResponse response)throws Exception{
		service.update(user,response);
	}
	@RequestMapping("/addUser.do")
	public void addUser(User user,HttpServletResponse response)throws Exception{
		service.add(user,response);
	}
	@RequestMapping("/deleteUser.do")
	public void delete(String  ids,HttpServletResponse response)throws Exception{
		service.delete(ids,response);
	}
}
