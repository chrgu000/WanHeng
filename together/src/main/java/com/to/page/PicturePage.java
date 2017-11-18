package com.to.page;

import lombok.Data;

@Data
public class PicturePage extends Page {
	private static final long serialVersionUID = 1L;
	private Integer houseId;
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
