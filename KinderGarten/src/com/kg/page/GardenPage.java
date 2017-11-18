package com.kg.page;

import java.util.List;

public class GardenPage extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private Integer id;
    private List<Integer> gardenIds;
    
	public List<Integer> getGardenIds() {
		return gardenIds;
	}

	public void setGardenIds(List<Integer> gardenIds) {
		this.gardenIds = gardenIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "GardenPage [id=" + id + ", name=" + name + "]";
	}

}
