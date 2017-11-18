package com.kg.page;

public class PicturePage extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer baby_id;
    @Override
	public String toString() {
		return "PicturePage [baby_id=" + baby_id + ", style_id=" + style_id
				+ ", type_id=" + type_id + "]";
	}

	public Integer getStyle_id() {
		return style_id;
	}

	public void setStyle_id(Integer styleId) {
		style_id = styleId;
	}

	private Integer type_id;
    private Integer style_id;
    
    
	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer typeId) {
		type_id = typeId;
	}

	public Integer getBaby_id() {
		return baby_id;
	}

	public void setBaby_id(Integer babyId) {
		baby_id = babyId;
	}

}
