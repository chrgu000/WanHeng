package com.dq.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.ProattDao;
import com.dq.entity.Proatt;
import com.dq.service.ProattService;

@Service("proattService")
@Transactional
public class ProattServiceImpl implements ProattService {
	@Autowired
	private ProattDao proattDao;

	public Long getTotal(Map<String, Object> map) {
		return proattDao.getTotal(map);
	}

	public List<Proatt> getByPage(Map<String, Object> map) {
		return proattDao.getByPage(map);
	}

	public List<Proatt> getByMap(Map<String, Object> map) {
		return proattDao.getByMap(map);
	}

	public Proatt findById(Integer id) {
		return proattDao.findById(id);
	}

	public Proatt findByMap(Map<String, Object> map) {
		return proattDao.findByMap(map);
	}

}
