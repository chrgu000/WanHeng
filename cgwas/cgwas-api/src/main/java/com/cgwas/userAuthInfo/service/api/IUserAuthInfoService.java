package com.cgwas.userAuthInfo.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userAuthInfo.entity.AttestationUserAuthInfo;
import com.cgwas.userAuthInfo.entity.UserAuthInfo;
import com.cgwas.userAuthInfo.entity.UserAuthInfoVo;

public interface IUserAuthInfoService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserAuthInfo userAuthInfo);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserAuthInfo userAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(UserAuthInfo userAuthInfo);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserAuthInfo userAuthInfo);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(UserAuthInfo userAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(UserAuthInfo userAuthInfo);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserAuthInfoVo load(UserAuthInfo userAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract UserAuthInfoVo selectForObject(UserAuthInfo userAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<UserAuthInfoVo> selectForList(UserAuthInfo userAuthInfo);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserAuthInfo userAuthInfo);

	/**
	 * 根据ID得到认证信息表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserAuthInfo> getUserAuthInfoById(Long userId);

	/**
	 * 根据用户ID更改认证信息表
	 * 
	 * @param authInfo
	 * @return
	 */
	public abstract Serializable updateUserAuthInfoById(UserAuthInfo authInfo);
	/**
	 * 根据用户ID获取认证状态
	 * @param userId
	 * @return
	 */
	public String getUserAuthInfoStatus(Long userId);
	/**
	 * 更改用户认证信息认证状态
	 * @param userId
	 * @param status
	 * @return
	 */
	public abstract Serializable updateUserAuthInfoStatus(Long userId,Boolean status);
	/**
	 * 获取用户认证列表
	 * @param attestationUserAuthInfo
	 * @param page
	 * @return
	 */
	public abstract List<AttestationUserAuthInfo> getUserAuthInfoList(AttestationUserAuthInfo attestationUserAuthInfo,Page page);
	
	/**
	 * 获取用户认证列表(数量)
	 * @param attestationUserAuthInfo
	 * @return
	 */
	public abstract Long getUserAuthInfoListCount(AttestationUserAuthInfo attestationUserAuthInfo);
	/**
	 * 根据用户id得到用户认证信息
	 * @param user_id
	 * @return
	 */
	public abstract UserAuthInfo getUserAuthInfoByUserId(Long user_id);
	/**
	 * 检测身份证是否存在
	 * @param idcard
	 * @return
	 */
	public abstract Long getHaveIdCard(String idcard);

}