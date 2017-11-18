package com.cgwas.degree.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.degree.entity.Degree;

public interface IDegreeDao extends IDaoSupport {
	Page page(Page page, Degree degree);
}