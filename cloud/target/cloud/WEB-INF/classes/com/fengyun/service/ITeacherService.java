package com.fengyun.service;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.UserInfo;
import com.fengyun.entity.Teacher;
import com.fengyun.entity.TeacherVo;
/**
*  Author yangjun
*/
public interface ITeacherService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Teacher teacher);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Teacher teacher);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Teacher teacher);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Teacher teacher);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Teacher teacher);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Teacher teacher);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract TeacherVo load(Teacher teacher);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract TeacherVo selectForObject(Teacher teacher);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<TeacherVo> selectForList(Teacher teacher);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Teacher teacher);

	public abstract Teacher getTeacherByUsrId(Long userId);

	public abstract Teacher getTeacherById(Long id);

	public abstract List<Teacher> getTeacherByName(String name);

	public abstract Teacher checkTeacherByUserId(Long user_id);

	public abstract UserInfo getUserInfoByUserId(Long user_id);

}