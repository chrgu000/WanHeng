package com.fengyun.service;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.InterestDirection;
import com.fengyun.entity.InterestDirectionVo;
/**
*  Author yangjun
*/
public interface IInterestDirectionService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(InterestDirection interestDirection);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(InterestDirection interestDirection);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(InterestDirection interestDirection);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(InterestDirection interestDirection);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(InterestDirection interestDirection);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(InterestDirection interestDirection);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract InterestDirectionVo load(InterestDirection interestDirection);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract InterestDirectionVo selectForObject(InterestDirection interestDirection);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<InterestDirectionVo> selectForList(InterestDirection interestDirection);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, InterestDirection interestDirection);

	public abstract List<InterestDirection> getAllInterestDirections(Integer type_id);

}