package com.cgwas.companyPosition.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyPosition.entity.CompanyPosition;
import com.cgwas.companyPosition.entity.CompanyPositionVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanyPositionService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanyPosition companyPosition);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanyPosition companyPosition);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanyPosition companyPosition);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanyPosition companyPosition);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanyPosition companyPosition);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanyPosition companyPosition);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanyPositionVo load(CompanyPosition companyPosition);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanyPositionVo selectForObject(CompanyPosition companyPosition);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanyPositionVo> selectForList(CompanyPosition companyPosition);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanyPosition companyPosition);
	
	/**
	 * 获取可管理职位列表
	 * @param page
	 * @param companyPosition
	 * @return
	 */
	public abstract List<CompanyPosition> getCompanyPositionList(Page page, CompanyPosition companyPosition,String allFlag);
	/**
	 * 获取可管理职位列表(数量)
	 * @param companyPosition
	 * @return
	 */
	public abstract Long getCompanyPositionListCount(CompanyPosition companyPosition);
	
	/**
	 * 删除职位(批量) 
	 * @param ids
	 * @param is_delete
	 */
	public abstract void batchDeleteCompanyPosition(List<Long> ids,String is_delete,Long company_id);
	/**
	 * 更改职位
	 * @param companyPosition
	 */
	public abstract void updateCompanyPositionByInfo(CompanyPosition companyPosition);

}