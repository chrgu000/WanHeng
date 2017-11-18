package com.cgwas.projectSearch.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

/**
 * @author yangjun <br/>s
 *         表名： p_project_search <br/>
 *         描述：p_project_search <br/>
 */
@SuppressWarnings("serial")
public class ProjectSearch implements Serializable {
	protected Long id;// id
	protected String search;// search
	protected String field;// field
	protected String sort;// sort
    protected String content;//content
    protected Long clazz_id;//clazz_id
    protected Long user_id;
    protected Long company_id;
    protected String flag;
    protected Long project_id;
    protected String name;
    protected Long label_id;
    protected Long sub_project_id;
    protected Map<String,Object> idsInfo;
    protected Long degree_id;
    protected String stage;
    protected String type;
    
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Long getDegree_id() {
		return degree_id;
	}

	public void setDegree_id(Long degree_id) {
		this.degree_id = degree_id;
	}

	public Long getSub_project_id() {
		return sub_project_id;
	}

	public Map<String, Object> getIdsInfo() {
		return idsInfo;
	}

	public void setIdsInfo(Map<String, Object> idsInfo) {
		this.idsInfo = idsInfo;
	}

	public void setSub_project_id(Long sub_project_id) {
		this.sub_project_id = sub_project_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getLabel_id() {
		return label_id;
	}
	public void setLabel_id(Long label_id) {
		this.label_id = label_id;
	}

	public Long getProject_id() {
		return project_id;
	}
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public ProjectSearch() {
		super();
	}

	public ProjectSearch(Long id) {
		super();
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getClazz_id() {
		return clazz_id;
	}
	

	public void setClazz_id(Long clazz_id) {
		this.clazz_id = clazz_id;
	}

	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "ProjectSearch [id=" + id + ", search=" + search + ", field="
				+ field + ", sort=" + sort + ", content=" + content
				+ ", clazz_id=" + clazz_id + ", user_id=" + user_id
				+ ", company_id=" + company_id + ", flag=" + flag
				+ ", project_id=" + project_id + ", name=" + name
				+ ", label_id=" + label_id + ", sub_project_id="
				+ sub_project_id + ", idsInfo=" + idsInfo + ", degree_id="
				+ degree_id + ", stage=" + stage + "]";
	}
	
}
