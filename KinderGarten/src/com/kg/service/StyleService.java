package com.kg.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kg.entity.Style;
import com.kg.entity.StylePic;
import com.kg.page.Page;

public interface StyleService {
	List<Style> getStyleByPage(Page page);

	Integer getRows(Page page);

	 List<Style> getStyleByTeacherId(Integer teacher_id);
	
   	Style getStyleById(Integer id);

   	void updateStyle(Style style, HttpServletResponse response)throws Exception;
   	
   	void deleteByIds(String ids,HttpServletResponse response)throws Exception;

	void addStyle(Style style, HttpServletResponse response)throws Exception;
	
	void addStylePic(StylePic stylePic,HttpServletResponse response)throws Exception;
}
