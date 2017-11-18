package com.cgwas.userGroup.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userGroup.entity.UserGroup;
import com.cgwas.userGroup.entity.UserGroupVo;

public interface IUserGroupService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserGroup userGroup);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserGroup userGroup);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(UserGroup userGroup);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserGroup userGroup);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(UserGroup userGroup);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(UserGroup userGroup);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserGroupVo load(UserGroup userGroup);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract UserGroupVo selectForObject(UserGroup userGroup);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<UserGroupVo> selectForList(UserGroup userGroup);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserGroup userGroup);

	/**
	 * 得到用户组列表
	 * 
	 * @param page
	 * @return
	 */
	public abstract List<UserGroup> getUserGroupList(UserGroup userGroup,Page page);

	/**
	 * 根据ID得到用户组信息
	 * @param id
	 * @return
	 */
	public abstract UserGroup getUserGroupById(Long id);
	/**
	 * 更改用户组信息
	 * @param userGroup
	 */
	public abstract void  updateUserGroupInfo(UserGroup userGroup);
	/**
	 * 批量删除用户组
	 * @param userGroupIdList
	 */
	public void batchDeleteUserGroup(List<Long> userGroupIdList);
	/**
	 * 得到管理组列表
	 * @param page
	 * @return
	 */
	public abstract List<UserGroup> getCompanyGroupList(UserGroup userGroup,Page page);
	/**
	 * 得到用户组列表(数量)
	 * @param userGroup
	 * @return
	 */
	public abstract Long getUserGroupListCount(UserGroup userGroup);
	/**
	 * 得到管理组列表(数量)
	 * @param userGroup
	 * @return
	 */
	public abstract Long getCompanyGroupListCount(UserGroup userGroup);

}