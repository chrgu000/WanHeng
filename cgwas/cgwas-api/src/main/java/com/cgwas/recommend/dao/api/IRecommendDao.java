package com.cgwas.recommend.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.recommend.entity.Recommend;

public interface IRecommendDao extends IDaoSupport {
	Page page(Page page, Recommend recommend);
}