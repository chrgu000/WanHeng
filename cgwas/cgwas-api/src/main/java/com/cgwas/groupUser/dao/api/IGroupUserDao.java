package com.cgwas.groupUser.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.groupUser.entity.GroupUser;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IGroupUserDao extends IDaoSupport {
	Page page(Page page, GroupUser groupUser);
}