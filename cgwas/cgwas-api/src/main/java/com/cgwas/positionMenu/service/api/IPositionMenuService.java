package com.cgwas.positionMenu.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.positionMenu.entity.PositionMenu;
import com.cgwas.positionMenu.entity.PositionMenuVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IPositionMenuService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(PositionMenu positionMenu);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(PositionMenu positionMenu);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(PositionMenu positionMenu);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(PositionMenu positionMenu);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(PositionMenu positionMenu);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(PositionMenu positionMenu);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract PositionMenuVo load(PositionMenu positionMenu);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract PositionMenuVo selectForObject(PositionMenu positionMenu);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<PositionMenuVo> selectForList(PositionMenu positionMenu);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, PositionMenu positionMenu);

}