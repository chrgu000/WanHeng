package com.jxc.dao;

import java.util.List;

import com.jxc.entity.News;
import com.jxc.entity.Price;
import com.jxc.entity.Title;

public interface TitleDao {
	List<Title> findAllTitle();
	
	List<Title> findTitlesBySightSpotId(Integer sight_spot_id);
	
	List<Price> findAllPrice();
	
	boolean addNews(News news);
}
