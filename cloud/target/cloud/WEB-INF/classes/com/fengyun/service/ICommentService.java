package com.fengyun.service;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Comment;
import com.fengyun.entity.CommentVo;
/**
*  Author yangjun
*/
public interface ICommentService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Comment comment);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Comment comment);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Comment comment);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Comment comment);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Comment comment);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Comment comment);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CommentVo load(Comment comment);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CommentVo selectForObject(Comment comment);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CommentVo> selectForList(Comment comment);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Comment comment);

	public abstract Page page1(Page page, Comment comment);

}