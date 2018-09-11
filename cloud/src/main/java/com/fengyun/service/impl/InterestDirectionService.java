package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.IInterestDirectionDao;
import com.fengyun.entity.InterestDirection;
import com.fengyun.entity.InterestDirectionVo;
import com.fengyun.service.IInterestDirectionService;
/**
*  Author yangjun
*/
@Service
public class InterestDirectionService implements IInterestDirectionService {
	private IInterestDirectionDao interestDirectionDao;

	public Serializable save(InterestDirection interestDirection){
		return interestDirectionDao.save(interestDirection);
	}

	public void delete(InterestDirection interestDirection){
		interestDirectionDao.delete(interestDirection);
	}
	
	public void deleteByExample(InterestDirection interestDirection){
		interestDirectionDao.deleteByExample(interestDirection);
	}

	public void update(InterestDirection interestDirection){
		interestDirectionDao.update(interestDirection);
	}
	
	public void updateIgnoreNull(InterestDirection interestDirection){
		interestDirectionDao.updateIgnoreNull(interestDirection);
	}
		
	public void updateByExample(InterestDirection interestDirection){
		interestDirectionDao.update("updateByExampleInterestDirection", interestDirection);
	}

	public InterestDirectionVo load(InterestDirection interestDirection){
		return (InterestDirectionVo)interestDirectionDao.reload(interestDirection);
	}
	
	public InterestDirectionVo selectForObject(InterestDirection interestDirection){
		return (InterestDirectionVo)interestDirectionDao.selectForObject("selectInterestDirection",interestDirection);
	}
	
	@SuppressWarnings("unchecked")
	public List<InterestDirectionVo> selectForList(InterestDirection interestDirection){
		return (List<InterestDirectionVo>)interestDirectionDao.selectForList("selectInterestDirection",interestDirection);
	}
	
	public Page page(Page page, InterestDirection interestDirection) {
		return interestDirectionDao.page(page, interestDirection);
	}

	@Autowired
	public void setIInterestDirectionDao(
			@Qualifier("interestDirectionDao") IInterestDirectionDao  interestDirectionDao) {
		this.interestDirectionDao = interestDirectionDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InterestDirection> getAllInterestDirections(Integer type_id) {
		return (List<InterestDirection>)interestDirectionDao.selectForList("getAllInterestDirections",type_id);
	}

}
