package com.to.service;


import com.to.entity.Orientation;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface OrientationService {
	List<Orientation> getOrientationByPage(Page page);
	 
	List<Orientation> getAllOrientation();
	
	 Integer getRows(Page page);
	 
	 Orientation getOrientationById(Integer id);
	 
	void updateOrientation(Orientation orientation, HttpServletResponse response)throws Exception;
	
	void addOrientation(Orientation orientation, HttpServletResponse response)throws Exception;
	
	void deleteOrientation(String ids, HttpServletResponse response)throws Exception;
}
