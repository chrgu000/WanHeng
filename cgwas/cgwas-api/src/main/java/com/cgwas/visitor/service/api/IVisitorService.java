package com.cgwas.visitor.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.visitor.entity.Visitor;
import com.cgwas.visitor.entity.VisitorVo;

public interface IVisitorService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Visitor visitor);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Visitor visitor);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Visitor visitor);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Visitor visitor);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Visitor visitor);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Visitor visitor);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract VisitorVo load(Visitor visitor);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract VisitorVo selectForObject(Visitor visitor);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<VisitorVo> selectForList(Visitor visitor);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Visitor visitor);
	/**
	 * 统计访问量
	 * @param visitor
	 */
	public abstract void getHaveIpSave(Visitor visitor);
	/**
	 * 按条件搜索
	 * @param visitorVo
	 * @return
	 */
	public abstract Long getVisitorCount(VisitorVo visitorVo);

}