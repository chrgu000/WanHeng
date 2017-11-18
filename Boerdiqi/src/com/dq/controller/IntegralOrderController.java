package com.dq.controller;

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

import com.dq.entity.IntegralOrder;
import com.dq.page.OrderPage;
import com.dq.service.IntegralOrderService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/integralOrder")
@Scope("prototype")
public class IntegralOrderController {
	private static final Logger log = Logger.getLogger(IntegralOrderController.class);
	@Resource
	private IntegralOrderService service;
 
	@RequestMapping("/loadIntegralOrder.do")
	public void loadIntegralOrder(Integer pageIndex, Integer limit,Integer user_id,
			OrderPage page, HttpServletResponse response,Integer s) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		page.setUser_id(user_id);
		List<IntegralOrder> integralorders = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			integralorders = service.getByPage(page);
			map.put("rows", integralorders);
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
			integralorders = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/updateIntegralOrder.do")
	public void updateIntegralOrder(IntegralOrder integralorder,HttpServletResponse response)
			throws Exception {
		service.update(integralorder, response);
	}

	@RequestMapping("/addIntegralOrder.do")
	public void addIntegralOrder(IntegralOrder integralorder, String  types, HttpServletResponse response)
			throws Exception {
		service.add(integralorder, response);
	}

	@RequestMapping("/deleteIntegralOrder.do")
	public void deleteIntegralOrder(String ids, HttpServletResponse response)
			throws Exception {
		service.delete(ids, response);
	}
	@RequestMapping("/getIntegralOrderById.do")
	public void getIntegralOrderById(Integer id,HttpServletResponse response) throws Exception{
		IntegralOrder integralOrder=service.getById(id);
		JSONArray obj=JSONArray.fromObject(integralOrder);
		ResponseUtil.write(response, obj);
	}
}
