package com.cgwas.space.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.space.dao.api.ISpaceDao;
import com.cgwas.space.entity.Space;
import com.cgwas.space.entity.SpaceVo;
import com.cgwas.space.service.api.ISpaceService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
@SuppressWarnings("unchecked")
public class SpaceService implements ISpaceService {
	private ISpaceDao spaceDao;

	public Serializable save(Space space){
		return spaceDao.save(space);
	}

	public void delete(Space space){
		spaceDao.delete(space);
	}
	
	public void deleteByExample(Space space){
		spaceDao.deleteByExample(space);
	}

	public void update(Space space){
		spaceDao.update(space);
	}
	
	public void updateIgnoreNull(Space space){
		spaceDao.updateIgnoreNull(space);
	}
		
	public void updateByExample(Space space){
		spaceDao.update("updateByExampleSpace", space);
	}

	public SpaceVo load(Space space){
		return (SpaceVo)spaceDao.reload(space);
	}
	
	public SpaceVo selectForObject(Space space){
		return (SpaceVo)spaceDao.selectForObject("selectSpace",space);
	}
	
	public List<SpaceVo> selectForList(Space space){
		return (List<SpaceVo>)spaceDao.selectForList("selectSpace",space);
	}
	
	public Page page(Page page, Space space) {
		return spaceDao.page(page, space);
	}

	@Autowired
	public void setISpaceDao(
			@Qualifier("spaceDao") ISpaceDao  spaceDao) {
		this.spaceDao = spaceDao;
	}

}
