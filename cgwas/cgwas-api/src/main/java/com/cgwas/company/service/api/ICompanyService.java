package com.cgwas.company.service.api;

import java.util.List;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.company.entity.AdminCompany;
import com.cgwas.company.entity.Company;
import com.cgwas.company.entity.CompanyVo;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfo;
import com.cgwas.sector.entity.Sector;
import com.cgwas.userCompany.entity.UserCompany;

public interface ICompanyService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Company company);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Company company);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Company company);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Company company);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Company company);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Company company);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanyVo load(Company company);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanyVo selectForObject(Company company);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanyVo> selectForList(Company company);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Company company);

	/**
	 * 用户添加公司
	 * 
	 * @param userId
	 * @param company
	 */
	public Result saveCompany(Long userId, String relation, Company company,
			Sector sector, CompanyAuthInfo companyAuthInfo,String uuid,StringBuffer charter_path);
	
	/**
	 * 用户添加公司(文件表单)
	 * @param userId
	 * @param relation
	 * @param company
	 * @param sector
	 * @param companyAuthInfo
	 * @param uuid
	 * @param charter_path
	 * @return
	 */
	public Result saveCompanyA(Long userId, String relation, Company company,
			Sector sector, CompanyAuthInfo companyAuthInfo,String uuid,MultipartFile charter_path);
	/**
	 * 更改公司基本信息
	 * 
	 * @param company
	 */
	public abstract void updateCompanyByCompanyId(Company company);

	/**
	 * 根据id得到公司基本信息
	 * 
	 * @param company
	 * @return
	 */
	public abstract Company selectCompanyById(Company company);

	/**
	 * 批量删除公司
	 * 
	 * @param ids
	 */
	public abstract void batchDeleteCompany(List<Long> companyIdList);

	/**
	 * 根据用户id获取公司信息
	 * 
	 * @param user_id
	 * @return
	 */
	public abstract List<Company> selectCompanyByUserId(long user_id);

	/**
	 * 得到管理公司列表
	 * 
	 * @param adminCompany
	 * @param page
	 * @return
	 */
	public abstract List<AdminCompany> getAdminCompanyList(
			AdminCompany adminCompany, Page page);

	/**
	 * 得到管理公司列表（数量）
	 * 
	 * @param adminCompany
	 * @return
	 */
	public abstract Long getAdminCompanyListCount(AdminCompany adminCompany);

	/**
	 * 获取用户拥有公司关系
	 * @param user_id
	 * @return
	 */
	public abstract List<UserCompany> getHaveCompanyByUserId(Long user_id,Long status);
}