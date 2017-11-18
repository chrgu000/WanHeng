package com.kg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kg.entity.Admin;
import com.kg.entity.Module;
import com.kg.entity.util.Menu;
import com.kg.entity.util.ReturnInfo;
import com.kg.entity.util.SubMenu;
import com.kg.service.ModuleService;
import com.kg.util.ResponseUtil;



@Controller
@RequestMapping("/menu")
@Scope("prototype")
public class MenuController {
	private static byte[] lock = new byte[0]; // 特殊的instance变量
	private static final Logger log = Logger.getLogger(MenuController.class);// 日志文件
	@Resource
	private ModuleService moduleService;

	@RequestMapping("getMenu.do")
	public void getMenu(HttpServletResponse response) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		ReturnInfo returnInfo = new ReturnInfo();
		Admin admin = null;
		JSONObject object = null;
		List<Module> modules = null;
		List<Module> submodules = null;
		List<Menu> menus = new ArrayList<Menu>();
		List<SubMenu> subMenus = new ArrayList<SubMenu>();
		Menu menu = null;
		SubMenu subMenu = null;
		try {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null) {
				session.removeAttribute("admin");
				subject.logout();
				returnInfo.setHasError(true);
				returnInfo.setErrType("outtime");
				returnInfo.setErrInfo("会话过期，请重新登录");
				return;
			}
			map.put("role_id", admin.getRole_id());
			map.put("nflag", 0);
			modules = moduleService.getModulesByMap(map);
			for (Module module : modules) {
				subMenus = new ArrayList<SubMenu>();
				menu = new Menu();
				menu.setText(module.getName());
				map.remove("nflag");
				map.put("nflag", 1);
				map.put("pmid", module.getMid());
				submodules = moduleService.getModulesByMap(map);
				for (Module submodule : submodules) {
					subMenu = new SubMenu();
					subMenu.setHref(submodule.getUrl());
					subMenu.setId(submodule.getId());
					subMenu.setText(submodule.getName());
					subMenus.add(subMenu);
				}
				menu.setItems(subMenus);
				menus.add(menu);
			}
			returnInfo.setHasError(false);
			returnInfo.setObject(menus);
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrType("error");
			returnInfo.setErrInfo("获取权限菜单异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response,object);
			map = null;
			subject = null;
			session = null;
			returnInfo = null;
			admin = null;
			object = null;
			modules = null;
			submodules = null;
			menus = null;
			subMenus = null;
			menu = null;
			subMenu = null;
		}
	}
}
