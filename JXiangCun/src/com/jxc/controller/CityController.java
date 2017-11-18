package com.jxc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.entity.City;
import com.jxc.page.CityPage;
import com.jxc.service.CityService;

@Controller
@RequestMapping("/city")
@Scope("prototype")
@SessionAttributes("cityPage")
public class CityController {
	@Resource
	private CityService service;

	@RequestMapping("/cityList.do")
	public String findAllAction(CityPage page, ModelMap map) {
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		map.addAttribute("cityPage", page);
		List<City> citys = service.findAllCityByPage(page);// 分页查询品牌信息
		map.put("citys", citys);
		return "admin/city";// 转发到品牌首页
	}

	@RequestMapping("/toAddCity.do")
	public String toAddAction() {
		return "admin/city_add";// 转发到品牌添加页面
	}

	@RequestMapping("/addCity.do")
	public String addAction(City city) {
		service.addCity(city);// 添加品牌信息
		return "redirect:../city/cityList.do";
	}

	@RequestMapping("/toUpdateCity.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		City city = service.findCityById(id); 
		map.put("city", city);
		return "admin/city_update"; 
	}

	@RequestMapping("/updateCity.do")
	public String updateAction(City city) {
		service.updateCity(city);// 修改品牌信息
		return "redirect:../city/cityList.do";// 重定向到品牌首页
	}

	@RequestMapping("/deleteCity.do")
	public String deleteAction(Integer id) {
		service.deleteCityById(id);// 根据id删除品牌
		return "redirect:../city/cityList.do";// 重定向到品牌首页
	}
}
