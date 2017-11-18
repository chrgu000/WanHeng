package com.to.service;


import com.to.entity.SupportingFacility;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface SupportingFacilityService {
	List<SupportingFacility> getSupportingFacilityByPage(Page page);
	 
	List<SupportingFacility> getAllSupportingFacility();
	
	 Integer getRows(Page page);
	 
	 SupportingFacility getSupportingFacilityById(Integer id);
	 
	void updateSupportingFacility(SupportingFacility supportingFacility, HttpServletResponse response)throws Exception;
	
	void addSupportingFacility(SupportingFacility supportingFacility, HttpServletResponse response)throws Exception;
	
	void deleteSupportingFacility(String ids, HttpServletResponse response)throws Exception;

	List<SupportingFacility> getSupportingFacilityByHouseId(Integer houseId);
}
