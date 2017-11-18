package com.dq.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.AttDao;
import com.dq.dao.ProattrDao;
import com.dq.entity.Att;
import com.dq.entity.Proattr;
import com.dq.service.ProattrService;

@Service("proattrService")
@Transactional
public class ProattrServiceImpl implements ProattrService {
	@Autowired
	private ProattrDao proattrDao;
	@Autowired
	private AttDao attDao;

	public Long getTotal(Map<String, Object> map) {
		return proattrDao.getTotal(map);
	}

	public List<Proattr> getByPage(Map<String, Object> map) {
		return proattrDao.getByPage(map);
	}

	public List<Proattr> getByMap(Map<String, Object> map) {
		return proattrDao.getByMap(map);
	}

	public Proattr findById(Integer id) {
		return proattrDao.findById(id);
	}

	public Proattr findByMap(Map<String, Object> map) {
		return proattrDao.findByMap(map);
	}

	public List<Att> getByAtt(Map<String, Object> map) {
		return attDao.getByAtt(map);
	}

}
