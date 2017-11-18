package com.cgwas.evaluation.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.evaluation.entity.Evaluation;
import com.cgwas.evaluation.entity.EvaluationVo;

public interface IEvaluationService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Evaluation evaluation);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Evaluation evaluation);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Evaluation evaluation);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Evaluation evaluation);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Evaluation evaluation);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Evaluation evaluation);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract EvaluationVo load(Evaluation evaluation);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract EvaluationVo selectForObject(Evaluation evaluation);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<EvaluationVo> selectForList(Evaluation evaluation);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Evaluation evaluation);

}