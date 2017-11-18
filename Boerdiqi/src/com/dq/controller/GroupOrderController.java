package com.dq.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dq.dao.CountDao;
import com.dq.entity.GroupOrder;
import com.dq.entity.Orders;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.OrderPage;
import com.dq.service.GroupOrderService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/grouporder")
@Scope("prototype")
public class GroupOrderController {
	private static final Logger log = Logger.getLogger(GroupOrderController.class);
	@Resource
	private GroupOrderService service;
	@Resource
	private CountDao dao;
 
	@RequestMapping("/loadGroupOrder.do")
	public void loadGroupOrder(Integer pageIndex, Integer limit,Integer user_id,
			OrderPage page, HttpServletResponse response,Integer s) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		page.setUser_id(user_id);
		List<GroupOrder> grouporders = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			grouporders = service.getByPage(page);
			map.put("rows", grouporders);
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
			grouporders = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/updateGroupOrder.do")
	public void updateGroupOrder(GroupOrder grouporder,HttpServletResponse response)
			throws Exception {
		service.update(grouporder, response);
	}

	@RequestMapping("/addGroupOrder.do")
	public void addGroupOrder(GroupOrder grouporder, String  types, HttpServletResponse response)
			throws Exception {
		service.add(grouporder, response);
	}

	@RequestMapping("/deleteGroupOrder.do")
	public void deleteGroupOrder(String ids, HttpServletResponse response)
			throws Exception {
		service.delete(ids, response);
	}
	@RequestMapping("/getOrdersByGroupOrderId.do")
	public void getOrdersByGroupOrderId(Integer group_order_id,HttpServletResponse response) throws Exception{
		List<Orders> orders=service.getOrdersByGroupOrderId(group_order_id);
		JSONArray arr=JSONArray.fromObject(orders);
		ResponseUtil.write(response, arr);
	}
	@RequestMapping("/loadCount.do")
	public void loadCount(HttpServletResponse response){
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		Map<String,Integer> map=new HashMap<String,Integer>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date());
		try {
		    Integer j=dao.getTradeCount();
		    Integer k=dao.getAmount();
		    Integer l=dao.getTradeCountToday(date);
		    Integer m=dao.getAmountToday(date);
		    
		    Integer n=dao.getOrderCount();
		    Integer o=dao.getOrderCountToday(date);
		    Integer p=dao.getOrderCountFaHuo();
		    Integer q=dao.getOrderCountDaiFaHuo();
		    
		    Integer t=dao.getYiTuiKuanCount();
		    Integer u=dao.getYiTuiKuans();
		    Integer v=dao.getDaiTuiKuanCount();
		    Integer w=dao.getDaiTuiKuans();
		    
		    Integer x=dao.getIntegralOrderCount();
		    Integer y=dao.getIntegralCountToday(date);
		    Integer z=dao.getIntegralOrderCountFaHuo();
		    Integer a=dao.getIntegralOrderCountDaiFaHuo();
		    map.put("j", j);
		    map.put("k", k);
		    map.put("l", l);
		    map.put("m", m); 
		    
		    map.put("n",n);
		    map.put("o",o);
		    map.put("p", p);
		    map.put("q", q);
		    
		    map.put("t", t);
		    map.put("u", u);
		    map.put("v",v); 
		    map.put("w", w);
		    
		    map.put("x",x);
		    map.put("y", y);
		    map.put("z", z);
		    map.put("a", a);
		    for (String key : map.keySet()) {
		    	   if(map.get(key)==null){
		    		   map.put(key, 0);
		    	   }
		    	  }
		    returnInfo.setObject(map);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
			object=JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			map=null;
			returnInfo = null;
			object = null;
		}
		
		
	}
}
