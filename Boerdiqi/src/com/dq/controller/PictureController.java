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

import com.dq.dao.PictureDao;
import com.dq.entity.Picture;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.PicturePage;
import com.dq.service.PictureService;
import com.dq.util.ResponseUtil;

@Controller
@RequestMapping("/picture")
@Scope("prototype")
public class PictureController {
	private static final Logger log = Logger.getLogger(PictureController.class);
	@Resource
	private PictureService service;
	@Resource
	private PictureDao dao;
	@RequestMapping("/loadPicture.do")
	public void loadPicture(HttpServletResponse response,String key,Integer product_id,PicturePage page) throws Exception {
		List<Picture> pictures = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			page.setProduct_id(product_id);
			pictures = service.getAllByPage(page);
			map.put("rows", pictures);
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
			pictures= null;
			map = null;
			object = null;
		}
	}

	 

	@RequestMapping("/addPicture.do")
	public void addPicture(Picture picture, HttpServletResponse response)
			throws Exception {
		if(picture.getProduct_id()==null){
			picture.setType("2");
		}else{
			picture.setType("1");
		}
		service.addPicture(picture, response);
	}

	@RequestMapping("/deletePicture.do")
	public void deletePicture(String ids, HttpServletResponse response)
			throws Exception {
		service.deletePicture(ids, response);
	}
}
