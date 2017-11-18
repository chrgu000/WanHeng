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

import com.dq.entity.Address;
import com.dq.page.AddressPage;
import com.dq.service.AddressService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/address")
@Scope("prototype")
public class AddressController {
	private static final Logger log = Logger.getLogger(AddressController.class);
	@Resource
	private AddressService service;
	@RequestMapping("/loadAddress.do")
	public void loadAddress(Integer pageIndex, Integer limit, 
			AddressPage page, HttpServletResponse response,Integer s) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Address> addresss = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			addresss = service.getAllByPage(page);
			map.put("rows", addresss);
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
			addresss = null;
			map = null;
			object = null;
		}
	}

	 
}
