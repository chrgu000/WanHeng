package com.yingtong.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yingtong.entity.Brand;
import com.yingtong.page.BrandPage;
import com.yingtong.service.BrandService;

@Controller
@RequestMapping("/brand")
@Scope("prototype")
@SessionAttributes("brandPage")
public class BrandController {
	@Resource
	private BrandService service;//品牌服务对象

	@RequestMapping("/brandList.do")
	public String findAllAction(BrandPage page, ModelMap map) {
		Integer rows = service.findRows(page);//获取数据表的数据行数
		page.setRows(rows);
		map.addAttribute("brandPage",page);
		List<Brand> brands = service.findAllBrandByPage(page);//分页查询品牌信息
		map.put("brands", brands);
		return "admin/brand";//转发到品牌首页
	}

	@RequestMapping("/toAddBrand.do")
	public String toAddAction() {
		return "admin/brand_add";//转发到品牌添加页面
	}

	@RequestMapping("/addBrand.do")
	public String addAction(Brand brand) {
		service.addBrand(brand);//添加品牌信息
		return "redirect:../brand/brandList.do";
	}

	@RequestMapping("/toUpdateBrand.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Brand brand = service.findBrandById(id);//根据ID查找品牌信息
		map.put("brand", brand);
		return "admin/brand_update";//转发到品牌修改页面
	}

	@RequestMapping("/updateBrand.do")
	public String updateAction(Brand brand) {
		service.updateBrand(brand);//修改品牌信息
		return "redirect:../brand/brandList.do";//重定向到品牌首页
	}

	@RequestMapping("/deleteBrand.do")
	public String deleteAction(Integer id) {
		service.deleteBrandById(id);//根据id删除品牌
		return "redirect:../brand/brandList.do";//重定向到品牌首页
	}
}
