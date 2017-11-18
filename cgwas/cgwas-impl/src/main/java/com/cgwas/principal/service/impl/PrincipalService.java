package com.cgwas.principal.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.principal.dao.api.IPrincipalDao;
import com.cgwas.principal.entity.Principal;
import com.cgwas.principal.entity.PrincipalVo;
import com.cgwas.principal.service.api.IPrincipalService;
/**
*  Author yangjun
*/
@Service
public class PrincipalService implements IPrincipalService {
	private IPrincipalDao principalDao;

	public Serializable save(Principal principal){
		return principalDao.save(principal);
	}

	public void delete(Principal principal){
		principalDao.delete(principal);
	}
	
	public void deleteByExample(Principal principal){
		principalDao.deleteByExample(principal);
	}

	public void update(Principal principal){
		principalDao.update(principal);
	}
	
	public void updateIgnoreNull(Principal principal){
		principalDao.updateIgnoreNull(principal);
	}
		
	public void updateByExample(Principal principal){
		principalDao.update("updateByExamplePrincipal", principal);
	}

	public PrincipalVo load(Principal principal){
		return (PrincipalVo)principalDao.reload(principal);
	}
	
	public PrincipalVo selectForObject(Principal principal){
		return (PrincipalVo)principalDao.selectForObject("selectPrincipal",principal);
	}
	
	public List<PrincipalVo> selectForList(Principal principal){
		return (List<PrincipalVo>)principalDao.selectForList("selectPrincipal",principal);
	}
	
	public Page page(Page page, Principal principal) {
		return principalDao.page(page, principal);
	}

	@Autowired
	public void setIPrincipalDao(
			@Qualifier("principalDao") IPrincipalDao  principalDao) {
		this.principalDao = principalDao;
	}

}
