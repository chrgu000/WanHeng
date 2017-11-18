package com.to.controller;


import com.to.entity.RentalInformation;
import com.to.page.Page;
import com.to.service.RentalInformationService;
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
@RequestMapping("/rentalInformation")
@Scope("prototype")
@SessionAttributes("page")
public class RentalInformationController {

	@Resource
	private RentalInformationService service;
	@RequestMapping("/loadRentalInformation.do")
	public void loadRentalInformation(Integer pageIndex, Integer limit, String key, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<RentalInformation> rentalInformations = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				rentalInformations = service.getRentalInformationByPage(page);
				map.put("rows", rentalInformations);
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
			rentalInformations = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateRentalInformation.do")
		public void updateRentalInformation(RentalInformation rentalInformation,HttpServletResponse response)throws Exception{
		service.updateRentalInformation(rentalInformation,response);
	}
	@RequestMapping("/addRentalInformation.do")
	public void addRentalInformation(RentalInformation rentalInformation,HttpServletResponse response)throws Exception{
		service.addRentalInformation(rentalInformation,response);
	}
	@RequestMapping("/deleteRentalInformation.do")
	public void deleteRentalInformation(String  ids,HttpServletResponse response)throws Exception{
		service.deleteRentalInformation(ids,response);
	}
	@RequestMapping("/getAllRentalInformation.do")
	public void getAllRentalInformation(HttpServletResponse response)throws Exception{
         service.getAllRentalInformations(response);

	}
}
