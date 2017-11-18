package com.cgwas.user.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.user.entity.AdminUser;
import com.cgwas.user.entity.User;
import com.cgwas.user.entity.UserVo;

public interface IUserService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(User user);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(User user);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(User user);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(User user);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(User user);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(User user);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserVo load(User user);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract UserVo selectForObject(User user);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<UserVo> selectForList(User user);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, User user);

	/**
	 * 根据电话号查询匹配数
	 * 
	 * @param user
	 * @return
	 */
	List<Integer> getNumByTel(User user);

	/**
	 * 根据电话号或用户名得到用户登录信息
	 * 
	 * @param account
	 *            账号
	 * @return
	 */
	List<User> getUserByAccount(String account);

	/**
	 * 更新用户登录时间
	 * 
	 * @param loginTime
	 * @param loginId
	 * @return
	 */
	public abstract Serializable updateByLastLoginTime(User user);

	/**
	 * 批量删除用户
	 * @param userIdList
	 */
	public Serializable batchDeleteUser(List<Long> userIdList);
	/**
	 * 根据ID得到 用户信息
	 * @param id
	 * @return
	 */
	public User getUserById(Long id);
	/**
	 * 根据帐户去修改密码
	 * @param user
	 * @return
	 */
	public abstract Serializable updateUserByAccount(User user);
	/**
	 * 获取用户认证列表
	 * @param adminUser
	 * @param page
	 * @return
	 */
	public abstract List<AdminUser> getAdminUserList(AdminUser adminUser,Page page);
	/**
	 * 获取用户认证列表(数量)
	 * @param adminUser
	 * @return
	 */
	public abstract Long getAdminUserListCount(AdminUser adminUser);
	/**
	 * 根据uuid获取用户信息
	 * @param uuid
	 * @return
	 */
	public abstract User getUserByUUID(String uuid);
}