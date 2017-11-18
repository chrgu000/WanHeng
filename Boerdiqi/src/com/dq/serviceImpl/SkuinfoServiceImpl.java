package com.dq.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.SkuinfoDao;
import com.dq.entity.Skuinfo;
import com.dq.service.SkuinfoService;

@Service("skuinfoService")
@Transactional
public class SkuinfoServiceImpl implements SkuinfoService {
	@Autowired
	private SkuinfoDao skuinfoDao;

	public Long getTotal(Map<String, Object> map) {
		return skuinfoDao.getTotal(map);
	}

	public List<Skuinfo> getByPage(Map<String, Object> map) {
		return skuinfoDao.getByPage(map);
	}

	public List<Skuinfo> getByMap(Map<String, Object> map) {
		return skuinfoDao.getByMap(map);
	}

	public Skuinfo findById(Integer id) {
		return skuinfoDao.findById(id);
	}

	public Skuinfo findByMap(Map<String, Object> map) {
		return skuinfoDao.findByMap(map);
	}

}
