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

import com.dq.entity.Product;
import com.dq.entity.Withdraw;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.WithdrawPage;
import com.dq.service.WithdrawService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/withdraw")
@Scope("prototype")
public class WithdrawController {
	private static final Logger log = Logger.getLogger(WithdrawController.class);
	@Resource
	private WithdrawService service;
	@RequestMapping("/loadWithdraw.do")
	public void loadWithdraw(Integer pageIndex, Integer limit, 
			WithdrawPage page, HttpServletResponse response,Integer s) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Withdraw> withdraws = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			withdraws = service.getAllByPage(page);
			map.put("rows", withdraws);
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
			withdraws = null;
			map = null;
			object = null;
		}
	}


	@RequestMapping("/changeStatus.do")
	public void changeState(Integer flag, Integer id,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		try {
			if (flag.equals(1)) {
				flag =2;
			}  
			Withdraw p = new Withdraw();
			p.setId(id);
			p.setFlag(flag);
			service.update(p);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");

		} finally {
			JSONObject object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
		}
	}
}
