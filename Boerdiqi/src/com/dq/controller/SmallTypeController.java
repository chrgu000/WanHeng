package com.dq.controller;

import java.io.IOException;
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

import com.dq.entity.BigType;
import com.dq.entity.SmallType;
import com.dq.page.SmallTypePage;
import com.dq.service.BigTypeService;
import com.dq.service.SmallTypeService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/smalltype")
@Scope("prototype")
public class SmallTypeController {
	private static final Logger log = Logger.getLogger(SmallTypeController.class);
	@Resource
	private SmallTypeService service;
	@Resource
	private BigTypeService bservice;
	@RequestMapping("/loadSmallType.do")
	public void loadSmallType(Integer pageIndex, Integer limit,SmallTypePage page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<SmallType> smalltypes = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				smalltypes = service.getByPage(page);
				map.put("rows", smalltypes);
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
			smalltypes = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateSmallType.do")
		public void updateSmallType(SmallType smalltype,HttpServletResponse response)throws Exception{
		service.update(smalltype,response);
	}
	@RequestMapping("/addSmallType.do")
	public void addSmallType(SmallType smalltype,HttpServletResponse response)throws Exception{
		service.add(smalltype,response);
	}
	@RequestMapping("/deleteSmallType.do")
	public void deleteSmallType(String  ids,HttpServletResponse response)throws Exception{
		service.delete(ids,response);
	}
	@RequestMapping("/getSmallTypeById.do")
	public void getAdminById(Integer id,HttpServletResponse response) throws IOException{
		SmallType smalltype=service.getById(id);
		JSONObject object=JSONObject.fromObject(smalltype);
		response.getWriter().print(object);
	}
	@RequestMapping("/getAllType.do")
	public void getAllType(HttpServletResponse response) throws Exception{
		List<BigType> bigtypes=bservice.getAllBigType();
		JSONArray arr=JSONArray.fromObject(bigtypes);
		ResponseUtil.write(response, arr);
	}
	@RequestMapping("/getSmallTypeByBigTypeId.do")
	public void getSmallType(HttpServletResponse response,Integer big_type_id) throws Exception{
		List<SmallType> smalltypes=service.getSmallTypeByBigTypeId(big_type_id);
		JSONArray arr=JSONArray.fromObject(smalltypes);
		ResponseUtil.write(response, arr);
	}
}
