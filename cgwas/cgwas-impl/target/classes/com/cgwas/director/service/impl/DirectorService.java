package com.cgwas.director.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.director.dao.api.IDirectorDao;
import com.cgwas.director.entity.Director;
import com.cgwas.director.entity.DirectorVo;
import com.cgwas.director.service.api.IDirectorService;
/**
*  Author yangjun
*/
@Service
public class DirectorService implements IDirectorService {
	private IDirectorDao directorDao;

	public Serializable save(Director director){
		return directorDao.save(director);
	}

	public void delete(Director director){
		directorDao.delete(director);
	}
	
	public void deleteByExample(Director director){
		directorDao.deleteByExample(director);
	}

	public void update(Director director){
		directorDao.update(director);
	}
	
	public void updateIgnoreNull(Director director){
		directorDao.updateIgnoreNull(director);
	}
		
	public void updateByExample(Director director){
		directorDao.update("updateByExampleDirector", director);
	}

	public DirectorVo load(Director director){
		return (DirectorVo)directorDao.reload(director);
	}
	
	public DirectorVo selectForObject(Director director){
		return (DirectorVo)directorDao.selectForObject("selectDirector",director);
	}
	
	public List<DirectorVo> selectForList(Director director){
		return (List<DirectorVo>)directorDao.selectForList("selectDirector",director);
	}
	
	public Page page(Page page, Director director) {
		return directorDao.page(page, director);
	}

	@Autowired
	public void setIDirectorDao(
			@Qualifier("directorDao") IDirectorDao  directorDao) {
		this.directorDao = directorDao;
	}

}
