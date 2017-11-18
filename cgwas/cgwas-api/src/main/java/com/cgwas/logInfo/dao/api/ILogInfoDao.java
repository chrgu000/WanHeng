package com.cgwas.logInfo.dao.api;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.logInfo.entity.LogInfo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ILogInfoDao extends IDaoSupport {
	Page page(Page page, LogInfo logInfo);
}