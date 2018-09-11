package com.fengyun.entity;

import java.util.HashMap;
import java.util.Map;

public class ParamInfo {
	private final String host = "http://192.168.16.202";
	private String path;
	private String method = "POST";
	private Map<String,Object> query;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = "/cgwas/cloud/"+path;
	}
	
	public Map<String, Object> getQuery() {
		return query;
	}
	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}
}
