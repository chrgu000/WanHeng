package com.cgwas.imgInfo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.imgInfo.dao.api.IImgInfoDao;
import com.cgwas.imgInfo.entity.ImgInfo;
import com.cgwas.imgInfo.entity.ImgInfoVo;
import com.cgwas.imgInfo.service.api.IImgInfoService;

@Service
public class ImgInfoService implements IImgInfoService {
	private IImgInfoDao imgInfoDao;

	public Serializable save(ImgInfo imgInfo) {
		return imgInfoDao.save(imgInfo);
	}

	public void delete(ImgInfo imgInfo) {
		imgInfoDao.delete(imgInfo);
	}

	public void deleteByExample(ImgInfo imgInfo) {
		imgInfoDao.deleteByExample(imgInfo);
	}

	public void update(ImgInfo imgInfo) {
		imgInfoDao.update(imgInfo);
	}

	public void updateIgnoreNull(ImgInfo imgInfo) {
		imgInfoDao.updateIgnoreNull(imgInfo);
	}

	public void updateByExample(ImgInfo imgInfo) {
		imgInfoDao.update("updateByExampleImgInfo", imgInfo);
	}

	public ImgInfoVo load(ImgInfo imgInfo) {
		return (ImgInfoVo) imgInfoDao.reload(imgInfo);
	}

	public ImgInfoVo selectForObject(ImgInfo imgInfo) {
		return (ImgInfoVo) imgInfoDao.selectForObject("selectImgInfo", imgInfo);
	}

	public List<ImgInfoVo> selectForList(ImgInfo imgInfo) {
		return (List<ImgInfoVo>) imgInfoDao.selectForList("selectImgInfo",
				imgInfo);
	}

	public Page page(Page page, ImgInfo imgInfo) {
		return imgInfoDao.page(page, imgInfo);
	}

	@Autowired
	public void setIImgInfoDao(@Qualifier("imgInfoDao") IImgInfoDao imgInfoDao) {
		this.imgInfoDao = imgInfoDao;
	}

	@Override
	public List<ImgInfo> getImgInfoById(ImgInfo imgInfo, String ids) {
		if (ids != null) { // 检测是否为批量
			// id批量查询
			String[] str1 = ids.split(",");// 将String[] 转换为long[]
			List<Long> str2 = new ArrayList<Long>();

			for (int i = 0; i < str1.length; i++) {
				str2.add(Long.valueOf(str1[i]));
			}
			// 参数列表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("imgInfo", imgInfo);
			map.put("list", str2);
			return (List<ImgInfo>) imgInfoDao.selectForList("getImgInfoByIds",
					map);
		} else {
			// 单个id查询
			return (List<ImgInfo>) imgInfoDao.selectForList("getImgInfoById",
					imgInfo);
		}

	}

	@Override
	public Serializable saveBatch(List<String> imgPath, ImgInfo imgInfo) {
		ImgInfo saveInfo = new ImgInfo();
		for (String path : imgPath) {
			saveInfo = imgInfo;
			saveInfo.setImg_url(path);
			this.save(saveInfo);
		}
		return null;
	}

}
