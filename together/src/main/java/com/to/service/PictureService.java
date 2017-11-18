package com.to.service;


import com.to.entity.Picture;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface PictureService {
	List<Picture> getPictureByPage(Page page);
	Integer getRows(Page page);
	void deletePicture(String ids, HttpServletResponse response, HttpSession session)
			throws Exception;

	List<Picture> getPictureByHouseId(Integer houseId);

	Picture getPictureById(Integer id);

	void deletePictureById(Picture p,HttpSession session,HttpServletResponse response);
}
