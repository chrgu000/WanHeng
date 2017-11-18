package com.cgwas.animationlighttask.entity;

import java.util.List;
import java.util.Map;

import com.cgwas.clazz.entity.Clazz;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.degree.entity.Degree;
import com.cgwas.project.entity.Project;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.publishtype.entity.PublishType;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.userInfo.entity.UserInfo;

/**
 * @author yangjun <br/>
 *         表名： p_animation_light_task <br/>
 *         描述：动画灯光任务表 <br/>
 */
@SuppressWarnings("serial")
public class AnimationLightTaskVo extends AnimationLightTask {
	private List<CompanySoftwareVo> companysoftwares;// 使用软件
	private PublishType publishtype;// 发布类型
	private ProjectSearchVo psearch;
	private UserInfo pointUser;// 任务指派者
	private Degree degree;// 难易度
	private Clazz clazz;// 类目
	protected boolean isDraw;// 是否是自己领取
	protected Long l_begin_time;
	protected Long l_end_time;
	protected Long l_actual_begin_time;
	protected Long l_actual_end_time;
	protected boolean isMorePeople;
	protected List<UserInfo> userInfos;
	protected Project project;
	protected SubProject subProject;
    protected Map<String,Object> map;    
    protected String currentChecker;
    protected List<String> checkers;
    protected List<Map<String,Object>> checkInfos;
    
	public List<Map<String, Object>> getCheckInfos() {
		return checkInfos;
	}
	public void setCheckInfos(List<Map<String, Object>> checkInfos) {
		this.checkInfos = checkInfos;
	}
	public String getCurrentChecker() {
		return currentChecker;
	}
	public void setCurrentChecker(String currentChecker) {
		this.currentChecker = currentChecker;
	}
	public List<String> getCheckers() {
		return checkers;
	}
	public void setCheckers(List<String> checkers) {
		this.checkers = checkers;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public SubProject getSubProject() {
		return subProject;
	}

	public void setSubProject(SubProject subProject) {
		this.subProject = subProject;
	}

	public boolean isMorePeople() {
		return isMorePeople;
	}

	public void setMorePeople(boolean isMorePeople) {
		this.isMorePeople = isMorePeople;
	}

	public Long getL_begin_time() {
		return l_begin_time;
	}

	public void setL_begin_time(Long l_begin_time) {
		this.l_begin_time = l_begin_time;
	}

	public Long getL_end_time() {
		return l_end_time;
	}

	public void setL_end_time(Long l_end_time) {
		this.l_end_time = l_end_time;
	}

	public Long getL_actual_begin_time() {
		return l_actual_begin_time;
	}

	public void setL_actual_begin_time(Long l_actual_begin_time) {
		this.l_actual_begin_time = l_actual_begin_time;
	}

	public Long getL_actual_end_time() {
		return l_actual_end_time;
	}

	public void setL_actual_end_time(Long l_actual_end_time) {
		this.l_actual_end_time = l_actual_end_time;
	}

	public boolean isDraw() {
		return isDraw;
	}

	public void setDraw(boolean isDraw) {
		this.isDraw = isDraw;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public List<CompanySoftwareVo> getCompanysoftwares() {
		return companysoftwares;
	}

	public void setCompanysoftwares(List<CompanySoftwareVo> companysoftwares) {
		this.companysoftwares = companysoftwares;
	}

	private UserInfo maker;// 制作者
	private UserInfo last_checker_user;// 任务的上级审查员

	public UserInfo getLast_checker_user() {
		return last_checker_user;
	}

	public void setLast_checker_user(UserInfo last_checker_user) {
		this.last_checker_user = last_checker_user;
	}

	public AnimationLightTaskVo() {
		super();
	}

	public AnimationLightTaskVo(Long id) {
		super();
		this.id = id;
	}

	public PublishType getPublishtype() {
		return publishtype;
	}

	public void setPublishtype(PublishType publishtype) {
		this.publishtype = publishtype;
	}

	public ProjectSearchVo getPsearch() {
		return psearch;
	}

	public void setPsearch(ProjectSearchVo psearch) {
		this.psearch = psearch;
	}

	public UserInfo getPointUser() {
		return pointUser;
	}

	public void setPointUser(UserInfo pointUser) {
		this.pointUser = pointUser;
	}

	public UserInfo getMaker() {
		return maker;
	}

	public void setMaker(UserInfo maker) {
		this.maker = maker;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}
}
