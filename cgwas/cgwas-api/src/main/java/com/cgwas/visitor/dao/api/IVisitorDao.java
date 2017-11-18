package com.cgwas.visitor.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.visitor.entity.Visitor;



public interface IVisitorDao extends IDaoSupport {
	Page page(Page page, Visitor visitor);
}