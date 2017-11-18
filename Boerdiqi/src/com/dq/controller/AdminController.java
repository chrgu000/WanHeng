package com.dq.controller;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dq.dao.AdminDao;
import com.dq.entity.Admin;
import com.dq.entity.Role;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.AdminPage;
import com.dq.service.AdminService;
import com.dq.service.RoleService;
import com.dq.util.IdGenerator;
import com.dq.util.MD5;
import com.dq.util.RSAUtils;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
@SessionAttributes("adminPage")
public class AdminController {
	private static final Logger log = Logger.getLogger(AdminController.class);
	@Resource
	private AdminService service;
    @Resource
    private RoleService rservice;
    @Resource
    private AdminDao dao;
	@RequestMapping("/checkLogin.do")
	public void checkLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		Admin admin = null;
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			admin = (Admin) session.getAttribute("admin");
			if (admin != null) {
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("admin/index.html");
			} else {
				returnInfo.setErrInfo("");
				returnInfo.setHasError(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
			admin = null;
		}
	}

	@RequestMapping("/login.do")
	public synchronized void login(Admin admin, ModelMap map,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MD5 md5 = new MD5();
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		UsernamePasswordToken token = null;
		Subject subject = SecurityUtils.getSubject();
		try {
			if (admin.getUsername() == null || admin.getPassword() == null) {
				returnInfo.setErrInfo("请输入用户名、密码");
				returnInfo.setHasError(true);
			} else {
				Session session = subject.getSession();
				RSAUtils rsa = new RSAUtils();
				String modulus = (String) session.getAttribute("Modulus");
				String private_exponent = (String) session
						.getAttribute("private_exponent");
				RSAPrivateKey prkey = RSAUtils.getPrivateKey(modulus,
						private_exponent);
				String password = rsa.decrypttoStr(prkey, admin.getPassword());
				admin.setPassword(md5.getMD5ofStr(password));
				token = new UsernamePasswordToken(admin.getUsername(), admin
						.getPassword());
				try {
					subject.login(token);
					String ip = request.getRemoteAddr();
					Admin a = service.login(admin);
					a.setIp(ip);
					a.setToken(IdGenerator.genPrimaryKey().replace("-", ""));
					dao.update(a);
					log.info("管理员登录:" + admin.getUsername() + ","
							+ request.getRemoteAddr());
					session.setAttribute("admin", a);
					session.setAttribute("auths", service.getPermissions(admin
							.getUsername()));
					returnInfo.setHasError(false);
					returnInfo.setErrInfo("admin/index.html");
				} catch (Exception e) {
					e.printStackTrace();
					returnInfo.setHasError(true);
					returnInfo.setErrInfo("用户名密码错误");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setErrInfo("系统异常");
			returnInfo.setHasError(true);
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
			md5 = null;
			token = null;
			subject = null;
		}
	}

	@RequestMapping("/creatKey.do")
	public void creatKeyAction(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		RSAUtils rsa = new RSAUtils();
		Map<String, Object> keyMap = rsa.createKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyMap.get("publicKey");
		RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get("privateKey");
		JSONObject jsonObject = null;
		try {
			String Modulus = publicKey.getModulus().toString(16);
			String Exponent = publicKey.getPublicExponent().toString(16);
			String private_exponent = privateKey.getPrivateExponent()
					.toString();
			session.setAttribute("Modulus", publicKey.getModulus().toString());
			session.setAttribute("private_exponent", private_exponent);
			map.put("errType", "true");
			map.put("Exponent", Exponent);
			map.put("Modulus", Modulus);
			jsonObject = JSONObject.fromObject(map);
			ResponseUtil.write(response, jsonObject);

		} catch (Exception e) {
			map.put("errType", "error");
			jsonObject = JSONObject.fromObject(map);
			ResponseUtil.write(response, jsonObject);
		} finally {
			map = null;
			rsa = null;
			keyMap = null;
			publicKey = null;
			privateKey = null;
			jsonObject = null;
		}
	}

	@RequestMapping("/loginOut.do")
	public String loginOut(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.removeAttribute("admin");
		subject.logout();
		return "redirect:../login.html";
	}

	@RequestMapping("getLoginInfo.do")
	public void getLoginInfo(HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		JSONObject object = null;
		Admin admin = null, admin2 = null;
		try {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null) {
				subject.logout();
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("会话过期，请重新登录");
				return;
			}
			admin2 = service.getById(admin.getId());
			if (!admin.getIp().equals(admin2.getIp())
					|| !admin.getToken().equals(admin2.getToken())) {
				session.removeAttribute("admin");
				subject.logout();
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("账号被登出啦！");
				return;
			}
			returnInfo.setHasError(false);
			returnInfo.setObject(admin);
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			admin = null;
			admin2 = null;
			object = null;
			session = null;
			object = null;
		}
	}

	@RequestMapping("/loadAdmin.do")
	public void loadAdmin(Integer pageIndex, Integer limit, String key,
			Integer id, AdminPage page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		page.setUsername(key);
		List<Admin> admins = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				admins = service.getByPage(page);
				map.put("rows", admins);
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
			admins = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateAdmin.do")
	public void updateAdmin(Admin admin,HttpServletResponse response)throws Exception{
		service.update(admin,response);
	}
	@RequestMapping("/addAdmin.do")
	public void addAdmin(Admin admin,HttpServletResponse response)throws Exception{
		service.add(admin,response);
	}
	@RequestMapping("/deleteAdmin.do")
	public void deleteAdmin(String  ids,HttpServletResponse response)throws Exception{
		service.delete(ids,response);
	}
	@RequestMapping("/updPsd.do")
	public void updPsd(HttpServletResponse response,String oldpassword,String password) throws Exception{
		service.updPsd(oldpassword,password,response);
	}
	 
	@RequestMapping("/getAllRole.do")
	public void getAllRole(HttpServletResponse response) throws Exception{
		List<Role> roles=rservice.getAllRole();
		JSONArray arr=JSONArray.fromObject(roles);
		ResponseUtil.write(response, arr);
	}
	@RequestMapping("/getAdminById.do")
	public void getAdminById(Integer id,HttpServletResponse response) throws IOException{
		Admin admin=service.getById(id);
		JSONObject object=JSONObject.fromObject(admin);
		response.getWriter().print(object);
	}
	@RequestMapping("/getAdmin.do")
	public void getAdmin(HttpServletResponse response)throws Exception{
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		JSONObject object = JSONObject.fromObject(admin);
		ResponseUtil.write(response, object);
		
	}
}
