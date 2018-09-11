package com.cgwas.common.dataaccess.impl;

import java.util.List;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.spring.CurrentSpringContext;

/**
 * 自定义约束分页查询
 * 
 * @author <a href="mailto:liuxin@zjhcsoft.com">liuxin</a>
 * @version 1.0 CriteriaPageQuery.java 2012-7-30
 */
public class FindEqualPageQuery {

	private static IDaoSupport daoSupport;

	private final Page page;
	private final FindEqualBuilder findEqualBuilder;

	public <T> FindEqualPageQuery(Page page, FindEqualBuilder findEqualBuilder) {
		this.page = page;
		this.findEqualBuilder = findEqualBuilder;
		if (daoSupport == null) {
			daoSupport = CurrentSpringContext.getBean("roofDaoSupport", IDaoSupport.class);
		}
	}

	public Page find() {
		Long rowCount = ((RoofDaoSupport) daoSupport).findEqualCount(findEqualBuilder);
		page.setTotal(rowCount);
		List<?> rows = daoSupport.findEqual(findEqualBuilder, page.getStart().intValue(), page.getLimit().intValue());
		page.setDataList(rows);
		return page;
	}

}
