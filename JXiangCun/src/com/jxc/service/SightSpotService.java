package com.jxc.service;

import java.util.List;
import java.util.Map;

import com.jxc.entity.SightSpot;
import com.jxc.page.Page;

public interface SightSpotService {
	boolean addSightSpot(SightSpot sightSpot);//注册

	boolean updateSightSpot(SightSpot sightSpot);

	boolean deleteSightSpotById(Integer id);
	
	boolean deleteSightSpotTitle(Integer sightSpotId);
	
	boolean addSightSpotTitle(Map<String,Integer>map);
	
	SightSpot findSightSpotById(Integer id);

	List<SightSpot> findAllSightSpotByPage(Page page);//分页查询

	Integer findRows(Page page);

	List<SightSpot> findAllSightSpot();//查询全部用户
	
	List<SightSpot> findSightSpotByCityId(Integer city_id);
	
	List<SightSpot> findSightSpotByAreaId(Integer area_id);
	
	List<SightSpot> findSightSpots();
}
