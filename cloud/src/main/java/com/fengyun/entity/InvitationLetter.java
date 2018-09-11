package com.fengyun.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;


/**
 * @author yangjun <br/>s
 *         表名： y_invitation_letter <br/> 	
 *         描述：y_invitation_letter <br/>
 */
@SuppressWarnings("serial")
public class InvitationLetter implements Serializable {
	protected Long id;// 主键
	protected Long learner_user_id;// 学员用户id
	protected Long teacher_user_id;// 讲师用户id
	protected String content;// 邀约函正文
	protected String replay_content;// 邀约函回复内容
	protected Date invite_time;// 邀约时间
	protected Date replay_time;// 邀约函回复时间
	protected String is_agree;// 邀约函是否被讲师同意(Y为同意,N为拒绝)
	protected String is_replay;// 邀约函是否被讲师回函(Y为回函,N为未回函)
	protected String is_public;//课程是否公开
	protected Long course_id;// 邀约函绑定的课程id
	protected String refuse_letter;//讲师拒绝邀约函内容
    protected List<LetterImg> letterImgs;
    protected String sendOrReceive;
    protected String img_urls;
    protected UserInfo user;
    
    
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public String getIs_public() {
		return is_public;
	}

	public void setIs_public(String is_public) {
		this.is_public = is_public;
	}

	public String getImg_urls() {
		return img_urls;
	}

	public void setImg_urls(String img_urls) {
		this.img_urls = img_urls;
	}

	public String getSendOrReceive() {
		return sendOrReceive;
	}

	public void setSendOrReceive(String sendOrReceive) {
		this.sendOrReceive = sendOrReceive;
	}

	public String getRefuse_letter() {
		return refuse_letter;
	}

	public void setRefuse_letter(String refuse_letter) {
		this.refuse_letter = refuse_letter;
	}

	public List<LetterImg> getLetterImgs() {
		return letterImgs;
	}

	public void setLetterImgs(List<LetterImg> letterImgs) {
		this.letterImgs = letterImgs;
	}

	public InvitationLetter() {
		super();
	}

	public InvitationLetter(Long id) {
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
	
	public Long getLearner_user_id() {
		return learner_user_id;
	}
	public void setLearner_user_id(Long learner_user_id) {
		this.learner_user_id = learner_user_id;
	}
	
	public Long getTeacher_user_id() {
		return teacher_user_id;
	}
	public void setTeacher_user_id(Long teacher_user_id) {
		this.teacher_user_id = teacher_user_id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getReplay_content() {
		return replay_content;
	}
	public void setReplay_content(String replay_content) {
		this.replay_content = replay_content;
	}
	
	public Date getInvite_time() {
		return invite_time;
	}
	public void setInvite_time(Date invite_time) {
		this.invite_time = invite_time;
	}
	
	public Date getReplay_time() {
		return replay_time;
	}
	public void setReplay_time(Date replay_time) {
		this.replay_time = replay_time;
	}
	
	public String getIs_agree() {
		return is_agree;
	}
	public void setIs_agree(String is_agree) {
		this.is_agree = is_agree;
	}
	
	public String getIs_replay() {
		return is_replay;
	}
	public void setIs_replay(String is_replay) {
		this.is_replay = is_replay;
	}
	
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
}
