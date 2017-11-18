package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Style;
import com.kg.entity.StylePic;
import com.kg.page.Page;

public interface StyleDao {
	List<Style> getStyleByPage(Page page);

	Integer getRows(Page page);

	 List<Style> getStyleByTeacherId(Integer teacher_id);
	
   	Style getStyleById(Integer id);

	void updateStyle(Style style);

	void deleteByIds(Map<String, String[]> map);

	void addStyle(Style style);
	
	void addStylePic(StylePic stylePic);

}
