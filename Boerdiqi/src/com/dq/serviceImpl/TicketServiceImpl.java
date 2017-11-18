package com.dq.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.TicketDao;
import com.dq.entity.Ticket;
import com.dq.entity.UserTicket;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.Page;
import com.dq.service.TicketService;
import com.dq.util.MD5;
import com.dq.util.ResponseUtil;
@Service("ticketService")
@Transactional
public class TicketServiceImpl implements TicketService {
	@Resource
	private TicketDao dao;

	public List<Ticket> getByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void update(Ticket ticket, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		MD5 md5 = new MD5();
		try {
			dao.update(ticket);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("保存成功");
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
			md5 = null;
		}
	}

	public void add(Ticket ticket, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			ticket.setState(1);
			ticket.setDelflag(1);
			ticket.setFlag(1);
			dao.save(ticket);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public void delete(String ids, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			Map<String, String[]> map = new HashMap<String, String[]>();
			String[] Ids = ids.split(",");
			map.put("ids", Ids);
		    dao.updateByIds(map);
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("该信息有级联数据,不能删除");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public Ticket getById(Integer id) {
		return dao.getById(id);
	}

	public List<UserTicket> getTicketsByUserId(Integer userId) {
		return dao.getTicketsByUserId(userId);
	}

	public List<Ticket> getTicketsByFlag() {
		return dao.getTicketsByFlag();
	}


}
