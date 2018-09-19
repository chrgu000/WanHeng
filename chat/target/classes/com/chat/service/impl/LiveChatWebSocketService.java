package com.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.dao.ILiveChatWebSocketDao;
import com.chat.entity.Message;
import com.chat.service.ILiveChatWebSocketService;
/**
*  Author yangjun
*/
@Service
public class LiveChatWebSocketService implements ILiveChatWebSocketService {
    @Autowired
    private ILiveChatWebSocketDao dao;
	@Override
	public void sendMessage(Message message) {
		dao.save("sendMessage", message);
	}
}
