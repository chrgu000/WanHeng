/**
 * 
 */
package com.kg.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kg.entity.Diary;
import com.kg.entity.DiaryType;
import com.kg.page.DiaryPage;

/**
 * @author 杨俊
 *
 */
public interface DiaryService {
	List<Diary> getDiaryByPage(DiaryPage page);

	Integer getRows(DiaryPage page);

	void deleteByIds(String ids,HttpServletResponse response,HttpSession session) throws Exception;

	void addDiary(Diary diary,HttpServletResponse response) throws Exception;

	List<DiaryType> findAllDiaryType();
	
	List<Diary> findDiaryByMap(Map<String,Object> map);
}
