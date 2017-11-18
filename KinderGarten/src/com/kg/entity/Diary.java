package com.kg.entity;

import java.io.Serializable;

public class Diary implements Serializable {
private Integer id;
private Integer diary_type_id;
private DiaryType diaryType;
private String path;
private String min_path;
private Integer teacher_id;
private String create_time;
private String content;

public String getMin_path() {
	return min_path;
}
public void setMin_path(String minPath) {
	min_path = minPath;
}
public DiaryType getDiaryType() {
	return diaryType;
}
public void setDiaryType(DiaryType diaryType) {
	this.diaryType = diaryType;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getDiary_type_id() {
	return diary_type_id;
}
public void setDiary_type_id(Integer diaryTypeId) {
	diary_type_id = diaryTypeId;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public Integer getTeacher_id() {
	return teacher_id;
}
public void setTeacher_id(Integer teacherId) {
	teacher_id = teacherId;
}
public String getCreate_time() {
	return create_time;
}
public void setCreate_time(String createTime) {
	create_time = createTime;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "Diary [content=" + content + ", create_time=" + create_time
			+ ", diaryType=" + diaryType + ", diary_type_id=" + diary_type_id
			+ ", id=" + id + ", min_path=" + min_path + ", path=" + path
			+ ", teacher_id=" + teacher_id + "]";
}

}
