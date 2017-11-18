package com.cgwas.companyGrowth.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyGrowth.entity.CompanyGrowth;
import com.cgwas.companyGrowth.entity.CompanyGrowthVo;
import com.cgwas.rewardsRecord.entity.RewardsRecord;


public interface ICompanyGrowthService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CompanyGrowth companyGrowth);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CompanyGrowth companyGrowth);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(CompanyGrowth companyGrowth);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CompanyGrowth companyGrowth);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(CompanyGrowth companyGrowth);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(CompanyGrowth companyGrowth);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CompanyGrowthVo load(CompanyGrowth companyGrowth);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CompanyGrowthVo selectForObject(CompanyGrowth companyGrowth);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CompanyGrowthVo> selectForList(CompanyGrowth companyGrowth);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CompanyGrowth companyGrowth);
	/**
	 * 公司成长积分奖罚
	 * @param action
	 * @param GrowthNum
	 * @param companyId
	 */
	public abstract void reduceOrAddGrowthCompany(String action, Integer GrowthNum, Long companyId,RewardsRecord rewardsRecord);
	
	/**
	 * 根据公司id得到公司成长信息
	 * @param company_id
	 * @return
	 */
	public abstract CompanyGrowth getCompanyGrowthByCompanyId(Long company_id);

}