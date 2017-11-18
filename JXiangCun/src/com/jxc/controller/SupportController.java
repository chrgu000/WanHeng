package com.jxc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.entity.Support;
import com.jxc.page.Page;
import com.jxc.service.SupportService;

@Controller
@RequestMapping("/support")
@Scope("prototype")
@SessionAttributes("page")
public class SupportController {
	@Resource
	private SupportService service;

	@RequestMapping("/supportList.do")
	public String findAllAction(Page page, ModelMap map) {
		Integer rows = service.findRows(page);// ��ȡ���ݱ����������
		page.setRows(rows);
		map.addAttribute("page", page);
		List<Support> supports = service.findAllSupportByPage(page);// ��ҳ��ѯƷ����Ϣ
		map.put("supports", supports);
		return "admin/support";// ת����Ʒ����ҳ
	}

	@RequestMapping("/toAddsupport.do")
	public String toAddAction() {
		return "admin/support_add";// ת����Ʒ�����ҳ��
	}

	@RequestMapping("/addSupport.do")
	public String addAction(Support support) {
		service.addSupport(support);// ���Ʒ����Ϣ
		return "redirect:../support/supportList.do";
	}

	@RequestMapping("/toUpdateSupport.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Support support = service.findSupportById(id); 
		map.put("support", support);
		return "admin/support_update"; 
	}

	@RequestMapping("/updateSupport.do")
	public String updateAction(Support support) {
		service.updateSupport(support);// �޸�Ʒ����Ϣ
		return "redirect:../support/supportList.do";// �ض���Ʒ����ҳ
	}

	@RequestMapping("/deleteSupport.do")
	public String deleteAction(Integer id) {
		service.deleteSupportById(id);// ����idɾ��Ʒ��
		return "redirect:../support/supportList.do";// �ض���Ʒ����ҳ
	}
}
