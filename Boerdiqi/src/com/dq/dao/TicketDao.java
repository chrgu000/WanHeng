package com.dq.dao;

import java.util.List;
import java.util.Map;

import com.dq.entity.Ticket;
import com.dq.entity.UserTicket;

public interface TicketDao extends BaseDao<Ticket> {
	void updateByIds(Map<String, String[]> map);
	
	List<UserTicket> getTicketsByUserId(Integer user_id);
	
	List<Ticket> getTicketsByFlag();
}
