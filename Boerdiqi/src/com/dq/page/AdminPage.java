package com.dq.page;

public class AdminPage extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
 

	@Override
	public String toString() {
		return "AdminPage [username=" + username + "]";
	}

}
