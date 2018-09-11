package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Chapter;
/**
*  Author yangjun
*/
public interface IChapterDao extends IDaoSupport {
	Page page(Page page, Chapter chapter);

	Page page1(Page page, Chapter chapter);
}