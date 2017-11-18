package com.dq.service;

import java.util.List;

import com.dq.entity.Ticket;
import com.dq.entity.UserTicket;

public interface TicketService extends BaseService<Ticket>{
	List<UserTicket> getTicketsByUserId(Integer user_id);
	
	List<Ticket> getTicketsByFlag();
}
