package com.to.controller;


import com.to.entity.ShareHouse;
import com.to.page.Page;
import com.to.service.ShareHouseService;
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
@RequestMapping("/shareHouse")
@Scope("prototype")
@SessionAttributes("page")
public class ShareHouseController {

	@Resource
	private ShareHouseService service;
	@RequestMapping("/loadShareHouse.do")
	public void loadShareHouse(Integer pageIndex, Integer limit, String key, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<ShareHouse> shareHouses = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				shareHouses = service.getShareHouseByPage(page);
				map.put("rows", shareHouses);
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
			shareHouses = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateShareHouse.do")
		public void updateShareHouse(ShareHouse shareHouse,HttpServletResponse response)throws Exception{
		service.updateShareHouse(shareHouse,response);
	}
	@RequestMapping("/addShareHouse.do")
	public void addShareHouse(ShareHouse shareHouse,HttpServletResponse response)throws Exception{
		service.addShareHouse(shareHouse,response);
	}
	@RequestMapping("/deleteShareHouse.do")
	public void deleteShareHouse(String  ids,HttpServletResponse response)throws Exception{
		service.deleteShareHouse(ids,response);
	}
}
