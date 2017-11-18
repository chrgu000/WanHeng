package com.to.controller;


import com.to.entity.PayWay;
import com.to.page.Page;
import com.to.service.PayWayService;
import com.to.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/payWay")
@Scope("prototype")
public class PayWayController {

	@Resource
	private PayWayService service;
	@RequestMapping("/loadPayWay.do")
	public void loadPayWay(Integer pageIndex, Integer limit, String key, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<PayWay> payWays = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				payWays = service.getPayWayByPage(page);
				map.put("rows", payWays);
				map.put("results", rows);
				map.put("hasError", false);
				map.put("error", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("hasError", true);
			map.put("results", 0);
			map.put("error", "error");
			map.put("rows", "");
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			payWays = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updatePayWay.do")
		public void updatePayWay(PayWay payWay,HttpServletResponse response)throws Exception{
		service.updatePayWay(payWay,response);
	}
	@RequestMapping("/addPayWay.do")
	public void addPayWay(PayWay payWay,HttpServletResponse response)throws Exception{
		service.addPayWay(payWay,response);
	}
	@RequestMapping("/deletePayWay.do")
	public void deletePayWay(String  ids,HttpServletResponse response)throws Exception{
		service.deletePayWay(ids,response);
	}
}
