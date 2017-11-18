package com.to.page;

import lombok.Data;

@Data
public class AdminPage extends Page {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
