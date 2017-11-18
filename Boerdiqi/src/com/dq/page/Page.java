package com.dq.page;

import java.io.Serializable;

public class Page implements Serializable {
	private Integer begin;
	protected Integer pageSize = 10;
	private Integer currentPage = 1;
	private Integer totalPage;
	protected Integer rows;

	public Integer getBegin() {
		return (currentPage - 1) * pageSize;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		if (rows % pageSize == 0) {
			if (rows / pageSize == 0) {
				return 1;
			} else {
				return rows / pageSize;
			}
		} else {
			return rows / pageSize + 1;
		}
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Page [begin=" + begin + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", rows=" + rows + ", totalPage="
				+ totalPage + "]";
	}

 
}