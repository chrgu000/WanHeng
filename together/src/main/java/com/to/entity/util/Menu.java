package com.to.entity.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Menu {
	private String text;
	List<SubMenu> items = new ArrayList<SubMenu>();
	boolean collapsed=true;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<SubMenu> getItems() {
		return items;
	}
	public void setItems(List<SubMenu> items) {
		this.items = items;
	}
	public boolean isCollapsed() {
		return collapsed;
	}
	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}
	
}
