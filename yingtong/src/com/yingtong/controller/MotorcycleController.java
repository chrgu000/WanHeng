package com.yingtong.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yingtong.entity.Brand;
import com.yingtong.entity.Motorcycle;
import com.yingtong.page.Page;
import com.yingtong.service.BrandService;
import com.yingtong.service.MotorcycleService;

@Controller
@RequestMapping("/motorcycle")
@Scope("prototype")
@SessionAttributes("page")
public class MotorcycleController {
	@Resource
	MotorcycleService service;//车型服务对象
	@Resource
	BrandService bservice;//品牌服务对象

	@RequestMapping("/motorcycle_list.do")
	public String findAllAction(ModelMap map, Page page) {
		Integer rows = service.findRows(page);
		page.setRows(rows);
		List<Motorcycle> motorcycles = service.findAllMotorcycleByPage(page);//分页查询所有车型
		List<Brand> brands=bservice.findAllBrand();//获取所有品牌对象
		map.put("brands",brands);
		map.put("motorcycles", motorcycles);
		return "admin/motorcycle";//转发到车型首页
	}

	@RequestMapping("/toAddMotorcycle.do")
	public String toAddAction(ModelMap map) {
		List<Brand> brands = bservice.findAllBrand();//获取所有品牌对象
		map.put("brands", brands);
		return "admin/motorcycle_add";//转发到车型添加页面
	}

	@RequestMapping("/addMotorcycle.do")
	public String addAction(Motorcycle motorcycle,HttpSession session) {
		session.setAttribute("motorcycle", motorcycle);
		service.addMotorcycle(motorcycle);//添加车型
		return "redirect:../motorcycle/motorcycle_list.do";//重定向到车型首页
	}

	@RequestMapping("/toUpdateMotorcycle.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Motorcycle motorcycle = service.findMotorcycleById(id);//根据id查询车型
		List<Brand> brands = bservice.findAllBrand();//获取所有品牌对象
		map.put("motorcycle", motorcycle);
		map.put("brands", brands);
		return "admin/motorcycle_update";//转发到车型修改页面
	}

	@RequestMapping("/updateMotorcycle.do")
	public String updateAction(Motorcycle motorcycle) {
		service.updateMotorcycle(motorcycle);//修改车型
		return "redirect:../motorcycle/motorcycle_list.do";//重定向到车型首页
	}

	@RequestMapping("/deleteMotorcycleById.do")
	public String deleteAction(Integer id) {
		service.deleteMotorcycleById(id);//删除车型根据id
		return "redirect:../motorcycle/motorcycle_list.do";//重定向到车型首页
	}
}
