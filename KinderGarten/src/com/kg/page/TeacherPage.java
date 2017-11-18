package com.kg.page;

import java.util.List;

public class TeacherPage extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private Integer garden_id;
	private Integer state;
    private List<Integer> gardenIds;
    
	public List<Integer> getGardenIds() {
		return gardenIds;
	}

	public void setGardenIds(List<Integer> gardenIds) {
		this.gardenIds = gardenIds;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getGarden_id() {
		return garden_id;
	}

	public void setGarden_id(Integer gardenId) {
		garden_id = gardenId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	 

}
