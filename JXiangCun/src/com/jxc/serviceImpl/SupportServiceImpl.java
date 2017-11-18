package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.SupportDao;
import com.jxc.entity.Support;
import com.jxc.page.Page;
import com.jxc.service.SupportService;
@Service("supportService")
@Transactional
public class SupportServiceImpl implements SupportService {
@Resource
private SupportDao dao;

	public List<Support> findAllSupport() {
		return dao.findAllSupport();
	}

	public List<Support> findAllSupportByPage(Page page) {
		return dao.findAllSupportByPage(page);
	}

	public Integer findRows(Page page) {
		return dao.findRows(page);
	}

	public Support findSupportById(Integer id) {
		return dao.findSupportById(id);
	}

	public boolean addSupport(Support support) {
		return dao.addSupport(support);
	}

	public boolean updateSupport(Support support) {
		return dao.updateSupport(support);
	}

	public boolean deleteSupportById(Integer id) {
		return dao.deleteSupportById(id);
	}

}
