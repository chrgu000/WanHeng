package com.kg.entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * 足迹实体类
 * 
 * @author 杨俊
 * 
 */
public class Footprint implements Serializable {
	private Integer num;
	private String garden_name;
	private String teacher_name;
	private String baby_name;
	private String start_time;// 入园时间
	private String end_time;// 离园时间
	private String isShit;// 是否大便
	private String isSiesta;
	private String siesta;
	private String date;
	private Integer id;
	private Integer baby_id;// babyId
	private Baby baby;// baby
	private Integer teacher_id;
	private Teacher teacher;
	private Integer garden_id;
	private Garden garden;
	private String state;

	public Footprint() {

	}

	public String getSiesta() {
		return siesta;
	}

	public void setSiesta(String siesta) {
		this.siesta = siesta;
	}

	public String getBaby_name() {
		return baby_name;
	}

	public void setBaby_name(String babyName) {
		baby_name = babyName;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacherName) {
		teacher_name = teacherName;
	}

	public String getGarden_name() {
		return garden_name;
	}

	public void setGarden_name(String gardenName) {
		garden_name = gardenName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Footprint(Integer num, String gardenName, String teacherName,
			String babyName, String startTime, String endTime, String isShit,
			String isSiesta, String date) {
		super();
		baby_name = babyName;
		start_time = startTime;
		end_time = endTime;
		this.isShit = isShit;
		teacher_name = teacherName;
		garden_name = gardenName;
		this.isSiesta = isSiesta;
		this.date = date;
		this.num = num;
	}

	public Integer getGarden_id() {
		return garden_id;
	}

	public void setGarden_id(Integer gardenId) {
		garden_id = gardenId;
	}

	public Garden getGarden() {
		return garden;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBaby_id() {
		return baby_id;
	}

	public void setBaby_id(Integer babyId) {
		baby_id = babyId;
	}

	public Baby getBaby() {
		return baby;
	}

	public void setBaby(Baby baby) {
		this.baby = baby;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String startTime) {
		start_time = startTime;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String endTime) {
		end_time = endTime;
	}

	public Integer getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Integer teacherId) {
		teacher_id = teacherId;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getIsSiesta() {
		return isSiesta;
	}

	public void setIsSiesta(String isSiesta) {
		this.isSiesta = isSiesta;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIsShit() {
		return isShit;
	}

	public void setIsShit(String isShit) {
		this.isShit = isShit;
	}

	@Override
	public String toString() {
		return "Footprint [baby=" + baby + ", baby_id=" + baby_id + ", date="
				+ date + ", end_time=" + end_time + ", garden=" + garden
				+ ", garden_id=" + garden_id + ", id=" + id + ", isShit="
				+ isShit + ", isSiesta=" + isSiesta + ", start_time="
				+ start_time + ", state=" + state + ", teacher=" + teacher
				+ ", teacher_id=" + teacher_id + "]";
	}

}
