package com.cgwas.space.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.space.entity.Space;
import com.cgwas.space.entity.SpaceVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ISpaceService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Space space);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Space space);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Space space);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Space space);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Space space);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Space space);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract SpaceVo load(Space space);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract SpaceVo selectForObject(Space space);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<SpaceVo> selectForList(Space space);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Space space);

}