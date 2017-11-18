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
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		map.addAttribute("ticketPage", page);
		List<Ticket> tickets = service.findAllTicketByPage(page);// 分页查询品牌信息
		List<Title> titles = tdao.findAllTitle();
		map.put("titles", titles);
		map.put("tickets", tickets);
		return "admin/ticket";// 转发到品牌首页
	}

	@RequestMapping("/toAddTicket.do")
	public String toAddAction(ModelMap map) {
		List<Title> titles = tdao.findAllTitle();
		map.put("titles", titles);
		return "admin/ticket_add";// 转发到品牌添加页面
	}

	@RequestMapping("/addTicket.do")
	public String addAction(Ticket ticket) {
		service.addTicket(ticket);// 添加品牌信息
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
		service.updateTicket(ticket);// 修改品牌信息
		return "redirect:../ticket/ticketList.do";// 重定向到品牌首页
	}

	@RequestMapping("/deleteTicket.do")
	public String deleteAction(Integer id) {
		service.deleteTicketById(id);// 根据id删除品牌
		return "redirect:../ticket/ticketList.do";// 重定向到品牌首页
	}
}
