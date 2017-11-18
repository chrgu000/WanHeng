package com.cgwas.gRole.service.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.gRole.entity.GRole;
import com.cgwas.gRole.entity.GRoleVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IGRoleService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(GRoleVo gRole);
	
	/**
	 * 添加系统团队角色
	 */
	public abstract void saveSysRole(GRole gRole);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(GRole gRole);
	
	/**
	 * 按对象中的主键进行删除，并更新他的子层级
	 */
	public abstract void deleteAndUpdateForId(GRoleVo gRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(GRole gRole);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(GRole gRole);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(GRole gRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(GRole gRole);
	
	/**
	 * 更新层级
	 */
	public abstract void updateFor_id(GRole gRole);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract GRoleVo load(GRole gRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract GRoleVo selectForObject(GRole gRole);
	
	/**
	 * 根据用户id获取团队角色信息
	 */
	public abstract List<GRoleVo> getGRoleListByUserId(GRole gRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<GRoleVo> selectForList(GRole gRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<HashMap> selectForListMap(GRole gRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<GRoleVo> listRelevance(GRole gRole);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, GRole gRole);

}