package com.dq.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.WithdrawDao;
import com.dq.entity.Withdraw;
import com.dq.page.Page;
import com.dq.service.WithdrawService;

@Service("withdrawService")
@Transactional
public class WithdrawServiceImpl implements WithdrawService {
	@Resource
	private  WithdrawDao dao;

	public List<Withdraw> getAllByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void update(Withdraw withdraw) {
	   dao.update(withdraw);
		
	}

}
