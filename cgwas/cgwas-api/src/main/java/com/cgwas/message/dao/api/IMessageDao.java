package com.cgwas.message.dao.api;



import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.message.entity.Message;


public interface IMessageDao extends IDaoSupport {
	Page page(Page page, Message message);
}