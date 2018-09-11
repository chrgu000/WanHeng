package com.fengyun.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： y_letter_img <br/>
 *         描述：y_letter_img <br/>
 */
@SuppressWarnings("serial")
public class LetterImg implements Serializable {
	protected Long id;// 主键
	protected Long letter_id;// 邀约函id
	protected String img_url;// 图片路径

	public LetterImg() {
		super();
	}

	public LetterImg(Long id) {
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
	
	public Long getLetter_id() {
		return letter_id;
	}
	public void setLetter_id(Long letter_id) {
		this.letter_id = letter_id;
	}
	
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
}
