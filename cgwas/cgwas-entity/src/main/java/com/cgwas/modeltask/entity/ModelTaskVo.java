package com.cgwas.modeltask.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cgwas.clazz.entity.Clazz;
import com.cgwas.company.entity.Company;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.degree.entity.Degree;
import com.cgwas.modeltype.entity.ModelType;
import com.cgwas.project.entity.Project;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.publishtype.entity.PublishType;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.userInfo.entity.UserInfo;

/**
 * @author yangjun <br/>
 *         表名： p_model_task <br/>
 *         描述：模型任务表 <br/>
 */
@SuppressWarnings("serial")
public class ModelTaskVo extends ModelTask {
	protected Long id;
	private List<CompanySoftwareVo> companysoftwares;// 使用软件
	private ModelType modeltype;// 模型类型
	private PublishType publishtype;// 发布类型
	private ProjectSearchVo psearch;
	private UserInfo pointUser;// 任务指派者
	private UserInfo maker;// 制作者
	private Degree degree;// 难易度
	private Clazz clazz;// 类目
	private UserInfo last_checker_user;// 任务的上级审查员
	private Company company;
	protected int usedDate;//实际用时
	protected Date actual_begin_time;
    protected Date actual_end_time;
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
    protected Map<String,Object> projectMap;
    protected Long freeEndDay;
    
    public List<Map<String, Object>> getCheckInfos() {
		return checkInfos;
	}
	public void setCheckInfos(List<Map<String, Object>> checkInfos) {
		this.checkInfos = checkInfos;
	}
	public Long getFreeEndDay() {
		return freeEndDay;
	}
	public void setFreeEndDay(Long freeEndDay) {
		this.freeEndDay = freeEndDay;
	}
	//放弃所属者，company动画公司，maker制作者
    public String getCurrentChecker() {
		return currentChecker;
	}
	public Map<String, Object> getProjectMap() {
		return projectMap;
	}
	public void setProjectMap(Map<String, Object> projectMap) {
		this.projectMap = projectMap;
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
	protected Long companyFiles_id;
    
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ModelTaskVo other = (ModelTaskVo) obj;
		if (clazz == null) {
			if (other.clazz != null) {
				return false;
			}
		} else if (!clazz.equals(other.clazz)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<UserInfo> getUserInfos() {
		return userInfos;
	}
	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
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
	public int getUsedDate() {
		return usedDate;
	}
	public void setUsedDate(int usedDate) {
		this.usedDate = usedDate;
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
	public List<CompanySoftwareVo> getCompanysoftwares() {
		return companysoftwares;
	}
	public void setCompanysoftwares(List<CompanySoftwareVo> companysoftwares) {
		this.companysoftwares = companysoftwares;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
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

	public UserInfo getLast_checker_user() {
		return last_checker_user;
	}

	public void setLast_checker_user(UserInfo last_checker_user) {
		this.last_checker_user = last_checker_user;
	}


	public ModelType getModeltype() {
		return modeltype;
	}

	public void setModeltype(ModelType modeltype) {
		this.modeltype = modeltype;
	}

	public PublishType getPublishtype() {
		return publishtype;
	}

	public void setPublishtype(PublishType publishtype) {
		this.publishtype = publishtype;
	}

	public ModelTaskVo() {
		super();
	}

	public ModelTaskVo(Long id) {
		super();
		this.id = id;
	}
	public Long getCompanyFiles_id() {
		return companyFiles_id;
	}
	public void setCompanyFiles_id(Long companyFiles_id) {
		this.companyFiles_id = companyFiles_id;
	}
}
