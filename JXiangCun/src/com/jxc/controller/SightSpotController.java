package com.jxc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.dao.TitleDao;
import com.jxc.entity.Area;
import com.jxc.entity.City;
import com.jxc.entity.Merchant;
import com.jxc.entity.Point;
import com.jxc.entity.SightSpot;
import com.jxc.entity.Title;
import com.jxc.page.SightSpotPage;
import com.jxc.service.AreaService;
import com.jxc.service.CityService;
import com.jxc.service.ProductService;
import com.jxc.service.SightSpotService;
import com.jxc.util.SightSpotUtil;

@Controller
@RequestMapping("/sightSpot")
@Scope("prototype")
@SessionAttributes("sightSpotPage")
public class SightSpotController {
	@Resource
	private SightSpotService service;
@Resource 
private CityService cservice;
@Resource 
private ProductService pservice;
@Resource
private AreaService aservice;
@Resource
private TitleDao tdao;

	@RequestMapping("/sightSpotList.do")
	public String findAllAction(SightSpotPage page, ModelMap map,HttpSession session) {
		session.setAttribute("title_id", page.getTitle_id());
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		map.addAttribute("sightSpotPage", page);
		List<SightSpot> sightSpots = service.findAllSightSpotByPage(page);// 分页查询品牌信息
		List<City> citys=cservice.findAllCity();
		map.put("sightSpots", sightSpots);
		map.put("citys", citys);
		return "admin/sightSpot";// 转发到品牌首页
	}

	@RequestMapping("/toAddSightSpot.do")
	public String toAddAction(ModelMap map) {
		List<City> citys=cservice.findAllCity();
		List<Title>titles=tdao.findAllTitle();
		map.put("citys", citys);
		map.put("titles", titles);
		return "admin/sightSpot_add";// 转发到品牌添加页面
	}

	@RequestMapping("/addSightSpot.do")
	public String addAction(SightSpot sightSpot,HttpSession session) {
		service.addSightSpot(sightSpot);// 添加品牌信息
		List<Integer> titleIds=sightSpot.getTitleIds();
		for (Integer id : titleIds) {
			Map<String,Integer> map=new HashMap<String,Integer>();
			map.put("sight_spot_id", sightSpot.getId());
			map.put("title_id", id);
			service.addSightSpotTitle(map);
		}
		return "redirect:../sightSpot/sightSpotList.do";
	}

	@RequestMapping("/toUpdateSightSpot.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		List<City> citys=cservice.findAllCity();
		SightSpot sightSpot = service.findSightSpotById(id);
		City city=cservice.findCityById(sightSpot.getCity_id());
		Area area=aservice.findAreaById(sightSpot.getArea_id());
		List<Area> areas=aservice.findAreasByCityId(sightSpot.getCity_id());
		map.put("citys", citys);
		map.put("sightSpot", sightSpot);
		map.put("areas", areas);
		return "admin/sightSpot_update";
	}

	@RequestMapping("/updateSightSpot.do")
	public String updateAction(SightSpot  sightSpot) {
		service.updateSightSpot (sightSpot);
		service.deleteSightSpotTitle(sightSpot.getId());
		List<Integer> titleIds=sightSpot.getTitleIds();
		for (Integer id : titleIds) {
			Map<String,Integer> map=new HashMap<String,Integer>();
			map.put("sight_spot_id", sightSpot.getId());
			map.put("title_id", id);
			service.addSightSpotTitle(map);
		}
		return "redirect:../sightSpot/ sightSpotList.do";// 重定向到品牌首页
	}

	@RequestMapping("/deleteSightSpot.do")
	public String deleteAction(Integer id) {
		service.deleteSightSpotById(id);// 根据id删除品牌
		service.deleteSightSpotTitle(id);
		return "redirect:../sightSpot/sightSpotList.do";// 重定向到品牌首页
	}
	@RequestMapping("/jujing.do")
	public String jujingAction(ModelMap map,HttpSession session,Integer title_id,Integer sight_spot_id){
		List<Merchant> merchantss=new ArrayList<Merchant>();
		if(title_id==null){
			title_id=4;
		}
		List<SightSpot> sightSpots=service.findSightSpots();
		if(sightSpots.size()>0){
			SightSpot sightSpot=SightSpotUtil.getSightSpotByPoint(sightSpots, (Point)session.getAttribute("point"));
			if(sight_spot_id!=null){
				sightSpot=service.findSightSpotById(sight_spot_id);
			}
			List<Merchant> merchants=sightSpot.getMerchants();
			for (Merchant merchant : merchants) {
				if(merchant.getTitleIds().contains(title_id)){
					merchantss.add(merchant);
				}
			}
			List<City> citys=cservice.findAllCity();
			if(citys.size()>0){
				List<SightSpot> sightspots=service.findSightSpotByCityId(citys.get(0).getId());
				map.put("sightspots", sightspots);
			}
			map.put("sightSpot", sightSpot);
			map.put("merchants", merchantss);
			map.put("citys", citys);
		}
		return "mobile/jujing";
	}
	@RequestMapping("/getArea.json")
	public void getAreaAction(Integer city_id,HttpServletResponse response) throws IOException{
		List<Area> areas=aservice.findAreasByCityId(city_id);
		response.setCharacterEncoding("utf-8");
		JSONArray arrays=JSONArray.fromObject(areas);
		response.getWriter().print(arrays);
	}
	@RequestMapping("/getSightSpotsByCityId.json")
	public void getSightSpotsAction(Integer city_id,HttpServletResponse response) throws IOException{
		List<SightSpot> sightSpots=service.findSightSpotByCityId(city_id);
		response.setCharacterEncoding("utf-8");
		JSONArray arr=JSONArray.fromObject(sightSpots);
		response.getWriter().print(arr);
	}
}
