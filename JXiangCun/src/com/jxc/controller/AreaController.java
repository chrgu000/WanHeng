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
		Integer rows = service.findRows(page);// ��ȡ���ݱ����������
		page.setRows(rows);
		map.addAttribute("areaPage", page);
		List<Area> areas = service.findAllAreaByPage(page);// ��ҳ��ѯƷ����Ϣ
		List<City> citys=cservice.findAllCity();
		map.put("citys", citys);
		map.put("areas", areas);
		return "admin/area";// ת����Ʒ����ҳ
	}

	@RequestMapping("/toAddArea.do")
	public String toAddAction(ModelMap  map) {
		List<City> citys=cservice.findAllCity();
		map.put("citys", citys);
		return "admin/area_add";// ת����Ʒ�����ҳ��
	}

	@RequestMapping("/addArea.do")
	public String addAction(Area area) {
		service.addArea(area);// ���Ʒ����Ϣ
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
		service.updateArea(area);// �޸�Ʒ����Ϣ
		return "redirect:../area/areaList.do";// �ض���Ʒ����ҳ
	}

	@RequestMapping("/deleteArea.do")
	public String deleteAction(Integer id) {
		service.deleteAreaById(id);// ����idɾ��Ʒ��
		return "redirect:../area/areaList.do";// �ض���Ʒ����ҳ
	}
}
