package com.cgwas.userEvaluation.entity;

/**
 * @author 模版生成 <br/>
 *         表名： u_user_evaluation <br/>
 *         描述：制作者评价表 <br/>
 */
@SuppressWarnings("serial")
public class UserEvaluationVo extends UserEvaluation {
	protected Long tagcount;
	protected String company_name;
	protected String name;
	protected String name1;

	public UserEvaluationVo() {
		super();
	}

	public UserEvaluationVo(Long id) {
		super();
		this.id = id;
	}

	public Long getTagcount() {
		return tagcount;
	}

	public void setTagcount(Long tagcount) {
		this.tagcount = tagcount;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

}
