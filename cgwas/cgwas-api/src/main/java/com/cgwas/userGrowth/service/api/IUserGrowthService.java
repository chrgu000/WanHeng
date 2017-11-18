package com.cgwas.userGrowth.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyGrowth.entity.CompanyGrowth;
import com.cgwas.userGrowth.entity.CompanyForGrowth;
import com.cgwas.userGrowth.entity.UserForGrowth;
import com.cgwas.userGrowth.entity.UserGrowth;
import com.cgwas.userGrowth.entity.UserGrowthVo;

public interface IUserGrowthService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserGrowth userGrowth);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserGrowth userGrowth);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(UserGrowth userGrowth);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserGrowth userGrowth);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(UserGrowth userGrowth);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(UserGrowth userGrowth);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserGrowthVo load(UserGrowth userGrowth);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract UserGrowthVo selectForObject(UserGrowth userGrowth);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<UserGrowthVo> selectForList(UserGrowth userGrowth);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserGrowth userGrowth);

	/**
	 * 根据用户ID获取成长信息
	 * 
	 * @param userId
	 * @return
	 */
	public abstract UserGrowth getUserGrowthByUserId(Long userId);

	/**
	 * 奖罚用户积分
	 * 
	 * @param action
	 * @param GrowthNum
	 * @param userId
	 */
	public void reduceOrAddGrowth(String action, Integer GrowthNum, Long userId,Integer prestige);

	/**
	 * 获取可修改用户积分列表
	 * 
	 * @param page
	 * @return
	 */
	public abstract List<UserForGrowth> getUserListForGrowth(UserForGrowth growth,Page page);

	/**
	 * 获取可修改公司积分列表
	 * 
	 * @param page
	 * @return
	 */
	public abstract List<CompanyForGrowth> getCompanyListForGrowth(CompanyForGrowth companyForGrowth,Page page);

	/**
	 * 获取可修改用户积分列表(数量)
	 * 
	 * @param growth
	 * @return
	 */
	public abstract Long getUserListForGrowthCount(UserForGrowth growth);
	/**
	 * 获取可修改公司积分列表(数量)
	 * @param companyGrowth
	 * @return
	 */
	public abstract Long getCompanyListForGrowthCount(CompanyForGrowth companyForGrowth);
}