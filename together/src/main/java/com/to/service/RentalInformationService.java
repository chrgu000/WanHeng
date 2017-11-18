package com.to.service;


import com.to.entity.RentalInformation;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface RentalInformationService {
	List<RentalInformation> getRentalInformationByPage(Page page);
	 
	List<RentalInformation> getAllRentalInformation();
	
	 Integer getRows(Page page);
	 
	 RentalInformation getRentalInformationById(Integer id);
	 
	void updateRentalInformation(RentalInformation rentalInformation, HttpServletResponse response)throws Exception;
	
	void addRentalInformation(RentalInformation rentalInformation, HttpServletResponse response)throws Exception;
	
	void deleteRentalInformation(String ids, HttpServletResponse response)throws Exception;

	void getAllRentalInformations(HttpServletResponse response)throws Exception;
}
