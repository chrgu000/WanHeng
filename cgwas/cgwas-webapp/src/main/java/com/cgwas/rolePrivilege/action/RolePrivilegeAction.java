package com.cgwas.rolePrivilege.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.rolePrivilege.entity.RolePrivilegeVo;
import com.cgwas.rolePrivilege.service.api.IRolePrivilegeService;
import com.cgwas.user.entity.User;
/**
 * 
 * @author yubangqiong
 *
 */
@Controller
@RequestMapping("cgwas/rolePrivilegeAction")
public class RolePrivilegeAction {
	@Autowired
	private IRolePrivilegeService rolePrivilegeService;
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;
	private static final Logger LOG = Logger.getLogger(RolePrivilegeAction.class);
	/**
	 * 添加或者更新权限
	 * @param rolePrivilegeVo
	 * @return
	 */
	@RequestMapping("/updateRole")
	public @ResponseBody Result create(RolePrivilegeVo rolePrivilegeVo,HttpServletRequest request) {
		LOG.info("------------------------------------加载rolePrivilegeAction.create()方法--------------------------------------");
		if(rolePrivilegeVo.getIds()==null || rolePrivilegeVo.getRole_id()==null){
			return new Result("SYS_PARAMETER",null);
		}
		/**
		 * 获取当前登录的用户
		 */
		User user = (User) request.getSession().getAttribute("loginUser");
		try {
			/**
			 * 角色类型
			 */
			rolePrivilegeVo.setRole_type(ConstantUtil.ROLE_TYPE);
			rolePrivilegeVo.setUserId(user.getId());
			rolePrivilegeVo.setUserName(user.getUsername());
			boolean flag=rolePrivilegeService.save(rolePrivilegeVo);
			if(flag){
				return new Result("权限更新成功");
			}
			return new Result("SYS_DATA_ERRO",null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 添加或者更新职称权限
	 * @param rolePrivilegeVo
	 * @return
	 */
	@RequestMapping("/updatePosition")
	public @ResponseBody Result updatePosition(RolePrivilegeVo rolePrivilegeVo,HttpServletRequest request) {
		LOG.info("------------------------------------加载rolePrivilegeAction.create()方法--------------------------------------");
		if(rolePrivilegeVo.getIds()==null || rolePrivilegeVo.getRole_id()==null){
			return new Result("SYS_PARAMETER",null);
		}
		User user=(User)request.getSession().getAttribute("loginUser");
		try {
			/*PrivilegeInfoVo privilegeInfoVo= new PrivilegeInfoVo();
			privilegeInfoVo.setPrivilege_url("rolePrivilegeAction/updatePosition.json");
			privilegeInfoVo.setUser_id(user.getId());
			List<PrivilegeInfoVo> list=privilegeInfoService.isPrivilegeByUrl(privilegeInfoVo);
			if(list.size()==0){
				return new Result("SYS_PRIVILEGE_ERRO",null);
			}*/
			/**
			 * 角色类型
			 */
			rolePrivilegeVo.setUserId(user.getId());
			rolePrivilegeVo.setUserName(user.getUsername());
			rolePrivilegeVo.setRole_type(ConstantUtil.POSITION_TYPE);
			boolean flag=rolePrivilegeService.save(rolePrivilegeVo);
			if(flag){
				return new Result("权限更新成功");
			}
			return new Result("SYS_DATA_ERRO",null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
}
