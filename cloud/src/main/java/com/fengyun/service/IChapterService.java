package com.fengyun.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Chapter;
import com.fengyun.entity.ChapterVo;
import com.fengyun.entity.Message;
/**
*  Author yangjun
*/
public interface IChapterService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Chapter chapter);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Chapter chapter);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Chapter chapter);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Chapter chapter);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Chapter chapter);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Chapter chapter);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract ChapterVo load(Chapter chapter);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract ChapterVo selectForObject(Chapter chapter);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<ChapterVo> selectForList(Chapter chapter);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Chapter chapter);

	public abstract Chapter getChapterById(Long id);

	public abstract void updateVideoStatus(Chapter chapter);

	public abstract Integer getMaxOrderByCourseId(Long course_id);

	public abstract Chapter getNearChapter(Map<String, Object> map);

	public abstract Page page1(Page page, Chapter chapter);


	public abstract void sendMessage(Message msg);

	public abstract Long getHasChapterNumsOfCourse(Long course_id);

	public abstract void sendUMessage(Message message);

	Integer getYesCheckChapterByCourseId(Long course_id);

	public abstract void updateChapterVideoUrl(Map<String, String> map);


}