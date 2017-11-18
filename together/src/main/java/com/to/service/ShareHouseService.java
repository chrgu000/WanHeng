package com.to.service;


import com.to.entity.ShareHouse;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface ShareHouseService {
	List<ShareHouse> getShareHouseByPage(Page page);
	 
	List<ShareHouse> getAllShareHouse();
	
	 Integer getRows(Page page);
	 
	 ShareHouse getShareHouseById(Integer id);
	 
	void updateShareHouse(ShareHouse shareHouse, HttpServletResponse response)throws Exception;
	
	void addShareHouse(ShareHouse shareHouse, HttpServletResponse response)throws Exception;
	
	void deleteShareHouse(String ids, HttpServletResponse response)throws Exception;
}
