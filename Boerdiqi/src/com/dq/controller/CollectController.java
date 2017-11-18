package com.dq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dq.entity.Collect;
import com.dq.service.CollectService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/collect")
public class CollectController {
	private static final Logger log = Logger.getLogger(CollectController.class);
	@Resource
	private CollectService service;
	@RequestMapping("/loadCollect.do")
	public void loadCollect(Integer user_id,HttpServletResponse response)
			throws Exception {
		List<Collect> collects = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			 
			collects = service.getCollects(user_id);
			map.put("rows", collects);
			map.put("results", 1);
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
			map = null;
			object = null;
		}
	}
}
