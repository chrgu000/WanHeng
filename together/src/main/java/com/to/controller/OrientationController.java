package com.to.controller;


import com.to.entity.Orientation;
import com.to.page.Page;
import com.to.service.OrientationService;
import com.to.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orientation")
@Scope("prototype")
public class OrientationController {

	@Resource
	private OrientationService service;
	@RequestMapping("/loadOrientation.do")
	public void loadOrientation(Integer pageIndex, Integer limit, String key, Page page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Orientation> orientations = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				orientations = service.getOrientationByPage(page);
			System.out.println(orientations.size());
				map.put("rows", orientations);
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
			orientations = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateOrientation.do")
		public void updateOrientation(Orientation orientation,HttpServletResponse response)throws Exception{
		service.updateOrientation(orientation,response);
	}
	@RequestMapping("/addOrientation.do")
	public void addOrientation(Orientation orientation,HttpServletResponse response)throws Exception{
		service.addOrientation(orientation,response);
	}
	@RequestMapping("/deleteOrientation.do")
	public void deleteOrientation(String  ids,HttpServletResponse response)throws Exception{
		service.deleteOrientation(ids,response);
	}
}
