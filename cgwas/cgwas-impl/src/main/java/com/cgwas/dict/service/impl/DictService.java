package com.cgwas.dict.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.dict.dao.api.IDictDao;
import com.cgwas.dict.entity.Dict;
import com.cgwas.dict.entity.DictVo;
import com.cgwas.dict.entity.Dicts;
import com.cgwas.dict.service.api.IDictService;

@Service
public class DictService implements IDictService {
	private IDictDao dictDao;

	public Serializable save(Dict dict) {
		return dictDao.save(dict);
	}

	public void delete(Dict dict) {
		dictDao.delete(dict);
	}

	public void deleteByExample(Dict dict) {
		dictDao.deleteByExample(dict);
	}

	public void update(Dict dict) {
		dictDao.update(dict);
	}

	public void updateIgnoreNull(Dict dict) {
		dictDao.updateIgnoreNull(dict);
	}

	public void updateByExample(Dict dict) {
		dictDao.update("updateByExampleDict", dict);
	}

	public DictVo load(Dict dict) {
		return (DictVo) dictDao.reload(dict);
	}

	public DictVo selectForObject(Dict dict) {
		return (DictVo) dictDao.selectForObject("selectDict", dict);
	}

	public List<DictVo> selectForList(Dict dict) {
		return (List<DictVo>) dictDao.selectForList("selectDict", dict);
	}

	public Page page(Page page, Dict dict) {
		return dictDao.page(page, dict);
	}

	@Autowired
	public void setIDictDao(@Qualifier("dictDao") IDictDao dictDao) {
		this.dictDao = dictDao;
	}

	@Override
	public List<Dicts> getSystemDicts() {

		return (List<Dicts>) dictDao.selectForList("getSystemDicts");
	}

}
