package com.kg.page;

public class AdminPage extends Page {
	private String username;
	 private Integer garden_id;

	public String getUsername() {
		return username;
	}

	public Integer getGarden_id() {
		return garden_id;
	}

	public void setGarden_id(Integer gardenId) {
		garden_id = gardenId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AdminPage [garden_id=" + garden_id + ", username=" + username
				+ "]";
	}
 

}
