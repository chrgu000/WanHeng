package com.kg.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.kg.entity.Footprint;
import com.kg.page.Page;

public interface FootPrintService {
	List<Footprint> getFootprintByPage(Page page);

	Integer getRows(Page page);
	
	void updateFootprint(Footprint footprint);
	
	 Footprint getFootprintById(Integer id);
	
	 void updateFootprint(Footprint footprint,HttpServletResponse response,String flag) throws Exception;

	void deleteFootprint(String ids, HttpServletResponse response,String flag)
			throws Exception;

	List<Footprint> getFootprintByTeacherId(Map<String, String> map);

	void updateFootprint(Footprint footprint, HttpServletResponse response)throws Exception;

	List<Footprint> getFootprintByBabyId(Integer id);

	List<Footprint> getFootprintByGardenId(Integer teacherId);
}
