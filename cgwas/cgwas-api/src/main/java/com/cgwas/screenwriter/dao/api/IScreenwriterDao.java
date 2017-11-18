package com.cgwas.screenwriter.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.creenwriter.entity.Screenwriter;
/**
*  Author yangjun
*/
public interface IScreenwriterDao extends IDaoSupport {
	Page page(Page page, Screenwriter screenwriter);
}