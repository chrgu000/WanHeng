package com.cgwas.spaceOrder.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.spaceOrder.entity.SpaceOrder;
/**
 * 
 * @author yubangqiong
 *
 */
public interface ISpaceOrderDao extends IDaoSupport {
	Page page(Page page, SpaceOrder spaceOrder);
}