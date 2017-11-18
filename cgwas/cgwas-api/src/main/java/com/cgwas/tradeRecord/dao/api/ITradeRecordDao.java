package com.cgwas.tradeRecord.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.tradeRecord.entity.TradeRecord;

public interface ITradeRecordDao extends IDaoSupport {
	Page page(Page page, TradeRecord tradeRecord);
}