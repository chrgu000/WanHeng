package com.cgwas.space.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.space.entity.Space;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ISpaceDao extends IDaoSupport {
	Page page(Page page, Space space);
}