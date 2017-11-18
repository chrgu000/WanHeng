package com.to.page;

import lombok.Data;

/**
 * @Author :yangjun on 2017/3/29 0029.
 */
@Data
public class HousePage extends  Page{
    private String Type;

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
    
}
