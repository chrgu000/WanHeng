package com.kg.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kg.entity.PicVo;
import com.kg.entity.Picture;
import com.kg.page.Page;
import com.kg.page.PicturePage;

public interface PictureService {
	List<Picture> getPictureByPage(Page page);

	Integer getRows(Page page);

	void deletePicture(String ids, HttpServletResponse response,HttpSession session)
			throws Exception;

	Integer getPicVo(PicVo picvo);

	Integer getTotalPicNum(Integer babyId);

	List<Picture> getPicturesByBabyId(Integer babyId);

	List<Picture> getPicturesByTypeId(PicVo vo);

	void addBabyPicture(Picture picture,HttpServletResponse response)throws Exception;

	List<Picture> getStylePictureByPage(PicturePage page);

	Integer getStyleRows(PicturePage page);

	void deleteStylePicture(String ids, HttpServletResponse response,HttpSession session)throws Exception;
}
