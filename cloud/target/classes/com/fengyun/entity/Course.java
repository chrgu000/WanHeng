package com.fengyun.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

/**
 * @author yangjun <br/>
 *         表名： y_course <br/>
 *         描述：y_course <br/>
 */
@SuppressWarnings("serial")
public class Course implements Serializable {
	protected Long id;// 主键
	protected String course_name;// 课程名称
	protected String type;// 课程类型1为录播课程,2为文本课程
	protected Long interest_direction_id;// 兴趣方向id
	protected String course_cover;// 课程封面
	protected String course_overview;// 课程概述
	protected String is_free;// 收费免费(Y为免费,N为付费)
	protected Double price;// 课程价格
	protected Long user_id;// 教师所在用户id
	protected String teacher_delflag;// 教师删除标记(Y为已删除N为未删除)
	protected String learner_delflag;// 学员删除标记(Y为已删除N为未删除)
	protected String is_apply;// 是否为邀约课程(Y为是,N为否)
	protected Integer star_nums;// 评论星级总数
	protected Integer join_nums;// 课程被加入人数
	protected String is_public;//是否公开
    protected List<Chapter> chapters;
    protected List<Software> softwares;
    protected List<TradeSkill> tradeSkills;
    protected Teacher teacher;
    protected InterestDirection interestDirection;
    protected Map<String,Object> search;
    protected String is_reply;
    protected Date create_date;
    protected Boolean updateCourse;
    protected String check_delflag;
    protected Integer type_id;
    protected Type type_obj;
    protected Integer program_id;
    protected Program program;
    protected Date begin_date;
    protected Date end_date;
    protected Time onlive_time;
    protected Integer try_learn_time;
    protected String is_save_video;
    protected String privilege;
    protected String live;
    
	public String getLive() {
		return live;
	}

	public void setLive(String live) {
		this.live = live;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public Date getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	 
	 

	public Time getOnlive_time() {
		return onlive_time;
	}

	public void setOnlive_time(Time onlive_time) {
		this.onlive_time = onlive_time;
	}

	public Integer getTry_learn_time() {
		return try_learn_time;
	}

	public void setTry_learn_time(Integer try_learn_time) {
		this.try_learn_time = try_learn_time;
	}

	public String getIs_save_video() {
		return is_save_video;
	}

	public void setIs_save_video(String is_save_video) {
		this.is_save_video = is_save_video;
	}

	public Type getType_obj() {
		return type_obj;
	}

	public void setType_obj(Type type_obj) {
		this.type_obj = type_obj;
	}

	public Integer getProgram_id() {
		return program_id;
	}

	public void setProgram_id(Integer program_id) {
		this.program_id = program_id;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public String getCheck_delflag() {
		return check_delflag;
	}

	public void setCheck_delflag(String check_delflag) {
		this.check_delflag = check_delflag;
	}

	public Boolean getUpdateCourse() {
		return updateCourse;
	}

	public void setUpdateCourse(Boolean updateCourse) {
		this.updateCourse = updateCourse;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getIs_reply() {
		return is_reply;
	}

	public void setIs_reply(String is_reply) {
		this.is_reply = is_reply;
	}

	public String getIs_public() {
		return is_public;
	}

	public void setIs_public(String is_public) {
		this.is_public = is_public;
	}

	public Integer getJoin_nums() {
		return join_nums;
	}

	public void setJoin_nums(Integer join_nums) {
		this.join_nums = join_nums;
	}

	public Map<String, Object> getSearch() {
		return search;
	}

	public void setSearch(Map<String, Object> search) {
		this.search = search;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	 
	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}

	public List<TradeSkill> getTradeSkills() {
		return tradeSkills;
	}

	public void setTradeSkills(List<TradeSkill> tradeSkills) {
		this.tradeSkills = tradeSkills;
	}

	public InterestDirection getInterestDirection() {
		return interestDirection;
	}

	public void setInterestDirection(InterestDirection interestDirection) {
		this.interestDirection = interestDirection;
	}

	public Course() {
		super();
	}

	public Course(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Long getInterest_direction_id() {
		return interest_direction_id;
	}
	public void setInterest_direction_id(Long interest_direction_id) {
		this.interest_direction_id = interest_direction_id;
	}
	
	public String getCourse_cover() {
		return course_cover;
	}
	public void setCourse_cover(String course_cover) {
		this.course_cover = course_cover;
	}
	
	public String getCourse_overview() {
		return course_overview;
	}
	public void setCourse_overview(String course_overview) {
		this.course_overview = course_overview;
	}
	
	public String getIs_free() {
		return is_free;
	}
	public void setIs_free(String is_free) {
		this.is_free = is_free;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public String getTeacher_delflag() {
		return teacher_delflag;
	}
	public void setTeacher_delflag(String teacher_delflag) {
		this.teacher_delflag = teacher_delflag;
	}
	
	public String getLearner_delflag() {
		return learner_delflag;
	}
	public void setLearner_delflag(String learner_delflag) {
		this.learner_delflag = learner_delflag;
	}
	
	public String getIs_apply() {
		return is_apply;
	}
	public void setIs_apply(String is_apply) {
		this.is_apply = is_apply;
	}
	
	public Integer getStar_nums() {
		return star_nums;
	}
	public void setStar_nums(Integer star_nums) {
		this.star_nums = star_nums;
	}
}
