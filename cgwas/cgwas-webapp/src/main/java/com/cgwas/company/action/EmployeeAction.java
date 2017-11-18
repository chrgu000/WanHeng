package com.cgwas.company.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.companyEvaluation.service.api.ICompanyEvaluationService;
import com.cgwas.companyPosition.entity.CompanyPosition;
import com.cgwas.companyPosition.service.api.ICompanyPositionService;
import com.cgwas.companySection.entity.CompanySection;
import com.cgwas.companySection.service.api.ICompanySectionService;
import com.cgwas.employee.entity.Employee;
import com.cgwas.employee.entity.EmployeeInfo;
import com.cgwas.employee.service.api.IEmployeeService;
import com.cgwas.user.entity.User;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;

@Controller
@RequestMapping("cgwas/companyAction")
public class EmployeeAction {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private ICompanyPositionService companyPositionService = null;
	@Autowired
	private ICompanySectionService companySectionService = null;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private ICompanyEvaluationService companyEvaluationService;

	/**
	 * 公司添加员工（R47）
	 * 
	 * @param employee
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEmployee")
	public Result addEmployee(Employee employee, HttpServletRequest request,
			HttpServletResponse response) {

		Long selectedCompany = this.getCompanyId(request);
		employee.setCompany_id(selectedCompany);
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}

		employee.setIs_delete("N");

		// 检查唯一性
		Employee retn = employeeService.checkEmployee(employee.getUser_id(),
				selectedCompany);

		if (retn != null) {
			if (retn.getEmp_status_id() == 2L) { // 若已离职则改变状态
				employee.setEmp_status_id(employee.getEmp_status_id());
				employeeService.updateEmployeeOne(employee); // (ids, employee);
				return new Result(Boolean.TRUE, "欢迎回来!", null);

			} else {

				return new Result("RY0072", null); // 无选中公司
			}

		}

		employeeService.save(employee);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 得到员工列表（R48）
	 * 
	 * @param company_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getEmployeeList")
	public Result getEmployeeList(Long start, String pageFlag,
			EmployeeInfo employeeInfo, String sortColumn, String sortType,
			Integer limit, HttpServletRequest request,
			HttpServletResponse response) {
		Integer pageMax = 10;
		if (!(limit == null || limit <= 0)) { // 判断是否有页数限制
			pageMax = limit;
		}

		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}

		Long selectedCompany = this.getCompanyId(request);
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			page.setLimit(Long.valueOf(pageMax));
			total = employeeService.getEmployeeListCount(selectedCompany,
					employeeInfo);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(pageMax * (start - 1));// 获取开始页面
			page.setLimit(Long.valueOf(pageMax));
			total = employeeService.getEmployeeListCount(selectedCompany,
					employeeInfo);
			// 设置记录总数
			page.setTotal(total);
			if ("next".equals(pageFlag)) { // 上一页下一页
				page.nextPage();
			} else {
				page.prePage();
			}
		}
		// 排序参数
		page.setSortColumn(sortColumn);
		page.setSortType(sortType);
		List<EmployeeInfo> employeeList = employeeService.getEmployeeList(
				selectedCompany, employeeInfo, page); // 得到员工列表
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("employeeList", employeeList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 更改员工信息(单个)(R49)
	 * 
	 * @param employee
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateEmployee")
	public Result updateEmployee(Employee employee, HttpServletRequest request,
			HttpServletResponse response) {
		employeeService.updateEmployeeOne(employee); // 更改员工信息
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 更改员工信息(批量)(R50)
	 * 
	 * @param ids
	 * @param employee
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateEmployeeBatch")
	public Result updateEmployeeBatch(String ids, Employee employee,
			HttpServletRequest request, HttpServletResponse response) {
		String[] str1 = ids.split(",");// 将String[] 转换为long[]
		List<Long> str2 = new ArrayList<Long>();

		for (int i = 0; i < str1.length; i++) {
			str2.add(Long.valueOf(str1[i]));
		}
		if (str2.size() == 0) {
			return new Result("RY0018", null); // 数组为空
		}
		employeeService.updateEmployeeBatch(str2, employee);
		return new Result(Boolean.TRUE, "成功!", null);

	}

	/**
	 * 获取员工信息（自己）（R51）
	 * 
	 * @param user_id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getEmployeeInfoUser")
	public Result getEmployeeInfoUser(HttpServletRequest request,
			HttpServletResponse response) {
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 得到员工信息
		EmployeeInfo retn = employeeService.getEmployeeInfoUser(loginUser
				.getId());
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 获取员工信息（管理者）（R52）
	 * 
	 * @param employee_id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getEmployeeInfoAdmin")
	public Result getEmployeeInfoAdmin(Long employee_id,
			HttpServletRequest request, HttpServletResponse response) {
		EmployeeInfo retn = employeeService.getEmployeeInfoAdmin(employee_id);
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 获取职称列表（管理员）（R89）
	 * 
	 * @param start
	 * @param pageFlag
	 * @param companyPosition
	 * @param sortColumn
	 * @param sortType
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCompanyPositionListForAdmin")
	public Result getCompanyPositionListForAdmin(Long start, String pageFlag,
			String allFlag, CompanyPosition companyPosition, String sortColumn,
			String sortType, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取公司id
		HttpSession session = request.getSession();
		// 获取session选中公司
		Long selectedCompany = (Long) session.getAttribute("sessionCompany");
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}
		companyPosition.setCompany_id(selectedCompany);
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = companyPositionService
					.getCompanyPositionListCount(companyPosition);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = companyPositionService
					.getCompanyPositionListCount(companyPosition);
			// 设置记录总数
			page.setTotal(total);
			if ("next".equals(pageFlag)) { // 上一页下一页
				page.nextPage();
			} else {
				page.prePage();
			}
		}
		// 排序参数
		page.setSortColumn(sortColumn);
		page.setSortType(sortType);
		// 获取公司列表
		List<CompanyPosition> positionList = companyPositionService
				.getCompanyPositionList(page, companyPosition, allFlag);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("positionList", positionList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 获取部门列表（R91）
	 * 
	 * @param start
	 * @param pageFlag
	 * @param companySection
	 * @param sortColumn
	 * @param sortType
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getcompanySectionListForAdmin")
	public Result getCompanySectionListForAdmin(Long start, String pageFlag,
			CompanySection companySection, String sortColumn, String sortType,
			String allFlag, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 获取session选中公司
		Long selectedCompany = (Long) session.getAttribute("sessionCompany");
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}
		companySection.setCompany_id(selectedCompany);
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = companySectionService
					.getcompanySectionListCount(companySection);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = companySectionService
					.getcompanySectionListCount(companySection);
			// 设置记录总数
			page.setTotal(total);
			if ("next".equals(pageFlag)) { // 上一页下一页
				page.nextPage();
			} else {
				page.prePage();
			}
		}
		// 排序参数
		page.setSortColumn(sortColumn);
		page.setSortType(sortType);
		// 获取公司列表
		List<CompanySection> sectionList = companySectionService
				.getcompanySectionList(page, companySection, allFlag);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("sectionList", sectionList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 批量删除公司职称（R92）
	 * 
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("batchDeleteCompanyPosition")
	public Result batchDeleteCompanyPosition(String ids,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 获取session选中公司
		Long selectedCompany = (Long) session.getAttribute("sessionCompany");
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}

		String[] str1 = ids.split(",");// 将String[] 转换为long[]
		List<Long> str2 = new ArrayList<Long>();

		for (int i = 0; i < str1.length; i++) {
			str2.add(Long.valueOf(str1[i]));
		}
		if (str2.size() == 0) {
			return new Result("RY0018", null); // 数组为空
		}
		companyPositionService.batchDeleteCompanyPosition(str2, "Y",
				selectedCompany);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 批量删除公司部门（R93）
	 * 
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("batchDeleteCompanySection")
	public Result batchDeleteCompanySection(String ids,
			HttpServletRequest request, HttpServletResponse response) {
		String[] str1 = ids.split(",");// 将String[] 转换为long[]
		HttpSession session = request.getSession();
		List<Long> str2 = new ArrayList<Long>();
		// 获取session选中公司
		Long selectedCompany = (Long) session.getAttribute("sessionCompany");
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}
		for (int i = 0; i < str1.length; i++) {
			str2.add(Long.valueOf(str1[i]));
		}
		if (str2.size() == 0) {
			return new Result("RY0018", null); // 数组为空
		}
		companySectionService.batchDeleteCompanySection(str2, "Y",
				selectedCompany);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 添加公司部门（R94）
	 * 
	 * @param companySection
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addCompanySection")
	public Result addCompanySection(CompanySection companySection,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		companySection.setIs_delete("N");
		// 获取session选中公司
		Long selectedCompany = (Long) session.getAttribute("sessionCompany");
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}
		companySection.setCompany_id(selectedCompany);
		companySectionService.save(companySection);
		return new Result(Boolean.TRUE, "成功!", null);

	}

	/**
	 * 添加公司职称（R95）
	 * 
	 * @param companySection
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addCompanyPosition")
	public Result addCompanyPosition(CompanyPosition companyPosition,
			HttpServletRequest request, HttpServletResponse response) {
		companyPosition.setIs_delete("N");
		HttpSession session = request.getSession();
		// 获取session选中公司
		Long selectedCompany = (Long) session.getAttribute("sessionCompany");
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}
		companyPosition.setCompany_id(selectedCompany);
		companyPositionService.save(companyPosition);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 更改公司部门(R96)
	 * 
	 * @param companySection
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCompanySection")
	public Result updateCompanySection(CompanySection companySection,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 获取session选中公司
		Long selectedCompany = (Long) session.getAttribute("sessionCompany");
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}
		companySection.setCompany_id(selectedCompany);
		companySectionService.updateCompanySectionByInfo(companySection);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 更改公司职称（R97）
	 * 
	 * @param companyPosition
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCompanyPosition")
	public Result updateCompanyPosition(CompanyPosition companyPosition,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 获取session选中公司
		Long selectedCompany = (Long) session.getAttribute("sessionCompany");
		if (selectedCompany == null) {

			return new Result("RY0065", null); // 无选中公司
		}
		companyPosition.setCompany_id(selectedCompany);
		companyPositionService.updateCompanyPositionByInfo(companyPosition);
		return new Result(Boolean.TRUE, "成功!", null);

	}

	/**
	 * 查询用户是否为该公司成员（R98）
	 * 
	 * @param user_id
	 * @param company_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getEmployeeByUserIdAndCompanyId")
	public Result getEmployeeByUserIdAndCompanyId(Long user_id, Long company_id) {

		EmployeeInfo info = employeeService.getEmployeeByUserIdAndCompanyId(
				user_id, company_id);
		// 获取该公司法人信息
		UserCompany company = userCompanyService.companygetUser(company_id);

		if (company.getUse_id() == user_id) { // 若是本公司法人则加入
			info = new EmployeeInfo();
		}
		return new Result(Boolean.TRUE, "成功!", info);
	}

	/**
	 * 查看好中差评论数(公司)（R142）
	 * 
	 * @param company_id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getGCBEvaluationCountCompany")
	public Result getGCBEvaluationCountCompany(Long company_id,
			HttpServletRequest request, HttpServletResponse response) {

		Long good = companyEvaluationService.getGCBEvaluationCountCompany(
				company_id, 1L);
		Long comm = companyEvaluationService.getGCBEvaluationCountCompany(
				company_id, 2L);
		Long bad = companyEvaluationService.getGCBEvaluationCountCompany(
				company_id, 3L);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("good", good == null ? 0 : good);
		map.put("comm", comm == null ? 0 : comm);
		map.put("bad", bad == null ? 0 : bad);

		return new Result(Boolean.TRUE, "成功!", map);
	}

	/**
	 * 检测登录状态
	 * 
	 * @param request
	 * @return
	 */
	private User getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(); // session
		return (User) session.getAttribute("loginUser");
	}

	private Long getCompanyId(HttpServletRequest request) {
		HttpSession session = request.getSession(); // session
		// 获取session选中公司
		Long selectedCompany = (Long) session.getAttribute("sessionCompany");
		return selectedCompany;
	}
}
