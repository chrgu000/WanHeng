package com.cgwas.taskCheck.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.taskCheck.entity.TaskCheck;

public interface ITaskCheckDao extends IDaoSupport {
	Page page(Page page, TaskCheck taskCheck);
}