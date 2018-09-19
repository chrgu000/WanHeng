package com.chat.service;

import java.util.Map;

import com.chat.entity.Message;

/**
*  Author yangjun
*/
public interface ISingleChatMemberService {

	public void updateOnlieState(Map<String, Object> query);

	public Map<String, Object> getNameOfUser(Map<String, Object> query);

	 


}