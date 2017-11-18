package com.cgwas.userQuestion.entity;



import javax.persistence.Id;
import java.io.Serializable;

/**
 * 密保问题实体类
 * @author Lingwh
 *
 */
@SuppressWarnings("serial")
public class UserQuestion implements Serializable {
	protected Long id;// id
	protected String question1;// question1
	protected String question2;// question2
	protected String question3;// question3
	protected String answer1;// answer1
	protected String answer2;// answer2
	protected String answer3;// answer3
	protected Long user_id; // 用户id

	public UserQuestion() {
		super();
	}

	public UserQuestion(Long id) {
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
	
	public String getQuestion1() {
		return question1;
	}
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}
	
	public String getQuestion2() {
		return question2;
	}
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}
	
	public String getQuestion3() {
		return question3;
	}
	public void setQuestion3(String question3) {
		this.question3 = question3;
	}
	
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
}
