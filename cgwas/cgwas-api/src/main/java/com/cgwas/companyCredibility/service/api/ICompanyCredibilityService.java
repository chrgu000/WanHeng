package com.cgwas.companyCredibility.service.api;



import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyCredibility.entity.CompanyCredibility;
import com.cgwas.companyCredibility.entity.CompanyCredibilityVo;


public interface ICompanyCredibilityService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanyCredibility companyCredibility);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanyCredibility companyCredibility);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanyCredibility companyCredibility);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanyCredibility companyCredibility);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanyCredibility companyCredibility);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanyCredibility companyCredibility);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanyCredibilityVo load(CompanyCredibility companyCredibility);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanyCredibilityVo selectForObject(CompanyCredibility companyCredibility);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanyCredibilityVo> selectForList(CompanyCredibility companyCredibility);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanyCredibility companyCredibility);
	/**
	 * 根据公司id得到信誉 信息
	 * @param company_id
	 * @return
	 */
	public abstract CompanyCredibility GetCompanyCredibilityByCompanyId(Long company_id);

}