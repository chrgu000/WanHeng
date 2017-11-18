package com.cgwas.forbid.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.company.entity.Company;
import com.cgwas.forbid.entity.CompanyForbid;
import com.cgwas.forbid.entity.Forbid;
import com.cgwas.forbid.entity.ForbidCompany;
import com.cgwas.forbid.entity.ForbidUser;
import com.cgwas.forbid.entity.ForbidVo;
import com.cgwas.forbid.entity.UserForbid;
import com.cgwas.user.entity.User;

public interface IForbidService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Forbid forbid);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Forbid forbid);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Forbid forbid);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Forbid forbid);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Forbid forbid);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Forbid forbid);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ForbidVo load(Forbid forbid);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ForbidVo selectForObject(Forbid forbid);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ForbidVo> selectForList(Forbid forbid);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Forbid forbid);

	/**
	 * 获取用户被禁记录
	 * 
	 * @param userID
	 * @return
	 */
	public abstract List<UserForbid> getUserForbidList(Long userId);

	/**
	 * 得到禁用公司列表
	 * 
	 * @return
	 */
	public abstract List<ForbidCompany> getCompanyListForForbid(ForbidCompany forbidCompany,Page page);

	/**
	 * 得到禁用公司列表(数量)
	 * 
	 * @return
	 */
	public abstract Long getCompanyListForForbidCount(ForbidCompany forbidCompany);

	/**
	 * 得到禁用人员列表
	 * 
	 * @param page
	 * @return
	 */
	public abstract List<ForbidUser> getUserListForForbid(ForbidUser forbidUser,Page page);

	/**
	 * 得到禁用人员列表(数量)
	 * @param user
	 * @return
	 */
	public abstract Long getUserListForForbidCount(ForbidUser forbidUser);
	
	
	
	/**
	 * 获得最长的禁用日期
	 * 
	 * @param forbid
	 * @return
	 */
	public abstract Forbid getNewForbid(Forbid forbid);

	/**
	 * 根据ID得到公司禁用记录
	 * 
	 * @param conpany_id
	 * @return
	 */
	public abstract List<CompanyForbid> getConpanyForbidList(Long company_id);

}