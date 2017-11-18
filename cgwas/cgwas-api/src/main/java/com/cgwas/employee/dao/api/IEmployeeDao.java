package com.cgwas.employee.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.employee.entity.Employee;

public interface IEmployeeDao extends IDaoSupport {
	Page page(Page page, Employee employee);
}