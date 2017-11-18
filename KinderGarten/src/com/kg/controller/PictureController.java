package com.kg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kg.entity.Baby;
import com.kg.entity.PicVo;
import com.kg.entity.Picture;
import com.kg.page.PicturePage;
import com.kg.service.PictureService;
import com.kg.util.CookieUtil;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/picture")
@SessionAttributes("picturePage")
public class PictureController {
	private static final Logger log = Logger.getLogger(BabyController.class);
	@Resource
	private PictureService service;

	@RequestMapping("/loadPicture.do")
	public void loadPicture(Integer pageIndex, Integer limit, String key,
			Integer baby_id, PicturePage page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		page.setBaby_id(baby_id);
		List<Picture> pictures = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			pictures = service.getPictureByPage(page);
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

	@RequestMapping("/loadStylePicture.do")
	public void loadStylePicture(Integer pageIndex, Integer limit,
			PicturePage page, HttpServletResponse response) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Picture> pictures = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getStyleRows(page);
			page.setRows(rows);
			pictures = service.getStylePictureByPage(page);
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

	@RequestMapping("/deletePicture.do")
	public void deletePicture(String ids, HttpServletResponse response,HttpSession session)
			throws Exception {
		service.deletePicture(ids, response,session);
	}

	@RequestMapping("/deleteStylePicture.do")
	public void deleteStylePicture(String ids, HttpServletResponse response,HttpSession session)
			throws Exception {
		service.deleteStylePicture(ids, response,session);
	}

	@RequestMapping("/getPicNum.do")
	public void getPicNum(PicVo picvo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		if (cookie != null) {
			String str = cookie.getValue();
			JSONObject obj = JSONObject.fromObject(str);
			Baby b = (Baby) JSONObject.toBean(obj, Baby.class);
			if (picvo.getBaby_id() == null) {
				picvo.setBaby_id(b.getId());
			}
		}
		Integer vo = service.getPicVo(picvo);
		if (vo == null) {
			vo = 0;
		}

		ResponseUtil.write(response, vo);
	}

	@RequestMapping("/getTotalPicNum.do")
	public void getTotalPicNum(Integer baby_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		if (cookie != null) {
			String str = cookie.getValue();
			JSONObject obj = JSONObject.fromObject(str);
			Baby b = (Baby) JSONObject.toBean(obj, Baby.class);
			if (baby_id == null) {
				baby_id = b.getId();
			}
		}
		Integer num = service.getTotalPicNum(baby_id);
		if (num == null) {
			num = 0;
		}
		ResponseUtil.write(response, num);
	}

	@RequestMapping("/getPicturesByBabyId.do")
	public void getPicturesByBabyId(Integer baby_id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		if (cookie != null) {
			String str = cookie.getValue();
			JSONObject obj = JSONObject.fromObject(str);
			Baby b = (Baby) JSONObject.toBean(obj, Baby.class);
			if (baby_id == null) {
				baby_id = b.getId();
			}
		}
		List<Picture> pictures = service.getPicturesByBabyId(baby_id);
		JSONArray arr = JSONArray.fromObject(pictures);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/getPicturesByTypeId.do")
	public void getPicturesByTypeId(PicVo vo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		if (cookie != null) {
			String str = cookie.getValue();
			JSONObject obj = JSONObject.fromObject(str);
			Baby b = (Baby) JSONObject.toBean(obj, Baby.class);
			if (vo.getBaby_id() == null) {
				vo.setBaby_id(b.getId());
			}
		}
		List<Picture> pictures = service.getPicturesByTypeId(vo);
		JSONArray arr = JSONArray.fromObject(pictures);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/addBabyPicture.do")
	public void addBabyPicture(HttpServletResponse response,
			HttpServletRequest request, String path, Picture picture)
			throws Exception {
		Thumbnails.of(
				request.getSession().getServletContext().getRealPath("/")
						+ path.substring(1, path.length())).size(200, 200)
				.keepAspectRatio(false).toFile(
						request.getSession().getServletContext().getRealPath(
								"/")
								+ "uploadstart-min/"
								+ path.substring(13, path.length()));
		picture.setPath(path);
		picture.setMin_path("/uploadstart-min/"+ path.substring(13, path.length()));
		service.addBabyPicture(picture, response);
	}
@RequestMapping("/deletePictures.do")
public void deletePictures(String ids,HttpServletResponse response,HttpSession session) throws Exception{
		service.deletePicture(ids, response,session);
}
public static void main(String[] args) {
	String path="/uploadstart/1491477420360252.jpg/uploadstart/1491477420523307.jpg";
	if(path.length()/33>1){
		for(int i=0;i<path.length()/33;i++){
			String imgUrl=path.substring(33*i, (i+1)*33);
			System.out.println(imgUrl);
		}
	}
		
}
}
