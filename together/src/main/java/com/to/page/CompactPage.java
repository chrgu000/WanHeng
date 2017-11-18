package com.to.page;

import lombok.Data;

/**
 * @Author :yangjun on 2017/3/29 0029.
 */
@Data
public class CompactPage extends Page {
    private Short  status;

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
    
}
