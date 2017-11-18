package com.jxc.service;

import java.util.List;

import com.jxc.entity.Area;
import com.jxc.page.Page;

public interface AreaService {
	List<Area> findAllArea();

	List<Area> findAllAreaByPage(Page page);

	List<Area> findAreasByCityId(Integer city_id);
	
	Integer findRows(Page page);

	Area findAreaById(Integer id);

	boolean addArea(Area area);

	boolean updateArea(Area area);

	boolean deleteAreaById(Integer id);
}
