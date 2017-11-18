package com.jxc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.dao.TitleDao;
import com.jxc.entity.Price;
import com.jxc.entity.SightSpot;
import com.jxc.entity.Ticket;
import com.jxc.entity.Title;
import com.jxc.page.TicketPage;
import com.jxc.service.TicketService;

@Controller
@RequestMapping("/ticket")
@Scope("prototype")
@SessionAttributes("ticketPage")
public class TicketController {
	@Resource
	private TicketService service;
	@Resource
	private TitleDao tdao;

	@RequestMapping("/ticketList.do")
	public String findAllAction(TicketPage page, ModelMap map) {
		Integer rows = service.findRows(page);// ��ȡ���ݱ����������
		page.setRows(rows);
		map.addAttribute("ticketPage", page);
		List<Ticket> tickets = service.findAllTicketByPage(page);// ��ҳ��ѯƷ����Ϣ
		List<Title> titles = tdao.findAllTitle();
		map.put("titles", titles);
		map.put("tickets", tickets);
		return "admin/ticket";// ת����Ʒ����ҳ
	}

	@RequestMapping("/toAddTicket.do")
	public String toAddAction(ModelMap map) {
		List<Title> titles = tdao.findAllTitle();
		map.put("titles", titles);
		return "admin/ticket_add";// ת����Ʒ�����ҳ��
	}

	@RequestMapping("/addTicket.do")
	public String addAction(Ticket ticket) {
		service.addTicket(ticket);// ���Ʒ����Ϣ
		return "redirect:../ticket/ticketList.do";
	}

	@RequestMapping("/toUpdateTicket.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Ticket ticket = service.findTicketById(id);
		List<Title> titles = tdao.findAllTitle();
		map.put("titles", titles);
		map.put("ticket", ticket);
		return "admin/ticket_update";
	}

	@RequestMapping("/updateTicket.do")
	public String updateAction(Ticket ticket) {
		service.updateTicket(ticket);// �޸�Ʒ����Ϣ
		return "redirect:../ticket/ticketList.do";// �ض���Ʒ����ҳ
	}

	@RequestMapping("/deleteTicket.do")
	public String deleteAction(Integer id) {
		service.deleteTicketById(id);// ����idɾ��Ʒ��
		return "redirect:../ticket/ticketList.do";// �ض���Ʒ����ҳ
	}
}
