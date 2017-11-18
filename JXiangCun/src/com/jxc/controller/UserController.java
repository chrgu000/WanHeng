package com.jxc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.jxc.entity.Invite;
import com.jxc.entity.Inviter;
import com.jxc.entity.Order;
import com.jxc.entity.Product;
import com.jxc.entity.User;
import com.jxc.page.UserPage;
import com.jxc.service.InviteService;
import com.jxc.service.MerchantService;
import com.jxc.service.OrderService;
import com.jxc.service.ProductService;
import com.jxc.service.UserService;
import com.jxc.util.DateUtil;
import com.jxc.util.GetWX;

@Controller
@RequestMapping("/user")
@Scope("prototype")
@SessionAttributes("userPage")
public class UserController {
	@Resource
	private UserService service;
	@Resource
	private ProductService pservice;
	@Resource
	private MerchantService mservice;
	@Resource
	private InviteService tservice;
	@Resource
	private OrderService oservice;
	@Resource
	private InviteService iservice;

	@RequestMapping("/user_list.do")
	public String findAllAction(UserPage page, ModelMap map) {
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		map.addAttribute("userPage", page);
		List<User> users = service.findAllUserByPage(page);// 分页查询品牌信息
		map.put("users", users);
		return "admin/user";// 转发到品牌首页
	}

	@RequestMapping("/deleteUser.do")
	public String deleteAction(Integer id) {
		service.deleteUserById(id);// 根据id删除品牌
		return "redirect:../user/user_list.do";// 重定向到品牌首页
	}

	@RequestMapping("/getTelCode.do")
	public void getTelAction(String tel, HttpSession session,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		Random rand = new Random();
		Integer num = (int) (rand.nextInt(899999) + 100000);// 生成随机验证码
		session.setAttribute("num", num);
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("aaf98f894d7439d8014d9370459d1636",
				"c30590757309461e868aee414872fc65");// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId("8aaf0708559f32dd0155a4d65a9904fe");// 初始化应用ID
		result = restAPI.sendTemplateSMS(tel, "106576",
				new String[] { num + "" });
		if ("000000".equals(result.get("statusCode"))) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}
	}

	@RequestMapping("/checkTelCode.do")
	public String checkTelCodeAction(String num, String tel,
			Integer product_id, ModelMap map, HttpSession session) {
		String code = session.getAttribute("num") + "";
		// if (num!=null&&num.equals(code)) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			String openId = user.getOpen_id();
			user = service.findUserByOpenId(openId);
			if (!tel.equals(user.getTel())) {
				user.setTel(tel);
				service.updateUser(user);
			}
		}
		Product product = pservice.findProductById(product_id);
		map.put("product", product);
		if (product.getIsFree().equals("1")) {
			return "mobile/submit_free";
		} else {
			List<Map> dates=DateUtil.getCalendar();
			map.put("dates", dates);
			return "mobile/submit";
		}
		// } else {
		// Product product=pservice.findProductById(product_id);
		// List<Product>
		// products=pservice.findProductsByMerchantId(product.getMerchant().getId());
		// Merchant
		// merchant=mservice.findMerchantById(product.getMerchant().getId());
		// map.put("products", products);
		// map.put("merchant", merchant);
		// map.put("message", "验证码输入错误");
		// return "mobile/merchant";
		// }
	}
@RequestMapping("/toSubmit.do")
public String toSubmitAction(Integer product_id,ModelMap map){
	Product product = pservice.findProductById(product_id);
	map.put("product", product);
	if (product.getIsFree().equals("1")) {
		return "mobile/submit_free";
	} else {
		List<Map> dates=DateUtil.getCalendar();
		map.put("dates", dates);
		return "mobile/submit";
	}
}
	@RequestMapping("/intoStart.do")
	public String intoStartAction(ModelMap map, HttpSession session,
			Invite invite, String state) {
		session.setAttribute("invite", invite);
		return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ GetWX.getPro("appid")
				+ "&redirect_uri=http%3a%2f%2f"
				+ GetWX.getPro("yuming")
				+ "%2fuser%2fstart.do&response_type=code&scope=snsapi_userinfo&state="
				+ state + "#wechat_redirect";
	}

	@RequestMapping("/start.do")
	public String startAction(String code, String state, ModelMap maps,
			HttpServletRequest request, HttpSession session) {
		String turl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ GetWX.getPro("appid")
				+ "&secret="
				+ GetWX.getPro("secret")
				+ "&code=" + code + "&grant_type=authorization_code";
		HttpClient client = new DefaultHttpClient();
		Map<String, Object> map = new HashMap<String, Object>();
		HttpGet get = new HttpGet(turl);
		String token = "";
		String openid = "";
		String headimgurl = "";
		String nickname = "";
		int error = 0;
		try {
			HttpResponse res = client.execute(get);
			String responseContent = null;
			HttpEntity entity = res.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
			JSONObject json = new JSONObject(responseContent);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (responseContent.indexOf("access_token") == -1) {
					error = 1;
				} else {
					token = json.get("access_token").toString();
					openid = json.get("openid").toString();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			get.releaseConnection();
			client.getConnectionManager().shutdown();
			get = null;
			client = null;
		}
		if (error == 1) {
			return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ GetWX.getPro("appid")
					+ "&redirect_uri=http%3a%2f%2f"
					+ GetWX.getPro("yuming")
					+ "%2fuser%2fstart.do&response_type=code&scope=snsapi_userinfo&state="
					+ state + "#wechat_redirect";
		} else {
			String turl1 = "https://api.weixin.qq.com/sns/userinfo?access_token="
					+ token + "&openid=" + openid + "&lang=zh_CN";
			HttpClient client1 = new DefaultHttpClient();
			HttpGet get1 = new HttpGet(turl1);
			try {
				HttpResponse res = client1.execute(get1);
				String responseContent = null;
				HttpEntity entity = res.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
				JSONObject json = new JSONObject(responseContent);
				if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					if (json.length() <= 3) {
					} else {
						nickname = json.get("nickname").toString();
						headimgurl = json.get("headimgurl").toString();
					}
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				get1.releaseConnection();
				client1.getConnectionManager().shutdown();
				get1 = null;
				client1 = null;
			}
		}
		Invite invite = (Invite) session.getAttribute("invite");
		Order order = oservice.findOrderById(invite.getOrder_id());
		if (order.getUser().getOpen_id().equals(openid)) {
			session.setAttribute("msg", "你是邀请者，不能支持自己!");
		} else {
			invite.setOpen_id(openid);
			Inviter it = tservice.findInviterByInvite(invite);
			if (it == null) {
				Inviter inviter = new Inviter();
				inviter.setOpen_id(openid);
				inviter.setNickname(nickname);
				inviter.setUrl(headimgurl);
				tservice.addInviter(inviter);
				invite.setInvite_id(inviter.getId());
				invite.setCreateDate(new Timestamp(System.currentTimeMillis()));
				tservice.addInvite(invite);
				User user = order.getUser();
				Product product = order.getProduct();
				invite.setUser_id(user.getId());
				invite.setProduct_id(product.getId());
				List<Invite> invites = iservice.findInvitesByInvite(invite);
				Integer num = invites.size();
				order.setSpare_num(order.getFree_num() - num);
				if (order.getSpare_num().equals(0)) {
					order.setIsInvite("1");
				}
				oservice.updateOrder(order);
				session.setAttribute("msg", "支持成功!");
			} else {
				session.setAttribute("msg", "你已经支持过!");
			}
		}
		if (!state.equals("null")) {
			Integer s = Integer.parseInt(state);
			User user = (User) session.getAttribute("user");
			if (user == null) {
				user = new User();
			}
			Timestamp t = new Timestamp(System.currentTimeMillis());
			user.setRegist_time(t);
			user.setOpen_id(openid);
			user.setNickname(nickname);
			user.setPath(headimgurl);
			User u = service.findUserByOpenId(openid);
			if (u == null) {
				service.addUser(user);
				session.setAttribute("user", user);
			} else {
				session.setAttribute("user", u);
			}

			return "redirect:../product/getProductsByMerchantId.do?merchant_id="
					+ s;
		}
		return "redirect:../order/invite.do?order_id=" + invite.getOrder_id()
				+ "&b=1";
	}
}
