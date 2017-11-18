package com.cgwas.companySection.action;

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
import com.cgwas.companySection.entity.CompanySection;
import com.cgwas.companySection.entity.CompanySectionVo;
import com.cgwas.companySection.service.api.ICompanySectionService;
import com.cgwas.role.service.api.IRoleService;
import com.cgwas.rolePrivilege.entity.RolePrivilegeVo;
import com.cgwas.rolePrivilege.service.api.IRolePrivilegeService;

/**
 * 
 * @author yubangqiong
 * 
 */
@Controller
@RequestMapping("cgwas/companySectionAction")
public class CompanySectionAction {
	private ICompanySectionService companySectionService;
	
	private static final Logger LOG = Logger
			.getLogger(CompanySectionAction.class);

	@RequestMapping("/index")
	public String index() {
		return "/dsoul/companySection/companySection_index.jsp";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	public Result list(String data, HttpServletRequest request, Model model) {
		Date startTime = new Date();
		LOG.info("------------------------------------加载companySectionAction.list()方法--------------------------------------");
		Map<String, Object> map = new HashMap<String, Object>();
		data = "{'page':{'pageSize':'3','pageNo':'0'},'object':{'company_id':17}}";
		try {
			JSONObject json = JSON.parseObject(data);
			JSON object = (JSON) JSON.toJSON(json.get("object"));
			CompanySectionVo companySectionVo = JSONObject.toJavaObject(object, CompanySectionVo.class);
			// TODO
			// User user=(User)request.getSession().getAttribute("loginUser");
			// 根据登录用户的id查询公司文件(夹)列表
			// if(user!=null){
			// companySectionVo.setUser_id(user.getId());
			// }

			Map<String, String> params = (Map<String, String>) json.get("page");
			Page page = PageUtils.createPage(params);
			page = companySectionService.page(page, companySectionVo);
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

	@RequestMapping("/create_page")
	public String create_page(Model model) {
		CompanySection companySection = new CompanySection();
		model.addAttribute("companySection", companySection);
		return "/dsoul/companySection/companySection_create.jsp";
	}

	@RequestMapping("/detail_page")
	public String detail_page(CompanySection companySection, Model model) {
		companySection = companySectionService.load(companySection);
		model.addAttribute("companySection", companySection);
		return "/dsoul/companySection/companySection_detail.jsp";
	}

	@RequestMapping("/create")
	public @ResponseBody
	Result create(CompanySection companySection) {
		if (companySection != null) {
			companySectionService.save(companySection);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/update")
	public @ResponseBody
	Result update(CompanySection companySection) {
		if (companySection != null) {
			companySectionService.updateIgnoreNull(companySection);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/delete")
	public @ResponseBody
	Result delete(CompanySection companySection) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		companySectionService.delete(companySection);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCompanySectionService(
			@Qualifier("companySectionService") ICompanySectionService companySectionService) {
		this.companySectionService = companySectionService;
	}
}
