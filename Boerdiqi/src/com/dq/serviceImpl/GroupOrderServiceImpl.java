package com.dq.serviceImpl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.GroupOrderDao;
import com.dq.entity.GroupOrder;
import com.dq.entity.Orders;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.Page;
import com.dq.service.GroupOrderService;
import com.dq.util.ResponseUtil;

@Service("grouporderService")
@Transactional
public class GroupOrderServiceImpl implements GroupOrderService {
	@Autowired
	private GroupOrderDao dao;
	public List<GroupOrder> getByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	 
	public void add(GroupOrder grouporder, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			grouporder.setStatus("0");
			grouporder.setDelflag(1);
			grouporder.setJoin_time(new Timestamp(System.currentTimeMillis()));
			grouporder.setOrder_num(System.currentTimeMillis()+"");
			dao.save(grouporder);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
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
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("该信息有级联数据,不能删除");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public GroupOrder getById(Integer id) {
		return dao.getById(id);
	}

	public void update(GroupOrder grouporder, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			grouporder.setStatus("4");
			dao.update(grouporder);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
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

	public List<Orders> getOrdersByGroupOrderId(Integer groupOrderId) {
		return dao.getOrdersByGroupOrderId(groupOrderId);
	}
}
