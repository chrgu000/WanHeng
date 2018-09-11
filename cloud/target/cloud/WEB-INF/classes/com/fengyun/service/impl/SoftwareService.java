package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.ISoftwareDao;
import com.fengyun.entity.Software;
import com.fengyun.entity.SoftwareVo;
import com.fengyun.service.ISoftwareService;
/**
*  Author yangjun
*/
@Service
public class SoftwareService implements ISoftwareService {
	private ISoftwareDao softwareDao;

	public Serializable save(Software software){
		return softwareDao.save(software);
	}

	public void delete(Software software){
		softwareDao.delete(software);
	}
	
	public void deleteByExample(Software software){
		softwareDao.deleteByExample(software);
	}

	public void update(Software software){
		softwareDao.update(software);
	}
	
	public void updateIgnoreNull(Software software){
		softwareDao.updateIgnoreNull(software);
	}
		
	public void updateByExample(Software software){
		softwareDao.update("updateByExampleSoftware", software);
	}

	public SoftwareVo load(Software software){
		return (SoftwareVo)softwareDao.reload(software);
	}
	
	public SoftwareVo selectForObject(Software software){
		return (SoftwareVo)softwareDao.selectForObject("selectSoftware",software);
	}
	
	@SuppressWarnings("unchecked")
	public List<SoftwareVo> selectForList(Software software){
		return (List<SoftwareVo>)softwareDao.selectForList("selectSoftware",software);
	}
	
	public Page page(Page page, Software software) {
		return softwareDao.page(page, software);
	}

	@Autowired
	public void setISoftwareDao(
			@Qualifier("softwareDao") ISoftwareDao  softwareDao) {
		this.softwareDao = softwareDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Software> getAllSoftwares(Integer type_id) {
		return (List<Software>)softwareDao.selectForList("getAllSoftwares",type_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Software> getSoftwaresByInterestDirectionId(
			Integer interest_direction_id) {
		return (List<Software>)softwareDao.selectForList("getSoftwaresByInterestDirectionId",interest_direction_id);
	}

}
