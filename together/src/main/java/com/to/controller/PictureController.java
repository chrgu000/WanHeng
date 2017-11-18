package com.to.controller;

import com.to.entity.Picture;
import com.to.page.PicturePage;
import com.to.service.PictureService;
import com.to.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/picture")
@SessionAttributes("picturePage")
public class PictureController {
	@Resource
	private PictureService service;
	@RequestMapping("/loadPicture.do")
	public void loadPicture(Integer pageIndex, Integer limit, String key,
							Integer houseId, PicturePage page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		page.setHouseId(houseId);
		List<Picture> pictures = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			pictures = service.getPictureByPage(page);
			System.out.println(pictures.size());
			for(Picture p:pictures){
				System.out.println(p);
			}
			map.put("rows", pictures);
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
			pictures = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/deletePictureById.do")
	public void deletePictureById(Integer id,HttpSession session,HttpServletResponse response){
		Picture p=service.getPictureById(id);
		service.deletePictureById(p,session,response);
	}
	@RequestMapping("/deletePicture.do")
	public void deletePicture(String ids, HttpServletResponse response,HttpSession session)
			throws Exception {
		service.deletePicture(ids, response,session);
	}
	@RequestMapping("/getPicturesByHouseId.do")
	public void getPicturesByHouseId(Integer houseId,HttpServletResponse response) throws Exception {
		List<Picture> pictures=service.getPictureByHouseId(houseId);
		JSONArray arr=JSONArray.fromObject(pictures);
		ResponseUtil.write(response,arr);
	}
}
