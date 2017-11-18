package com.dq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dq.entity.Ticket;
import com.dq.entity.UserTicket;
import com.dq.page.TicketPage;
import com.dq.service.TicketService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/ticket")
@Scope("prototype")
public class TicketController {
	private static final Logger log = Logger.getLogger(TicketController.class);
	@Resource
	private TicketService service;
   @RequestMapping("/getTicketsByUserId.do")
   public void getTicketsByUserId(Integer user_id,HttpServletResponse response) throws Exception{
	   List<UserTicket> tickets = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			tickets = service.getTicketsByUserId(user_id);
			map.put("rows", tickets);
			map.put("results", tickets.size());
			map.put("hasError", false);
			map.put("error", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("hasError", true);
			map.put("error", "error");
			map.put("rows", "");
			map.put("results", 0);
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			tickets = null;
			map = null;
			object = null;
		}
   }
	@RequestMapping("/loadTicket.do")
	public void loadTicket(Integer pageIndex, Integer limit,TicketPage page, HttpServletResponse response,Integer s) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Ticket> tickets = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			tickets = service.getByPage(page);
			map.put("rows", tickets);
			map.put("results", rows);
			map.put("hasError", false);
			map.put("error", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("hasError", true);
			map.put("error", "error");
			map.put("rows", "");
			map.put("results", 0);
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			tickets = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/updateTicket.do")
	public void updateTicket(Ticket ticket,HttpServletResponse response)
			throws Exception {
		service.update(ticket, response);
	}

	@RequestMapping("/addTicket.do")
	public void addTicket(Ticket ticket, String  types, HttpServletResponse response)
			throws Exception {
		service.add(ticket, response);
	}

	@RequestMapping("/deleteTicket.do")
	public void deleteTicket(String ids, HttpServletResponse response)
			throws Exception {
		service.delete(ids, response);
	}
}
