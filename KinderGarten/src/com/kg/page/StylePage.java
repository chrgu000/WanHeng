package com.kg.page;

import java.util.List;

public class StylePage extends Page{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private List<Integer> gardenIds;
private Integer teacher_id;
private Integer garden_id;

public Integer getGarden_id() {
	return garden_id;
}
public void setGarden_id(Integer gardenId) {
	garden_id = gardenId;
}
public List<Integer> getGardenIds() {
	return gardenIds;
}
public void setGardenIds(List<Integer> gardenIds) {
	this.gardenIds = gardenIds;
}
public Integer getTeacher_id() {
	return teacher_id;
}
public void setTeacher_id(Integer teacherId) {
	teacher_id = teacherId;
}
 

}
