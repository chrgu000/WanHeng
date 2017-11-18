package com.jxc.service;

import java.util.List;

import com.jxc.entity.Picture;
import com.jxc.page.Page;

public interface PictureService {
	List<Picture> findAllPicture(Integer type);

	List<Picture> findAllPictureByPage(Page page);

	List<Picture> findPicturesByMerchantId(Integer id);

	Integer findRows(Page page);

	Picture findPictureById(Integer id);

	boolean addPicture(Picture picture);

	boolean updatePicture(Picture picture);

	boolean deletePictureById(Integer id);
}
