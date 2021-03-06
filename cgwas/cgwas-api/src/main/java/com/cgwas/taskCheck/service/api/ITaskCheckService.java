package com.cgwas.taskCheck.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.taskCheck.entity.TaskCheck;
import com.cgwas.taskCheck.entity.TaskCheckVo;

public interface ITaskCheckService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(TaskCheck taskCheck);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(TaskCheck taskCheck);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(TaskCheck taskCheck);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(TaskCheck taskCheck);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(TaskCheck taskCheck);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(TaskCheck taskCheck);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract TaskCheckVo load(TaskCheck taskCheck);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract TaskCheckVo selectForObject(TaskCheck taskCheck);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<TaskCheckVo> selectForList(TaskCheck taskCheck);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, TaskCheck taskCheck);

}