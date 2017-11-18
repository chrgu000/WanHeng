package com.jxc.page;

import java.io.Serializable;

public class Page implements Serializable {
	private Integer begin;// ��ʼ����
	private Integer pageSize = 10;// ҳ���С
	private Integer currentPage = 1;// ��ǰҳ
	private Integer totalPage;// ��ҳ
	private Integer rows;// ��������
	private Integer titleAddr_id;// ����id
	private Integer brand_id;//Ʒ��id
	

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public Integer getTitleAddr_id() {
		return titleAddr_id;
	}

	public void setTitleAddr_id(Integer titleAddr_id) {
		this.titleAddr_id = titleAddr_id;
	}

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
		}else{
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
		return "Page [begin=" + begin + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", rows=" + rows + " titleAddr_id=" + titleAddr_id + "]";
	}
public static void main(String[] args) {
	
}
}
