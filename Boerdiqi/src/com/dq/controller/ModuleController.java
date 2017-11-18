package com.dq.controller;

import java.io.IOException;
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

import com.dq.entity.Module;
import com.dq.entity.Role;
import com.dq.entity.util.ReturnInfo;
import com.dq.service.ModuleService;
import com.dq.service.RoleService;
import com.dq.util.Authority;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/module")
@Scope("prototype")
public class ModuleController {
	private static final Logger log = Logger.getLogger(ModuleController.class);
	@Resource
	private ModuleService service;
@Resource
private RoleService rservice;
	@RequestMapping("/getTopModules.do")
	public void getTopModules(HttpServletResponse response,Integer role_id) throws IOException{
		response.setCharacterEncoding("utf-8");
		List<Module> modules=service.getTopModules();
		List<Integer> moduleIds=service.getModulesByRoleId(role_id);
		Role role=rservice.getById(role_id);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("modules",modules);
		map.put("moduleIds", moduleIds);
		map.put("role", role);
		JSONObject object=JSONObject.fromObject(map);
		response.getWriter().print(object);
	}
	@RequestMapping("/setModules.do")
	public void setModules(Integer role_id,Integer[] moduleIds,HttpServletResponse response) throws Exception{
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		boolean check=false;
		try {
			check=Authority.hasAuthority("/role/setModules.do");
			if(true){
				service.deleteRoleModuleByRoleId(role_id);
				Map<String,Object> map=new HashMap<String,Object>();
				for (Integer module_id : moduleIds) {
					map.put("role_id", role_id);
					map.put("module_id", module_id);
					service.addRoleModule(map);
				}
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			}
//			else{
//				returnInfo.setHasError(true);
//				returnInfo.setErrInfo("没有该操纵权限");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object=JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;
		}
	}
}
