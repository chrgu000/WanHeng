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
	private BrandService service;//Ʒ�Ʒ������

	@RequestMapping("/brandList.do")
	public String findAllAction(BrandPage page, ModelMap map) {
		Integer rows = service.findRows(page);//��ȡ���ݱ����������
		page.setRows(rows);
		map.addAttribute("brandPage",page);
		List<Brand> brands = service.findAllBrandByPage(page);//��ҳ��ѯƷ����Ϣ
		map.put("brands", brands);
		return "admin/brand";//ת����Ʒ����ҳ
	}

	@RequestMapping("/toAddBrand.do")
	public String toAddAction() {
		return "admin/brand_add";//ת����Ʒ�����ҳ��
	}

	@RequestMapping("/addBrand.do")
	public String addAction(Brand brand) {
		service.addBrand(brand);//���Ʒ����Ϣ
		return "redirect:../brand/brandList.do";
	}

	@RequestMapping("/toUpdateBrand.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Brand brand = service.findBrandById(id);//����ID����Ʒ����Ϣ
		map.put("brand", brand);
		return "admin/brand_update";//ת����Ʒ���޸�ҳ��
	}

	@RequestMapping("/updateBrand.do")
	public String updateAction(Brand brand) {
		service.updateBrand(brand);//�޸�Ʒ����Ϣ
		return "redirect:../brand/brandList.do";//�ض���Ʒ����ҳ
	}

	@RequestMapping("/deleteBrand.do")
	public String deleteAction(Integer id) {
		service.deleteBrandById(id);//����idɾ��Ʒ��
		return "redirect:../brand/brandList.do";//�ض���Ʒ����ҳ
	}
}
