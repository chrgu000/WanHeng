package com.cgwas.labelTag.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.labelTag.dao.api.ILabelTagDao;
import com.cgwas.labelTag.entity.LabelTag;
import com.cgwas.labelTag.entity.LabelTagVo;
import com.cgwas.labelTag.service.api.ILabelTagService;
/**
*  Author yangjun
*/
@Service
public class LabelTagService implements ILabelTagService {
	private ILabelTagDao labelTagDao;

	public Serializable save(LabelTag labelTag){
		return labelTagDao.save(labelTag);
	}

	public void delete(LabelTag labelTag){
		labelTagDao.delete(labelTag);
	}
	
	public void deleteByExample(LabelTag labelTag){
		labelTagDao.deleteByExample(labelTag);
	}

	public void update(LabelTag labelTag){
		labelTagDao.update(labelTag);
	}
	
	public void updateIgnoreNull(LabelTag labelTag){
		labelTagDao.updateIgnoreNull(labelTag);
	}
		
	public void updateByExample(LabelTag labelTag){
		labelTagDao.update("updateByExampleLabelTag", labelTag);
	}

	public LabelTagVo load(LabelTag labelTag){
		return (LabelTagVo)labelTagDao.reload(labelTag);
	}
	
	public LabelTagVo selectForObject(LabelTag labelTag){
		return (LabelTagVo)labelTagDao.selectForObject("selectLabelTag",labelTag);
	}
	
	public List<LabelTagVo> selectForList(LabelTag labelTag){
		return (List<LabelTagVo>)labelTagDao.selectForList("selectLabelTag",labelTag);
	}
	
	public Page page(Page page, LabelTag labelTag) {
		return labelTagDao.page(page, labelTag);
	}

	@Autowired
	public void setILabelTagDao(
			@Qualifier("labelTagDao") ILabelTagDao  labelTagDao) {
		this.labelTagDao = labelTagDao;
	}

}
