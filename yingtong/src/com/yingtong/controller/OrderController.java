package com.yingtong.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom.JDOMException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yingtong.entity.Article;
import com.yingtong.entity.Car;
import com.yingtong.entity.Order;
import com.yingtong.entity.User;
import com.yingtong.page.OrderPage;
import com.yingtong.page.Page;
import com.yingtong.service.ArticleService;
import com.yingtong.service.CarService;
import com.yingtong.service.OrderService;
import com.yingtong.service.UserService;
import com.yingtong.util.AlipayNotify;
import com.yingtong.util.CommonUtil;
import com.yingtong.util.ConfigUtil;
import com.yingtong.util.HttpUtil;
import com.yingtong.util.PayCommonUtil;
import com.yingtong.util.XMLUtil;

@Controller
@Scope("prototype")
@RequestMapping("/order")
@SessionAttributes("orderPage")
public class OrderController {
	@Resource
	private OrderService service;// 订单服务对象
	@Resource
	private CarService cservice;// 车辆服务对象
	@Resource
	private UserService uservice;// 用户服务对象
	@Resource
	private ArticleService aservice;// 文章服务对象

	@RequestMapping("/order_list.do")
	public String findAllOrderAction(OrderPage page, ModelMap map) {
		Integer rows = service.findRows(page);
		page.setRows(rows);
		List<Order> orders = service.findAllOrderByPage(page);// 获取所有订单
		map.put("orderPage", page);
		map.put("orders6", orders);
		return "admin/order";
	}

	@RequestMapping("/addOrder.do")
	public String addAction(Order order, HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String order_num = System.currentTimeMillis() + "";// 根据系统时间生成订单号
		order.setOrder_num(order_num);
		order.setCar_buy_time(Timestamp.valueOf(sdf.format(Calendar
				.getInstance().getTime())));// 根据系统时间添加购车时间
		User user = (User) session.getAttribute("user");
		order.setUser_id(user.getId());
		order.setOpenid(user.getOpen_id());
		// //////////////////////////////////////////////////////////////////
		String appid = ConfigUtil.APPID; // appid
		// String appsecret = PayConfigUtil.APP_SECRET; // appsecret
		String mch_id = ConfigUtil.MCH_ID; // 商业号
		String key = ConfigUtil.API_KEY; // key

		String currTime = PayCommonUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = PayCommonUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;
		String order_price = (int) (order.getTotal_price() * 100) + ""; // 价格
																		// 注意：价格的单位是分
		String body = "盈通租车"; // 商品名称
		String out_trade_no = order_num; // 订单号
		// 获取发起电脑 ip
		String spbill_create_ip = null;
		try {
			spbill_create_ip = InetAddress.getLocalHost().getHostAddress()
					.toString();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 回调接口
		String notify_url = ConfigUtil.NOTIFY_URL;
		String trade_type = "NATIVE";
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("total_fee", order_price);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
		packageParams.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(packageParams);
		String resXml = HttpUtil.postData(ConfigUtil.UNIFIED_ORDER_URL,
				requestXML);
		Map map = null;
		try {
			map = XMLUtil.doXMLParse(resXml);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// String return_code = (String) map.get("return_code");
		// String prepay_id = (String) map.get("prepay_id");
		String urlCode = (String) map.get("code_url");
		order.setUrlCode(urlCode);
		// ///////////////////////////////////////////////////////////////
		service.addOrder(order);
		session.setAttribute("order", order);
		return "redirect:../order/showOrder1.do";
	}

	@RequestMapping("/addOrder1.do")
	public String addOrderAction(Order order, HttpSession session,
			HttpServletRequest request) {
		session.removeAttribute("selectCarInfo1");
		User user = (User) session.getAttribute("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String order_num = System.currentTimeMillis() + "";// 根据系统时间生成订单号
		order.setOrder_num(order_num);
		order.setCar_buy_time(Timestamp.valueOf(sdf.format(Calendar
				.getInstance().getTime())));// 根据系统时间添加购车时间
		order.setOpenid(user.getOpen_id());
		order.setUser_id(user.getId());
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", ConfigUtil.APPID);
		parameters.put("mch_id", "1363620102");
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
		parameters.put("body", order_num.substring(0, 5));
		parameters.put("out_trade_no", order_num);
	   parameters.put("total_fee", (int) (order.getTotal_price() * 100));
		//parameters.put("total_fee", 1);
		try {
			parameters.put("spbill_create_ip", InetAddress.getLocalHost()
					.getHostAddress().toString());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		parameters.put("notify_url", ConfigUtil.NOTIFY_URL1);
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", order.getOpenid());
		String sign = PayCommonUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		String result = CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL,
				"POST", requestXML);
		Map<String, String> map = null;
		try {
			map = XMLUtil.doXMLParse(result);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}// 解析微信返回的信息，以Map形式存储便于取值
		SortedMap<Object, Object> params = new TreeMap<Object, Object>();
		params.put("appId", ConfigUtil.APPID);
		String time = Long.toString(new Date().getTime());
		params.put("timeStamp", time);
		String nonceStr = PayCommonUtil.CreateNoncestr();
		params.put("nonceStr", nonceStr);
		params.put("package", "prepay_id=" + map.get("prepay_id"));
		params.put("signType", ConfigUtil.SIGN_TYPE);
		String paySign = PayCommonUtil.createSign("UTF-8", params);
		String userAgent = request.getHeader("user-agent");
		char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger") + 15);
		order.setUser_id(((User) session.getAttribute("user")).getId());
		order.setAppId(ConfigUtil.APPID);
		order.setTimeStamp(time);
		order.setNonceStr(nonceStr);
		order.setPackageValue("prepay_id=" + map.get("prepay_id"));
		order.setSignType(ConfigUtil.SIGN_TYPE);
		order.setSendUrl(ConfigUtil.SUCCESS_URL1);
		order.setAgent(new String(new char[] { agent }));
		order.setPaySign(paySign);
		service.addOrder(order);
		session.setAttribute("order", order);
		return "redirect:../order/showOrderM1.do";
	}

	@RequestMapping("/showOrder.do")
	public String showOrderAction(OrderPage page, ModelMap map,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Order> orders = null;
		if (user != null) {
			page.setUser_id(user.getId());
			orders = service.findOrdersByPage(page);
		} else {
			map.put("order", "t");
			return "index/order";
		}
		List<Article> articles = aservice.findArticlesByTitleId(4);// 获取会员条款
		map.put("status", page.getStatus());
		map.put("articles5", articles);
		map.put("orders", orders);
		return "index/order";
	}

	@RequestMapping("/showOrderCenter.do")
	public String showOrderCenterAction(OrderPage page, ModelMap map,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Order> orders = null;
		page.setUser_id(user.getId());
		orders = service.findOrdersByPage(page);
		map.put("status", page.getStatus());
		map.put("orders", orders);
		return "mobile/myorder";
	}

	@RequestMapping("/showOrderM1.do")
	// 等待付款
	public String showOrderM1Action(ModelMap map, HttpSession session, Integer m) {
		Order order = (Order) session.getAttribute("order");
		if (order != null) {
			Car car = cservice.findCarById(order.getCar_id());
			User user = uservice.findUserById(order.getUser_id());
			order.setStatus("1");// 等待付款状态
			map.put("order", order);
			map.put("car2", car);
			map.put("user2", user);
		}
		map.put("m", m);
		return "mobile/order1";
	}

	@RequestMapping("/showOrder1.do")
	// 等待付款
	public String showOrder1Action(ModelMap map, HttpSession session, Integer m) {
		Order order = (Order) session.getAttribute("order");
		if (order != null) {
			Car car = cservice.findCarById(order.getCar_id());
			User user = uservice.findUserById(order.getUser_id());
			order.setStatus("1");// 等待付款状态
			map.put("order", order);
			map.put("car2", car);
			map.put("user2", user);
		}
		map.put("m", m);
		return "index/order1";
	}

	@RequestMapping("/showOrder2.do")
	// 已取消
	public String showOrderM2Action(ModelMap map, Integer order_id) {
		Order order = service.findOrderById(order_id);
		order.setStatus("2");// 已取消状态
		service.updateOderStatus(order);
		map.put("order2", order);
		return "index/order2";
	}

	@RequestMapping("/showOrderM2.do")
	// 已取消
	public String showOrder2Action(ModelMap map, Integer order_id) {
		Order order = service.findOrderById(order_id);
		order.setStatus("2");// 已取消状态
		service.updateOderStatus(order);
		map.put("order2", order);
		return "mobile/order2";
	}

	@RequestMapping("/zhifu.do")
	// 提交成功，待支付
	public String zhiFuAction(Integer order_id, ModelMap map,
			HttpSession session) {
		if (order_id == null) {
			order_id = Integer.parseInt(session.getAttribute("order_id")
					.toString());
		}
		Order order = service.findOrderById(order_id);
		Car car = order.getCar();
		List<Order> orders = service.findOrdersByCarId(car.getId());
		if ((car.getNum() - orders.size()) == 1) {
			car.setRent_status("1");
			cservice.updateCarRentStatus(car);
		} else if (car.getNum() - orders.size() <= 0) {
			return "redirect:../order/showOrder1.do?m=1";
		}
		if (order_id != null) {
			session.setAttribute("order_id", order_id);
		}
		try {
			map.put("orderzf", order);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "index/zhifu";
	}

	@RequestMapping("/zhifuM.do")
	// 提交成功，待支付
	public String zhiFuMAction(Integer order_id, ModelMap map,
			HttpSession session) {
		if (order_id == null) {
			order_id = Integer.parseInt(session.getAttribute("order_id")
					.toString());
		}
		Order order = service.findOrderById(order_id);
		Car car = order.getCar();
		List<Order> orders = service.findOrdersByCarId(car.getId());
		if ((car.getNum() - orders.size()) == 1) {
			car.setRent_status("1");
			cservice.updateCarRentStatus(car);
		} else if (car.getNum() - orders.size() <= 0) {
			return "redirect:../order/showOrderM1.do?m=1";
		}

		if (order_id != null) {
			session.setAttribute("order_id", order_id);
		}
		session.setAttribute("short_rent", "true");
		map.put("orderzf", order);
		return "mobile/zhifu";
	}

	@RequestMapping("/notifyOrder.do")
	public String notifyAction(HttpServletRequest request, HttpSession session,
			ModelMap map) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			params.put(name, valueStr);
		}
		boolean verify_result = AlipayNotify.verify(params);
		if (verify_result) {// 支付宝验证支付成功
			Object orderid = session.getAttribute("order_id");
			Integer order_id = null;
			if (orderid != null) {
				order_id = Integer.parseInt(orderid.toString());
				Order order = service.findOrderById(order_id);
				order.setStatus("3");// 租赁中状态
				service.updateOderStatus(order);
				map.put("order6", order);
			}
			String short_rent = (String) session.getAttribute("short_rent");
			if (short_rent != null && short_rent.equals("true")) {
				return "mobile/zhifu_notify";
			} else {
				return "index/zhifu_notify";
			}
		} else {
			return "redirect:../order/zhifu.do";
		}
	}

	@RequestMapping("/notifyOrder1.do")
	public void notify1Action(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws IOException, JDOMException {
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		String result = new String(outSteam.toByteArray(), "utf-8");//
		// 获取微信调用我们notify_url的返回信息
		Map<Object, Object> map = XMLUtil.doXMLParse(result);
		String order_num = (String) map.get("out_trade_no");
		Order order = service.findOrderByOrderNum(order_num);
		order.setStatus("3");// 租赁中状态
		service.updateOderStatus(order);
		outSteam.close();
		inStream.close();
		response.getWriter().print(PayCommonUtil.setXML("SUCCESS", "")); //
		// 告诉微信服务器，我收到信息了，不要在调用回调action了
	}

	@RequestMapping("/notifyOrder2.do")
	public String notify2Action(HttpServletRequest request,
			HttpSession session, HttpServletResponse response)
			throws IOException, JDOMException {
		response.setContentType("text/html;charset=utf-8");
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		String result = new String(outSteam.toByteArray(), "utf-8");//
		// 获取微信调用我们notify_url的返回信息
		Map<Object, Object> map = XMLUtil.doXMLParse(result);
		if (map != null) {
			String order_num = (String) map.get("out_trade_no");
			Order order = service.findOrderByOrderNum(order_num);
			if (order != null) {
				order.setStatus("3");// 租赁中状态
			}
			service.updateOderStatus(order);
		}
		outSteam.close();
		inStream.close();
		response.getWriter().print(PayCommonUtil.setXML("SUCCESS", "")); //
		return "mobile/zhifu_notify";
	}

	@RequestMapping("/checkPay.json")
	public void checkPayAction(Integer id, HttpServletResponse response)
			throws IOException {
		Order order = service.findOrderById(id);
		if (order.getStatus() != null && order.getStatus().equals("3")) {
			response.getWriter().print(3);
		} else {
			response.getWriter().print(1);
		}
	}

	@RequestMapping("/payFinished.do")
	public String payAction(ModelMap map, Integer id) {
		Order order = service.findOrderById(id);
		map.put("order6", order);
		return "index/zhifu_notify";
	}

	@RequestMapping("/confirmFinished.do")
	public String confirmFinishedAction(Integer id) {
		Order order = service.findOrderById(id);
		order.setStatus("4");// 确认完成租车交易
		service.updateOderStatus(order);
		Car car = order.getCar();
		List<Order> orders=service.findOrdersByCarId(car.getId());
		if(car.getNum()-orders.size()>=1){
			car.setRent_status("0");
		}
		cservice.updateCarRentStatus(car);
		return "redirect:../order/order_list.do";
	}

	@RequestMapping("/deleteOrderById.do")
	public String deleteAction(Integer id) {
		service.deleteOrderById(id);
		return "redirect:../order/order_list.do";
	}

	@RequestMapping("/checkOrder.do")
	public String checkOrderAction(Integer order_id, String status, ModelMap map) {
		Order order = service.findOrderById(order_id);
		if (status != null && !status.equals("")) {
			order.setStatus(status);// 设置订单交易状态
			service.updateOderStatus(order);
		}
		map.put("check_order", order);
		return "index/check_order";
	}

	@RequestMapping("/getMyFaPiao.do")
	public String getMyFaPiaoAction(ModelMap map, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Order> orders = service.findOrderByUserId(user.getId());
		List<Order> fapiao = new ArrayList<Order>();
		boolean isHas = false;
		for (Order order : orders) {
			if (order.getTel() != null && !order.getTel().equals("")) {
				fapiao.add(order);
				isHas = true;
			}
		}
		if (!isHas) {
			map.put("msg", "您还没有开具发票!");
		}
		map.put("orders", fapiao);
		return "mobile/myfapiao";
	}

	@RequestMapping("/toAddFaPiao.do")
	public String toAddFapiaoAction(ModelMap map, HttpSession session) {
		boolean isHas = false;
		User user = (User) session.getAttribute("user");
		List<Order> fapiao = new ArrayList<Order>();
		List<Order> orders = service.findOrderByUserId(user.getId());
		for (Order order : orders) {
			if (order.getTel() == null || order.getTel().equals("")) {
				fapiao.add(order);
				isHas = true;
			}
		}
		if (!isHas) {
			map.put("msg", "没有订单供发票选择!");
		}
		map.put("orders", fapiao);

		return "mobile/addmyfapiao";
	}

	@RequestMapping("/addFapiao.do")
	public String addFapiaoAction(Order order, ModelMap map) {
		service.updateOrder(order);
		map.put("msg1", "发票成功增加");
		return "mobile/addmyfapiao";
	}
}
