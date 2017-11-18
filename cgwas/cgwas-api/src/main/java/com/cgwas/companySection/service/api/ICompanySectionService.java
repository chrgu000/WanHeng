package com.cgwas.companySection.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companySection.entity.CompanySection;
import com.cgwas.companySection.entity.CompanySectionVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanySectionService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanySection companySection);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanySection companySection);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanySection companySection);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanySection companySection);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanySection companySection);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanySection companySection);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanySectionVo load(CompanySection companySection);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanySectionVo selectForObject(CompanySection companySection);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanySectionVo> selectForList(CompanySection companySection);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanySection companySection);
	
	/**
	 *  获取可管理职位列表
	 * @param page
	 * @param companySection
	 * @return
	 */
	public abstract List<CompanySection> getcompanySectionList(Page page, CompanySection companySection,String allFlag);
	/**
	 *  获取可管理职位列表(数量)
	 * @param companySection
	 * @return
	 */
	public abstract Long getcompanySectionListCount(CompanySection companySection);
	/**
	 * 删除部门(批量) 
	 * @param ids
	 * @param is_delete
	 */
	public abstract void batchDeleteCompanySection(List<Long> ids,String is_delete,Long company_id);
	/**
	 * 更改部门
	 * @param companySection
	 */
	public abstract void updateCompanySectionByInfo(CompanySection companySection);

}