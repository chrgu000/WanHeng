package com.cgwas.labelTag.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.labelTag.entity.LabelTag;
/**
*  Author yangjun
*/
public interface ILabelTagDao extends IDaoSupport {
	Page page(Page page, LabelTag labelTag);
}