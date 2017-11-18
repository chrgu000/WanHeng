package com.cgwas.privilegeInfo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.PrivilegeTree;
import com.cgwas.companyPosition.service.api.ICompanyPositionService;
import com.cgwas.logInfo.service.api.ILogInfoService;
import com.cgwas.privilegeInfo.entity.PrivilegeInfo;
import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.role.entity.RoleVo;
import com.cgwas.role.service.api.IRoleService;
import com.cgwas.rolePrivilege.service.api.IRolePrivilegeService;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.entity.UserCompanyVo;
import com.cgwas.userCompany.service.api.IUserCompanyService;
/**
 * 
 * @author yubangqiong
 *
 */
@Controller
@RequestMapping("cgwas/privilegeInfoAction")
public class PrivilegeInfoAction {
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;
	@Autowired
	private IRolePrivilegeService rolePrivilegeService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private ICompanyPositionService companyPositionService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private ILogInfoService logInfoService;

	private static final Logger LOG = Logger.getLogger(PrivilegeInfoAction.class);
	@RequestMapping("/index")
	public String index() {
		return "/dsoul/privilegeInfo/privilegeInfo_index.jsp";
	}
	@RequestMapping("/list")
	public Result list(HttpServletRequest request) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.list()方法--------------------------------------");
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			return new Result("MC00000", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 一级菜单列表
	 * @param privilegeInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/menuList")
	public @ResponseBody Result menuList(PrivilegeInfo privilegeInfo) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.list()方法--------------------------------------");
		if(privilegeInfo.getParent_id()==null){
			privilegeInfo.setParent_id(0l);
		}
		try {
			List<PrivilegeInfoVo>  list= privilegeInfoService.selectForList(privilegeInfo);
			
			return new Result(Boolean.TRUE,"成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 根据角色获取权限列表结构
	 * @param privilegeInfo
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/rolePrivilegeList")
	public @ResponseBody Result rolePrivilegeList(PrivilegeInfoVo privilegeInfoVo) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.rolePrivilegeList()方法--------------------------------------");
		Map<String, Object> map=new HashMap<String, Object>();
		JSONObject json=null;
		try {
			if(privilegeInfoVo.getRole_id()==null){
				List<RoleVo> list=roleService.selectForList(new RoleVo());
				map.put("roleList", list);
			}
			List<Map>  list= privilegeInfoService.selectPrivilegeByRoleId(privilegeInfoVo);
			if(list.size()>0){
				json=PrivilegeTree.getNodeModel(list);
			}
			map.put("jsonList", json);
			return new Result(Boolean.TRUE,"成功",map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	/**
	 * 根据团队角色id获取权限列表结构
	 * @param privilegeInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/gRolePrivilegeList")
	public @ResponseBody Result gRolePrivilegeList(Long role_id) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.gRolePrivilegeList()方法--------------------------------------");
		if(role_id==null){
			return new Result("SYS_PARAMETER",null);
		}
		try {
			PrivilegeInfoVo privilegeInfoVo= new PrivilegeInfoVo();
			privilegeInfoVo.setRole_id(role_id);
			List<PrivilegeInfoVo> list= privilegeInfoService.selectPrivilegeByGRole(privilegeInfoVo);
			return new Result(Boolean.TRUE,"成功",list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 根据职称获取权限列表结构
	 * @param privilegeInfo
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/positionPrivilegeList")
	public @ResponseBody Result positionPrivilegeList(PrivilegeInfoVo privilegeInfoVo,HttpServletRequest request) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.rolePrivilegeList()方法--------------------------------------");
		Map<String, Object> map=new HashMap<String, Object>();
		JSONObject json=null;
		User user=(User)request.getSession().getAttribute("loginUser");
		privilegeInfoVo.setUser_id(user.getId());
		try {
			List<Map>  list= privilegeInfoService.selectPrivilegeByPositionId(privilegeInfoVo);
			if(list.size()>0){
				json=PrivilegeTree.getNodeModel(list);
			}
			map.put("jsonList", json);
			return new Result(Boolean.TRUE,"成功",map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 根据用户获取权限列表结构
	 * @param privilegeInfo
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/userPrivilegeList")
	public @ResponseBody Result userPrivilegeList(PrivilegeInfoVo privilegeInfoVo) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.userPrivilegeList()方法--------------------------------------");
		Map<String, Object> map=new HashMap<String, Object>();
		JSONObject json=null;
		try {
			List<Map>  list= privilegeInfoService.selectPrivilegeByUserId(privilegeInfoVo);
			if(list.size()>0){
				json=PrivilegeTree.getNodeModel(list);
			}
			map.put("jsonList", json);
			return new Result(Boolean.TRUE,"成功",map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	@ResponseBody
	@RequestMapping("/create")
	public Result create(PrivilegeInfoVo privilegeInfo) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.create()方法--------------------------------------");
		try {
			privilegeInfoService.save(privilegeInfo);
			return new Result("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 判断该用户是否拥有该菜单下对应的子菜单(功能)权限，若存在前端跳到返回的url指定页面
	 * @param data
	 * @param response
	 * @return
	 */
	@RequestMapping("/isPrivilege")
	public @ResponseBody Result isPrivilege(Long id,HttpServletResponse response,HttpServletRequest request) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.isPrivilege()方法--------------------------------------");
		if(id==null){
			return new Result("SYS_PARAMETER",null);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		PrivilegeInfoVo privilegeInfoVo= new PrivilegeInfoVo();
		User user= (User)request.getSession().getAttribute("loginUser");
		List<PrivilegeInfoVo> list= null;
		try {
			privilegeInfoVo.setParent_id(id);
			RoleVo roleVo = roleService.selectForUserId(user.getId());
			if(roleVo==null){
				return new Result("SYS_PRIVILEGE",null);
			}
			if (roleVo.getRole_type().equals("ADMIN")) {
				list=privilegeInfoService.selectForList(privilegeInfoVo);
			}else{
				Long company_id=(Long)request.getSession().getAttribute("sessionCompany");
				System.out.println("----------------公司id---"+company_id+"-----------------");
				if(company_id!=null){
					UserCompany userCompany= userCompanyService.companygetUser(company_id);
					if(user.getId().longValue()==userCompany.getUse_id().longValue()){
						List<Long> privilegeIds= privilegeInfoService.selectPIdsByUserId(user.getId());
						privilegeInfoVo= new PrivilegeInfoVo();
						privilegeInfoVo.setParent_id(id);
						privilegeInfoVo.setPrivilegeIds(privilegeIds);
						list=privilegeInfoService.selectPrivilegeByCheck(privilegeInfoVo);
					}else{
						privilegeInfoVo.setUser_id(user.getId());
						privilegeInfoVo.setCompany_id(company_id);
						list=privilegeInfoService.isPrivilegeByUrl(privilegeInfoVo);
					}
				}else{
					UserCompany userCompany= new UserCompany();
					userCompany.setUse_id(user.getId());
					List<UserCompanyVo> companyList= userCompanyService.selectForList(userCompany);
					if(companyList.size()==0){
						privilegeInfoVo.setUser_id(user.getId());
						list=privilegeInfoService.isPrivilegeByUrl(privilegeInfoVo);
					}else{
						List<Long> privilegeIds= privilegeInfoService.selectPIdsByUserId(user.getId());
						privilegeInfoVo= new PrivilegeInfoVo();
						privilegeInfoVo.setParent_id(id);
						privilegeInfoVo.setPrivilegeIds(privilegeIds);
						list=privilegeInfoService.selectPrivilegeByCheck(privilegeInfoVo);
					}
				}
			}
			map.put("list", list);
			if(list.size()==0){
				return new Result("SYS_PRIVILEGE",null);
			}
			return new Result(Boolean.TRUE,"成功",map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 任务权限
	 * @param data
	 * @param response
	 * @return
	 */
	@RequestMapping("/taskPrivilege")
	public @ResponseBody Result taskPrivilege(Long id,Long user_id,HttpServletResponse response,HttpServletRequest request) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.isPrivilege()方法--------------------------------------");
		if(id==null){
			return new Result("SYS_PARAMETER",null);
		}
		PrivilegeInfo privilegeInfo= new PrivilegeInfo();
		Map<String, Object> map=new HashMap<String, Object>();
		PrivilegeInfoVo privilegeInfoVo= new PrivilegeInfoVo();
		User user= (User)request.getSession().getAttribute("loginUser");
		List<PrivilegeInfoVo> list= null;
		try {
			privilegeInfo.setParent_id(id);
			RoleVo roleVo = roleService.selectForUserId(user.getId());
			if(roleVo==null){
				return new Result("SYS_PRIVILEGE",null);
			}
			if (roleVo.getRole_type().equals("ADMIN")) {
				list=privilegeInfoService.selectForList(privilegeInfoVo);
			}else{
				Long company_id=(Long)request.getSession().getAttribute("projectCompany");
				UserCompany userCompany= userCompanyService.companygetUser(company_id);
				if(user.getId().longValue()==userCompany.getUse_id().longValue()){
					list=privilegeInfoService.selectForList(privilegeInfoVo);
				}else{
					privilegeInfoVo.setUser_id(user.getId());
					privilegeInfoVo.setCompany_id(company_id);
					list=privilegeInfoService.isPrivilegeByUrl(privilegeInfoVo);
				}
			}
			map.put("list", list);
			if(list.size()==0){
				return new Result("SYS_PRIVILEGE",null);
			}
			return new Result(Boolean.TRUE,"成功",map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 项目管理权限
	 * @param data
	 * @param response
	 * @return
	 */
	@RequestMapping("/projectPrivilege")
	public @ResponseBody Result projectPrivilege(Long id,Long user_id,Long company_id,HttpServletResponse response,HttpServletRequest request) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.projectPrivilege()方法--------------------------------------");
		if(id==null){
			return new Result("SYS_PARAMETER",null);
		}
		Map<String, Object> param=new HashMap<String, Object>();
		PrivilegeInfoVo privilegeInfoVo= new PrivilegeInfoVo();
		User user= (User)request.getSession().getAttribute("loginUser");
		List<PrivilegeInfoVo> list= null;
		try {
			privilegeInfoVo.setParent_id(id);
			RoleVo roleVo = roleService.selectForUserId(user.getId());
			if(roleVo==null){
				return new Result("SYS_PRIVILEGE",null);
			}
			if (roleVo.getRole_type().equals("ADMIN")) {
				list=privilegeInfoService.selectForList(privilegeInfoVo);
			}else{
				Long company_ids=(Long)request.getSession().getAttribute("projectCompany");
				if(company_ids==null){
					company_ids=company_id;
				}
				UserCompany userCompany= userCompanyService.companygetUser(company_ids);
				if(user.getId().longValue()==userCompany.getUse_id().longValue()){
					list=privilegeInfoService.selectForList(privilegeInfoVo);
				}else{
					privilegeInfoVo.setUser_id(user.getId());
					privilegeInfoVo.setCompany_id(company_ids);
					list=privilegeInfoService.isPrivilegeByUrl(privilegeInfoVo);
				}
			}
			if(list.size()==0){
				return new Result("SYS_PRIVILEGE",null);
			}
			for (PrivilegeInfoVo privilegeInfoVo2 : list) {
				param.put(privilegeInfoVo2.getPrivilege_mark(), privilegeInfoVo2.getId());
			}
			return new Result(Boolean.TRUE,"成功",param);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 权限列表
	 * @param data
	 * @param response
	 * @return
	 */
	@RequestMapping("/privilegeList")
	public Result privilegeList(Long privilege_id,HttpServletResponse response,HttpServletRequest request) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.privilegeList()方法--------------------------------------");
		if(privilege_id==null){
			return new Result("SYS_PARAMETER",null);
		}
		User user=(User)request.getSession().getAttribute("loginUser");
		Map<String, Object> map=new HashMap<String, Object>();
		PrivilegeInfoVo privilegeInfoVo= new PrivilegeInfoVo();
		PrivilegeInfo privilegeInfo= new PrivilegeInfo();
		try {
			//获取当前菜单对象
			privilegeInfo.setId(privilege_id);
			privilegeInfo=privilegeInfoService.selectForObject(privilegeInfo);
			List<Long> privilegeIds=privilegeInfoService.selectPIdsByUserId(user.getId());
			privilegeInfoVo.setPrivilegeIds(privilegeIds);
			if(privilegeInfo.getParent_id()==0){
				privilegeInfoVo.setParent_id(privilegeInfo.getId());
			}else{
				/**
				 * 参数注入
				 */
				privilegeInfoVo.setParent_id(privilegeInfo.getParent_id());
				privilegeInfoVo.setPrivilege_type("M");//M表示菜单权限
				List<PrivilegeInfoVo> menulist=privilegeInfoService.selectPrivilegeByCheck(privilegeInfoVo);
				if(menulist.size()>0){
					map.put("menuList", menulist);
					privilegeInfoVo.setParent_id(menulist.get(0).getId());
				}else{
					privilegeInfoVo.setParent_id(privilegeInfo.getParent_id());
				}
			}
			privilegeInfoVo.setPrivilege_type("P");// P表示功能权限
			List<PrivilegeInfoVo> prilvlegelist=privilegeInfoService.selectPrivilegeByCheck(privilegeInfoVo);
			map.put("privilegeList", prilvlegelist);
			return new Result(Boolean.TRUE,"",map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	@Transactional
	public Result delete(PrivilegeInfo privilegeInfo,String id) {
		LOG.info("------------------------------------加载PrivilegeInfoAction.delete()方法--------------------------------------");
		id="10,11";
		String[] ids=id.split(",");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("ids", ids);
		//状态标识是根据对应的字段删除数据
		param.put("status", 2);
		try {
			//TODO 执行删除权限相关表的信息操作
			privilegeInfoService.deleteAll(param,privilegeInfo);
			return new Result("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
}
