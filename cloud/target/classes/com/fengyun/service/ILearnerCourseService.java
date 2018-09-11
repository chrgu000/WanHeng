package com.fengyun.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.LearnerCourse;
import com.fengyun.entity.LearnerCourseVo;
/**
*  Author yangjun
*/
public interface ILearnerCourseService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(LearnerCourse learnerCourse);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(LearnerCourse learnerCourse);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(LearnerCourse learnerCourse);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(LearnerCourse learnerCourse);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(LearnerCourse learnerCourse);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(LearnerCourse learnerCourse);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract LearnerCourseVo load(LearnerCourse learnerCourse);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract LearnerCourseVo selectForObject(LearnerCourse learnerCourse);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<LearnerCourseVo> selectForList(LearnerCourse learnerCourse);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, LearnerCourse learnerCourse);

	public abstract LearnerCourse getLearnerCourseByUserMap(
			Map<String, Object> map);

}