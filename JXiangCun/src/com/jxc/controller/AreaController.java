package com.jxc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.entity.Area;
import com.jxc.entity.City;
import com.jxc.page.AreaPage;
import com.jxc.service.AreaService;
import com.jxc.service.CityService;

@Controller
@RequestMapping("/area")
@Scope("prototype")
@SessionAttributes("areaPage")
public class AreaController {
	@Resource
	private AreaService service;
	@Resource 
	private CityService cservice;

	@RequestMapping("/areaList.do")
	public String findAllAction(AreaPage page, ModelMap map) {
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		map.addAttribute("areaPage", page);
		List<Area> areas = service.findAllAreaByPage(page);// 分页查询品牌信息
		List<City> citys=cservice.findAllCity();
		map.put("citys", citys);
		map.put("areas", areas);
		return "admin/area";// 转发到品牌首页
	}

	@RequestMapping("/toAddArea.do")
	public String toAddAction(ModelMap  map) {
		List<City> citys=cservice.findAllCity();
		map.put("citys", citys);
		return "admin/area_add";// 转发到品牌添加页面
	}

	@RequestMapping("/addArea.do")
	public String addAction(Area area) {
		service.addArea(area);// 添加品牌信息
		return "redirect:../area/areaList.do";
	}

	@RequestMapping("/toUpdateArea.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Area area = service.findAreaById(id); 
		List<City> citys=cservice.findAllCity();
		map.put("citys", citys);
		map.put("area", area);
		return "admin/area_update"; 
	}

	@RequestMapping("/updateArea.do")
	public String updateAction(Area area) {
		service.updateArea(area);// 修改品牌信息
		return "redirect:../area/areaList.do";// 重定向到品牌首页
	}

	@RequestMapping("/deleteArea.do")
	public String deleteAction(Integer id) {
		service.deleteAreaById(id);// 根据id删除品牌
		return "redirect:../area/areaList.do";// 重定向到品牌首页
	}
}
