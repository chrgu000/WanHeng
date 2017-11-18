package com.cgwas.animationlighttask.entity;

import javax.persistence.Id;

import java.util.Date;
import java.util.Map;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_animation_light_task <br/>
 *         描述：动画灯光任务表 <br/>
 */
@SuppressWarnings("serial")
public class AnimationLightTask implements Serializable {
	protected Long id;// 主键
	protected Long class_id;// 类目id
	protected Long project_id;// 所属项目id
	protected String is_parent_poject;// is_parent_poject
	protected String pattern_number;// 编号
	protected String cart;// 分镜
	protected String time;//帧数
	protected Double second;//秒
	protected Long publish_type_id;// 发布类型
	protected String making_requirement;// 制作要求
	protected String reference_material;// 参考素材
	protected String plan_time;// 计划工时
	protected String actual_working_hours;// 实际工时
	protected String price;// 单价
	protected double timeliness;//时效期
	protected Date begin_time;// 开始日期
	protected Date end_time;// 结束日期
	protected Integer wight;// 优先级
	protected String remark;// 备注
	protected String commit_path;// 提交路径
	protected String extraction_path;// 打包路径
	protected Long degree_id;// 难易度
	protected String stage;// 任务所在阶段
	protected String task_status;// 任务状态
	protected Long user_id;// 接收着id
	protected Long creater_id;// 创建者id
	protected Date create_time;// 创建时间
	protected Date modified_time;// 最新修改时间
	protected Integer check_status;// 审核状态
	protected Long company_id;// 公司id
    protected Long point_status;
    protected String frameRateContent;
    protected Date actual_begin_time;
    protected String delay_cause;
    protected String abandon;
    protected String observer;
    protected String company_stage;
    protected String company_status;
    protected String rate;
    protected String cause;
    protected  String checkUserTimes;
    protected Date publishTime;
    protected Long getTaskNums;
    
    public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Long getGetTaskNums() {
		return getTaskNums;
	}

	public void setGetTaskNums(Long getTaskNums) {
		this.getTaskNums = getTaskNums;
	}

	public String getCheckUserTimes() {
		return checkUserTimes;
	}

	public void setCheckUserTimes(String checkUserTimes) {
		this.checkUserTimes = checkUserTimes;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getCompany_stage() {
		return company_stage;
	}

	public void setCompany_stage(String company_stage) {
		this.company_stage = company_stage;
	}

	public String getCompany_status() {
		return company_status;
	}

	public void setCompany_status(String company_status) {
		this.company_status = company_status;
	}

	/**
     * 不在此类
     */
    /**
     * 公司文件id
     */
    
    protected Long companyFiles_id;

	public String getObserver() {
		return observer;
	}

	public void setObserver(String observer) {
		this.observer = observer;
	}

	public String getDelay_cause() {
		return delay_cause;
	}

	public String getAbandon() {
		return abandon;
	}

	public void setAbandon(String abandon) {
		this.abandon = abandon;
	}

	public void setDelay_cause(String delay_cause) {
		this.delay_cause = delay_cause;
	}

	public Date getActual_begin_time() {
		return actual_begin_time;
	}

	public void setActual_begin_time(Date actual_begin_time) {
		this.actual_begin_time = actual_begin_time;
	}

	 

	public String getFrameRateContent() {
		return frameRateContent;
	}

	public void setFrameRateContent(String frameRateContent) {
		this.frameRateContent = frameRateContent;
	}

	public Double getSecond() {
		return second;
	}

	public void setSecond(Double second) {
		this.second = second;
	}

	public Long getPoint_status() {
		return point_status;
	}

	public void setPoint_status(Long point_status) {
		this.point_status = point_status;
	}

	public double getTimeliness() {
		return timeliness;
	}

	public void setTimeliness(double timeliness) {
		this.timeliness = timeliness;
	}

	public AnimationLightTask() {
		super();
	}

	public AnimationLightTask(Long id) {
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
	
	public Long getClass_id() {
		return class_id;
	}
	public void setClass_id(Long class_id) {
		this.class_id = class_id;
	}
	
	public Long getProject_id() {
		return project_id;
	}
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}
	
	public String getIs_parent_poject() {
		return is_parent_poject;
	}
	public void setIs_parent_poject(String is_parent_poject) {
		this.is_parent_poject = is_parent_poject;
	}
	
	public String getPattern_number() {
		return pattern_number;
	}

	public void setPattern_number(String pattern_number) {
		this.pattern_number = pattern_number;
	}

	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	 
	public String getMaking_requirement() {
		return making_requirement;
	}
	public void setMaking_requirement(String making_requirement) {
		this.making_requirement = making_requirement;
	}
	
	public String getReference_material() {
		return reference_material;
	}
	public void setReference_material(String reference_material) {
		this.reference_material = reference_material;
	}
	
	public String getPlan_time() {
		return plan_time;
	}
	public void setPlan_time(String plan_time) {
		this.plan_time = plan_time;
	}
	
	public String getActual_working_hours() {
		return actual_working_hours;
	}
	public void setActual_working_hours(String actual_working_hours) {
		this.actual_working_hours = actual_working_hours;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	
	
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer getWight() {
		return wight;
	}
	public void setWight(Integer wight) {
		this.wight = wight;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getCommit_path() {
		return commit_path;
	}
	public void setCommit_path(String commit_path) {
		this.commit_path = commit_path;
	}
	
	public String getExtraction_path() {
		return extraction_path;
	}
	public void setExtraction_path(String extraction_path) {
		this.extraction_path = extraction_path;
	}
	
	
	public Long getPublish_type_id() {
		return publish_type_id;
	}

	public void setPublish_type_id(Long publish_type_id) {
		this.publish_type_id = publish_type_id;
	}

	public Long getDegree_id() {
		return degree_id;
	}

	public void setDegree_id(Long degree_id) {
		this.degree_id = degree_id;
	}

	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	public String getTask_status() {
		return task_status;
	}
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getCreater_id() {
		return creater_id;
	}
	public void setCreater_id(Long creater_id) {
		this.creater_id = creater_id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getModified_time() {
		return modified_time;
	}
	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
	}
	
	public Integer getCheck_status() {
		return check_status;
	}
	public void setCheck_status(Integer check_status) {
		this.check_status = check_status;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public Long getCompanyFiles_id() {
		return companyFiles_id;
	}

	public void setCompanyFiles_id(Long companyFiles_id) {
		this.companyFiles_id = companyFiles_id;
	}
}
