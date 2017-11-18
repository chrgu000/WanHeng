package com.cgwas.screenwriter.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.creenwriter.entity.Screenwriter;
import com.cgwas.screenwriter.dao.api.IScreenwriterDao;
import com.cgwas.screenwriter.entity.ScreenwriterVo;
import com.cgwas.screenwriter.service.api.IScreenwriterService;
/**
*  Author yangjun
*/
@Service
public class ScreenwriterService implements IScreenwriterService {
	private IScreenwriterDao screenwriterDao;

	public Serializable save(Screenwriter screenwriter){
		return screenwriterDao.save(screenwriter);
	}

	public void delete(Screenwriter screenwriter){
		screenwriterDao.delete(screenwriter);
	}
	
	public void deleteByExample(Screenwriter screenwriter){
		screenwriterDao.deleteByExample(screenwriter);
	}

	public void update(Screenwriter screenwriter){
		screenwriterDao.update(screenwriter);
	}
	
	public void updateIgnoreNull(Screenwriter screenwriter){
		screenwriterDao.updateIgnoreNull(screenwriter);
	}
		
	public void updateByExample(Screenwriter screenwriter){
		screenwriterDao.update("updateByExampleScreenwriter", screenwriter);
	}

	public ScreenwriterVo load(Screenwriter screenwriter){
		return (ScreenwriterVo)screenwriterDao.reload(screenwriter);
	}
	
	public ScreenwriterVo selectForObject(Screenwriter screenwriter){
		return (ScreenwriterVo)screenwriterDao.selectForObject("selectScreenwriter",screenwriter);
	}
	
	public List<ScreenwriterVo> selectForList(Screenwriter screenwriter){
		return (List<ScreenwriterVo>)screenwriterDao.selectForList("selectScreenwriter",screenwriter);
	}
	
	public Page page(Page page, Screenwriter screenwriter) {
		return screenwriterDao.page(page, screenwriter);
	}

	@Autowired
	public void setIScreenwriterDao(
			@Qualifier("screenwriterDao") IScreenwriterDao  screenwriterDao) {
		this.screenwriterDao = screenwriterDao;
	}

}
