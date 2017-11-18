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

import com.dq.entity.Standard;
import com.dq.page.Page;
import com.dq.service.StandardService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/standard")
@Scope("prototype")
public class StandardController {
	private static final Logger log = Logger.getLogger(StandardController.class);
	@Resource
	private StandardService service;
	@RequestMapping("/loadStandard.do")
	public void loadStandard(Integer pageIndex, Integer limit, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Standard> standards = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				standards = service.getByPage(page);
				map.put("rows", standards);
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
			standards = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateStandard.do")
		public void updateStandard(Standard standard,HttpServletResponse response)throws Exception{
		service.update(standard,response);
	}
	@RequestMapping("/addStandard.do")
	public void addStandard(Standard standard,HttpServletResponse response)throws Exception{
		service.add(standard,response);
	}
	@RequestMapping("/deleteStandard.do")
	public void deleteStandard(String  ids,HttpServletResponse response)throws Exception{
		service.delete(ids,response);
	}
	@RequestMapping("/getAllStandard.do")
	public void getAllStandard(HttpServletResponse response) throws Exception{
		List<Standard> standards=service.getAllStandard();
		JSONArray arr=JSONArray.fromObject(standards);
		ResponseUtil.write(response, arr);
	}
}
