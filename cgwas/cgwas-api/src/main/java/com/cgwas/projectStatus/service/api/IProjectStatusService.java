package com.cgwas.projectStatus.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.projectStatus.entity.ProjectStatus;
import com.cgwas.projectStatus.entity.ProjectStatusVo;
/**
*  Author yangjun
*/
public interface IProjectStatusService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ProjectStatus projectStatus);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ProjectStatus projectStatus);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(ProjectStatus projectStatus);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ProjectStatus projectStatus);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(ProjectStatus projectStatus);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(ProjectStatus projectStatus);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ProjectStatusVo load(ProjectStatus projectStatus);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ProjectStatusVo selectForObject(ProjectStatus projectStatus);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ProjectStatusVo> selectForList(ProjectStatus projectStatus);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ProjectStatus projectStatus);

}