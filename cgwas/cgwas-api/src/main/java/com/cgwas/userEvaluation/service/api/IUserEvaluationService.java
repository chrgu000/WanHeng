package com.cgwas.userEvaluation.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userEvaluation.entity.UserEvaluation;
import com.cgwas.userEvaluation.entity.UserEvaluationVo;

public interface IUserEvaluationService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserEvaluation userEvaluation);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserEvaluation userEvaluation);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(UserEvaluation userEvaluation);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserEvaluation userEvaluation);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(UserEvaluation userEvaluation);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(UserEvaluation userEvaluation);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserEvaluationVo load(UserEvaluation userEvaluation);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract UserEvaluationVo selectForObject(
			UserEvaluation userEvaluation);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<UserEvaluationVo> selectForList(
			UserEvaluation userEvaluation);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserEvaluation userEvaluation);

	/**
	 * 条件搜索评论 (数量)
	 * 
	 * @param userEvaluation
	 * @return
	 */
	public abstract Long getUserEvaluationVListCount(
			UserEvaluation userEvaluation);

	/**
	 * 条件搜索评论
	 * 
	 * @param userEvaluation
	 * @param page
	 * @param allFlag
	 * @return
	 */
	public abstract List<UserEvaluationVo> getUserEvaluationVList(
			UserEvaluation userEvaluation, Page page, String allFlag);

	/**
	 * 获取人员擅长
	 * 
	 * @param user_id
	 * @return
	 */
	public abstract List<UserEvaluationVo> getUserTag(Long user_id);

	/**
	 * 获取好中差评论数
	 * 
	 * @param user_id
	 * @param evaluate_type
	 * @return
	 */
	public abstract Long getGCBEvaluationCount(Long user_id, Long evaluate_type);

}