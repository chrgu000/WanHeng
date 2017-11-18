package com.cgwas.bankInfo.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.bankInfo.entity.BankInfo;
import com.cgwas.bankInfo.entity.BankInfoVo;
import com.cgwas.common.dataaccess.api.Page;

public interface IBankInfoService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(BankInfo bankInfo);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(BankInfo bankInfo);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(BankInfo bankInfo);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(BankInfo bankInfo);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(BankInfo bankInfo);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(BankInfo bankInfo);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract BankInfoVo load(BankInfo bankInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract BankInfoVo selectForObject(BankInfo bankInfo);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<BankInfoVo> selectForList(BankInfo bankInfo);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, BankInfo bankInfo);

	/**
	 * 获取用户银行卡信息
	 * 
	 * @param bank_id
	 * @return
	 */
	public abstract List<BankInfo> getUserBankList(Long user_id);

	/**
	 * 更改银行卡状态
	 * 
	 * @param status
	 * @param id
	 */
	public abstract void updateStatusBankById(String status, Long id,
			Long user_id);

	/**
	 * 更改银行卡信息
	 * 
	 * @param bankInfo
	 */
	public abstract void updateBankById(BankInfo bankInfo);
	/**
	 * 根据用户id获取银行信息
	 * @param bankInfo
	 * @return
	 */
	public abstract BankInfo getBankInfoByUserId(BankInfo bankInfo);

}