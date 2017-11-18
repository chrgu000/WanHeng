package com.cgwas.subproject.entity;

import java.util.Date;
import java.util.List;

import com.cgwas.company.entity.Company;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.director.entity.DirectorVo;
import com.cgwas.frameRateCompany.entity.FrameRateCompanyVo;
import com.cgwas.principal.entity.PrincipalVo;
import com.cgwas.project.entity.Graphics;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.projectStatus.entity.ProjectStatusVo;
import com.cgwas.projectType.entity.ProjectTypeVo;
import com.cgwas.resolutionCompany.entity.ResolutionCompanyVo;
import com.cgwas.role.entity.Role;
import com.cgwas.screenwriter.entity.ScreenwriterVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author yangjun <br/>
 *         表名： p_sub_project <br/>
 *         描述：子项目 <br/>
 */
@SuppressWarnings("serial")
// @JsonInclude(value = Include.NON_NULL)
public class SubProjectVo extends SubProject {
	private ProjectTypeVo projectType;// 类型
	private FrameRateCompanyVo frameRateCompany;// 帧速率
	private String resolution;// 分辨率
	private List<CompanySoftwareVo> companySoftwares;// 软件
	private ProjectSearchVo psearch;
	private Company company;
	private ProjectStatusVo projectStatus;// 状态
	private List<DirectorVo> directors;// 导演
	private List<PrincipalVo> principals;// 负责人
	private List<ScreenwriterVo> screenwriters;// 编剧
	// =========================================
	private Integer total_undistributed;// 项目任务未分配的数量
	private Integer total_running;// 项目任务进行中的数量
	private Integer total_finished;// 项目任务已完成的数量
	private Integer model_task_total;// 项目模型任务的总数量
	private Integer model_task_finished_total;// 项目模型任务已完成的数量
	private Integer animation_task_total;// 项目动画任务的总数量
	private Integer animation_task_finished_total;// 项目动画任务已完成的数量
	private Integer light_task_total;// 项目灯光任务的总数量
	private Integer light_task_finished_total;// 项目灯光任务已完成的总数量
	private String finished_rate;// 已完成的百分比
	private Graphics graphics;// 項目图表信息
	private List<Role> roles;
	private boolean hasTask;//项目中是否直接拥有任务
	// ======================================
	private Integer subProjectNums;// 子项目数
	private Long useDate;// 可用工作日
	private Integer taskTotalNums;// 总任务数
	private Integer projectManagerNums;// 项目管理者人数
	private Integer projectMakerNums;// 项目制作者人数
	private double projectPrepayment;// 项目预支付金额
	private double projectPaid;// 项目已支付
	private List<SubProjectVo> subProjects;
	protected Date actual_begin_time;
	protected Date actual_end_time;
	protected Long l_begin_time;
	protected Long l_end_time;
	protected Long l_actual_begin_time;
	protected Long l_actual_end_time;
	protected Long n;
	private Long companyFiles_id;
	private Long parentFileId;//父文件夹id
	private String folder_path;//项目文件夹绝对路径
	public boolean isHasTask() {
		return hasTask;
	}

	public void setHasTask(boolean hasTask) {
		this.hasTask = hasTask;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getN() {
		return n;
	}

	public void setN(Long n) {
		this.n = n;
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

	public Date getActual_begin_time() {
		return actual_begin_time;
	}

	public void setActual_begin_time(Date actual_begin_time) {
		this.actual_begin_time = actual_begin_time;
	}

	public Date getActual_end_time() {
		return actual_end_time;
	}

	public void setActual_end_time(Date actual_end_time) {
		this.actual_end_time = actual_end_time;
	}

	public List<SubProjectVo> getSubProjects() {
		return subProjects;
	}

	public void setSubProjects(List<SubProjectVo> subProjects) {
		this.subProjects = subProjects;
	}

	public Integer getTotal_undistributed() {
		return total_undistributed;
	}

	public void setTotal_undistributed(Integer total_undistributed) {
		this.total_undistributed = total_undistributed;
	}

	public Integer getTotal_running() {
		return total_running;
	}

	public void setTotal_running(Integer total_running) {
		this.total_running = total_running;
	}

	public Integer getTotal_finished() {
		return total_finished;
	}

	public void setTotal_finished(Integer total_finished) {
		this.total_finished = total_finished;
	}

	public Integer getModel_task_total() {
		return model_task_total;
	}

	public void setModel_task_total(Integer model_task_total) {
		this.model_task_total = model_task_total;
	}

	public Integer getModel_task_finished_total() {
		return model_task_finished_total;
	}

	public void setModel_task_finished_total(Integer model_task_finished_total) {
		this.model_task_finished_total = model_task_finished_total;
	}

	public Integer getAnimation_task_total() {
		return animation_task_total;
	}

	public void setAnimation_task_total(Integer animation_task_total) {
		this.animation_task_total = animation_task_total;
	}

	public Integer getAnimation_task_finished_total() {
		return animation_task_finished_total;
	}

	public void setAnimation_task_finished_total(
			Integer animation_task_finished_total) {
		this.animation_task_finished_total = animation_task_finished_total;
	}

	public Integer getLight_task_total() {
		return light_task_total;
	}

	public void setLight_task_total(Integer light_task_total) {
		this.light_task_total = light_task_total;
	}

	public Integer getLight_task_finished_total() {
		return light_task_finished_total;
	}

	public void setLight_task_finished_total(Integer light_task_finished_total) {
		this.light_task_finished_total = light_task_finished_total;
	}

	public String getFinished_rate() {
		return finished_rate;
	}

	public void setFinished_rate(String finished_rate) {
		this.finished_rate = finished_rate;
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public Integer getSubProjectNums() {
		return subProjectNums;
	}

	public void setSubProjectNums(Integer subProjectNums) {
		this.subProjectNums = subProjectNums;
	}

	public Long getUseDate() {
		return useDate;
	}

	public void setUseDate(Long useDate) {
		this.useDate = useDate;
	}

	public Integer getTaskTotalNums() {
		return taskTotalNums;
	}

	public void setTaskTotalNums(Integer taskTotalNums) {
		this.taskTotalNums = taskTotalNums;
	}

	public Integer getProjectManagerNums() {
		return projectManagerNums;
	}

	public void setProjectManagerNums(Integer projectManagerNums) {
		this.projectManagerNums = projectManagerNums;
	}

	public Integer getProjectMakerNums() {
		return projectMakerNums;
	}

	public void setProjectMakerNums(Integer projectMakerNums) {
		this.projectMakerNums = projectMakerNums;
	}

	public double getProjectPrepayment() {
		return projectPrepayment;
	}

	public void setProjectPrepayment(double projectPrepayment) {
		this.projectPrepayment = projectPrepayment;
	}

	public double getProjectPaid() {
		return projectPaid;
	}

	public void setProjectPaid(double projectPaid) {
		this.projectPaid = projectPaid;
	}

	public ProjectTypeVo getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectTypeVo projectType) {
		this.projectType = projectType;
	}

	public FrameRateCompanyVo getFrameRateCompany() {
		return frameRateCompany;
	}

	public void setFrameRateCompany(FrameRateCompanyVo frameRateCompany) {
		this.frameRateCompany = frameRateCompany;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public List<CompanySoftwareVo> getCompanySoftwares() {
		return companySoftwares;
	}

	public void setCompanySoftwares(List<CompanySoftwareVo> companySoftwares) {
		this.companySoftwares = companySoftwares;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ProjectStatusVo getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatusVo projectStatus) {
		this.projectStatus = projectStatus;
	}

	public List<DirectorVo> getDirectors() {
		return directors;
	}

	public void setDirectors(List<DirectorVo> directors) {
		this.directors = directors;
	}

	public List<PrincipalVo> getPrincipals() {
		return principals;
	}

	public void setPrincipals(List<PrincipalVo> principals) {
		this.principals = principals;
	}

	public List<ScreenwriterVo> getScreenwriters() {
		return screenwriters;
	}

	public void setScreenwriters(List<ScreenwriterVo> screenwriters) {
		this.screenwriters = screenwriters;
	}

	public ProjectSearchVo getPsearch() {
		return psearch;
	}

	public void setPsearch(ProjectSearchVo psearch) {
		this.psearch = psearch;
	}

	public SubProjectVo() {
		super();
	}

	public SubProjectVo(Long id) {
		super();
		this.id = id;
	}

	public Long getCompanyFiles_id() {
		return companyFiles_id;
	}

	public void setCompanyFiles_id(Long companyFiles_id) {
		this.companyFiles_id = companyFiles_id;
	}

	public Long getParentFileId() {
		return parentFileId;
	}

	public void setParentFileId(Long parentFileId) {
		this.parentFileId = parentFileId;
	}

	public String getFolder_path() {
		return folder_path;
	}

	public void setFolder_path(String folder_path) {
		this.folder_path = folder_path;
	}

}
