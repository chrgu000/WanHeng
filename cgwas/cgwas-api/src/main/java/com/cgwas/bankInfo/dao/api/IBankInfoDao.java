package com.cgwas.bankInfo.dao.api;

import com.cgwas.bankInfo.entity.BankInfo;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;

public interface IBankInfoDao extends IDaoSupport {
	Page page(Page page, BankInfo bankInfo);
}