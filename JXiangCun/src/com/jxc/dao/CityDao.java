package com.jxc.dao;

import java.util.List;

import com.jxc.entity.City;
import com.jxc.page.Page;


public interface CityDao {
	List<City> findAllCity();

	List<City> findAllCityByPage(Page page);

	Integer findRows(Page page);

	Integer findRow();

	City findCityById(Integer id);

	boolean addCity(City city);

	boolean updateCity(City city);

	boolean deleteCityById(Integer id);
}
