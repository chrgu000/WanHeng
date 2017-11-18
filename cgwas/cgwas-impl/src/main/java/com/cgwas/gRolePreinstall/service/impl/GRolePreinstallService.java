package com.cgwas.gRolePreinstall.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.gRolePreinstall.dao.api.IGRolePreinstallDao;
import com.cgwas.gRolePreinstall.entity.GRolePreinstall;
import com.cgwas.gRolePreinstall.entity.GRolePreinstallVo;
import com.cgwas.gRolePreinstall.service.api.IGRolePreinstallService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class GRolePreinstallService implements IGRolePreinstallService {
	@Autowired
	private IGRolePreinstallDao gRolePreinstallDao;

	public Serializable save(GRolePreinstall gRolePreinstall){
		return gRolePreinstallDao.save(gRolePreinstall);
	}

	public void delete(GRolePreinstall gRolePreinstall){
		gRolePreinstallDao.delete(gRolePreinstall);
	}
	
	public void deleteByExample(GRolePreinstall gRolePreinstall){
		gRolePreinstallDao.deleteByExample(gRolePreinstall);
	}

	public void update(GRolePreinstall gRolePreinstall){
		gRolePreinstallDao.update(gRolePreinstall);
	}
	
	public void updateIgnoreNull(GRolePreinstall gRolePreinstall){
		gRolePreinstallDao.updateIgnoreNull(gRolePreinstall);
	}
		
	public void updateByExample(GRolePreinstall gRolePreinstall){
		gRolePreinstallDao.update("updateByExampleGRolePreinstall", gRolePreinstall);
	}

	public GRolePreinstallVo load(GRolePreinstall gRolePreinstall){
		return (GRolePreinstallVo)gRolePreinstallDao.reload(gRolePreinstall);
	}
	
	public GRolePreinstallVo selectForObject(GRolePreinstall gRolePreinstall){
		return (GRolePreinstallVo)gRolePreinstallDao.selectForObject("selectGRolePreinstall",gRolePreinstall);
	}
	
	@SuppressWarnings("unchecked")
	public List<GRolePreinstallVo> selectForList(GRolePreinstall gRolePreinstall){
		return (List<GRolePreinstallVo>)gRolePreinstallDao.selectForList("selectGRolePreinstall",gRolePreinstall);
	}
	
	public Page page(Page page, GRolePreinstall gRolePreinstall) {
		return gRolePreinstallDao.page(page, gRolePreinstall);
	}

}
