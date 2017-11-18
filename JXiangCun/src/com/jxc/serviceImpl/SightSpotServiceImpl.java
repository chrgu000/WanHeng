package com.jxc.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.SightSpotDao;
import com.jxc.entity.SightSpot;
import com.jxc.page.Page;
import com.jxc.service.SightSpotService;

@Service("sightSpotService")
@Transactional
public class SightSpotServiceImpl implements SightSpotService {
	@Resource
	private SightSpotDao dao;

	public boolean addSightSpot(SightSpot sightSpot) {
		return dao.addSightSpot(sightSpot);
	}

	public boolean updateSightSpot(SightSpot sightSpot) {
		return dao.updateSightSpot(sightSpot);
	}

	public boolean deleteSightSpotById(Integer id) {
		return dao.deleteSightSpotById(id);
	}

	public SightSpot findSightSpotById(Integer id) {
		return dao.findSightSpotById(id);
	}

	public List<SightSpot> findAllSightSpotByPage(Page page) {
		return dao.findAllSightSpotByPage(page);
	}

	public Integer findRows(Page page) {
		return dao.findRows(page);
	}

	public List<SightSpot> findAllSightSpot() {
		return dao.findAllSightSpot();
	}

	public List<SightSpot> findSightSpotByCityId(Integer city_id) {
		return dao.findSightSpotByCityId(city_id);
	}

	public List<SightSpot> findSightSpots() {
		return dao.findSightSpots();
	}

	public boolean addSightSpotTitle(Map<String, Integer> map) {
		return dao.addSightSpotTitle(map);
	}

	public boolean deleteSightSpotTitle(Integer sightSpotId) {
		return dao.deleteSightSpotTitle(sightSpotId);
	}

	public List<SightSpot> findSightSpotByAreaId(Integer areaId) {
		return dao.findSightSpotByAreaId(areaId);
	}

}
