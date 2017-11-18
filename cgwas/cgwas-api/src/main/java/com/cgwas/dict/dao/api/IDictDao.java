package com.cgwas.dict.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.dict.entity.Dict;

public interface IDictDao extends IDaoSupport {
	Page page(Page page, Dict dict);
}