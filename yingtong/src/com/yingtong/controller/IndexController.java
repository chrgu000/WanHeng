package com.yingtong.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yingtong.dao.TitleAddrDao;
import com.yingtong.entity.Address;
import com.yingtong.entity.Car;
import com.yingtong.entity.TitleAddr;
import com.yingtong.service.AddressService;
import com.yingtong.service.CarService;

@Controller
@RequestMapping("/index")
@Scope("prototype")
public class IndexController {
	@Resource
	private CarService service;//汽车服务对象
	@Resource
	private AddressService aservice;//地址服务对象
	@Resource
	private TitleAddrDao dao;//市区服务对象

	@RequestMapping("/index.do")
	public String indexAction(ModelMap map, HttpServletRequest request) {
		String state = "1";
		List<Car> cars = service.findIndexCarByState(state);//获取在首页的车辆
		List<Address> addresses = aservice.findAllAddressByTitleAddrId(1);//获取市区id为1的所有地址
		List<TitleAddr> titleAddrs = dao.findAllTitleAddr();//获取所有市区信息
		map.put("cars", cars);
		map.put("addresses", addresses);
		map.put("titleAddrs", titleAddrs);
		return "index/index";
	}

	@RequestMapping(value = "/getAddress.json", produces = "application/json;charset=UTF-8")
	public void getAddressAction(Integer titleAddrId,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		List<Address> address = aservice
				.findAllAddressByTitleAddrId(titleAddrId);//根据市区id获取地址信息
		JSONArray jsonArray = JSONArray.fromObject(address);
		response.getWriter().print(jsonArray);
	}
}
