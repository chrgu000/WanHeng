package com.cgwas.role.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.json.entity.Result;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.role.entity.Role;
import com.cgwas.role.entity.RoleVo;
import com.cgwas.role.service.api.IRoleService;
import com.cgwas.rolePrivilege.entity.RolePrivilegeVo;
import com.cgwas.rolePrivilege.service.api.IRolePrivilegeService;
/**
 * 
 * @author yubangqiong 系统角色
 *
 */
@Controller
@RequestMapping("cgwas/roleAction")
public class RoleAction {
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IRolePrivilegeService rolePrivilegeService;
	private static final Logger LOG = Logger.getLogger(RoleAction.class);

	@RequestMapping("/index")
	public String index() {
		return "/dsoul/role/role_index.jsp";
	}

	/**
	 * 角色列表
	 * @param pageSize
	 * @param pageNo
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Result list( HttpServletRequest request) {
		LOG.info("------------------------------------加载RoleAction.list()方法--------------------------------------");
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			map.put("dataList", roleService.selectForList(new Role()));
			return new Result(Boolean.TRUE,"成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	@ResponseBody
	@RequestMapping("/create")
	public Result create(RoleVo roleVo) {
		LOG.info("------------------------------------加载RoleAction.create()方法--------------------------------------");
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			roleService.save(roleVo);
			return new Result("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",map);
		}
	}
	
	/**
	 *  系统角色权限
	 * @param 
	 * @return
	 */
	@RequestMapping("/updateRolePrivilege")
	@ResponseBody
	public Result updateRolePrivilege(@RequestParam(required = false, value = "pids[]") List<Long> pids,
			Long roleId, HttpServletResponse response,CompanyFilesVo companyFilesVo) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		//校验必需参数是否为空
		if( roleId==null || pids==null){
			return new Result("SYS_PARAMETER",null);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		RolePrivilegeVo rolePrivilege = new RolePrivilegeVo();
		long startTime = System.currentTimeMillis();
		try {
			//参数注入
			rolePrivilege.setRole_id(roleId);
			rolePrivilege.setRole_type("ROLE_TYPE");
			rolePrivilege.setPidList(pids);
			rolePrivilegeService.updateRolePrivilege(rolePrivilege);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",map);
		}
		long endTime = System.currentTimeMillis();
        System.out.println("总共花费时间为：" + (endTime - startTime) + "毫秒...");
		return new Result(Boolean.TRUE,"success",map);
	}
	
	@RequestMapping("/update")
	public @ResponseBody Result update(RoleVo roleVo,HttpServletResponse response) {
		LOG.info("------------------------------------加载RoleAction.update()方法--------------------------------------");
		try {
			if (roleVo != null) {
				roleService.updateIgnoreNull(roleVo);
			} 
			return new Result("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Result delete(Role role) {
		LOG.info("------------------------------------加载RoleAction.delete()方法--------------------------------------");
		//状态标识是根据对应的字段删除数据
//		List<UserVo> userVoList=roleService.selectForList(role);
//		if(roleVoList.size()>0){
//			return new Result("R0001", null);
//		}
		try {
			//TODO 执行删除权限相关表的信息操作
			roleService.delete(role);
			return new Result("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
}
