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
		Integer rows = service.findRows(page);// ��ȡ���ݱ����������
		page.setRows(rows);
		map.addAttribute("cityPage", page);
		List<City> citys = service.findAllCityByPage(page);// ��ҳ��ѯƷ����Ϣ
		map.put("citys", citys);
		return "admin/city";// ת����Ʒ����ҳ
	}

	@RequestMapping("/toAddCity.do")
	public String toAddAction() {
		return "admin/city_add";// ת����Ʒ�����ҳ��
	}

	@RequestMapping("/addCity.do")
	public String addAction(City city) {
		service.addCity(city);// ���Ʒ����Ϣ
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
		service.updateCity(city);// �޸�Ʒ����Ϣ
		return "redirect:../city/cityList.do";// �ض���Ʒ����ҳ
	}

	@RequestMapping("/deleteCity.do")
	public String deleteAction(Integer id) {
		service.deleteCityById(id);// ����idɾ��Ʒ��
		return "redirect:../city/cityList.do";// �ض���Ʒ����ҳ
	}
}
