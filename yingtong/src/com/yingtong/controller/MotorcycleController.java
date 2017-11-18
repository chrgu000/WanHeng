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
	MotorcycleService service;//���ͷ������
	@Resource
	BrandService bservice;//Ʒ�Ʒ������

	@RequestMapping("/motorcycle_list.do")
	public String findAllAction(ModelMap map, Page page) {
		Integer rows = service.findRows(page);
		page.setRows(rows);
		List<Motorcycle> motorcycles = service.findAllMotorcycleByPage(page);//��ҳ��ѯ���г���
		List<Brand> brands=bservice.findAllBrand();//��ȡ����Ʒ�ƶ���
		map.put("brands",brands);
		map.put("motorcycles", motorcycles);
		return "admin/motorcycle";//ת����������ҳ
	}

	@RequestMapping("/toAddMotorcycle.do")
	public String toAddAction(ModelMap map) {
		List<Brand> brands = bservice.findAllBrand();//��ȡ����Ʒ�ƶ���
		map.put("brands", brands);
		return "admin/motorcycle_add";//ת�����������ҳ��
	}

	@RequestMapping("/addMotorcycle.do")
	public String addAction(Motorcycle motorcycle,HttpSession session) {
		session.setAttribute("motorcycle", motorcycle);
		service.addMotorcycle(motorcycle);//��ӳ���
		return "redirect:../motorcycle/motorcycle_list.do";//�ض��򵽳�����ҳ
	}

	@RequestMapping("/toUpdateMotorcycle.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Motorcycle motorcycle = service.findMotorcycleById(id);//����id��ѯ����
		List<Brand> brands = bservice.findAllBrand();//��ȡ����Ʒ�ƶ���
		map.put("motorcycle", motorcycle);
		map.put("brands", brands);
		return "admin/motorcycle_update";//ת���������޸�ҳ��
	}

	@RequestMapping("/updateMotorcycle.do")
	public String updateAction(Motorcycle motorcycle) {
		service.updateMotorcycle(motorcycle);//�޸ĳ���
		return "redirect:../motorcycle/motorcycle_list.do";//�ض��򵽳�����ҳ
	}

	@RequestMapping("/deleteMotorcycleById.do")
	public String deleteAction(Integer id) {
		service.deleteMotorcycleById(id);//ɾ�����͸���id
		return "redirect:../motorcycle/motorcycle_list.do";//�ض��򵽳�����ҳ
	}
}
