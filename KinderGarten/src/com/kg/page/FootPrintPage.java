package com.kg.page;

public class FootPrintPage extends Page {
private Integer teacher_id;
private Integer baby_id;
private String username;
private Integer garden_id;

@Override
public String toString() {
	return "FootPrintPage [baby_id=" + baby_id + ", garden_id=" + garden_id
			+ ", teacher_id=" + teacher_id + ", username=" + username + "]";
}

public Integer getGarden_id() {
	return garden_id;
}

public void setGarden_id(Integer gardenId) {
	garden_id = gardenId;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public Integer getBaby_id() {
	return baby_id;
}

public void setBaby_id(Integer babyId) {
	baby_id = babyId;
}

public Integer getTeacher_id() {
	return teacher_id;
}

public void setTeacher_id(Integer teacherId) {
	teacher_id = teacherId;
}

}
