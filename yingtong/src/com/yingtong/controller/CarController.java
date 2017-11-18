package com.yingtong.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yingtong.dao.BrandDao;
import com.yingtong.dao.PriceDao;
import com.yingtong.dao.TitleAddrDao;
import com.yingtong.dao.VehicleDao;
import com.yingtong.entity.Address;
import com.yingtong.entity.Brand;
import com.yingtong.entity.Car;
import com.yingtong.entity.Order;
import com.yingtong.entity.Price;
import com.yingtong.entity.TitleAddr;
import com.yingtong.entity.User;
import com.yingtong.entity.Vehicle;
import com.yingtong.page.CarPage;
import com.yingtong.page.SelectCar;
import com.yingtong.service.AddressService;
import com.yingtong.service.CarService;
import com.yingtong.service.OrderService;

@Controller
@RequestMapping("/car")
@Scope("prototype")
@SessionAttributes("carPage")
public class CarController {
	@Resource
	private OrderService oservice;
	@Resource
	private CarService service;// 车辆服务对象
	@Resource
	private AddressService aservice;// 地址服务对象
	@Resource
	private PriceDao pdao;// 价格接口
	@Resource
	private VehicleDao vdao;// 车型接口
	@Resource
	private BrandDao bdao;// 品牌接口
	@Resource
	private TitleAddrDao tadao;// 市区接口

	@RequestMapping("/car_list.do")
	public String findAllAction(CarPage page, HttpSession session, ModelMap map) {
		session.setAttribute("titleAddr_id", page.getTitleAddr_id());// session保存市区id
		Integer rows = service.findRowsByTitleAddrId(page);// 根据市区id查询数据表数据行数
		page.setRows(rows);
		map.addAttribute("carPage", page);
		List<Car> cars = service.findAllCarByPageAndTitleAddrId(page);// 根据市区id分页查询车辆信息
		for (Car car : cars) {
			List<Order> order=oservice.findOrdersByCarId(car.getId());
			car.setSpare_num(car.getNum()-order.size());
		}
		List<Brand> brands = bdao.findAllBrand();
		List<Price> prices = pdao.findAllPrice();
		map.put("cars", cars);
		map.put("brands", brands);
		map.put("prices", prices);
		return "admin/car";// 转发到车辆首页
	}

	@RequestMapping("/toUpdateCar.do")
	public String toUpdateAction(Integer id, Integer titleAddr_id, ModelMap map) {
		Car car = service.findCarById(id);// 根据id查找车辆信息
		List<Address> addresses = aservice
				.findAllAddressByTitleAddrId(titleAddr_id);// 查找该市区下的地址信息
		List<Price> prices = pdao.findAllPrice();// 获取所有价格
		List<Vehicle> vehicles = vdao.findAllVehicle();// 获取所有车型
		List<Brand> brands = bdao.findAllBrand();// 获取所有品牌
		TitleAddr titleAddr = tadao.findTitleAddrById(titleAddr_id);// 根据市区id获取市区对象
		map.put("car", car);
		map.put("addresses", addresses);
		map.put("prices", prices);
		map.put("vehicles", vehicles);
		map.put("brands", brands);
		map.put("tilteAddr", titleAddr);
		return "admin/car_update";// 重定向到车辆信息修改页面
	}

	@RequestMapping("/updateCar.do")
	public String updateAction(Car car) {
		if(car.getNum()==0){
			car.setRent_status("1");
		}else{
			car.setRent_status("0");
		}
		service.updateCar(car);// 修改车辆信息
		return "redirect:../car/car_list.do";// 重定向到车辆信息首页
	}

	@RequestMapping("/toAddCar.do")
	public String toAddAction(Integer titleAddr_id, HttpSession session,
			ModelMap map) {
		List<Address> addresses = aservice
				.findAllAddressByTitleAddrId(titleAddr_id);// 查找该市区下的地址信息
		List<Price> prices = pdao.findAllPrice();// 获取所有价格
		List<Vehicle> vehicles = vdao.findAllVehicle();// 获取所有车型
		List<Brand> brands = bdao.findAllBrand();// 获取所有品牌
		TitleAddr titleAddr = tadao.findTitleAddrById(titleAddr_id);// 根据市区id获取市区对象
		map.put("prices", prices);
		map.put("vehicles", vehicles);
		map.put("brands", brands);
		map.put("titleAddr", titleAddr);
		map.put("addresses", addresses);
		return "admin/car_add";// 重定向到车辆信息添加页面
	}

	@RequestMapping("/addCar.do")
	public String addAction(Car car) {
		Integer num=car.getNum();
		if(num==0){
			car.setRent_status("1");
		}else{
			car.setRent_status("0");
		}
		service.addCar(car);// 添加车辆信息
		return "redirect:../car/car_list.do";// 重定向到车辆信息首页
	}

	@RequestMapping("/deleteCarById.do")
	public String deleteAction(Integer id) {
		service.deleteCarById(id);// 根据id删除车辆信息
		return "redirect:../car/car_list.do";// 重定向到车辆信息首页
	}

	/**
	 * 线程安全问题的有待解决
	 * 
	 * @param car
	 * @param login1
	 * @param session
	 * @param map
	 * @param short_rent
	 * @return
	 */
	@RequestMapping("/duanzu.do")
	public String duanzuAction(SelectCar car, String login1,
			HttpSession session, ModelMap map) {
		if (login1 != null && login1.equals("1")) {
			car = (SelectCar) session.getAttribute("selectCar1");
		}
		List<Car>  cars = service.findCarsBySelectCar(car);// 根据条件获取所车辆
		List<Brand> brands = bdao.findAllBrand();// 获取所有品牌
		Integer brow = bdao.findRow();
		List<Vehicle> vehicles = vdao.findAllVehicle();// 获取所有车型
		Integer vrow = vdao.findRows();
		List<Price> prices = pdao.findAllPrice();// 获取所有价格类型
		Integer prow = pdao.findRows();
		List<TitleAddr> titleAddrs = tadao.findAllTitleAddr();// 获取所有市区
		List<Address> addresses = aservice.findAllAddressByTitleAddrId(1);// 获取市区id为1下的所有地址
		map.put("cars1", cars);
		map.put("brands", brands);
		map.put("vehicles", vehicles);
		map.put("prices", prices);
		map.put("titleAddrs", titleAddrs);
		map.put("addresses", addresses);
		session.setAttribute("car3", car);
		session.setAttribute("prow", prow);
		session.setAttribute("brow", brow);
		session.setAttribute("vrow", vrow);
		return "index/zijia";
	}

	@RequestMapping("/rentCar.do")
	public String rentCarAction(SelectCar car, String BTime, String STime,
			String BAddr, String SAddr, Integer id, HttpSession session,
			ModelMap map) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			car.setBuyTime(BTime);
			car.setSendTime(STime);
			car.setSendAddr(SAddr);
			car.setBuyAddr(BAddr);
			List<Car> cars = service.findCarsBySelectCar(car);// 根据条件获取所车辆
			List<Brand> brands = bdao.findAllBrand();// 获取所有品牌
			Integer brow = bdao.findRow();
			List<Vehicle> vehicles = vdao.findAllVehicle();// 获取所有车型
			Integer vrow = vdao.findRows();
			List<Price> prices = pdao.findAllPrice();// 获取所有价格类型
			Integer prow = pdao.findRows();
			map.put("cars1", cars);
			map.put("brands", brands);
			map.put("vehicles", vehicles);
			map.put("prices", prices);
			map.put("info", "1");
			session.setAttribute("selectCar1", car);
			session.setAttribute("prices", prices);
			session.setAttribute("prow", prow);
			session.setAttribute("brow", brow);
			session.setAttribute("vrow", vrow);
			return "index/zijia";
		} else {
			Car c = service.findCarById(id);
			session.setAttribute("car", c);
			car.setBuyTime(BTime);
			car.setSendTime(STime);
			car.setSendAddr(SAddr);
			car.setBuyAddr(BAddr);
			session.setAttribute("selectCar", car);
			return "redirect:../index/dingdan.jsp";
		}
	}

	@RequestMapping("/rentCar1.do")
	public String rentCarAction(HttpSession session, Integer car_id,
			ModelMap map) {
		SelectCar selectCar=(SelectCar) session.getAttribute("selectCarInfo1");
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:../mobile/login.jsp";
		}else if(selectCar==null){
			return "redirect:../car/duanzu1.do?message=1";
		}	else {
			Car car = service.findCarById(car_id);
			map.put("rent_car", car);
			return "mobile/order";
		}
	}

	@RequestMapping("/duanzu1.do")
	public String duanzu1Action(SelectCar car, ModelMap map, String short_rent,
			HttpSession session,String message) {
		List<Car> cars = null;
		if (short_rent != null && short_rent.equals("1")) {// 根据条件查询过来的短租自驾首页
			SelectCar car1 = (SelectCar) session.getAttribute("selectCarInfo");
			car1.setBuyTime(car.getBuyTime().toString());
			car1.setSendTime(car.getSendTime().toString());
			car1.setBuyAddr(car.getBuyAddr());
			car1.setSendAddr(car.getSendAddr());
			car1.setDays(car.getDays());
			session.setAttribute("selectCarInfo1", car1);
			cars = service.findCarsBySelectCar(car1);
		} else if (short_rent != null && short_rent.equals("0")) {// 登录过来的短租自驾首页
			SelectCar car2 = (SelectCar) session.getAttribute("selectCarInfo1");
			cars = service.findCarsBySelectCar(car2);
		} else {
			cars = service.findCarsBySelectCar(car);// 从首页跳转过来的短租自驾首页、根据条件获取所车辆
		}
		List<Brand> brands = bdao.findAllBrand();// 获取所有品牌
		List<Vehicle> vehicles = vdao.findAllVehicle();// 获取所有车型
		List<Price> prices = pdao.findAllPrice();// 获取所有价格类型
		map.put("cars2", cars);
		map.put("brands", brands);
		map.put("vehicles", vehicles);
		map.put("prices", prices);
		if(message!=null&&message.equals("1")){
		return "redirect:../car/getRentCar.do";
		}
		return "mobile/duanzu";
	}

	@RequestMapping("/getRentCar.do")
	public String getRentCarAction(SelectCar car, HttpSession session) {
		session.setAttribute("selectCarInfo", car);
		return "redirect:../car/getRentCar2.do";
	}

	@RequestMapping("/getRentCar2.do")
	public String duanzu2Action(ModelMap map) {
		List<TitleAddr> titleAddrs = tadao.findAllTitleAddr();// 获取所有市区
		List<Address> addresses = aservice.findAllAddressByTitleAddrId(1);// 获取市区id为1下的所有地址
		map.put("titleAddrs", titleAddrs);
		map.put("addresses", addresses);
		return "mobile/duanzu1";
	}
}
