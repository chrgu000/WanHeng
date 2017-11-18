package com.to.controller;


import com.to.entity.*;
import com.to.page.Page;
import com.to.service.*;
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
@RequestMapping("/decorateSituation")
@SessionAttributes("page")

@Scope("prototype")
public class DecorateSituationController {

	@Resource
	private DecorateSituationService service;
	@Resource
	private OrientationService oservice;
	@Resource
	private PayWayService pservice;
	@Resource
	private SupportingFacilityService sservice;
	@Resource
	private ShareHouseService hservice;
	@RequestMapping("/loadDecorateSituation.do")
	public void loadDecorateSituation(Integer pageIndex, Integer limit, String key, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<DecorateSituation> decorateSituations = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				decorateSituations = service.getDecorateSituationByPage(page);
				map.put("rows", decorateSituations);
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
			decorateSituations = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateDecorateSituation.do")
		public void updateDecorateSituation(DecorateSituation decorateSituation,HttpServletResponse response)throws Exception{
		service.updateDecorateSituation(decorateSituation,response);
	}
	@RequestMapping("/addDecorateSituation.do")
	public void addDecorateSituation(DecorateSituation decorateSituation,HttpServletResponse response)throws Exception{
		service.addDecorateSituation(decorateSituation,response);
	}
	@RequestMapping("/deleteDecorateSituation.do")
	public void deleteDecorateSituation(String  ids,HttpServletResponse response)throws Exception{
		service.deleteDecorateSituation(ids,response);
	}
	@RequestMapping("/getAllDecorateSituation.do")
	public void getAllDecorateSituation(HttpServletResponse response)throws Exception{
		try{
			List<DecorateSituation> decorateSituations=service.getAllDecorateSituation();
			JSONArray arr= JSONArray.fromObject(decorateSituations);
			ResponseUtil.write(response,arr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping("getAllOrientation.do")
	public void getAllOrientation(HttpServletResponse response) throws Exception {
		List<Orientation>  orientations=oservice.getAllOrientation();
		JSONArray arr= JSONArray.fromObject(orientations);
		ResponseUtil.write(response,arr);
	}
	@RequestMapping("getAllPayWay.do")
	public void getAllPayWay(HttpServletResponse response) throws Exception {
		List<PayWay> payWays=pservice.getAllPayWay();
		JSONArray arr= JSONArray.fromObject(payWays);
		ResponseUtil.write(response,arr);
	}
	@RequestMapping("getAllSupportingFacility.do")
	public void getAllSupportingFacility(HttpServletResponse response) throws Exception {
		List<SupportingFacility> supportingFacilities=sservice.getAllSupportingFacility();
		JSONArray arr= JSONArray.fromObject(supportingFacilities);
		ResponseUtil.write(response,arr);
	}
	@RequestMapping("getAllShareHouse.do")
	public void getAllShareHouse(HttpServletResponse response) throws Exception {
		List<ShareHouse> shareHouses=hservice.getAllShareHouse();
		JSONArray arr= JSONArray.fromObject(shareHouses);
		ResponseUtil.write(response,arr);
	}
}
