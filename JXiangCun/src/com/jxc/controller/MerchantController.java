package com.jxc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

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

import com.jxc.dao.SelectDao;
import com.jxc.dao.SightSpotDao;
import com.jxc.dao.TitleDao;
import com.jxc.entity.Area;
import com.jxc.entity.City;
import com.jxc.entity.Distance;
import com.jxc.entity.Mark;
import com.jxc.entity.Merchant;
import com.jxc.entity.Picture;
import com.jxc.entity.Point;
import com.jxc.entity.Price;
import com.jxc.entity.Product;
import com.jxc.entity.Sequence;
import com.jxc.entity.SightSpot;
import com.jxc.entity.Title;
import com.jxc.entity.User;
import com.jxc.page.MerchantPage;
import com.jxc.page.Select;
import com.jxc.service.AreaService;
import com.jxc.service.CityService;
import com.jxc.service.MarkService;
import com.jxc.service.MerchantService;
import com.jxc.service.PictureService;
import com.jxc.service.ProductService;
import com.jxc.service.SightSpotService;
import com.jxc.service.UserService;
import com.jxc.util.GetWX;
import com.jxc.util.MerchantUtil;

@Controller
@RequestMapping("/merchant")
@Scope("prototype")
@SessionAttributes("merchantPage")
public class MerchantController {
	@Resource
	private MerchantService service;
	@Resource
	private CityService cservice;
	@Resource
	private TitleDao tdao;
	@Resource
	private SightSpotService sservice;
	@Resource
	private PictureService pservice;
	@Resource
	private ProductService pdservice;
	@Resource
	private SelectDao sdao;
	@Resource
	private SightSpotDao ssdao;
	@Resource
	private MarkService mservice;
	@Resource
	private UserService uservice;
	@Resource
	private AreaService aservice;

	@RequestMapping("/merchantList.do")
	public String findAllAction(MerchantPage page, ModelMap map,
			HttpSession session) {
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		List<City> citys = cservice.findAllCity();
		List<Price> prices = tdao.findAllPrice();
		map.addAttribute("merchantPage", page);
		List<Merchant> merchants = service.findAllMerchantByPage(page);// 分页查询品牌信
		map.put("merchants", merchants);
		map.put("citys", citys);
		map.put("prices", prices);
		return "admin/merchant";// 转发到品牌首页
	}

	@RequestMapping("/toAddMerchant.do")
	public String toAddAction(ModelMap map, HttpSession session) {
		List<City> citys = cservice.findAllCity();
		List<Price> prices = tdao.findAllPrice();
		List<SightSpot> sightSpots = sservice.findAllSightSpot();
		Map<String, List<Integer>> maps = new HashMap<String, List<Integer>>();
		map.put("citys", citys);
		map.put("prices", prices);
		map.put("sightSpots", sightSpots);
		return "admin/merchant_add";// 转发到品牌添加页面
	}

	@RequestMapping("/addMerchant.do")
	public String addAction(Merchant merchant) {
		service.addMerchant(merchant);// 添加品牌信息
		List<Integer> markIds = merchant.getMarkIds();
		for (Integer id : markIds) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("merchant_id", merchant.getId());
			map.put("mark_id", id);
			service.addMerchantMark(map);
		}
		List<Integer> titleIds = merchant.getTitleIds();
		for (Integer id : titleIds) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("merchant_id", merchant.getId());
			map.put("title_id", id);
			service.addMerchantTitle(map);
		}
		return "redirect:../merchant/merchantList.do";
	}

	@RequestMapping("/toUpdateMerchant.do")
	public String toUpdateAction(ModelMap map, Integer id, HttpSession session) {
		Merchant merchant = service.findMerchantById(id);
		List<Area> areas=aservice.findAreasByCityId(merchant.getCity().getId());
		List<Title> titles = merchant.getTitles();
		Map<String, List<Integer>> maps = new HashMap<String, List<Integer>>();
		maps.put("titleIds", merchant.getTitleIds());
		List<Mark> marks = mservice.findMarkByTitleIds(maps);
		List<City> citys = cservice.findAllCity();
		List<Price> prices = tdao.findAllPrice();
		List<SightSpot> sightSpots = sservice.findAllSightSpot();
		map.put("merchant", merchant);
		map.put("citys", citys);
		map.put("prices", prices);
		map.put("sightSpots", sightSpots);
		map.put("marks", marks);
		map.put("areas", areas);
		return "admin/merchant_update";
	}

	@RequestMapping("/updateMerchant.do")
	public String updateAction(Merchant merchant) {
		service.updateMerchant(merchant);// 修改品牌信息
		service.deleteMerchantMark(merchant.getId());
		service.deleteMerchantTitle(merchant.getId());
		List<Integer> markIds = merchant.getMarkIds();
		for (Integer id : markIds) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("merchant_id", merchant.getId());
			map.put("mark_id", id);
			service.addMerchantMark(map);
		}
		List<Integer> titleIds = merchant.getTitleIds();
		for (Integer id : titleIds) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("merchant_id", merchant.getId());
			map.put("title_id", id);
			service.addMerchantTitle(map);
		}
		return "redirect:../merchant/merchantList.do";// 重定向到品牌首页
	}

	@RequestMapping("/deleteMerchant.do")
	public String deleteAction(Integer id) {
		service.deleteMerchantById(id);// 根据id删除品牌
		service.deleteMerchantMark(id);
		service.deleteMerchantTitle(id);
		return "redirect:../merchant/merchantList.do";// 重定向到品牌首页
	}

	@RequestMapping("/getSightSpot.json")
	public void getSighSpotAction(Integer area_id, HttpServletResponse response)
			throws IOException {
		List<SightSpot> sightspots = sservice.findSightSpotByAreaId(area_id);
		response.setCharacterEncoding("utf-8");
		JSONArray jsonArray = JSONArray.fromObject(sightspots);
		response.getWriter().print(jsonArray);
	}

	@RequestMapping("/getPictures.do")
	public String getPicturesAction(ModelMap map, Integer merchant_id) {
		List<Picture> pictures = pservice.findPicturesByMerchantId(merchant_id);
		map.put("pictures", pictures);
		return "admin/getPictures";
	}

	@RequestMapping("/getProducts.do")
	public String getProductsAction(ModelMap map, Integer merchant_id) {
		List<Product> products = pdservice
				.findProductsByMerchantId(merchant_id);
		map.put("products", products);
		return "admin/getProducts";
	}

	@RequestMapping("/intoStart.do")
	public String intoStartAction(ModelMap map, HttpSession session,
			Point point, String state) {
		session.setAttribute("point", point);
		return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ GetWX.getPro("appid")
				+ "&redirect_uri=http%3a%2f%2f"
				+ GetWX.getPro("yuming")
				+ "%2fmerchant%2fstart.do&response_type=code&scope=snsapi_userinfo&state="
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
		if (user == null) {
			user = new User();
		}
		Timestamp t = new Timestamp(System.currentTimeMillis());
		user.setRegist_time(t);
		user.setOpen_id(openid);
		user.setNickname(nickname);
		user.setPath(headimgurl);
		User u = uservice.findUserByOpenId(openid);
		if (u == null) {
			uservice.addUser(user);
			session.setAttribute("user", user);
		} else {
			session.setAttribute("user", u);
		}
		return "redirect:../merchant/cunyouIndex.do";
	}

	@RequestMapping("/cunyouIndex.do")
	public String cunyouAction(ModelMap map, Select select, HttpSession session) {
		Point point = (Point) session.getAttribute("point");
		List<Distance> distances = sdao.findAllDistance();
		List<Price> prices = sdao.findAllPrice();
		List<Sequence> sequences = sdao.findAllSequence();
		List<Mark> marks = sdao.findMarksByTitleId(1);
		session.setAttribute("select", select);
		List<Merchant> merchants = service.findMerchantsBySelect(select);
		merchants = MerchantUtil.getMerchantsByPointAndDistanceId(merchants,
				point, select.getDistance_id());
		List<City> citys = cservice.findAllCity();
		List<Picture> pictures = pservice.findAllPicture(2);
		map.put("distances", distances);
		map.put("prices", prices);
		map.put("sequences", sequences);
		map.put("marks", marks);
		map.put("merchants", merchants);
		map.put("citys", citys);
		map.put("pictures", pictures);
		return "mobile/info";
	}

	@RequestMapping("/updateIsOk.json")
	public void updateIsOkAction(Merchant merchant) {
		service.updateMerchant(merchant);
	}

	@RequestMapping("/findProductsByid.do")
	public String findProductsAction(ModelMap map, Integer id) {
		Merchant merchant = service.findMerchantById(id);
		List<Product> products = pdservice.findProductsByMerchantId(id);
		map.put("merchant", merchant);
		map.put("products", products);
		return "mobile/merchant";
	}

	@RequestMapping("/getTitle.json")
	public void getTitleAction(Integer sight_spot_id,
			HttpServletResponse response) throws IOException {
		List<Title> titles = tdao.findTitlesBySightSpotId(sight_spot_id);
		JSONArray arr = JSONArray.fromObject(titles);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(arr);
	}

	@RequestMapping("/getMarks.json")
	public void getMarkAction(String titleIds, HttpServletResponse response)
			throws IOException {
		List<Integer> titleids = new ArrayList<Integer>();
		if (titleIds != "") {
			String[] titles = titleIds.split(",");
			for (String s : titles) {
				titleids.add(Integer.parseInt(s));
			}
			response.setCharacterEncoding("utf-8");
			Map<String, List<Integer>> maps = new HashMap<String, List<Integer>>();
			maps.put("titleIds", titleids);
			List<Mark> marks = mservice.findMarkByTitleIds(maps);
			JSONArray arr = JSONArray.fromObject(marks);
			response.getWriter().print(arr);
		}
	}

	@RequestMapping("/getArea.json")
	public void getAreaAction(Integer city_id, HttpServletResponse response)
			throws IOException {
		List<Area> areas = aservice.findAreasByCityId(city_id);
		response.setCharacterEncoding("utf-8");
		JSONArray arr = JSONArray.fromObject(areas);
		response.getWriter().print(arr);
	}
}
