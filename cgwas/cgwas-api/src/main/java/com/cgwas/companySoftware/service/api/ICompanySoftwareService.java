package com.cgwas.companySoftware.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companySoftware.entity.CompanySoftware;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
/**
*  Author yangjun
*/
public interface ICompanySoftwareService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanySoftware companySoftware);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanySoftware companySoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanySoftware companySoftware);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanySoftware companySoftware);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanySoftware companySoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanySoftware companySoftware);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanySoftwareVo load(CompanySoftware companySoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanySoftwareVo selectForObject(CompanySoftware companySoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanySoftwareVo> selectForList(CompanySoftware companySoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanySoftware companySoftware);

}