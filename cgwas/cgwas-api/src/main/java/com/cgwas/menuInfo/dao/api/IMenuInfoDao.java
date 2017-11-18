package com.cgwas.menuInfo.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.menuInfo.entity.MenuInfo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IMenuInfoDao extends IDaoSupport {
	Page page(Page page, MenuInfo menuInfo);
}