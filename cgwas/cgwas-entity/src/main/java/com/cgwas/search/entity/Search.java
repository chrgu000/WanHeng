package com.cgwas.search.entity;

import java.io.Serializable;

public class Search implements Serializable{
   private Long id;
   private String search;
   private Long num;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getSearch() {
	return search;
}
public void setSearch(String search) {
	this.search = search;
}
public Long getNum() {
	return num;
}
public void setNum(Long num) {
	this.num = num;
}
@Override
public String toString() {
	return "Search [id=" + id + ", search=" + search + ", num=" + num + "]";
}
   
}
