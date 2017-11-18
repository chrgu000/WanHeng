package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Garden;
import com.kg.page.Page;

public interface GardenDao {
	List<Garden> getGardenByPage(Page page);

	List<Garden> getAllGarden();

	Integer getRows(Page page);

	Garden getGardenById(Integer id);

	void updateGarden(Garden garden);

	void deleteByIds(Map<String, String[]> map);

	void addGarden(Garden garden);

	List<Garden> getGardensByAdminId(Integer id);
}
