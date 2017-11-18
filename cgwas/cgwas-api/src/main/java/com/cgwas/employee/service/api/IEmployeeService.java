package com.cgwas.employee.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.employee.entity.Employee;
import com.cgwas.employee.entity.EmployeeInfo;
import com.cgwas.employee.entity.EmployeeVo;

public interface IEmployeeService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Employee employee);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Employee employee);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Employee employee);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Employee employee);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Employee employee);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Employee employee);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract EmployeeVo load(Employee employee);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract EmployeeVo selectForObject(Employee employee);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<EmployeeVo> selectForList(Employee employee);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Employee employee);

	/**
	 * 根据公司ID获取员工列表
	 * 
	 * @param companyId
	 * @return
	 */
	public abstract List<EmployeeInfo> getEmployeeList(Long companyId,EmployeeInfo employeeInfo,  Page page);

	/**
	 * 更改员工信息(单个)
	 * 
	 * @param employee
	 */
	public abstract void updateEmployeeOne(Employee employee);

	/**
	 * 更改员工信息(批量)
	 * @param empIdList
	 * @param employee
	 */
	public abstract void updateEmployeeBatch(List<Long> empIdList,Employee employee);
	/**
	 * 获取员工信息（自己）
	 * @param user_Id
	 * @return
	 */
	public abstract EmployeeInfo getEmployeeInfoUser(Long user_Id);
	/**
	 * 获取员工信息（管理员）
	 * @param user_Id
	 * @return
	 */
	public abstract EmployeeInfo getEmployeeInfoAdmin(Long employee_Id);
	/**
	 * 根据公司ID获取员工列表(数量)
	 * @param employeeInfo
	 * @return
	 */
	public abstract Long getEmployeeListCount(Long companyId,EmployeeInfo employeeInfo);
	/**
	 * 根据公司ID和用户Id得到员工信息
	 * @param employee
	 * @return
	 */
	public abstract String getEmployeeInfoByCompanyIdAndUserId(Employee employee,String projectName);
	/**
	 * 查询员工是否存在公司内
	 * @param user_id
	 * @param company_id
	 * @return
	 */
	public abstract EmployeeInfo getEmployeeByUserIdAndCompanyId(Long user_id,Long company_id);
	/**
	 * 根据公司Id和用户id得到信息
	 * @param user_id
	 * @param company_id
	 * @return
	 */
	public abstract Employee checkEmployee(Long user_id,Long company_id);
}