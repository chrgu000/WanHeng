package com.cgwas.auditRecord.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.auditRecord.dao.api.IAuditRecordDao;
import com.cgwas.auditRecord.entity.AuditRecord;
import com.cgwas.auditRecord.entity.AuditRecordVo;
import com.cgwas.auditRecord.service.api.IAuditRecordService;
import com.cgwas.common.dataaccess.api.Page;
@Service
public class AuditRecordService implements IAuditRecordService {
	private IAuditRecordDao auditRecordDao;

	public Serializable save(AuditRecord auditRecord){
		return auditRecordDao.save(auditRecord);
	}

	public void delete(AuditRecord auditRecord){
		auditRecordDao.delete(auditRecord);
	}
	
	public void deleteByExample(AuditRecord auditRecord){
		auditRecordDao.deleteByExample(auditRecord);
	}

	public void update(AuditRecord auditRecord){
		auditRecordDao.update(auditRecord);
	}
	
	public void updateIgnoreNull(AuditRecord auditRecord){
		auditRecordDao.updateIgnoreNull(auditRecord);
	}
		
	public void updateByExample(AuditRecord auditRecord){
		auditRecordDao.update("updateByExampleAuditRecord", auditRecord);
	}

	public AuditRecordVo load(AuditRecord auditRecord){
		return (AuditRecordVo)auditRecordDao.reload(auditRecord);
	}
	
	public AuditRecordVo selectForObject(AuditRecord auditRecord){
		return (AuditRecordVo)auditRecordDao.selectForObject("selectAuditRecord",auditRecord);
	}
	
	public List<AuditRecordVo> selectForList(AuditRecord auditRecord){
		return (List<AuditRecordVo>)auditRecordDao.selectForList("selectAuditRecord",auditRecord);
	}
	
	public Page page(Page page, AuditRecord auditRecord) {
		return auditRecordDao.page(page, auditRecord);
	}

	@Autowired
	public void setIAuditRecordDao(
			@Qualifier("auditRecordDao") IAuditRecordDao  auditRecordDao) {
		this.auditRecordDao = auditRecordDao;
	}

	@Override
	public AuditRecord getAVGAuditRecord(AuditRecord auditRecord) {
		return (AuditRecord)auditRecordDao.selectForObject("getAVGAuditRecord",auditRecord);
	}

	@Override
	public List<AuditRecord> getcheckerNum(AuditRecord auditRecord) {
		return (List<AuditRecord>)auditRecordDao.selectForList("getcheckerNum",auditRecord);
	}

	@Override
	public List<Long> getcheckCount(AuditRecord auditRecord) {
		return (List<Long>)auditRecordDao.selectForList("getcheckCount",auditRecord);
	}

}
