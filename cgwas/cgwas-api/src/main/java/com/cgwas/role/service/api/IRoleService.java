package com.cgwas.role.service.api;

import java.util.List;
import java.util.Map;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.role.entity.Role;
import com.cgwas.role.entity.RoleVo;

public interface IRoleService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Role role);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Role role);
	
	/**
	 * 删除多条 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void deleteAll(Map<String,Object> map,Role role);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Role role);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Role role);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Role role);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Role role);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract RoleVo load(Role role);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract RoleVo selectForObject(Role role);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract RoleVo selectForUserId(Long user_id);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<RoleVo> selectForList(Role role);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Role role);
	/**
	 * 根据用户id得到权限信息
	 * @param userId
	 * @return
	 */
	public abstract RoleVo getRoleByUserId (Long userId);

}