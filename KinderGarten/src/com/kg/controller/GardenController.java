package com.kg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kg.entity.Admin;
import com.kg.entity.Garden;
import com.kg.page.GardenPage;
import com.kg.service.AdminService;
import com.kg.service.GardenService;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/garden")
@SessionAttributes("gardenPage")
public class GardenController {
	private static final Logger log = Logger.getLogger(GardenController.class);
	@Resource
	private GardenService service;
    @Resource
    private AdminService adminService;
     @RequestMapping("/loadGarden.do")
 	public void loadAdmin(Integer pageIndex, Integer limit, String key,
 			 GardenPage page, HttpSession session,HttpServletResponse response)
 			throws Exception {
 		page.setCurrentPage(pageIndex + 1);
 		page.setPageSize(limit);
 		page.setName(key);
 		List<Garden>	gardens = new ArrayList<Garden>(); 
 		Admin admin=(Admin) session.getAttribute("admin");
 		if(admin.getRole_id()==2){
 			List<Integer> gardenIds=adminService.getGardenIds(admin.getId());
 			page.setGardenIds(gardenIds);
 		}
 	 		Map<String, Object> map = new HashMap<String, Object>();
 	 		try {
 	 			 
 				Integer rows = service.getRows(page);
 				page.setRows(rows);
 				gardens = service.getGardenByPage(page);
 				map.put("results", rows);
 	 			map.put("rows", gardens);
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
 	 			gardens = null;
 	 			map = null;
 	 			object = null;
 	 		}
 		}
	@RequestMapping("/updGarden.do")
	public void updateGarden(Garden garden,HttpServletResponse response)throws Exception{
		service.updateGarden(garden,response);
	}
	@RequestMapping("/addGarden.do")
	public void addGarden(Garden garden,HttpServletResponse response)throws Exception{
		service.addGarden(garden,response);
	}
	@RequestMapping("/deleteGarden.do")
	public void deleteGarden(String  ids,HttpServletResponse response)throws Exception{
		service.deleteGarden(ids,response);
	}
	@RequestMapping("/getGardenById.do")
	public void getGardenById(String id,HttpServletResponse response) throws Exception{
		Garden garden=service.getGardenById(Integer.parseInt(id));
		JSONObject object=JSONObject.fromObject(garden);
		ResponseUtil.write(response, object);
	}
	@RequestMapping("/getAllGarden.do")
	public void getAllGarden(HttpServletResponse response) throws Exception{
		List<Garden> gardens=service.getAllGarden();
		JSONArray arr=JSONArray.fromObject(gardens);
		ResponseUtil.write(response, arr);
		
	}
}
