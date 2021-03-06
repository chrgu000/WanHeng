package com.to.controller;

import com.to.entity.Admin;
import com.to.entity.Module;
import com.to.entity.util.Menu;
import com.to.entity.util.ReturnInfo;
import com.to.entity.util.SubMenu;
import com.to.service.ModuleService;
import com.to.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





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
		Admin admin = null;
		Session session = subject.getSession();
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		List<Module> modules = null;
		List<Module> submodules;
		submodules = null;
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
			map.put("role_id", admin.getRoleId());
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
