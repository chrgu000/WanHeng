package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Title;

public interface TitleDao {
	List<Title> findAllTitles();

	Title findTitleById(Integer id);
}
