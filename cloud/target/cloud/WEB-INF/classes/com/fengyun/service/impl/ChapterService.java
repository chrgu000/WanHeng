package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.IChapterDao;
import com.fengyun.entity.Chapter;
import com.fengyun.entity.ChapterVo;
import com.fengyun.entity.Message;
import com.fengyun.service.IChapterService;
/**
*  Author yangjun
*/
@Service
public class ChapterService implements IChapterService {
	private IChapterDao chapterDao;

	public Serializable save(Chapter chapter){
		return chapterDao.save(chapter);
	}

	public void delete(Chapter chapter){
		chapterDao.delete(chapter);
	}
	
	public void deleteByExample(Chapter chapter){
		chapterDao.deleteByExample(chapter);
	}

	public void update(Chapter chapter){
		chapterDao.update(chapter);
	}
	
	public void updateIgnoreNull(Chapter chapter){
		chapterDao.updateIgnoreNull(chapter);
	}
		
	public void updateByExample(Chapter chapter){
		chapterDao.update("updateByExampleChapter", chapter);
	}

	public ChapterVo load(Chapter chapter){
		return (ChapterVo)chapterDao.reload(chapter);
	}
	
	public ChapterVo selectForObject(Chapter chapter){
		return (ChapterVo)chapterDao.selectForObject("selectChapter",chapter);
	}
	
	public List<ChapterVo> selectForList(Chapter chapter){
		return (List<ChapterVo>)chapterDao.selectForList("selectChapter",chapter);
	}
	
	public Page page(Page page, Chapter chapter) {
		return chapterDao.page(page, chapter);
	}

	@Autowired
	public void setIChapterDao(
			@Qualifier("chapterDao") IChapterDao  chapterDao) {
		this.chapterDao = chapterDao;
	}

	@Override
	public Chapter getChapterById(Long id) {
		return (Chapter) chapterDao.selectForObject("getChapterById", id);
	}

	@Override
	public void updateVideoStatus(Chapter chapter) {
		chapterDao.update("updateVideoStatus", chapter);
	}

	@Override
	public Integer getMaxOrderByCourseId(Long course_id) {
		return (Integer) chapterDao.selectForObject("getMaxOrderByCourseId", course_id);
	}

	@Override
	public Chapter getNearChapter(Map<String, Object> map) {
		return (Chapter) chapterDao.selectForObject("getNearChapter", map);
	}

	@Override
	public Page page1(Page page, Chapter chapter) {
		return chapterDao.page1(page, chapter);
	}

	@Override
	public Integer getYesCheckChapterByCourseId(Long course_id) {
		return (Integer) chapterDao.selectForObject("getYesCheckChapterByCourseId", course_id);
	}

	@Override
	public void sendMessage(Message msg) {
		 chapterDao.save("sendMessage",msg);
		
	}

	@Override
	public Long getHasChapterNumsOfCourse(Long course_id) {
		return  (Long) chapterDao.selectForObject("getHasChapterNumsOfCourse", course_id);
	}

	@Override
	public void sendUMessage(Message message) {
		 chapterDao.save("sendUMessage",message);
	}

}
