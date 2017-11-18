package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.AreaDao;
import com.jxc.entity.Area;
import com.jxc.page.Page;
import com.jxc.service.AreaService;

@Service("areaService")
@Transactional
public class AreaServiceImpl implements AreaService {
	@Resource
	private AreaDao dao;

	public List<Area> findAllArea() {
		return dao.findAllArea();
	}

	public List<Area> findAllAreaByPage(Page page) {
	 
		return dao.findAllAreaByPage(page);
	}

	public Integer findRows(Page page) {
		 
		return dao.findRows(page);
	}
 
	public Area findAreaById(Integer id) {
		 
		return dao.findAreaById(id);
	}

	public boolean addArea(Area area) {
		  
		return dao.addArea(area);
	}

	public boolean updateArea(Area area) {
		 
		return dao.updateArea(area);
	}

	public boolean deleteAreaById(Integer id) {
		 
		return dao.deleteAreaById(id);
	}


	public List<Area> findAreasByCityId(Integer cityId) {
		return dao.findAreasByCityId(cityId);
	}

}
