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

import com.dq.entity.Balance;
import com.dq.page.BalancePage;
import com.dq.service.BalanceService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/balance")
@Scope("prototype")
public class BalanceController {
	private static final Logger log = Logger.getLogger(BalanceController.class);
	@Resource
	private BalanceService service;
	@RequestMapping("/loadBalance.do")
	public void loadBalance(Integer pageIndex, Integer limit, 
			BalancePage page, HttpServletResponse response,Integer s) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Balance> balances = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			balances = service.getAllByPage(page);
			map.put("rows", balances);
			map.put("results", rows);
			map.put("hasError", false);
			map.put("error", "");
		} catch (Exception e) {
			map.put("hasError", true);
			map.put("error", "error");
			map.put("rows", "");
			map.put("results", 0);
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			balances = null;
			map = null;
			object = null;
		}
	}

	 
}
