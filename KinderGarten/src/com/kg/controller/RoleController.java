package com.kg.controller;

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

import com.kg.entity.Role;
import com.kg.page.RolePage;
import com.kg.service.RoleService;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/role")
@Scope("prototype")
public class RoleController {
	private static final Logger log = Logger.getLogger(RoleController.class);
	@Resource
	private RoleService service;
	@RequestMapping("/loadRole.do")
	public void loadRole(Integer pageIndex, Integer limit, String key,RolePage page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Role> roles = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				roles = service.getRoleByPage(page);
				map.put("rows", roles);
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
			roles = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateRole.do")
		public void updateRole(Role role,HttpServletResponse response)throws Exception{
		service.updateRole(role,response);
	}
	@RequestMapping("/addRole.do")
	public void addRole(Role role,HttpServletResponse response)throws Exception{
		service.addRole(role,response);
	}
	@RequestMapping("/deleteRole.do")
	public void deleteRole(String  ids,HttpServletResponse response)throws Exception{
		service.deleteRole(ids,response);
	}
}
