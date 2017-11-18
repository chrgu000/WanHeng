package com.cgwas.companyFilesUser.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyFilesUser.entity.CompanyFilesUser;
import com.cgwas.companyFilesUser.entity.CompanyFilesUserVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanyFilesUserService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanyFilesUser companyFilesUser);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanyFilesUser companyFilesUser);
	
	/**
	 * 删除多条记录
	 */
	public abstract void deleteAll(List<Long> ids);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanyFilesUser companyFilesUser);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanyFilesUser companyFilesUser);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanyFilesUser companyFilesUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanyFilesUser companyFilesUser);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanyFilesUserVo load(CompanyFilesUser companyFilesUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanyFilesUserVo selectForObject(CompanyFilesUser companyFilesUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanyFilesUserVo> selectForList(CompanyFilesUser companyFilesUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanyFilesUser companyFilesUser);
	
	/**
	 * 根据制作者id和父文件id查询列表
	 */
	public abstract List<CompanyFilesUserVo> selectCompanyFilesUserByCheck(CompanyFilesUserVo companyFilesUserVo);

}