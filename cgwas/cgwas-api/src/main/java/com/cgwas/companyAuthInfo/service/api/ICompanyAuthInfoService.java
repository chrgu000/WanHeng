package com.cgwas.companyAuthInfo.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyAuthInfo.entity.AttestationCompanyAuthInfo;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfo;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfoVo;
import com.cgwas.companyAuthInfo.entity.CompanyLegalPerson;

public interface ICompanyAuthInfoService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanyAuthInfo companyAuthInfo);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanyAuthInfo companyAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanyAuthInfo companyAuthInfo);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanyAuthInfo companyAuthInfo);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanyAuthInfo companyAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanyAuthInfo companyAuthInfo);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanyAuthInfoVo load(CompanyAuthInfo companyAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanyAuthInfoVo selectForObject(
			CompanyAuthInfo companyAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanyAuthInfoVo> selectForList(
			CompanyAuthInfo companyAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanyAuthInfo companyAuthInfo);

	/**
	 * 根据公司ID得到公司认证信息
	 * 
	 * @param companyId
	 * @return
	 */
	public abstract CompanyAuthInfo getCompanyAuthInfoByCompanyId(Long companyId);

	/**
	 * 更具公司ID更改公司认证信息
	 * 
	 * @param companyAuthInfo
	 */
	public abstract void updateCompanyAuthInfoByCompanyId(
			CompanyAuthInfo companyAuthInfo);

	/**
	 * 认证公司认证信息
	 * 
	 * @param companyId
	 * @param status
	 * @return
	 */
	public abstract Serializable authenticationCompanyAuthInfo(Long companyId,
			Boolean status,Long userid);

	/**
	 * 根据公司ID得到公司认证信息
	 * 
	 * @param companyAuthInfo
	 * @return
	 */
	public abstract CompanyAuthInfo selectCompanyAuthInfoByCompanyId(
			CompanyAuthInfo companyAuthInfo);

	/**
	 * 得到可认证 公司列表
	 * 
	 * @param attestationCompanyAuthInfo
	 * @param page
	 * @return
	 */
	public abstract List<AttestationCompanyAuthInfo> getCompanyAuthInfoList(
			AttestationCompanyAuthInfo attestationCompanyAuthInfo, Page page);

	/**
	 * 得到可认证 公司列表(数量)
	 * 
	 * @param attestationCompanyAuthInfo
	 * @return
	 */
	public abstract long getCompanyAuthInfoListCount(
			AttestationCompanyAuthInfo attestationCompanyAuthInfo);
	
	/**
	 * 获取法人信息 
	 * @param company_id
	 * @return
	 */
	public abstract CompanyLegalPerson getCompanyLegalPerson(Long company_id);
}