package com.cgwas.arbitrateUserInfo.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.arbitrateUserInfo.entity.ArbitrateUserInfo;
import com.cgwas.arbitrateUserInfo.entity.ArbitrateUserInfoVo;
import com.cgwas.arbitrateUserInfo.entity.CompanyArbitrateInfo;
import com.cgwas.arbitrateUserInfo.entity.UserArbitrateInfo;
import com.cgwas.common.dataaccess.api.Page;

public interface IArbitrateUserInfoService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ArbitrateUserInfo arbitrateUserInfo);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ArbitrateUserInfo arbitrateUserInfo);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(ArbitrateUserInfo arbitrateUserInfo);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ArbitrateUserInfo arbitrateUserInfo);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(ArbitrateUserInfo arbitrateUserInfo);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(ArbitrateUserInfo arbitrateUserInfo);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ArbitrateUserInfoVo load(ArbitrateUserInfo arbitrateUserInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ArbitrateUserInfoVo selectForObject(
			ArbitrateUserInfo arbitrateUserInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ArbitrateUserInfoVo> selectForList(
			ArbitrateUserInfo arbitrateUserInfo);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ArbitrateUserInfo arbitrateUserInfo);
	
	/**
	 * 获取用户仲裁信息
	 * @param arbitrateInfo
	 * @return
	 */
	public abstract List<UserArbitrateInfo>  getUserArbitrateInfo(UserArbitrateInfo userArbitrateInfo);
	/**
	 * 获取公司仲裁信息
	 * @param arbitrateInfo
	 * @return
	 */
	public abstract List<CompanyArbitrateInfo> getCompanyArbitrateInfo(CompanyArbitrateInfo arbitrateInfo);
	
	/**
	 * 更改仲裁状态
	 * @param arbitrateUserInfo
	 */
	void updateIsPass(ArbitrateUserInfo arbitrateUserInfo);

}