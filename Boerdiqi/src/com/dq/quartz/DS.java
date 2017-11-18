package com.dq.quartz;

import java.util.List;

import javax.annotation.Resource;

import com.dq.dao.TicketDao;
import com.dq.entity.Ticket;
import com.dq.util.TicketUtil;

public class DS {
	@Resource 
	private TicketDao dao;
	public void work() throws Exception {
		List<Ticket> tickets=dao.getTicketsByFlag();
		List<Ticket> ts=TicketUtil.getTickets(tickets);
		for (Ticket ticket : ts) {
			dao.update(ticket);
		}
	}
}
