package com.cgwas.rewardsRecord.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.rewardsRecord.entity.RewardsRecord;

public interface IRewardsRecordDao extends IDaoSupport {
	Page page(Page page, RewardsRecord rewardsRecord);
}