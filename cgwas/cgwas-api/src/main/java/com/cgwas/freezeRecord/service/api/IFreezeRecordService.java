package com.cgwas.freezeRecord.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.freezeRecord.entity.FreezeRecord;
import com.cgwas.freezeRecord.entity.FreezeRecordVo;
import com.cgwas.tradeRecord.entity.TradeStatistics;

public interface IFreezeRecordService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(FreezeRecord freezeRecord);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(FreezeRecord freezeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(FreezeRecord freezeRecord);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(FreezeRecord freezeRecord);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(FreezeRecord freezeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(FreezeRecord freezeRecord);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract FreezeRecordVo load(FreezeRecord freezeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract FreezeRecordVo selectForObject(FreezeRecord freezeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<FreezeRecordVo> selectForList(FreezeRecord freezeRecord);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, FreezeRecord freezeRecord);

	/**
	 * 得到冻结的金额
	 * 
	 * @param freezeRecord
	 * @return
	 */
	public abstract Double getUserFreezePrice(FreezeRecord freezeRecord);

	/**
	 * 根据任务id解冻金额
	 * 
	 * @param task_id
	 * @param user_id
	 */
	public abstract void thawFreezeRecord(Long task_id, Long user_id,Long task_type);

	/**
	 * 获取一条冻结金额信息
	 * 
	 * @param task_id
	 * @param user_id
	 */
	public abstract FreezeRecordVo selectFreezeRecordByTask(Long task_id,
			Long user_id, Long trade_tyoe);

	/**
	 * 列表查询冻结信息
	 * 
	 * @param page
	 * @param freezeRecord
	 * @param allFlag
	 * @return
	 */
	public abstract List<FreezeRecordVo> selectFreezeRecordList(Page page,
			FreezeRecord freezeRecord, String allFlag);

	/**
	 * 列表查询冻结信息(数量)
	 * 
	 * @param freezeRecord
	 * @return
	 */
	public abstract Long selectFreezeRecordListCount(FreezeRecord freezeRecord);
	
	/**
	 * 保存冻结记录
	 * @param freezeRecord
	 * @return
	 */
	public abstract String saveFreezeRecord(FreezeRecord freezeRecord,String password,String passwordFlag);
	
	/**
	 * 解冻揭露
	 * @param taskId
	 * @param trade_tyoe
	 * @param password
	 * @param user_id
	 * @param type
	 * @param stageFlag
	 * @return
	 */
	public abstract String releaseFreezeRecord(Long taskId, Long trade_tyoe,
			String password,Long user_id,String type,String stageFlag,Integer custom);
	/**
	 * 查询冻结数量
	 * @param freezeRecord
	 * @return
	 */
	public abstract Long checkFreezeRecordCount(FreezeRecord freezeRecord);
	/**
	 * 解冻其余接受者
	 * @param freezeRecord
	 */
	public abstract void  thawReceiverFreezeRecord(FreezeRecord freezeRecord);
	/**
	 * 根据任务获取冻结人员表
	 * @param freezeRecord
	 * @return
	 */
	public abstract List<Long> searchUserIdByTaskId(FreezeRecord freezeRecord);
	
}