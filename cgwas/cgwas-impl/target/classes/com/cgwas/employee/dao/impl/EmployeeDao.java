package com.cgwas.employee.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.employee.dao.api.IEmployeeDao;
import com.cgwas.employee.entity.Employee;

@Service
public class EmployeeDao extends AbstractDao implements IEmployeeDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	public Page page(Page page, Employee employee) {
		
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectEmployeePage", "selectEmployeeCount");
		return pageQuery.select(employee);
	}

	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	
	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}

}