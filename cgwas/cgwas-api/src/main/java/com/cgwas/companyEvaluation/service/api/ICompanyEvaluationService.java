package com.cgwas.companyEvaluation.service.api;



import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyEvaluation.entity.CompanyEvaluation;
import com.cgwas.companyEvaluation.entity.CompanyEvaluationVo;
import com.cgwas.userEvaluation.entity.UserEvaluation;

public interface ICompanyEvaluationService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanyEvaluation companyEvaluation);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanyEvaluation companyEvaluation);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanyEvaluation companyEvaluation);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanyEvaluation companyEvaluation);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanyEvaluation companyEvaluation);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanyEvaluation companyEvaluation);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanyEvaluationVo load(CompanyEvaluation companyEvaluation);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanyEvaluationVo selectForObject(CompanyEvaluation companyEvaluation);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanyEvaluationVo> selectForList(CompanyEvaluation companyEvaluation);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanyEvaluation companyEvaluation);
	
	
	/**
	 *  条件搜索公司评论(数量)
	 * @param companyEvaluation
	 * @return
	 */
	public abstract Long getCompanyEvaluationVListCount(CompanyEvaluation companyEvaluation);
	/**
	 *  条件搜索公司评论
	 * @param companyEvaluation
	 * @param page
	 * @param allFlag
	 * @return
	 */
	public abstract List<CompanyEvaluationVo> getUserEvaluationVList(CompanyEvaluation companyEvaluation,Page page,String allFlag);
	/**
	 *  获取好中差评论数 (公司)
	 * @param company_id
	 * @param evaluate_type
	 * @return
	 */
	public abstract Long  getGCBEvaluationCountCompany(Long company_id,Long evaluate_type);
}