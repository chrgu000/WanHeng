package com.cgwas.companyFiles.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_company_files <br/>
 *         描述：公司文件夹表 <br/>
 */
@SuppressWarnings("serial")
public class CompanyFiles implements Serializable {
	protected Long id;// 主键
	protected String file_name;// 文件(夹)名称
	protected Long file_size;// 文件大小
	protected String file_url;// 文件路径
	protected Integer sort;// 排序
	protected String task_type;// 任务类型（MODEL_TASK .模型任务 ANIMATION_LIGHT_TASK. 动画灯光）
	protected String file_type;// 文件类别（项目，子项目，任务）
	protected String is_file;// 是否文件夹
	protected Long for_id;// 所属id
	protected Long parent_id;// 父id
	protected Long creator_id;//创建者id
	protected Long company_id;// 公司id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date update_time;// 修改时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date create_time;// 创建时间

	public CompanyFiles() {
		super();
	}

	public CompanyFiles(Long id) {
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
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	
	public String getIs_file() {
		return is_file;
	}
	public void setIs_file(String is_file) {
		this.is_file = is_file;
	}
	
	public Long getFor_id() {
		return for_id;
	}
	public void setFor_id(Long for_id) {
		this.for_id = for_id;
	}
	
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Long creator_id) {
		this.creator_id = creator_id;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public Long getFile_size() {
		return file_size;
	}

	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}
}
