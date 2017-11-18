package com.kg.controller;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.kg.entity.Admin;
import com.kg.entity.Garden;
import com.kg.entity.Role;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.AdminPage;
import com.kg.service.AdminService;
import com.kg.service.GardenService;
import com.kg.service.RoleService;
import com.kg.util.IdGenerator;
import com.kg.util.MD5;
import com.kg.util.RSAUtils;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
@SessionAttributes("adminPage")
public class AdminController {
	private static final Logger log = Logger.getLogger(AdminController.class);
	@Resource
	private AdminService service;
    @Resource
    private GardenService gservice;
    @Resource
    private RoleService rservice;
	@RequestMapping("/getTelCode.do")
	public void getTel(final String tel, Integer number,HttpSession session,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		     Integer n=(Integer)session.getAttribute("number");
		    if(number!=null&&number.equals(n)){
		    	Random rand = new Random();
				Integer num = (int) (rand.nextInt(899999) + 100000);// 生成随机验证码
				session.setAttribute("num", num);
				HashMap<String, Object> result = null;
				CCPRestSDK restAPI = new CCPRestSDK();
				restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
				restAPI.setAccount("8a216da85a3c0c39015a4089973601e1",
						"02ba9baec02845009583f26c15a4d735");// 初始化主帐号和主帐号TOKEN
				restAPI.setAppId("8aaf07085a3c0ea1015a58fe6a2b09ca");// 初始化应用ID
				result = restAPI.sendTemplateSMS(tel, "155706", new String[] { num
						+ "" });
				if ("000000".equals(result.get("statusCode"))) {
					response.getWriter().print(1);
				} else {
					response.getWriter().print(0);
				}
		    }
	}
    
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
				returnInfo.setErrInfo("index.html");
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
					service.updateAdmin(a);
					log.info("管理员登录:" + admin.getUsername() + ","
							+ request.getRemoteAddr());
					session.setAttribute("admin", a);
					session.setAttribute("auths", service.getPermissions(admin
							.getUsername()));
					returnInfo.setHasError(false);
					returnInfo.setErrInfo("index.html");
				} catch (Exception e) {
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
		return "redirect:login.html";
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
			admin2 = service.getAdminById(admin.getId());
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
		page.setGarden_id(id);
		List<Admin> admins = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				admins = service.getAdminByPage(page);
				for (Admin admin : admins) {
					if(admin.getRole().getId()==2){
						String gardenes="";
						List<Garden> gardens=gservice.getGardensByAdminId(admin.getId());
						for (Garden garden : gardens) {
							gardenes+=garden.getName()+"</br>";
						}
						admin.setGardenes(gardenes);
					}
				}
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
	@Transactional
	public void updateAdmin(Admin admin,@RequestParam(value = "gardenIds[]", required = false) Integer[] gardenIds,HttpServletResponse response)throws Exception{
		service.updateAdmin(admin,response);
		if(gardenIds!=null){
			service.deleteAdminGarden(admin.getId());
			for (Integer id : gardenIds) {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("admin_id", admin.getId());
				map.put("garden_id", id);
				service.addAdminGarden(map);
			}
		}
	}
	@RequestMapping("/addAdmin.do")
	@Transactional
	public void addAdmin(Admin admin,@RequestParam(value = "gardenIds[]", required = false) Integer[] gardenIds,HttpServletResponse response)throws Exception{
		service.addAdmin(admin,response);
		if(gardenIds!=null){
			for (Integer id : gardenIds) {
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("admin_id", admin.getId());
				map.put("garden_id", id);
				service.addAdminGarden(map);
			}
		}
	}
	@RequestMapping("/deleteAdmin.do")
	@Transactional
	public void deleteAdmin(String  ids,HttpServletResponse response)throws Exception{
		service.deleteAdmin(ids,response);
	}
	@RequestMapping("/updPsd.do")
	public void updPsd(HttpServletResponse response,String oldpassword,String password) throws Exception{
		service.updPsd(oldpassword,password,response);
	}
	@RequestMapping("/getAllGarden.do")
	public void getAllGarden(HttpServletResponse response,HttpSession session) throws Exception{
		Admin admin=(Admin) session.getAttribute("admin");
		if(admin.getRole_id()==2){
			List<Garden> gardens=admin.getGardens();
			JSONArray arr=JSONArray.fromObject(gardens);
			ResponseUtil.write(response, arr);
		}else{
			List<Garden> gardens=gservice.getAllGarden();
			JSONArray arr=JSONArray.fromObject(gardens);
			ResponseUtil.write(response, arr);
		}
	
	}
	@RequestMapping("/getAllRole.do")
	public void getAllRole(HttpServletResponse response) throws Exception{
		List<Role> roles=rservice.getAllRole();
		JSONArray arr=JSONArray.fromObject(roles);
		ResponseUtil.write(response, arr);
	}
	@RequestMapping("/getAdminById.do")
	public void getAdminById(Integer id,HttpServletResponse response) throws IOException{
		Admin admin=service.getAdminById(id);
		List<Garden> gardens=gservice.getGardensByAdminId(id);
		admin.setGardens(gardens);
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
