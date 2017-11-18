package com.cgwas.companyFiles.service.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyFiles.entity.CompanyFiles;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFilesUser.entity.CompanyFilesUser;
import com.cgwas.companyFilesUser.entity.CompanyFilesUserVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ICompanyFilesService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanyFiles companyFiles);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanyFiles companyFiles);
	
	/**
	 * 按对象中的主键进行删除多个，如果是DRDS，还需要添加拆分键
	 */
	public abstract void deleteAll(CompanyFiles companyFiles);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanyFiles companyFiles);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanyFiles companyFiles);
	
	/**
	 * 制作者接收的任务审核通过后给制作者添加相应的权限
	 */
	public abstract Boolean addGroleProvilege(CompanyFilesVo companyFiles);
	
	/**
	 * 制作者放弃任务审核通过后或者完成任务后移除相应的权限
	 */
	public abstract Boolean removeGroleProvilege(CompanyFilesVo companyFiles);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanyFiles companyFiles);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanyFiles companyFiles);
	
	/**
	 * 根据url删除文件表记录以及OSS信息
	 */
	public abstract void deleteRelationByFileUrl(String file_url);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanyFilesVo load(CompanyFiles companyFiles);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanyFilesVo selectForObject(CompanyFiles companyFiles);
	
	/**
	 * 根据条件获取移动弹框的文件夹列表
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<Map> selectCompanyFilesMap(CompanyFiles companyFiles);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanyFilesVo> selectList(CompanyFiles companyFiles);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanyFilesVo> selectForList(CompanyFiles companyFiles);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 * @param companyFilesUser
	 * @return
	 */
	public abstract List<CompanyFilesUserVo> selectCpmpanyFilesUsers(CompanyFilesUser companyFilesUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanyFiles companyFiles);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page pageForCompany(Page page, CompanyFiles companyFiles);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page pageForGRole(Page page, CompanyFiles companyFiles);
	
	/**
	 * 根据公司id获取公司的有效容量
	 */
	public abstract Long getTotalTolume(Long company_id);
}