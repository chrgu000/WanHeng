package com.cgwas.statement.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.statement.entity.Statement;

public interface IStatementDao extends IDaoSupport {
	Page page(Page page, Statement statement);
}