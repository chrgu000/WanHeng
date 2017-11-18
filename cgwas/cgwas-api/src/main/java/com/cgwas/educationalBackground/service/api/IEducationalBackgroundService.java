package com.cgwas.educationalBackground.service.api;



import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.educationalBackground.entity.EducationalBackground;
import com.cgwas.educationalBackground.entity.EducationalBackgroundVo;

public interface IEducationalBackgroundService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(EducationalBackground educationalBackground);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(EducationalBackground educationalBackground);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(EducationalBackground educationalBackground);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(EducationalBackground educationalBackground);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(EducationalBackground educationalBackground);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(EducationalBackground educationalBackground);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract EducationalBackgroundVo load(EducationalBackground educationalBackground);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract EducationalBackgroundVo selectForObject(EducationalBackground educationalBackground);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<EducationalBackgroundVo> selectForList(EducationalBackground educationalBackground);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, EducationalBackground educationalBackground);
	/**
	 * 根据ID得到学习经历信息
	 * @param userId
	 * @return
	 */
	public List<EducationalBackground> getEducationalBackgroundByUserId(Long userId);
	/**
	 * 更具用户ID和信息ID修改用户教育经历信息
	 * @param educationalBackground
	 * @return
	 */
	public abstract Serializable updateEducationalBackgroundByUserId(EducationalBackground educationalBackground);
}