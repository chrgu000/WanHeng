package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Diary;
import com.kg.entity.DiaryType;
import com.kg.page.DiaryPage;

public interface DiaryDao {
	List<Diary> getDiaryByPage(DiaryPage page);

	Integer getRows(DiaryPage page);

	void deleteByIds(Map<String, String[]> map);

	void addDiary(Diary diary);

	List<DiaryType> findAllDiaryType();
	
	List<Diary> findDiaryByMap(Map<String,Object> map);

	List<Diary> getDiaryByIds(Map<String, String[]> map);

}
