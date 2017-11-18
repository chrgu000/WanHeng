package com.jxc.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.jxc.dao.TitleDao;
import com.jxc.entity.Invite;
import com.jxc.entity.Merchant;
import com.jxc.entity.News;
import com.jxc.entity.Order;
import com.jxc.entity.Product;
import com.jxc.entity.User;
import com.jxc.page.OrderPage;
import com.jxc.service.InviteService;
import com.jxc.service.OrderService;
import com.jxc.service.ProductService;
import com.jxc.service.UserService;
import com.jxc.util.CommonUtil;
import com.jxc.util.ConfigUtil;
import com.jxc.util.PayCommonUtil;
import com.jxc.util.XMLUtil;

@Controller
@RequestMapping("/order")
@Scope("prototype")
@SessionAttributes("orderPage")
public class OrderController {
	@Resource
	private OrderService service;
	@Resource
	private ProductService pservice;
	@Resource
	private InviteService iservice;
	@Resource
	private UserService uservice;
	@Resource
	private TitleDao dao;

	@RequestMapping("/order_list.do")
	public String findAllAction(OrderPage page, ModelMap map) {
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		map.addAttribute("orderPage", page);
		List<Order> orders = service.findAllOrderByPage(page);// 分页查询品牌信息
		map.put("orders", orders);
		return "admin/order";// 转发到品牌首页
	}

	@RequestMapping("/deleteOrder.do")
	public String deleteAction(Integer id) {
		service.deleteOrderById(id);// 根据id删除品牌
		return "redirect:../order/order_list.do";// 重定向到品牌首页
	}

	@RequestMapping("/addOrder.do")
	public String addAction(Integer product_id, HttpSession session,
			ModelMap map, HttpServletRequest request) {
		Product product = pservice.findProductById(product_id);
		Merchant merchant = product.getMerchant();
		User user = (User) session.getAttribute("user");
		map.put("product", product);
		pservice.updateProduct(product);
		Order order = new Order();
		order.setOrder_num(System.currentTimeMillis() + "");
		order.setProduct_id(product_id);
		order.setMerchant_id(merchant.getId());
		// order.setTitle_id(merchant.getTitle_id());
		if (user != null) {
			order.setUser_id(user.getId());
		}
		order.setTotal_price(0);
		order.setDays(1);
		order.setIsFree("1");
		order.setCreateTime(new Timestamp(System.currentTimeMillis()));
		order.setEndDate(product.getEndDate());
		order.setFree_num(product.getFree_num());
		order.setSpare_num(product.getFree_num());
		service.addOrder(order);
		map.put("product", product);
		map.put("order", order);
		return "mobile/invite_start";
	}

	@RequestMapping("/invite.do")
	public String inviteAction(Integer order_id, ModelMap map, String b) {
		Order order = service.findOrderById(order_id);
		Product product = order.getProduct();
		Merchant merchant = order.getMerchant();
		User user = order.getUser();
		Invite invite = new Invite();
		invite.setUser_id(user.getId());
		invite.setProduct_id(product.getId());
		List<Invite> invites = iservice.findInvitesByInvite(invite);
		map.put("merchant", merchant);
		map.put("product", product);
		map.put("invites", invites);
		map.put("user", user);
		map.put("order", order);
		map.put("num", order.getSpare_num());
		map.put("b", b);
		return "mobile/invited";
	}

	@RequestMapping("/updateOrder.json")
	public void updateAction(Integer order_id) {
		Order order = service.findOrderById(order_id);
		User user = order.getUser();
		Product product = order.getProduct();
		Invite invite = new Invite();
		invite.setUser_id(user.getId());
		invite.setProduct_id(product.getId());
		List<Invite> invites = iservice.findInvitesByInvite(invite);
		Integer num = invites.size();
		if (product.getFree_num() - num <= 0) {
			order.setIsInvite("1");
		} else {
			order.setIsInvite("2");
		}
		service.updateOrder(order);
	}

	@RequestMapping("/addOrder1.do")
	public String addOrderAction(Order order, ModelMap m, HttpSession session,
			HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String order_num = System.currentTimeMillis() + "";// 根据系统时间生成订单号
		order.setOrder_num(order_num);
		order.setStatus("1");
		order.setCreateTime(Timestamp.valueOf(sdf.format(Calendar.getInstance()
				.getTime())));// 根据系统时间添加购车时间
		Product product = pservice.findProductById(order.getProduct_id());
		order.setProduct(product);
		Merchant merchant = product.getMerchant();
		order.setMerchant_id(merchant.getId());
		order.setIsFree("0");
		User user = (User) session.getAttribute("user");
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", ConfigUtil.APPID);
		parameters.put("mch_id", "1372045302");
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
		parameters.put("body", "聚乡村" + product.getTitle());
		parameters.put("out_trade_no", order_num);
		parameters.put("total_fee", (int) (order.getTotal_price() * 100));
		// parameters.put("total_fee", 1);
		try {
			parameters.put("spbill_create_ip", InetAddress.getLocalHost()
					.getHostAddress().toString());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", user.getOpen_id());
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
		order.setUser_id(user.getId());
		order.setAppId(ConfigUtil.APPID);
		order.setTimeStamp(time);
		order.setNonceStr(nonceStr);
		order.setPackageValue("prepay_id=" + map.get("prepay_id"));
		order.setSignType(ConfigUtil.SIGN_TYPE);
		order.setSendUrl(ConfigUtil.SUCCESS_URL);
		order.setAgent(new String(new char[] { agent }));
		order.setPaySign(paySign);
		service.addOrder(order);
		session.setAttribute("order", order);
		session.setAttribute("order_id", order.getId());
		User user1 = uservice.findUserById(order.getUser_id());
		m.put("u", user);
		return "mobile/order1";
	}

	@RequestMapping("/addMoney.do")
	public String addMoneyAction(Order order, double money,
			HttpServletRequest request, HttpSession session, ModelMap ma) {
		User user = (User) session.getAttribute("user");
		String order_num = System.currentTimeMillis() + "";
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", ConfigUtil.APPID);
		parameters.put("mch_id", "1372045302");
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
		parameters.put("body", "聚乡村充值");
		parameters.put("out_trade_no", order_num);
		parameters.put("total_fee", "1");
		try {
			parameters.put("spbill_create_ip", InetAddress.getLocalHost()
					.getHostAddress().toString());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", user.getOpen_id());
		String sign = PayCommonUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		String result = CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL,
				"POST", requestXML);
		Map<String, String> map = null;
		try {
			map = XMLUtil.doXMLParse(result);
			System.out.println(map);
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
		order.setUser_id(user.getId());
		order.setAppId(ConfigUtil.APPID);
		order.setTimeStamp(time);
		order.setNonceStr(nonceStr);
		order.setPackageValue("prepay_id=" + map.get("prepay_id"));
		order.setSignType(ConfigUtil.SIGN_TYPE);
		order.setSendUrl(ConfigUtil.SUCCESS_URL);
		order.setAgent(new String(new char[] { agent }));
		order.setPaySign(paySign);
		session.setAttribute("order", order);
		ma.put("a", 1);
		ma.put("money", money);
		return "mobile/qianbao";
	}

	@RequestMapping("/updateMoney.json")
	public void updateMoneyAction(double money, HttpSession session,
			HttpServletResponse response) throws IOException {
		User user = (User) session.getAttribute("user");
		News news = new News();
		Timestamp t = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		news.setCreateTime(t);
		news.setContent(user.getNickname() + "在" + sdf.format(new Date())
				+ "充值了" + money + "元钱");
		news.setType("1");
		dao.addNews(news);
		double m = user.getMoney();
		user.setMoney(m + money);
		uservice.updateUser(user);
		System.out.println(money+":"+user.getMoney());
		response.getWriter().print(1);
	}

	@RequestMapping("/notifyOrder.do")
	public String notifyAction(HttpSession session, ModelMap map) {
		Integer order_id = Integer.parseInt(session.getAttribute("order_id")
				.toString());
		Order order = service.findOrderById(order_id);
		map.put("order", order);
		return "mobile/order4";
	}

	@RequestMapping("/cancelOrder.do")
	public String cancelOrderAction(Integer order_id, ModelMap map) {
		Order order = service.findOrderById(order_id);
		order.setStatus("2");// 已取消状态
		service.updateOrder(order);
		map.put("order", order);
		return "mobile/order2";
	}

	@RequestMapping("/getOrdersByTitleId")
	public String getOrdersAction(Integer title_id, ModelMap map) {
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("title_id", title_id);
		List<Order> orders = service.findOrdersByTitleId(m);
		map.put("orders", orders);
		map.put("m", 1);
		return "mobile/center";
	}
}
