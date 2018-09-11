package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Comment;
/**
*  Author yangjun
*/
public interface ICommentDao extends IDaoSupport {
	Page page(Page page, Comment comment);

	Page page1(Page page, Comment comment);
}