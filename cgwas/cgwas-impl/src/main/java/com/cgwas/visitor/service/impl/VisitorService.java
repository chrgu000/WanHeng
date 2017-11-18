package com.cgwas.visitor.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.visitor.dao.api.IVisitorDao;
import com.cgwas.visitor.entity.Visitor;
import com.cgwas.visitor.entity.VisitorVo;
import com.cgwas.visitor.service.api.IVisitorService;

@Service
public class VisitorService implements IVisitorService {
	private IVisitorDao visitorDao;

	public Serializable save(Visitor visitor) {
		return visitorDao.save(visitor);
	}

	public void delete(Visitor visitor) {
		visitorDao.delete(visitor);
	}

	public void deleteByExample(Visitor visitor) {
		visitorDao.deleteByExample(visitor);
	}

	public void update(Visitor visitor) {
		visitorDao.update(visitor);
	}

	public void updateIgnoreNull(Visitor visitor) {
		visitorDao.updateIgnoreNull(visitor);
	}

	public void updateByExample(Visitor visitor) {
		visitorDao.update("updateByExampleVisitor", visitor);
	}

	public VisitorVo load(Visitor visitor) {
		return (VisitorVo) visitorDao.reload(visitor);
	}

	public VisitorVo selectForObject(Visitor visitor) {
		return (VisitorVo) visitorDao.selectForObject("selectVisitor", visitor);
	}

	public List<VisitorVo> selectForList(Visitor visitor) {
		return (List<VisitorVo>) visitorDao.selectForList("selectVisitor",
				visitor);
	}

	public Page page(Page page, Visitor visitor) {
		return visitorDao.page(page, visitor);
	}

	@Autowired
	public void setIVisitorDao(@Qualifier("visitorDao") IVisitorDao visitorDao) {
		this.visitorDao = visitorDao;
	}

	@Override
	public void getHaveIpSave(Visitor visitor) {

		Long count = (Long) visitorDao.selectForObject("getHaveIp",
				visitor.getIp());
		if (count == 0) { // 如果不存在则保存本次访问
			this.save(visitor);
		}
	}

	@Override
	public Long getVisitorCount(VisitorVo visitorVo) {
		return (Long) visitorDao.selectForObject("getVisitorCount", visitorVo);
	}

}
