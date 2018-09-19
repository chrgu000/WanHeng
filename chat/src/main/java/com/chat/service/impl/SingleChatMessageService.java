package com.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.dao.ISingleChatMessageDao;
import com.chat.entity.Message;
import com.chat.service.ISingleChatMessageService;
/**
*  Author yangjun
*/
@Service
public class SingleChatMessageService implements ISingleChatMessageService {
    @Autowired
    private ISingleChatMessageDao dao;

	@Override
	public void sendUMessage(Message message) {
		dao.save("sendUMessage", message);
	}
}
