package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.PictureDao;
import com.jxc.entity.Picture;
import com.jxc.page.Page;
import com.jxc.service.PictureService;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {
	@Resource
	private PictureDao dao;

	public List<Picture> findAllPicture(Integer type) {
		return dao.findAllPicture(type);
	}

	public List<Picture> findAllPictureByPage(Page page) {
		return dao.findAllPictureByPage(page);
	}

	public Integer findRows(Page page) {
		return dao.findRows(page);
	}

	public Picture findPictureById(Integer id) {
		return dao.findPictureById(id);
	}

	public boolean addPicture(Picture picture) {
		return dao.addPicture(picture);
	}

	public boolean updatePicture(Picture picture) {
		return dao.updatePicture(picture);
	}

	public boolean deletePictureById(Integer id) {
		return dao.deletePictureById(id);
	}

	public List<Picture> findPicturesByMerchantId(Integer id) {
		return dao.findPicturesByMerchantId(id);
	}

}
