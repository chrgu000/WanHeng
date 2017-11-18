package com.kg.controller;

import java.sql.Date;
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

import com.kg.entity.Diary;
import com.kg.entity.DiaryType;
import com.kg.entity.Teacher;
import com.kg.page.DiaryPage;
import com.kg.service.DiaryService;
import com.kg.util.CookieUtil;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/diary")
@SessionAttributes("diaryPage")
public class DiaryController {
	private static final Logger log = Logger.getLogger(DiaryController.class);
	@Resource
	private DiaryService service;

	@RequestMapping("/loadDiary.do")
	public void loadAdmin(Integer pageIndex, Integer limit, DiaryPage page,
			HttpServletResponse response) throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Diary> diarys = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer rows = service.getRows(page);
			page.setRows(rows);
			diarys = service.getDiaryByPage(page);
			map.put("rows", diarys);
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
			diarys = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/deleteDiary.do")
	public void deleteDiary(String ids, HttpServletResponse response,
			HttpSession session) throws Exception {
		service.deleteByIds(ids, response,session);
	}

	@RequestMapping("/addDiary.do")
	public void addDiary(Diary diary, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "teacher");
		String str = cookie.getValue();
		JSONObject obj = JSONObject.fromObject(str);
		Teacher t = (Teacher) JSONObject.toBean(obj, Teacher.class);
		diary.setTeacher_id(t.getId());
		diary.setCreate_time(new Date(System.currentTimeMillis()).toString());
		String path = diary.getPath();
		Thumbnails.of(
				request.getSession().getServletContext().getRealPath("/")
						+ path.substring(1, path.length())).size(200, 200)
				.keepAspectRatio(false).toFile(
						request.getSession().getServletContext().getRealPath(
								"/")
								+ "uploadstart-min/"
								+ path.substring(13, path.length()));
		diary.setMin_path("/uploadstart-min/"
				+ path.substring(13, path.length()));
		service.addDiary(diary, response);
	}

	@RequestMapping("/getAllDiaryType.do")
	public void getAllDiaryType(HttpServletResponse response) throws Exception {
		List<DiaryType> diaryTypes = service.findAllDiaryType();
		JSONArray arr = JSONArray.fromObject(diaryTypes);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/findDiary.do")
	public void getDiary(HttpServletRequest request,
			HttpServletResponse response, Integer diary_type_id,String create_time)
			throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "teacher");
		String str = cookie.getValue();
		JSONObject obj = JSONObject.fromObject(str);
		Teacher t = (Teacher) JSONObject.toBean(obj, Teacher.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teacher_id", t.getId());
		map.put("create_time", create_time);
		map.put("diary_type_id", diary_type_id);
		List<Diary> diarys = service.findDiaryByMap(map);
		JSONArray arr = JSONArray.fromObject(diarys);
		ResponseUtil.write(response, arr);
	}
	@RequestMapping("/deleteDiarys.do")
	public void deleteDiarys(String ids,HttpServletResponse response,HttpSession session) throws Exception{
		service.deleteByIds(ids, response,session);
	}
}
