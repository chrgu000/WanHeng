package com.kg.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kg.entity.Baby;
import com.kg.entity.ExportExcel;
import com.kg.entity.Foot;
import com.kg.entity.Footprint;
import com.kg.entity.Teacher;
import com.kg.page.FootPrintPage;
import com.kg.service.FootPrintService;
import com.kg.util.Authority;
import com.kg.util.CookieUtil;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/footprint")
@SessionAttributes("footprintPage")
public class FootPrintController {
	private static final Logger footprint = Logger
			.getLogger(BabyController.class);
	@Resource
	private FootPrintService service;

	@RequestMapping("/loadFootprint.do")
	public void loadAdmin(Integer pageIndex, Integer limit, String key,
			String flag, FootPrintPage page, Integer baby_id,
			Integer teacher_id, HttpServletResponse response,
			HttpSession session) throws Exception {
		page.setBaby_id(baby_id);
		page.setTeacher_id(teacher_id);
		session.setAttribute("flag", flag);
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		page.setUsername(key);
		List<Footprint> footprints = null;
		Map<String, Object> map = new HashMap<String, Object>();
		boolean check = false;
		try {
			if (flag != null && flag.equals("0")) {
				check = Authority
						.hasAuthority("/footprint/stu_loadFootprint.do");
			} else {
				check = Authority
						.hasAuthority("/footprint/tea_loadFootprint.do");
			}
			if (check == false) {
				map.put("hasError", true);
				map.put("error", "没有该操作权限");
				return;
			} else {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				footprints = service.getFootprintByPage(page);
				map.put("rows", footprints);
				map.put("results", rows);
				map.put("hasError", false);
				map.put("error", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("hasError", true);
			map.put("error", "error");
			map.put("rows", "");
			map.put("results", 0);
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			footprints = null;
			map = null;
			object = null;
		}
	}

	@RequestMapping("/deleteFootprint.do")
	public void deleteFootprint(String ids, HttpServletResponse response,
			HttpSession session) throws Exception {
		String flag = (String) session.getAttribute("flag");
		service.deleteFootprint(ids, response, flag);
	}

	@RequestMapping("/updFootprint.do")
	public void updateFootprint(Footprint footprint,
			HttpServletResponse response, HttpSession session) throws Exception {
		String flag = (String) session.getAttribute("flag");
		service.updateFootprint(footprint, response, flag);
	}

	@RequestMapping("/updFootprintById.do")
	public void updateFootprintById(Footprint footprint,
			HttpServletResponse response, HttpSession session) throws Exception {
		footprint.setState("1");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String start_time=footprint.getStart_time();
		String end_time=footprint.getEnd_time();
		if(start_time!=null&&!"".equals(start_time)){
			footprint.setStart_time(sdf.format(sdf.parse(start_time)));
		}
		if(end_time!=null&&!"".equals(end_time)){
			footprint.setEnd_time(sdf.format(sdf.parse(end_time)));
		}
		service.updateFootprint(footprint, response);
	}

	@RequestMapping("/getFootprintById.do")
	public void getFootprintById(String id, HttpServletResponse response)
			throws Exception {
		Footprint footprint = service.getFootprintById(Integer.parseInt(id));
		JSONObject object = JSONObject.fromObject(footprint);
		ResponseUtil.write(response, object);
	}

	@RequestMapping("/getFootprintByTeacherId.do")
	public void getFootprintByTeacherId(HttpServletRequest request,
			HttpServletResponse response, String date) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "teacher");
		String str = cookie.getValue();
		JSONObject obj = JSONObject.fromObject(str);
		Teacher t = (Teacher) JSONObject.toBean(obj, Teacher.class);
		if (date == null ||  "".equals(date)) {
			date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
		}else{
			date=new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", t.getId().toString());
		map.put("date", date);
		System.out.println(map);
		List<Footprint> footprints = service.getFootprintByTeacherId(map);
		JSONArray arr = JSONArray.fromObject(footprints);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/getFootprintByBabyId.do")
	public void getFootprintByBabyId(HttpServletRequest request,
			HttpServletResponse response, String date) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		String str = cookie.getValue();
		JSONObject obj = JSONObject.fromObject(str);
		Baby b = (Baby) JSONObject.toBean(obj, Baby.class);
		List<Footprint> footprints = service.getFootprintByBabyId(b.getId());
		JSONArray arr = JSONArray.fromObject(footprints);
		ResponseUtil.write(response, arr);
	}

	@RequestMapping("/exportFootprint.do")
	public void exportFootprint(Integer garden_id, Integer teacher_id,
			Integer baby_id, HttpServletResponse response,
			HttpServletRequest request) {
		List<Footprint> footprints = null;
		if (garden_id != null) {
			footprints = service.getFootprintByGardenId(garden_id);
		} else if (teacher_id != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", teacher_id + "");
			map.put("date", null);
			footprints = service.getFootprintByTeacherId(map);
		} else {
			footprints = service.getFootprintByBabyId(baby_id);
		}
		File file = new File(request.getSession().getServletContext()
				.getRealPath("/WEB-INF/1.jpg"));
		response.setContentType("octets/stream");
		response.addHeader("Content-Disposition",
				"attachment;filename=footprint.xls");
		// 测试图书
		ExportExcel<Foot> ex = new ExportExcel<Foot>();
		String[] headers = { "足迹编号", "园区名称", "教师名称", "学生名称", "午餐情况",
				"是否大便", "是否午睡", "日期" };
		List<Foot> dataset = new ArrayList<Foot>();
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			byte[] buf = new byte[bis.available()];
			while ((bis.read(buf)) != -1) {
				// 将图片数据存放到缓冲数组中
			}
			if (footprints != null && footprints.size() > 0) {
				for (int i = 1; i <= footprints.size(); i++) {
					Footprint f = footprints.get(i - 1);
					dataset.add(new Foot(i, f.getGarden().getName(), f
							.getTeacher().getUsername(), f.getBaby()
							.getUsername(), f.getSiesta(),
							f.getIsShit(), f.getIsSiesta(), f.getDate()));
				}
			}
			OutputStream out = response.getOutputStream();
			if (dataset.size() > 0) {
				ex.exportExcel(headers, dataset, out);
				out.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
