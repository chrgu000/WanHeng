package com.cgwas.degree.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.AbstractDao;
import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.IPageQuery;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageQueryFactory;
import com.cgwas.common.dataaccess.impl.PageQuery;
import com.cgwas.degree.dao.api.IDegreeDao;
import com.cgwas.degree.entity.Degree;

@Service
public class DegreeDao extends AbstractDao implements IDegreeDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	public Page page(Page page, Degree degree) {

		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,
				"selectDegreePage", "selectDegreeCount");
		return pageQuery.select(degree);
	}

	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}

}