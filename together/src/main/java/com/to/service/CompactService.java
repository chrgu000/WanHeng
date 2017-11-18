package com.to.service;


import com.to.entity.*;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public interface CompactService {
	List<Compact> getCompactByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 Compact getCompactById(Integer id);
	 
	void updateCompact(Compact compact, HttpServletResponse response)throws Exception;
	
	void addCompact(Compact compact, HttpServletResponse response)throws Exception;
	
	void deleteCompact(String ids, HttpServletResponse response)throws Exception;

	List<Compact> getCompactByStatus(Map<String, Integer> map);

	void getCompactByHouseId(Integer houseId, HttpServletResponse response);

	void addGas(Gas gas);

	void addCondo(Condo condo);

	void addWater(Water water);

	void addPower(Power power);

	void updatePower(Fee fee);

	void updateCondo(Fee fee);

	void updateGas(Fee fee);

	void updateWater(Fee fee);

	List<Compact> getCompactByStatusAndUser(Map<String, String> map);

	List<Price> getAllPrice();
}
