package com.kg.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kg.entity.Garden;
import com.kg.page.Page;

public interface GardenService {
	List<Garden> getGardenByPage(Page page);

	List<Garden> getAllGarden();

	Integer getRows(Page page);

	Garden getGardenById(Integer id);

	void updateGarden(Garden garden);


	void addGarden(Garden garden);

	void updateGarden(Garden garden, HttpServletResponse response)
			throws Exception;

	void addGarden(Garden garden, HttpServletResponse response) throws Exception;

	void deleteGarden(String ids, HttpServletResponse response) throws Exception;

	List<Garden> getGardensByAdminId(Integer id);
}
