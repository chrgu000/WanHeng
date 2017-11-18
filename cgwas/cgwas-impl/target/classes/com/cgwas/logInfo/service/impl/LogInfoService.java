package com.cgwas.logInfo.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.logInfo.dao.api.ILogInfoDao;
import com.cgwas.logInfo.entity.LogInfo;
import com.cgwas.logInfo.entity.LogInfoVo;
import com.cgwas.logInfo.service.api.ILogInfoService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class LogInfoService implements ILogInfoService {
	private ILogInfoDao logInfoDao;

	public Serializable save(LogInfo logInfo){
		logInfo.setAccess_time(new Date());
		return logInfoDao.save(logInfo);
	}

	public void delete(LogInfo logInfo){
		logInfoDao.delete(logInfo);
	}
	
	public void deleteByExample(LogInfo logInfo){
		logInfoDao.deleteByExample(logInfo);
	}

	public void update(LogInfo logInfo){
		logInfoDao.update(logInfo);
	}
	
	public void updateIgnoreNull(LogInfo logInfo){
		logInfoDao.updateIgnoreNull(logInfo);
	}
		
	public void updateByExample(LogInfo logInfo){
		logInfoDao.update("updateByExampleLogInfo", logInfo);
	}

	public LogInfoVo load(LogInfo logInfo){
		return (LogInfoVo)logInfoDao.reload(logInfo);
	}
	
	public LogInfoVo selectForObject(LogInfo logInfo){
		return (LogInfoVo)logInfoDao.selectForObject("selectLogInfo",logInfo);
	}
	
	public List<LogInfoVo> selectForList(LogInfo logInfo){
		return (List<LogInfoVo>)logInfoDao.selectForList("selectLogInfo",logInfo);
	}
	
	public Page page(Page page, LogInfo logInfo) {
		return logInfoDao.page(page, logInfo);
	}

	@Autowired
	public void setILogInfoDao(
			@Qualifier("logInfoDao") ILogInfoDao  logInfoDao) {
		this.logInfoDao = logInfoDao;
	}

}
