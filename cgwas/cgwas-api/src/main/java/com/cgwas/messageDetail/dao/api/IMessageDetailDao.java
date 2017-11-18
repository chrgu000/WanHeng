package com.cgwas.messageDetail.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.messageDetail.entity.MessageDetail;



public interface IMessageDetailDao extends IDaoSupport {
	Page page(Page page, MessageDetail messageDetail);
}