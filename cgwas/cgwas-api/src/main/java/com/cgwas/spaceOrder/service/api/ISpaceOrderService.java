package com.cgwas.spaceOrder.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.spaceOrder.entity.SpaceOrder;
import com.cgwas.spaceOrder.entity.SpaceOrderVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ISpaceOrderService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(SpaceOrder spaceOrder);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(SpaceOrder spaceOrder);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(SpaceOrder spaceOrder);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(SpaceOrder spaceOrder);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(SpaceOrder spaceOrder);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(SpaceOrder spaceOrder);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract SpaceOrderVo load(SpaceOrder spaceOrder);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract SpaceOrderVo selectForObject(SpaceOrder spaceOrder);
	
	/**
	 * 按对象中的非空属性作为条件，获取公司购买的空间总和
	 */
	public abstract Integer getSumSpace(SpaceOrder spaceOrder);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<SpaceOrderVo> selectForList(SpaceOrder spaceOrder);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, SpaceOrder spaceOrder);

}