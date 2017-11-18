package com.cgwas.projectSearch.entity;

/**
 * @author yangjun <br/>
 *         表名： p_project_search <br/>
 *         描述：p_project_search <br/>
 */
 @SuppressWarnings("serial")
public class ProjectSearchVo extends ProjectSearch {

	public ProjectSearchVo() {
		super();
	}

	public ProjectSearchVo(Long id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProjectSearchVo [id=" + id + ", search=" + search + ", field="
				+ field + ", sort=" + sort + ", content=" + content
				+ ", clazz_id=" + clazz_id + ", user_id=" + user_id
				+ ", company_id=" + company_id + ", flag=" + flag
				+ ", project_id=" + project_id + ", name=" + name
				+ ", label_id=" + label_id + ", sub_project_id="
				+ sub_project_id + ", idsInfo=" + idsInfo + ", degree_id="
				+ degree_id + ", stage=" + stage + ", type=" + type + "]";
	}


}
