package com.cgwas.gRole.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.MultipleTree;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.gRole.service.api.IGRoleService;
import com.cgwas.gRolePrivilege.entity.GRolePrivilegeVo;
import com.cgwas.groupUser.entity.GroupUserVo;
import com.cgwas.groupUser.service.api.IGroupUserService;
import com.cgwas.rolePrivilege.service.api.IRolePrivilegeService;
import com.cgwas.user.entity.User;

/**
 * 
 * @author yubangqiong 团队角色
 * 
 */
@Controller
@RequestMapping("cgwas/gRoleAction")
public class GRoleAction {
	@Autowired
	private IGRoleService gRoleService;
	@Autowired
	private IGroupUserService groupUserService;
	@Autowired
	private IRolePrivilegeService rolePrivilegeService;

	@RequestMapping("/index")
	public String index() {
		return "/dsoul/gRole/gRole_index.jsp";
	}

	@RequestMapping("/list")
	public Result list(String data, HttpServletRequest request, Model model,
			GRoleVo gRoleVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 注入值
		gRoleVo.setProject_id(1l);
		gRoleVo.setIs_parent_poject("1");
		try {
			List<GRoleVo> list = gRoleService.selectForList(gRoleVo);
			map.put("dataList", list);
			return new Result("MC00000", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
	}

	/**
	 * 团队角色与成员结构
	 * 
	 * @param gRoleVo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/listModel")
	@ResponseBody
	public Result listModel(GRoleVo gRoleVo) {
		// 注入值
		// gRoleVo.setProject_id(1l);
		// gRoleVo.setIs_parent_poject("1");//1.父项目，0 子项目
		if (gRoleVo.getProject_id() == null
				|| gRoleVo.getIs_parent_poject() == null) {
			return new Result("SYS_PARAMETER", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject json = null;
		try {
			List<GRoleVo> list = gRoleService.listRelevance(gRoleVo);
			List<HashMap> gRoleModel = gRoleService.selectForListMap(gRoleVo);
			if (gRoleModel.size() > 0) {
				json = MultipleTree.getNodeModel(gRoleModel);
			}
			map.put("gRoleModel", list);
			map.put("gRolelist", json);
			return new Result(Boolean.TRUE, "成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", map);
		}
	}

	/**
	 * 团队角色结构
	 * 
	 * @param gRoleVo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/gRoleModel")
	public Result gRoleModel(String is_parent_poject, Long projectId) {
		GRoleVo gRoleVo = new GRoleVo();
		Map<String, Object> map = new HashMap<String, Object>();
		// 注入值
		gRoleVo.setProject_id(1l);
		gRoleVo.setIs_parent_poject("1");// 1.父项目，0 子项目
		if (projectId == null || is_parent_poject == null) {
			return new Result("SYS_PARAMETER", null);
		}
		try {
			List<HashMap> gRoleModel = gRoleService.selectForListMap(gRoleVo);
			map.put("gRoleModel", MultipleTree.getNodeModel(gRoleModel));
			return new Result(Boolean.TRUE, "成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", map);
		}
	}

	/**
	 * 更新团队角色管理层级
	 * 
	 * @param gRoleVo
	 * @return
	 */
	@RequestMapping("/updateSupervise")
	// @RequestMapping(value="/updateSupervise",method=RequestMethod.POST)
	public Result updateSupervise(Long id,Long for_id) {
		if (id == null || for_id == null) {
			return new Result("SYS_PARAMETER", null);
		}
		if(id==1){
			return new Result("GR0005", null);
		}
		GRoleVo gRoleVo = new GRoleVo();
		GRoleVo sVo = new GRoleVo();
		gRoleVo.setId(id);
		gRoleVo.setFor_id(for_id);
		// 查询父级
		GRoleVo parentVo = new GRoleVo();
		parentVo.setFor_id(for_id);
		do{
			sVo.setId(parentVo.getFor_id());
			parentVo = gRoleService.selectForObject(sVo);
			if(parentVo.getFor_id()==1 || parentVo.getId()==1){
				break;
			}
			if(parentVo.getFor_id().equals(id)){
				break;
			}
		}while(true);
		if(parentVo.getFor_id().equals(gRoleVo.getId())){
			return new Result("GR0006", null);
		}
		try {
			gRoleVo.setNum(parentVo.getNum()+1);
			gRoleService.updateFor_id(gRoleVo);
			return new Result(Boolean.TRUE, "管理层级更新成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
	}

	/**
	 * 更新团队角色权限
	 * 
	 * @param companyFilesVo
	 * @return
	 */
	@RequestMapping("/updateGRolePrivilege")
	@ResponseBody
	public Result updateGRolePrivilege(
			@RequestParam(required = false, value = "pids[]") List<Long> pids,
			Long roleId, Long companyFileId, HttpServletResponse response,
			CompanyFilesVo companyFilesVo) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (roleId == null || companyFileId == null || pids == null) {
			return new Result("SYS_PARAMETER", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		GRolePrivilegeVo rolePrivilege = new GRolePrivilegeVo();
		long startTime = System.currentTimeMillis();
		try {
			// 参数注入
			rolePrivilege.setRole_id(roleId);
			rolePrivilege.setCompany_file_id(companyFileId);
			// rolePrivilege.setPidList(pids);
			// rolePrivilegeService.updateRolePrivilege(rolePrivilege);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", map);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("总共花费时间为：" + (endTime - startTime) + "毫秒...");
		return new Result(Boolean.TRUE, "success", map);
	}

	/**
	 * 系统角色添加测试
	 * 
	 * @param gRoleVo
	 * @return
	 */
	@RequestMapping(value="/sysCreate",method=RequestMethod.POST)
	@ResponseBody
	public Result sysCreate(GRoleVo gRoleVo) {
		if (gRoleVo.getIs_parent_poject() == null
				|| gRoleVo.getProject_id() == null) {
			return new Result("SYS_PARAMETER", null);
		}
		try {
			gRoleService.saveSysRole(gRoleVo);
			return new Result("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("GR0002", null);
		}
	}

	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public Result create(GRoleVo gRoleVo,HttpServletRequest request) {
		if (gRoleVo.getIs_parent_poject() == null
				|| gRoleVo.getProject_id() == null) {
			return new Result("SYS_PARAMETER", null);
		}
		// 查询父级
		GRoleVo parentVo = new GRoleVo();
		parentVo.setFor_id(-1l);
		parentVo = gRoleService.selectForObject(parentVo);
		gRoleVo.setFor_id(parentVo.getId());
		gRoleVo.setNum(0);
		try {
			gRoleService.save(gRoleVo);
			return new Result("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("GR0002", null);
		}
	}

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(Long id,HttpServletRequest request) {
		if (id == null || id == null) {
			return new Result("SYS_PARAMETER", null);
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		try {
			GRoleVo gRole =new GRoleVo();
			gRole.setId(id);
			gRole = gRoleService.selectForObject(gRole);
			if(gRole==null){
				return new Result("GR0007", null);
			}
			if (gRole.getRole_p_id() != null) {
				return new Result("SYS_F12_ERROR", null);
			}
			// 参数注入
			GroupUserVo groupUserVo = new GroupUserVo();
			groupUserVo.setRole_id(gRole.getId());
			List<GroupUserVo> glist = groupUserService.selectForList(groupUserVo);
			// 判断是否存在该角色的成员
			if (glist.size() == 0) {
				gRole.setUser_id(user.getId());
				gRole.setUser_name(user.getUsername());
				gRoleService.deleteAndUpdateForId(gRole);
				return new Result("团队角色删除成功！");
			}
			return new Result("GR0003", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("GR0004", null);
		}
	}
}
