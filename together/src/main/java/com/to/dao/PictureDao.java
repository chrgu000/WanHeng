package com.to.dao;

import com.to.entity.Picture;
import com.to.page.Page;

import java.util.List;
import java.util.Map;



public interface PictureDao {
	List<Picture> getPictureByPage(Page page);

	 Integer getRows(Page page);

	 void deleteByIds(Map<String, String[]> map);

	List<Picture> getPictureByIds(Map<String, String[]> map);


	List<Picture> getPictureByHouseId(Integer houseId);

	Picture getPictureById(Integer id);

	void deletePictureById(Integer id);
}
