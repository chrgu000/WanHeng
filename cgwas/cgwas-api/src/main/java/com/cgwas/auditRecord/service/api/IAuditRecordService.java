package com.cgwas.auditRecord.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.auditRecord.entity.AuditRecord;
import com.cgwas.auditRecord.entity.AuditRecordVo;
import com.cgwas.common.dataaccess.api.Page;

public interface IAuditRecordService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(AuditRecord auditRecord);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(AuditRecord auditRecord);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(AuditRecord auditRecord);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(AuditRecord auditRecord);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(AuditRecord auditRecord);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(AuditRecord auditRecord);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract AuditRecordVo load(AuditRecord auditRecord);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract AuditRecordVo selectForObject(AuditRecord auditRecord);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<AuditRecordVo> selectForList(AuditRecord auditRecord);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, AuditRecord auditRecord);
	
	/**
	 * 得到最后平均评分
	 * @param auditRecord
	 * @return
	 */
	public abstract AuditRecord getAVGAuditRecord(AuditRecord auditRecord);
	/**
	 * 得到检查人员数量
	 * @param auditRecord
	 * @return
	 */
	public abstract List<AuditRecord> getcheckerNum(AuditRecord auditRecord);
	/**
	 * 得到检查次数数量
	 * @param auditRecord
	 * @return
	 */
	public abstract  List<Long> getcheckCount(AuditRecord auditRecord);
	

}