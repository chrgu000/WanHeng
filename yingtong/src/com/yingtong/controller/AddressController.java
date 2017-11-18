package com.yingtong.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yingtong.dao.TitleAddrDao;
import com.yingtong.entity.Address;
import com.yingtong.entity.TitleAddr;
import com.yingtong.page.Page;
import com.yingtong.service.AddressService;

@Controller
@RequestMapping("/address")
@Scope("prototype")
@SessionAttributes("page")
public class AddressController {
	@Resource
	private AddressService service;// ��ַ�������

	@RequestMapping("/address_list.do")
	public String findAllAction(ModelMap map, Page page, HttpSession session) {
		session.setAttribute("titleAddr_id", page.getTitleAddr_id());// session��������ID
		Integer rows = service.findRowsByTitleAddrId(page.getTitleAddr_id());// ��ȡ��������ID�µ���������
		page.setRows(rows);
		List<Address> addresses = service.findAllAdressByPageTitleAddrId(page);// ��ȡ��������ID�·�ҳ��ѯ�ĵ�ַ��Ϣ
		map.put("addresses", addresses);
		return "admin/address";// ת������ַ��Ϣ��ҳ
	}

	@RequestMapping("/toAddAddress.do")
	public String toAddAction(ModelMap map, Integer titleAddr_id) {
		map.put("titleAddr_id", titleAddr_id);
		return "admin/address_add";// ת������ַ���ҳ��
	}

	@RequestMapping("/addAddress.do")
	public String addAction(Address address) {
		service.addAddress(address);// ��ӵ�ַ��Ϣ
		return "redirect:address_list.do";// �ض����ַ��Ϣ��ҳ
	}

	@RequestMapping("/toUpdateAddress.do")
	public String toUpdateAction(Integer id, ModelMap map) {
		Address address = service.findAddressById(id);
		map.put("address", address);
		return "admin/address_update";// ת������ַ�޸�ҳ��
	}

	@RequestMapping("/updateAddress.do")
	public String updateAction(Address address) {
		service.updateAddress(address);// �޸ĵ�ַ��Ϣ
		return "redirect:address_list.do";// �ض����ַ��Ϣ��ҳ
	}

	@RequestMapping("deleteAddressById.do")
	public String deleteAction(Integer id) {
		service.deleteAddressById(id);// ����Idɾ����ַ��Ϣ
		return "redirect:address_list.do";// �ض����ַ��Ϣ��ҳ
	}
}
