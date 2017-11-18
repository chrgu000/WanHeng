package com.cgwas.tradeRecord.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.tradeRecord.entity.TradeRecord;
import com.cgwas.tradeRecord.entity.TradeRecordVo;
import com.cgwas.tradeRecord.entity.TradeStatistics;

public interface ITradeRecordService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(TradeRecord tradeRecord);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(TradeRecord tradeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(TradeRecord tradeRecord);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(TradeRecord tradeRecord);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(TradeRecord tradeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(TradeRecord tradeRecord);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract TradeRecordVo load(TradeRecord tradeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract TradeRecordVo selectForObject(TradeRecord tradeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<TradeRecordVo> selectForList(TradeRecord tradeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, TradeRecord tradeRecord);

	/**
	 * 查询今年交易记录
	 * 
	 * @param type
	 * @return
	 */
	public abstract List<TradeStatistics> selectYearTrade(String type);

	/**
	 * 获取用户充值/提现总额
	 * 
	 * @param tradeRecord
	 * @return
	 */
	public abstract String getUserAllTrade(TradeRecord tradeRecord);

	/**
	 * 查询充值订单是否存在
	 * 
	 * @param user_id
	 * @param order_id
	 * @return
	 */
	public abstract Long checkRechargeTrade(Long user_id, String order_id);

}