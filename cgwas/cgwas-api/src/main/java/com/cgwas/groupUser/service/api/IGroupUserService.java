package com.cgwas.groupUser.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.groupUser.entity.GroupUser;
import com.cgwas.groupUser.entity.GroupUserVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IGroupUserService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(GroupUser groupUser);
	
	public abstract void saveGroup(GroupUserVo groupUser);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(GroupUser groupUser);
	
	/**
	 * 删除审核员
	 * @param groupUser
	 */
	public abstract void deleteAuditor(GroupUser groupUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(GroupUser groupUser);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(GroupUser groupUser);
	
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateSupervise(Long state,Long fid,Long sid);
	
	/**
	 * 更新团队成员层级
	 * @param groupUser
	 * @return
	 */
	public abstract Boolean updateNum(Long id);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(GroupUser groupUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(GroupUser groupUser);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract GroupUserVo load(GroupUser groupUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract GroupUserVo selectForObject(GroupUser groupUser);
	
	/**
	 * 获取当前角色管理层级最大的一个成员
	 */
	public abstract GroupUserVo getMaxNumByCheck(GroupUser groupUser);
	
	/**
	 * 查询用户是否已经是某个项目的制作者角色
	 */
	public abstract GroupUserVo selectFabricator(GroupUser groupUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<GroupUserVo> selectForList(GroupUser groupUser);
	
	/**
	 * 获取当前项目的成员，除了制作者
	 */
	public abstract List<GroupUserVo> selectTeamList(GroupUser groupUser);
	
	/**
	 * 按项目查询审核员列表
	 */
	public abstract List<GroupUserVo> getAuditorList(GroupUser groupUser);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, GroupUser groupUser);

}