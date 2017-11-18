package com.cgwas.educationalBackground.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.educationalBackground.entity.EducationalBackground;



public interface IEducationalBackgroundDao extends IDaoSupport {
	Page page(Page page, EducationalBackground educationalBackground);
}