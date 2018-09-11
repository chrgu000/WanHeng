package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Member;

/**
*  Author yangjun
*/
public interface IMemberDao extends IDaoSupport {
	Page page(Page page, Member member);

	Page page1(Page page, Member member);
}