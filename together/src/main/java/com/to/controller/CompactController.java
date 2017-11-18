package com.to.controller;


import com.to.entity.*;
import com.to.page.CompactPage;
import com.to.service.CompactService;
import com.to.service.HouseService;
import com.to.util.CompactUtil;
import com.to.util.CookieUtil;
import com.to.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/compact")
@Scope("prototype")
@SessionAttributes("compactPage")
public class CompactController {

	@Resource
	private CompactService service;
	@Resource
	private HouseService hservice;
	@RequestMapping("/loadCompact.do")
	public void loadCompact(Integer pageIndex, Integer limit, String key, CompactPage page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<Compact> compacts = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {   List<String> payTimes=new ArrayList<String>();
				Integer rows = service.getRows(page);
				page.setRows(rows);
				compacts = service.getCompactByPage(page);
			    for(Compact c:compacts){
					Short number=c.getPayWay().getNumber();
					String t1=c.getStartTime();
					String t2=c.getEndTime();
					c.setPayTimes(CompactUtil.getPayTimes(t1,t2,number));
				}
				map.put("rows", compacts);
				map.put("results", rows);
				map.put("hasError", false);
				map.put("error", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("hasError", true);
			map.put("results", 0);
			map.put("error", "error");
			map.put("rows", "");
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			compacts = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateCompact.do")
		public void updateCompact(Compact compact,Fee fee,HttpServletResponse response)throws Exception{
		service.updateCompact(compact,response);
		service.updatePower(fee);
		service.updateCondo(fee);
		service.updateGas(fee);
		service.updateWater(fee);
	}
	@RequestMapping("/addCompact.do")
	public void addCompact(Compact compact, HttpServletResponse response, HttpServletRequest request)throws Exception{
		try{
			Cookie cookie = CookieUtil.getCookieByName(request, "user");
			if (cookie != null) {
				String value = URLDecoder.decode(cookie.getValue(), "utf-8");
				JSONObject obj = JSONObject.fromObject(value);
				User u = (User) JSONObject.toBean(obj, User.class);
				compact.setUserId(u.getId());
				service.addCompact(compact, response);
				Compact c = service.getCompactById(compact.getId());
				Short number=c.getPayWay().getNumber();
				String t1=c.getStartTime();
				String t2=c.getEndTime();
				List<String> payTimes=CompactUtil.getPayTimes(t1,t2,number);
				for(String s:payTimes){
					if(compact.getGasWay().equals("房东缴")){
						Gas gas=new Gas();
						gas.setJoinTime(Date.valueOf(s));
						gas.setCompactId(c.getId());
						service.addGas(gas);
					}
					if(compact.getCondoWay().equals("房东缴")){
						Condo condo=new Condo();
						condo.setJoinTime(Date.valueOf(s));
						condo.setCompactId(c.getId());
						service.addCondo(condo);
					}
					if(compact.getWaterWay().equals("房东缴")){
						Water water=new Water();
						water.setJoinTime(Date.valueOf(s));
						water.setCompactId(c.getId());
						service.addWater(water);
					}
					if(compact.getPowerWay().equals("房东缴")){
						Power power=new Power();
						power.setJoinTime(Date.valueOf(s));
						power.setCompactId(c.getId());
						service.addPower(power);
					}
				}
				House house=new House();
				house.setId(compact.getId());
				house.setStatus((short)2);
				hservice.updateHouse(house,response);

			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}

	}
	@RequestMapping("/deleteCompact.do")
	public void deleteCompact(String  ids,HttpServletResponse response)throws Exception{
		service.deleteCompact(ids,response);
	}
	@RequestMapping("/getCompactByHouseId.do")
	public void getCompactByHouseId(Integer houseId,HttpServletResponse response)throws Exception{
		service.getCompactByHouseId(houseId,response);
	}
	@RequestMapping("/getCompactsByStatus.do")
	public void getCompactsByStatus(Integer status,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "user");
		if (cookie != null) {
			String value = URLDecoder.decode(cookie.getValue(), "utf-8");
			JSONObject obj = JSONObject.fromObject(value);
			User u = (User) JSONObject.toBean(obj, User.class);
			Map<String,Integer> map=new HashMap<String,Integer>();
		    map.put("status",status);
			map.put("userId",u.getId());
			try{
				Long l1=System.currentTimeMillis();
				List<Compact> compacts=service.getCompactByStatus(map);
				Long l2=System.currentTimeMillis();
				System.out.println(l2-l1);
				for(Compact c:compacts){
						Short number=c.getPayWay().getNumber();
						String t1=c.getStartTime();
						String t2=c.getEndTime();
						c.setPayTimes(CompactUtil.getPayTimes(t1,t2,number));
				}
				Long l3=System.currentTimeMillis();
				System.out.println(l3-l2);
				JSONArray arr= JSONArray.fromObject(compacts);
				ResponseUtil.write(response,arr);
				System.out.println(System.currentTimeMillis()-l3);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("/getCompactsByStatusAndUser.do")
	public void getCompactsByStatusAndUser(Integer status,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "user");
		if (cookie != null) {
			String value = URLDecoder.decode(cookie.getValue(), "utf-8");
			JSONObject obj = JSONObject.fromObject(value);
			User u = (User) JSONObject.toBean(obj, User.class);
			Map<String,String> map=new HashMap<String,String>();
			map.put("status",status+"");
			map.put("tel",u.getTel());
			try{
				List<Compact> compacts=service.getCompactByStatusAndUser(map);
				for(Compact c:compacts){
					Short number=c.getPayWay().getNumber();
					String t1=c.getStartTime();
					String t2=c.getEndTime();
					c.setPayTimes(CompactUtil.getPayTimes(t1,t2,number));
				}
				JSONArray arr= JSONArray.fromObject(compacts);
				ResponseUtil.write(response,arr);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
		@RequestMapping("/getCompactById.do")
		public void getCompactById(Integer id,HttpServletResponse response)throws Exception{
			Compact c = service.getCompactById(id);
			Short number=c.getPayWay().getNumber();
			String t1=c.getStartTime();
			String t2=c.getEndTime();
			c.setPayTimes(CompactUtil.getPayTimes(t1,t2,number));
			JSONObject object=JSONObject.fromObject(c);
			ResponseUtil.write(response,object);

    }
	@RequestMapping("/getAllPrice.do")
	public void getAllPrice(HttpServletResponse response) throws Exception {
		List<Price> prices=service.getAllPrice();
		JSONArray arr=JSONArray.fromObject(prices);
		ResponseUtil.write(response,arr);
	}
	@RequestMapping("/changeRenterImg.do")
	public void changeRenterImg(Compact c,HttpServletResponse response) throws Exception {
		System.out.println(c.getId()+";"+c.getRenter_img());
		service.updateCompact(c,response);
	}
	@RequestMapping("/changeUserImg.do")
	public void changeUserImg(Compact c,HttpServletResponse response) throws Exception {
		System.out.println(c.getId()+";"+c.getRenter_img());
		service.updateCompact(c,response);
	}
	@RequestMapping("/changeState.do")
	public void changeState(Compact c,HttpServletResponse response) throws Exception {
		System.out.println(c.getId()+":"+c.getState());
		service.updateCompact(c,response);

	}
}