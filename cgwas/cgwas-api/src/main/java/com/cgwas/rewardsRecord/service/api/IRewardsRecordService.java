package com.cgwas.rewardsRecord.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.rewardsRecord.entity.RewardsRecord;
import com.cgwas.rewardsRecord.entity.RewardsRecordVo;

public interface IRewardsRecordService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(RewardsRecord rewardsRecord);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(RewardsRecord rewardsRecord);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(RewardsRecord rewardsRecord);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(RewardsRecord rewardsRecord);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(RewardsRecord rewardsRecord);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(RewardsRecord rewardsRecord);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract RewardsRecordVo load(RewardsRecord rewardsRecord);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract RewardsRecordVo selectForObject(RewardsRecord rewardsRecord);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<RewardsRecordVo> selectForList(
			RewardsRecord rewardsRecord);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, RewardsRecord rewardsRecord);
	
	/**
	 * 根据用户ID得到奖罚记录
	 * @param userId
	 * @return
	 */
	public abstract List<RewardsRecord> getRewardsRecordListByUserId(Long use_id);
	/**
	 * 根据公司ID得到奖罚记录
	 * @param company_id
	 * @return
	 */
	public abstract List<RewardsRecord> getRewardsRecordListByCompanyId(Long company_id);

}