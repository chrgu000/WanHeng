package com.to.serviceImpl;


import com.to.dao.HouseDao;
import com.to.entity.House;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.HouseService;
import com.to.util.HttpClientUtil;
import com.to.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("HouseService")
@Transactional
public class HouseServiceImpl implements HouseService {
@Resource
private HouseDao dao;
	private static final String URL="http://restapi.amap.com/v3/geocode/geo?key=80eb6e5d424ed185d20da350aaefd75a&address=city";
	public List<House> getHouseByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateHouse(House house,HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		Map<String,String> map= HttpClientUtil.doGet(URL.replace("city",house.getCity()+house.getArea()+house.getAddress()));
		String latitude=map.get("latitude");
		String longitude=map.get("longitude");
		if(latitude!=null){
			house.setLatitude(Double.valueOf(latitude));
		}
		if(longitude!=null){
			house.setLongitude(Double.valueOf(longitude));
		}
		try {   house.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(house);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("修改成功");
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object= JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;
		}
	}

	public void addHouse(House house, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {   Map<String,String> map= HttpClientUtil.doGet(URL.replace("city",house.getCity()+house.getArea()+house.getAddress()));
				String latitude=map.get("latitude");
				String longitude=map.get("longitude");
				if(latitude!=null){
					house.setLatitude(Double.valueOf(latitude));
				}
				if(longitude!=null){
					house.setLongitude(Double.valueOf(longitude));
				}
				dao.save(house);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("添加成功");
		} catch (Exception e) {
			System.out.println("ok");
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("输入的地址无效");
			e.printStackTrace();
		}finally{
			object=JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
		}
	}

	public void deleteHouse(String ids, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;

		try {
			Map<String,String[]> map=new HashMap<String,String[]>();
			String[] Ids=ids.split(",");
			map.put("ids", Ids);
			dao.deleteByIds(map);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object=JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;
		}		
	}

	@Override
	public void addHousePicture(Map<String, String> map) {
		try{
			dao.addHousePicture(map);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public House getHouseById(Integer id) {
		return dao.getById(id);
	}
	public void addHouseSupportingFacility(Map<String, String> map){
		try{
			dao.addHouseSupportingFacility(map);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<House> getHousesByArea(String area) {
		return dao.getHouseByArea(area);
	}

	@Override
	public List<House> getHousesByStatus(Map map) {
		return dao.getHousesByStatus(map);
	}

	@Override
	public void deleteById(Integer id,HttpServletResponse response) throws Exception {
		try {
			dao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ResponseUtil.write(response, 1);
		}
	}

	@Override
	public void deleteHouseSupportingFacilityByHouseId(Integer houseId) {
		dao.deleteHouseSupportingFacilityByHouseId(houseId);
	}

	@Override
	public List<House> getHousesByType(Map<String, String> map) {
		return dao.getHousesByType(map);
	}

	@Override
	public List<House> getHousesByPrice(Map<String, Object> map) {
		return dao.getHousesByPrice(map);
	}

	@Override
	public List<House> getHousesBySearch(Map<String, String> map) {
		return dao.getHouseBySearch(map);
	}
}
