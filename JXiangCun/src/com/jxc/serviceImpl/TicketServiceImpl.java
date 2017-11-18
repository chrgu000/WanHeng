package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.TicketDao;
import com.jxc.entity.Ticket;
import com.jxc.page.Page;
import com.jxc.service.TicketService;

@Service("ticketService")
@Transactional
public class TicketServiceImpl implements TicketService {
	@Resource
	private TicketDao dao;

	public List<Ticket> findAllTicket() {
		return dao.findAllTicket();
	}

	public List<Ticket> findAllTicketByPage(Page page) {
		return dao.findAllTicketByPage(page);
	}

	public Integer findRows(Page page) {
		return dao.findRows(page);
	}

	public Ticket findTicketById(Integer id) {
		return dao.findTicketById(id);
	}

	public boolean addTicket(Ticket ticket) {
		return dao.addTicket(ticket);
	}

	public boolean updateTicket(Ticket ticket) {
		return dao.updateTicket(ticket);
	}

	public boolean deleteTicketById(Integer id) {
		return dao.deleteTicketById(id);
	}

}
