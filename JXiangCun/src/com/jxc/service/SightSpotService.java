package com.jxc.service;

import java.util.List;
import java.util.Map;

import com.jxc.entity.SightSpot;
import com.jxc.page.Page;

public interface SightSpotService {
	boolean addSightSpot(SightSpot sightSpot);//ע��

	boolean updateSightSpot(SightSpot sightSpot);

	boolean deleteSightSpotById(Integer id);
	
	boolean deleteSightSpotTitle(Integer sightSpotId);
	
	boolean addSightSpotTitle(Map<String,Integer>map);
	
	SightSpot findSightSpotById(Integer id);

	List<SightSpot> findAllSightSpotByPage(Page page);//��ҳ��ѯ

	Integer findRows(Page page);

	List<SightSpot> findAllSightSpot();//��ѯȫ���û�
	
	List<SightSpot> findSightSpotByCityId(Integer city_id);
	
	List<SightSpot> findSightSpotByAreaId(Integer area_id);
	
	List<SightSpot> findSightSpots();
}
