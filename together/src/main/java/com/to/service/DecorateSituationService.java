package com.to.service;


import com.to.entity.DecorateSituation;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface DecorateSituationService {
	List<DecorateSituation> getDecorateSituationByPage(Page page);
	 
	List<DecorateSituation> getAllDecorateSituation();
	
	 Integer getRows(Page page);
	 
	 DecorateSituation getDecorateSituationById(Integer id);
	 
	void updateDecorateSituation(DecorateSituation decorateSituation, HttpServletResponse response)throws Exception;
	
	void addDecorateSituation(DecorateSituation decorateSituation, HttpServletResponse response)throws Exception;
	
	void deleteDecorateSituation(String ids, HttpServletResponse response)throws Exception;
}
