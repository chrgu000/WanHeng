package com.cgwas.degree.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.degree.dao.api.IDegreeDao;
import com.cgwas.degree.entity.Degree;
import com.cgwas.degree.entity.DegreeVo;
import com.cgwas.degree.service.api.IDegreeService;

@Service
public class DegreeService implements IDegreeService {
	private IDegreeDao degreeDao;

	public Serializable save(Degree degree) {
		return degreeDao.save(degree);
	}

	public void delete(Degree degree) {
		degreeDao.delete(degree);
	}

	public void deleteByExample(Degree degree) {
		degreeDao.deleteByExample(degree);
	}

	public void update(Degree degree) {
		degreeDao.update(degree);
	}

	public void updateIgnoreNull(Degree degree) {
		degreeDao.updateIgnoreNull(degree);
	}

	public void updateByExample(Degree degree) {
		degreeDao.update("updateByExampleDegree", degree);
	}

	public DegreeVo load(Degree degree) {
		return (DegreeVo) degreeDao.reload(degree);
	}

	public DegreeVo selectForObject(Degree degree) {
		List<DegreeVo> diList = (List<DegreeVo>) degreeDao.selectForList(
				"selectDegree", degree);
		if (diList.size() == 0) {
			return new DegreeVo();
		} else {
			return diList.get(0);

		}
	}

	public List<DegreeVo> selectForList(Degree degree) {
		return (List<DegreeVo>) degreeDao.selectForList("selectDegree", degree);
	}

	public Page page(Page page, Degree degree) {
		return degreeDao.page(page, degree);
	}

	@Autowired
	public void setIDegreeDao(@Qualifier("degreeDao") IDegreeDao degreeDao) {
		this.degreeDao = degreeDao;
	}

}
