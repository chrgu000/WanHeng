package com.cgwas.employee.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.EmailSendUtil;
import com.cgwas.company.entity.Company;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.employee.dao.api.IEmployeeDao;
import com.cgwas.employee.entity.Employee;
import com.cgwas.employee.entity.EmployeeInfo;
import com.cgwas.employee.entity.EmployeeVo;
import com.cgwas.employee.service.api.IEmployeeService;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.service.api.IUserInfoService;

@Service
public class EmployeeService implements IEmployeeService {
	private IEmployeeDao employeeDao;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private ICompanyService companyService;

	public Serializable save(Employee employee) {
		return employeeDao.save(employee);
	}

	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

	public void deleteByExample(Employee employee) {
		employeeDao.deleteByExample(employee);
	}

	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	public void updateIgnoreNull(Employee employee) {
		employeeDao.updateIgnoreNull(employee);
	}

	public void updateByExample(Employee employee) {
		employeeDao.update("updateByExampleEmployee", employee);
	}

	public EmployeeVo load(Employee employee) {
		return (EmployeeVo) employeeDao.reload(employee);
	}

	public EmployeeVo selectForObject(Employee employee) {
		return (EmployeeVo) employeeDao.selectForObject("selectEmployee",
				employee);
	}

	public List<EmployeeVo> selectForList(Employee employee) {
		return (List<EmployeeVo>) employeeDao.selectForList("selectEmployee",
				employee);
	}

	public Page page(Page page, Employee employee) {
		return employeeDao.page(page, employee);
	}

	@Autowired
	public void setIEmployeeDao(
			@Qualifier("employeeDao") IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public List<EmployeeInfo> getEmployeeList(Long companyId,
			EmployeeInfo employeeInfo, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_Id", companyId);
		map.put("page", page);
		map.put("employeeInfo", employeeInfo);
		return (List<EmployeeInfo>) employeeDao.selectForList(
				"getEmployeeList", map);
	}

	@Override
	public void updateEmployeeOne(Employee employee) {
		employeeDao.update("updateEmployeeOne", employee);
	}

	@Override
	public void updateEmployeeBatch(List<Long> empIdList, Employee employee) {
		// 参数列表
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("section_id", employee.getSection_id());
		map.put("position_id", employee.getPosition_id());
		map.put("company_id", employee.getCompany_id());
		map.put("emp_status_id", employee.getEmp_status_id());
		map.put("begin_date", employee.getBegin_date());
		map.put("end_date", employee.getEnd_date());
		map.put("list", empIdList);
		if (employee.getEmp_status_id() == 4L) {
		// 	map.put("is_delete", "Y");
			employeeDao.delete("deleteEmployeeBeatch", map);
		}else{
			
			employeeDao.update("updateEmployeeBatch", map);
		}
		
	}

	@Override
	public EmployeeInfo getEmployeeInfoUser(Long user_id) {
		return (EmployeeInfo) employeeDao.selectForObject("getEmployeeInfo",
				user_id);
	}

	@Override
	public EmployeeInfo getEmployeeInfoAdmin(Long employee_id) {
		return (EmployeeInfo) employeeDao.selectForObject(
				"getEmployeeInfoAdmin", employee_id);
	}

	@Override
	public Long getEmployeeListCount(Long companyId, EmployeeInfo employeeInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_Id", companyId);
		map.put("employeeInfo", employeeInfo);

		return (Long) employeeDao.selectForObject("getEmployeeListCount", map);
	}

	@Override
	public String getEmployeeInfoByCompanyIdAndUserId(Employee employee,
			String projectName) {
		EmployeeInfo retn = (EmployeeInfo) employeeDao.selectForObject(
				"getEmployeeInfoByCompanyIdAndUserId", employee);
		if (retn != null) { // 发送邮件
			//
			EmailSendUtil emailSendUtil = new EmailSendUtil();
			// 得到人员信息
			List<UserInfo> retnList = userInfoService.getUserInfoById(employee
					.getUser_id());
			if (retnList == null) {
				return "用户不存在!";
			}
			UserInfo retnUserInfo = retnList.get(0);
			// 得到公司信息
			Company company = new Company();
			company.setId(employee.getCompany_id());
			Company retnCompany = companyService.selectCompanyById(company);
			if (retnUserInfo.getEmail() != null) { // 得到邮件
				Map<String, String> context = new HashMap<String, String>();
				context.put("companyName", retnCompany.getCompany_name()); // 公司名
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateStr = sdf.format(date); // 日期
				context.put("date", dateStr); // 日期
				context.put("projectName", projectName); // 日期
				// 发送邮件
				emailSendUtil.sendEmail(context, retnUserInfo.getEmail(),
						"项目邀请", "invite", null);
				return "SUCCESS";
			} else {
				return "邮箱不存在!";
			}
		} else { // 直接邀请

		}
		return "SUCCESS";
	}

	@Override
	public EmployeeInfo getEmployeeByUserIdAndCompanyId(Long user_id,
			Long company_id) {
		Employee employee = new Employee();
		employee.setCompany_id(company_id);
		employee.setUser_id(user_id);
		EmployeeInfo retn = (EmployeeInfo) employeeDao.selectForObject(
				"getEmployeeInfoByCompanyIdAndUserId", employee);
		return retn;
	}

	@Override
	public Employee checkEmployee(Long user_id, Long company_id) {
		Employee employee = new Employee();
		employee.setCompany_id(company_id);
		employee.setUser_id(user_id);
		return (Employee) employeeDao
				.selectForObject("checkEmployee", employee);
	}
}
