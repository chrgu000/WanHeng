package com.cgwas.userCredibility.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userCredibility.entity.UserCredibility;
import com.cgwas.userCredibility.entity.UserCredibilityVo;

public interface IUserCredibilityService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserCredibility userCredibility);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserCredibility userCredibility);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(UserCredibility userCredibility);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserCredibility userCredibility);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(UserCredibility userCredibility);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(UserCredibility userCredibility);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserCredibilityVo load(UserCredibility userCredibility);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract UserCredibilityVo selectForObject(
			UserCredibility userCredibility);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<UserCredibilityVo> selectForList(
			UserCredibility userCredibility);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserCredibility userCredibility);
	/**
	 * 根据userId查询评价内容
	 * @param userCredibility
	 * @return
	 */
	public abstract UserCredibility selectUserCredibilityByUserId(UserCredibility userCredibility);

}