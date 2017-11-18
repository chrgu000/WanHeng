package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.CityDao;
import com.jxc.entity.City;
import com.jxc.page.Page;
import com.jxc.service.CityService;

@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService {
	@Resource
	private CityDao dao;

	public List<City> findAllCity() {
		return dao.findAllCity();
	}

	public List<City> findAllCityByPage(Page page) {
	 
		return dao.findAllCityByPage(page);
	}

	public Integer findRows(Page page) {
		 
		return dao.findRows(page);
	}

	public Integer findRow() {
	 
		return dao.findRow();
	}

	public City findCityById(Integer id) {
		 
		return dao.findCityById(id);
	}

	public boolean addCity(City city) {
		  
		return dao.addCity(city);
	}

	public boolean updateCity(City city) {
		 
		return dao.updateCity(city);
	}

	public boolean deleteCityById(Integer id) {
		 
		return dao.deleteCityById(id);
	}

}
