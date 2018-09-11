package com.fengyun.service;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.CourseSoftware;
import com.fengyun.entity.CourseSoftwareVo;
/**
*  Author yangjun
*/
public interface ICourseSoftwareService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CourseSoftware courseSoftware);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CourseSoftware courseSoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CourseSoftware courseSoftware);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CourseSoftware courseSoftware);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CourseSoftware courseSoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CourseSoftware courseSoftware);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CourseSoftwareVo load(CourseSoftware courseSoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CourseSoftwareVo selectForObject(CourseSoftware courseSoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CourseSoftwareVo> selectForList(CourseSoftware courseSoftware);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CourseSoftware courseSoftware);

}