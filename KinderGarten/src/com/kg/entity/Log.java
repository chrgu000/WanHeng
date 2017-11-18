package com.kg.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 日志实体类
 * 
 * @author 杨俊
 * 
 */
public class Log implements Serializable {
	private Integer id;
	private String content;// 评语内容
	private String reply;// 回复内容
	private String create_time;// 评语时间
	private String living_ability;// 生活能力
	private String learning_ability;// 学习能力
	private String communication_ability;// 交往能力
	private Integer baby_id;// babyId
	private Baby baby;// baby对象
	private Integer teacher_id;
	private Teacher teacher;
	private Integer state;// 审核状态
    private Integer flag;//是否审核过
    
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}

	public String getLiving_ability() {
		return living_ability;
	}

	public void setLiving_ability(String livingAbility) {
		living_ability = livingAbility;
	}

	public String getLearning_ability() {
		return learning_ability;
	}

	public void setLearning_ability(String learningAbility) {
		learning_ability = learningAbility;
	}

	public String getCommunication_ability() {
		return communication_ability;
	}

	public void setCommunication_ability(String communicationAbility) {
		communication_ability = communicationAbility;
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

	 

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Log [baby=" + baby + ", baby_id=" + baby_id
				+ ", communication_ability=" + communication_ability
				+ ", content=" + content + ", create_time=" + create_time
				+ ", id=" + id + ", learning_ability=" + learning_ability
				+ ", living_ability=" + living_ability + ", reply=" + reply
				+ ", state=" + state + ", teacher=" + teacher + ", teacher_id="
				+ teacher_id + "]";
	}

}
