package com.cgwas.groupUser.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.company.entity.CompanyVo;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.employee.entity.EmployeeVo;
import com.cgwas.employee.service.api.IEmployeeService;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.gRole.service.api.IGRoleService;
import com.cgwas.groupUser.entity.GroupUserVo;
import com.cgwas.groupUser.service.api.IGroupUserService;
import com.cgwas.message.entity.Message;
import com.cgwas.message.service.api.IMessageService;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.project.entity.Project;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.service.api.ISubProjectService;
import com.cgwas.user.entity.User;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.service.api.IUserInfoService;

/**
 * 
 * @author yubangqiong
 * 
 */
@Controller
@RequestMapping("cgwas/groupUserAction")
public class GroupUserAction {
	private IGroupUserService groupUserService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private ICompanyService companyService;//公司
	@Autowired
	private IGRoleService gRoleService;//团队角色
	@Autowired
	private IProjectService projectService;// 父项目
	@Autowired
	private ISubProjectService subProjectService;//子项目
	@Autowired
	private IUserInfoService userInfoService;

	@RequestMapping("/index")
	public String index() {
		return "/dsoul/groupUser/groupUser_index.jsp";
	}

	/**
	 * 团队成员列表
	 * 
	 * @param groupUserVo
	 * @return
	 */
	
	@RequestMapping("/list")
	@ResponseBody
	public Result list(GroupUserVo groupUserVo) {
		if ( groupUserVo.getRole_id() == null) { 
			return new Result("SYS_PARAMETER", null); 
		}
		List<GroupUserVo> list = null;
		try {
			list = groupUserService.selectForList(groupUserVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
		return new Result(Boolean.TRUE,"成功",list);
	}

	/**
	 * 添加团队成员
	 * 
	 * @param groupUserVo
	 * @return
	 */
	@RequestMapping("/create")
	public @ResponseBody
	Result create(GroupUserVo groupUserVo,HttpServletRequest request) {
		if (groupUserVo.getIs_parent_project() == null
				|| groupUserVo.getProject_id() == null
				|| groupUserVo.getRole_id() == null
				|| groupUserVo.getUser_id() == null|| groupUserVo.getCompany_id() == null) {
			return new Result("SYS_PARAMETER", null);
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		EmployeeVo employee = new EmployeeVo();
		GroupUserVo obj =groupUserVo;
		employee.setCompany_id(groupUserVo.getCompany_id());
		employee.setUser_id(groupUserVo.getUser_id());
		try {
			groupUserVo = groupUserService.selectForObject(groupUserVo);
			if (groupUserVo != null) {
				return new Result("GU0001", null);
			}
			/**
			 * 判断该用户是否是公司员工
			 */
//			employee = employeeService.selectForObject(employee);
//			if (employee == null) {
//				return new Result("GU0005", null);
//			}
			/**
			 * 查询当前角色层级最大的成员
			*/
			groupUserVo = groupUserService.getMaxNumByCheck(obj);
			if(groupUserVo==null){
				obj.setNum(1);
			}else{
				if(groupUserVo.getNum()==null){
					obj.setNum(1);
				}else{
					obj.setNum(groupUserVo.getNum()+1);
				}
			}
			obj.setUserId(user.getId());
			obj.setUserName(user.getUsername());
			groupUserService.saveGroup(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
		return new Result("添加成功!");
	}

	/**
	 * 公司发送信息邀请用户成为团队成员
	 * 
	 * @param groupUserVo
	 * @param request
	 * @return
	 */
	@RequestMapping("/joinUser")
	public @ResponseBody
	Result joinUser(GroupUserVo groupUserVo, HttpServletRequest request) {
		if (groupUserVo.getRole_id() == null
				|| groupUserVo.getUser_id() == null
				|| groupUserVo.getCompany_id() == null
				|| groupUserVo.getIs_parent_project() == null
				|| groupUserVo.getProject_id() == null) {
			return new Result("SYS_PARAMETER", null);
		}
		String projectName=null;
		String name="";
		User user = (User) request.getSession().getAttribute("loginUser");
		try {
			/**
			 * 查询公司
			 */
			CompanyVo companyVo = new CompanyVo();
			companyVo.setId(groupUserVo.getCompany_id());
			companyVo = companyService.selectForObject(companyVo);

			/**
			 * 查询角色
			 */
			GRoleVo gRoleVo = new GRoleVo();
			gRoleVo.setId(groupUserVo.getRole_id());
			gRoleVo = gRoleService.selectForObject(gRoleVo);

			/**
			 * 查询项目
			 */
			/**
			 * 如果是父项目
			 */
			if(groupUserVo.getIs_parent_project().equals(ConstantUtil.IS_PARENT_POJECT)){
				Project project=new Project();
				project.setId(groupUserVo.getProject_id());
				project=projectService.selectForObject(project);
				projectName=project.getName();
			}else{//否则子项目
				SubProject subProject= new SubProject();
				subProject.setId(groupUserVo.getProject_id());
				subProject=subProjectService.selectForObject(subProject);
				projectName=subProject.getName();
			}
			/**
			 * 获取用户
			 */
			List<UserInfo> userInfoList=userInfoService.getUserInfoById(user.getId());
			if(userInfoList.size()>0){
				name=userInfoList.get(0).getName();
			}
			Message message = new Message();
			//信息所属
			message.setFor_id(groupUserVo.getRole_id());
			message.setManipulate_status(ConstantUtil.MESSAGE_JOIN_GROLE);
			message.setMessage_type(ConstantUtil.MESSAGE_JOIN_USER);
			message.setContent("'" + name + "'邀请你成为'"
					+ companyVo.getCompany_name() + "''"
					+ projectName + gRoleVo.getRole_name()
					+ "'角色");
			// user.name 邀请你成为groupUserVo.getCompanyName() 的 projectName
			// roleName 角色
			MessageDetail messageDetail = new MessageDetail();
			messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
			messageDetail.setSend_id(user.getId());// 发送者id
			messageDetail.setUser_id(groupUserVo.getUser_id());// 接收者id
			messageService.sndMessAge(message, messageDetail);
			return new Result(Boolean.TRUE,"邀请信息发送成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}

	/**
	 * 更新团队成员审核层级
	 * 
	 * @param fid
	 *            目标id
	 * @param sid
	 *            被动id
	 * @param gRoleVo
	 * @return
	 */
	@RequestMapping("/updateSupervise")
	public Result updateSupervise(Long state, Long fid, Long sid) {
		if (state == null || fid == null || sid == null) {
			return new Result("SYS_PARAMETER", null);
		}
		// 注入值supervise
		try {
			groupUserService.updateSupervise(state, fid, sid);
			return new Result(Boolean.TRUE, "成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 
	 * 获取审核员列表
	 * @param groupUserVo
	 * @return
	 */
	@RequestMapping("/getAuditorList")
	public @ResponseBody Result getAuditorList(GroupUserVo groupUserVo) {
		if (groupUserVo.getIs_parent_project() == null || groupUserVo.getProject_id() == null) {
			return new Result("SYS_PARAMETER", null);
		}
		groupUserVo.setRole_id(2l);
		try {
			List<GroupUserVo> list =groupUserService.getAuditorList(groupUserVo);
			return new Result(Boolean.TRUE, "审核员列表", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}

	/**
	 * 删除团队成员 同时更新团队成员相应管理层级
	 * 
	 * @param groupUserVo
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(GroupUserVo groupUserVo, HttpServletResponse response) {
		if (groupUserVo.getId() == null) {
			return new Result("SYS_PARAMETER", null);
		}
		try {
			groupUserService.deleteAuditor(groupUserVo);
			return new Result(Boolean.TRUE, "团队成员删除成功！", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 *
	 * 获取顶级管理层级成员
	 * @param groupUserVo
	 * @param response
	 * @return
	 */
	@RequestMapping("/getSupervise")
	@ResponseBody
	public Result getSupervise(Long project_id,String is_parent_project, HttpServletRequest request) {
		if (project_id == null || is_parent_project==null) {
			return new Result("SYS_PARAMETER", null);
		}
		Map<String,Object> map= new HashMap<String,Object>();
		List<GroupUserVo> list=null;
		GroupUserVo groupUserVo = new GroupUserVo();
		groupUserVo.setIs_parent_project(is_parent_project);
		groupUserVo.setProject_id(project_id);
		try {
			list=groupUserService.selectTeamList(groupUserVo);
			if(list.size()==0){
				return new Result(Boolean.TRUE, "暂无成员，请添加", null);
			}
			groupUserVo=list.get(0);
			groupUserVo.setPeopleNum(list.size());
			map.put("groupUserVo",groupUserVo);
			map.put("list",list);
			return new Result(Boolean.TRUE, "成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	@Autowired(required = true)
	public void setGroupUserService(
			@Qualifier("groupUserService") IGroupUserService groupUserService) {
		this.groupUserService = groupUserService;
	}
}
