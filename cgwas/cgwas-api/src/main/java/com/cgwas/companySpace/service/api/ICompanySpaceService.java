package com.cgwas.companySpace.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companySpace.entity.CompanySpace;
import com.cgwas.companySpace.entity.CompanySpaceVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanySpaceService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanySpace companySpace);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanySpace companySpace);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanySpace companySpace);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanySpace companySpace);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanySpace companySpace);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanySpace companySpace);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanySpaceVo load(CompanySpace companySpace);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanySpaceVo selectForObject(CompanySpace companySpace);
	
	
	/**
	 * 获取系统预设初始空间容量
	 * @param companySpace
	 * @return
	 */
	public abstract CompanySpaceVo getInitSpace(CompanySpace companySpace);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanySpaceVo> selectForList(CompanySpace companySpace);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanySpace companySpace);

}