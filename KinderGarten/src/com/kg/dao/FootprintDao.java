package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Footprint;
import com.kg.page.Page;

public interface FootprintDao {
	List<Footprint> getFootprintByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 Integer getCount(Integer baby_id);
	 
	 void updateByBabyId(Footprint footprint);
	 
	 Footprint getFootprintById(Integer id);
	 
	 void updateFootprint(Footprint footprint);
	 
	void deleteByIds(Map<String, String[]> map);
	 
	 void addFootprint(Footprint footprints);

	List<Footprint> getFootprintByTeacherId(Map<String,String> map);

	List<Footprint> getFootprintByBabyId(Integer id);

	List<Footprint> getFootprintByGardenId(Integer gardenId);

	List<Footprint> getFootprintBabyIds(Map<String, String[]> map);

	List<Footprint> getFootprintTeacherIds(Map<String, String[]> map);
}
