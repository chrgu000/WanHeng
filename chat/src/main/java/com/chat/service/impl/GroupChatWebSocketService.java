package com.chat.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.catalina.websocket.WsOutbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.dao.IGroupChatWebSocketDao;
import com.chat.entity.Chapter;
import com.chat.entity.Member;
import com.chat.entity.Message;
import com.chat.service.IGroupChatWebSocketService;

/**
 * Author yangjun
 */
@Service
public class GroupChatWebSocketService implements IGroupChatWebSocketService {
	@Autowired
	private IGroupChatWebSocketDao dao;

	@Override
	public void sendGroupMessage(Message message) {
		dao.save("sendGroupMessage", message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getGroupMembers(Map<String, Object> query) {
		return (List<Member>) dao.selectForList("getGroupMembers", query);
	}

	@Override
	public void addMemberMessage(Map<String, Object> mess) {
		 dao.save("addMemberMessage", mess);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getNameOfUserGroup(Map<String, Object> query) {
		return (Map<String, Object>)dao.selectForObject("getNameOfUserGroup", query);
	}

	@Override
	public void updateVideoStatus(Chapter chapter) {
		System.out.println(chapter.getVedio_url()+","+chapter.getVideo_status());
		 dao.update("updateVideoStatus", chapter);
		
	}
}
