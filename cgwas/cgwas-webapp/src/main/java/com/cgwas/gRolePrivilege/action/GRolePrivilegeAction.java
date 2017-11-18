package com.cgwas.gRolePrivilege.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.json.entity.Result;
import com.cgwas.gRolePrivilege.entity.GRolePrivilegeVo;
import com.cgwas.gRolePrivilege.service.api.IGRolePrivilegeService;
import com.cgwas.logInfo.service.api.ILogInfoService;
import com.cgwas.user.entity.User;
/**
 * 
 * @author yubangqiong
 *
 */
@Controller
@RequestMapping("cgwas/gRolePrivilegeAction")
public class GRolePrivilegeAction {
	@Autowired
	private IGRolePrivilegeService gRolePrivilegeService;
	@Autowired
	private ILogInfoService logInfoService;
	private static final Logger LOG = Logger.getLogger(GRolePrivilegeAction.class);
	@RequestMapping("/index")
	public String index() {
		return "/dsoul/gRolePrivilege/gRolePrivilege_index.jsp";
	}

	
	@RequestMapping("/updateGRole")
	public @ResponseBody Result updateGRole(GRolePrivilegeVo gRolePrivilegeVo,HttpServletRequest request) {
		LOG.info("------------------------------------加载GRolePrivilegeAction.updateGRole()方法--------------------------------------");
		if(gRolePrivilegeVo.getIds()==null || gRolePrivilegeVo.getRole_id()==null){
			return new Result("SYS_PARAMETER",null);
		}
		/**
		 * 获取当前登录的用户
		 */
		User user = (User) request.getSession().getAttribute("loginUser");
		try {
			gRolePrivilegeVo.setUserId(user.getId());
			gRolePrivilegeVo.setUserName(user.getUsername());
			boolean flag=gRolePrivilegeService.saveAndUpdate(gRolePrivilegeVo);
			if(flag){
				return new Result("更新权限成功！");
			}
			return new Result("SYS_DATA_ERRO",null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
}
