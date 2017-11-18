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
	private CarService service;//�����������
	@Resource
	private AddressService aservice;//��ַ�������
	@Resource
	private TitleAddrDao dao;//�����������

	@RequestMapping("/index.do")
	public String indexAction(ModelMap map, HttpServletRequest request) {
		String state = "1";
		List<Car> cars = service.findIndexCarByState(state);//��ȡ����ҳ�ĳ���
		List<Address> addresses = aservice.findAllAddressByTitleAddrId(1);//��ȡ����idΪ1�����е�ַ
		List<TitleAddr> titleAddrs = dao.findAllTitleAddr();//��ȡ����������Ϣ
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
				.findAllAddressByTitleAddrId(titleAddrId);//��������id��ȡ��ַ��Ϣ
		JSONArray jsonArray = JSONArray.fromObject(address);
		response.getWriter().print(jsonArray);
	}
}
