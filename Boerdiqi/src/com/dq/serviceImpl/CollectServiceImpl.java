package com.dq.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.CollectDao;
import com.dq.entity.Collect;
import com.dq.service.CollectService;

@Service("collectService")
@Transactional
public class CollectServiceImpl implements CollectService {
	@Resource
	private CollectDao dao;

	public List<Collect> getCollects(Integer user_id) {
		return dao.getCollects(user_id);
	}
}
