package com.cgwas.imgInfo.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： s_img_info <br/>
 *         描述：图片信息表 <br/>
 */
@SuppressWarnings("serial")
public class ImgInfo implements Serializable {
	protected Long id;// 主键
	protected String img_title;// 图片标题
	protected String img_url;// 图片路径
	protected String img_type;// 图片类型
	protected Long for_id;// 所属id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_time;// 创建时间

	public ImgInfo() {
		super();
	}

	public ImgInfo(Long id) {
		super();
		this.id = id;
	}

	@Id
	// 主键
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImg_title() {
		return img_title;
	}

	public void setImg_title(String img_title) {
		this.img_title = img_title;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getImg_type() {
		return img_type;
	}

	public void setImg_type(String img_type) {
		this.img_type = img_type;
	}

	public Long getFor_id() {
		return for_id;
	}

	public void setFor_id(Long for_id) {
		this.for_id = for_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
