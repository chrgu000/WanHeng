package com.chat.service;

import java.util.List;
import java.util.Map;

import com.chat.entity.Chapter;
import com.chat.entity.Member;
import com.chat.entity.Message;

/**
*  Author yangjun
*/
public interface IGroupChatWebSocketService {

	void sendGroupMessage(Message message);

	List<Member> getGroupMembers(Map<String, Object> query);

	void addMemberMessage(Map<String, Object> mess);

	Map<String, Object> getNameOfUserGroup(
			Map<String, Object> query);

	void updateVideoStatus(Chapter chapter);
}