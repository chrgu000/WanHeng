package com.cgwas.companyEvaluation.entity;

/**
 * @author 模版生成 <br/>
 *         表名： u_company_evaluation <br/>
 *         描述：公司评价表 <br/>
 */
@SuppressWarnings("serial")
public class CompanyEvaluationVo extends CompanyEvaluation {
	protected String modelname; // 任务名

	protected String username; // 用户名

	protected String namelight; // 灯光任务名

	public CompanyEvaluationVo() {
		super();
	}

	public CompanyEvaluationVo(Long id) {
		super();
		this.id = id;
	}

	

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNamelight() {
		return namelight;
	}

	public void setNamelight(String namelight) {
		this.namelight = namelight;
	}

}
