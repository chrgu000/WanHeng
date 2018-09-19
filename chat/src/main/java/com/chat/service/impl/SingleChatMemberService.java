package com.chat.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.dao.ISingleChatMemberDao;
import com.chat.service.ISingleChatMemberService;
/**
*  Author yangjun
*/
@Service
public class SingleChatMemberService implements ISingleChatMemberService {
    @Autowired
    private ISingleChatMemberDao dao;

	@Override
	public void updateOnlieState(Map<String, Object> query) {
		dao.update("updateOnlieState", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getNameOfUser(Map<String, Object> query) {
		return (Map<String, Object>)dao.selectForObject("getNameOfUser", query);
	}
	 
}
