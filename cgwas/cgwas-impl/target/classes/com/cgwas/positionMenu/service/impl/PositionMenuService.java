package com.cgwas.positionMenu.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.positionMenu.dao.api.IPositionMenuDao;
import com.cgwas.positionMenu.entity.PositionMenu;
import com.cgwas.positionMenu.entity.PositionMenuVo;
import com.cgwas.positionMenu.service.api.IPositionMenuService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class PositionMenuService implements IPositionMenuService {
	private IPositionMenuDao positionMenuDao;

	public Serializable save(PositionMenu positionMenu){
		return positionMenuDao.save(positionMenu);
	}

	public void delete(PositionMenu positionMenu){
		positionMenuDao.delete(positionMenu);
	}
	
	public void deleteByExample(PositionMenu positionMenu){
		positionMenuDao.deleteByExample(positionMenu);
	}

	public void update(PositionMenu positionMenu){
		positionMenuDao.update(positionMenu);
	}
	
	public void updateIgnoreNull(PositionMenu positionMenu){
		positionMenuDao.updateIgnoreNull(positionMenu);
	}
		
	public void updateByExample(PositionMenu positionMenu){
		positionMenuDao.update("updateByExamplePositionMenu", positionMenu);
	}

	public PositionMenuVo load(PositionMenu positionMenu){
		return (PositionMenuVo)positionMenuDao.reload(positionMenu);
	}
	
	public PositionMenuVo selectForObject(PositionMenu positionMenu){
		return (PositionMenuVo)positionMenuDao.selectForObject("selectPositionMenu",positionMenu);
	}
	
	public List<PositionMenuVo> selectForList(PositionMenu positionMenu){
		return (List<PositionMenuVo>)positionMenuDao.selectForList("selectPositionMenu",positionMenu);
	}
	
	public Page page(Page page, PositionMenu positionMenu) {
		return positionMenuDao.page(page, positionMenu);
	}

	@Autowired
	public void setIPositionMenuDao(
			@Qualifier("positionMenuDao") IPositionMenuDao  positionMenuDao) {
		this.positionMenuDao = positionMenuDao;
	}

}
