package com.jxc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.dao.TitleDao;
import com.jxc.entity.Admin;
import com.jxc.entity.Power;
import com.jxc.entity.Title;
import com.jxc.page.Page;
import com.jxc.serviceImpl.AdminServiceImpl;
import com.jxc.serviceImpl.PowerServiceImpl;
import com.jxc.util.MD5;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
@SessionAttributes("page")
public class AdminController {
	@Resource
	private AdminServiceImpl service;//管理员服务对象
	@Resource 
	private PowerServiceImpl pservice;//角色服务对象
	@Resource
	private TitleDao dao;
	@RequestMapping("/login.do")
	public String loginAction(Admin admin, HttpServletRequest request,
			HttpSession session) {
		MD5 md5 = new MD5();
		Admin admin1=null;
		if(admin!=null&&admin.getPassword()!=null){
			admin.setPassword(md5.getMD5ofStr(admin.getPassword()));// 将密码密文处理一下
			admin1 = service.login(admin);// 登录获取与登录等值的admin对象
		}
		if (admin1 == null) {
			request.setAttribute("message", "用户名或密码错误");
			return "admin/login";//转发到登录页面
		}
		List<Title> titles=dao.findAllTitle();
		session.setAttribute("admin", admin1);//session保存登录者的信息
		session.setAttribute("titles", titles);//session保存标题信息
		return "redirect:../admin/index.jsp";//重定向到管理员首页
	}

	@RequestMapping("/toAddAdmin.do")
	public String toAddAction(ModelMap map) {
		List<Power> powers=pservice.findAllPower();//获取所有的权限
		List<Admin> admins=service.findAllAdmin();//获取所有的管理员
		List<String> names=new ArrayList<String>();
		for (Admin admin: admins) {
			names.add(admin.getUsername());//获取所有管理员的姓名集合对象
		}
		map.put("names",names.toString());
		map.put("powers",powers);
		return "admin/admin_add";//转发到管理员添加页面
	}

	@RequestMapping("/addAdmin.do")
	public String addAction(Admin admin) { 
		MD5 md5=new MD5();
		admin.setPassword(md5.getMD5ofStr(admin.getPassword()));//用MD5的加密技术对密码进行加密
       service.addAdmin(admin);//添加管理员
       return "redirect:../admin/findAllAdminByPage.do";//重定向到管理员的首页
	}
	@RequestMapping("/toUpdateAdmin.do")
	public String toUpdateAction(ModelMap map,String username) {
		Admin admin = service.findAdminByUsername(username);//根据用户名查询管理员信心
		map.put("admin", admin);
		return "admin/admin_update";//转发到管理员修改页面
	}

	@RequestMapping("/updateAdmin.do")
	public String updateAction(Admin admin) {
		MD5 md5=new MD5();
		admin.setPassword(md5.getMD5ofStr(admin.getPassword()));//MD5技术对密码进行加密
		if (service.updateAdmin(admin)) {//修改管理员信息
			return "redirect:../admin/ findAllAdminByPage.do";//重定向管理员首页
		}
		return "admin/toUpdateAdmin.do?username=" + admin.getUsername();//转发到修改管理员信息页面
	}

	@RequestMapping("/deleteAdmin.do")
	public String deleteAction(Integer id) {
		service.deleteAdminById(id);//根据id删除管理员信息
			return "redirect:../admin/ findAllAdminByPage.do";//重定向到管理员首页
	}

	@RequestMapping("/findAllAdminByPage.do")
	public String findAllAction(ModelMap map, Page page) {
		int rows=service.findRows();//获取数据表数据的行数
		page.setRows(rows);
		List<Admin> admins = service.findAllAdminByPage(page);//分页查询所有管理员的信息
		map.put("page",page);
		map.put("admins", admins);
		return "admin/admin";//转发到管理员首页
	}
}
