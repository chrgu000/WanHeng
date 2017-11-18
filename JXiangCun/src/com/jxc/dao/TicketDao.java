package com.jxc.dao;

import java.util.List;

import com.jxc.entity.Ticket;
import com.jxc.page.Page;
public interface TicketDao {
	List<Ticket> findAllTicket();

	List<Ticket> findAllTicketByPage(Page page);

	Integer findRows(Page page);

	Ticket findTicketById(Integer id);

	boolean addTicket(Ticket ticket);

	boolean updateTicket(Ticket ticket);

	boolean deleteTicketById(Integer id);
}
