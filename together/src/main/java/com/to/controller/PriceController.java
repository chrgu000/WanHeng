package com.to.controller;


import com.to.entity.Price;
import com.to.page.Page;
import com.to.service.PriceService;
import com.to.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/price")
@Scope("prototype")
@SessionAttributes("page")
public class PriceController {

	@Resource
	private PriceService service;
	@RequestMapping("/loadPrice.do")
	public void loadPrice(Integer pageIndex, Integer limit, String key, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Price> prices = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				prices = service.getPriceByPage(page);
				map.put("rows", prices);
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
			prices = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updatePrice.do")
		public void updatePrice(Price price,HttpServletResponse response)throws Exception{
		service.updatePrice(price,response);
	}
	@RequestMapping("/addPrice.do")
	public void addPrice(Price price,HttpServletResponse response)throws Exception{
		service.addPrice(price,response);
	}
	@RequestMapping("/deletePrice.do")
	public void deletePrice(String  ids,HttpServletResponse response)throws Exception{
		service.deletePrice(ids,response);
	}
}
