package com.cgwas.imgInfo.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.imgInfo.entity.ImgInfo;

public interface IImgInfoDao extends IDaoSupport {
	Page page(Page page, ImgInfo imgInfo);
}