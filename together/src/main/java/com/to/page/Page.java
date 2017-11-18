package com.to.page;

import lombok.Data;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
@Data
public class Page {
    private Integer begin;
    protected Integer pageSize = 20;
    private Integer currentPage = 1;
    private Integer totalPage;
    protected Integer rows;
    
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
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getBegin() {
        return (currentPage - 1) * pageSize;
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
}
