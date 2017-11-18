package com.cgwas.userInfo.service.api;



import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.user.entity.User;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.entity.UserInfoVo;


public interface IUserInfoService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserInfo userInfo);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserInfo userInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(UserInfo userInfo);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserInfo userInfo);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(UserInfo userInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(UserInfo userInfo);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserInfoVo load(UserInfo userInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract UserInfoVo selectForObject(UserInfo userInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<UserInfoVo> selectForList(UserInfo userInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserInfo userInfo);
	/**
	 * 根据电话号或用户名得到用户登录信息 
	 * @param userId
	 * @return
	 */
	public List<UserInfo> getUserInfoById (Long userId);
	/**
	 * 更改用户信息
	 * @param userId
	 * @return
	 */
	public abstract Serializable updateUserInfoByUserId(UserInfo userInfo);	
	/**
	 * 根据帐号获取email
	 * @param user
	 * @return
	 */
	public abstract String getEmailByAccount(User user);
	/**
	 * 充值和提现
	 * @param userInfo
	 */
	public abstract Serializable rechargeMoney(UserInfo userInfo);
	/**
	 * 根据用户名字获取头像列表
	 * @param name
	 * @return
	 */
	public abstract List<UserInfo> getUserHeardPicByName(String name);

}