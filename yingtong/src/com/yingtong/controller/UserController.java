package com.yingtong.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
import com.yingtong.entity.Article;
import com.yingtong.entity.Car;
import com.yingtong.entity.Order;
import com.yingtong.entity.Phone;
import com.yingtong.entity.User;
import com.yingtong.page.UserPage;
import com.yingtong.service.ArticleService;
import com.yingtong.service.CarService;
import com.yingtong.service.OrderService;
import com.yingtong.service.UserService;
import com.yingtong.util.GetWX;
import com.yingtong.util.MD5;

@Controller
@Scope("prototype")
@RequestMapping("/user")
@SessionAttributes("userPage")
public class UserController {
	@Resource
	private UserService service;// 用户服务对象
	@Resource
	private ArticleService aservice;// 文章服务对象
	@Resource
	private OrderService oservice;
	@Resource
	private CarService cservice;
	private Integer number = 0;

	@RequestMapping("/user_list.do")
	public String findAllAction(ModelMap map, UserPage page) {
		Integer rows = service.findRows(page);
		page.setRows(rows);
		map.addAttribute("userPage", page);
		List<User> users = service.findAllUserByPage(page);// 获取所有用户
		map.put("users", users);
		return "admin/user";
	}

	@RequestMapping("/deleteUserById.do")
	public String deleteAction(Integer id, HttpSession session) {
		service.deleteUserById(id);// 删除用户
		oservice.deleteOrderByUserId(id);
		return "redirect:../user/user_list.do";
	}

	@RequestMapping("/regist.do")
	public String registAction(User user, String code, HttpSession session,
			ModelMap map) {
		String num = session.getAttribute("num") + "";
		if (code != null && code.equals(num)) {
			Timestamp regist_time = new Timestamp(System.currentTimeMillis());
			user.setRegist_time(regist_time);
			MD5 md5 = new MD5();
			if (user != null && user.getPassword() != null) {
				user.setPassword(md5.getMD5ofStr(user.getPassword()));// 将密码进行MD5加密
			}
			service.regist(user);// 注册
			return "redirect:../index/login.jsp";
		} else {
			session.setAttribute("u", user);
			session.setAttribute("msg", "验证码输入错误!");
			return "redirect:../user/toRegist.do";
		}
	}

	@RequestMapping("/regist1.do")
	public String regist1Action(User user, String code, HttpSession session,
			ModelMap map) {
		String num = session.getAttribute("num") + "";
		if (code != null && code.equals(num)) {
			Timestamp regist_time = new Timestamp(System.currentTimeMillis());
			user.setRegist_time(regist_time);
			MD5 md5 = new MD5();
			if (user != null && user.getPassword() != null) {
				user.setPassword(md5.getMD5ofStr(user.getPassword()));// 将密码进行MD5加密
			}
			service.regist(user);// 注册
			return "redirect:../mobile/login.jsp";
		} else {
			session.setAttribute("u", user);
			session.setAttribute("msg", "验证码输入错误!");
			return "redirect:../user/toRegist.do";
		}
	}

	@RequestMapping("/getTelCode.do")
	public void getTelAction(final String tel, HttpSession session,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Timestamp time = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String t = sdf.format(time);
				if (t.equals("00:00:00")) {
					Phone phone = new Phone();
					phone.setId(null);
					phone.setNumber(0);
					service.updatePhone(phone);
				}
			}
		}, 1000, 1000);
		List<Phone> phones = service.findPhoneByTel(tel);
		if (phones == null||phones.size()==0) {
			Phone phone1 = new Phone();
			phone1.setTel(tel);
			phone1.setNumber(1);
			number = 1;
			service.addPhone(phone1);
		} else {
			for (Phone phone : phones) {
				number = phone.getNumber();
				phone.setNumber(++number);
				service.updatePhone(phone);
			}
		}
		if (number != null && number <= 10) {
			Random rand = new Random();
			Integer num = (int) (rand.nextInt(899999) + 100000);// 生成随机验证码
			session.setAttribute("num", num);
			HashMap<String, Object> result = null;
			CCPRestSDK restAPI = new CCPRestSDK();
			restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
			restAPI.setAccount("aaf98f894d7439d8014d9370459d1636",
					"c30590757309461e868aee414872fc65");// 初始化主帐号和主帐号TOKEN
			restAPI.setAppId("8aaf0708559f32dd0155a4d65a9904fe");// 初始化应用ID
			result = restAPI.sendTemplateSMS(tel, "106576", new String[] { num
					+ "" });
			if ("000000".equals(result.get("statusCode"))) {
				response.getWriter().print(1);
			} else {
				response.getWriter().print(0);
			}
		}
	}

	@RequestMapping("/toRegist.do")
	public String toRegistAction(ModelMap map) {
		List<User> users = service.findAllUser();
		List<String> tels = new ArrayList<String>();
		List<String> usernames = new ArrayList<String>();
		for (User user : users) {
			tels.add(user.getTel());// 获取用户中所有手机号
			usernames.add(user.getUsername());
		}
		map.put("tels", tels);
		map.put("usernames", usernames);
		return "index/regist";
	}

	@RequestMapping("/toRegist1.do")
	public String toRegis1tAction(ModelMap map) {
		List<User> users = service.findAllUser();
		List<String> tels = new ArrayList<String>();
		List<String> usernames = new ArrayList<String>();
		for (User user : users) {
			tels.add(user.getTel());// 获取用户中所有手机号
			usernames.add(user.getUsername());
		}
		map.put("tels", tels);
		map.put("usernames", usernames);
		return "mobile/regist";
	}

	@RequestMapping("/intoStart.do")
	public String intoStartAction(ModelMap map, HttpSession session,
			String state) {
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
		User user = (User) session.getAttribute("user");
		user.setOpen_id(openid);
		user.setNickname(nickname);
		user.setPath(headimgurl);
		service.updateUser(user);
		session.setAttribute("user", user);
		if (state != null && state.equals("1")) {
			return "redirect:../mobile/center.jsp";
		} else if (state != null && state.equals("2")) {
			return "redirect:../car/duanzu1.do?short_rent=0";
		}
		return null;
	}

	@RequestMapping("/login.do")
	public String loginAction(User user, String order, HttpSession session,
			String phone, String center, ModelMap map) {
		MD5 md5 = new MD5();
		User user1 = null;
		if (user != null && user.getPassword() != null) {
			user.setPassword(md5.getMD5ofStr(user.getPassword()));// 将密码进行MD5加密
			user1 = service.login(user);// 登录
		}
		if (user1 != null) {
			session.setAttribute("user", user1);// 登录成功
			if (phone != null && phone.equals("1") && center != null
					&& center.equals("1")) {
				return "redirect:../user/intoStart.do?state=1";
			} else if (phone != null && phone.equals("1")) {
				return "redirect:../user/intoStart.do?state=2";
			} else {
				return "redirect:../index/index.do";
			}
		} else if (user1 == null && phone != null && phone.equals("1")) {
			map.put("mess", "手机号或密码错误!");// 登录失败
			return "mobile/login";
		} else {
			map.put("message", "手机号或密码错误!");// 登录失败
			return "index/login";
		}
	}

	@RequestMapping("/login1.do")
	public String login1Action(User user, HttpSession session, String order,
			ModelMap map) {
		MD5 md5 = new MD5();
		User user1 = null;
		if (user != null && user.getPassword() != null) {
			user.setPassword(md5.getMD5ofStr(user.getPassword()));// 将密码进行MD5加密
			user1 = service.login(user);
		}
		if (order != null && order.equals("t")) {
			if (user1 != null) {
				session.setAttribute("user", user1);// 登录成功
				return "redirect:../order/showOrder.do";
			} else {
				session.setAttribute("message", "手机号或密码错误!");// 登录失败
				map.put("order", "t");
				return "index/order";
			}
		} else {
			if (user1 != null) {
				session.setAttribute("user", user1);// 登录成功
				return "redirect:../car/duanzu.do?login1=1";
			} else {
				session.setAttribute("message", "手机号或密码错误!");// 登录失败
				return "redirect:../car/rentCar.do";
			}
		}

	}

	@RequestMapping("/myInfo.do")
	public String myInfoAction(ModelMap map) {
		List<Article> articles = aservice.findArticlesByTitleId(4);// 获取会员条款
		map.put("articles3", articles);
		return "index/myInfo";
	}

	@RequestMapping("/updateMyInfo.do")
	public String updateMyInfoAction(Integer id, ModelMap map) {
		User user = service.findUserById(id);
		List<Article> articles = aservice.findArticlesByTitleId(4);// 获取会员条款
		map.put("articles", articles);
		map.put("user", user);
		return "index/myInfo_update";
	}

	@RequestMapping("/update.do")
	public String upadateAction(User user, HttpSession session, String center) {
		service.updateUser(user);// 修改个人信息
		session.setAttribute("user", user);
		if (center != null && center.equals("1")) {
			return "mobile/myInfo";
		}
		return "redirect:../user/myInfo.do";
	}

	@RequestMapping("/toUpdatePwd.do")
	public String toUpdatePwdAction(ModelMap map) {
		List<Article> articles = aservice.findArticlesByTitleId(4);// 获取会员条款
		map.put("articles4", articles);
		return "index/password_update";
	}

	@RequestMapping("checkOldPwd.json")
	public void checkOldPwdAction(String oldpwd, HttpServletResponse response,
			HttpSession session) {
		MD5 md5 = new MD5();
		User user = (User) session.getAttribute("user");
		if (oldpwd != null && user != null
				&& md5.getMD5ofStr(oldpwd).equals(user.getPassword())) {
			try {
				response.getWriter().print(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("/updatePwd.do")
	// 更新密码
	public String updateAction(User user, HttpSession session, String center) {
		MD5 md5 = new MD5();
		User user1 = (User) session.getAttribute("user");
		user1.setPassword(md5.getMD5ofStr(user.getPassword()));
		service.updateUser(user1);
		if (center != null && center.equals("1")) {
			return "redirect:../mobile/login.jsp?center=1";
		}
		return "redirect:../index/login.jsp";
	}

	@RequestMapping("/findbackPwd.do")
	// 找回密码
	public String updatePwdAction(User user, String code, HttpSession session,
			ModelMap map) {
		String num = session.getAttribute("num") + "";
		if (code != null && code.equals(num)) {
			if (user != null && user.getPassword() != null) {
				MD5 md5 = new MD5();
				user.setPassword(md5.getMD5ofStr(user.getPassword()));
			}
			service.updatePwdByTel(user);
			map.put("info", "1");
		} else {
			map.put("u", user);
			map.put("msg", "验证码输入错误!");
		}
		return "index/findBackPwd";
	}

	@RequestMapping("/findbackPwd1.do")
	// 找回密码
	public String updatePwd1Action(User user, String code, HttpSession session,
			ModelMap map) {
		String num = session.getAttribute("num") + "";
		if (code != null && code.equals(num)) {
			if (user != null && user.getPassword() != null) {
				MD5 md5 = new MD5();
				user.setPassword(md5.getMD5ofStr(user.getPassword()));
			}
			service.updatePwdByTel(user);
			map.put("info", "1");
		} else {
			map.put("u", user);
			map.put("msg", "验证码输入错误!");
		}
		return "mobile/findBackPwd";
	}

	@RequestMapping("/getUserByTel.json")
	public void getUserByTelAction(String tel, HttpServletResponse response)
			throws IOException {
		User user = service.findUserByTel(tel);
		response.setCharacterEncoding("utf-8");
		if (user == null) {
			response.getWriter().print("0");
		} else {
			response.getWriter().print("1");
		}
	}

	@RequestMapping("/center.do")
	public String centerAction(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:../mobile/login.jsp?center=1";
		}
		return "redirect:../mobile/center.jsp";
	}
}
