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

import com.dq.entity.BigType;
import com.dq.page.Page;
import com.dq.service.BigTypeService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/bigtype")
@Scope("prototype")
public class BigTypeController {
	private static final Logger log = Logger.getLogger(BigTypeController.class);
	@Resource
	private BigTypeService service;
	@RequestMapping("/loadBigType.do")
	public void loadBigType(Integer pageIndex, Integer limit, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<BigType> bigtypes = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				bigtypes = service.getByPage(page);
				map.put("rows", bigtypes);
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
			bigtypes = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateBigType.do")
		public void updateBigType(BigType bigtype,HttpServletResponse response)throws Exception{
		service.update(bigtype,response);
	}
	@RequestMapping("/addBigType.do")
	public void addBigType(BigType bigtype,HttpServletResponse response)throws Exception{
		service.add(bigtype,response);
	}
	@RequestMapping("/deleteBigType.do")
	public void deleteBigType(String  ids,HttpServletResponse response)throws Exception{
		service.delete(ids,response);
	}
}
