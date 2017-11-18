package com.to.controller;


import com.to.entity.SupportingFacility;
import com.to.page.Page;
import com.to.service.SupportingFacilityService;
import com.to.util.ResponseUtil;
import net.sf.json.JSONArray;
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
@RequestMapping("/supportingFacility")
@Scope("prototype")
@SessionAttributes("page")
public class SupportingFacilityController {

	@Resource
	private SupportingFacilityService service;
	@RequestMapping("/loadSupportingFacility.do")
	public void loadSupportingFacility(Integer pageIndex, Integer limit, String key, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<SupportingFacility> supportingFacilitys = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				supportingFacilitys = service.getSupportingFacilityByPage(page);
				map.put("rows", supportingFacilitys);
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
			supportingFacilitys = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateSupportingFacility.do")
		public void updateSupportingFacility(SupportingFacility supportingFacility,HttpServletResponse response)throws Exception{
		service.updateSupportingFacility(supportingFacility,response);
	}
	@RequestMapping("/addSupportingFacility.do")
	public void addSupportingFacility(SupportingFacility supportingFacility,HttpServletResponse response)throws Exception{
		service.addSupportingFacility(supportingFacility,response);
	}
	@RequestMapping("/deleteSupportingFacility.do")
	public void deleteSupportingFacility(String  ids,HttpServletResponse response)throws Exception{
		service.deleteSupportingFacility(ids,response);
	}
	@RequestMapping("/getSupportingFacilityByHouseId.do")
	public void getSupportingFacilityByHouseId(Integer houseId,HttpServletResponse response) throws Exception {
		List<SupportingFacility> supportingFacilities=service.getSupportingFacilityByHouseId(houseId);
		JSONArray arr= JSONArray.fromObject(supportingFacilities);
		System.out.println(houseId);
		for(SupportingFacility s:supportingFacilities){
			System.out.println(s);
		}
		ResponseUtil.write(response,arr);

	}
}
