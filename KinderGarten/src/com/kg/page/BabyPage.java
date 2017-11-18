package com.kg.page;

import java.util.List;

public class BabyPage extends Page{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private Integer garden_id;
	private Integer state;
	private Integer teacher_id;
	private String isStudy;
	private List<Integer> gardenIds;
	
	public List<Integer> getGardenIds() {
		return gardenIds;
	}
	public void setGardenIds(List<Integer> gardenIds) {
		this.gardenIds = gardenIds;
	}
	public String getIsStudy() {
		return isStudy;
	}
	public void setIsStudy(String isStudy) {
		this.isStudy = isStudy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(Integer teacherId) {
		teacher_id = teacherId;
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
	@Override
	public String toString() {
		return "BabyPage [garden_id=" + garden_id + ", isStudy=" + isStudy
				+ ", state=" + state + ", teacher_id=" + teacher_id
				+ ", username=" + username + "]";
	}

}
