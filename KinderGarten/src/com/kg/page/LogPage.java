package com.kg.page;

public class LogPage extends Page {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer baby_id;
private Integer teacher_id;
private Integer state;

public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
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
@Override
public String toString() {
	return "LogPage [baby_id=" + baby_id + ", state=" + state + ", teacher_id="
			+ teacher_id + "]";
}

}
