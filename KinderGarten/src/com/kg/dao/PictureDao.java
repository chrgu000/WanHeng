package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.PicVo;
import com.kg.entity.Picture;
import com.kg.entity.StylePic;
import com.kg.page.Page;
import com.kg.page.PicturePage;

public interface PictureDao {
	List<Picture> getPictureByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 List<Picture> getPictureByBabyIds(Map<String,String[]> map);
	 
	 Picture getPictureById(Integer id);
	 
	 void updatePicture(Picture picture);
	 
	 void deleteByIds(Map<String, String[]> map);
	 
	 void addPicture(Picture picture);

	Integer getPicVo(PicVo picvo);

	Integer getTotalPicNum(Integer babyId);

	List<Picture> getPicturesByBabyId(Integer babyId);

	List<Picture> getPicturesByTypeId(PicVo vo);

	void addBabyPicture(Picture picture);

	List<Picture> getStylePictureByPage(PicturePage page);

	Integer getStyleRows(PicturePage page);

	void deleteStyleByIds(Map<String, String[]> map);

	List<Picture> getPictureByIds(Map<String, String[]> map);

	List<StylePic> getStylePictureByIds(Map<String, String[]> map);
}
