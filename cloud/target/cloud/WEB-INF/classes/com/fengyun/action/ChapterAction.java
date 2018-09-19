// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ChapterAction.java

package com.fengyun.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.fengyun.entity.Chapter;
import com.fengyun.entity.Course;
import com.fengyun.service.IChapterService;
import com.fengyun.service.ICourseService;
import com.fengyun.util.*;
import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpSession;

public class ChapterAction
{

	private IChapterService chapterService;
	private ICourseService courseService;
	private SimpleDateFormat sdf;
	private SimpleDateFormat timeSdf;

	public ChapterAction()
	{
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		timeSdf = new SimpleDateFormat("HH:mm:ss");
	}

	public String getOwnName(HttpSession session)
	{
		JSONObject user = JSONObject.parseObject(session.getAttribute("loginUser").toString());
		String nickname = user.getString("nickname");
		return nickname;
	}

	public Map getSingleChatOwnName(HttpSession session)
	{
		JSONObject user = JSONObject.parseObject(session.getAttribute("loginUser").toString());
		Long userId = user.getLong("id");
		Map query = new HashMap();
		query.put("userId", userId);
		String result1 = HttpUtil.doPost((new StringBuilder(String.valueOf(HTTPConfig.HTTP_PREFIX))).append("/cgwas/cloud/getUserInfoById.action").toString(), query);
		JSONArray users = JSONArray.parseObject(result1).getJSONArray("data");
		JSONObject obj = users.getJSONObject(0);
		String name = obj.getString("name");
		if (name == null || name.isEmpty())
			name = user.getString("nickname");
		query.clear();
		query.put("name", name);
		query.put("user_id", userId);
		query.put("head_pic_url", obj.getString("head_pic_path"));
		query.put("time", timeSdf.format(new Date()));
		return query;
	}

	public Result converVideo(String source, String target, String videoTimeLength, String filePath)
	{
		System.out.println((new StringBuilder("source:")).append(source).toString());
		System.out.println((new StringBuilder("target:")).append(target).toString());
		System.out.println((new StringBuilder("videoTimeLength:")).append(videoTimeLength).toString());
		System.out.println((new StringBuilder("filePath:")).append(filePath).toString());
		ConvertVideo.ConvertVideoTest(source, target);
		(new File(source)).delete();
		Chapter chapter = new Chapter();
		String course_length = ReadVideo.getVideoTimeLength(videoTimeLength);
		chapter.setCourse_length(course_length);
		chapter.setVideo_status("Y");
		chapter.setVedio_url(filePath);
		chapterService.updateVideoStatus(chapter);
		System.out.println("end");
		return new Result(Boolean.TRUE, "成功", null);
	}

	public Result getChapterListByCourseId(Integer pageSize, Integer pageNo, Chapter chapter, String startDate, String endDate)
	{
		Map map = new HashMap();
		Page page = null;
		try
		{
			Map params = new HashMap();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			if (startDate != null && !startDate.trim().equals(""))
				map.put("startDate", sdf.format(sdf.parse(startDate)));
			if (endDate != null && !endDate.trim().equals(""))
				map.put("endDate", sdf.format(sdf.parse(endDate)));
			chapter.setSearch(map);
			page = PageUtils.createPage(params);
			page = chapterService.page1(page, chapter);
			map.clear();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
			map.put("pageMax", Integer.valueOf(Double.valueOf(Math.ceil(((double)page.getTotal().longValue() * 1.0D) / (double)page.getLimit().longValue())).intValue()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}

	public Result getChapterListByUserMap(Integer pageSize, Integer pageNo, Course course, Chapter chapter, String startDate, String endDate, HttpSession session)
	{
		Map map = new HashMap();
		Page page = null;
		try
		{
			JSONObject user = JSONObject.parseObject(session.getAttribute("loginUser").toString());
			Long userId = user.getLong("id");
			Map params = new HashMap();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			if (startDate != null && !startDate.trim().equals(""))
				map.put("startDate", sdf.format(sdf.parse(startDate)));
			if (endDate != null && !endDate.trim().equals(""))
				map.put("endDate", sdf.format(sdf.parse(endDate)));
			map.put("course", course);
			map.put("user_id", userId);
			chapter.setSearch(map);
			page = PageUtils.createPage(params);
			page = chapterService.page(page, chapter);
			map.clear();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
			map.put("pageMax", Integer.valueOf(Double.valueOf(Math.ceil(((double)page.getTotal().longValue() * 1.0D) / (double)page.getLimit().longValue())).intValue()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}

	public Result getCheckChapterList(Integer pageSize, Integer pageNo, Chapter chapter, Course course, String startDate, String endDate)
	{
		Map map = new HashMap();
		Page page = null;
		try
		{
			Map params = new HashMap();
			params.put("pageSize", String.valueOf(pageSize));
			params.put("pageNo", String.valueOf(pageNo));
			if (startDate != null && !startDate.trim().equals(""))
				map.put("startDate", sdf.format(sdf.parse(startDate)));
			if (endDate != null && !endDate.trim().equals(""))
				map.put("endDate", sdf.format(sdf.parse(endDate)));
			map.put("course", course);
			chapter.setSearch(map);
			page = PageUtils.createPage(params);
			page = chapterService.page(page, chapter);
			map.clear();
			map.put("pageMax", Integer.valueOf(Double.valueOf(Math.ceil(((double)page.getTotal().longValue() * 1.0D) / (double)page.getLimit().longValue())).intValue()));
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new Result("00001", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}

	public Result create(Chapter chapter)
	{
		if (chapter != null)
		{
			chapterService.save(chapter);
			return new Result("保存成功!");
		} else
		{
			return new Result("数据传输失败!");
		}
	}

	public Result getChapterById(Long id)
	{
		Chapter chapter = chapterService.getChapterById(id);
		return new Result(Result.SUCCESS, "成功!", chapter);
	}

	public Result changeChapterOrder(Long chapter_id, String type)
	{
		Chapter currentChapter = chapterService.getChapterById(chapter_id);
		Integer currentOrder = currentChapter.getOrder_num();
		Map map = new HashMap();
		map.put("course_id", currentChapter.getCourse().getId());
		map.put("type", type);
		map.put("order_num", currentOrder);
		Chapter nearChapter = chapterService.getNearChapter(map);
		Integer nearOrder = nearChapter.getOrder_num();
		Integer tmp = currentOrder;
		currentOrder = nearOrder;
		nearOrder = tmp;
		currentChapter.setOrder_num(currentOrder);
		nearChapter.setOrder_num(nearOrder);
		chapterService.updateIgnoreNull(currentChapter);
		chapterService.updateIgnoreNull(nearChapter);
		return new Result(Result.SUCCESS, "成功", null);
	}

	public Result update(Map map, Chapter chapter, Course course)
	{
		if (map.get("chapter_id") != null)
		{
			chapter.setId(Long.valueOf((String)map.get("chapter_id")));
			chapter.setCourse_content((String)map.get("course_content"));
			chapter.setCheck_idea((String)map.get("check_idea"));
			chapter.setCheck_status((String)map.get("check_status"));
			chapter.setDelflag((String)map.get("delflag"));
			System.out.println(map);
			chapterService.updateIgnoreNull(chapter);
			Chapter c = chapterService.getChapterById(Long.valueOf((String)map.get("chapter_id")));
			Long course_id = c.getCourse().getId();
			List chapters = courseService.getChaptersByCourseIdOfCheck(course_id);
			if (chapters.size() == 0)
				course.setCheck_delflag("Y");
			else
				course.setCheck_delflag("N");
			Integer n = chapterService.getYesCheckChapterByCourseId(course_id);
			if ("N".equals(c.getCourse().getIs_free()) && n.equals(Integer.valueOf(0)))
			{
				course.setCheck_delflag("Y");
				course.setIs_public("N");
			}
			course.setId(course_id);
			courseService.updateIgnoreNull(course);
			Course crs = courseService.getCourseById(course_id);
			if ("Y".equals(chapter.getCheck_status()) && !"Y".equals(crs.getIs_apply()))
			{
				course.setIs_public("Y");
				course.setId(course_id);
				courseService.updateIgnoreNull(course);
			}
		}
		if (map.get("course_id") != null)
		{
			course.setId(Long.valueOf((String)map.get("course_id")));
			course.setCourse_overview((String)map.get("course_overview"));
			courseService.updateIgnoreNull(course);
		}
		return new Result("保存成功!");
	}

	public Result delete(Chapter chapter)
	{
		chapterService.delete(chapter);
		Chapter c = chapterService.getChapterById(chapter.getId());
		Long course_id = c.getCourse().getId();
		List chapters = courseService.getChaptersByCourseIdOfCheck(course_id);
		Course crs = courseService.getCourseInfoById(course_id);
		System.out.println(crs.getIs_free());
		Course course = new Course();
		course.setId(course_id);
		if (chapters.size() == 0)
		{
			course.setCheck_delflag("Y");
			course.setIs_public("N");
		} else
		{
			course.setCheck_delflag("N");
			course.setIs_public("Y");
		}
		courseService.updateIgnoreNull(course);
		return new Result("删除成功!");
	}
}
