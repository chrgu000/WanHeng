package com.cgwas.workExperience.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.workExperience.entity.WorkExperience;



public interface IWorkExperienceDao extends IDaoSupport {
	Page page(Page page, WorkExperience workExperience);
}