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
	private AddressService service;// 地址服务对象

	@RequestMapping("/address_list.do")
	public String findAllAction(ModelMap map, Page page, HttpSession session) {
		session.setAttribute("titleAddr_id", page.getTitleAddr_id());// session保存市区ID
		Integer rows = service.findRowsByTitleAddrId(page.getTitleAddr_id());// 获取按照市区ID下的数据行数
		page.setRows(rows);
		List<Address> addresses = service.findAllAdressByPageTitleAddrId(page);// 获取按照市区ID下分页查询的地址信息
		map.put("addresses", addresses);
		return "admin/address";// 转发到地址信息首页
	}

	@RequestMapping("/toAddAddress.do")
	public String toAddAction(ModelMap map, Integer titleAddr_id) {
		map.put("titleAddr_id", titleAddr_id);
		return "admin/address_add";// 转发到地址添加页面
	}

	@RequestMapping("/addAddress.do")
	public String addAction(Address address) {
		service.addAddress(address);// 添加地址信息
		return "redirect:address_list.do";// 重定向地址信息首页
	}

	@RequestMapping("/toUpdateAddress.do")
	public String toUpdateAction(Integer id, ModelMap map) {
		Address address = service.findAddressById(id);
		map.put("address", address);
		return "admin/address_update";// 转发到地址修改页面
	}

	@RequestMapping("/updateAddress.do")
	public String updateAction(Address address) {
		service.updateAddress(address);// 修改地址信息
		return "redirect:address_list.do";// 重定向地址信息首页
	}

	@RequestMapping("deleteAddressById.do")
	public String deleteAction(Integer id) {
		service.deleteAddressById(id);// 根据Id删除地址信息
		return "redirect:address_list.do";// 重定向地址信息首页
	}
}
