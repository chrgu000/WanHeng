package com.yingtong.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yingtong.dao.BrandDao;
import com.yingtong.dao.LongRentServiceDao;
import com.yingtong.entity.Brand;
import com.yingtong.entity.Car;
import com.yingtong.entity.LongRentService;
import com.yingtong.entity.Motorcycle;
import com.yingtong.page.LongRentServicePage;
import com.yingtong.service.CarService;
import com.yingtong.serviceImpl.MotorcycleServiceImpl;

@Controller
@RequestMapping("/longRentService")
@Scope("prototype")
@SessionAttributes("longRentServicePage")
public class LongRentServiceController {
	@Resource
	private LongRentServiceDao dao;// ����������
	@Resource
	private MotorcycleServiceImpl mservice;// ���ͷ������
	@Resource
	private CarService cservice;// �����������
	@Resource
	private BrandDao bdao;// Ʒ�Ʒ������

	@RequestMapping("/longRentService_list.do")
	public String findAllLongRentService(ModelMap map,LongRentServicePage page) {
		Integer rows = dao.findRows(page);
		page.setRows(rows);
		map.addAttribute("longRentServicePage", page);
		List<LongRentService> longRentServices = dao
				.findAllLongRentService(page);// ��ȡ���г����˻�
		map.put("longRentServices", longRentServices);
		return "admin/longRentService";
	}

	@RequestMapping("/deleteLongRentServiceById.do")
	public String deleteAction(Integer id) {
		dao.deleteLongRentServiceById(id);// ����idɾ�������˻�
		return "redirect:../longRentService/longRentService_list.do";

	}

	@RequestMapping("/addlongRentService.do")
	public String addAction(LongRentService longRentService, ModelMap map) {
		Timestamp apply_time = new Timestamp(System.currentTimeMillis());
		longRentService.setApply_time(apply_time);
		if (dao.addLongRentService(longRentService)) {// ��ӳ����˻�
			map.put("message", "success");
		}
		return "index/changzu";
	}

	@RequestMapping("/longRentServiceIndex.do")
	public String IndexAction(ModelMap map) {
		String state = "1";
		List<Car> cars = cservice.findIndexCarByState(state);// ��ȡ����ҳ�ĳ���
		List<Brand> brands = bdao.findAllBrand();// ��ȡ����Ʒ��
		List<Motorcycle> motorcycles = mservice.findMotorcyclesByBrandId(1);// ��ȡƷ��idΪ1�����г���
		List<LongRentService> longRentServices = dao.findAllLongRentServices();// ��ȡ���г����˻�
		List<String> tels = new ArrayList<String>();
		for (LongRentService longRentService : longRentServices) {
			tels.add(longRentService.getTel());
		}
		map.put("cars2", cars);
		map.put("brands", brands);
		map.put("motorcycles", motorcycles);
		map.put("tels", tels);
		return "index/changzu";
	}

	@RequestMapping("/longRentServiceIndex1.do")
	public String Index1Action(ModelMap map, LongRentService longRentService,
			HttpSession session) {
		List<Brand> brands = bdao.findAllBrand();// ��ȡ����Ʒ��
		List<Motorcycle> motorcycles = mservice.findMotorcyclesByBrandId(1);// ��ȡƷ��idΪ1�����г���
		map.put("brands", brands);
		map.put("motorcycles", motorcycles);
		return "mobile/changzu1";
	}

	@RequestMapping("/addlongRentService1.do")
	public String add1Action(LongRentService longRentService,
			HttpServletRequest request, ModelMap map,HttpSession session) {
		List<String> tels = new ArrayList<String>();
		List<LongRentService> longRentServices = dao.findAllLongRentServices();// ��ȡ���г����˻�
		for (LongRentService longRentService1 : longRentServices) {
			tels.add(longRentService1.getTel());
		}
		map.put("tels", tels);
		Brand brand = bdao.findBrandById(longRentService.getBrand_id());
		longRentService.setBrand(brand.getName());
		session.setAttribute("longRentService", longRentService);
		return "mobile/changzu2";
	}

	@RequestMapping("/addlongRentService2.do")
	public String add2Action(LongRentService longRentService, ModelMap map,
			HttpSession session) {
		Timestamp apply_time = new Timestamp(System.currentTimeMillis());
		longRentService.setApply_time(apply_time);
		LongRentService l = (LongRentService) session
				.getAttribute("longRentService");
		longRentService.setBrand(l.getBrand());
		longRentService.setBuy_time(l.getBuy_time());
		longRentService.setCar_num(l.getCar_num());
		longRentService.setDays(l.getDays());
		longRentService.setMotorcycle(l.getMotorcycle());
		 dao.addLongRentService(longRentService);//��ӳ����˻�
		return "redirect:../mobile/changzucg.jsp";
	}

	@RequestMapping(value = "/getMotorcycles.json", produces = "application/json;charset=UTF-8")
	public void getAddressAction(Integer brand_id, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		List<Motorcycle> motorcycles = mservice
				.findMotorcyclesByBrandId(brand_id);
		JSONArray jsonArray = JSONArray.fromObject(motorcycles);
		response.getWriter().print(jsonArray);
	}
}
