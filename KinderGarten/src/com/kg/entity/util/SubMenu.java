package com.kg.entity.util;

/**
 * 二级菜单
 * 
 * @author Administrator
 *
 */
public class SubMenu {
	private String id;
	private String text;
	private String href;
	private boolean closeable = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isCloseable() {
		return closeable;
	}

	public void setCloseable(boolean closeable) {
		this.closeable = closeable;
	}

	@Override
	public String toString() {
		return "SubMenu [closeable=" + closeable + ", href=" + href + ", id="
				+ id + ", text=" + text + "]";
	}
	
}
