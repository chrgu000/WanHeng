package com.cgwas.projectStatus.entity;

/**
 * @author yangjun <br/>
 *         表名： p_project_status <br/>
 *         描述：项目状态表 <br/>
 */
 @SuppressWarnings("serial")
public class ProjectStatusVo extends ProjectStatus {

	public ProjectStatusVo() {
		super();
	}

	public ProjectStatusVo(Long id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProjectStatusVo [id=" + id + ", content=" + content
				+ ", color=" + color + "]";
	}


}
