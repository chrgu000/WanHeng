package com.cgwas.menuRole.service.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.menuRole.entity.MenuRole;
import com.cgwas.menuRole.entity.MenuRoleVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IMenuRoleService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(MenuRole menuRole);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(MenuRole menuRole);
	
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void deleteAll(Map<String,Object> map,MenuRole menuRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(MenuRole menuRole);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(MenuRole menuRole);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(MenuRole menuRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(MenuRole menuRole);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract MenuRoleVo load(MenuRole menuRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract MenuRoleVo selectForObject(MenuRole menuRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<MenuRoleVo> selectForList(MenuRole menuRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, MenuRole menuRole);

}