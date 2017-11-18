package com.dq.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.BalanceDao;
import com.dq.entity.Balance;
import com.dq.page.Page;
import com.dq.service.BalanceService;

@Service("balanceService")
@Transactional
public class BalanceServiceImpl implements BalanceService {
	@Resource
	private BalanceDao dao;

	public List<Balance> getAllByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

}
