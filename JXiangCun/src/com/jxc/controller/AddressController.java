package com.jxc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.entity.Address;
import com.jxc.page.AddressPage;
import com.jxc.service.AddressService;

@Controller
@RequestMapping("/address")
@Scope("prototype")
@SessionAttributes("addressPage")
public class AddressController {
	@Resource
	private AddressService service;

	@RequestMapping("/addressList.do")
	public String findAllAction(AddressPage page, ModelMap map) {
		Integer rows = service.findRows(page);// ��ȡ���ݱ����������
		page.setRows(rows);
		map.addAttribute("addressPage", page);
		List<Address> addresses = service.findAllAddressByPage(page);// ��ҳ��ѯƷ����Ϣ
		map.put("addresses", addresses);
		return "admin/address";// ת����Ʒ����ҳ
	}

	@RequestMapping("/toAddAddress.do")
	public String toAddAction() {
		return "admin/address_add";// ת����Ʒ�����ҳ��
	}

	@RequestMapping("/addAddress.do")
	public String addAction(Address address) {
		service.addAddress(address);// ���Ʒ����Ϣ
		return "redirect:../address/addressList.do";
	}

	@RequestMapping("/toUpdateAddress.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Address address = service.findAddressById(id); 
		map.put("address", address);
		return "admin/address_update"; 
	}

	@RequestMapping("/updateAddress.do")
	public String updateAction(Address address) {
		service.updateAddress(address);// �޸�Ʒ����Ϣ
		return "redirect:../address/addressList.do";// �ض���Ʒ����ҳ
	}

	@RequestMapping("/deleteAddress.do")
	public String deleteAction(Integer id) {
		service.deleteAddressById(id);// ����idɾ��Ʒ��
		return "redirect:../address/addressList.do";// �ض���Ʒ����ҳ
	}
}
