package com.dq.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dq.entity.Picture;
import com.dq.page.Page;

public interface PictureService {
	List<Picture> getAllByPage(Page page);

	void addPicture(Picture picture, HttpServletResponse response)
			throws Exception;

	void deletePicture(String ids, HttpServletResponse response)
			throws Exception;
}
