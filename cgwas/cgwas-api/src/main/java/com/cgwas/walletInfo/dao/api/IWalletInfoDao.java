package com.cgwas.walletInfo.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.walletInfo.entity.WalletInfo;

public interface IWalletInfoDao extends IDaoSupport {
	Page page(Page page, WalletInfo walletInfo);
}