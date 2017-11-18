package com.kg.page;

public class DiaryPage extends Page{
private Integer diary_type_id;
private Integer teacher_id;

public Integer getTeacher_id() {
	return teacher_id;
}

public void setTeacher_id(Integer teacherId) {
	teacher_id = teacherId;
}

public Integer getDiary_type_id() {
	return diary_type_id;
}

public void setDiary_type_id(Integer diaryTypeId) {
	diary_type_id = diaryTypeId;
}

@Override
public String toString() {
	return "DiaryPage [diary_type_id=" + diary_type_id + "]";
}
}
