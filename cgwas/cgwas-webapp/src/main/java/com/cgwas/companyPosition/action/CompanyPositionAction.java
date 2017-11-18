package com.cgwas.companyPosition.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.companyPosition.entity.CompanyPosition;
import com.cgwas.companyPosition.entity.CompanyPositionVo;
import com.cgwas.companyPosition.service.api.ICompanyPositionService;
import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.role.service.api.IRoleService;
import com.cgwas.rolePrivilege.entity.RolePrivilegeVo;
import com.cgwas.rolePrivilege.service.api.IRolePrivilegeService;

/**
 * 
 * @author yubangqiong
 * 
 */
@Controller
@RequestMapping("cgwas/companyPositionAction")
public class CompanyPositionAction {
	private ICompanyPositionService companyPositionService;
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IRolePrivilegeService rolePrivilegeService;
	private static final Logger LOG = Logger
			.getLogger(CompanyPositionAction.class);

	@RequestMapping("/index")
	public String index() {
		return "/dsoul/companyPosition/companyPosition_index.jsp";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	public Result list(String data, HttpServletRequest request, Model model) {
		Date startTime = new Date();
		LOG.info("------------------------------------加载companyPositionAction.list()方法--------------------------------------");
		Map<String, Object> map = new HashMap<String, Object>();
		data = "{'page':{'pageSize':'3','pageNo':'0'},'object':{'company_id':17}}";
		try {
			JSONObject json = JSON.parseObject(data);
			JSON object = (JSON) JSON.toJSON(json.get("object"));
			CompanyPositionVo companyPositionVo = JSONObject.toJavaObject(
					object, CompanyPositionVo.class);
			// TODO
			// User user=(User)request.getSession().getAttribute("loginUser");
			// 根据登录用户的id查询公司文件(夹)列表
			// if(user!=null){
			// companyPositionVo.setUser_id(user.getId());
			// }

			Map<String, String> params = (Map<String, String>) json.get("page");
			Page page = PageUtils.createPage(params);
			page = companyPositionService.page(page, companyPositionVo);
			map = new HashMap<String, Object>();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
			Date endTime = new Date();
			LOG.info("接口请求时间：" + (endTime.getTime() - startTime.getTime())
					+ "毫秒");
			return new Result(Boolean.TRUE, "MC00000", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
	}

	/**
	 * 职位权限列表
	 * 
	 * @param CompanyPositionVo
	 * @return
	 */
	@RequestMapping("/positionPrivilegeList")
	public @ResponseBody
	Result positionPrivilegeList(CompanyPositionVo companyPositionVo,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		PrivilegeInfoVo privilegeInfoVo = new PrivilegeInfoVo();
		long startTime = System.currentTimeMillis();
		try {
			//
			List<CompanyPositionVo> companyPositionVoList = companyPositionService
					.selectForList(companyPositionVo);
			// 根据菜单显示相关权限
			//privilegeInfoVo.setMenu_id(1l);
			List<PrivilegeInfoVo> privilegeList = privilegeInfoService.selectForList(privilegeInfoVo);
			map.put("privilegeList", privilegeList);
			map.put("companyPositionVoList", companyPositionVoList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Result("0000001", map);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("总共花费时间为：" + (endTime - startTime) + "毫秒...");
		return new Result(Boolean.TRUE, "success", map);
	}

	@RequestMapping("/create_page")
	public String create_page(Model model) {
		CompanyPosition companyPosition = new CompanyPosition();
		model.addAttribute("companyPosition", companyPosition);
		return "/dsoul/companyPosition/companyPosition_create.jsp";
	}

	@RequestMapping("/updatePositionPrivilege")
	@ResponseBody
	public Result updatePositionPrivilege(
			@RequestParam(required = false, value = "pids[]") List<Long> pids,
			Long roleId, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		if(roleId==null || pids==null){
			return new Result("SYS_PARAMETER",null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		RolePrivilegeVo rolePrivilege = new RolePrivilegeVo();
		try {
			rolePrivilege.setRole_id(roleId);
			rolePrivilege.setRole_type("POSITION_TYPE");
			rolePrivilege.setPidList(pids);
			rolePrivilegeService.updateRolePrivilege(rolePrivilege);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Result(Boolean.TRUE, "success", map);
	}

	@RequestMapping("/detail_page")
	public String detail_page(CompanyPosition companyPosition, Model model) {
		companyPosition = companyPositionService.load(companyPosition);
		model.addAttribute("companyPosition", companyPosition);
		return "/dsoul/companyPosition/companyPosition_detail.jsp";
	}

	@RequestMapping("/create")
	public @ResponseBody
	Result create(CompanyPosition companyPosition) {
		if (companyPosition != null) {
			companyPositionService.save(companyPosition);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/update")
	public @ResponseBody
	Result update(CompanyPosition companyPosition) {
		if (companyPosition != null) {
			companyPositionService.updateIgnoreNull(companyPosition);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/delete")
	public @ResponseBody
	Result delete(CompanyPosition companyPosition) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		companyPositionService.delete(companyPosition);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCompanyPositionService(
			@Qualifier("companyPositionService") ICompanyPositionService companyPositionService) {
		this.companyPositionService = companyPositionService;
	}
}
