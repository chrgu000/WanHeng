package com.jxc.entity;

public class Search {
	private Integer id;
	private String title;// ����
	private String chr;// ����
	private String url;// ����
	private String num;// ���

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChr() {
		return chr;
	}

	public void setChr(String chr) {
		this.chr = chr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Search [id=" + id + ", title=" + title + ", chr=" + chr
				+ ", url=" + url + ", num=" + num + "]";
	}

}
