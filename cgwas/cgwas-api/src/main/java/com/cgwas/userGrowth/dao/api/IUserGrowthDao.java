package com.cgwas.userGrowth.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userGrowth.entity.UserGrowth;

public interface IUserGrowthDao extends IDaoSupport {
	Page page(Page page, UserGrowth userGrowth);
}