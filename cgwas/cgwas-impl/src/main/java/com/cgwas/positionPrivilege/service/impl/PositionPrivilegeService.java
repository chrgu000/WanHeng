package com.cgwas.positionPrivilege.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.positionPrivilege.dao.api.IPositionPrivilegeDao;
import com.cgwas.positionPrivilege.entity.PositionPrivilege;
import com.cgwas.positionPrivilege.entity.PositionPrivilegeVo;
import com.cgwas.positionPrivilege.service.api.IPositionPrivilegeService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class PositionPrivilegeService implements IPositionPrivilegeService {
	private IPositionPrivilegeDao positionPrivilegeDao;

	public Serializable save(PositionPrivilege positionPrivilege){
		return positionPrivilegeDao.save(positionPrivilege);
	}

	public void delete(PositionPrivilege positionPrivilege){
		positionPrivilegeDao.delete(positionPrivilege);
	}
	
	public void deleteByExample(PositionPrivilege positionPrivilege){
		positionPrivilegeDao.deleteByExample(positionPrivilege);
	}

	public void update(PositionPrivilege positionPrivilege){
		positionPrivilegeDao.update(positionPrivilege);
	}
	
	public void updateIgnoreNull(PositionPrivilege positionPrivilege){
		positionPrivilegeDao.updateIgnoreNull(positionPrivilege);
	}
		
	public void updateByExample(PositionPrivilege positionPrivilege){
		positionPrivilegeDao.update("updateByExamplePositionPrivilege", positionPrivilege);
	}

	public PositionPrivilegeVo load(PositionPrivilege positionPrivilege){
		return (PositionPrivilegeVo)positionPrivilegeDao.reload(positionPrivilege);
	}
	
	public PositionPrivilegeVo selectForObject(PositionPrivilege positionPrivilege){
		return (PositionPrivilegeVo)positionPrivilegeDao.selectForObject("selectPositionPrivilege",positionPrivilege);
	}
	
	@SuppressWarnings("unchecked")
	public List<PositionPrivilegeVo> selectForList(PositionPrivilege positionPrivilege){
		return (List<PositionPrivilegeVo>)positionPrivilegeDao.selectForList("selectPositionPrivilege",positionPrivilege);
	}
	
	public Page page(Page page, PositionPrivilege positionPrivilege) {
		return positionPrivilegeDao.page(page, positionPrivilege);
	}

	@Autowired
	public void setIPositionPrivilegeDao(
			@Qualifier("positionPrivilegeDao") IPositionPrivilegeDao  positionPrivilegeDao) {
		this.positionPrivilegeDao = positionPrivilegeDao;
	}

}
