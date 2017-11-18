package com.cgwas.userQuestion.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userQuestion.entity.UserQuestion;

public interface IUserQuestionDao extends IDaoSupport {
	Page page(Page page, UserQuestion userQuestion);
}