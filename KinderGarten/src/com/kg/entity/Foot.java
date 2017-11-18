package com.kg.entity;

public class Foot {
	private Integer num;
	private String garden_name;
	private String teacher_name;
	private String baby_name;
	private String siesta;
	private String isShit;// 是否大便
	private String isSiesta;
	private String date;
	
	public Foot(Integer num, String gardenName, String teacherName,
			String babyName, String siesta, String isShit, String isSiesta,
			String date) {
		super();
		this.num = num;
		garden_name = gardenName;
		teacher_name = teacherName;
		baby_name = babyName;
		this.siesta = siesta;
		this.isShit = isShit;
		this.isSiesta = isSiesta;
		this.date = date;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getGarden_name() {
		return garden_name;
	}
	public void setGarden_name(String gardenName) {
		garden_name = gardenName;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacherName) {
		teacher_name = teacherName;
	}
	public String getBaby_name() {
		return baby_name;
	}
	public void setBaby_name(String babyName) {
		baby_name = babyName;
	}
	
	public String getSiesta() {
		return siesta;
	}
	public void setSiesta(String siesta) {
		this.siesta = siesta;
	}
	public String getIsShit() {
		return isShit;
	}
	public void setIsShit(String isShit) {
		this.isShit = isShit;
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
	 
	@Override
	public String toString() {
		return "Foot [baby_name=" + baby_name + ", date=" + date
				+ ", garden_name=" + garden_name + ", isShit=" + isShit
				+ ", isSiesta=" + isSiesta + ", num=" + num + ", siesta="
				+ siesta + ", teacher_name=" + teacher_name + "]";
	}
	
}
