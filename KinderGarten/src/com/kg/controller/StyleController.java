package com.kg.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.kg.entity.Admin;
import com.kg.entity.Baby;
import com.kg.entity.Garden;
import com.kg.entity.Style;
import com.kg.entity.StylePic;
import com.kg.entity.Teacher;
import com.kg.page.StylePage;
import com.kg.service.StyleService;
import com.kg.util.CookieUtil;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/style")
@SessionAttributes("stylePage")
public class StyleController {
	private static final Logger log = Logger.getLogger(BabyController.class);
	@Resource
	private StyleService service;

	@RequestMapping("/loadStyle.do")
	public void loadAdmin(Integer pageIndex, Integer limit, StylePage page,
			HttpServletResponse response,Integer garden_id,HttpSession session) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Style> styles = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Admin admin=(Admin) session.getAttribute("admin");
			if(admin.getRole_id()==2&&garden_id==null&&admin.getGardens()!=null){
				List<Integer> gardenIds=new ArrayList<Integer>();
				for (Garden g : admin.getGardens()) {
					gardenIds.add(g.getId());
				}
				page.setGardenIds(gardenIds);
			}
			if(garden_id!=null){
				page.setGarden_id(garden_id);
			}
			Integer rows = service.getRows(page);
			page.setRows(rows);
			styles = service.getStyleByPage(page);
			map.put("rows", styles);
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
			styles = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/getStyleByTeacherId.do")
	public void getStyleByTeacherId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "teacher");
		List<Style> styles;
		if (cookie == null) {
			cookie = CookieUtil.getCookieByName(request, "baby");
			String str = cookie.getValue();
			JSONObject obj = JSONObject.fromObject(str);
			Baby b = (Baby) JSONObject.toBean(obj, Baby.class);
			styles = service.getStyleByTeacherId(b.getTeacher_id());
		} else {
			String str = cookie.getValue();
			JSONObject obj = JSONObject.fromObject(str);
			Teacher t = (Teacher) JSONObject.toBean(obj, Teacher.class);
			styles = service.getStyleByTeacherId(t.getId());
		}
		JSONArray arr = JSONArray.fromObject(styles);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/addStyle.do")
	public void addStyle(Style style, String imgUrl,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "teacher");
		String str = cookie.getValue();
		JSONObject obj = JSONObject.fromObject(str);
		Teacher t = (Teacher) JSONObject.toBean(obj, Teacher.class);
		style.setTeacher_id(t.getId());
		style.setGarden_id(t.getGarden_id());
		style.setCreate_time(new Date(System.currentTimeMillis()));
		service.addStyle(style, response);
		int length=imgUrl.length()/33;
		String s;
        if(length>1){
        	for(int i=1;i<=length;i++){
        		s=imgUrl.substring(33*(i-1), 33*(i-1)+33);
        		Thumbnails.of(
        				request.getSession().getServletContext().getRealPath("/")
        						+ s.substring(1, s.length())).scale(0.45f).toFile(
        						request.getSession().getServletContext().getRealPath(
        								"/")
        								+ "uploadstart-min/"
        								+ s.substring(13, s.length()));
        		StylePic stylePic=new StylePic();
        		stylePic.setMin_path("/uploadstart-min/"+ s.substring(13, s.length()));
        		stylePic.setImgUrl(s);
        		stylePic.setStyle_id(style.getId());
        		String create_time=new Timestamp(System.currentTimeMillis()).toString();
        		stylePic.setCreate_time(create_time.substring(0,create_time.length()-7));
        		service.addStylePic(stylePic, response);
        	}
        }else{
        	StylePic stylePic=new StylePic();
    		stylePic.setImgUrl(imgUrl);
    		stylePic.setStyle_id(style.getId());
    		String create_time=new Timestamp(System.currentTimeMillis()).toString();
    		stylePic.setCreate_time(create_time.substring(0,create_time.length()-7));
    		Thumbnails.of(
    				request.getSession().getServletContext().getRealPath("/")
    						+ imgUrl.substring(1, imgUrl.length())).scale(0.45f).toFile(
    						request.getSession().getServletContext().getRealPath(
    								"/")
    								+ "uploadstart-min/"
    								+ imgUrl.substring(13, imgUrl.length()));
    		stylePic.setMin_path("/uploadstart-min/"+ imgUrl.substring(13, imgUrl.length()));
    		service.addStylePic(stylePic, response);
        }
	}
	@RequestMapping("/updateStyle.do")
	public void updateStyle(Style style,HttpServletResponse response)throws Exception{
		service.updateStyle(style,response);
	}
	@RequestMapping("/deleteStyle.do")
	public void deleteStyle(String ids,HttpServletResponse response)throws Exception{
		service.deleteByIds(ids, response);
	}
	@RequestMapping("/getStyleById.do")
	public void getStyleById(Integer id,HttpServletResponse response)throws Exception{
		Style style=service.getStyleById(id);
		JSONObject obj=JSONObject.fromObject(style);
		ResponseUtil.write(response, obj);
	}
	 
}
