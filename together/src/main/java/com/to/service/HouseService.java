package com.to.service;


import com.to.entity.House;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public interface HouseService {
	List<House> getHouseByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 House getHouseById(Integer id);
	 
	void updateHouse(House house,HttpServletResponse response)throws Exception;
	
	void addHouse(House house,HttpServletResponse response)throws Exception;
	
	void deleteHouse(String ids, HttpServletResponse response)throws Exception;

	void addHousePicture(Map<String, String> map);

	void addHouseSupportingFacility(Map<String, String> map);

	List<House> getHousesByArea(String area);

	List<House> getHousesByStatus(Map map);

	void deleteById(Integer id,HttpServletResponse response)throws Exception;

	void deleteHouseSupportingFacilityByHouseId(Integer houseId);

	List<House> getHousesByType(Map<String, String> map);

	List<House> getHousesByPrice(Map<String, Object> map);

	List<House> getHousesBySearch(Map<String, String> map);
}
