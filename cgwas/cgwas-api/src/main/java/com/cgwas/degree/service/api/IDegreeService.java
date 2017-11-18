package com.cgwas.degree.service.api;



import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.degree.entity.Degree;
import com.cgwas.degree.entity.DegreeVo;


public interface IDegreeService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Degree degree);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Degree degree);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Degree degree);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Degree degree);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Degree degree);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Degree degree);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract DegreeVo load(Degree degree);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract DegreeVo selectForObject(Degree degree);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<DegreeVo> selectForList(Degree degree);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Degree degree);

}