package com.cgwas.freezeRecord.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.freezeRecord.entity.FreezeRecord;

public interface IFreezeRecordDao extends IDaoSupport {
	Page page(Page page, FreezeRecord freezeRecord);
}