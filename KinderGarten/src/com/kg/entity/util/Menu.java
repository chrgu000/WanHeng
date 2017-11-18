package com.kg.entity.util;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private String text;
	List<SubMenu> items = new ArrayList<SubMenu>();
	boolean collapsed=true;
	
	public boolean isCollapsed() {
		return collapsed;
	}
	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}
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
	@Override
	public String toString() {
		return "Menu [items=" + items + ", text=" + text + "]";
	}
	 
}
