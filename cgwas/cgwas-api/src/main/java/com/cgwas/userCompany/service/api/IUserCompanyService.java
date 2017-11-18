package com.cgwas.userCompany.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.company.entity.Company;
import com.cgwas.employee.entity.EmployeeInfo;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.entity.UserCompanyVo;

public interface IUserCompanyService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserCompany userCompany);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserCompany userCompany);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(UserCompany userCompany);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserCompany userCompany);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(UserCompany userCompany);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(UserCompany userCompany);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserCompanyVo load(UserCompany userCompany);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract UserCompanyVo selectForObject(UserCompany userCompany);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<UserCompanyVo> selectForList(UserCompany userCompany);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserCompany userCompany);

	/**
	 * 获取公司对应用户
	 * 
	 * @param userCompany
	 * @return
	 */
	public abstract UserCompany companygetUser(Long company_id);

	/**
	 * 获取用户拥有公司
	 * 
	 * @param user_id
	 * @return
	 */
	public abstract List<UserCompany> getUserHaveCompany(Long user_id,
			Long start, Long end, UserCompany userCompany, String sortType,
			String sortColumn);

	/**
	 * 获取用户拥有公司(数量)
	 * 
	 * @param user_id
	 * @return
	 */
	public abstract Long getUserHaveCompanyCount(Long user_id,
			UserCompany userCompany);

	/**
	 * 获取用户加入公司
	 * 
	 * @param user_id
	 * @return
	 */
	public abstract List<UserCompany> getUserJoinCompany(Long user_id,
			Long start, Long end, UserCompany userCompany, String sortType,
			String sortColumn);

	/**
	 * 获取公司对应项目
	 * 
	 * @param ids
	 * @return
	 */
	public abstract List<UserCompany> getCompanyProjectNum(List<Long> ids);
	/**
	 * 获取公司对应员工
	 * @param ids
	 * @return
	 */
	public abstract List<UserCompany> getCompanyEmpNum(List<Long> ids);
	/**
	 * 获取公司对应职位
	 * @param ids
	 * @return
	 */
	public abstract List<UserCompany> getCompanyPositionNum(List<Long> ids);
	/**
	 * 获取公司对应部门
	 * @param ids
	 * @return
	 */
	public abstract List<UserCompany> getCompanySectionNum(List<Long> ids);

	/**
	 * 获取管理员可管理公司
	 * 
	 * @return
	 */
	public abstract List<UserCompany> getAdminCompany(Long start, Long end,
			UserCompany userCompany, String sortType, String sortColumn);

	/**
	 * 获取用户加入公司(数量)
	 * 
	 * @param user_id
	 * @return
	 */
	public abstract Long getUserJoinCompanyCount(Long user_id,
			UserCompany userCompany);

	/**
	 * 获取管理员可管理公司(数量)
	 * 
	 * @param user_id
	 * @return
	 */
	public abstract Long getAdminCompanyCount(UserCompany userCompany);

	/**
	 * 根据公司id得到UUID
	 * 
	 * @param company_id
	 * @return
	 */
	public String getCompanyUserUUID(Long company_id);

}