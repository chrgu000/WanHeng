package com.to.controller;


import com.to.entity.House;
import com.to.entity.User;
import com.to.page.HousePage;
import com.to.service.HouseService;
import com.to.util.CookieUtil;
import com.to.util.FileUtil;
import com.to.util.HouseUtil;
import com.to.util.ResponseUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
@Scope("prototype")
@SessionAttributes("housePage")

public class HouseController {
	@Resource
	private HouseService service;

	 @RequestMapping("/deleteById.do")
	 public void deleteById(Integer id,HttpServletResponse response) throws Exception {
		 service.deleteById(id,response);
	 }
	@RequestMapping("/loadHouse.do")
	public void loadHouse(Integer pageIndex, Integer limit, String key, HousePage page, HttpServletResponse response)
			throws Exception {
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		List<House> houses = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				houses = service.getHouseByPage(page);
				map.put("rows", houses);
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
			houses = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/updateHouse.do")
		public void updateHouse(House house,HttpServletRequest request,HttpSession session, String supportingFacilityIds, String path, String data,HttpServletResponse response)throws Exception{
		Integer houseId=house.getId();
		String[] ds=new String[]{};
		if(data!=null){
			ds=data.split("_");
		}
		String url=session.getServletContext().getRealPath("");
		System.out.println(path);
		if(supportingFacilityIds!=null){
			service.deleteHouseSupportingFacilityByHouseId(houseId);
			String[] ids=supportingFacilityIds.split(",");
			for(int i=0;i<ids.length;i++){
			Map<String,String> map=new HashMap<String,String>();
				map.put("houseId",houseId+"");
				map.put("supportingFacilityId",ids[i]);
				service.addHouseSupportingFacility(map);
			}
		}
		Map<String,String> map1=new HashMap<String,String>();
		if(path!=null&&path.length()>0&&path.length()%33==0){
			if(path.length()/33>1){
				for(int i=0;i<path.length()/33;i++){
					String imgUrl=path.substring(33*i, (i+1)*33);
					Thumbnails.of(request.getSession().getServletContext().getRealPath("/")
							+ imgUrl.substring(1, imgUrl.length())).scale(1f).outputQuality(0.25f).toFile(request.getSession().getServletContext().getRealPath(
							"/")
							+ "uploadstart-min/"
							+ imgUrl.substring(13, imgUrl.length()));
					map1.put("houseId",houseId+"");
					map1.put("data",ds[i]);
					map1.put("imgUrl","/uploadstart-min/"+ imgUrl.substring(13, imgUrl.length()));
					map1.put("createTime",new Timestamp(System.currentTimeMillis()).toString());
					String p1 = url + imgUrl.substring(1, imgUrl.length());
					System.out.println(FileUtil.deleteFile(p1));
					service.addHousePicture(map1);
				}
			}else{
				Thumbnails.of(request.getSession().getServletContext().getRealPath("/")
						+ path.substring(1, path.length())).scale(1f).outputQuality(0.25f).toFile(request.getSession().getServletContext().getRealPath(
						"/")
						+ "uploadstart-min/"
						+ path.substring(13, path.length()));
				map1.put("houseId",houseId+"");
				map1.put("data",ds[0]);
				map1.put("imgUrl","/uploadstart-min/"+ path.substring(13, path.length()));
				map1.put("createTime",new Timestamp(System.currentTimeMillis()).toString());
				String p1 = url + path.substring(1, path.length());
				System.out.println(FileUtil.deleteFile(p1));
				service.addHousePicture(map1);
			}
		}else if(path!=null&&path.length()>0&&path.length()%34==0){
			if(path.length()/34>1){
				for(int i=0;i<path.length()/34;i++){
					String imgUrl=path.substring(34*i, (i+1)*34);
					Thumbnails.of(request.getSession().getServletContext().getRealPath("/")
							+ imgUrl.substring(1, imgUrl.length())).scale(1f).outputQuality(0.25f).toFile(request.getSession().getServletContext().getRealPath(
							"/")
							+ "uploadstart-min/"
							+ imgUrl.substring(13, imgUrl.length()));
					map1.put("houseId",houseId+"");
					map1.put("data",ds[i]);
					map1.put("imgUrl","/uploadstart-min/"+ imgUrl.substring(13, imgUrl.length()));
					map1.put("createTime",new Timestamp(System.currentTimeMillis()).toString());
					String p1 = url + imgUrl.substring(1, imgUrl.length());
					System.out.println(FileUtil.deleteFile(p1));
					service.addHousePicture(map1);
				}
			}else{
				Thumbnails.of(request.getSession().getServletContext().getRealPath("/")
						+ path.substring(1, path.length())).scale(1f).outputQuality(0.25f).toFile(request.getSession().getServletContext().getRealPath(
						"/")
						+ "uploadstart-min/"
						+ path.substring(13, path.length()));
				map1.put("houseId",houseId+"");
				map1.put("data",ds[0]);
				map1.put("imgUrl","/uploadstart-min/"+ path.substring(13, path.length()));
				map1.put("createTime",new Timestamp(System.currentTimeMillis()).toString());
				String p1 = url + path.substring(1, path.length());
				System.out.println(FileUtil.deleteFile(p1));
				service.addHousePicture(map1);
			}
		}
		service.updateHouse(house,response);
	}
	@Transactional
	@RequestMapping("/addHouse.do")
	public void addHouse(House house, String supportingFacilityIds, String path, String data,HttpSession session,HttpServletRequest  request, HttpServletResponse response)throws Exception{
		Cookie cookie = CookieUtil.getCookieByName(request, "user");
		String url = session.getServletContext().getRealPath("");
		String[] ds=data.split("_");
		System.out.println(path);
 		try{
			if (cookie != null) {
				String value= URLDecoder.decode(cookie.getValue(),"utf-8");
				JSONObject obj = JSONObject.fromObject(value);
				User u = (User) JSONObject.toBean(obj, User.class);
				house.setUserId(u.getId());
				house.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				house.setCreateTime(new Timestamp(System.currentTimeMillis()));
				house.setDelflag((short)2);
				house.setStatus((short)0);
				service.addHouse(house,response);
				Integer houseId=house.getId();
				if(supportingFacilityIds!=null){
					String[] ids=supportingFacilityIds.split(",");
					Map<String,String> map=new HashMap<String,String>();
					for(int i=0;i<ids.length;i++){
						map.put("houseId",houseId+"");
						map.put("supportingFacilityId",ids[i]);
						service.addHouseSupportingFacility(map);
					}
				}
				Map<String,String> map1=new HashMap<String,String>();

				if(path.length()%33==0){
					if(path.length()/33>1){
						for(int i=0;i<path.length()/33;i++){
							String imgUrl=path.substring(33*i, (i+1)*33);
							Thumbnails.of(request.getSession().getServletContext().getRealPath("/")
									+ imgUrl.substring(1, imgUrl.length())).scale(1f).outputQuality(0.25f).toFile(request.getSession().getServletContext().getRealPath(
									"/")
									+ "uploadstart-min/"
									+ imgUrl.substring(13, imgUrl.length()));
							map1.put("houseId",houseId+"");
							map1.put("imgUrl","/uploadstart-min/"+ imgUrl.substring(13, imgUrl.length()));
							map1.put("createTime",new Timestamp(System.currentTimeMillis()).toString());
							map1.put("data",ds[i]);
							String p1 = url + imgUrl.substring(1, imgUrl.length());
							System.out.println(FileUtil.deleteFile(p1));
							service.addHousePicture(map1);
						}
					}else{
						Thumbnails.of(request.getSession().getServletContext().getRealPath("/")
								+ path.substring(1, path.length())).scale(1f).outputQuality(0.25f).toFile(request.getSession().getServletContext().getRealPath(
								"/")
								+ "uploadstart-min/"
								+ path.substring(13, path.length()));
						map1.put("houseId",houseId+"");
						map1.put("imgUrl","/uploadstart-min/"+ path.substring(13, path.length()));
						map1.put("createTime",new Timestamp(System.currentTimeMillis()).toString());
						String p1 = url + path.substring(1, path.length());
						map1.put("data",ds[0]);
						System.out.println(FileUtil.deleteFile(p1));
						service.addHousePicture(map1);
					}
				}else if(path.length()%34==0){
					if(path.length()/34>1){
						for(int i=0;i<path.length()/34;i++){
							String imgUrl=path.substring(34*i, (i+1)*34);
							Thumbnails.of(request.getSession().getServletContext().getRealPath("/")
									+ imgUrl.substring(1, imgUrl.length())).scale(1f).outputQuality(0.25f).toFile(request.getSession().getServletContext().getRealPath(
									"/")
									+ "uploadstart-min/"
									+ imgUrl.substring(13, imgUrl.length()));
							map1.put("houseId",houseId+"");
							map1.put("imgUrl","/uploadstart-min/"+ imgUrl.substring(13, imgUrl.length()));
							map1.put("createTime",new Timestamp(System.currentTimeMillis()).toString());
							String p1 = url + imgUrl.substring(1, imgUrl.length());
							map1.put("data",ds[i]);
							System.out.println(FileUtil.deleteFile(p1));
							service.addHousePicture(map1);
						}
					}else{
						Thumbnails.of(request.getSession().getServletContext().getRealPath("/")
								+ path.substring(1, path.length())).scale(1f).outputQuality(0.25f).toFile(request.getSession().getServletContext().getRealPath(
								"/")
								+ "uploadstart-min/"
								+ path.substring(13, path.length()));
					map1.put("houseId",houseId+"");
					map1.put("imgUrl","/uploadstart-min/"+ path.substring(13, path.length()));
					map1.put("createTime",new Timestamp(System.currentTimeMillis()).toString());
					String p1 = url + path.substring(1, path.length());
					map1.put("data",ds[0]);
					System.out.println(FileUtil.deleteFile(p1));
					service.addHousePicture(map1);
			}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			throw  new RuntimeException();
		}
	}
	@RequestMapping("/deleteHouse.do")
	public void deleteHouse(String  ids,HttpServletResponse response)throws Exception{
		service.deleteHouse(ids,response);
	}
	@RequestMapping("/getHosuesByArea.do")
	public void getHousesByArea(String area,HttpServletResponse response,HttpSession session) throws Exception {
		session.setAttribute("area",area);
       List<House> houses=service.getHousesByArea(area);
		JSONArray arr= JSONArray.fromObject(houses);
		ResponseUtil.write(response,arr);
	}
	@RequestMapping("/getHouseByStatus.do")
	public void getHouseByStatus(String status,HttpServletResponse response,HttpServletRequest request) throws Exception {
		Cookie cookie = CookieUtil.getCookieByName(request, "user");
		if (cookie != null) {
			String value= URLDecoder.decode(cookie.getValue(),"utf-8");
			JSONObject obj = JSONObject.fromObject(value);
			User u = (User) JSONObject.toBean(obj, User.class);
			Map map=new HashMap<String,Integer>();
			map.put("userId",u.getId());
			map.put("status",status);
			List<House> houses=service.getHousesByStatus(map);
			JSONArray arr=JSONArray.fromObject(houses);
			ResponseUtil.write(response,arr);
	}
}
	@RequestMapping("/getHouseById.do")
	public void getHouseById(Integer id,HttpServletResponse response) throws Exception {
		try{
			House house=service.getHouseById(id);
			JSONObject object=JSONObject.fromObject(house);
			ResponseUtil.write(response,object);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping("/getHosuesByType.do")
	public void getHosuesByType(String type,HttpServletResponse response,HttpSession  session) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("type",type);
		map.put("area", (String) session.getAttribute("area"));
		List<House> houses=service.getHousesByType(map);
		JSONArray arr=JSONArray.fromObject(houses);
		ResponseUtil.write(response,arr);
	}
	@RequestMapping("/getHosuesByPrice.do")
	public void getHosuesByPrice(String price,HttpServletResponse response,HttpSession  session) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		price=price.replace("元","");
		if(price!=null&&price.indexOf("以下")!=-1){
			price=price.replace("以下","");
			map.put("less",Integer.parseInt(price));
			map.put("more",null);
			map.put("start",null);
			map.put("end",null);
		}else if(price!=null&&price.indexOf("以上")!=-1){
			price=price.replace("以上","");
			map.put("less",null);
			map.put("more",Integer.parseInt(price));
			map.put("start",null);
			map.put("end",null);
		}else if(price!=null&&price.indexOf("-")!=-1){
			String[] ps=price.split("-");
			map.put("less",null);
			map.put("more",null);
			map.put("start",Integer.parseInt(ps[0]));
			map.put("end",Integer.parseInt(ps[1]));
		}
		map.put("area", (String) session.getAttribute("area"));
		List<House> houses=service.getHousesByPrice(map);
		JSONArray arr=JSONArray.fromObject(houses);
		ResponseUtil.write(response,arr);
	}
	@RequestMapping("/getHosuesBySearch.do")
	public void getHouseBySearch(String search,HttpServletResponse response,HttpSession session) throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("search",search);
		map.put("area", (String) session.getAttribute("area"));
		List<House> houses=service.getHousesBySearch(map);
		JSONArray arr=JSONArray.fromObject(houses);
		ResponseUtil.write(response,arr);
	}
    @RequestMapping("/getHousesByLocation.do")
	public void getHousesByLocation(double longitude,double latitude,HttpServletResponse response,HttpSession session) throws Exception {
		List<House> houses=service.getHousesByArea((String)session.getAttribute("area"));
		houses= HouseUtil.getHousesByLocation(houses,latitude,longitude);
		JSONArray arr= JSONArray.fromObject(houses);
		ResponseUtil.write(response,arr);
	}
}